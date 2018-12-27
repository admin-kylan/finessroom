package com.yj.service.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yj.common.result.JsonResult;
import com.yj.dal.dao.*;
import com.yj.dal.model.*;
import com.yj.service.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    private IFrAgreementService iFrAgreementService;

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
    private ProjectOrderMapper projectOrder;

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
        ProjectOrder projectOrder = JSONObject.toJavaObject((JSON) map.get("projctOrder"), ProjectOrder.class);
        SysConsumeOrder sysConsumeOrder = JSONObject.toJavaObject((JSON) map.get("sysConsumeOrder"), SysConsumeOrder.class);
        AddProject addProject = JSONObject.toJavaObject((JSON) map.get("addProject"), AddProject.class);
        FrCardAgreement frCardAgreement = JSONObject.toJavaObject((JSON) map.get("cardAgreement"), FrCardAgreement.class);

        JSONArray cardOrderPayModeData = (JSONArray) map.get("cardOrderPayModeData");
        JSONArray cardOrderDetailList = (JSONArray) map.get("cardOrderDetailList");
        JSONArray cardOrderPriceDetailList = (JSONArray) map.get("cardOrderPriceDetailList");
        JSONArray cardOrderAllotSetList = (JSONArray) map.get("cardOrderAllotSetList");
        JSONArray clientPersonnelRelate = (JSONArray) map.get("clientPersonnelRelate");

        //保存数据库

        for(Object object: cardOrderPayModeData){
            FrCardOrderPayMode frCardOrderPayMode = (FrCardOrderPayMode) object;

        }
        for(Object object: cardOrderDetailList){
            FrCardOrderDatail frCardOrderDatail = (FrCardOrderDatail) object;
        }

        for(Object object: cardOrderPriceDetailList){
            FrCardOrderPriceDatail frCardOrderPriceDatail = (FrCardOrderPriceDatail) object;
        }
        for(Object object: cardOrderAllotSetList){
            FrCardOrderAllotSet frCardOrderAllotSet = (FrCardOrderAllotSet) object;
        }
        for(Object object: clientPersonnelRelate){
            FrClientPersonnelRelate relate = (FrClientPersonnelRelate) object;
        }



    }
}
