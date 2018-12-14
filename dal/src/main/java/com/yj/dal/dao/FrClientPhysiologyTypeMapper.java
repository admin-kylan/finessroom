package com.yj.dal.dao;

import com.yj.dal.model.FrClientPhysiologyType;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 客户生理状况 类型 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-08
 */
public interface FrClientPhysiologyTypeMapper extends BaseMapper<FrClientPhysiologyType> {

    FrClientPhysiologyType findById(String id);
}
