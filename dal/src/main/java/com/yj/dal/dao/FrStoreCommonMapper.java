package com.yj.dal.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yj.dal.model.FrStoreCommon;

/**
 * <p>
 * 门店通用设置表 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-12
 */
public interface FrStoreCommonMapper extends BaseMapper<FrStoreCommon> {

    FrStoreCommon  selectTopOne(FrStoreCommon frStoreCommon);
}
