package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.util.HttpServletUtils;
import com.yj.dal.dao.PersonnelInfoMapper;
import com.yj.dal.dao.RoleInfoMapper;
import com.yj.dal.model.FrClientPersonnelRelate;
import com.yj.dal.model.PersonlRole;
import com.yj.dal.dao.PersonlRoleMapper;
import com.yj.dal.model.PersonnelInfo;
import com.yj.dal.model.RoleInfo;
import com.yj.service.service.IPersonlRoleService;
import com.yj.service.base.BaseServiceImpl;
import com.yj.service.service.IPersonnelInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 人员担任角色表 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-23
 */
@Service
public class PersonlRoleServiceImpl extends BaseServiceImpl<PersonlRoleMapper, PersonlRole> implements IPersonlRoleService {

    @Autowired
    PersonlRoleMapper personlRoleMapper;

    @Autowired
    PersonnelInfoMapper personnelInfoMapper;
    @Autowired
    IPersonnelInfoService iPersonnelInfoService;

    @Autowired
    RoleInfoMapper roleInfoMapper;

    @Override
    public List<PersonnelInfo> getPersonlByRole(String rid) throws YJException {

        //校验参数
        if (rid == null) {
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        //获取客户代码
        String customerCode = HttpServletUtils.getCustmerCode();
        if (StringUtils.isEmpty(customerCode)) {
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }

        List<PersonlRole> personlRoles =
                personlRoleMapper.selectList(
                        new EntityWrapper<PersonlRole>().where("RoleInfoId={0}", rid).where("CustomerCode={0}", customerCode));
        List<PersonnelInfo> personnelInfos = new ArrayList<>();
        for (PersonlRole personlRole : personlRoles) {
            PersonnelInfo personnelInfo = personnelInfoMapper.selectById(personlRole.getPersonnelInfoId());
            personnelInfos.add(personnelInfo);
        }
        return personnelInfos;
    }

    @Override
    public Map<String, String> getParentIdByPersonnelInfoId(String pifId, String code) {
        Map<String, String> map = personlRoleMapper.getParentIdByPersonnelInfoId(pifId, code);
        return map;
    }

    @Override
    public List<PersonnelInfo> getCoach() {
        List<RoleInfo> roleInfos = roleInfoMapper.selectList(
                new EntityWrapper<RoleInfo>().where("ISTeach={0}", 1));
        List<PersonnelInfo> personnelInfos = new ArrayList<>();
        if (roleInfos.size() > 0) {
            Set<String> set = new HashSet<String>();

            for (RoleInfo roleInfo : roleInfos) {
                List<String> list = personlRoleMapper.getParentId(roleInfo.getId());
                set.addAll(list);
            }
            List<String> ParentIds = new ArrayList<String>(set);
            for (String parentId : ParentIds) {
                PersonnelInfo personnelInfo = iPersonnelInfoService.selectOne(
                        new EntityWrapper<PersonnelInfo>().setSqlSelect("Mobile", "RelName", "ID").where("ID={0}", parentId)
                );
                personnelInfos.add(personnelInfo);
            }
        }
        return personnelInfos;
    }
}
