package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.dal.dao.PersonlRoleMapper;
import com.yj.dal.dao.RoleInfoMapper;
import com.yj.dal.model.PersonnelInfo;
import com.yj.dal.dao.PersonnelInfoMapper;
import com.yj.dal.model.RoleInfo;
import com.yj.service.service.IPersonlRoleService;
import com.yj.service.service.IPersonnelInfoService;
import com.yj.service.base.BaseServiceImpl;
import com.yj.service.service.IRoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 人员信息表 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-09
 */
@Service
public class PersonnelInfoServiceImpl extends BaseServiceImpl<PersonnelInfoMapper, PersonnelInfo> implements IPersonnelInfoService {

    @Autowired
    IRoleInfoService roleInfoService;
    @Autowired
    PersonlRoleMapper personlRoleMapper;

    /**
     * 获取销售人员列表
     *
     * @return
     */
    @Override
    public JsonResult getMarketUserList(String shopId, String code) {
        List<PersonnelInfo> list = baseMapper.getMarketUserList(shopId, code);
        return JsonResult.success(list);
    }

    /**
     * 查询角色为服务会稽的人员
     *
     * @return
     * @throws YJException
     */
    @Override
    public List<PersonnelInfo> getServicePersonnel(Integer userType) throws YJException {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String customerCode = CookieUtils.getCookieValue(req, "code", true);
        List<Object> rids = roleInfoService.selectObjs(new EntityWrapper<RoleInfo>().setSqlSelect("id").where("UserType={0}", userType).where("CustomerCode={0}", customerCode));
        List<PersonnelInfo> personnelInfos = new ArrayList<>();
        if (rids.size() > 0) {
            Set<String> set = new HashSet<String>();
            for (Object id : rids) {
                List<String> list = personlRoleMapper.getParentId((String) id);
                set.addAll(list);
            }
            List<String> ParentIds = new ArrayList<String>(set);
            for (String parentId : ParentIds) {
                PersonnelInfo personnelInfo = selectOne(
                        new EntityWrapper<PersonnelInfo>().setSqlSelect("Mobile", "RelName", "ID").where("ID={0}", parentId));
                personnelInfos.add(personnelInfo);
            }
        }
        return personnelInfos;
    }

    @Override
    public List<PersonnelInfo> getUnallocatedPersonnel(String rid) throws YJException {
        List<PersonnelInfo> personnelInfos = new ArrayList<>();
        Set<String> set = new HashSet<String>();
        List<String> list = personlRoleMapper.getParentId(rid);
        set.addAll(list);
        List<String> ParentIds = new ArrayList<String>(set);
        for (String parentId : ParentIds) {
            PersonnelInfo personnelInfo = selectOne(
                    new EntityWrapper<PersonnelInfo>().setSqlSelect("ShopId", "RelName", "ID").where("ID={0}", parentId));
            personnelInfos.add(personnelInfo);
        }
        return personnelInfos;
    }

}