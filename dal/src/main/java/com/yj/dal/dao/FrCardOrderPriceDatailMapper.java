package com.yj.dal.dao;

import com.yj.dal.model.FrCardOrderPriceDatail;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 资金交易明细 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-24
 */
public interface FrCardOrderPriceDatailMapper extends BaseMapper<FrCardOrderPriceDatail> {

    FrCardOrderPriceDatail queryTopOne(FrCardOrderPriceDatail frCardOrderPriceDatail);
}
