package com.yj.dal.dao;

import com.yj.dal.model.FrEducationClientInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程用户表，用户预约课程 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2019-01-05
 */
public interface FrEducationClientInfoMapper extends BaseMapper<FrEducationClientInfo> {


    List<Map<String, Object>> findMemberReserveStatusList(@Param("eduId") String eduId, @Param("reserveStatus") String reserveStatus, @Param("searchInput") String searchInput);
}
