package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.*;
import com.yj.dal.model.*;
import com.yj.dal.param.ClientDistributionParam;
import com.yj.service.service.*;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 * 客户员工关系表 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-31
 */
@Service
public class FrClientPersonnelRelateServiceImpl extends BaseServiceImpl<FrClientPersonnelRelateMapper, FrClientPersonnelRelate> implements IFrClientPersonnelRelateService {
    @Autowired
    IPersonnelInfoService personnelInfoService;

    @Autowired
    IRoleInfoService roleInfoService;

    @Autowired
    IFrIndustryService frIndustryService;

    @Autowired
    RoleInfoMapper roleInfoMapper;

    @Autowired
    IFrClientLatencePersonalService frClientLatencePersonalService;

    @Autowired
    IFrClientService frClientService;

    @Autowired
    IFrClientPersonalService frClientPersonalService;

    @Autowired
    IFrClientPersonnelRelateService frClientPersonnelRelateService;

    @Autowired
    PersonlRoleMapper personlRoleMapper;

    /**
     * 添加认领人
     * clientType 1为潜在 2为现有
     *
     * @param cids
     * @return
     * @throws YJException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult claiming(String[] cids, String clientType) throws YJException {
        //校验参数
        if (cids.length <= 0) {
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取当前登录用户的id
        String pid = CookieUtils.getCookieValue(req, "id", true);
        String shopid = CookieUtils.getCookieValue(req, "shopid", true);
        String name = CookieUtils.getCookieValue(req, "name", true);
        String code = CookieUtils.getCookieValue(req, "code", true);
        for (String cid : cids) {
            List<FrClientPersonnelRelate> frClientPersonnelRelateList = selectList(
                    new EntityWrapper<FrClientPersonnelRelate>().where("client_id={0}", cid));
            //未关联过人员
            if (frClientPersonnelRelateList.size() <= 0) {
                savePersonnelRelate(pid, shopid, name, code, cid);
            } else {
                int temp = 0;
                for (int i = 0; i < frClientPersonnelRelateList.size(); i++) {
                    //关联过认领人
                    if (frClientPersonnelRelateList.get(i).getType() == 1) {
                        //查询当前操作人角色
                        String fistName = roleInfoMapper.getFistName(pid);
                        //查询该角色默认保护天数
                        FrIndustry frIndustry = frIndustryService.selectOne(
                                new EntityWrapper<FrIndustry>().setSqlSelect("id", "days").where("role={0}", fistName).and("is_using=1")
                                        .and("customer_type=1")
                        );
                        //潜在客户
                        if (clientType == "1") {
                            FrClientLatencePersonal frClientLatencePersonal = frClientLatencePersonalService.selectOne(
                                    new EntityWrapper<FrClientLatencePersonal>().where("client_id={0}", frClientPersonnelRelateList.get(i).getClientId()));
                            if (frClientLatencePersonal != null) {
                                //重置默认保护天数
                                frClientLatencePersonal.setProtectDay(frIndustry.getDays());
                                frClientLatencePersonalService.updateById(frClientLatencePersonal);
                            }
                            //现有客户
                        } else if (clientType == "2") {
                            FrClientPersonal frClientPersonal = frClientPersonalService.selectOne(
                                    new EntityWrapper<FrClientPersonal>().where("client_id={0}", frClientPersonnelRelateList.get(i).getClientId()));
                            if (frClientPersonal != null) {
                                //重置默认保护天数
                                frClientPersonal.setProtectDay(frIndustry.getDays());
                                frClientPersonalService.updateById(frClientPersonal);
                            }
                        }
                        FrClient frClient = frClientService.selectOne(
                                new EntityWrapper<FrClient>().where("id={0}", frClientPersonnelRelateList.get(i).getClientId())
                        );
                        if (frClient != null) {
                            frClient.setProtectDay(frIndustry.getDays());
                            frClientService.updateById(frClient);
                        }
                    } else if (frClientPersonnelRelateList.get(i).getType() != 1) {
                        temp++;
                    }
                    //全部关联教练,未关联认领人
                    if (temp == frClientPersonnelRelateList.size()) {
                        savePersonnelRelate(pid, shopid, name, code, cid);
                    }
                }
            }

        }

        return JsonResult.success();
    }

    /**
     * 保存认领人关系
     *
     * @param pid
     * @param shopid
     * @param name
     * @param code
     * @param cid
     */
    private void savePersonnelRelate(String pid, String shopid, String name, String code, String cid) {
        FrClientPersonnelRelate frClientPersonnelRelate = new FrClientPersonnelRelate();
        frClientPersonnelRelate.setId(UUIDUtils.generateGUID());
        frClientPersonnelRelate.setShopId(shopid);
        frClientPersonnelRelate.setPersonalId(pid);
        frClientPersonnelRelate.setType(1);
        frClientPersonnelRelate.setCreateTime(new Date());
        frClientPersonnelRelate.setCustomerCode(code);
        frClientPersonnelRelate.setCreateUserName(name);
        frClientPersonnelRelate.setClientId(cid);
        frClientPersonnelRelate.setCreateUserId(pid);
        frClientPersonnelRelate.setUserType(0);
        baseMapper.insert(frClientPersonnelRelate);
    }

    /**
     * 根据客户id获得教练人员
     *
     * @param cid
     * @return
     * @throws YJException
     */
    @Override
    public List<Map<String, String>> getService(String cid, String type) throws YJException {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取当前登录用户的id
        String code = CookieUtils.getCookieValue(req, "code", true);
        //校验参数
        if (cid == null) {
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        List<FrClientPersonnelRelate> relates = baseMapper.selectList(
                new EntityWrapper<FrClientPersonnelRelate>().where("client_id={0} and user_type=0", cid)
                        .and("type!=1")
        );
        List<RoleInfo> roleInfos = new ArrayList<>();
        if (type == "3") {
            //教练
            roleInfos = roleInfoMapper.selectList(
                    new EntityWrapper<RoleInfo>().setSqlSelect("ID", "FirstName").where("CustomerCode={0}", code).where("ISTeach={0}", 1)
            );
        } else {
            roleInfos = roleInfoMapper.selectList(
                    new EntityWrapper<RoleInfo>().setSqlSelect("ID", "FirstName").where("CustomerCode={0}", code).where("UserType={0}", type)
            );
        }

        List<Map<String, String>> list = new ArrayList<>();
        if (roleInfos.size() > 0) {
            for (FrClientPersonnelRelate relate : relates) {
                for (RoleInfo roleInfo : roleInfos) {
                    Map map = new HashMap();
                    if (relate.getRoleId().equals(roleInfo.getId())) {
                        PersonnelInfo relName = personnelInfoService.selectOne(
                                new EntityWrapper<PersonnelInfo>().setSqlSelect("RelName")
                                        .where("ID={0}", relate.getPersonalId()).where("Status={0}", 0)
                        );
                        map.put("key", roleInfo.getFirstName());
                        map.put("val", relName.getRelName());
                        list.add(map);
                    }
                }

            }
        }
        return list;
    }

    /**
     * 分配
     *
     * @param params
     * @return
     * @throws YJException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult clientDistribution(ClientDistributionParam params) throws YJException {
        //校验参数
        if (params.getCids().length <= 0 || params.getPersonalId() == null || params.getRoleId() == null) {
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取当前登录用户的id
        String code = CookieUtils.getCookieValue(req, "code", true);
        f1:
        for (String cid : params.getCids()) {
            List<FrClientPersonnelRelate> relates = frClientPersonnelRelateService.selectList(
                    new EntityWrapper<FrClientPersonnelRelate>().where("client_id={0}", cid).where("CustomerCode={0}", code)
            );
            if (relates.size() > 0) {
                for (int i = 0; i < relates.size(); i++) {
                    if (params.getRoleId().equals(relates.get(i).getRoleId())) {
                        FrClientPersonnelRelate relate = relates.get(i);
                        relate.setPersonalId(params.getPersonalId().split(",")[0]);
                        relate.setShopId(params.getPersonalId().split(",")[1]);
                        baseMapper.updateById(relate);
                        continue f1;
                    }

                }
            }

            FrClientPersonnelRelate relate = new FrClientPersonnelRelate();
            relate.setId(UUIDUtils.generateGUID());
            relate.setPersonalId(params.getPersonalId().split(",")[0]);
            relate.setShopId(params.getPersonalId().split(",")[1]);
            relate.setRoleId(params.getRoleId());
            relate.setClientId(cid);
            relate.setUserType(0);
            baseMapper.insert(relate);
        }
        return JsonResult.success();
    }

}
