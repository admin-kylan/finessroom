package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.dal.model.FrCardOrderDatail;
import com.yj.dal.model.FrCardOrderPriceDatail;
import com.yj.service.base.BaseService;

import java.math.BigDecimal;

/**
 * <p>
 * 资金交易明细 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-24
 */
public interface IFrCardOrderPriceDatailService extends BaseService<FrCardOrderPriceDatail> {

    /**
     * 只获取订单的一条最新一条状态
     * @param frCardOrderPriceDatail
     * @return
     * @throws YJException
     */
    FrCardOrderPriceDatail queryTopOne(FrCardOrderPriceDatail frCardOrderPriceDatail) throws YJException;

    /**
     * 初始化订单数据
     * @param frCardOrderDatail
     * @return
     * @throws YJException
     */
//    FrCardOrderPriceDatail getCardDatailAndPrice(FrCardOrderDatail frCardOrderDatail) throws  YJException;

    /**
     * 根据订单收支，设置金额正负
     * @param orderStatus
     * @param storagePrice
     * @return
     */
    Double getOrderPriceByOrderStatus(boolean orderStatus, Double storagePrice)throws YJException;

}
