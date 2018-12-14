package com.yj.dal.dao;

import com.yj.dal.model.FrClientLifeType;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 客户生活详情 类型 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-02
 */
public interface FrClientLifeTypeMapper extends BaseMapper<FrClientLifeType> {

    FrClientLifeType findById(String id);
}
