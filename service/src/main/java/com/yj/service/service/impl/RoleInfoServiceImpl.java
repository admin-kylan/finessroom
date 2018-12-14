package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.HttpServletUtils;
import com.yj.common.util.StringUtils;
import com.yj.dal.model.RoleInfo;
import com.yj.dal.dao.RoleInfoMapper;
import com.yj.service.service.IRoleInfoService;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-23
 */
@Service
public class RoleInfoServiceImpl extends BaseServiceImpl<RoleInfoMapper, RoleInfo> implements IRoleInfoService {

    @Autowired
    RoleInfoMapper roleInfoMapper;

    @Override
    public List<RoleInfo> findAll() throws YJException {
        //获取客户代码
        String customerCode = HttpServletUtils.getCustmerCode();
        if (org.apache.commons.lang3.StringUtils.isEmpty(customerCode)) {
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        List<RoleInfo> roleInfos = roleInfoMapper.selectList(
                new EntityWrapper<RoleInfo>().where("ISTeach={0}", 1).where("CustomerCode={0}", customerCode));
        return roleInfos;
    }

    @Override
    public RoleInfo getRoleByFirstName(String firstName) throws YJException {
        //获取客户代码
        String customerCode = HttpServletUtils.getCustmerCode();
        if (org.apache.commons.lang3.StringUtils.isEmpty(customerCode)) {
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        RoleInfo roleInfo = selectOne(
                new EntityWrapper<RoleInfo>().where("FirstName={0}", firstName).where("CustomerCode={0}", customerCode));
        return roleInfo;
    }

    @Override
    public List<Map<String, String>> getUpGradeAll(String roleInfoId) {
        List<Map<String, String>> list = new ArrayList<>();
        if (!StringUtils.isEmpty(roleInfoId)) {
            list = roleInfoMapper.getUpGradeAll(roleInfoId);
        }
        return list;
    }



}
