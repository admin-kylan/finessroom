package com.yj.service.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.*;
import com.yj.dal.model.*;
import com.yj.service.service.IFrCardOrderAllotSetService;
import com.yj.service.service.IFrCardOrderDatailService;
import com.yj.service.service.IFrCardOrderPayModeService;
import com.yj.service.service.IFrCardOrderPriceDatailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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

    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private SdaduimMapper sdaduimMapper;

//    @Autowired
//    private AddConsumeProjectMapper addConsumeProjectMapper;

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
    @Autowired
    private AddProjectMapper addProjectMapper;
    @Resource
    private FrClientPersonnelRelateMapper frClientPersonnelRelateMapper;
    @Resource
    private PersonnelInfoMapper personnelInfoMapper;

    @Autowired
    private AddProjectConsumeMapper addProjectConsumeMapper;

    @Resource
    private FrProjectStartRecordMapper frProjectStartRecordMapper;
    @Resource
    private FrProjectExtensionRecordMapper frProjectExtensionRecordMapper;
    @Resource
    private FrProjectRemnantRecordMapper frProjectRemnantRecordMapper;
    @Resource
    private ReturnAddProjectMapper returnAddProjectMapper;
    @Resource
    private TurnProjectMapper turnProjectMapper;
    @Resource
    private FrClientMapper frClientMapper;

    @Autowired
    private FrGroupCourseMapper frGroupCourseMapper;
    @Autowired
    private FrGroupCourceRelationMapper frGroupCourceRelationMapper;

    @Autowired
    private FrPrivateCourceMapper frPrivateCourceMapper;
    @Autowired
    private FrPrivateCourceRelationMapper frPrivateCourceRelationMapper;


    @Autowired
    private FrPrivatePackageMapper frPrivatePackageMapper;
    @Autowired
    private FrPrivatePackageRelationMapper frPrivatePackageRelationMapper;
    @Autowired
    private FrCardOrderDatailMapper frCardOrderDatailMapper;

    @Autowired
    private FrConsumeMoneyOrderServiceImpl frConsumeMoneyOrderService;

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


    /**
     * 保存
     * @param map
     */
    public void addSaveCustomer(Map map) {
        //json 转成实体
        ProjectOrder projectOrder = JSONObject.parseObject((String) map.get("projectOrder"), ProjectOrder.class);
        SysConsumeOrder sysConsumeOrder = JSONObject.parseObject((String) map.get("sysConsumeOrder"), SysConsumeOrder.class);
        AddProject addProject = JSONObject.parseObject((String) map.get("addProject"), AddProject.class);
        FrCardAgreement frCardAgreement = JSONObject.parseObject((String) map.get("cardAgreement"), FrCardAgreement.class);

        //数组
        JSONArray cardOrderPayModeData = JSONArray.parseArray((String) map.get("cardOrderPayModeData")) ;
        JSONArray cardOrderDetailList = JSONArray.parseArray((String) map.get("cardOrderDetailList"));
      //  JSONArray cardOrderPriceDetailList = JSONArray.parseArray((String) map.get("cardOrderPriceDetailList"));
        JSONArray cardOrderAllotSetList = JSONArray.parseArray((String) map.get("cardOrderAllotSetList"));
        JSONArray clientPersonnelRelate = JSONArray.parseArray((String) map.get("clientPersonnelRelate"));
       // JSONObject cardOrderDetail = JSONObject.parseObject((String) map.get("cardOrderDetail"));
        Date now = new Date();

        String createUserId = (String) map.get("createUserId");
        String createUserName = (String) map.get("createUserName");
        String orderId = UUIDUtils.generateGUID();
        //addProject
        addProject.setId(orderId);
      //  addProject.setProjectId(orderId);
        addProjectMapper.insert(addProject);


        //保存数据库 order
        projectOrder.setId(UUIDUtils.generateGUID());
        projectOrder.setCreateTime(now);
        projectOrder.setCreateUserId(createUserId);
        projectOrder.setCreateUserName(createUserName);
        projectOrder.setObjectId(orderId);
        projectOrderMapper.insert(projectOrder);

        //sysConsumeOrder
        sysConsumeOrder.setId(UUIDUtils.generateGUID());
        sysConsumeOrder.setCreateId(createUserId);
        sysConsumeOrder.setCreateName(createUserName);
        sysConsumeOrder.setCreateTime(now);
        sysConsumeOrderMapper.insert(sysConsumeOrder);



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

        //资金明细表
        for(Object object: cardOrderDetailList){
            JSONObject jsonObject = (JSONObject) object;
            FrCardOrderPriceDatail frCardOrderPriceDatail = JSONObject.parseObject(JSON.toJSONString(jsonObject.getJSONObject("orderdetailPrice")), FrCardOrderPriceDatail.class) ;
            FrCardOrderDatail frCardOrderDatail = JSONObject.parseObject(JSON.toJSONString(jsonObject.getJSONObject("orderDetail")), FrCardOrderDatail.class) ;
            String priceID = UUIDUtils.generateGUID();
            frCardOrderPriceDatail.setOrderId(orderId);
            frCardOrderPriceDatail.setCreateUserId(createUserId);
            frCardOrderPriceDatail.setCreateUserName(createUserName);
            frCardOrderPriceDatail.setId(priceID);
            frCardOrderPriceDatail.setCreateTime(now);
            iFrCardOrderPriceDatailService.insert(frCardOrderPriceDatail);


            frCardOrderDatail.setId(UUIDUtils.generateGUID());
            frCardOrderDatail.setCreateTime(now);
            frCardOrderDatail.setCreateUserId(createUserId);
            frCardOrderDatail.setCreateUserName(createUserName);
            frCardOrderDatail.setOrderId(orderId);
            frCardOrderDatail.setOrderPriceId(priceID);
            iFrCardOrderDatailService.insert(frCardOrderDatail);
        }

//        for(Object object: cardOrderPriceDetailList){
//            FrCardOrderPriceDatail frCardOrderPriceDatail = JSONObject.parseObject(JSON.toJSONString(object), FrCardOrderPriceDatail.class) ;
//            frCardOrderPriceDatail.setOrderId(orderId);
//            frCardOrderPriceDatail.setCreateUserId(createUserId);
//            frCardOrderPriceDatail.setCreateUserName(createUserName);
//            frCardOrderPriceDatail.setId(UUIDUtils.generateGUID());
//            frCardOrderPriceDatail.setCreateTime(now);
//            iFrCardOrderPriceDatailService.insert(frCardOrderPriceDatail);
//        }
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
            frClientPersonnelRelate.setOtherTableId(orderId);
            frClientPersonnelRelateMapper.insert(frClientPersonnelRelate);

        }

        //新增订单表
        this.saveOrderEntityByNewBuy(map, addProject, sysConsumeOrder);



    }

    /**
     * 新购项目
     * @param map
     * @param addProject
     * @param sysConsumeOrder
     */
    private void saveOrderEntityByNewBuy(Map map, AddProject addProject, SysConsumeOrder sysConsumeOrder){

        MoneyReport moneyReport = JSONObject.parseObject((String) map.get("moneyReport"), MoneyReport.class);


        String userTypeName = "新购项目";
        String tableName = "AddProject";
        Date date = new Date();
        moneyReport.setOrderNumber(frConsumeMoneyOrderService.getOrderIdByTime());
        moneyReport.setUseTypeName(userTypeName);
        moneyReport.setTableId(addProject.getId());
        moneyReport.setTableName(tableName);
        moneyReport.setMemberCardId(sysConsumeOrder.getMemberCardId());
        moneyReport.setCreateTime(date);
        moneyReport.setId(UUIDUtils.generateGUID());
        moneyReport.setAddprojectId(addProject.getId());
        frConsumeMoneyOrderService.saveMoneyReport(moneyReport);


    }

    /**
     * 项目
     * @param type
     * @param status
     * @param shopId
     * @param timeType
     * @param startDate
     * @param endDate
     * @param
     * @param code
     * @return
     */
    public List<Map<String, Object>> getProjectList(String type, String status, String shopId,
                              String timeType, String startDate, String endDate, String courseName, String code,String orderType, String cid){

        if(!StringUtils.isBlank(startDate)){
            startDate += " 00:00:00";
        }
        if(!StringUtils.isBlank(endDate)){
            endDate += " 23:59:59";
        }
        if(StringUtils.equals(orderType, "1")){
            return addProjectMapper.getProjectList(status, shopId, timeType, startDate, endDate, courseName, code, cid);
        }
//
        return addProjectMapper.getProjectList2(status, shopId, timeType, startDate, endDate, courseName, code, cid);

    }

    /**
     * 选择框获取的数据
     * @param shopId
     * @param cid
     * @param code
     * @return
     */
    public List<Map<String, Object>> getProjectListSelect(String shopId, String cid, String code){

        return addProjectMapper.getProjectListSelect(shopId, code, cid);
    }


     /**
     * 查询启用记录
     * @param shopId
     * @param cid
     * @param code
     * @return
     */
    public List<Map<String, Object>> findStartProjectRecord(String shopId, String cid, String code){

        return addProjectMapper.findStartProjectRecord(shopId, cid, code);
    }




    /**
     * 启用项目
     * @param map
     */
    public void starCustomer(Map<String, String> map) throws Exception {
        String orderId = map.get("orderId");
        String cid = map.get("cid");
        String name = map.get("name");
        String code = map.get("code");
        String operId = map.get("operId");
        String clientName = map.get("clientName");
        String courseName = map.get("courseName");
        Date now = new Date();
        AddProject addProject = addProjectMapper.selectById(orderId);;
        FrProjectStartRecord frProjectStartRecord = new FrProjectStartRecord();
        //查询
       // addProject.setProjectId(id);
       // addProject = addProjectMapper.selectOne(addProject);
        if(null == addProject){
            throw new Exception("启用出错，未找到启用数据");
        }
        frProjectStartRecord.setProjectOrderId(orderId);
        frProjectStartRecord = frProjectStartRecordMapper.selectOne(frProjectStartRecord);
        if(null == frProjectStartRecord){
            frProjectStartRecord = new FrProjectStartRecord();
        }
        frProjectStartRecord.setProjectOrderId(orderId);
        //记录表
        frProjectStartRecord.setId(UUIDUtils.generateGUID());
        frProjectStartRecord.setCustomerCode(code);
        frProjectStartRecord.setOperateDate(now);
        frProjectStartRecord.setOperatePersonId(operId);
        frProjectStartRecord.setOperatePersonName(name);
        frProjectStartRecord.setCreateUser(name);
        frProjectStartRecord.setCreateTime(now);
        frProjectStartRecord.setOldStartDate(addProject.getStartTime());
        frProjectStartRecord.setOldEndDate(addProject.getEndTime());
        frProjectStartRecord.setClientId(cid);
        frProjectStartRecord.setClientName(clientName);
        frProjectStartRecord.setCourseName(courseName);

        //计算时间差
        Date startTime = addProject.getStartTime();
        Date endTime = addProject.getEndTime();
        long time = startTime.getTime() - now.getTime();
        addProject.setStartTime(now);
        addProject.setEndTime(new Date(endTime.getTime() - time));
        frProjectStartRecord.setStartDate(now);
        frProjectStartRecord.setEndDate(addProject.getEndTime());
        //正常
        addProject.setState(0);
        //更新
        addProjectMapper.updateAllColumnById(addProject);
        //---- 保存
        frProjectStartRecordMapper.insert(frProjectStartRecord);

    }

    /**
     * 查询补余记录
     * @param shopId
     * @param cid
     * @param code
     * @return
     */
    public List<Map<String, Object>> findCustomerRemnantRecord(String shopId, String cid, String code){

        return addProjectMapper.findCustomerRemnantRecord(shopId, cid, code);
    }

    /**
     * 补余
     * @param map
     * @return
     */
    public ProjectOrder customerRemnant(Map<String, String> map){
        String orderId = map.get("orderId");
        String cid = map.get("cid");
        String operId = map.get("id");
        String name = map.get("name");
        String code = map.get("code");
        String clientName = map.get("clientName");
        String shopId = map.get("shopId");
        String sdadiumId = map.get("sdadiumId");
        String courseName = map.get("courseName");
        Date now = new Date();
        FrProjectRemnantRecord frProjectRemnantRecord = new FrProjectRemnantRecord();
        AddProject addProject = null;

        ProjectOrder projectOrder = new ProjectOrder();
        projectOrder.setObjectId(orderId);
        projectOrder = projectOrderMapper.selectOne(projectOrder);
        addProject = addProjectMapper.selectById(orderId);
        //修改成正常
        addProject.setState(4);
        //
        frProjectRemnantRecord.setProjectOrderId(orderId);
        //查询是否存在
        if(null != frProjectRemnantRecordMapper.selectOne(frProjectRemnantRecord)){
            return projectOrder;
        }
        //记录表
        frProjectRemnantRecord.setId(UUIDUtils.generateGUID());
        frProjectRemnantRecord.setCustomerCode(code);
        frProjectRemnantRecord.setOperateDate(now);
        frProjectRemnantRecord.setOperatePersonId(operId);
        frProjectRemnantRecord.setOperatePersonName(name);
        frProjectRemnantRecord.setCreateUser(name);
        frProjectRemnantRecord.setCreateTime(now);
        frProjectRemnantRecord.setArrearsPrice(projectOrder.getNoPrice());
        frProjectRemnantRecord.setRemnantPrice(projectOrder.getNoPrice());
        frProjectRemnantRecord.setProjectOrderId(orderId);
        frProjectRemnantRecord.setClientId(cid);
        frProjectRemnantRecord.setClientName(clientName);
        frProjectRemnantRecord.setCourseName(courseName);
        //---
        projectOrder.setNoPrice(0.0);
        projectOrder.setRetChange(0.0);

        projectOrderMapper.updateAllColumnById(projectOrder);
        addProjectMapper.updateAllColumnById(addProject);
        frProjectRemnantRecordMapper.insert(frProjectRemnantRecord);

        FrClient frClient = frClientMapper.selectById(cid);
        this.saveOrderEntityByCustomerRemnant(addProject, frClient, operId, name, shopId, sdadiumId);
        return projectOrder;
    }

    /**
     * 新购项目补余
     * @param addProject
     * @param frClient
     * @param id
     * @param name
     */
    private void saveOrderEntityByCustomerRemnant(AddProject addProject, FrClient frClient, String id, String name, String shopId, String sdadiumId){

        MoneyReport moneyReport = new MoneyReport();


        String userTypeName = "新购项目补余";
        String tableName = "AddProject";
        Date date = new Date();
        moneyReport.setCustomerCode(addProject.getCustomerCode());
        moneyReport.setOrderNumber(frConsumeMoneyOrderService.getOrderIdByTime());
        moneyReport.setUseType(1);
        moneyReport.setUseTypeName(userTypeName);
        moneyReport.setTableId(addProject.getId());
        moneyReport.setTableName(tableName);
        moneyReport.setCustomerId(frClient.getId());
        moneyReport.setMemberCardId(addProject.getCardId());
        moneyReport.setName(frClient.getClientName());
        moneyReport.setPhone(frClient.getMobile());
        moneyReport.setCreateId(id);
        moneyReport.setCreateName(name);
        moneyReport.setCreateTime(date);
        moneyReport.setId(UUIDUtils.generateGUID());
        moneyReport.setShopId(shopId);
        moneyReport.setSdadiumId(sdadiumId);
        frConsumeMoneyOrderService.saveMoneyReport(moneyReport);
    }

    /**
     * 查询补余记录
     * @param shopId
     * @param cid
     * @param code
     * @return
     */
    public List<Map<String, Object>> findCustomerExtensionRecord(String shopId, String cid, String code){

        return addProjectMapper.findCustomerExtensionRecord(shopId, cid, code);
    }

    /**
     * 延期
     * @param map
     * @return
     */
    public AddProject customerExtension(Map<String, String> map){
        String orderId = map.get("orderId");
        String useful = map.get("useful");
        String flag = map.get("flag");
        String cid = map.get("cid");
        String name = map.get("name");
        String code = map.get("code");
        String operId = map.get("operId");
        String clientName = map.get("clientName");
        String courseName = map.get("courseName");
        Date now = new Date();
        FrProjectExtensionRecord frProjectExtensionRecord = new FrProjectExtensionRecord();
        AddProject addProject = addProjectMapper.selectById(orderId);

       // addProject.setProjectId(orderId);
       // addProject = addProjectMapper.selectOne(addProject);
        addProject.setUseful(String.valueOf(Integer.parseInt(addProject.getUseful()) + Integer.parseInt(useful)));

        //延期记录
        frProjectExtensionRecord.setId(UUIDUtils.generateGUID());
        frProjectExtensionRecord.setCustomerCode(code);
        frProjectExtensionRecord.setOperateDate(now);
        frProjectExtensionRecord.setOperatePersonId(operId);
        frProjectExtensionRecord.setOperatePersonName(name);
        frProjectExtensionRecord.setCreateUser(name);
        frProjectExtensionRecord.setCreateTime(now);
        frProjectExtensionRecord.setProjectOrderId(orderId);
        frProjectExtensionRecord.setExtensionDate(useful);
        frProjectExtensionRecord.setDescription(flag);
        frProjectExtensionRecord.setOldStartDate(addProject.getStartTime());
        frProjectExtensionRecord.setOldEndDate(addProject.getEndTime());
        frProjectExtensionRecord.setClientId(cid);
        frProjectExtensionRecord.setClientName(clientName);
        frProjectExtensionRecord.setCourseName(courseName);
        //正常
        if(addProject.getState() == 0){
            Date date = addProject.getEndTime();
            Calendar cl = Calendar.getInstance();
            cl.setTime(date);
            cl.add(Calendar.MONTH, +Integer.parseInt(useful));
            addProject.setEndTime(cl.getTime());

        }
        if(addProject.getState() == 2){
            Date date = new Date();
            Calendar cl = Calendar.getInstance();
            cl.setTime(date);
            cl.add(Calendar.MONTH, +Integer.parseInt(useful));
            addProject.setEndTime(cl.getTime());
        }
        frProjectExtensionRecord.setStartDate(now);
        frProjectExtensionRecord.setEndDate(addProject.getEndTime());
        //


        addProject.setStartTime(now);
        addProject.setState(0);
        addProjectMapper.updateAllColumnById(addProject);
        frProjectExtensionRecordMapper.insert(frProjectExtensionRecord);
        return addProject;
    }

    /**
     * 转让表
     * @param map
     * @return
     */
    public ReturnAddProject setTurnProject(Map map) throws Exception {
        TurnProject turnProject = new TurnProject();
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map));
        FrClient newClient = new FrClient();
        SysConsumeOrder sysConsumeOrder = null;
        ProjectOrder projectOrder = null;


        String operId = jsonObject.getString("operId");
        String operName = jsonObject.getString("operName");
        String shopId = jsonObject.getString("shopId");
        String cid = jsonObject.getString("cid");
        String clientName = jsonObject.getString("clientName");
        String code = jsonObject.getString("coce");
        String mobile = jsonObject.getString("mobile");
        String projectId = jsonObject.getString("projectId");
        String consumeId = jsonObject.getString("consumeId");
        String poId = jsonObject.getString("poId");
        String sdadiumId = jsonObject.getString("sdadiumId");
        String courseName = jsonObject.getString("courseName");


        AddProject addProject = addProjectMapper.selectById(projectId);

        newClient.setMobile(mobile);
        newClient = frClientMapper.selectOne(newClient);
        if(null == newClient){
            throw new Exception("获取用户出错，用户不存在");
        }

        turnProject.setId(UUIDUtils.generateGUID());
        turnProject.setAddProjectId(projectId);
        turnProject.setCreateId(operId);
        turnProject.setCreateTime(new Date());
        turnProject.setCreateName(operName);
        turnProject.setPayType(jsonObject.getInteger("payType"));
        turnProject.setFee(jsonObject.getDouble("fee"));
        turnProject.setPayMoney(jsonObject.getDouble("fee"));
        turnProject.setOldCustomerId(cid);
        turnProject.setNewCustomerId(newClient.getId());
        turnProject.setCourseName(courseName);
      //  turnProject.setOldCardId(sysConsumeOrder.getCustomerId());
        turnProject.setPerShopId(shopId);
        //转让过去，sysConsumeOrder的客户Id也要改成现在的Id
        projectOrder = projectOrderMapper.selectById(poId);
        if(null != projectOrder){
            projectOrder.setPersonnelId(newClient.getId());
            projectOrderMapper.updateAllColumnById(projectOrder);
        }


        turnProjectMapper.insert(turnProject);

        this.saveOrderEntityBySetTurnProject(code, addProject, cid, clientName, mobile, turnProject, shopId, sdadiumId);
        return null;
    }

    /**
     * 新购项目转让表
     * @param code
     * @param addProject
     * @param cid
     * @param clientName
     * @param mobile
     * @param turnProject
     */
    private void saveOrderEntityBySetTurnProject(String code, AddProject addProject, String cid, String clientName, String mobile, TurnProject turnProject, String shopId, String sdadiumId){

        MoneyReport moneyReport = new MoneyReport();


        String userTypeName = "新购项目转让";
        String tableName = "AddProject";
        Date date = new Date();
        moneyReport.setCustomerCode(code);
        moneyReport.setOrderNumber(frConsumeMoneyOrderService.getOrderIdByTime());
        moneyReport.setUseType(1);
        moneyReport.setUseTypeName(userTypeName);
        moneyReport.setTableId(addProject.getId());
        moneyReport.setTableName(tableName);
        moneyReport.setCustomerId(cid);
        moneyReport.setMemberCardId(addProject.getCardId());
        moneyReport.setName(clientName);
        moneyReport.setPhone(mobile);
        moneyReport.setCreateId(turnProject.getCreateId());
        moneyReport.setCreateName(turnProject.getCreateName());
        moneyReport.setCreateTime(date);
        moneyReport.setId(UUIDUtils.generateGUID());
        moneyReport.setShopId(shopId);
        moneyReport.setSdadiumId(sdadiumId);
        frConsumeMoneyOrderService.saveMoneyReport(moneyReport);
    }


    /**
     * 退费表
     * @param map
     * @return
     */
    public ReturnAddProject setReturnAddProject(Map map){

        ReturnAddProject returnAddProject = new ReturnAddProject();
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map));

        //计算付款方式
        String payType = jsonObject.getString("payType");
        double money = jsonObject.getDouble("returnMoney");

        String operId = jsonObject.getString("id");
        String operName = jsonObject.getString("name");
        String cid = jsonObject.getString("cid");
        String clientName = jsonObject.getString("clientName");
        String projectId = jsonObject.getString("projectId");
        String courseName = jsonObject.getString("courseName");
        //String consumeId = jsonObject.getString("consumeId");
      //  String code = jsonObject.getString("code");
        AddProject addProject = addProjectMapper.selectById(projectId);

        FrClient frClient = frClientMapper.selectById(cid);
        returnAddProject.setAddProjectID(projectId);
        returnAddProject.setCustomerCode(addProject.getCustomerCode());
        returnAddProject.setReturnCount(jsonObject.getInteger("endCount"));
        returnAddProject.setReturnMoney(money);
        returnAddProject.setCash(jsonObject.getDouble("fee"));
        returnAddProject.setTurnMoney(0.0);
        returnAddProject.setReturnNumber(jsonObject.getString("count"));
        returnAddProject.setSaleName(frClient.getConsultantName());
        returnAddProject.setSaleID(frClient.getConsultantId());
        returnAddProject.setCreateTime(new Date());
        returnAddProject.setCreateNameID(operId);
        returnAddProject.setCreateName(operName);
        returnAddProject.setPayType(payType);
        returnAddProject.setRaShopid(jsonObject.getString("deduct"));
        returnAddProject.setId(UUIDUtils.generateGUID());
        returnAddProject.setEmployID(operId);
        returnAddProject.setEmployName(operName);
        returnAddProject.setClientId(cid);
        returnAddProject.setClientName(clientName);
        returnAddProject.setCourseName(courseName);
        returnAddProjectMapper.insert(returnAddProject);
        //历史
        addProject.setState(3);
        addProjectMapper.updateAllColumnById(addProject);

        //
        this.saveOrderEntityBySetReturnAddProject(jsonObject, addProject, frClient);

        return returnAddProject;
    }

    /**
     * 新购项目退费表
     * @param jsonObject
     * @param addProject
     * @param frClient
     */
    private void saveOrderEntityBySetReturnAddProject(JSONObject jsonObject, AddProject addProject, FrClient frClient){

        MoneyReport moneyReport = new MoneyReport();
        String operId = jsonObject.getString("id");
        String operName = jsonObject.getString("name");
        String cid = jsonObject.getString("cid");
        String clientName = jsonObject.getString("clientName");
        String code = jsonObject.getString("code");
        String shopId = jsonObject.getString("shopId");
        String sdadiumId = jsonObject.getString("sdadiumId");

        String userTypeName = "新购项目退费";
        String tableName = "AddProject";
        Date date = new Date();
        moneyReport.setCustomerCode(code);
        moneyReport.setOrderNumber(frConsumeMoneyOrderService.getOrderIdByTime());
        moneyReport.setUseType(1);
        moneyReport.setUseTypeName(userTypeName);
        moneyReport.setTableId(addProject.getId());
        moneyReport.setTableName(tableName);
        moneyReport.setCustomerId(cid);
        moneyReport.setMemberCardId(addProject.getCardId());
        moneyReport.setName(clientName);
        moneyReport.setPhone(frClient.getMobile());
        moneyReport.setCreateId(operId);
        moneyReport.setCreateName(operName);
        moneyReport.setCreateTime(date);
        moneyReport.setId(UUIDUtils.generateGUID());
        moneyReport.setShopId(shopId);
        moneyReport.setSdadiumId(sdadiumId);
        frConsumeMoneyOrderService.saveMoneyReport(moneyReport);
    }


    /**
     * 查询退费记录
     * @param shopId
     * @param clientId
     * @param code
     * @return
     */
    public List getListReturnAddProject(String shopId, String clientId, String code){
//        List<ReturnAddProject> returnAddProjects = returnAddProjectMapper.selectList(new EntityWrapper<ReturnAddProject>()
//                .where("clientId = '" + cid + "'"));
//        return returnAddProjects;

        return addProjectMapper.getListReturnAddProject(shopId, clientId, code);
    }


    /**
     * 查询转让记录
     * @param shopId
     * @param clientId
     * @param code
     * @return
     */
    public List getListTurnProject(String shopId, String clientId, String code){
        return addProjectMapper.getListTurnProject(shopId, clientId, code);

//        List<TurnProject> returnAddProjects = turnProjectMapper.selectList(new EntityWrapper<TurnProject>()
//                .where("OldCustomerId = '" + cid + "'"));
//        List list = new ArrayList();
//        for(TurnProject turnProject: returnAddProjects){
//            Map map = new JSONObject();
//            FrClient frClientOld = frClientMapper.selectById(turnProject.getOldCustomerId());
//            FrClient frClientNew = frClientMapper.selectById(turnProject.getNewCustomerId());
//            map.put("turnProject", turnProject);
//            map.put("frClientOld", frClientOld);
//            map.put("frClientNew", frClientNew);
//            list.add(map);
//        }
//        return list;
    }


    /**
     * 冲销
     * @param id
     */
    public void deleteTurnProject(String id){
        TurnProject turnProject = turnProjectMapper.selectById(id);
        //AddProject addProject = addProjectMapper.selectById(turnProject.getAddProjectId());
        ProjectOrder projectOrder = new ProjectOrder();
        projectOrder.setObjectId(turnProject.getAddProjectId());
        projectOrder = projectOrderMapper.selectOne(projectOrder);
        SysConsumeOrder sysConsumeOrder = new SysConsumeOrder();
        sysConsumeOrder.setOrderNumber(projectOrder.getOrderNumber());
        sysConsumeOrder.setCustomerId(turnProject.getNewCustomerId());
        sysConsumeOrder = sysConsumeOrderMapper.selectOne(sysConsumeOrder);
        sysConsumeOrder.setCustomerId(turnProject.getOldCustomerId());
        sysConsumeOrderMapper.updateAllColumnById(sysConsumeOrder);
        turnProjectMapper.deleteById(turnProject.getId());
    }


    /**
     * 查询项目list
     * @param shopId
     * @param sdaduimId
     * @param code
     */
    public List<Map<String, Object>> getCourseList(String shopId, String sdaduimId, String code, String eduType){
        if(StringUtils.equals(eduType, "0")){
            return frCustomerCourseProjectMapper.getCourseList(shopId, sdaduimId, code);
        }
        return frCustomerCourseProjectMapper.getCourseList1(shopId, sdaduimId, code);

    }

    /**
     * 查询项目list
     * @param shopId
     * @param sdaduimId
     * @param code
     */
    public List<Map<String, Object>> getCoursePackageCourseId(String shopId, String sdaduimId, String code, String courseId){

        return frCustomerCourseProjectMapper.getCoursePackageCourseId(shopId, sdaduimId, code, courseId);

    }




}

