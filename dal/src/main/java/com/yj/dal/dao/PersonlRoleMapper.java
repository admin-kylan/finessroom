package com.yj.dal.dao;

import com.yj.dal.model.PersonlRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 人员担任角色表 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-23
 */
public interface PersonlRoleMapper extends BaseMapper<PersonlRole> {

    Map<String, String> getParentIdByPersonnelInfoId(@Param("pifId")String pifId, @Param("code")String code);

    List<String> getParentId(String id);
}
