package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.dal.model.RoleInfo;
import com.yj.service.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-23
 */
public interface IRoleInfoService extends BaseService<RoleInfo> {
        List<RoleInfo> findAll()throws YJException;

    RoleInfo getRoleByFirstName(String firstName)throws YJException;

    /**
     * 获取指定角色的所有上级主管
     * @return
     */
    List<Map<String,String>> getUpGradeAll(String roleInfoId);


}
