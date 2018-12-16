package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.*;
import com.yj.dal.dao.FrCardTypeMapper;
import com.yj.dal.dao.PersonnelInfoMapper;
import com.yj.dal.model.*;
import com.yj.dal.dao.FrCardOrderInfoMapper;
import com.yj.service.service.*;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * 会员卡订单记录 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-26
 */
@Service
public class FrCardOrderInfoServiceImpl extends BaseServiceImpl<FrCardOrderInfoMapper, FrCardOrderInfo> implements IFrCardOrderInfoService {

    @Resource
    private IFrCardOrderDatailService iFrCardOrderDatailService;
    @Resource
    private IFrCardOrderBackService iFrCardOrderBackService;
    @Resource
    private IFrCardSupplyRecordService iFrCardSupplyRecordService;
    @Resource
    private IFrCardOrderStorageService iFrCardOrderStorageService;



    @Override
    public String getOrderNo() {
        //判断重复
        List<FrCardOrderInfo> list = baseMapper.selectList(
                new EntityWrapper<FrCardOrderInfo>().where("is_using = 1")
        );
        int n = 0;
        Random r = new Random();
        String orderNo;
        while (true){
            orderNo = DateUtil.dateToString(new Date(), "yyyyMMddHHmm")+(r.nextInt(99999-10000)+10000);;
            if(list == null || list.size()<1){
                break;
            }
            for(FrCardOrderInfo f:list){
                if(orderNo.equals(f.getOrderNo())){
                    n = 1;
                }
            }
            if(n == 0){
                break;
            }

        }
        return orderNo;
    }

    @Override
    public PageUtils getCardOrederList(String code,PageUtil<FrCard> pageUtil) throws YJException {
        Map map = new HashMap();
        map.put("limit",pageUtil.getRows());//每页多少条
        map.put("page",pageUtil.getPage());//当前页
        Page page = new Query<FrCardOrderInfo>(map).getPage();
        //查询该会员卡订单列表总数据
        List<FrCardOrderInfo> list = baseMapper.getCardOrederList(page,pageUtil);
        page.setRecords(list);
        return new PageUtils(page);
    }

    @Override
    public Map<String, Object> queryOrderInfoPrice(String cardId, String CustomerCode)throws YJException {
        if(StringUtils.isEmpty(CustomerCode)){
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        if(StringUtils.isEmpty(cardId)){
            throw new YJException(YJExceptionEnum.VIPCARDNO_NOT_EXISTED);
        }
        FrCardOrderInfo frCardOrderInfo = new FrCardOrderInfo();
        frCardOrderInfo.setCardId(cardId);
        frCardOrderInfo.setCustomerCode(CustomerCode);
        return baseMapper.queryOrderInfoPrice(frCardOrderInfo);
    }

    /**
     * 获取退卡数据
     * @param frCardOrderInfo
     * @return
     * @throws YJException
     */
    @Override
    public Map<String, Object> getBlackCardData(FrCardOrderInfo frCardOrderInfo) throws YJException {
        if(frCardOrderInfo == null){
            throw new  YJException(YJExceptionEnum.OBJECT_NOT_FOUND);
        }
        //查询订单信息
        frCardOrderInfo.setStatus(CommonUtils.ORDER_STATUS_1);
        frCardOrderInfo.setAuditStatus(CommonUtils.AUDIT_ORDER_STATUS_1);
        frCardOrderInfo.setUsing(true);
        Map<String, Object> map = baseMapper.getBlackCardData(frCardOrderInfo);
        // 查询可退金额
        FrCardOrderBack frCardOrderBack = new FrCardOrderBack();
        frCardOrderBack.setClientId(frCardOrderInfo.getClientId());
        frCardOrderBack.setCardId(frCardOrderInfo.getCardId());
        frCardOrderBack.setCustomerCode(frCardOrderInfo.getCustomerCode());
        Double allPrice = this.getAllBlackPrice(frCardOrderBack);
        if(allPrice == null){
            allPrice = 0.0;
        }
        if( map == null){
            map = new HashMap<>();
        }
        map.put("allPrice",allPrice);
        return map;
    }


    /**
     * 根据会员卡信息获取订单信息及卡种信息
     * @param frCardOrderInfo
     * @return
     * @throws YJException
     */
    @Override
    public Map<String,Object> queryCardAndType( FrCardOrderInfo frCardOrderInfo) throws YJException {
        if(frCardOrderInfo == null){
            throw  new YJException(YJExceptionEnum.REQUEST_NULL);
        }
        if(StringUtils.isEmpty(frCardOrderInfo.getCardId()) || StringUtils.isEmpty(frCardOrderInfo.getCustomerCode()) || StringUtils.isEmpty(frCardOrderInfo.getClientId())){
           throw  new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        return baseMapper.queryCardAndType(frCardOrderInfo);
    }


    /**
     * 查询应退卡金额
     * @param frCardOrderBack
     * @return
     * @throws YJException
     */
    public Double getAllBlackPrice( FrCardOrderBack frCardOrderBack) throws YJException{
        //先初始化可退金额
        Double blackPrice = 0.0;
        if(frCardOrderBack == null || StringUtils.isEmpty(frCardOrderBack.getClientId())
                ||  StringUtils.isEmpty(frCardOrderBack.getCardId()) || StringUtils.isEmpty(frCardOrderBack.getCustomerCode())){
            return blackPrice;
        }
        //获取此会员卡下的所有剩余储值金额，剩余权益，计算可退款金额
        Map<String,Double>  getHaveNum = iFrCardOrderBackService.getHaveNum(frCardOrderBack,true);
        Double orderPrice = NumberUtilsTwo.getDoubleNum("orderPrice",getHaveNum);
        Double orderRightsNum = NumberUtilsTwo.getDoubleNum("orderRightsNum",getHaveNum);
        Double childPrice = NumberUtilsTwo.getDoubleNum("childPrice",getHaveNum);
        Double childRightsNum = NumberUtilsTwo.getDoubleNum("childRightsNum",getHaveNum);
        //剩余总权益 = 父卡剩余权益+子卡剩余权益    ------------注意下子卡金额的查询方式是否有误
        Double allPrice  = orderPrice+childPrice;
        Double allNum = orderRightsNum+childRightsNum;
        List<String> list = iFrCardSupplyRecordService.getCardIdList(frCardOrderBack.getCardId(),CommonUtils.CARD_SUPPLY_RECORD_TYPE_2);
        //--根据剩余权益  计算可退金额
        Double  allNumPrice = this.getOrderPrice(frCardOrderBack,allNum,list);
        //--根据剩余储值，计算可退储值金额 --- 父卡的
        FrCardOrderStorage  frCardOrderStorage = new FrCardOrderStorage();
        frCardOrderStorage.setClientId(frCardOrderBack.getClientId());
        frCardOrderStorage.setCustomerCode(frCardOrderBack.getCustomerCode());
        frCardOrderStorage.setCardId(frCardOrderBack.getCardId());
        Double allStoragePrice  = iFrCardOrderStorageService.refundStorage(frCardOrderStorage,false,NumberUtilsTwo.getDoublPrice(orderPrice),list,false);
        //预留一个地方查询子卡的-----------所有可退储值金额-------------
        Double allStorageCPrice = 0.0;
        boolean toChile = false;
        if(toChile){
            frCardOrderStorage.setClientId(frCardOrderBack.getClientId());
            frCardOrderStorage.setCustomerCode(frCardOrderBack.getCustomerCode());
//            frCardOrderStorage.setCardId(frCardOrderBack.getCardId());        --------------这边应该设置的是子卡的id
            allStorageCPrice  = iFrCardOrderStorageService.refundStorage(frCardOrderStorage,true,NumberUtilsTwo.getDoublPrice(childPrice),list,false);
        }
        blackPrice = allNumPrice + allStoragePrice + allStorageCPrice;
        return  blackPrice;
    }


    /**
     * 获取指定会员卡的可退金额--不包含可退的储值金额
     * @return
     */
    public Double getOrderPrice( FrCardOrderBack frCardOrderBack,Double allNum, List<String> list)throws  YJException{
        //先初始化可退金额
        Double blackPrice = 0.0;
        if(frCardOrderBack == null || StringUtils.isEmpty(frCardOrderBack.getClientId())
                ||  StringUtils.isEmpty(frCardOrderBack.getCardId())
                || StringUtils.isEmpty(frCardOrderBack.getCustomerCode())
                || allNum <= 0.0){
            return blackPrice;
        }
        //查看是否有续卡订单
        if(list == null){
            list = iFrCardSupplyRecordService.getCardIdList(frCardOrderBack.getCardId(),CommonUtils.CARD_SUPPLY_RECORD_TYPE_2);
        }
        //获取全部的订单信息、新购、续卡、卡升级、转卡---包括补余金额
        //获取全部的订单信息------------List
        Map<String,Object> map = new HashMap<>();
        map.put("CustomerCode",frCardOrderBack.getCustomerCode());
        map.put("clientId",frCardOrderBack.getClientId());
        map.put("status",CommonUtils.ORDER_STATUS_1);
        map.put("auditStatus",CommonUtils.AUDIT_ORDER_STATUS_1);
        map.put("isUsing",1);
        map.put("cardIdList",list);
        List<FrCardOrderInfo> frCardOrderInfoList = baseMapper.queryBrackInfoList(map);
        if(frCardOrderInfoList == null){
            throw  new YJException(YJExceptionEnum.CARD_INFO_EXISTED);
        }
        //初始化设置应退款金额
        Double allHaveNum = allNum;
        for(FrCardOrderInfo frCardOrderInfo:frCardOrderInfoList){
            //购买的总权益 = 购买权益+赠送权益
            Double allBuyNum = NumberUtilsTwo.getDoublPrice(frCardOrderInfo.getBuyRightsNum())+NumberUtilsTwo.getDoublPrice(frCardOrderInfo.getGiveRightsNum());
            boolean toFlag = true;
            if(allBuyNum < allHaveNum){
                toFlag = false;
                Integer orderState = frCardOrderInfo.getOrderState();
                //订单是已结款状态的实际支付金额=应付金额
                // 默认是未结款状态
                boolean isFlag = true;
                if(CommonUtils.CARD_ORDRE_STATE_3 == orderState || CommonUtils.CARD_ORDRE_STATE_4 == orderState || CommonUtils.CARD_ORDRE_STATE_5 == orderState){
                    blackPrice = blackPrice + NumberUtilsTwo.getDoublPrice(frCardOrderInfo.getNeedPrice());
                    isFlag = false;
                }
                if(isFlag){
                    //实际金额暂时存放在getDiscountPrice里的
                    blackPrice = blackPrice + NumberUtilsTwo.getDoublPrice(frCardOrderInfo.getDiscountPrice())+NumberUtilsTwo.getDoublPrice(frCardOrderInfo.getDiscount());
                }
                allHaveNum = allHaveNum - allBuyNum;
            }
            //如果第一条数据，剩余权益小于购买权益，就直接退出；
            if(toFlag){
                //退款金额 = 购卡金额/（购买权益+赠送权益）*剩余权益
                //购卡金额 = 实际付款金额
                Double buyCardPrice =  NumberUtilsTwo.getDoublPrice(frCardOrderInfo.getDiscountPrice())+NumberUtilsTwo.getDoublPrice(frCardOrderInfo.getDiscount());
                Double needPayPrice = NumberUtilsTwo.getDoublPrice(frCardOrderInfo.getNeedPrice());
                Double needPrice = needPayPrice;
                //若应付款金额大于实际付款金额，购卡金额 = 实际付款金额；
                if(buyCardPrice < needPayPrice ){
                    needPrice = buyCardPrice;
                }
                Double  price  =  needPrice/allBuyNum * allHaveNum;
                blackPrice = blackPrice + price;
               break;
            }
        }
        return  blackPrice;
    }

    public String getToString(String key,Map<String,Object> map){
        String mess = "";
        if(map != null){
            Object str = map.get(key);
            if(str != null){
                mess = str.toString();
            }
        }
        return  mess;
    }
}
