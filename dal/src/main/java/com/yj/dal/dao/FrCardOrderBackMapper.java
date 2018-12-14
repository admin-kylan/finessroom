package com.yj.dal.dao;

import com.yj.common.exception.YJException;
import com.yj.dal.model.FrCardOrderBack;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员卡退卡订单 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-19
 */
public interface FrCardOrderBackMapper extends BaseMapper<FrCardOrderBack> {

    List<Map<String, Object>> queryBlackCardList(FrCardOrderBack frCardOrderBack)throws YJException;

    Double queryBlackCardSum(FrCardOrderBack frCardOrderBack)throws YJException;

}
