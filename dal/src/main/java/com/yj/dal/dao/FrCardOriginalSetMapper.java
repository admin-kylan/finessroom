package com.yj.dal.dao;

import com.yj.dal.model.FrCardOriginalSet;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 会员卡购买前，后台设置保存 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-10
 */
public interface FrCardOriginalSetMapper extends BaseMapper<FrCardOriginalSet> {

    Map<String,Object> querySelectOneFrCard(FrCardOriginalSet frCardOriginalSet);

}
