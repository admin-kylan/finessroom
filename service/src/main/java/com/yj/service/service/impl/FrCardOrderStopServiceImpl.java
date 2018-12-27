package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.*;
import com.yj.dal.model.*;
import com.yj.dal.dao.FrCardOrderStopMapper;
import com.yj.service.service.*;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 会员卡 停止/冻结订单 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
@Service
public class FrCardOrderStopServiceImpl extends BaseServiceImpl<FrCardOrderStopMapper, FrCardOrderStop> implements IFrCardOrderStopService {

    @Resource
    private IFrCardService iFrCardService;
    @Resource
    private IFrCardOrderStopPicService iFrCardOrderStopPicService;
    @Resource
    private IFrCardOrderPayModeService iFrCardOrderPayModeService;
    @Resource
    private IFrCardOrderPriceDatailService iFrCardOrderPriceDatailService;

    @Override
    public JsonResult getStopCardListByType(FrCardOrderStop frCardOrderStop) throws YJException {
        if(frCardOrderStop == null){
            throw  new  YJException(YJExceptionEnum.OBJECT_NOT_FOUND);
        }
        //获取会员卡设置信息
        Map<String,Object> frC = this.getFrCardData(frCardOrderStop);
        Map<String,Object> map = new HashMap<>();
        //获取停卡数据
        List<Map<String,Object>> frCardOriginalSets = baseMapper.getStopCardListByType(frCardOrderStop);
        //统计有效的停卡数据，停卡时间
        this.getFrStopCount(frCardOriginalSets,map);
        map.put("frCard",frC);
        map.put("list",frCardOriginalSets);
        return JsonResult.success(map);
    }

    public Map<String,Object> getFrCardData(FrCardOrderStop frCardOrderStop)throws YJException{
        //获取会员卡的设置信息
        FrCard frCard = new FrCard();
        frCard.setId(frCardOrderStop.getCardId());
        frCard.setCustomerCode(frCardOrderStop.getCustomerCode());
        //获取会员卡设置信息
        Map<String,Object> frC = iFrCardService.queryCollectionOriginalSet(frCard);
        return frC;
    }

    public void getFrStopCount(List<Map<String,Object>> frCardOriginalSets,Map<String,Object> map){
        //已使用次数
        Integer realStopNum = 0;
        //已经停卡天数（根据实际的）
        Integer realTotalNum = 0,estStopTime = 0,stopTime = 0;
        if(frCardOriginalSets != null && frCardOriginalSets.size()>0){
            for(Map<String,Object> mapT:frCardOriginalSets){
                String priceStatus = mapT.get("priceStatus").toString();
                if(!"2".equals(priceStatus)){
                    String stopStatus = mapT.get("stopStatus").toString();
                    if("1".equals(stopStatus)){
                        Object estTim =  mapT.get("estStopTime");
                        if(estTim != null && estTim instanceof Integer){
                            estStopTime += ((Integer)estTim);
                        }
                    }
                    //已经停止、终止的
                    if("2".equals(stopStatus)){
                        Object sTim =  mapT.get("stopTime");
                        if(sTim != null && sTim instanceof Integer){
                            stopTime += ((Integer)sTim);
                        }
                    }
                    realStopNum +=1;
                }
            }
        }
        realTotalNum = estStopTime + stopTime;
        map.put("realTotalNum",realTotalNum);
        map.put("realStopNum",realStopNum);
    }

    /**
     * 插入停卡数据
     * @param frCardOrderStop
     * @param list
     * @param imagePath
     * @param imagesList
     * @param shopId
     * @return
     * @throws YJException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult toInterCardOrderStop(FrCardOrderStop frCardOrderStop, List<FrCardOrderPayMode> list, String imagePath, List<String> imagesList,String shopId) throws YJException {
        if (frCardOrderStop == null) {
            return JsonResult.failMessage("数据获取异常");
        }
        if(StringUtils.isEmpty(frCardOrderStop.getCardId()) || StringUtils.isEmpty(frCardOrderStop.getClientId())
                || StringUtils.isEmpty(frCardOrderStop.getCustomerCode()) || StringUtils.isEmpty(frCardOrderStop.getPersonnelId())
                || frCardOrderStop.getStartTime() == null || frCardOrderStop.getEstEndTime() == null
                || frCardOrderStop.getEstStopTime() == null){
            return   JsonResult.failMessage("会员卡ID,客户ID，type状态，开始时间未获取");
        }
        if(frCardOrderStop.getTotalPrice() != null && frCardOrderStop.getTotalPrice() >0){
            if(list == null || list.size() <= 0){
                return  JsonResult.failMessage("支付金额不足收费金额");
            }
        }
        if(list != null && list.size() >0){
            Double orderPrice = 0.0;
            for(FrCardOrderPayMode frCardOrderPayMode:list){
                Double price = frCardOrderPayMode.getPayPrice();
                if(price != null && price instanceof Double){
                    orderPrice += price;
                }
            }
            frCardOrderStop.setTotalPrice(orderPrice);
        }
        //获取会员卡设置信息
        Map<String,Object> frC = this.getFrCardData(frCardOrderStop);
        Map<String,Object> map = new HashMap<>();
        //已使用次数
        Integer realStopNum = 0;
        //已经停卡时长（根据实际的）
        Integer realTotalNum = 0;
        Integer cardType = null;
        if( frC.get("type")!= null && frC.get("type") instanceof Integer){
            cardType = (Integer) frC.get("type");
        }
        if(frC != null ){
            String status = frC.get("status").toString();
            if(CommonUtils.CARD_STATUS_2.toString().equals(status) || CommonUtils.CARD_STATUS_3.toString().equals(status) || CommonUtils.CARD_STATUS_4.toString().equals(status) || CommonUtils.CARD_STATUS_6.toString().equals(status)){
                return JsonResult.failMessage("会员卡状态冻结、过期、未开卡、历史，不可操作");
            }
            //获取停卡数据
            List<Map<String,Object>> frCardOriginalSets = baseMapper.getStopCardListByType(frCardOrderStop);
            //统计有效的停卡数据，停卡时间
            this.getFrStopCount(frCardOriginalSets,map);
        }
        if(map != null ){
            //获取已经停卡次数
            Object alStopNum = map.get("realStopNum");
            //获取实际停卡时长
            Object alTotalNum = map.get("realTotalNum");
            if(alStopNum != null && alStopNum instanceof Integer){
                realStopNum = (Integer)alStopNum;
            }
            if(alTotalNum != null && alTotalNum instanceof Integer){
                realTotalNum = (Integer)alTotalNum;
            }
        }
        //每次最多停卡天数
        Integer stopDays = 0;
        //可停卡总时长
        Integer totalDays = 0;
        // 超出部分的价格
        Double outPrice = 0.0;
        if(realStopNum.toString().equals(frC.get("stopNum"))){
            return  JsonResult.failMessage("停卡次数已满");
        }
        if(frC.get("stopDays") != null && frC.get("stopDays") instanceof Integer ){
            stopDays = (Integer) frC.get("stopDays");
        }
        if(frCardOrderStop.getEstStopTime() > stopDays){
            return JsonResult.failMessage("已超出每次最长停卡时长，请重新设置");
        }
        if(frC.get("totalDays") != null && frC.get("totalDays") instanceof Integer ){
            totalDays = (Integer) frC.get("totalDays");
        }
        if(frCardOrderStop.getEstStopTime() > (totalDays-realTotalNum)){
            if(frC.get("outPrice") != null  ){
                outPrice = Double.valueOf(frC.get("outPrice").toString());
            }
            Integer num = frCardOrderStop.getEstStopTime()-(totalDays-realTotalNum);
            Double  price = Double.parseDouble(num.toString()) * outPrice;
            if(frCardOrderStop.getTotalPrice() != null){
                System.out.println("frCardOrderStop.getTotalPrice()============:"+frCardOrderStop.getTotalPrice());
                System.out.println("price.toString()============:"+price.toString());
                if(!frCardOrderStop.getTotalPrice().toString().equals(price.toString())){
                    return  JsonResult.failMessage("收费标准不统一");
                }
            }
        }
        //获取失效时间
        String invalidTime = frC.get("invalidTime").toString();
        Date currdate = DateUtil.stringToDate(invalidTime,"yyyy-MM-dd HH:mm:ss");
        currdate = DateUtil.getAddForDays(currdate,frCardOrderStop.getEstStopTime());
        invalidTime =DateUtil.dateToString(currdate,"yyyy-MM-dd HH:mm:ss");
        //判断开始时间是否今天
        Date startTime = frCardOrderStop.getStartTime();
        Date isNow = new Date();
        Integer status = CommonUtils.CARD_STATUS_0;
        // 不是马上停卡
        if(startTime.getTime() < isNow.getTime()){
            status =  CommonUtils.CARD_STATUS_1;
        }
        //初始化停卡信息
        frCardOrderStop.setUsing(true);
        frCardOrderStop.setType(1);
        frCardOrderStop.setPriceStatus(1);
        frCardOrderStop.setStopStatus(status);
        frCardOrderStop.setId(UUIDUtils.generateGUID());
        frCardOrderStop.setInvalidTime(invalidTime);
        frCardOrderStop.setStatus(CommonUtils.ORDER_STATUS_0);
        frCardOrderStop.setAuditStatus(CommonUtils.AUDIT_ORDER_STATUS_0);
        //初始化停卡图片
        List<FrCardOrderStopPic> frCardOrderStopPics = new ArrayList<>();
        FrCardOrderStopPic frCardOrderStopPic = new FrCardOrderStopPic();
        StringBuffer imageBuff = new StringBuffer(imagePath);
        if(imagesList != null && imagesList.size() >0){
            for(String imgUrl:imagesList){
                frCardOrderStopPic.setId(UUIDUtils.generateGUID());
                frCardOrderStopPic.setStopId(frCardOrderStop.getId());
                frCardOrderStopPic.setPicState(true);
                frCardOrderStopPic.setPicLink(imageBuff.append(imgUrl).toString());
                frCardOrderStopPic.setCreateTime(new Date());
                frCardOrderStopPics.add(frCardOrderStopPic);
                frCardOrderStopPic = new FrCardOrderStopPic();
                imageBuff = new StringBuffer(imagePath);
            }
        }
        FrCard frCard1 = new FrCard();
        frCard1.setInvalidTime(invalidTime);
        //会员卡停卡
        if(CommonUtils.CARD_STATUS_1 == status){
            //更新会员卡的失效日期准备
            frCard1.setStatus(status);
        }
        boolean su = iFrCardService.update(frCard1,new EntityWrapper<FrCard>().where("id={0}",frCardOrderStop.getCardId())
                .and("client_id={0}",frCardOrderStop.getClientId()).and("CustomerCode={0}",frCardOrderStop.getCustomerCode()));
        if(!su){
            throw  new YJException(YJExceptionEnum.REQUEST_NULL);
        }
        baseMapper.insert(frCardOrderStop);
        if(frCardOrderStopPics != null && frCardOrderStopPics.size()>0 ){
            for(FrCardOrderStopPic frCardOrderStopPic1:frCardOrderStopPics){
                iFrCardOrderStopPicService.insert(frCardOrderStopPic1);
            }
        }
        if(list != null && list.size()>0){
            this.getCardDatailAndPrice(frCardOrderStop,false,cardType,shopId);
            for(FrCardOrderPayMode frCardOrderPayMode:list){
                frCardOrderPayMode.setId(UUIDUtils.generateGUID());
                frCardOrderPayMode.setOrderId(frCardOrderStop.getId());
                iFrCardOrderPayModeService.insert(frCardOrderPayMode);
            }
        }
        return JsonResult.success(true);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult toAddStopType(FrCardOrderStop frCardOrderStop) throws YJException {
        if (frCardOrderStop == null) {
            return JsonResult.failMessage("数据获取异常");
        }
        if(StringUtils.isEmpty(frCardOrderStop.getPersonnelId())|| StringUtils.isEmpty(frCardOrderStop.getCustomerCode())
                || StringUtils.isEmpty(frCardOrderStop.getClientId()) || StringUtils.isEmpty(frCardOrderStop.getCardId())
                || StringUtils.isEmpty(frCardOrderStop.getFlag()) || frCardOrderStop.getStopStatus() == null){
            return JsonResult.failMessage("数据获取失败");
        }
        //操作是冻结
        frCardOrderStop.setType(2);
        //获取会员卡设置信息
        Map<String,Object> frC = this.getFrCardData(frCardOrderStop);
        if(frC != null ){
            String status = frC.get("status").toString();
            if( ("3").equals(status) || "4".equals(status) || "6".equals(status)){
                return JsonResult.failMessage("会员卡状过期、未开卡、历史，不可操作");
            }
            if("1".equals(frCardOrderStop.getStopStatus())  && "2".equals(status)){
                 return  JsonResult.failMessage("已经冻结的卡，不能重复冻结");
            }
        }
        FrCardOrderStop frCardOrderStopT = new FrCardOrderStop();
        frCardOrderStopT.setType(2);
        frCardOrderStopT.setStopStatus(1);
        frCardOrderStopT = baseMapper.selectOne(frCardOrderStopT);
        Date data = new Date();
        FrCard frCard = new FrCard();
        if(frCardOrderStop.getStopStatus() == 1){
            if(frCardOrderStopT != null){
              return  JsonResult.failMessage("不能重复冻结");
            }
            //冻结时间就是停卡时间
            frCardOrderStop.setStartTime(data);
            //冻结不需要付款
            frCardOrderStop.setPriceStatus(1);
            // 默认启用状态
            frCardOrderStop.setUsing(true);
            //冻结直接免除审核，审核人就是操作人员
            frCardOrderStop.setStatus(1);
            frCardOrderStop.setAuditStatus(1);
            frCardOrderStop.setStatusUserId(frCardOrderStop.getPersonnelId());
            frCardOrderStop.setAuditUserId(frCardOrderStop.getPersonnelId());
            frCardOrderStop.setId(UUIDUtils.generateGUID());
            frCard.setStatus(2);
            baseMapper.insert(frCardOrderStop);
        }
        if(frCardOrderStop.getStopStatus() == 2 ){
            if(frCardOrderStopT == null){
                return  JsonResult.failMessage("查无需要解冻的数据");
            }
            FrCardOrderStop frCardOrderStop1 = new FrCardOrderStop();
            frCardOrderStop1.setEndTime(data);
            frCardOrderStop1.setStopUserId(frCardOrderStop.getPersonnelId());
            frCardOrderStop1.setStopStatus(2);
            baseMapper.update(frCardOrderStop1,
                    new EntityWrapper<FrCardOrderStop>().where("card_id={0}",frCardOrderStop.getCardId())
                            .and("type={0}",2).and("id={0}",frCardOrderStopT.getId())
                            .and("is_using={0}",1).and("client_id={0}",frCardOrderStop.getClientId()));
            frCard.setStatus(0);
        }
        iFrCardService.update(frCard,new EntityWrapper<FrCard>().where("id={0}",frCardOrderStop.getCardId())
                .and("client_id ={0}",frCardOrderStop.getClientId())
                .and("is_using={0}",1)
                .and("CustomerCode ={0}",frCardOrderStop.getCustomerCode()));
        return JsonResult.success(true);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult toUpdateStopType(FrCardOrderStop frCardOrderStop,String shopId) throws YJException {
        if (frCardOrderStop == null) {
            return JsonResult.failMessage("数据获取异常");
        }
        if( CommonUtils.ORDER_STATUS_1 == frCardOrderStop.getStatus() || CommonUtils.AUDIT_ORDER_STATUS_1 == frCardOrderStop.getAuditStatus() ){
            return JsonResult.failMessage("该数据已审核通过，禁止冲销");
        }
        Integer success = 0;
        FrCardOrderStop frCardOrderStop1 = new FrCardOrderStop();
        frCardOrderStop1.setPriceStatus(CommonUtils.ORDER_TYPE_2);
        //获取会员卡有效时间
        FrCard frCard = iFrCardService.selectById(frCardOrderStop.getCardId());
        if(frCard == null){
            return  JsonResult.failMessage("会员卡数据有误，撤销失败");
        }
        //获取失效时间--以会员卡的为准
        String invalidTime = frCard.getInvalidTime();
        //获取停卡时长 --- 根据预计停卡时长
        Integer day  = frCardOrderStop.getEstStopTime();
        //获取实际停卡时长
        Integer endDay = frCardOrderStop.getStopTime();
        //获取停卡状态  --- 0：正常 1：停卡 2：终止
        Integer isFlag = frCardOrderStop.getStopStatus();
        //如果是未开始前撤销回复正常状态
        String invalidTimeNow = "";
        Integer stopStatus = CommonUtils.CARD_STATUS_0;
        Integer status = CommonUtils.ORDER_STATUS_0;
        Integer auditStatus = CommonUtils.AUDIT_ORDER_STATUS_0;
        invalidTimeNow = DateUtil.getBeforeDay(invalidTime,day,true);
        boolean toType = true;
        //如果该数据已终止，获取停卡状态设置的失效时间
        if(CommonUtils.STOP_STATUS_2 == isFlag || CommonUtils.STOP_STATUS_3 == isFlag || CommonUtils.STOP_STATUS_4 == isFlag ){
            toType = false;
            if(CommonUtils.STOP_STATUS_3 == isFlag ){
                status = CommonUtils.ORDER_STATUS_1;
            }
            if(CommonUtils.STOP_STATUS_4 == isFlag ){
                status = CommonUtils.ORDER_STATUS_1;
                auditStatus = CommonUtils.AUDIT_ORDER_STATUS_1;
            }
            //已经终止的获取实际停卡时长;
            stopStatus = CommonUtils.CARD_STATUS_1;
            //先减去实际停卡时长
            invalidTime = DateUtil.getBeforeDay(invalidTime,endDay,true);
            //再加上预计停卡时长  --- 获取停卡状态时长
            invalidTimeNow = DateUtil.getBeforeDay(invalidTime,day,false);
            frCardOrderStop1.setPriceStatus(CommonUtils.ORDER_TYPE_1);
            frCardOrderStop1.setInvalidTime(invalidTimeNow);
        }
        frCardOrderStop1.setStatus(status);
        frCardOrderStop1.setAuditStatus(auditStatus);
        frCardOrderStop1.setStopStatus(stopStatus);
        FrCard frCard1 = new FrCard();
        frCard1.setInvalidTime(invalidTimeNow);
        frCard1.setStatus(stopStatus);
        //更新会员卡的失效时间
        boolean su = iFrCardService.update(frCard1,new EntityWrapper<FrCard>().where("id={0}",frCardOrderStop.getCardId())
                .and("client_id={0}",frCardOrderStop.getClientId()).and("CustomerCode={0}",frCardOrderStop.getCustomerCode()));
        if(!su){
            throw  new YJException(YJExceptionEnum.REQUEST_NULL);
        }
        //撤销停卡数据
        baseMapper.update(frCardOrderStop1,new EntityWrapper<FrCardOrderStop>().where("id={0}",frCardOrderStop.getId())
                .and("card_id={0}",frCardOrderStop.getCardId()).and("client_id={0}",frCardOrderStop.getClientId())
                .and("CustomerCode={0}",frCardOrderStop.getCustomerCode()));
        // 若是终止冲销，不更新交易明细
        if(toType){
            //资金交易明细，订单交易明细插入一条撤销数据
            if(frCardOrderStop.getTotalPrice() != null && frCardOrderStop.getTotalPrice() >0){
                this.getCardDatailAndPrice(frCardOrderStop,true,frCard.getType(),shopId);
            }
        }
        return JsonResult.success(SqlHelper.retBool(success));
    }

    /**
     * 明细表插入数据
     * @param frCardOrderStop
     * @param orderStatus
     * @param cardType
     * @param shopId
     * @return
     * @throws YJException
     */
    @Transactional(rollbackFor = Exception.class)
    public FrCardOrderPriceDatail getCardDatailAndPrice(FrCardOrderStop frCardOrderStop,boolean orderStatus,Integer cardType,String shopId) throws  YJException{
        FrCardOrderPriceDatail frCardOrderPriceDatail = null;
        if(frCardOrderStop != null){
            //初始化交易明细数据
            frCardOrderPriceDatail = new FrCardOrderPriceDatail();
            frCardOrderPriceDatail.setId(UUIDUtils.generateGUID());
            frCardOrderPriceDatail.setCustomerCode(frCardOrderStop.getCustomerCode());
            frCardOrderPriceDatail.setCardId(frCardOrderStop.getCardId());
            frCardOrderPriceDatail.setClientId(frCardOrderStop.getClientId());
            frCardOrderPriceDatail.setCardType(cardType);
            frCardOrderPriceDatail.setOrderStatus(orderStatus);
            Double orderPrice = iFrCardOrderPriceDatailService.getOrderPriceByOrderStatus(orderStatus,frCardOrderStop.getTotalPrice());
            frCardOrderPriceDatail.setOrderPrice(orderPrice);
            frCardOrderPriceDatail.setShopId(shopId);
            frCardOrderPriceDatail.setPersonnelId(frCardOrderStop.getPersonnelId());
            frCardOrderPriceDatail.setStatus(CommonUtils.ORDER_STATUS_0);
            frCardOrderPriceDatail.setAuditStatus(CommonUtils.AUDIT_ORDER_STATUS_0);
            frCardOrderPriceDatail.setOrderType(CommonUtils.PAY_MODE_ORDER_TYPE_8);
            frCardOrderPriceDatail.setOrderId(frCardOrderStop.getId());
            iFrCardOrderPriceDatailService.insert(frCardOrderPriceDatail);
        }
        return frCardOrderPriceDatail;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult toStopTerminationOK(FrCardOrderStop frCardOrderStop, String shopId) throws YJException {
        if(frCardOrderStop == null){
            return  JsonResult.failMessage("参数获取有误");
        }
        if(StringUtils.isEmpty(frCardOrderStop.getId())|| StringUtils.isEmpty(frCardOrderStop.getCustomerCode()) || StringUtils.isEmpty(frCardOrderStop.getCardId())
                || StringUtils.isEmpty(frCardOrderStop.getClientId()) || StringUtils.isEmpty(frCardOrderStop.getStopUserId())){
            return  JsonResult.failMessage("设置的参数有误：订单id，客户代码，会员卡id，客户id，终止操作人员id");
        }
        // 验证会员卡是否存在？
        FrCard frCard = iFrCardService.selectOne(new EntityWrapper<FrCard>().where("id={0}",frCardOrderStop.getCardId())
        .and("is_using={0}",1).and("client_id={0}",frCardOrderStop.getClientId())
                .and("CustomerCode={0}",frCardOrderStop.getCustomerCode()));
        if(frCard == null){
            return JsonResult.failMessage("会员卡信息有误");
        }
        //不是正常卡状态，不是停卡状态，禁止操作
        if(CommonUtils.CARD_STATUS_1 != frCard.getStatus() && CommonUtils.CARD_STATUS_0 != frCard.getStatus()){
            return  JsonResult.failMessage("请核对卡状态，不是正常的卡，不是停卡状态的卡，禁止操作");
        }
        //查询停卡信息
        FrCardOrderStop frCardOrderStop1 = new FrCardOrderStop();
        frCardOrderStop1.setId(frCardOrderStop.getId());
        frCardOrderStop1.setCustomerCode(frCardOrderStop.getCustomerCode());
        frCardOrderStop1.setCardId(frCardOrderStop.getCardId());
        frCardOrderStop1.setClientId(frCardOrderStop.getClientId());
        frCardOrderStop1.setType(CommonUtils.STOP_TYPE_1);
        frCardOrderStop1 = baseMapper.selectOne(frCardOrderStop1);
        if(frCardOrderStop1 == null){
            return  JsonResult.failMessage("查无此次停卡数据");
        }
        // 验证数据是否启用
        if(frCardOrderStop1.getStopStatus() != null && CommonUtils.STOP_STATUS_1 != frCardOrderStop1.getStopStatus()){
            return JsonResult.failMessage("只有已经停卡的数据才能终止");
        }
        if(CommonUtils.ORDER_TYPE_2 == frCardOrderStop1.getPriceStatus()){
            return  JsonResult.failMessage("已冲销的数据禁止操作");
        }
        //开始停卡的时间
        Date startTime = frCardOrderStop1.getStartTime();
        //实际停卡时间
        Date entTime = new Date();
        if(frCardOrderStop.getEndTime() != null){
            entTime = frCardOrderStop.getEndTime();
        }
        // 获取数据预计停卡时长
        Integer estStopTime = frCardOrderStop1.getEstStopTime();
        //实际停卡时长的 --- 终止时间- 开始停卡时间
        Integer stopTime = frCardOrderStop.getStopTime();
        Integer stopTimeT = DateUtil.daysBetweenThree(startTime,entTime);
        if(stopTimeT != stopTime){
            return JsonResult.failMessage("实际停卡时间计算有误");
        }
        //获取失效时间 ------------ 以会员卡失效时间为准
        String invalidTime = DateUtil.getBeforeDay(frCard.getInvalidTime(),estStopTime,true);
        invalidTime = DateUtil.getBeforeDay(invalidTime,stopTime,false);
        //更新会员卡状态准备
        FrCard frCard1 = new FrCard();
        frCard1.setInvalidTime(invalidTime);
        frCard1.setStatus(CommonUtils.CARD_STATUS_0);
        //更新终止状态准备
        String f = frCardOrderStop1.getFlag();
        String s = frCardOrderStop.getFlag();
        StringBuffer flag = new StringBuffer();
        if(!StringUtils.isEmpty(f)){
             flag .append("停卡原因：");
             flag.append(f).append(";  ");
        }
        if(!StringUtils.isEmpty(s)){
            flag.append("终止原因：").append(s);
        }
        f = flag.toString();
        frCardOrderStop.setFlag(f);
        frCardOrderStop.setInvalidTime(invalidTime);
        frCardOrderStop.setEndTime(entTime);
        Integer stopStatus =  CommonUtils.STOP_STATUS_2;
        if(CommonUtils.ORDER_STATUS_1 == frCardOrderStop1.getStatus()){
            stopStatus =  CommonUtils.STOP_STATUS_3;
            if(CommonUtils.AUDIT_ORDER_STATUS_1 == frCardOrderStop1.getAuditStatus()){
                stopStatus =  CommonUtils.STOP_STATUS_4;
            }
        }
        //先判断审核状态是否之前有审核通过的？
        frCardOrderStop.setStopStatus(stopStatus);
        frCardOrderStop.setStatus(CommonUtils.ORDER_STATUS_0);
        frCardOrderStop.setAuditStatus(CommonUtils.AUDIT_ORDER_STATUS_0);
        //更新数据
        boolean su = iFrCardService.update(frCard1,new EntityWrapper<FrCard>().where("id={0}",frCardOrderStop.getCardId())
                .and("client_id={0}",frCardOrderStop.getClientId()).and("CustomerCode={0}",frCardOrderStop.getCustomerCode()));
        if(!su){
            throw  new YJException(YJExceptionEnum.REQUEST_NULL);
        }
        baseMapper.update(frCardOrderStop,new EntityWrapper<FrCardOrderStop>().where("id ={0}",frCardOrderStop1.getId())
        .and("is_using={0}",1).and("CustomerCode={0}",frCardOrderStop1.getCustomerCode())
                .and("card_id={0}",frCardOrderStop1.getCardId()).and("client_id={0}",frCardOrderStop1.getClientId()));
        return JsonResult.success(true);
    }

    //根据开始停卡时间，-- 停卡状态是正常的 更新为停卡----会员卡停卡
    //根据预计终止时间------停卡状态未停卡中的 --更新未终止，会员卡正常
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCardStop() throws YJException {
       //根据条件先查询出所有复核条件的数据
        Date date = new Date();
        String nowTime = DateUtil.dateToString(date,DateUtil.NORMAL_FORM);
        List<FrCardOrderStop> frCardOrderStopList = baseMapper.queryStopTimeList(nowTime);
       //存放要跟新为停卡的会员卡
        List<String> stopCardList = new ArrayList<>();
       //存放要跟新为正常卡的会员卡
        List<String> openCardList = new ArrayList<>();
        //存放要跟新的停卡记录
        List<FrCardOrderStop> updateFrCardOrderStop = new ArrayList<>();
        if(frCardOrderStopList != null  &&  frCardOrderStopList.size() > 0){
            //表示有可能有数据需要更新
            for(FrCardOrderStop frCardOrderStop:frCardOrderStopList){
                FrCardOrderStop frCardOrderStop1 = this.getStopTimeList(frCardOrderStop,date);
                if(frCardOrderStop1 !=  null){
                    updateFrCardOrderStop.add(frCardOrderStop1);
                    if(CommonUtils.STOP_STATUS_1 == frCardOrderStop1.getStopStatus()){
                        stopCardList.add(frCardOrderStop1.getCardId());
                    }
                    if(CommonUtils.STOP_STATUS_2 == frCardOrderStop1.getStopStatus() ||
                            CommonUtils.STOP_STATUS_3 == frCardOrderStop1.getStopStatus() ||
                            CommonUtils.STOP_STATUS_4 == frCardOrderStop1.getStopStatus()){
                        openCardList.add(frCardOrderStop1.getCardId());
                    }
                }
            }
        }
        //准备更新会员卡停卡数据
        if( stopCardList != null && stopCardList.size()>0){
            iFrCardService.toUpdateStopTime(stopCardList,CommonUtils.CARD_STATUS_1);
        }
        //准备更新会员卡为正常卡
        if( openCardList != null && openCardList.size()>0){
            iFrCardService.toUpdateStopTime(openCardList,CommonUtils.CARD_STATUS_0);
        }
        //更新停卡数据
        if( updateFrCardOrderStop != null && updateFrCardOrderStop.size()>0){
            for(FrCardOrderStop frCardOrderStop:updateFrCardOrderStop){
                if(frCardOrderStop != null && !StringUtils.isEmpty(frCardOrderStop.getId())){
                    baseMapper.toUpdateTimeCard(frCardOrderStop);
                }
            }
        }
    }


    public FrCardOrderStop getStopTimeList(FrCardOrderStop frCardOrderStop,Date date){
        FrCardOrderStop stop = null;
        if(frCardOrderStop == null){
            return  stop;
        }
        stop = new FrCardOrderStop();
        //正常
        if(CommonUtils.STOP_STATUS_0 == frCardOrderStop.getStopStatus()){
            //判断开始停卡时间是否小于现在
            if(!StringUtils.isEmpty(frCardOrderStop.getStartTime().toString())){
                boolean isFlage = DateUtil.compareDate(date,frCardOrderStop.getStartTime());
                if(isFlage){
                    stop.setStopStatus(CommonUtils.STOP_STATUS_1);
                }
            }
        }
        //停卡
        if(CommonUtils.STOP_STATUS_1 == frCardOrderStop.getStopStatus()){
            //系统更新根据预计时间，不用修改失效时间
            //判断预计停止结束时间是否小于现在
            if(!StringUtils.isEmpty(frCardOrderStop.getEstEndTime().toString())){
                boolean isFlage = DateUtil.compareDate(date,frCardOrderStop.getEstEndTime());
                if(isFlage){
                    //判断终止状态
                    Integer stopStatus = CommonUtils.STOP_STATUS_2;
                    if(CommonUtils.ORDER_STATUS_1 == frCardOrderStop.getStatus()){
                        stopStatus = CommonUtils.STOP_STATUS_3;
                    }
                    if(CommonUtils.AUDIT_ORDER_STATUS_1 == frCardOrderStop.getStatus()){
                        stopStatus = CommonUtils.STOP_STATUS_4;
                    }
                    stop.setStopStatus(stopStatus);
                    stop.setStopTime(frCardOrderStop.getEstStopTime());
                    stop.setEndTime(date);
                    //先用-1表示是系统自动更新的
                    stop.setStopUserId("-1");
                }
            }
        }
        stop.setId(frCardOrderStop.getId());
        stop.setCardId(frCardOrderStop.getCardId());
        stop.setCustomerCode(frCardOrderStop.getCustomerCode());
        return stop;
    }

}
