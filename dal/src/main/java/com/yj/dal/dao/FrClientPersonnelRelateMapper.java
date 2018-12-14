package com.yj.dal.dao;

import com.yj.dal.model.FrClientPersonnelRelate;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 客户员工关系表 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-31
 */
public interface FrClientPersonnelRelateMapper extends BaseMapper<FrClientPersonnelRelate> {

    void deleteByClient(String id);
}
