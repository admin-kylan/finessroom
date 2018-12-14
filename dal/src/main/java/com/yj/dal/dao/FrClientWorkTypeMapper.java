package com.yj.dal.dao;

import com.yj.dal.model.FrClientWorkType;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 客户工作行业 类型 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-08
 */
public interface FrClientWorkTypeMapper extends BaseMapper<FrClientWorkType> {

    FrClientWorkType findById(String id);
}
