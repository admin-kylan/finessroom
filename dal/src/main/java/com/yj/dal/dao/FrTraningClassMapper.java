package com.yj.dal.dao;

import com.yj.dal.dto.FrTraningClassListDTO;
import com.yj.dal.model.FrTraningClass;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-15
 */
public interface FrTraningClassMapper extends BaseMapper<FrTraningClass> {


    List<FrTraningClassListDTO> frTraningClassList(String traningSeriesId);

    List<FrTraningClassListDTO> frTraningClassList2(String traningSeriesId);

    List<Map<String, Object>> getPlan(@Param("id") String id, @Param("type") Integer type);

    Map<String, Object> getPlanById(@Param("id") String id);
}
