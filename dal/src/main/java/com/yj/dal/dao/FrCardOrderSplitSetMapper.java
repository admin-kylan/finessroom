package com.yj.dal.dao;

import com.yj.dal.model.FrCardOrderSplitSet;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 所有会员卡分期付款订单，相关分期付款设置 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
public interface FrCardOrderSplitSetMapper extends BaseMapper<FrCardOrderSplitSet> {

    public List<Map<String,Object>> queryFrCardSplitSet(FrCardOrderSplitSet frCardOrderSplitSet);
}
