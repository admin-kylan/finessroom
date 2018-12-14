package com.yj.dal.dao;

import com.yj.dal.model.FrCardOrderDatail;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 订单交易明细 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-24
 */
public interface FrCardOrderDatailMapper extends BaseMapper<FrCardOrderDatail> {

    /**
     * 统计储值金额   根据（a.order_price）求和统计
     * @param frCardOrderDatail
     * @return
     */
    Double querySumOrderPrice(FrCardOrderDatail frCardOrderDatail);

    /**
     * 统计储值金额   根据 (a.order_amt） 统计
     * @param frCardOrderDatail
     * @return
     */
    Double queryAmtOrderPrice(FrCardOrderDatail frCardOrderDatail);

    FrCardOrderDatail queryTopOne(FrCardOrderDatail frCardOrderDatail);

    Map<String,Double> getAmtByCardId(FrCardOrderDatail frCardOrderDatail);
}
