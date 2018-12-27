package com.yj.service.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.*;
import com.yj.dal.model.*;
import com.yj.dal.dao.FrCardSupplyRecordMapper;
import com.yj.service.base.BaseServiceImpl;
import com.yj.service.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 会员卡补卡 、续卡、转卡、卡升级记录 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-06
 */
@Service
public class FrCardSupplyRecordServiceImpl extends BaseServiceImpl<FrCardSupplyRecordMapper, FrCardSupplyRecord> implements IFrCardSupplyRecordService {

    @Resource
    private IFrCardService iFrCardService;
    @Resource
    private IFrCardOrderPayModeService iFrCardOrderPayModeService;
    @Resource
    private IFrCardOrderPriceDatailService iFrCardOrderPriceDatailService;
    @Resource
    private IFrAgreementService iFrAgreementService;
    @Resource
    private IFrCardOrderInfoService iFrCardOrderInfoService;
    @Resource
    private IFrCardTypeService iFrCardTypeService;
    @Resource
    private IFrCardNumService iFrCardNumService;
    @Resource
    private IFrCardOriginalSetService iFrCardOriginalSetService;
    @Resource
    private IFrCardOrderDatailService iFrCardOrderDatailService;
    @Resource
    private IFrCardAgreementService iFrCardAgreementService;
    @Resource
    private IFrChildCardService iFrChildCardService;


    @Override
    public Map<String, Object> queryCarDsupplyRecordList(FrCardSupplyRecord frCardSupplyRecord)throws YJException {
        if(frCardSupplyRecord == null){
            throw  new YJException(YJExceptionEnum.REQUEST_NULL);
        }
        if(StringUtils.isEmpty(frCardSupplyRecord.getCustomerCode())){
            throw  new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        if(StringUtils.isEmpty(frCardSupplyRecord.getClientId())){
            throw  new YJException(YJExceptionEnum.VIPCARDNO_ISNOTBLANK);
        }
        List<Map<String, Object>> list = baseMapper.queryCarDsupplyRecordList(frCardSupplyRecord);
        Map<String,Object> map = new HashMap<>();
        Double SupplyMoney = baseMapper.queryCountAll(frCardSupplyRecord.getClientId());
        map.put("SupplyMoney",SupplyMoney);
        map.put("list",list);
        return map;
    }

    /**
     * 添加数据
     * @param frCardSupplyRecord
     * @param frCardOrderPayModes
     * @return
     * @throws YJException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean toInsertAndAdd(FrCardSupplyRecord frCardSupplyRecord, List<FrCardOrderPayMode> frCardOrderPayModes,String shopId) throws YJException {
        if(frCardSupplyRecord == null){
            throw new YJException(YJExceptionEnum.OBJECT_NOT_FOUND);
        }
        if(StringUtils.isEmpty(frCardSupplyRecord.getCustomerCode())|| StringUtils.isEmpty(frCardSupplyRecord.getExternalNo())
                || StringUtils.isEmpty(frCardSupplyRecord.getClientId()) || StringUtils.isEmpty(frCardSupplyRecord.getNewCardNo())
                || StringUtils.isEmpty(frCardSupplyRecord.getOriCardId())){
            throw new  YJException(YJExceptionEnum.REQUEST_NULL);
        }
        //初始化补卡信息前准
        FrCard frCard = iFrCardService.selectOne(new EntityWrapper<FrCard>().where("id={0}",frCardSupplyRecord.getOriCardId())
                               .and("CustomerCode={0}",frCardSupplyRecord.getCustomerCode()));
        if(frCard == null){
            throw new  YJException(YJExceptionEnum.FRCARD_NOT_EXISTED);
        }
        if(CommonUtils.CARD_STATUS_2 == frCard.getStatus() || CommonUtils.CARD_STATUS_3 == frCard.getStatus() || CommonUtils.CARD_STATUS_6 == frCard.getStatus()){
            throw new  YJException(YJExceptionEnum.FRCARD_STATUS_EXISTED);
        }
        //初始化补卡信息
        frCardSupplyRecord.setNewCardId(frCard.getId());
        frCardSupplyRecord.setOriCardNo(frCard.getCardNo());
        frCardSupplyRecord.setUsing(true);
        frCardSupplyRecord.setStatus(0);
        frCardSupplyRecord.setAuditStatus(0);
        frCardSupplyRecord.setType(CommonUtils.CARD_SUPPLY_RECORD_TYPE_1);
        frCardSupplyRecord.setId(UUIDUtils.generateGUID());
        Double allPrice  = iFrCardOrderPayModeService.getAllPrice(frCardOrderPayModes,frCardSupplyRecord.getId());
        frCardSupplyRecord.setPayPrice(allPrice);
        baseMapper.insert(frCardSupplyRecord);
        FrCard frCard1 = new FrCard();
        frCard1.setCardNo(frCardSupplyRecord.getNewCardNo());
        frCard1.setExternalNo(frCardSupplyRecord.getExternalNo());
        iFrCardService.update(frCard1,new EntityWrapper<FrCard>().where("id={0}",frCard.getId())
                .and("CustomerCode={0}",frCard.getCustomerCode()));
        if(frCardOrderPayModes != null && frCardOrderPayModes.size()>0){
            this.getCardDatailAndPrice(frCardSupplyRecord,false,frCard.getType(),shopId);
            for(FrCardOrderPayMode  frCardOrderPayMode:frCardOrderPayModes ){
                iFrCardOrderPayModeService.insert(frCardOrderPayMode);
            }
        }
        return true;
    }

    /**
     * 明细表插入数据
     * @param frCardSupplyRecord
     * @param orderStatus
     * @param cardType
     * @param shopId
     * @return
     * @throws YJException
     */
    @Transactional(rollbackFor = Exception.class)
    public FrCardOrderPriceDatail getCardDatailAndPrice(FrCardSupplyRecord frCardSupplyRecord, boolean orderStatus, Integer cardType, String shopId) throws  YJException{
        FrCardOrderPriceDatail frCardOrderPriceDatail = null;
        if(frCardSupplyRecord != null){
            //初始化交易明细数据
            frCardOrderPriceDatail = new FrCardOrderPriceDatail();
            frCardOrderPriceDatail.setId(UUIDUtils.generateGUID());
            frCardOrderPriceDatail.setCustomerCode(frCardSupplyRecord.getCustomerCode());
            frCardOrderPriceDatail.setCardId(frCardSupplyRecord.getNewCardId());
            frCardOrderPriceDatail.setClientId(frCardSupplyRecord.getClientId());
            frCardOrderPriceDatail.setCardType(cardType);
            frCardOrderPriceDatail.setOrderStatus(orderStatus);
            Double orderPrice = iFrCardOrderPriceDatailService.getOrderPriceByOrderStatus(orderStatus,frCardSupplyRecord.getPayPrice());
            frCardOrderPriceDatail.setOrderPrice(orderPrice);
            frCardOrderPriceDatail.setShopId(shopId);
            frCardOrderPriceDatail.setPersonnelId(frCardSupplyRecord.getPersonnelId());
            frCardOrderPriceDatail.setStatus(CommonUtils.ORDER_STATUS_0);
            frCardOrderPriceDatail.setAuditStatus(CommonUtils.AUDIT_ORDER_STATUS_0);
            Integer orderType = null;
            // 补卡
            if(CommonUtils.CARD_SUPPLY_RECORD_TYPE_1 == frCardSupplyRecord.getType()){
                orderType = CommonUtils.PAY_MODE_ORDER_TYPE_4;
            }
            // 续卡
            if(CommonUtils.CARD_SUPPLY_RECORD_TYPE_2 == frCardSupplyRecord.getType()){
                orderType = CommonUtils.PAY_MODE_ORDER_TYPE_3;
            }
            // 转卡
            if(CommonUtils.CARD_SUPPLY_RECORD_TYPE_3 == frCardSupplyRecord.getType()){
                orderType = CommonUtils.PAY_MODE_ORDER_TYPE_3;
            }
            // 卡升级
            if(CommonUtils.CARD_SUPPLY_RECORD_TYPE_4 == frCardSupplyRecord.getType()){
                orderType = CommonUtils.PAY_MODE_ORDER_TYPE_10;
            }
            if(orderType == null){
                throw new  YJException(YJExceptionEnum.PAY_TYPE_NOT_EXISTED);
            }
            frCardOrderPriceDatail.setOrderType(orderType);
            frCardOrderPriceDatail.setOrderId(frCardSupplyRecord.getId());

            iFrCardOrderPriceDatailService.insert(frCardOrderPriceDatail);
        }
        return frCardOrderPriceDatail;
    }


    /**
     * 添加续卡
     * @param frCardSupplyRecord
     * @param frCard
     * @param frCardOrderInfo
     * @param frCardOrderPayModes
     * @param frCardOrderAllotSetList
     * @param mapS
     * @param mapI
     * @return
     * @throws YJException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult addContinueList(FrCardSupplyRecord frCardSupplyRecord, FrCard frCard,
                                      FrCardOrderInfo frCardOrderInfo, List<FrCardOrderPayMode> frCardOrderPayModes,
                                      List<FrCardOrderAllotSet> frCardOrderAllotSetList,
                                      Map<String, String> mapS, Map<String, Integer> mapI) throws YJException {
        //如果协议规则，会员卡，会员卡订单未获取，返回
        if(frCardSupplyRecord == null || frCard == null || frCardOrderInfo == null){
            throw  new YJException(YJExceptionEnum.REQUEST_NULL);
        }
        //会员卡号，客户ID，会员卡、会员卡种信息，会员外部卡号，操作店铺ID，客户代码 需提供
        if(StringUtils.isEmpty(frCard.getId()) || StringUtils.isEmpty(frCard.getShopId())|| StringUtils.isEmpty(frCard.getCustomerCode())
                || StringUtils.isEmpty(frCard.getClientId()) || StringUtils.isEmpty(frCard.getExternalNo()) || StringUtils.isEmpty(frCard.getCardNo())
                || frCardSupplyRecord.getCardOpening() == null  || frCardSupplyRecord.getReplacementCard() == null){
            return JsonResult.failMessage("信息设置有误，会员卡，");
        }
        //获取之前的会员卡信息--验证信息
        FrCard frCard1 = iFrCardService.selectById(frCard.getId());
        if(frCard1 == null){   return JsonResult.failMessage("未查询到对应的会员卡信息");  }
        frCard.setCardTypeId(frCard1.getCardTypeId());
        //判断原订单状态是否结款 --- 卡种是否销售中即存在
        FrCardOrderInfo frCardOrderInfo1 = new FrCardOrderInfo();
        frCardOrderInfo1.setClientId(frCard1.getClientId());
        frCardOrderInfo1.setCardId(frCard1.getId());
        frCardOrderInfo1.setCustomerCode(frCard.getCustomerCode());
        Map<String,Object> infoCard = iFrCardOrderInfoService.queryCardAndType(frCardOrderInfo1);
        if(infoCard == null){
            return JsonResult.failMessage("卡订单信息获取失败");
        }
        //判断此会员的订单是否结款？未结款的订单不允许操作
        String  orderState =  this.getParemtToMap(infoCard,"orderState");
        if(!CommonUtils.CARD_ORDRE_STATE_3.toString().equals(orderState)
                && !CommonUtils.CARD_ORDRE_STATE_4.toString().equals(orderState)
                && !CommonUtils.CARD_ORDRE_STATE_5.toString().equals(orderState)){
            return JsonResult.failMessage("未结款订单禁止续卡");
        }
        //卡种停止销售了，禁止操作
        String cardTypeId = this.getParemtToMap(infoCard,"bId");
        if(StringUtils.isEmpty(cardTypeId)){
            return  JsonResult.failMessage("此会员卡卡种停止销售,请先操作卡升级或者转卡");
        }
        String cardTypeName = this.getParemtToMap(infoCard,"cardTypeName");
        String cardFlag = this.getParemtToMap(infoCard,"cardFlag");
        //判断是直接延续开始  --  默认另行开卡
        //直接延续  开卡时间 = 老会员卡的失效时间    * 开卡方式（0，直接延续 ； 1，另行开卡）
        frCardSupplyRecord.setBindTime(frCard.getBindTime());
        String cardNumId = frCard1.getCardNumId();
        if(!frCardSupplyRecord.getCardOpening()){
            if(StringUtils.isEmpty(frCard1.getBindTime())){
                return  JsonResult.failMessage("续卡的直接延续开卡方式，只适用于已开的会员卡");
            }
            if(StringUtils.isEmpty(frCard1.getInvalidTime())){
                return  JsonResult.failMessage("此会员卡的卡失效时间未设置");
            }
            frCard.setBindTime(frCard1.getInvalidTime());
            frCardSupplyRecord.setBindTime(frCard1.getInvalidTime());
        }
        //默认不更换卡号，需要后台生成一个新卡号，替换原先卡号
        boolean isFlag = true;
        if(frCardSupplyRecord.getReplacementCard()){
            //更换新卡
            if(frCard1.getCardNo().equals(frCard.getCardNo())){
                return JsonResult.failMessage("更换新卡，请先随机生成新会员卡号");
            }
            if(StringUtils.isEmpty(frCard.getCardNumId())){
                return JsonResult.failMessage("会员卡规则ID未获取");
            }
            frCardSupplyRecord.setNewCardNo(frCard.getCardNo());
            frCardSupplyRecord.setOriCardNo(frCard1.getCardNo());
            frCardSupplyRecord.setOriCardId(frCard1.getId());
            //前端已经生成过，后端不需要再次生成
            isFlag = false;
            cardNumId = frCard.getCardNumId();
        }
        frCard.setCardNumId(cardNumId);
        if(isFlag){
            //要更新的老会员卡信息初始化
            //生成新会员卡，及会员卡对应规则ID
            Map<String,String> map =  iFrCardNumService.getCardNum(frCard.getCustomerCode());
            //重新生成会员卡号
            frCard.setCardNo(map.get("cardNo"));
            frCard.setCardNumId(map.get("cardNumId"));

            frCardSupplyRecord.setNewCardNo(frCard1.getCardNo());
            frCardSupplyRecord.setOriCardNo(frCard.getCardNo());
            frCardSupplyRecord.setOriCardId(frCard.getId());
        }
        //生成协议编号
        FrCardAgreement frCardAgreement = iFrAgreementService.getAgreement(frCard.getCustomerCode());
        if(frCardAgreement == null){
            return  JsonResult.failMessage("订单规则生成有误");
        }
        iFrCardService.toAddFrCard(frCardAgreement,frCard,frCardOrderInfo,frCardOrderPayModes,frCardOrderAllotSetList,mapS,mapI);
        //初始化续卡订单信息
        frCardSupplyRecord.setId(UUIDUtils.generateGUID());
        frCardSupplyRecord.setNewCardId(frCard.getId());
        frCardSupplyRecord.setClientId(frCard.getClientId());
        frCardSupplyRecord.setPersonnelId(frCardOrderInfo.getPersonnelId());
        StringBuffer flag = new StringBuffer(frCardSupplyRecord.getOriCardNo());
        frCardSupplyRecord.setSupplyRemarks(flag.append("==>").append(frCardSupplyRecord.getNewCardNo()).toString());
        frCardSupplyRecord.setUsing(true);
        frCardSupplyRecord.setCustomerCode(frCard.getCustomerCode());
        frCardSupplyRecord.setExternalNo(frCard.getExternalNo());
        frCardSupplyRecord.setType(CommonUtils.CARD_SUPPLY_RECORD_TYPE_2);
        frCardSupplyRecord.setCardName(cardTypeName);
        frCardSupplyRecord.setCardFlag(cardFlag);
        frCardSupplyRecord.setOrderState(CommonUtils.ORDER_TYPE_1);
        frCardSupplyRecord.setOrderInfoId(frCardOrderInfo.getId());
        frCardSupplyRecord.setPayPrice(frCardOrderInfo.getNeedPrice());
        frCardSupplyRecord.setInvalidTime(frCard.getInvalidTime());
        //插入一条续卡记录
        baseMapper.insert(frCardSupplyRecord);
        return JsonResult.success(true);
    }



    public  String  getParemtToMap(Map<String,Object> map,String key){
        String par = "";
        if(map != null){
            Object str = map.get(key);
            if(str != null){
                par = str.toString();
            }
        }
        return par;
    }

    @Override
    public PageUtils queryContinueCardLis(Page page, Map<String,Object> map) throws YJException {
        map = this.getCardTypeData(map);
        if(page != null){
            List<Map<String,Object>>  list = baseMapper.queryContinueCardLis(page,map);
            page.setRecords(list);
        }
        return new PageUtils(page);
    }


    /**
     * 获取会员卡过期时间，本次需购买的权益，售价，剩余权益
     * @param map
     * @return
     */
    public Map<String,Object> getCardTypeData(Map<String,Object> map) throws YJException{
        if(map == null){
            map = new HashMap<>();
        }
        if(map != null){
            Object cardId = map.get("cardId");
            Map<String,Object>  mapT = iFrCardService.queryCardType((String) cardId);
            if(mapT != null){
                map.put("cardTypeId",mapT.get("cardTypeId"));
                map.put("invalidTime",mapT.get("invalidTime"));
                map.put("haveRightsNum",mapT.get("haveRightsNum"));
                map.put("fSalesPrice",mapT.get("fSalesPrice"));
                map.put("fTotalNum",mapT.get("fTotalNum"));
            }
        }
        return  map;
    }


    /**
     * 添加转卡或者卡升级订单
     * @param frCardSupplyRecord
     * @param frCardOrderPayModes
     * @param mapS
     * @param mapI
     * @return
     * @throws YJException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult addTransferCard(FrCardSupplyRecord frCardSupplyRecord, List<FrCardOrderPayMode> frCardOrderPayModes, Map<String, String> mapS, Map<String, Integer> mapI) throws YJException {
        if(frCardSupplyRecord == null || mapS == null){
            return  JsonResult.failMessage("数据设置有误");
        }
        if(StringUtils.isEmpty(frCardSupplyRecord.getOriCardId())){
            return JsonResult.failMessage("要转卡的会员卡ID未获取");
        }
        //验证旧会员卡信息
        FrCard oriCard = iFrCardService.selectById(frCardSupplyRecord.getOriCardId());
        if(oriCard == null){
            return JsonResult.failMessage("查无此要转卡的会员卡");
        }
        if(CommonUtils.CARD_STATUS_6 == oriCard.getStatus() || CommonUtils.CARD_STATUS_3 == oriCard.getStatus()){
            return JsonResult.failMessage("过期卡，历史卡禁止任何操作");
        }
        //获取要转型的卡类型
        String newCardTypeId = mapS.get("newCardTypeId");
        if(StringUtils.isEmpty(newCardTypeId)){
            return JsonResult.failMessage("要转卡的类型卡信息获取有误");
        }
        String shopId = mapS.get("shopId");
        //获取转卡的卡类型信息
        FrCardType frCardType = iFrCardTypeService.selectById(newCardTypeId);
        frCardSupplyRecord.setCardName(frCardType.getCardTypeName());
        //卡种信息未获取或者已经停止销售
        if(frCardType == null || !frCardType.getUsing()){
            throw  new  YJException(YJExceptionEnum.CARD_TYPE_EXISTED);
        }
        //购买权设置信息有误
        if(frCardType.getTotalNum() == null || frCardType.getTotalNum() <= 0 || StringUtils.isEmpty(frCardType.getServiceLife())){
            throw  new  YJException(YJExceptionEnum.CARD_TYPE_TOTAL_EXISTED);
        }
        //初始化新会员卡信息
        FrCard newCard = this.getNewFrCard(frCardSupplyRecord,oriCard,shopId,frCardType);
        newCard.setId(oriCard.getId());
        //重置卡前设置
        FrCardOriginalSet  frCardOriginalSet = new FrCardOriginalSet();
        frCardOriginalSet.setCardId(newCard.getId());
        //初始化新会员卡的卡前设置
        iFrCardOriginalSetService.initFrCardOrgiginalSet(frCardOriginalSet,frCardType);
        //新会员----更换会之前的卡前设置
        newCard.setOriginalId(frCardOriginalSet.getId());
        // 准备更新旧会员卡数据
        FrCard oldCardF = oriCard;
        oldCardF.setId(UUIDUtils.generateGUID());
        oldCardF.setStatus(CommonUtils.CARD_STATUS_6);
        if(oriCard.getCardNo().equals(newCard.getCardNo())){
           Map<String,String> map =  iFrCardNumService.getCardNum(oriCard.getCustomerCode());
           if(map != null){
               oldCardF.setCardNo(map.get("cardNo"));
               oldCardF.setCardNumId(map.get("cardNumId"));
           }
        }
        //更新之前的会员卡信息成为历史卡
        iFrCardService.update(newCard,new EntityWrapper<FrCard>().where("id={0}",newCard.getId()));
        iFrCardService.insert(oldCardF);
        FrCardOriginalSet frCardOriginalSet1 = new FrCardOriginalSet();
        frCardOriginalSet1.setCardId(oldCardF.getId());
        iFrCardOriginalSetService.update(frCardOriginalSet1,new EntityWrapper<FrCardOriginalSet>().where("id={0}",oriCard.getOriginalId()));
        //获取之前的订单数据
        FrCardOrderInfo frCardOrderInfo = iFrCardOrderInfoService.selectOne(new EntityWrapper<FrCardOrderInfo>().where("card_id={0}",newCard.getId())
        .and("client_id={0}",newCard.getClientId()).and("CustomerCode={0}",newCard.getCustomerCode()).and("is_using={0}",1));

        String  payMentMoney = mapS.get("payMentMoney");
        Map<String,Double> payMoney =  JSONObject.parseObject(payMentMoney,Map.class);
        String oldInfo = frCardOrderInfo.getId();
        //更新旧订单信息；
        frCardOrderInfo.setId(UUIDUtils.generateGUID());
        frCardOrderInfo.setCardId(oldCardF.getId());
        iFrCardOrderInfoService.insert(frCardOrderInfo);
        //计算剩余金额 = 购卡金额/(购买权益+赠送权益）*剩余权益
        Double gPrice = frCardOrderInfo.getNeedPrice();
        Double buyNum = 0.0+frCardOrderInfo.getBuyRightsNum();
        Double giveNum = 0.0+frCardOrderInfo.getGiveRightsNum();
        FrCardOrderInfo frCardOrderInfo1 = this.getNewCardInfo(frCardOrderInfo,newCard,frCardSupplyRecord.getType(),
                frCardOrderPayModes,payMoney, frCardType,oldInfo);
        frCardOrderInfo1.setCardSetId(frCardOriginalSet.getId());
        frCardOrderInfo1.setId(oldInfo);
        iFrCardOrderInfoService.update(frCardOrderInfo1,new EntityWrapper<FrCardOrderInfo>().where("id={0}",oldInfo));

        //插入一条协议编号信息
        FrCardAgreement frCardAgreement = iFrAgreementService.getAgreement(newCard.getCustomerCode());
        if(frCardAgreement == null){
            return  JsonResult.failMessage("订单规则生成有误");
        }
        frCardAgreement.setCustomerCode(oldCardF.getCustomerCode());
        frCardAgreement.setUsing(true);
        frCardAgreement.setCardId(oldCardF.getId());
        frCardAgreement.setId(UUIDUtils.generateGUID());
        //规则表
        iFrCardAgreementService.insert(frCardAgreement);

        Integer orderType = frCardSupplyRecord.getType();
        if(CommonUtils.CARD_ORDRE_INFO_TYPE_4 == orderType){
            orderType = CommonUtils.PAY_MODE_ORDER_TYPE_10;
        }
        if(CommonUtils.CARD_ORDRE_INFO_TYPE_3 == orderType){
            orderType = CommonUtils.PAY_MODE_ORDER_TYPE_3;
        }
        //初始化交易明细
        FrCardOrderPriceDatail frCardOrderPriceDatail = iFrCardService.getCardDatailAndPrice(frCardOrderInfo1,false,newCard.getType(),orderType);
        iFrCardOrderPriceDatailService.insert(frCardOrderPriceDatail);

        //获取会员卡的剩余权益
        FrCardOrderDatail frCardOrderDatail = new FrCardOrderDatail();
        frCardOrderDatail.setCardId(frCardOrderPriceDatail.getCardId());
        frCardOrderDatail.setClientId(frCardOrderPriceDatail.getClientId());
        frCardOrderDatail.setCustomerCode(frCardOrderPriceDatail.getCustomerCode());
        frCardOrderDatail.setType(CommonUtils.ORDER_DATAIL_TYPE_1);
        Double havaRightsNum = iFrCardOrderDatailService.querySumOrderPrice(frCardOrderDatail, false);
        //计算剩余金额 = 购卡金额/(购买权益+赠送权益）*
        Double havaPrice = 0.0;
        if(havaRightsNum > 0){
           havaPrice  = gPrice/(buyNum+giveNum) * havaRightsNum;
        }
        //插入一条扣除剩余金额的记录--准备
        FrCardOrderPriceDatail frCardOrderPriceDatail1 = iFrCardService.getCardDatailAndPrice(frCardOrderInfo1,false,newCard.getType(),orderType);
        frCardOrderPriceDatail1.setOrderStatus(true);
        Double orderPrice = iFrCardOrderPriceDatailService.getOrderPriceByOrderStatus(true,havaPrice);
        frCardOrderPriceDatail1.setOrderPrice(orderPrice);
        String mess = "";
        String messT = "";
        if(CommonUtils.CARD_SUPPLY_RECORD_TYPE_3 == frCardSupplyRecord.getType()){
            mess = "转卡扣除剩余权益";
            messT = "转卡充值权益";
        }
        if(CommonUtils.CARD_SUPPLY_RECORD_TYPE_4 == frCardSupplyRecord.getType()){
            mess = "卡升级扣除剩余权益";
            messT = "卡升级充值权益";
        }
        FrCardOrderDatail frCardOrderDatail1 =  iFrCardOrderDatailService.getOrderDatailInfo(frCardOrderPriceDatail1);
        frCardOrderDatail1.setFlag(mess);
        frCardOrderDatail1.setType(CommonUtils.ORDER_DATAIL_TYPE_1);
        frCardOrderDatail1.setOrderStatus(false);
        Double rightsNum = iFrCardOrderDatailService.getOrderPriceByOrderStatus(false, havaRightsNum);
        frCardOrderDatail1.setOrderRightsNum(rightsNum);
        frCardOrderDatail1.setOrderAmt(0.0);
        iFrCardOrderDatailService.insert(frCardOrderDatail1);
        FrCardOrderDatail frCardOrderDatail11 = this.getPriceDatail(frCardOrderPriceDatail,frCardOrderInfo1,messT);
        iFrCardOrderDatailService.insert(frCardOrderDatail11);
        //插入转卡或者卡升级信息
        frCardSupplyRecord.setOriCardId(oldCardF.getId());
        frCardSupplyRecord.setOriCardNo(oldCardF.getCardNo());
        frCardSupplyRecord.setNewCardId(newCard.getId());
        frCardSupplyRecord.setNewCardNo(newCard.getCardNo());
        frCardSupplyRecord.setNewCardNumId(newCard.getCardNumId());
        frCardSupplyRecord.setExternalNo(newCard.getExternalNo());
        frCardSupplyRecord.setBindTime(newCard.getBindTime());
        frCardSupplyRecord.setInvalidTime(newCard.getInvalidTime());
        frCardSupplyRecord.setOrderInfoId(frCardOrderInfo1.getId());
        frCardSupplyRecord.setOrderState(CommonUtils.ORDER_TYPE_1);
        frCardSupplyRecord.setPayPrice(NumberUtilsTwo.getDoubleNum("allPrice",payMoney));
        frCardSupplyRecord.setId(UUIDUtils.generateGUID());
        frCardSupplyRecord.setInvalidTime(newCard.getInvalidTime());
        baseMapper.insert(frCardSupplyRecord);
        if(frCardOrderPayModes != null && frCardOrderPayModes.size()>0){
            for(FrCardOrderPayMode  frCardOrderPayMode:frCardOrderPayModes ){
                iFrCardOrderPayModeService.insert(frCardOrderPayMode);
            }
        }
        return JsonResult.success(true);
    }

    //初始化新会员卡信息
    public FrCard getNewFrCard(FrCardSupplyRecord frCardSupplyRecord,FrCard oldCard,String shopId,FrCardType frCardType)throws YJException{
        //初始化新会员卡信息
        FrCard frCard = new FrCard();
        frCard.setClientId(oldCard.getClientId());
        frCard.setCardTypeId(frCardType.getId());
        //销售此类型卡的门店
        frCard.setShopId(shopId);
        frCard.setStatus(oldCard.getStatus());
        frCard.setBindTime(oldCard.getBindTime());
        frCard.setType(frCardType.getType());
        frCard.setUsing(true);
        frCard.setCustomerCode(frCardSupplyRecord.getCustomerCode());
        Double total = 0.0+frCardType.getTotalNum();
        frCard.setHaveRightsNum(total);
        frCard.setExternalNo(oldCard.getExternalNo());
        frCard.setNote(oldCard.getNote());
        //若开卡了设置失效时间 = 开卡时间+使用期限；
        String inv = frCardType.getServiceLife();
        String start =  frCard.getBindTime();
        if(!StringUtils.isEmpty(start)){
            String serviceLisfe = iFrCardService.getInv(inv,start);
            if(!StringUtils.isEmpty(serviceLisfe)){
                frCard.setInvalidTime(serviceLisfe);
            }
        }
        //设置会员号
        if(StringUtils.isEmpty(frCardSupplyRecord.getNewCardNo())){
             throw new  YJException(YJExceptionEnum.VIPCARDNO_NOT_EXISTED);
        }
        frCard.setCardNo(frCardSupplyRecord.getNewCardNo());
        //判断会员规则协议号
        String cardNumId = this.getCardNumId(frCardSupplyRecord.getNewCardNumId(),oldCard.getCardNumId(),
                            frCardSupplyRecord.getOriCardNo(),frCardSupplyRecord.getNewCardNo(),frCardSupplyRecord.getCustomerCode());
        if(StringUtils.isEmpty(cardNumId)){
            throw new  YJException(YJExceptionEnum.CARD_NUM_ID_EXISTED);
        }
        frCard.setCardNumId(cardNumId);
        Date date = new Date();
        frCard.setCreateTime(date);
        frCard.setCreateUserId("");
        frCard.setUpdateTime(date);
        frCard.setUpdateUserId("");
        return frCard;
    }

    /**
     * 根据旧订单信息，初始化新会员卡订单----转卡、卡升级
     * @param frCardOrderInfo   //老订单信息
     * @param frCard       //新会员卡信息
     * @param infoType     //订单操作类型
     * @param frCardOrderPayModes       //支付方式
     * @param payMentMoney               //支付计算
     * @return
     */
    public FrCardOrderInfo getNewCardInfo(FrCardOrderInfo frCardOrderInfo,FrCard frCard,Integer infoType,
                                          List<FrCardOrderPayMode> frCardOrderPayModes,
                                          Map<String,Double> payMentMoney,
                                          FrCardType frCardType,String id)throws YJException{
        //初始化订单信息
        frCardOrderInfo.setOrderNo(iFrCardOrderInfoService.getOrderNo());
        frCardOrderInfo.setId(id);
        frCardOrderInfo.setCardId(frCard.getId());
        frCardOrderInfo.setCardTypeId(frCard.getCardTypeId());
        frCardOrderInfo.setType(infoType);
        frCardOrderInfo.setShopId(frCard.getShopId());
        frCardOrderInfo.setUsing(true);
        frCardOrderInfo.setBuyRightsNum(frCardType.getTotalNum());
        //赠送的权益先直接延用
//        frCardOrderInfo.setGiveRightsNum(0);
        //审核状态默认未审核
        frCardOrderInfo.setStatus(CommonUtils.ORDER_STATUS_0);
        frCardOrderInfo.setAuditStatus(CommonUtils.AUDIT_ORDER_STATUS_0);
        Integer orderState = CommonUtils.CARD_ORDRE_STATE_2;
        //现在实际应付金额
        Double needPrice = NumberUtilsTwo.getDoubleNum("price",payMentMoney);
        //之前的应金额
        Double oldPrice = +frCardOrderInfo.getNeedPrice();
        //现在的总价
        Double totalPrice = NumberUtilsTwo.getDoubleNum("totalPrice",payMentMoney)+frCardOrderInfo.getTotalPrice();
        //未付金额
        Double noPrice = NumberUtilsTwo.getDoubleNum("noPrice",payMentMoney)+frCardOrderInfo.getNoPrice();
        //找零
        Double reChange = NumberUtilsTwo.getDoubleNum("retChange",payMentMoney)+frCardOrderInfo.getRetChange();
        //现在的支付金额
        Double allPrice  = iFrCardOrderPayModeService.getAllPrice(frCardOrderPayModes,frCardOrderInfo.getId());
        if(!needPrice.toString().equals(allPrice.toString())){
            throw  new  YJException(YJExceptionEnum.PAY_PRICE_EXISTED);
        }
        payMentMoney.put("allPrice",allPrice);
        frCardOrderInfo.setNeedPrice(oldPrice+needPrice);
        frCardOrderInfo.setTotalPrice(totalPrice);
        frCardOrderInfo.setNoPrice(noPrice);
        frCardOrderInfo.setRetChange(reChange);
        //总权益 = 购买权益+赠送权益
        Integer allNum = frCardOrderInfo.getBuyRightsNum()+frCardOrderInfo.getGiveRightsNum();
         //设置会员卡的总权益
        frCard.setHaveRightsNum(allNum+0.0);
        return  frCardOrderInfo;
    }
    //初始化订单交易明细-- 收入
    public FrCardOrderDatail getPriceDatail(FrCardOrderPriceDatail frCardOrderPriceDatail,FrCardOrderInfo frCardOrderInfo,String mess)throws YJException{
        FrCardOrderDatail  frCardOrderDatail =  iFrCardOrderDatailService.getOrderDatailInfo(frCardOrderPriceDatail);
        frCardOrderDatail.setFlag(mess);
        frCardOrderDatail.setType(CommonUtils.ORDER_DATAIL_TYPE_1);
        frCardOrderDatail.setOrderStatus(true);
        Integer buyRights = frCardOrderInfo.getBuyRightsNum();
        Integer giveRights = frCardOrderInfo.getGiveRightsNum();
        if(buyRights == null){   buyRights = 0;   }
        if(giveRights == null){  giveRights = 0;   }
        Double allRightsNum = 0.0+(buyRights+giveRights);
        Double rightsNum = iFrCardOrderDatailService.getOrderPriceByOrderStatus(true, allRightsNum);
        frCardOrderDatail.setOrderRightsNum(rightsNum);
        frCardOrderDatail.setOrderAmt(rightsNum);
        return frCardOrderDatail;
    }


    /**
     * 检索卡号是否符合规则
     * @param newCardNo
     * @param code
     * @return
     */
    @Override
    public String isFlagNewCardNo(String newCardNo,String code,Map<String,String> map){
        String id = iFrCardNumService.checkCardNum(newCardNo,code);
        if("".equals(id)){
            return "新会员卡号不可用";
        }
        if("1".equals(id)){
            return "暂无会员卡号规则，请创建会员卡号规则";
        }
        map.put("id",id);
        return "true";
    }

    /**
     * 根据新旧会员卡获取会员卡规则ID
     * @param newcardNumId
     * @param oldCardNumId
     * @param cardNo
     * @param newCardNo
     * @param code
     * @return
     */
    public String getCardNumId(String newcardNumId,String oldCardNumId,String cardNo,String newCardNo,String code) {
        String cardNumId = "";
        boolean isFlag = true;
        if(cardNo.equals(newCardNo)){
            cardNumId = oldCardNumId;
            isFlag = false;
        }
        if(isFlag){
            cardNumId = newcardNumId;
            if(StringUtils.isEmpty(cardNumId)){
                Map<String,String> map = new HashMap<>();
                String  cardN = this.isFlagNewCardNo(newCardNo,code,map);
                if("true".equals(cardN)){
                    cardNumId = map.get("id");
                }
            }
        }
        return cardNumId;
    }

    @Override
    public List<FrCardSupplyRecord> quereySupplyList(FrCardSupplyRecord frCardSupplyRecord) {
        List<FrCardSupplyRecord> frCardSupplyRecordList = null;
        if(frCardSupplyRecord != null && frCardSupplyRecord.getType() != null && !StringUtils.isEmpty(frCardSupplyRecord.getNewCardId())){
            frCardSupplyRecordList = baseMapper.quereySupplyList(frCardSupplyRecord);
        }
        return frCardSupplyRecordList;
    }

    @Override
    public List<String> getCardIdList(String cardId,Integer type) {
        List<String> list = new ArrayList<>();
        list.add(cardId);
        if(StringUtils.isEmpty(cardId)){  return list;  }
        //查看是否有续卡订单
        FrCardSupplyRecord frCardSupplyRecord = new FrCardSupplyRecord();
        frCardSupplyRecord.setNewCardId(cardId);
        frCardSupplyRecord.setType(type);
        List<FrCardSupplyRecord> frCardSupplyRecordList = this.quereySupplyList(frCardSupplyRecord);
        if(frCardSupplyRecordList != null){
            for(FrCardSupplyRecord frCardSupplyRecord1 : frCardSupplyRecordList){
                list.add(frCardSupplyRecord1.getOriCardId());
            }
        }
        return list;
    }

    /**
     * 续卡的定时任务
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSupplyRecordTime()throws YJException {
        //查询续卡的开卡时间是今天的，之前的会员卡更新为历史卡，新会员卡开卡，如果过是更换新卡，卡号注意变更
        // 转移旧卡的剩余储值金额
        // 查询状态是直接延续，开卡时间小于现在时间
        Date date = new Date();
        Map<String,Object> map = new HashMap<>();
        map.put("nowTime",date);
        //type里存放的是卡类型，orderInfoId 存放了旧会员卡规则
        List<FrCardSupplyRecord> list = baseMapper.updateSupplyRecordTime(map);
        if(list != null && list.size()>0){
            for(FrCardSupplyRecord frCardSupplyRecord:list){
                this.continueOpenList(frCardSupplyRecord);
            }
        }
    }

    /**
     * 续卡开卡---------此方法暂时默认是现在的直接延续
     * @param frCardSupplyRecord
     * @return
     * @throws YJException
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean  continueOpenList(FrCardSupplyRecord frCardSupplyRecord) throws YJException {
        //如果协议规则，会员卡，会员卡订单未获取，返回
        if(frCardSupplyRecord == null ){
            throw  new YJException(YJExceptionEnum.REQUEST_NULL);
        }
        //续卡开卡==============
        // 开卡方式（0，直接延续 ； 1，另行开卡）
        boolean cardOpening = frCardSupplyRecord.getCardOpening();
        // 是否更换新卡(0、否；1、是)
        boolean replacementCard = frCardSupplyRecord.getReplacementCard();
        //直接延续才需要需要定时任务开启，如果过是另行开卡的话，客户自己设置开卡时间
        if(!cardOpening) {
            //直接延续
            String bindTime = frCardSupplyRecord.getBindTime();
            if (StringUtils.toIsEmpty(bindTime)) {
                //如果未查询到设置的开卡时间
                FrCard frCard = iFrCardService.selectById(frCardSupplyRecord.getOriCardId());
                if (frCard == null) {
                    throw new YJException(YJExceptionEnum.OBJECT_NOT_FOUND);
                }
                bindTime = frCard.getInvalidTime();
                if (StringUtils.toIsEmpty(bindTime)) {
                    throw new YJException(YJExceptionEnum.OBJECT_NOT_FOUND);
                }
            }
            //准备会员卡信息
            FrCard oldCard = new FrCard();
            oldCard.setId(frCardSupplyRecord.getOriCardId());
            oldCard.setStatus(CommonUtils.CARD_STATUS_6);
            FrCard newCard = new FrCard();
            newCard.setId(frCardSupplyRecord.getNewCardId());
            oldCard.setStatus(CommonUtils.CARD_STATUS_0);
            // 是否更换新卡(0、否；1、是)
            if (!replacementCard) {
                oldCard.setCardNo(frCardSupplyRecord.getNewCardNo());
                oldCard.setCardNumId(frCardSupplyRecord.getNewCardNumId());
                newCard.setCardNo(frCardSupplyRecord.getOriCardNo());
                newCard.setCardNumId(frCardSupplyRecord.getOrderInfoId());
            }
            //先更新会员卡状态--旧卡先更新成会员卡后，金额不再变动
            iFrCardService.update(oldCard,new EntityWrapper<FrCard>().where("id={0}",frCardSupplyRecord.getOriCardId()).and("CustomerCode={0}",frCardSupplyRecord.getCustomerCode()));
            iFrCardService.update(newCard,new EntityWrapper<FrCard>().where("id={0}",frCardSupplyRecord.getNewCardId()).and("CustomerCode={0}",frCardSupplyRecord.getCustomerCode()));
            //初始化参数
            Map<String,Integer> mapI = new HashMap<>();
            mapI.put("cardType",frCardSupplyRecord.getType());
            mapI.put("orderType",CommonUtils.PAY_MODE_ORDER_TYPE_2);
            mapI.put("type",CommonUtils.ORDER_DATAIL_TYPE_2);
            Map<String,String> mapS = new HashMap<>();
            //表示定时任务，系统操作
            String timeTask = "-1";
            mapS.put("shopId",timeTask);
            mapS.put("personnelId",timeTask);
            mapS.put("orderId",timeTask);
            mapS.put("flag","定时任务系统：转移剩余储值扣款");
            mapS.put("OrderStatus","false");
            mapS.put("cardId",frCardSupplyRecord.getOriCardId());
            //获取卡种剩余权益/或者储值
            Double orderPrice = iFrCardOrderDatailService.getOrderPrice(mapS.get("cardId"),frCardSupplyRecord.getClientId(),frCardSupplyRecord.getCustomerCode(), mapI.get("type"),false);
            List<FrCardOrderDatail> frCardOrderDatailList = new ArrayList();
            this.getOrderDatailInfo(frCardSupplyRecord,orderPrice,mapI,mapS,frCardOrderDatailList);
            mapS.put("flag","定时任务系统：转移剩余储值到新卡");
            mapS.put("OrderStatus","true");
            mapS.put("cardId",frCardSupplyRecord.getNewCardId());
            this.getOrderDatailInfo(frCardSupplyRecord,orderPrice,mapI,mapS,frCardOrderDatailList);
            if(frCardOrderDatailList != null && frCardOrderDatailList.size()>0){
               for(FrCardOrderDatail frCardOrderDatail1 :frCardOrderDatailList){
                   //获取卡种剩余权益/或者储值
                   Double havaRightsNum = iFrCardOrderDatailService.getOrderPrice(frCardOrderDatail1.getCardId(),frCardOrderDatail1.getClientId(),frCardOrderDatail1.getCustomerCode(),frCardOrderDatail1.getType(),false);
                   frCardOrderDatail1.setOrderAmt(havaRightsNum+frCardOrderDatail1.getOrderPrice());
                   iFrCardOrderDatailService.insert(frCardOrderDatail1);
               }
            }
            //续卡后需要把子卡都转移到此卡名下
            FrChildCard frChildCard = new FrChildCard();
            frChildCard.setParentCardId(frCardSupplyRecord.getNewCardId());
            iFrChildCardService.update(frChildCard, new EntityWrapper<FrChildCard>().where("CustomerCode={0}", frCardSupplyRecord.getCustomerCode()).and("parent_card_id={0}", frCardSupplyRecord.getOriCardId()));
        }
        return true;
    }

    /**
     * 根据信息初始化订单交易明细
     * @param frCardSupplyRecord
     * @param orderPrice
     * @param mapI
     * @param mapS
     * @param list
     * @return
     * @throws YJException
     */
    public FrCardOrderDatail getOrderDatailInfo(FrCardSupplyRecord frCardSupplyRecord,
                                                Double orderPrice,Map<String,Integer> mapI,Map<String,String> mapS,
                                                List<FrCardOrderDatail> list)throws YJException{
        Integer cardType = null,orderType = null,type = null;
        if(mapI != null){
            cardType  = mapI.get("cardType");
            orderType = mapI.get("orderType");
            type = mapI.get("type");
        }
        String shopId = null,personnelId = null,orderId = null,cardId = null,flag = null,OrderStatus = null;
        if(mapS != null){
            shopId = mapS.get("shopId");
            personnelId = mapS.get("personnelId");
            orderId = mapS.get("orderId");
            cardId = mapS.get("cardId");
            flag = mapS.get("flag");
            OrderStatus = mapS.get("OrderStatus");
        }
        //默认是收入
        boolean isFlag = true;
        if("false".equals(OrderStatus)){
            isFlag = false;
        }
        if("true".equals(OrderStatus)){
            isFlag = true;
        }
        if(cardType == null || orderType == null || type == null ||
                StringUtils.toIsEmpty(shopId) ||  StringUtils.toIsEmpty(personnelId) ||  StringUtils.toIsEmpty(orderId) ||
                StringUtils.toIsEmpty(cardId) ||  StringUtils.toIsEmpty(flag)){
            return  null;
        }
        FrCardOrderDatail frCardOrderDatail = null;
        if(frCardSupplyRecord != null && orderPrice > 0 ) {
            frCardOrderDatail = new FrCardOrderDatail();
            frCardOrderDatail.setId(UUIDUtils.generateGUID());
            frCardOrderDatail.setStatus(frCardSupplyRecord.getStatus());
            frCardOrderDatail.setAuditStatus(frCardSupplyRecord.getAuditStatus());
            frCardOrderDatail.setCustomerCode(frCardSupplyRecord.getCustomerCode());
            frCardOrderDatail.setClientId(frCardSupplyRecord.getClientId());
            frCardOrderDatail.setUsing(true);
            frCardOrderDatail.setCardType(cardType);
            frCardOrderDatail.setShopId(shopId);
            frCardOrderDatail.setPersonnelId(personnelId);
            frCardOrderDatail.setOrderType(orderType);
            frCardOrderDatail.setOrderId(orderId);
            frCardOrderDatail.setCardId(cardId);
            frCardOrderDatail.setFlag(flag);
            frCardOrderDatail.setType(type);
            frCardOrderDatail.setOrderStatus(isFlag);
            Double price = iFrCardOrderDatailService.getOrderPriceByOrderStatus(isFlag, orderPrice);
            if (CommonUtils.ORDER_DATAIL_TYPE_2 == type) {
                frCardOrderDatail.setOrderPrice(price);
            }
            if (CommonUtils.ORDER_DATAIL_TYPE_1 == type) {
                frCardOrderDatail.setOrderRightsNum(price);
            }
            //获取卡种剩余权益/或者储值
            Double havaRightsNum = iFrCardOrderDatailService.getOrderPrice(frCardOrderDatail.getCardId(),frCardOrderDatail.getClientId(),frCardOrderDatail.getCustomerCode(),frCardOrderDatail.getType(),false);
            frCardOrderDatail.setOrderAmt(havaRightsNum+price);
        }
        if(frCardOrderDatail != null ){
            list.add(frCardOrderDatail);
        }
        return frCardOrderDatail;
    }

    //    /**
//     * 初始化扣除旧卡的剩余权益，储值金额，转移到新卡上
//     * @param frCardSupplyRecord
//     * @return
//     * @throws YJException
//     */
//   public List<FrCardOrderDatail> getListDatail(FrCardSupplyRecord frCardSupplyRecord,FrCardOrderPriceDatail frCardOrderPriceDatail,Integer oldType,Integer newType,Map<String,Double> allHave)throws YJException{
//        //获取指定会员卡的剩余权益
//        FrCardOrderDatail frCardOrderDatail = new FrCardOrderDatail();
//        frCardOrderDatail.setCardId(frCardSupplyRecord.getOriCardId());
//        frCardOrderDatail.setClientId(frCardSupplyRecord.getClientId());
//        frCardOrderDatail.setCustomerCode(frCardSupplyRecord.getCustomerCode());
//        frCardOrderDatail.setType(CommonUtils.ORDER_DATAIL_TYPE_1);
//        //不包含子卡的所有信息
//        Map<String,Double> map1 = iFrCardOrderDatailService.getAmtByCardId(frCardOrderDatail);
//        Double orderPrice = NumberUtilsTwo.getDoubleNum("orderPrice",map1); //剩余储值金额
//        Double orderRightsNum = NumberUtilsTwo.getDoubleNum("orderRightsNum",map1);  //剩余权益
//        allHave.put("orderPrice",orderPrice);
//        allHave.put("orderRightsNum",orderRightsNum);
//        // 类型（1、补卡；2、续卡；3、转卡；4、卡升级；）
//        String  flagNum = "" , flagPrice = "",addFlagNum = "",addFlagPrice = "";
//        if(CommonUtils.CARD_SUPPLY_RECORD_TYPE_2 == frCardSupplyRecord.getType()){
//            //续卡
//            flagNum = "续卡操作扣除旧卡全部剩余权益";
//            flagPrice = "续卡操作扣除旧卡全部剩余金额";
//            addFlagNum = "续卡操作添加旧卡全部剩余权益";
//            addFlagPrice = "续卡操作添加旧卡全部剩余金额";
//        }
//       if(CommonUtils.CARD_SUPPLY_RECORD_TYPE_3 == frCardSupplyRecord.getType()){
//           //转卡
//           flagNum = "转卡操作扣除旧卡全部剩余权益";
//           flagPrice = "转卡操作扣除旧卡全部剩余金额";
//           addFlagNum = "转卡操作添加旧卡全部剩余权益";
//           addFlagPrice = "转卡操作添加旧卡全部剩余金额";
//       }
//       if(CommonUtils.CARD_SUPPLY_RECORD_TYPE_4 == frCardSupplyRecord.getType()){
//           //卡升级
//           flagNum = "卡升级操作扣除旧卡全部剩余权益";
//           flagPrice = "卡升级操作扣除旧卡全部剩余金额";
//           addFlagNum = "卡升级操作添加旧卡全部剩余权益";
//           addFlagPrice = "卡升级操作添加旧卡全部剩余金额";
//       }
//       List<FrCardOrderDatail> list = new ArrayList<>();
//       this.getOrderDatailInfo(frCardOrderPriceDatail,flagNum,CommonUtils.ORDER_DATAIL_TYPE_1, orderRightsNum,list,false,frCardSupplyRecord.getOriCardId(),oldType);
//       this.getOrderDatailInfo(frCardOrderPriceDatail,flagPrice,CommonUtils.ORDER_DATAIL_TYPE_2, orderPrice,list,false,frCardSupplyRecord.getOriCardId(),oldType);
//       this.getOrderDatailInfo(frCardOrderPriceDatail,addFlagNum,CommonUtils.ORDER_DATAIL_TYPE_1, orderRightsNum,list,true,frCardSupplyRecord.getNewCardId(),newType);
//       this.getOrderDatailInfo(frCardOrderPriceDatail,addFlagPrice,CommonUtils.ORDER_DATAIL_TYPE_2, orderPrice,list,true,frCardSupplyRecord.getNewCardId(),newType);
//       return list;
//    }



}
