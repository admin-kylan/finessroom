package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.dal.model.PersonlRole;
import com.yj.dal.model.PersonnelInfo;
import com.yj.service.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 人员担任角色表 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-23
 */
public interface IPersonlRoleService extends BaseService<PersonlRole> {

    List<PersonnelInfo> getPersonlByRole(String rid)throws YJException;

    Map<String,String> getParentIdByPersonnelInfoId(String pifId,String code);

    List<PersonnelInfo> getCoach();
}
