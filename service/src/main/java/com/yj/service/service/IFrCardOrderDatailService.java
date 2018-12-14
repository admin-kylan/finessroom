package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.dal.model.FrCardOrderDatail;
import com.yj.dal.model.FrCardOrderPriceDatail;
import com.yj.service.base.BaseService;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 * 订单交易明细 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-24
 */
public interface IFrCardOrderDatailService extends BaseService<FrCardOrderDatail> {

    /**
     * * 统计剩余的储值金额  type注意 (根据a.order_price)
     * @param frCardOrderDatail
     * @param isFlage  true 根据（a.order_price）如果数据量多的话，建议不使用
     *                   false 根据最新的数据的剩余金额
     * @return
     * @throws YJException
     */
    public Double querySumOrderPrice(FrCardOrderDatail frCardOrderDatail, boolean isFlage)throws YJException;

    /**
     * 只获取订单的一条最新一条状态
     * @param frCardOrderDatail
     * @return
     * @throws YJException
     */
    FrCardOrderDatail queryTopOne(FrCardOrderDatail frCardOrderDatail) throws YJException;

    /**
     * 根据订单收支，设置金额正负
     * @param orderStatus
     * @param storagePrice
     * @return
     */
    Double getOrderPriceByOrderStatus(boolean orderStatus,Double storagePrice)throws YJException;

    /**
     *  根据资金交易明细初始化订单明细部分参数
     * @param frCardOrderPriceDatail
     * @return
     * @throws YJException
     */
    FrCardOrderDatail getOrderDatailInfo( FrCardOrderPriceDatail frCardOrderPriceDatail) throws YJException;

    /**
     * 获取并设置余额后插入数据
     * @param frCardOrderDatail
     * @param isFlage
     * @return
     * @throws YJException
     */
     boolean queryOrderPriceAmt(FrCardOrderDatail frCardOrderDatail, boolean isFlage)throws YJException;


     Map<String,Double> getAmtByCardId(FrCardOrderDatail frCardOrderDatail)throws YJException;

     Double getOrderPrice(String cardId,String clientId,String code, Integer type,boolean isFlage)throws YJException;

}
