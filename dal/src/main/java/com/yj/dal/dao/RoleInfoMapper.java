package com.yj.dal.dao;

import com.yj.dal.model.RoleInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-23
 */
public interface RoleInfoMapper extends BaseMapper<RoleInfo> {

    RoleInfo selectByfirstName(String firstName);

    List<Map<String,String>> getUpGradeAll(@Param("roleInfoId")String roleInfoId);

    String getFistName(String pid);
}
