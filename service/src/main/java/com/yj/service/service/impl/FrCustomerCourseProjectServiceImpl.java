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
    @Resource
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
        JSONObject cardOrderDetail = JSONObject.parseObject((String) map.get("cardOrderDetail"));
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



    }

    /**
     * 项目
     * @param type
     * @param status
     * @param shopName
     * @param timeType
     * @param startDate
     * @param endDate
     * @param name
     * @param code
     * @return
     */
    public List getCourseList(String type, String status, String shopName,
                              String timeType, String startDate, String endDate, String name, String code,String orderType, String cid){

        String conditon = "CustomerCode = '" + code + "' ";
        if(StringUtils.equals("1", status)){
            conditon += " and State = 0";
        }
        if(StringUtils.equals("2", status)){
            conditon += " and State = 3";
        }
        if(StringUtils.equals(timeType, "1")){
            if(!StringUtils.isBlank(startDate)){
                conditon += " and StartTime >= " + new Date(startDate) ;
            }
            if(!StringUtils.isBlank(endDate)){
                conditon += " and StartTime <= " + new Date(startDate) ;
            }

        }
        if(StringUtils.equals(timeType, "2")){
            if(!StringUtils.isBlank(startDate)){
                conditon += " and StartTime >= " + new Date(startDate) ;
            }
            if(!StringUtils.isBlank(endDate)){
                conditon += " and StartTime <= " + new Date(startDate) ;
            }
        }
        if(StringUtils.equals(timeType, "3")){
            if(!StringUtils.isBlank(startDate)){
                conditon += " and StartTime >= " + new Date(startDate) ;
            }
            if(!StringUtils.isBlank(endDate)){
                conditon += " and StartTime <= " + new Date(startDate) ;
            }
        }
        if(!StringUtils.isBlank(shopName)){
            conditon += " and ShopId = '" + shopName + "'";
        }
        List<AddProject> list = addProjectMapper.selectList(new EntityWrapper<AddProject>()
                .where(conditon));

        return getList(list, Integer.parseInt(orderType), cid);
    }



    private List getList(List<AddProject> list, Integer type, String cid){

        List list1 = new ArrayList();
        //订单id
        String id = "";
        for(AddProject addProject: list){
            Map map = new JSONObject();
            ProjectOrder projectOrder = new ProjectOrder();
            // AddProject addProject = new AddProject();
            AddProjectConsume addProjectConsume = new AddProjectConsume();
            List<AddProjectConsume> addProjectConsumes = null;
            PersonnelInfo personnelInfo = new PersonnelInfo();
            SysConsumeOrder sysConsumeOrder = new SysConsumeOrder();
            FrProjectStartRecord frProjectStartRecord = new FrProjectStartRecord();
            FrProjectRemnantRecord frProjectRemnantRecord = new FrProjectRemnantRecord();
            List<FrProjectExtensionRecord> frProjectExtensionRecords = new ArrayList<>();
            List<FrClientPersonnelRelate> frClientPersonnelRelates = null;
            FrClientPersonnelRelate frClientPersonnelRelate = null;
            Shop shop = new Shop();
            Sdaduim sdaduim = new Sdaduim();
            //----
            id = addProject.getId();
            //projectOrder
            projectOrder.setObjectId(id);
            projectOrder.setOrderType(type);

            // addProject.setProjectId(id);
            //消费表
            addProjectConsume.setAddProjectId(id);
            //启用记录表
            frProjectStartRecord.setProjectOrderId(id);

            //补余
            frProjectRemnantRecord.setProjectOrderId(id);

            //延期
            frProjectRemnantRecord.setProjectOrderId(id);

            //---
            projectOrder = projectOrderMapper.selectOne(projectOrder);
            if(null == projectOrder){
                continue;
            }
            sysConsumeOrder.setOrderNumber(projectOrder.getOrderNumber());
            sysConsumeOrder.setCustomerId(cid);
            sysConsumeOrder = sysConsumeOrderMapper.selectOne(sysConsumeOrder);
            if(null == sysConsumeOrder){
                continue;
            }
            addProject = addProjectMapper.selectOne(addProject);
            shop = shopMapper.selectById(addProject.getShopId());
            sdaduim = sdaduimMapper.selectById(addProject.getSdadiumId());
            addProjectConsumes = addProjectConsumeMapper.selectList(new EntityWrapper<AddProjectConsume>()
                    .where("AddProjectId = '" + addProject.getId() + "'"));

            if(null == addProjectConsumes){
                addProjectConsumes = new ArrayList<>();
            }
            personnelInfo = personnelInfoMapper.selectById(sysConsumeOrder.getCreateId());
            frProjectStartRecord = frProjectStartRecordMapper.selectOne(frProjectStartRecord);
            frProjectRemnantRecord = frProjectRemnantRecordMapper.selectOne(frProjectRemnantRecord);
            frProjectExtensionRecords = frProjectExtensionRecordMapper.selectList(new EntityWrapper<FrProjectExtensionRecord>()
                    .where("projectOrderId = '" + addProject.getId() + "'"));

            frClientPersonnelRelates = frClientPersonnelRelateMapper.selectList(new EntityWrapper<FrClientPersonnelRelate>()
                    .where("other_table_id = '" + addProject.getId() + "'"));
            if(null != frClientPersonnelRelates && frClientPersonnelRelates.size() > 0){
                frClientPersonnelRelate = frClientPersonnelRelates.get(0);
            }
            //教练
            map.put("frClientPersonnelRelates",frClientPersonnelRelate);

            //项目订单表
            map.put("projectOrder", JSONObject.toJSON(projectOrder));

            //会员增购项目表
            map.put("addProject", JSONObject.toJSON(addProject));

            //门店
            map.put("shop", JSONObject.toJSON(shop));

            // 场馆
            map.put("sdaduim", JSONObject.toJSON(sdaduim));

            // 增购项目表 //addConsumeProject
            map.put("addProjectConsumes", JSONObject.toJSON(addProjectConsumes));

            // 消费结账单表
            map.put("sysConsumeOrder", JSONObject.toJSON(sysConsumeOrder));

            // 用户
            map.put("personnelInfo", JSONObject.toJSON(personnelInfo));

            // 启用记录
            map.put("frProjectStartRecord", JSONObject.toJSON(frProjectStartRecord));

            // 补余记录
            map.put("frProjectRemnantRecord", JSONObject.toJSON(frProjectRemnantRecord));

            // 延期记录
            map.put("frProjectExtensionRecords", JSONArray.toJSON(frProjectExtensionRecords));

            list1.add(map);
        }
        return list1;
    }

    /**
     * 查询全部
     * @param shopid
     * @param code
     * @return
     */
    public List getOrderListByCid(String shopid, String code, String cid){
        List<AddProject> list = addProjectMapper.selectList(new EntityWrapper<AddProject>()
                .where("ShopId = '" + shopid + "' and CustomerCode = '" + code + "' "));

        List list1 = new ArrayList();
        //订单id
        String id = "";
        for(AddProject addProject: list){
            Map map = new JSONObject();
            ProjectOrder projectOrder = new ProjectOrder();
           // AddProject addProject = new AddProject();
            AddProjectConsume addProjectConsume = new AddProjectConsume();
            List<AddProjectConsume> addProjectConsumes = null;
            PersonnelInfo personnelInfo = new PersonnelInfo();
            SysConsumeOrder sysConsumeOrder = new SysConsumeOrder();
            FrProjectStartRecord frProjectStartRecord = new FrProjectStartRecord();
            FrProjectRemnantRecord frProjectRemnantRecord = new FrProjectRemnantRecord();
            List<FrProjectExtensionRecord> frProjectExtensionRecords = new ArrayList<>();
            List<FrClientPersonnelRelate> frClientPersonnelRelates = null;
            FrClientPersonnelRelate frClientPersonnelRelate = null;
            Shop shop = new Shop();
            Sdaduim sdaduim = new Sdaduim();
            //----
            id = addProject.getId();
            //projectOrder
            projectOrder.setObjectId(id);

           // addProject.setProjectId(id);
            //消费表
            addProjectConsume.setAddProjectId(id);
            //启用记录表
            frProjectStartRecord.setProjectOrderId(id);

            //补余
            frProjectRemnantRecord.setProjectOrderId(id);

            //延期
          //  frProjectRemnantRecord.setProjectOrderId(id);

            //---
            projectOrder = projectOrderMapper.selectOne(projectOrder);
            if(null == projectOrder){
                projectOrder = new ProjectOrder();
            }
            sysConsumeOrder.setOrderNumber(projectOrder.getOrderNumber());
            sysConsumeOrder.setCustomerId(cid);
            sysConsumeOrder = sysConsumeOrderMapper.selectOne(sysConsumeOrder);
            if(null == sysConsumeOrder){
                continue;
            }


            addProject = addProjectMapper.selectOne(addProject);
            shop = shopMapper.selectById(addProject.getShopId());
            sdaduim = sdaduimMapper.selectById(addProject.getSdadiumId());
            addProjectConsumes = addProjectConsumeMapper.selectList(new EntityWrapper<AddProjectConsume>()
                    .where("AddProjectId = '" + addProject.getId() + "'"));

            personnelInfo = personnelInfoMapper.selectById(sysConsumeOrder.getCreateId());
            frProjectStartRecord = frProjectStartRecordMapper.selectOne(frProjectStartRecord);
            frProjectRemnantRecord = frProjectRemnantRecordMapper.selectOne(frProjectRemnantRecord);
            frProjectExtensionRecords = frProjectExtensionRecordMapper.selectList(new EntityWrapper<FrProjectExtensionRecord>()
                    .where("projectOrderId = '" + addProject.getId() + "'"));
            frClientPersonnelRelates = frClientPersonnelRelateMapper.selectList(new EntityWrapper<FrClientPersonnelRelate>()
                    .where("other_table_id = '" + addProject.getId() + "'"));
            if(null != frClientPersonnelRelates && frClientPersonnelRelates.size() > 0){
                frClientPersonnelRelate = frClientPersonnelRelates.get(0);
            }
            if(null == addProjectConsumes){
                addProjectConsumes = new ArrayList<>();
            }
            //教练
            map.put("frClientPersonnelRelates",frClientPersonnelRelate);

            //项目订单表
            map.put("projectOrder", JSONObject.toJSON(projectOrder));

            //会员增购项目表
            map.put("addProject", JSONObject.toJSON(addProject));

            //门店
            map.put("shop", JSONObject.toJSON(shop));

            // 场馆
            map.put("sdaduim", JSONObject.toJSON(sdaduim));

            // 增购项目表 //addConsumeProject
            map.put("addProjectConsumes", JSONObject.toJSON(addProjectConsumes));

            // 消费结账单表
            map.put("sysConsumeOrder", JSONObject.toJSON(sysConsumeOrder));

            // 用户
            map.put("personnelInfo", JSONObject.toJSON(personnelInfo));

            // 启用记录
            map.put("frProjectStartRecord", JSONObject.toJSON(frProjectStartRecord));

            // 补余记录
            map.put("frProjectRemnantRecord", JSONObject.toJSON(frProjectRemnantRecord));

            // 延期记录
            map.put("frProjectExtensionRecords", JSONArray.toJSON(frProjectExtensionRecords));

            list1.add(map);
        }
        return list1;
    }

    /**
     * 启用项目
     * @param id
     */
    public AddProject starCustomer(String id, String cid, String name, String code){
        Date now = new Date();
        AddProject addProject = addProjectMapper.selectById(id);;
        FrProjectStartRecord frProjectStartRecord = new FrProjectStartRecord();
        //查询
       // addProject.setProjectId(id);
       // addProject = addProjectMapper.selectOne(addProject);
        frProjectStartRecord.setProjectOrderId(id);
        if(null != frProjectStartRecordMapper.selectOne(frProjectStartRecord)){
            return addProject;
        }
        //记录表
        frProjectStartRecord.setId(UUIDUtils.generateGUID());
        frProjectStartRecord.setCustomerCode(code);
        frProjectStartRecord.setOperateDate(now);
        frProjectStartRecord.setOperatePersonId(cid);
        frProjectStartRecord.setOperatePersonName(name);
        frProjectStartRecord.setCreateUser(name);
        frProjectStartRecord.setCreateTime(now);
        frProjectStartRecord.setOldStartDate(addProject.getStartTime());
        frProjectStartRecord.setOldEndDate(addProject.getEndTime());

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

        return addProject;
    }


    /**
     * 补余
     * @param id
     * @return
     */
    public ProjectOrder customerRemnant(String id, String cid, String name, String code){
        Date now = new Date();
        FrProjectRemnantRecord frProjectRemnantRecord = new FrProjectRemnantRecord();
        AddProject addProject = null;

        ProjectOrder projectOrder = new ProjectOrder();
        projectOrder.setObjectId(id);
        projectOrder = projectOrderMapper.selectOne(projectOrder);
        addProject = addProjectMapper.selectById(id);
        //修改成正常
        addProject.setState(4);
        //
        frProjectRemnantRecord.setProjectOrderId(id);
        //查询是否存在
        if(null != frProjectRemnantRecordMapper.selectOne(frProjectRemnantRecord)){
            return projectOrder;
        }
        //记录表
        frProjectRemnantRecord.setId(UUIDUtils.generateGUID());
        frProjectRemnantRecord.setCustomerCode(code);
        frProjectRemnantRecord.setOperateDate(now);
        frProjectRemnantRecord.setOperatePersonId(cid);
        frProjectRemnantRecord.setOperatePersonName(name);
        frProjectRemnantRecord.setCreateUser(name);
        frProjectRemnantRecord.setCreateTime(now);
        frProjectRemnantRecord.setArrearsPrice(projectOrder.getNoPrice());
        frProjectRemnantRecord.setRemnantPrice(projectOrder.getNoPrice());
        frProjectRemnantRecord.setProjectOrderId(id);
        //---
        projectOrder.setNoPrice(0.0);
        projectOrder.setRetChange(0.0);

        projectOrderMapper.updateAllColumnById(projectOrder);
        addProjectMapper.updateAllColumnById(addProject);
        frProjectRemnantRecordMapper.insert(frProjectRemnantRecord);
        return projectOrder;
    }

    /**
     * 延期
     * @param orderId
     * @param useful
     * @return
     */
    public AddProject customerExtension(String orderId, String useful, String flag, String cid, String name, String code){
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
        frProjectExtensionRecord.setOperatePersonId(cid);
        frProjectExtensionRecord.setOperatePersonName(name);
        frProjectExtensionRecord.setCreateUser(name);
        frProjectExtensionRecord.setCreateTime(now);
        frProjectExtensionRecord.setProjectOrderId(orderId);
        frProjectExtensionRecord.setExtensionDate(useful);
        frProjectExtensionRecord.setDescription(flag);
        frProjectExtensionRecord.setOldStartDate(addProject.getStartTime());
        frProjectExtensionRecord.setOldEndDate(addProject.getEndTime());

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
    public ReturnAddProject setTurnProject(Map map, String name, String shopid){
        TurnProject turnProject = new TurnProject();
       // JSONObject jsonObject = JSONObject.parseObject(map.toString());
        JSONObject jsonObject = JSONObject.parseObject((String) map.get("insertData"));
        ProjectOrder projectOrder = JSONObject.parseObject((String) map.get("projectOrder"), ProjectOrder.class);
        AddProject addProject = JSONObject.parseObject((String) map.get("addProject"), AddProject.class);
        SysConsumeOrder sysConsumeOrder = JSONObject.parseObject((String) map.get("sysConsumeOrder"), SysConsumeOrder.class);
        FrClient frClient = new FrClient();
        frClient.setMobile(jsonObject.getString("mobile"));
        frClient = frClientMapper.selectOne(frClient);
//        PersonnelInfo personnelInfo = new PersonnelInfo();
//        personnelInfo.setMobile(jsonObject.getString("mobile"));
//        personnelInfo = personnelInfoMapper.selectOne(personnelInfo);
//        turnProject.setNewCardId(addProject.getCardId());
//        turnProject.setOldCardId(addProject.getCardId());
        turnProject.setId(UUIDUtils.generateGUID());
        turnProject.setAddProjectId(addProject.getId());
        turnProject.setCreateId(jsonObject.getString("cid"));
        turnProject.setCreateTime(new Date());
        turnProject.setCreateName(name);
        turnProject.setPayType(jsonObject.getInteger("payType"));
        turnProject.setFee(jsonObject.getDouble("fee"));
        turnProject.setPayMoney(jsonObject.getDouble("fee"));
        turnProject.setOldCustomerId(sysConsumeOrder.getCustomerId());
      //  turnProject.setOldCardId(sysConsumeOrder.getCustomerId());
        turnProject.setNewCustomerId(frClient.getId());
        turnProject.setPerShopId(shopid);
        //转让过去，sysConsumeOrder的客户Id也要改成现在的Id
        sysConsumeOrder = sysConsumeOrderMapper.selectById(sysConsumeOrder.getId());
        sysConsumeOrder.setCustomerId(frClient.getId());
        sysConsumeOrderMapper.updateAllColumnById(sysConsumeOrder);
        turnProjectMapper.insert(turnProject);
        return null;
    }


    /**
     * 退费表
     * @param map
     * @return
     */
    public ReturnAddProject setReturnAddProject(Map map, String name){

        ReturnAddProject returnAddProject = new ReturnAddProject();
        JSONObject jsonObject = JSONObject.parseObject((String) map.get("insertData"));
        AddProject addProject = JSONObject.parseObject((String) map.get("addProject"), AddProject.class);
        String objectId = addProject.getId();
        addProject = addProjectMapper.selectById(objectId);
        //计算付款方式
        String payType = jsonObject.getString("payType");
        double money = jsonObject.getDouble("returnMoney");
        returnAddProject.setAddProjectID(objectId);
        returnAddProject.setCustomerCode(addProject.getCustomerCode());
        returnAddProject.setReturnCount(jsonObject.getInteger("endCount"));
        returnAddProject.setReturnMoney(money);
        returnAddProject.setCash(jsonObject.getDouble("fee"));
        returnAddProject.setTurnMoney(0.0);
        returnAddProject.setReturnNumber(jsonObject.getString("count"));
        returnAddProject.setSaleName(name);
        returnAddProject.setCreateTime(new Date());
        returnAddProject.setCreateNameID(jsonObject.getString("cid"));
        returnAddProject.setCreateName(jsonObject.getString("name"));
        returnAddProject.setPayType(payType);
        returnAddProject.setRaShopid(jsonObject.getString("deduct"));
        returnAddProject.setId(UUIDUtils.generateGUID());
        returnAddProject.setEmployID(jsonObject.getString("id"));
        returnAddProject.setEmployName(jsonObject.getString("name"));
        returnAddProjectMapper.insert(returnAddProject);
        //历史
        addProject.setState(3);
        addProjectMapper.updateAllColumnById(addProject);
        return returnAddProject;
    }

    /**
     * 查询退费记录
     * @param cid
     * @return
     */
    public List getListReturnAddProject(String cid){
        List<ReturnAddProject> returnAddProjects = returnAddProjectMapper.selectList(new EntityWrapper<ReturnAddProject>()
                .where("CreateNameID = '" + cid + "'"));
        return returnAddProjects;
    }


    /**
     * 查询转让记录
     * @param cid
     * @return
     */
    public List getListTurnProject(String cid){
        List<TurnProject> returnAddProjects = turnProjectMapper.selectList(new EntityWrapper<TurnProject>()
                .where("OldCustomerId = '" + cid + "'"));
        List list = new ArrayList();
        for(TurnProject turnProject: returnAddProjects){
            Map map = new JSONObject();
            FrClient frClientOld = frClientMapper.selectById(turnProject.getOldCustomerId());
            FrClient frClientNew = frClientMapper.selectById(turnProject.getNewCustomerId());
            map.put("turnProject", turnProject);
            map.put("frClientOld", frClientOld);
            map.put("frClientNew", frClientNew);
            list.add(map);
        }
        return list;
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


}

