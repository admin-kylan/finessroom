package com.yj.service.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CommonUtils;
import com.yj.common.util.NumberUtilsTwo;
import com.yj.common.util.StringUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.model.*;
import com.yj.dal.dao.FrCardOrderComplementMapper;
import com.yj.service.base.BaseServiceImpl;
import com.yj.service.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.beans.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员卡补余订单 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-06
 */
@Service
public class FrCardOrderComplementServiceImpl extends BaseServiceImpl<FrCardOrderComplementMapper, FrCardOrderComplement> implements IFrCardOrderComplementService {

    @Resource
    private IFrCardOrderSplitSetDdService iFrCardOrderSplitSetDdService;
    @Resource
    private IFrCardOrderAllotSetService iFrCardOrderAllotSetService;
    @Resource
    private IFrCardOrderSplitSetService iFrCardOrderSplitSetService;
    @Resource
    private IFrCardOrderPriceDatailService iFrCardOrderPriceDatailService;
    @Resource
    private IFrCardService iFrCardService;
    @Resource
    private IFrCardOrderPayModeService iFrCardOrderPayModeService;
    @Resource
    private IFrCardOrderInfoService iFrCardOrderInfoService;
    @Resource
    private IFrCardOrderDatailService iFrCardOrderDatailService;

    @Override
    public List<Map<String, Object>>  getComplementList(FrCardOrderComplement frCardOrderComplement)throws YJException {
        if(frCardOrderComplement == null){
            throw  new YJException(YJExceptionEnum.REQUEST_NULL);
        }
        if(StringUtils.isEmpty(frCardOrderComplement.getCustomerCode())){
            throw  new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        if(StringUtils.isEmpty(frCardOrderComplement.getClientId())){
            throw  new YJException(YJExceptionEnum.VIPCARDNO_ISNOTBLANK);
        }
        if(StringUtils.isEmpty(frCardOrderComplement.getCardId())){
            throw  new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        List<Map<String, Object>> list = baseMapper.queryComplementList(frCardOrderComplement);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult toAddComplemen(FrCardOrderComplement frCardOrderComplement, List<FrCardOrderPayMode> frCardOrderPayModes, List<FrCardOrderAllotSet> frCardOrderAllotSetList, String orderSetIdList) throws YJException {
        if(frCardOrderComplement == null){
            return JsonResult.failMessage("数据异常");
        }
        if(StringUtils.isEmpty(frCardOrderComplement.getClientId()) || StringUtils.isEmpty(frCardOrderComplement.getCardId())
                || StringUtils.isEmpty(frCardOrderComplement.getSplitSetId()) || StringUtils.isEmpty(frCardOrderComplement.getPersonnelId())
                || StringUtils.isEmpty(frCardOrderComplement.getCustomerCode()) || StringUtils.isEmpty(frCardOrderComplement.getShopId())
                || frCardOrderComplement.getTotalPrice()==null || frCardOrderComplement.getType() == null || frCardOrderComplement.getNeedPrice() == null){
            return JsonResult.failMessage("补余信息有误");
        }
        //获取总支付金额
        Double allPrice  = 0.0;
        //插入补余信息-- 准备
        frCardOrderComplement.setId(UUIDUtils.generateGUID());
        if( frCardOrderPayModes != null){
            allPrice = iFrCardOrderPayModeService.getAllPrice(frCardOrderPayModes,frCardOrderComplement.getId());
        }
        //验证补余金额是否合法
        //票卷抵扣金额
        Double ticketPrice = NumberUtilsTwo.getDoublPrice(frCardOrderComplement.getTicketPrice());
        //获取抵扣的储值金额
        Double storagePrice =  NumberUtilsTwo.getDoublPrice(frCardOrderComplement.getStoragePrice());
        //总抵扣金额
        Double allTicket = ticketPrice+storagePrice;
        FrCard storageCard = null;
        Double price = 0.0;
        if( storagePrice > 0){
            if(StringUtils.isEmpty(frCardOrderComplement.getStorageCardId())){
                return JsonResult.failMessage("未获取到抵扣的会员卡号");
            }
            storageCard = iFrCardService.selectById(frCardOrderComplement.getStorageCardId());
            if(CommonUtils.CARD_STATUS_6 == storageCard.getStatus()){
                return  JsonResult.failMessage("亲,历史卡禁止任何操作呦");
            }
            if(!storageCard.getUsing()){
                return  JsonResult.failMessage("亲,该会员卡不是启用状态的呦");
            }
            price = iFrCardOrderDatailService.getOrderPrice(storageCard.getId(),storageCard.getClientId(),storageCard.getCustomerCode(),storageCard.getType(),false);
            if(storagePrice > price){
                return  JsonResult.failMessage("此会员卡剩余的储值金额不足抵扣");
            }
        }
        if( allTicket <= 0 &&  allPrice <= 0){
            return  JsonResult.failMessage("支付金额设置有误，请重新核对");
        }
        //总价
        Double totalPrice = NumberUtilsTwo.getDoublPrice(frCardOrderComplement.getTotalPrice());
        //应付金额
        Double needPrice = NumberUtilsTwo.getDoublPrice(frCardOrderComplement.getNeedPrice());
        //剩余欠款金额
        Double allNoPrice = NumberUtilsTwo.getDoublPrice(frCardOrderComplement.getNoPrice());
        //找零
        Double retChange = NumberUtilsTwo.getDoublPrice(frCardOrderComplement.getRetChange());
        if(totalPrice > (needPrice + allTicket) ){
            return  JsonResult.failMessage("支付金额不应小于应付金额");
        }
        Double noPrice = allNoPrice - totalPrice;
        if( 0 > noPrice){
            return  JsonResult.failMessage("剩余欠款设置有误，请重新核对金额");
        }
        if(((allTicket+allPrice)- totalPrice) !=retChange){
            return  JsonResult.failMessage("支付数据有误，请重新核对");
        }
        //判断订单是否存在
        FrCardOrderInfo  frCardOrderInfo = iFrCardOrderInfoService.selectById(frCardOrderComplement.getSplitSetId());
        if(frCardOrderInfo == null){
            return  JsonResult.failMessage("查无此订单信息");
        }
        if(CommonUtils.CARD_ORDRE_STATE_3 == frCardOrderInfo.getOrderState() ||  CommonUtils.CARD_ORDRE_STATE_4 == frCardOrderInfo.getOrderState()
                || CommonUtils.CARD_ORDRE_STATE_5 == frCardOrderInfo.getOrderState() ){
            return  JsonResult.failMessage("已结款订单无需补余，请重新核对");
        }
        List<String> orderSetId = null;
        //如果分期补余，补余的订单ID 不能放空
        if( CommonUtils.COMPLEMENT_TYPE_2 == frCardOrderComplement.getType()){
            if(!StringUtils.isEmpty(orderSetIdList)){
               orderSetId = JSONArray.parseArray(orderSetIdList,String.class);
            }
            if(orderSetId == null){
                return  JsonResult.failMessage("选择的分期信息有误，请重新操作");
            }
        }
        //如果是设置了业绩分配信息，业绩分配信息不能为空
        if(frCardOrderComplement.getAllotSetType() == 1){
            if(frCardOrderAllotSetList == null || frCardOrderAllotSetList.size() <=0){
                return JsonResult.failMessage("业绩分配信息有误请重新操作");
            }
        }
        FrCard frCard = iFrCardService.selectById(frCardOrderComplement.getCardId());
        if( CommonUtils.CARD_STATUS_6.toString().equals(frCard.getStatus())){
            return JsonResult.failMessage("历史卡不可操作");
        }
        //若全部欠款已结清，更新订单状态为已结款 CARD_ORDRE_STATE_3
        FrCardOrderInfo  frCardOrderInfo1 = null;
        if( noPrice <= 0){
            //订单状态更新为已结款
            frCardOrderInfo1 = new FrCardOrderInfo();
            Integer orderState = CommonUtils.CARD_ORDRE_STATE_3;
            if(CommonUtils.ORDER_STATUS_1 == frCardOrderInfo.getStatus()){
                orderState = CommonUtils.CARD_ORDRE_STATE_4;
            }
            if(CommonUtils.AUDIT_ORDER_STATUS_1 == frCardOrderInfo.getStatus()){
                orderState = CommonUtils.CARD_ORDRE_STATE_5;
            }
            frCardOrderInfo1.setOrderState(orderState);
            frCardOrderInfo1.setStatus(CommonUtils.ORDER_STATUS_0);
            frCardOrderInfo1.setAuditStatus(CommonUtils.AUDIT_ORDER_STATUS_0);
        }
        frCardOrderComplement.setUsing(true);
        frCardOrderComplement.setOrderState(CommonUtils.ORDER_TYPE_1);
        frCardOrderComplement.setStatus(CommonUtils.ORDER_STATUS_0);
        frCardOrderComplement.setAuditStatus(CommonUtils.AUDIT_ORDER_STATUS_0);
        frCardOrderComplement.setNoPrice(noPrice);
        frCardOrderComplement.setCardType(frCard.getType());
        //准备插入数据
        baseMapper.insert(frCardOrderComplement);
        //插入支付信息
        if(frCardOrderPayModes != null && frCardOrderPayModes.size()>0){
            for(FrCardOrderPayMode frCardOrderPayMode : frCardOrderPayModes){
                iFrCardOrderPayModeService.insert(frCardOrderPayMode);
            }
        }
        //更新分期表
        if(orderSetId != null && orderSetId.size()>0){
            for(String id:orderSetId){
                FrCardOrderSplitSetDd frCardOrderSplitSetDd = new FrCardOrderSplitSetDd();
                frCardOrderSplitSetDd.setStatus(CommonUtils.ORDER_SPLIT_SET_DD_1);
                frCardOrderSplitSetDd.setComplementId(frCardOrderComplement.getId());
                iFrCardOrderSplitSetDdService.update(frCardOrderSplitSetDd,new EntityWrapper<FrCardOrderSplitSetDd>().where("id={0}",id));
            }
        }
        //插入业绩分配信息
        if(frCardOrderComplement.getAllotSetType() == 1 && frCardOrderAllotSetList != null || frCardOrderAllotSetList.size() >0){
            for(FrCardOrderAllotSet frCardOrderAllotSet:frCardOrderAllotSetList){
                frCardOrderAllotSet.setOrderId(frCardOrderComplement.getId());
                frCardOrderAllotSet.setCustomerCode(frCardOrderComplement.getCustomerCode());
                frCardOrderAllotSet.setId(UUIDUtils.generateGUID());
                iFrCardOrderAllotSetService.insert(frCardOrderAllotSet);
            }
        }
//        更新会员卡订单信息
        if(frCardOrderInfo1 != null){
            iFrCardOrderInfoService.update(frCardOrderInfo1,new EntityWrapper<FrCardOrderInfo>().where("id={0}",frCardOrderInfo.getId())
                    .and("card_id={0}",frCardOrderInfo.getCardId()).and("CustomerCode={0}",frCardOrderInfo.getCustomerCode()));
        }
        // 有支付金额才需插入一条资金数据；
        if(allPrice > 0 ){
           FrCardOrderPriceDatail frCardOrderPriceDatail = this.getOrderPriceInfo(frCardOrderComplement,allPrice,false,frCard.getType());
           iFrCardOrderPriceDatailService.insert(frCardOrderPriceDatail);
        }
        //抵扣部分有数据 --- 有抵扣储值部分的；
        if(storageCard != null){
            FrCardOrderDatail frCardOrderDatail1 = this.getOrderDatailInfo(frCardOrderComplement,frCardOrderComplement.getStoragePrice(),false,storageCard.getType());
            frCardOrderDatail1.setCardId(storageCard.getId());
            frCardOrderDatail1.setClientId(storageCard.getClientId());
            frCardOrderDatail1.setFlag(frCard.getCardNo()+":补余抵扣储值");
            //查询对方会员卡剩余储值金额是否足够抵扣？
            price = iFrCardOrderDatailService.getOrderPrice(storageCard.getId(),storageCard.getClientId(),storageCard.getCustomerCode(),storageCard.getType(),false);
            frCardOrderDatail1.setOrderAmt(price + frCardOrderDatail1.getOrderPrice());
            iFrCardOrderDatailService.insert(frCardOrderDatail1);
        }
        //更新会员卡状态=======================可能需更改========如果要更改状态还需再确认下
//        FrCard frCard = iFrCardService.selectById(frCardOrderComplement.getCardId());
        return JsonResult.success(true);
    }

    /**
     * 补余初始化资金交易
     * @param frCardOrderComplement
     * @param allPrice
     * @param orderStatus
     * @param cardType
     * @return
     * @throws YJException
     */
    public FrCardOrderPriceDatail getOrderPriceInfo(FrCardOrderComplement frCardOrderComplement,Double allPrice,boolean orderStatus,Integer cardType)throws YJException{
        FrCardOrderPriceDatail frCardOrderPriceDatail = new FrCardOrderPriceDatail();
        frCardOrderPriceDatail.setId(UUIDUtils.generateGUID());
        frCardOrderPriceDatail.setCardId(frCardOrderComplement.getCardId());
        frCardOrderPriceDatail.setClientId(frCardOrderComplement.getClientId());
        frCardOrderPriceDatail.setOrderStatus(orderStatus);
        Double orderPrice = iFrCardOrderPriceDatailService.getOrderPriceByOrderStatus(orderStatus,allPrice);
        frCardOrderPriceDatail.setOrderPrice(orderPrice);
        frCardOrderPriceDatail.setShopId(frCardOrderComplement.getShopId());
        frCardOrderPriceDatail.setPersonnelId(frCardOrderComplement.getPersonnelId());
        frCardOrderPriceDatail.setUsing(true);
        frCardOrderPriceDatail.setCustomerCode(frCardOrderComplement.getCustomerCode());
        frCardOrderPriceDatail.setStatus(CommonUtils.ORDER_STATUS_0);
        frCardOrderPriceDatail.setAuditStatus(CommonUtils.AUDIT_ORDER_STATUS_0);
        frCardOrderPriceDatail.setOrderId(frCardOrderComplement.getId());
        frCardOrderPriceDatail.setOrderType(CommonUtils.PAY_MODE_ORDER_TYPE_6);
        frCardOrderPriceDatail.setCardType(cardType);
        return frCardOrderPriceDatail;
    }


    /**
     * 初始化储值订单信息
     * @param frCardOrderComplement
     * @param orderStatus
     * @param cardType
     * @return
     * @throws YJException
     */
    public FrCardOrderDatail getOrderDatailInfo(FrCardOrderComplement frCardOrderComplement,Double allPrice,boolean orderStatus,Integer cardType)throws YJException{
        FrCardOrderDatail frCardOrderDatail1 = new FrCardOrderDatail();
        frCardOrderDatail1.setId(UUIDUtils.generateGUID());
        frCardOrderDatail1.setOrderStatus(orderStatus);
        Double orderPrice = iFrCardOrderDatailService.getOrderPriceByOrderStatus(orderStatus,allPrice);
        frCardOrderDatail1.setOrderPrice(orderPrice);
        frCardOrderDatail1.setShopId(frCardOrderComplement.getShopId());
        frCardOrderDatail1.setPersonnelId(frCardOrderComplement.getPersonnelId());
        frCardOrderDatail1.setUsing(true);
        frCardOrderDatail1.setCustomerCode(frCardOrderComplement.getCustomerCode());
        frCardOrderDatail1.setType(CommonUtils.ORDER_DATAIL_TYPE_2);
        frCardOrderDatail1.setStatus(CommonUtils.ORDER_STATUS_0);
        frCardOrderDatail1.setAuditStatus(CommonUtils.AUDIT_ORDER_STATUS_0);
        frCardOrderDatail1.setOrderId(frCardOrderComplement.getId());
        frCardOrderDatail1.setOrderType(CommonUtils.PAY_MODE_ORDER_TYPE_6);
        frCardOrderDatail1.setCardType(cardType);
        frCardOrderDatail1.setCardId(frCardOrderComplement.getCardId());
        frCardOrderDatail1.setClientId(frCardOrderComplement.getClientId());
        return frCardOrderDatail1;
    }



    /**
     * 补余冲销
     * @param frCardOrderComplement
     * @return
     * @throws YJException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean toUpdateOrderStart(FrCardOrderComplement frCardOrderComplement) throws YJException {
        if(frCardOrderComplement == null){
            throw new YJException(YJExceptionEnum.OBJECT_NOT_FOUND);
        }
        if(CommonUtils.ORDER_TYPE_2 == frCardOrderComplement.getOrderState()){
            throw new YJException(YJExceptionEnum.ORDER_TYPE_STATUS);
        }
        // 已经审核通过的数据冲销
        if(CommonUtils.ORDER_STATUS_1 == frCardOrderComplement.getStatus() || CommonUtils.AUDIT_ORDER_STATUS_1 == frCardOrderComplement.getAuditStatus()){
            throw new YJException(YJExceptionEnum.OBJECT_TYPE_STATUS);
        }
        //如果有抵扣的储值，需撤销
        FrCardOrderDatail frCardOrderDatail1 = null;
        if(frCardOrderComplement.getStoragePrice() > 0){
            if(StringUtils.isEmpty(frCardOrderComplement.getStorageCardId())){
                throw new YJException(YJExceptionEnum.STORAGE_PRICE_CARD_ID);
            }
          FrCard frCard = iFrCardService.selectById(frCardOrderComplement.getStorageCardId());
          frCardOrderDatail1 = iFrCardOrderDatailService.selectOne(
                  new EntityWrapper<FrCardOrderDatail>().where("CustomerCode={0}",frCard.getCustomerCode()).and("order_id={0}",frCardOrderComplement.getId())
                          .and("type={0}",CommonUtils.ORDER_DATAIL_TYPE_2).and("card_id={0}",frCard.getId()).and("client_id={0}",frCard.getClientId())
                          .and("card_type={0}",frCard.getType()).and("order_status={0}",0).and("order_type={0}",CommonUtils.PAY_MODE_ORDER_TYPE_6));
        }
        FrCardOrderComplement frCardOrderComplement1 = new FrCardOrderComplement();
        //补余状态冲销
        frCardOrderComplement1.setOrderState(CommonUtils.ORDER_TYPE_2);
        //查看补余的订单信息 ---- 是结款状态才需要更新成已付款
        FrCardOrderInfo frCardOrderInfo = iFrCardOrderInfoService.selectById(frCardOrderComplement.getSplitSetId());
        boolean isFlag = false;
        Integer status =  CommonUtils.ORDER_STATUS_0;
        Integer auditStatus =  CommonUtils.AUDIT_ORDER_STATUS_0;
        if(CommonUtils.CARD_ORDRE_STATE_3 == frCardOrderInfo.getOrderState()){
            isFlag = true;
        }
        if(CommonUtils.CARD_ORDRE_STATE_4 == frCardOrderInfo.getOrderState()){
            status =  CommonUtils.ORDER_STATUS_1;
            isFlag = true;
        }
        if(CommonUtils.CARD_ORDRE_STATE_5 == frCardOrderInfo.getOrderState()){
            status =  CommonUtils.ORDER_STATUS_1;
            auditStatus =  CommonUtils.AUDIT_ORDER_STATUS_1;
            isFlag = true;
        }
        if(frCardOrderComplement.getType() == CommonUtils.COMPLEMENT_TYPE_2){
            //撤销分期表数据
            FrCardOrderSplitSet frCardOrderSplitSet = iFrCardOrderSplitSetService.selectOne(new EntityWrapper<FrCardOrderSplitSet>().where("order_id={0}",frCardOrderComplement.getSplitSetId()).and("CustomerCode={0}",frCardOrderComplement.getCustomerCode()));
            if(frCardOrderSplitSet != null){
                FrCardOrderSplitSetDd frCardOrderSplitSetDd = new FrCardOrderSplitSetDd();
                frCardOrderSplitSetDd.setStatus(CommonUtils.ORDER_SPLIT_SET_DD_0);
                iFrCardOrderSplitSetDdService.update(frCardOrderSplitSetDd,new EntityWrapper<FrCardOrderSplitSetDd>().where("split_set_id={0}",frCardOrderSplitSet.getId()).and("complement_id={0}",frCardOrderComplement.getId()));
            }
        }
        //更新补余信息
        baseMapper.update(frCardOrderComplement1,new EntityWrapper<FrCardOrderComplement>().where("id={0}",frCardOrderComplement.getId()).and("CustomerCode={0}",frCardOrderComplement.getCustomerCode()));
       // 业绩表信息初始化
        Integer allotSetType = frCardOrderComplement.getAllotSetType();
        if(allotSetType != null && allotSetType == 1){
//           查询设置的业绩表信息;
            FrCardOrderAllotSet frCardOrderAllotSet = new FrCardOrderAllotSet();
            //设置的业绩表不启用；
            frCardOrderAllotSet.setUsing(false);
            iFrCardOrderAllotSetService.update(frCardOrderAllotSet,
                    new EntityWrapper<FrCardOrderAllotSet>().where("order_type={0}",CommonUtils.ORDER_ALLOT_TYPE_6).
                            and("order_id={0}",frCardOrderComplement.getId()).and("CustomerCode={0}",frCardOrderComplement.getCustomerCode()));
        }
        FrCardOrderPriceDatail frCardOrderPriceDatail = new FrCardOrderPriceDatail();
        frCardOrderPriceDatail.setCardId(frCardOrderComplement.getCardId());
        frCardOrderPriceDatail.setCustomerCode(frCardOrderComplement.getCustomerCode());
        frCardOrderPriceDatail.setOrderType(CommonUtils.PAY_MODE_ORDER_TYPE_6);
        frCardOrderPriceDatail.setClientId(frCardOrderComplement.getClientId());
        frCardOrderPriceDatail.setOrderId(frCardOrderComplement.getId());
        frCardOrderPriceDatail = iFrCardOrderPriceDatailService.queryTopOne(frCardOrderPriceDatail);
        Date date = new Date();
        if(frCardOrderPriceDatail != null){
            frCardOrderPriceDatail.setId(UUIDUtils.generateGUID());
            frCardOrderPriceDatail.setOrderStatus(true);
            Double orderPrice = iFrCardOrderPriceDatailService.getOrderPriceByOrderStatus(true,frCardOrderPriceDatail.getOrderPrice());
            frCardOrderPriceDatail.setOrderPrice(orderPrice);
            frCardOrderPriceDatail.setCreateTime(date);
            frCardOrderPriceDatail.setUpdateTime(date);
            frCardOrderPriceDatail.setCreateUserId(frCardOrderComplement.getUpdateUserId());
            frCardOrderPriceDatail.setUpdateUserId(frCardOrderComplement.getUpdateUserId());
            iFrCardOrderPriceDatailService.insert(frCardOrderPriceDatail);
        }
        if(frCardOrderDatail1 != null){
            frCardOrderDatail1.setId(UUIDUtils.generateGUID());
            frCardOrderDatail1.setFlag("补余抵扣冲销");
            frCardOrderDatail1.setOrderStatus(true);
            Double orderPrice = iFrCardOrderDatailService.getOrderPriceByOrderStatus(true,frCardOrderDatail1.getOrderPrice());
            frCardOrderDatail1.setOrderPrice(orderPrice);
            frCardOrderDatail1.setCreateTime(date);
            frCardOrderDatail1.setUpdateTime(date);
            //查询对方会员卡剩余储值金额是否足够抵扣？
            Double price = iFrCardOrderDatailService.getOrderPrice(frCardOrderDatail1.getCardId(),frCardOrderDatail1.getClientId(),frCardOrderDatail1.getCustomerCode(),frCardOrderDatail1.getCardType(),false);
            frCardOrderDatail1.setOrderAmt(price + frCardOrderDatail1.getOrderPrice());
            iFrCardOrderDatailService.insert(frCardOrderDatail1);
        }
        if(isFlag){
            FrCardOrderInfo frCardOrderInfo1 = new FrCardOrderInfo();
            frCardOrderInfo1.setOrderState(CommonUtils.CARD_ORDRE_STATE_2);
            frCardOrderInfo1.setStatus(status);
            frCardOrderInfo1.setAuditStatus(auditStatus);
            iFrCardOrderInfoService.update(frCardOrderInfo1,new EntityWrapper<FrCardOrderInfo>().where("id={0}",frCardOrderInfo.getId())
                    .and("CustomerCode={0}",frCardOrderInfo.getCustomerCode()));
        }
        return true;
    }

    @Override
    public Double getComplementAllMoney(FrCardOrderComplement frCardOrderComplement) throws YJException {
        if(frCardOrderComplement == null){
            throw  new YJException(YJExceptionEnum.REQUEST_NULL);
        }
        if(StringUtils.isEmpty(frCardOrderComplement.getCustomerCode())){
            throw  new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        if(StringUtils.isEmpty(frCardOrderComplement.getClientId())){
            throw  new YJException(YJExceptionEnum.VIPCARDNO_ISNOTBLANK);
        }
        if(StringUtils.isEmpty(frCardOrderComplement.getCardId())){
            throw  new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        Double complementAllMoney = baseMapper.getComplementAllMoney(frCardOrderComplement);
        return complementAllMoney;
    }
}
