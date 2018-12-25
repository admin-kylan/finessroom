package com.yj.dal.dao;

import com.yj.dal.model.FrAction;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-14
 */
public interface FrActionMapper extends BaseMapper<FrAction> {

    Map<String,Object> getAction(String cid);
}
