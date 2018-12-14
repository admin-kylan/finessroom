package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CommonUtils;
import com.yj.common.util.NumberUtilsTwo;
import com.yj.common.util.StringUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.FrCardOrderPayModeMapper;
import com.yj.dal.dao.FrCardOriginalSetMapper;
import com.yj.dal.model.*;
import com.yj.dal.dao.FrCardOrderBackMapper;
import com.yj.service.service.*;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员卡退卡订单 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-19
 */
@Service
public class FrCardOrderBackServiceImpl extends BaseServiceImpl<FrCardOrderBackMapper, FrCardOrderBack> implements IFrCardOrderBackService {

    @Resource
    private FrCardOriginalSetMapper frCardOriginalSetMapper;
    @Resource
    private FrCardOrderPayModeMapper frCardOrderPayModeMapper;
    @Resource
    private IFrCardService iFrCardService;
    @Resource
    private IFrChildCardService iFrChildCardService;
    @Resource
    private IFrCardOrderDatailService iFrCardOrderDatailService;
    @Resource
    private IFrCardOrderPriceDatailService iFrCardOrderPriceDatailService;

    /**
     * 获取退卡信息
     * @param frCardOrderBack
     * @return
     * @throws YJException
     */
    @Override
    public Map<String, Object> queryBlackCardList(FrCardOrderBack frCardOrderBack) throws YJException {
        if(frCardOrderBack == null){
            throw new YJException(YJExceptionEnum.OBJECT_NOT_FOUND);
        }
        if(StringUtils.isEmpty(frCardOrderBack.getClientId()) || StringUtils.isEmpty(frCardOrderBack.getCustomerCode())){
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        List<Map<String, Object>> list = baseMapper.queryBlackCardList(frCardOrderBack);
        Map<String,Object> map = new HashMap<>();
        Double SupplyMoney = baseMapper.queryBlackCardSum(frCardOrderBack);
        map.put("SupplyMoney",SupplyMoney);
        map.put("list",list);
        return map;
    }

    /**
     * 添加退卡信息
     * @param frCardOrderBack
     * @param shopId
     * @return
     * @throws YJException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult toInsertBlace(FrCardOrderBack frCardOrderBack,String shopId) throws YJException {
        if(frCardOrderBack == null){
            throw new YJException(YJExceptionEnum.REQUEST_NULL);
        }
        if(StringUtils.isEmpty(frCardOrderBack.getCustomerCode()) || StringUtils.isEmpty(frCardOrderBack.getClientId())
                || StringUtils.isEmpty(frCardOrderBack.getCardId()) || frCardOrderBack.getRefundType() == null
                || frCardOrderBack.getAllotSetType() == null || frCardOrderBack.getNeedPrice() == null
                || frCardOrderBack.getTotalPrice() == null || frCardOrderBack.getProcedPrcie() == null){
            throw  new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        if(CommonUtils.PAY_MODE_4 != frCardOrderBack.getRefundType() ){
            if(StringUtils.isEmpty(frCardOrderBack.getAccountNumber())){
                return JsonResult.failMessage("请填写退款账号");
            }
        }
        if(frCardOrderBack.getTotalPrice() > frCardOrderBack.getNeedPrice()){
            return JsonResult.failMessage("实际退款金额大于应退款金额，请重新核对");
        }
        //查询卡前设置
        FrCardOriginalSet frCardOriginalSet = new FrCardOriginalSet();
        frCardOriginalSet.setCardId(frCardOrderBack.getCardId());
        Map<String,Object> map = frCardOriginalSetMapper.querySelectOneFrCard(frCardOriginalSet);
        boolean zkxffs = false;
        Integer status = null;
        Integer cardType = null;
        if(map != null){
            frCardOrderBack.setCardFlag(map.get("cardFlag").toString());
            frCardOrderBack.setCardTypeName(map.get("cardTypeName").toString());
            if(map.get("zkXffs") != null){
                if(CommonUtils.ORIGINALSET_ZKXFFS_1.equals(map.get("zkXffs").toString())){
                    zkxffs = true;
                }
            }
            Object fcStatus = map.get("fcStatus");
            if(fcStatus != null){
                status = Integer.valueOf(fcStatus.toString());
            }
            Object type  = map.get("cardType");
            if(type != null && type instanceof Integer){
                cardType = (Integer)type ;
            }
        }
        //根据会员卡信息查询到卡前设置，就不需要再查询会员卡
        if(map == null){
            //获取会员卡状态，已退卡的不再操作
            FrCard  frCard = iFrCardService.selectOne(new EntityWrapper<FrCard>().where("id={0}",frCardOrderBack.getCardId())
                    .and("is_using={0}",1).and("client_id={0}",frCardOrderBack.getClientId()).and("CustomerCode={0}",frCardOrderBack.getCustomerCode()));
            if(frCard == null){
                return JsonResult.failMessage("查无要退卡的会员卡信息");
            }
            status = frCard.getStatus();
            cardType = frCard.getType();
        }
        if(status == null){
            return JsonResult.failMessage("查询会员卡状态有误，操作失败");
        }
        if(cardType == null){
            return  JsonResult.failMessage("未获取退卡的卡类型");
        }
        if(CommonUtils.CARD_STATUS_2.toString().equals(status) || CommonUtils.CARD_STATUS_3.toString().equals(status)
                || CommonUtils.CARD_STATUS_4.toString().equals(status) || CommonUtils.CARD_STATUS_6.toString().equals(status)){
            return JsonResult.failMessage("会员卡状态冻结、过期、未开卡、历史，不可操作");
        }
        //插入退卡信息
        frCardOrderBack.setId(UUIDUtils.generateGUID());
        frCardOrderBack.setUsing(true);
        //更新会员卡状态--历史卡
        FrCard frCard = new FrCard();
        frCard.setStatus(CommonUtils.CARD_STATUS_6);
        //更新子卡信息
        FrChildCard frChildCard = new FrChildCard();
        frChildCard.setType(CommonUtils.CHILD_CARD_STATUS_2);
        // 订单表
        FrCardOrderPayMode frCardOrderPayMode = new FrCardOrderPayMode();
        frCardOrderPayMode.setId(UUIDUtils.generateGUID());
        frCardOrderPayMode.setOrderId(frCardOrderBack.getId());
        frCardOrderPayMode.setOrderType(CommonUtils.PAY_MODE_ORDER_TYPE_7);
        frCardOrderPayMode.setPayMode(frCardOrderBack.getRefundType());
        frCardOrderPayMode.setPayPrice(frCardOrderBack.getTotalPrice());
        frCardOrderPayMode.setType(false);
        //初始化资金交易明细
        FrCardOrderPriceDatail frCardOrderPriceDatail =  this.getCardDatailAndPrice(frCardOrderBack,true ,cardType,shopId);
        //获取会员卡的剩余权益，剩余储值金额，分享出去的权益
        List<FrCardOrderDatail> frCardOrderDatailList = this.getListOrderDatail(frCardOrderPriceDatail,zkxffs);
        Integer success = baseMapper.insert(frCardOrderBack);
        if(SqlHelper.retBool(success)){
            iFrChildCardService.update(frChildCard,new EntityWrapper<FrChildCard>().where("parent_card_id={0}",frCardOrderBack.getCardId()).and("CustomerCode={0}",frCardOrderBack.getCustomerCode()));
            iFrCardService.update(frCard,new EntityWrapper<FrCard>().where("id={0}",frCardOrderBack.getCardId()).and("CustomerCode={0}",frCardOrderBack.getCustomerCode()));
            frCardOrderPayModeMapper.insert(frCardOrderPayMode);
            //有金额才需要支付-------------先让其他状态都更新了再操作资金交易部分
            if(frCardOrderDatailList != null && frCardOrderDatailList.size() >0){
                iFrCardOrderPriceDatailService.insert(frCardOrderPriceDatail);
                for(FrCardOrderDatail frCardOrderDatail:frCardOrderDatailList){
                    iFrCardOrderDatailService.insert(frCardOrderDatail);
                }
            }
        }
        return JsonResult.success(SqlHelper.retBool(success));
    }

    /**
     * 添加退卡资金，订单明细
     * @param frCardOrderBack
     * @param isFlage  支付状态（0：支出 1：收入）
     * @param cardType 卡类型
     * @param shopId   门店ID
     * @return
     * @throws YJException
     */
    @Transactional(rollbackFor = Exception.class)
    public FrCardOrderPriceDatail getCardDatailAndPrice(FrCardOrderBack frCardOrderBack,boolean isFlage,Integer cardType,String shopId)throws YJException{
        //初始化订单交易明细
        FrCardOrderPriceDatail  frCardOrderPriceDatail = new FrCardOrderPriceDatail();
        frCardOrderPriceDatail.setId(UUIDUtils.generateGUID());
        frCardOrderPriceDatail.setStatus(CommonUtils.ORDER_STATUS_0);
        frCardOrderPriceDatail.setAuditStatus(CommonUtils.AUDIT_ORDER_STATUS_0);
        frCardOrderPriceDatail.setOrderType(CommonUtils.PAY_MODE_ORDER_TYPE_7);
        frCardOrderPriceDatail.setCardId(frCardOrderBack.getCardId());
        frCardOrderPriceDatail.setClientId(frCardOrderBack.getClientId());
        frCardOrderPriceDatail.setPersonnelId(frCardOrderBack.getPersonnelId());
        frCardOrderPriceDatail.setCustomerCode(frCardOrderBack.getCustomerCode());
        frCardOrderPriceDatail.setOrderId(frCardOrderBack.getId());
//        支付状态（0：支出 1：收入）
        frCardOrderPriceDatail.setOrderStatus(isFlage);
        Double orderPrice = iFrCardOrderPriceDatailService.getOrderPriceByOrderStatus(isFlage,frCardOrderBack.getTotalPrice());
        frCardOrderPriceDatail.setOrderPrice(orderPrice);
        frCardOrderPriceDatail.setCardType(cardType);
        frCardOrderPriceDatail.setShopId(shopId);
        return frCardOrderPriceDatail;
    }

    /**
     * 获取会员卡，及名下子卡的所有剩余储值金额及权益
     * @param frCardOrderBack
     * @param zkxffs
     * @return
     * @throws YJException
     */
    @Override
    public Map<String,Double>  getHaveNum(FrCardOrderBack frCardOrderBack,boolean zkxffs)throws  YJException{
        //获取会员卡的剩余权益，剩余储值金额，分享出去的权益
        FrCardOrderDatail frCardOrderDatail1 = new FrCardOrderDatail();
        frCardOrderDatail1.setClientId(frCardOrderBack.getClientId());
        frCardOrderDatail1.setCardId(frCardOrderBack.getCardId());
        frCardOrderDatail1.setCustomerCode(frCardOrderBack.getCustomerCode());
        frCardOrderDatail1.setCustomerCode(frCardOrderBack.getCustomerCode());
        frCardOrderDatail1.setUsing(true);
        Map<String,Double> map1 = iFrCardOrderDatailService.getAmtByCardId(frCardOrderDatail1);
        Double orderPrice = NumberUtilsTwo.getDoubleNum("orderPrice",map1); //剩余储值金额---父卡
        Double orderRightsNum = NumberUtilsTwo.getDoubleNum("orderRightsNum",map1);  //剩余权益  --父卡
        Map<String,Double> map2 = new HashMap<>();
        if(zkxffs){
            //获取所有子卡的剩余金额，权益
            FrChildCard frChildCard = new FrChildCard();
            frChildCard.setCustomerCode(frCardOrderDatail1.getCustomerCode());
            frChildCard.setParentCardId(frCardOrderDatail1.getCardId());
            map2 = iFrChildCardService.queryChildCardAmt(frChildCard);
        }
        Double childPrice = NumberUtilsTwo.getDoubleNum("orderPrice",map2); //剩余储值金额--- 子卡
        Double childRightsNum = NumberUtilsTwo.getDoubleNum("orderRightsNum",map2);  //剩余权益  -- 子卡
        Map<String,Double> havaNum = new HashMap<>();
        havaNum.put("orderPrice",orderPrice);
        havaNum.put("orderRightsNum",orderRightsNum);
        havaNum.put("childPrice",childPrice);
        havaNum.put("childRightsNum",childRightsNum);
        return  havaNum;
    }


    public FrCardOrderDatail getOrderDatailInfo( FrCardOrderPriceDatail frCardOrderPriceDatail, String mess,Integer type,
                                                  Double orderPrice,String childId,String clientId,
                                                 List<FrCardOrderDatail> frCardOrderDatailList)throws YJException{
        FrCardOrderDatail frCardOrderDatail = null;
        if(orderPrice > 0 ) {
            frCardOrderDatail = iFrCardOrderDatailService.getOrderDatailInfo(frCardOrderPriceDatail);
            frCardOrderDatail.setFlag(mess);
            frCardOrderDatail.setType(type);
            frCardOrderDatail.setOrderStatus(false);
            Double price = iFrCardOrderDatailService.getOrderPriceByOrderStatus(false, orderPrice);
            if (CommonUtils.ORDER_DATAIL_TYPE_2 == type) {
                frCardOrderDatail.setOrderPrice(price);
            }
            if (CommonUtils.ORDER_DATAIL_TYPE_1 == type) {
                frCardOrderDatail.setOrderRightsNum(price);
            }
            if (!StringUtils.isEmpty(childId)) {
                frCardOrderDatail.setCardId(childId);
            }
            if (!StringUtils.isEmpty(clientId)) {
                frCardOrderDatail.setClientId(clientId);
            }
            frCardOrderDatail.setOrderAmt(0.0);
        }
        if(frCardOrderDatail != null ){
            frCardOrderDatailList.add(frCardOrderDatail);
        }
        return frCardOrderDatail;
    }


    public List<FrCardOrderDatail> getListOrderDatail( FrCardOrderPriceDatail frCardOrderPriceDatail, boolean zkxffs)throws YJException{
        List<FrCardOrderDatail> frCardOrderDatailList = null;
        if(frCardOrderPriceDatail != null){
            frCardOrderDatailList = new ArrayList<>();
            //获取会员卡的剩余权益，剩余储值金额，分享出去的权益
            FrCardOrderDatail frCardOrderDatail1 = new FrCardOrderDatail();
            frCardOrderDatail1.setClientId(frCardOrderPriceDatail.getClientId());
            frCardOrderDatail1.setCardId(frCardOrderPriceDatail.getCardId());
            frCardOrderDatail1.setCustomerCode(frCardOrderPriceDatail.getCustomerCode());
            frCardOrderDatail1.setUsing(true);
            Map<String,Double> map1 = iFrCardOrderDatailService.getAmtByCardId(frCardOrderDatail1);
            Double orderPrice = NumberUtilsTwo.getDoubleNum("orderPrice",map1); //剩余储值金额---父卡
            Double orderRightsNum = NumberUtilsTwo.getDoubleNum("orderRightsNum",map1);  //剩余权益  --父卡
            this.getOrderDatailInfo(frCardOrderPriceDatail,"退卡扣款储值",CommonUtils.ORDER_DATAIL_TYPE_2, orderPrice,"","",frCardOrderDatailList);
            this.getOrderDatailInfo(frCardOrderPriceDatail,"退卡扣除权益",CommonUtils.ORDER_DATAIL_TYPE_1, orderRightsNum,"","",frCardOrderDatailList);
            if(zkxffs){
                //获取此卡下的所有子卡的剩余金额，权益
                FrChildCard frChildCard = new FrChildCard();
                frChildCard.setCustomerCode(frCardOrderDatail1.getCustomerCode());
                frChildCard.setParentCardId(frCardOrderDatail1.getCardId());
                List<Map<String,Object>> maps =  iFrChildCardService.queryChildCardAmtList(frChildCard);
                if(maps != null && maps.size() >0){
                    for(Map<String,Object> map :maps){
                        Double childOrderPrice = NumberUtilsTwo.getDouNum("orderPrice",map);
                        Double childOrderRights =  NumberUtilsTwo.getDouNum("orderRightsNum",map);
                        String childId = "";
                        String clientId = "";
                        if(map.get("id") != null){
                            Object  id= map.get("id");
                            childId = id.toString();
                        }
                        if(map.get("id") != null){
                            Object  client = map.get("childClientId");
                            clientId = client.toString();
                        }
                        if(!StringUtils.isEmpty(childId)){
                            this.getOrderDatailInfo(frCardOrderPriceDatail,"父卡退卡扣除储值",CommonUtils.ORDER_DATAIL_TYPE_2,
                                    childOrderPrice,childId.toString(),clientId, frCardOrderDatailList);
                            this.getOrderDatailInfo(frCardOrderPriceDatail,"父卡退卡扣除权益",CommonUtils.ORDER_DATAIL_TYPE_1,
                                    childOrderRights,childId.toString(),clientId, frCardOrderDatailList);
                        }
                    }
                }
            }
        }
        return frCardOrderDatailList;
    }
}
