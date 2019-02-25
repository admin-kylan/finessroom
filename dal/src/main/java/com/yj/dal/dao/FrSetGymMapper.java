package com.yj.dal.dao;

import com.yj.dal.model.FrSetGym;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 健身馆设置保存 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-28
 */
public interface FrSetGymMapper extends BaseMapper<FrSetGym> {

    Map<String, Object> getTime(String modelId);

    List<Map<String, Object>> getCity(String customerCode);
}
