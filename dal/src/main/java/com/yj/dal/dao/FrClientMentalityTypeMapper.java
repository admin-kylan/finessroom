package com.yj.dal.dao;

import com.yj.dal.model.FrClientMentalityType;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 客户心理情况 类型 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-08
 */
public interface FrClientMentalityTypeMapper extends BaseMapper<FrClientMentalityType> {

    FrClientMentalityType findById(String id);
}
