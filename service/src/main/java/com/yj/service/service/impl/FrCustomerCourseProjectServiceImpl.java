package com.yj.service.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.*;
import com.yj.dal.model.*;
import com.yj.service.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * by Kylan
 *
 * @author YongL
 * @date 2018/12/18
 */
@Service
public class FrCustomerCourseProjectServiceImpl {

    @Autowired
    private FrCustomerCourseProjectMapper frCustomerCourseProjectMapper;


    @Resource
    private IFrCardOrderPayModeService iFrCardOrderPayModeService;
    @Resource
    private FrCardAgreementMapper frCardAgreementMapper;

    @Resource
    private IFrCardOrderAllotSetService iFrCardOrderAllotSetService;
    @Resource
    private IFrCardOrderDatailService iFrCardOrderDatailService;
    @Resource
    private IFrCardOrderPriceDatailService iFrCardOrderPriceDatailService;
    @Resource
    private ProjectOrderMapper projectOrderMapper;
    @Resource
    private SysConsumeOrderMapper sysConsumeOrderMapper;
    @Resource
    private AddProjectMapper addProjectMapper;
    @Resource
    private FrClientPersonnelRelateMapper frClientPersonnelRelateMapper;

    /**
     * 根据场馆ID查询 并且 userType 是动态的，选择教练还是助教
     *
     * @param userType
     * @param
     * @param code
     * @return
     */
    public List<PersonnelInfo> getTechnicianBySdaduimId(String userType, String businessTypeId, String code) {
        return frCustomerCourseProjectMapper.getTechnicianBySdaduimId(userType, businessTypeId, code);
    }


    public void addSaveCustomer(Map map) {
        //json 转成实体
        ProjectOrder projectOrder = JSONObject.parseObject((String) map.get("projectOrder"), ProjectOrder.class);
        SysConsumeOrder sysConsumeOrder = JSONObject.parseObject((String) map.get("sysConsumeOrder"), SysConsumeOrder.class);
        AddProject addProject = JSONObject.parseObject((String) map.get("addProject"), AddProject.class);
        FrCardAgreement frCardAgreement = JSONObject.parseObject((String) map.get("cardAgreement"), FrCardAgreement.class);

        //数组
        JSONArray cardOrderPayModeData = JSONArray.parseArray((String) map.get("cardOrderPayModeData")) ;
        JSONArray cardOrderDetailList = JSONArray.parseArray((String) map.get("cardOrderDetailList"));
        JSONArray cardOrderPriceDetailList = JSONArray.parseArray((String) map.get("cardOrderPriceDetailList"));
        JSONArray cardOrderAllotSetList = JSONArray.parseArray((String) map.get("cardOrderAllotSetList"));
        JSONArray clientPersonnelRelate = JSONArray.parseArray((String) map.get("clientPersonnelRelate"));
        Date now = new Date();
        String createUserId = (String) map.get("createUserId");
        String createUserName = (String) map.get("createUserName");
        String orderId = UUIDUtils.generateGUID();
        //保存数据库 order
        projectOrder.setId(orderId);
        projectOrder.setCreateTime(now);
        projectOrder.setCreateUserId(createUserId);
        projectOrder.setCreateUserName(createUserName);
        projectOrderMapper.insert(projectOrder);

        //sysConsumeOrder
        sysConsumeOrder.setId(UUIDUtils.generateGUID());
        sysConsumeOrder.setCreateId(createUserId);
        sysConsumeOrder.setCreateName(createUserName);
        sysConsumeOrder.setCreateTime(now);
        sysConsumeOrderMapper.insert(sysConsumeOrder);

        //addProject
        addProject.setId(UUIDUtils.generateGUID());
        addProject.setProjectId(orderId);
        addProjectMapper.insert(addProject);

        //cardAgreement
        frCardAgreement.setId(UUIDUtils.generateGUID());
        frCardAgreement.setProjectId(orderId);
        frCardAgreementMapper.insert(frCardAgreement);

        for(Object object: cardOrderPayModeData){
            FrCardOrderPayMode frCardOrderPayMode = JSONObject.parseObject(JSON.toJSONString(object), FrCardOrderPayMode.class) ;
            frCardOrderPayMode.setId(UUIDUtils.generateGUID());
            frCardOrderPayMode.setOrderId(orderId);
            iFrCardOrderPayModeService.insert(frCardOrderPayMode);
        }
        for(Object object: cardOrderDetailList){
            FrCardOrderDatail frCardOrderDatail = JSONObject.parseObject(JSON.toJSONString(object), FrCardOrderDatail.class) ;
            frCardOrderDatail.setId(UUIDUtils.generateGUID());
            frCardOrderDatail.setCreateTime(now);
            frCardOrderDatail.setCreateUserId(createUserId);
            frCardOrderDatail.setCreateUserName(createUserName);
            frCardOrderDatail.setOrderId(orderId);
            iFrCardOrderDatailService.insert(frCardOrderDatail);
        }

        for(Object object: cardOrderPriceDetailList){
            FrCardOrderPriceDatail frCardOrderPriceDatail = JSONObject.parseObject(JSON.toJSONString(object), FrCardOrderPriceDatail.class) ;
            frCardOrderPriceDatail.setOrderId(orderId);
            frCardOrderPriceDatail.setCreateUserId(createUserId);
            frCardOrderPriceDatail.setCreateUserName(createUserName);
            frCardOrderPriceDatail.setId(UUIDUtils.generateGUID());
            frCardOrderPriceDatail.setCreateTime(now);
            iFrCardOrderPriceDatailService.insert(frCardOrderPriceDatail);
        }
        for(Object object: cardOrderAllotSetList){
            FrCardOrderAllotSet frCardOrderAllotSet = JSONObject.parseObject(JSON.toJSONString(object), FrCardOrderAllotSet.class) ;
            frCardOrderAllotSet.setOrderId(orderId);
            frCardOrderAllotSet.setCreateTime(now);
            frCardOrderAllotSet.setCreateUserId(createUserId);
            frCardOrderAllotSet.setCreateUserName(createUserName);
            frCardOrderAllotSet.setId(UUIDUtils.generateGUID());
            iFrCardOrderAllotSetService.insert(frCardOrderAllotSet);
        }
        for(Object object: clientPersonnelRelate){
            FrClientPersonnelRelate frClientPersonnelRelate = JSONObject.parseObject(JSON.toJSONString(object), FrClientPersonnelRelate.class) ;
            frClientPersonnelRelate.setCreateTime(now);
            frClientPersonnelRelate.setId(UUIDUtils.generateGUID());
            frClientPersonnelRelate.setCreateUserName(createUserName);
            frClientPersonnelRelate.setCreateUserId(createUserId);
            frClientPersonnelRelateMapper.insert(frClientPersonnelRelate);

        }



    }
}
