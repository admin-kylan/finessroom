package com.yj.dal.dao;

import com.yj.dal.model.FrClientMotionType;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 客户运动档案 类型 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-02
 */
public interface FrClientMotionTypeMapper extends BaseMapper<FrClientMotionType> {

    FrClientMotionType findById(String id);
}
