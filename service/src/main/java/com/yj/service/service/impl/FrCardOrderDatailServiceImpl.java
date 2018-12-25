package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.util.CommonUtils;
import com.yj.common.util.NumberUtilsTwo;
import com.yj.common.util.StringUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.model.FrCardOrderDatail;
import com.yj.dal.dao.FrCardOrderDatailMapper;
import com.yj.dal.model.FrCardOrderPriceDatail;
import com.yj.service.service.IFrCardOrderDatailService;
import com.yj.service.base.BaseServiceImpl;
import com.yj.service.service.IFrCardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * 订单交易明细 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-24
 */
@Service
public class FrCardOrderDatailServiceImpl extends BaseServiceImpl<FrCardOrderDatailMapper, FrCardOrderDatail> implements IFrCardOrderDatailService {

    @Resource
    private IFrCardService iFrCardService;

    @Override
    public Double querySumOrderPrice(FrCardOrderDatail frCardOrderDatail,boolean isFlage) throws YJException {
        if(frCardOrderDatail == null){
            throw  new  YJException(YJExceptionEnum.PARAM_ERROR);
        }
        if(StringUtils.isEmpty(frCardOrderDatail.getCardId()) || StringUtils.isEmpty(frCardOrderDatail.getClientId())
         || StringUtils.isEmpty(frCardOrderDatail.getCustomerCode()) || frCardOrderDatail.getType() == null){
            throw new  YJException(YJExceptionEnum.REQUEST_NULL);
        }
        Double amt = 0.0;
        if(isFlage){
            amt = baseMapper.querySumOrderPrice(frCardOrderDatail);
        }
        if(!isFlage){
            amt = baseMapper.queryAmtOrderPrice(frCardOrderDatail);
        }
        if(amt == null){
            amt = 0.0;
        }
        return amt;
    }

    @Override
    public FrCardOrderDatail queryTopOne(FrCardOrderDatail frCardOrderDatail) throws YJException {
        if(frCardOrderDatail == null){
            throw new YJException(YJExceptionEnum.REQUEST_NULL);
        }
        if(StringUtils.isEmpty(frCardOrderDatail.getCardId()) || StringUtils.isEmpty(frCardOrderDatail.getClientId())
                || StringUtils.isEmpty(frCardOrderDatail.getCustomerCode()) || StringUtils.isEmpty(frCardOrderDatail.getOrderId())){
            throw  new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        FrCardOrderDatail frCardOrderDatail1 = baseMapper.queryTopOne(frCardOrderDatail);
        return frCardOrderDatail1;
    }

    /**
     * 根据订单收支，设置金额正负
     * @param orderStatus
     * @param storagePrice
     * @return
     */
    @Override
    public Double getOrderPriceByOrderStatus(boolean orderStatus,Double storagePrice)throws YJException{
        Double orderPrice = storagePrice;
        //收入
        if(orderStatus){
            if(storagePrice < 0){
                orderPrice = storagePrice*(-1.0);
            }
        }
        if(!orderStatus){
            if(storagePrice > 0){
                orderPrice = storagePrice*(-1.0);
            }
        }
        return  orderPrice;
    }

    @Override
    public FrCardOrderDatail getOrderDatailInfo(FrCardOrderPriceDatail frCardOrderPriceDatail) throws YJException {
        FrCardOrderDatail frCardOrderDatail = new FrCardOrderDatail();
        frCardOrderDatail.setId(UUIDUtils.generateGUID());
        frCardOrderDatail.setStatus(frCardOrderPriceDatail.getStatus());
        frCardOrderDatail.setAuditStatus(frCardOrderPriceDatail.getAuditStatus());
        frCardOrderDatail.setCustomerCode(frCardOrderPriceDatail.getCustomerCode());
        frCardOrderDatail.setUsing(true);
        frCardOrderDatail.setCardType(frCardOrderPriceDatail.getCardType());
        frCardOrderDatail.setShopId(frCardOrderPriceDatail.getShopId());
        frCardOrderDatail.setPersonnelId(frCardOrderPriceDatail.getPersonnelId());
        frCardOrderDatail.setOrderType(frCardOrderPriceDatail.getOrderType());
        frCardOrderDatail.setOrderPriceId(frCardOrderPriceDatail.getId());
        frCardOrderDatail.setOrderId(frCardOrderPriceDatail.getOrderId());
        frCardOrderDatail.setCardId(frCardOrderPriceDatail.getCardId());
        frCardOrderDatail.setClientId(frCardOrderPriceDatail.getClientId());
        return frCardOrderDatail;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean queryOrderPriceAmt(FrCardOrderDatail frCardOrderDatail, boolean isFlage) throws YJException {
        //要更新前再设置余额
        FrCardOrderDatail frCardOrderDatail1 = new FrCardOrderDatail();
        frCardOrderDatail1.setCustomerCode(frCardOrderDatail.getCustomerCode());
        frCardOrderDatail1.setClientId(frCardOrderDatail.getClientId());
        frCardOrderDatail1.setCardId(frCardOrderDatail.getCardId());
        frCardOrderDatail1.setCardType(frCardOrderDatail.getCardType());
        frCardOrderDatail1.setType(frCardOrderDatail.getType());
        Double amt = this.querySumOrderPrice(frCardOrderDatail1,isFlage);
        Integer type = frCardOrderDatail.getType();
        Double orderPrice = 0.0;
        if(CommonUtils.ORDER_DATAIL_TYPE_1 == type){
            //权益
           orderPrice = frCardOrderDatail.getOrderRightsNum();
        }
        if(CommonUtils.ORDER_DATAIL_TYPE_2 == type){
            //储值
            orderPrice = frCardOrderDatail.getOrderPrice();
        }
        if(CommonUtils.ORDER_DATAIL_TYPE_3 == type){
            //其他消费   --  其他消费表里还没有对应的统计，
            orderPrice = 0.0;
        }
        if (orderPrice == null){ orderPrice = 0.0; }
        frCardOrderDatail.setOrderAmt(orderPrice + amt);
        frCardOrderDatail.setCreateTime(new Date());
        frCardOrderDatail.setUpdateTime(new Date());
        Integer succ = baseMapper.insert(frCardOrderDatail);
        return SqlHelper.retBool(succ);
    }


    /**
     * 获取信息获取卡中剩余金额，剩余权益
     * @param frCardOrderDatail
     * @return
     * @throws YJException
     */
    @Override
    public Map<String,Double> getAmtByCardId(FrCardOrderDatail frCardOrderDatail) throws YJException {
        FrCardOrderDatail frCardOrderDatail1 = new FrCardOrderDatail();
        frCardOrderDatail1.setCustomerCode(frCardOrderDatail.getCustomerCode());
        frCardOrderDatail1.setClientId(frCardOrderDatail.getClientId());
        frCardOrderDatail1.setCardId(frCardOrderDatail.getCardId());
        Map<String,Object> orderCard  = baseMapper.getAmtByCardId(frCardOrderDatail1);
        String bindTime = StringUtils.getStringObject("bindTime",orderCard);
        Integer cardType = NumberUtilsTwo.getIntNum("cardType",orderCard);
        //剩余权益 ------ 根据会员卡类型，初始化剩余权益
        Double orderRightsNum = NumberUtilsTwo.getDouNum("orderRightsNum",orderCard);
        orderRightsNum = iFrCardService.getHaveNumByType(cardType,bindTime,orderRightsNum);

        Map<String,Double> amtByCard = new HashMap<>();
        amtByCard.put("orderRightsNum",orderRightsNum);
        amtByCard.put("orderPrice",NumberUtilsTwo.getDouNum("orderPrice",orderCard));
        return amtByCard;
    }

    /**
     * 获取剩余储值金额 封装
     * @param cardId
     * @param clientId
     * @param code
     * @param type
     * @return
     * @throws YJException
     */
    @Override
    public Double getOrderPrice(String cardId, String clientId, String code, Integer type,boolean isFlage) throws YJException {
        FrCardOrderDatail frCardOrderDatail = new FrCardOrderDatail();
        frCardOrderDatail.setCardId(cardId);
        frCardOrderDatail.setClientId(clientId);
        frCardOrderDatail.setCustomerCode(code);
        frCardOrderDatail.setType(type);
        //统计剩余的储值金额
        Double orderPrice = this.querySumOrderPrice(frCardOrderDatail,isFlage);
        if(orderPrice == null){
            orderPrice = 0.0;
        }
        return  orderPrice;
    }
}

