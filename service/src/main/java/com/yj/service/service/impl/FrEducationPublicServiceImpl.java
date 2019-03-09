package com.yj.service.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.util.DateUtil;
import com.yj.common.util.PageUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.*;
import com.yj.dal.model.*;
import com.yj.service.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * className FrEducationServiceImpl
 * by Kylan
 *
 * @author Kylan
 * @date 2019-01-05 11:25
 */
@Service
public class FrEducationPublicServiceImpl {

    @Autowired
    private FrClientMapper frClientMapper;

    @Autowired
    private FrClientPersonalMapper frClientPersonalMapper;

    @Autowired
    private FrCardMapper frCardMapper;

    @Autowired
    private FrLevelMapper frLevelMapper;

    @Autowired
    private PersonnelInfoMapper personnelInfoMapper;

    @Autowired
    private FrEducationPublicMapper frEducationPublicMapper;

    @Autowired
    private FrGroupClassRoomMapper frGroupClassRoomMapper;

    @Autowired
    private FrGroupClassRoomSeatMapper frGroupClassRoomSeatMapper;

    @Autowired
    private FrEducationClientInfoMapper frEducationClientInfoMapper;

    @Autowired
    private FrEducationPlanMapper frEducationPlanMapper;

    @Autowired
    private FrEducationConfigMapper frEducationConfigMapper;

    @Autowired
    private FrEducationReserveObjectMapper frEducationReserveObjectMapper;
    @Resource
    private IFrCardOrderPayModeService iFrCardOrderPayModeService;

    @Resource
    private IFrCardOrderPriceDatailService iFrCardOrderPriceDatailService;

    @Resource
    private IFrCardOrderDatailService iFrCardOrderDatailService;

    @Autowired
    private FrEducationServiceImpl frEducationService;

    @Autowired
    private FrEducationMemberOrderMapper frEducationMemberOrderMapper;

    @Autowired
    private FrGroupCourseMapper frGroupCourseMapper;

    @Autowired
    private FrEducationCardObjectMapper frEducationCardObjectMapper;

    @Autowired
    private ProjectOrderMapper projectOrderMapper;

    @Autowired
    private FrEducationClassOrderMapper frEducationClassOrderMapper;

    @Autowired
    private IFrGroupCourseService iFrGroupCourseService;

    @Autowired
    private IFrCategoryItemService categoryItemService;//场馆项目

    @Autowired
    private FrSettingInfoMapper frSettingInfoMapper;

    @Autowired
    private FrEducationFreezeClientMapper frEducationFreezeClientMapper;

    @Autowired
    private FrConsumeMoneyOrderServiceImpl frConsumeMoneyOrderService;
    /**
     * 获取教练
     *
     * @param code
     * @param shopId
     * @return
     */
    public List findCoachList(String code, String shopId) {
        return frEducationPublicMapper.findCoachList(shopId, code);
    }

    /**
     * 获取场馆
     *
     * @param code
     * @param shopId
     * @return
     */
    public List findSdaduimList(String code, String shopId) {
        return frEducationPublicMapper.findSdaduimList(shopId, code);
    }

    /**
     * 查询数据
     *
     * @param code
     * @param shopId
     * @param coachId
     * @param sdaduimId
     * @param beginDate
     * @param endDate
     * @param eduType     0团教 1 私教
     * @param reserveType 私教一对一
     * @return
     */
    public List findEducationList(String code, String shopId, String coachId, String sdaduimId, String beginDate, String endDate, String eduType, String reserveType) {
        //私教一对一
        if (StringUtils.equals(eduType, "1") && StringUtils.equals(reserveType, "1")) {
            //findEducationOneToOneClientInfo
            List<Map<String, Object>> list = frEducationPublicMapper.findEducationOneToOneList(shopId, code, coachId, sdaduimId, beginDate, endDate, eduType);
            String eduId = "";
            Map<String, Object> mapClientInfo = null;
            for(Map<String, Object> map: list){
                eduId = String.valueOf(map.get("id"));
                mapClientInfo = frEducationPublicMapper.findEducationOneToOneClientInfo(eduId);
                if(null != mapClientInfo){
                    map.putAll(mapClientInfo);
                }

            }
            return list;

         //   return frEducationPublicMapper.findEducationOneToManyList(shopId, code, coachId, sdaduimId, beginDate, endDate, eduType);

        }

        //团教
        return frEducationPublicMapper.findEducationList(shopId, code, coachId, sdaduimId, beginDate, endDate, eduType, reserveType);
    }

    /**
     * 查询指定教学中的待确认会员
     *
     * @param eduId
     * @param reserveStatus
     * @return
     */
    public List findMemberReserveStatusList(String eduId, String reserveStatus, String searchInput) {
        return frEducationClientInfoMapper.findMemberReserveStatusList(eduId, reserveStatus, searchInput);
    }

    /**
     * 更改会员预约状态
     *
     * @param map
     * @return
     */
    public void changeEduClientStatus(Map<String, String> map) throws Exception {
        Integer reserveStatus = Integer.parseInt(map.get("status"));
        String id = map.get("clientInfoId");
        FrEducationClientInfo frEducationClientInfo = null;
        //改成已预约
        if (1 == reserveStatus) {
            frEducationClientInfo = frEducationClientInfoMapper.selectById(id);
            //判断当前用户是否已经预约过
            List<FrEducationClientInfo> frEducationClientInfos = frEducationClientInfoMapper.findMemberNotCancel(frEducationClientInfo.getEducationId(), frEducationClientInfo.getMemberId());
            if (frEducationClientInfos.size() > 0) {
                throw new Exception("该会员已经预约过该课程。");
            }
        }
        frEducationClientInfo = new FrEducationClientInfo();
        frEducationClientInfo.setId(id);
        frEducationClientInfo.setReserveStatus(reserveStatus);
        frEducationClientInfoMapper.updateById(frEducationClientInfo);
    }

    /**
     * 预约详情，根据Id 查询数据
     *
     * @param eduId
     * @return
     */
    public Map findEducationById(String eduId) {
        return frEducationPublicMapper.findEducationById(eduId);
    }


    /**
     * 查询会员
     *
     * @param CustomerCode
     * @param mobile
     * @return
     */
    public List<Map<String, Object>> getClientList(String CustomerCode, String mobile) {
        List<Map<String, Object>> lists = frEducationPublicMapper.getClientByMobileName(CustomerCode, mobile);
        //查询会员卡
        String clientId = "";
//        for(Map<String, Object> map: lists){
//            clientId = (String) map.get("id");
//            String condition = "client_id='" + clientId + "' and is_using=1 and status=0 and CustomerCode='" + CustomerCode + "'";
//            List cards = frCardMapper.selectList(new EntityWrapper<FrCard>().where(condition));
//            map.put("cards", cards);
//        }


        return lists;
    }

    /**
     * 查询教室座位表
     *
     * @param roomId
     * @return
     */
    public Map getGroupRoomSeatById(String roomId) {
        return frEducationPublicMapper.getGroupRoomSeatById(roomId);
    }

    /**
     * 保存预约
     *
     * @param map
     */
    public void saveEducationItem(Map<String, Object> map) throws Exception {
        String level = "";
        String eduType = "0";
        eduType = String.valueOf(map.get("eduType"));
        FrEducationClientInfo frEducationClientInfo = JSONObject.parseObject(JSONObject.toJSONString(map), FrEducationClientInfo.class);
        FrEducation frEducation = frEducationPublicMapper.selectById(frEducationClientInfo.getEducationId());
        //判断当前用户是否被冻结
        FrEducationFreezeClient frEducationFreezeClient = new FrEducationFreezeClient();
        frEducationFreezeClient.setClientId(frEducationClientInfo.getReserveClientId());
        frEducationFreezeClient.setEduType(frEducation.getType());
        frEducationFreezeClient = frEducationFreezeClientMapper.selectOne(frEducationFreezeClient);

        if(null != frEducationFreezeClient && frEducationFreezeClient.getUse() == false){
            //被冻结
            throw new Exception("抱歉，该用户'"+frEducationClientInfo.getReserveClientName()+"'被冻结");
        }
        //判断该课程是否预约满员
        //判断当前课程预约人数是否已经满员

        Integer a = 0;
        Map<String, String> mapValue = frEducationClientInfoMapper.findClientCountByEduId(frEducation.getId(), "1");
        a += Integer.parseInt(String.valueOf(mapValue.get("value")));
        mapValue = frEducationClientInfoMapper.findClientCountByEduId(frEducation.getId(), "2");
        a += Integer.parseInt(String.valueOf(mapValue.get("value")));
        if(frEducation.getReserveTotalNum() <= a){
            throw new Exception("抱歉，该预约人数已满！");
        }


        //课程配置
        FrEducationConfig frEducationConfig = new FrEducationConfig();
        frEducationConfig.setEducationId(frEducationClientInfo.getEducationId());
        frEducationConfig = frEducationConfigMapper.selectOne(frEducationConfig);


        //判断当前用户卡号是否已经预约
        List<FrEducationClientInfo> frEducationClientInfos = frEducationClientInfoMapper.findMemberNotCancel(frEducationClientInfo.getEducationId(), frEducationClientInfo.getMemberId());
        if (frEducationClientInfos.size() > 0) {
            throw new Exception("该会员已经预约");
        }
        //判断会员等级预约限制
        Map<String, Object> mapClient = frEducationPublicMapper.getClientMemberType(frEducationClientInfo.getMemberId());
        //判断限制
        //frEducationReserveObject = frEducationReserveObjectMapper.selectOne(frEducationReserveObject);
        FrClient frClient = frClientMapper.selectById(frEducationClientInfo.getMemberId());
        level = frClient.getLevelId();
        frEducationService.checkLevel(level, frEducationConfig.getId(), (String) mapClient.get("memberType"));

        //保存
        frEducationClientInfo.setId(UUIDUtils.generateGUID());
        //查找座位 {replace}
        if(StringUtils.equals(eduType, "0")){
            FrGroupClassRoomSeat frGroupClassRoomSeat = frGroupClassRoomSeatMapper.selectById(frEducationClientInfo.getSeatId());
            frGroupClassRoomSeat.setSeatNum(JSONArray.toJSONString(map.get("seatNum")).replace("{replace}", frEducationClientInfo.getId()));
            frGroupClassRoomSeatMapper.updateAllColumnById(frGroupClassRoomSeat);
        }
        frEducationClientInfoMapper.insert(frEducationClientInfo);

    }


    /**
     * 查询已经付钱过的用户
     *
     * @param eduId
     * @return
     */
    public List<Map<String, Object>> findMemberOrderListByEduId(String eduId, String searchInput) {
        List<Map<String, Object>> map = frEducationMemberOrderMapper.findMemberOrderList(eduId, searchInput);
        return map;
    }

    /**
     * 根据卡号，手机号，票券，团购号查询卡号
     *
     * @param searchInput
     * @param shopId
     * @return
     */
    public List<Map<String, Object>> findMemberCardByInput(String searchInput, String shopId, String configStartClass, String code) {
        List<Map<String, Object>> map = null;
        boolean isStartClass = Boolean.parseBoolean(configStartClass);
        String isSearchMobile = "0";
        if (searchInput.length() == 11) {
            isSearchMobile = "1";
        }
        //true 全部可以预约
        if (isStartClass) {
            map = frEducationPublicMapper.findMemberCardByInput(searchInput, shopId, code, isSearchMobile);
        }
        //false 只能应预约过的可以预约
        else {
            map = frEducationPublicMapper.findMemberCardReserveByInput(searchInput, shopId, code, isSearchMobile);
        }

        return map;
    }

    /**
     * 查看训练计划
     *
     * @param courseId
     * @return
     */
    public List<Map<String, Object>> findShowTrainingPlan(String courseId) {
        //findCourseTrainingPlan
        List<Map<String, Object>> list = frEducationPublicMapper.findCourseTrainingPlan(courseId);
        return list;
    }

    /**
     * 查看课程
     *
     * @param courseId
     * @return
     */
    public Map<String, Object> findCourseById(String courseId) {
        Map<String, Object> map = (Map<String, Object>) JSONObject.toJSON(frGroupCourseMapper.selectById(courseId));
        return map;
    }


    /**
     * 查看排课配置的课程
     *
     * @param eduId
     * @return
     */
    public List<FrEducationCardObject> findSettCourseEdu(String eduId) {
        List<FrEducationCardObject> frEducationCardObjects = frEducationCardObjectMapper.findSettCourseEdu(eduId);
        return frEducationCardObjects;
    }

    /**
     * 查看该用户是否买了该课程
     *
     * @param courseId
     * @param clientId
     * @param cardId
     * @param code
     * @param num
     * @return
     */
    public List<Map<String, Object>> findCourseByClientId(String courseId, String clientId,
                                                          String cardId, String code,
                                                          String num, String shopId) {
        List<Map<String, Object>> list = projectOrderMapper.findCourseByClientId(courseId, clientId, cardId, code, num, shopId);
        return list;
    }

    /**
     * 保存修改对课程的修改
     *
     * @param map
     */
    public void updateProjectOrder(Map<String, String> map) throws Exception {
        String orderId = UUIDUtils.generateGUID();
        String endAmt = map.get("endAmt");
        String id = map.get("id");
        ProjectOrder projectOrder = projectOrderMapper.selectById(id);
        FrEducationMemberOrder frEducationMemberOrder = JSONObject.parseObject(map.get("cardObject"), FrEducationMemberOrder.class);
        try {
            if (null == frEducationMemberOrder) {
                throw new Exception("提交出错，请重试。");
            }
            frEducationMemberOrder.setId(orderId);
            projectOrder.setAllCount(projectOrder.getAllCount() - Integer.parseInt(endAmt));
            //并且保存课程现在的值
            int count = projectOrder.getAllCount();
            projectOrder.setTotalPrice(projectOrder.getPrice() + count);

            this.insertEducationMemberOrder(frEducationMemberOrder, map, 1);
            //修改课程数
            projectOrderMapper.updateAllColumnById(projectOrder);


        } catch (NumberFormatException e) {
            throw new Exception("预约失败，请重试。。");
        }

    }

    /**
     * 计算FrEducationMemberOrder 再保存
     *
     * @param frEducationMemberOrder
     * @param map
     */
    private void insertEducationMemberOrder(FrEducationMemberOrder frEducationMemberOrder, Map<String, String> map, Integer inte) throws Exception {


        //计算结束时间
        Calendar cl = Calendar.getInstance();
        cl.setTime(frEducationMemberOrder.getBeginDateReal());
        cl.add(Calendar.MINUTE, +frEducationMemberOrder.getTotalTime());
        frEducationMemberOrder.setEndDateReal(cl.getTime());


        //判断当前用户是否预约，否则新增
        FrEducationClientInfo frEducationClientInfo = JSONObject.parseObject(map.get("reserveClientInfo"), FrEducationClientInfo.class);
        if (null == frEducationClientInfo) {
            throw new Exception("预约失败，请重试");
        }

        //关联clientInfo
        String clientInfoId = UUIDUtils.generateGUID();
        FrEducationClientInfo frEducationClientInfo1 = null;
        frEducationClientInfo1 = frEducationClientInfoMapper.findByEduIdForNormal(frEducationClientInfo.getEducationId(), frEducationClientInfo.getMemberId());

        //判断是否已经存在
        if (null != frEducationClientInfo1) {
            //查看当前的memberOrder 是否存在
            FrEducationMemberOrder frEducationMemberOrder1 = frEducationMemberOrderMapper.findMemberOrderNormal(frEducationClientInfo1.getEducationId(), frEducationClientInfo1.getId());
            if (null != frEducationMemberOrder1) {
                //已经存在 ，则不能重复交钱扣款
                throw new Exception("该会员已经付款扣费,请勿重复提交");
            }
            clientInfoId = frEducationClientInfo1.getId();
            frEducationClientInfo1.setReserveStatus(1);
            frEducationClientInfoMapper.updateAllColumnById(frEducationClientInfo1);
            //赋值给
            frEducationClientInfo = frEducationClientInfo1;
        } else {

            //判断当前课程预约人数是否已经满员
            FrEducation frEducation = frEducationPublicMapper.selectById(frEducationMemberOrder.getEducationId());
            Integer a = 0;
            Map<String, String> mapValue = frEducationClientInfoMapper.findClientCountByEduId(frEducationMemberOrder.getEducationId(), "1");
            a += Integer.parseInt(String.valueOf(mapValue.get("value")));
            mapValue = frEducationClientInfoMapper.findClientCountByEduId(frEducationMemberOrder.getEducationId(), "2");
            a += Integer.parseInt(String.valueOf(mapValue.get("value")));
            if(frEducation.getReserveTotalNum() <= a){
                throw new Exception("抱歉，该预约人数已满！");
            }
            frEducationClientInfo.setId(clientInfoId);
            frEducationClientInfoMapper.insert(frEducationClientInfo);
        }

        //给订单添加id
        frEducationMemberOrder.setClientInfoId(clientInfoId);
        //保存订单
        frEducationMemberOrderMapper.insert(frEducationMemberOrder);
        //保存订单
        if(inte == 2){
            this.saveOrderEntityByDetail(map, frEducationMemberOrder, frEducationClientInfo);
        }
        if(inte == 3 || inte == 1){
            this.saveOrderEntity(frEducationMemberOrder, map, frEducationClientInfo);
        }

    }
    /**
     *
     * 新增销售订单表
     * @param frEducationMemberOrder
     * @param frEducationClientInfo
     * @param orderObj
     */
    private void saveOrderEntity(FrEducationMemberOrder frEducationMemberOrder, Map orderObj, FrEducationClientInfo frEducationClientInfo){
        Date date = new Date();
        MoneyReport moneyReport = new MoneyReport();
        ConsumeAccountOrder consumeAccountOrder = new ConsumeAccountOrder();
        ConsumeAccountInfo consumeAccountInfo = new ConsumeAccountInfo();
        String userTypeName = "团教私教管理";
        String tableName = "fr_education_client_info";
        FrEducation frEducation = frEducationPublicMapper.selectById(frEducationClientInfo.getEducationId());

        moneyReport.setCustomerCode(frEducation.getCustomerCode());
        moneyReport.setOrderNumber(frConsumeMoneyOrderService.getOrderIdByTime());
        moneyReport.setUseType(1);
        moneyReport.setUseTypeName(userTypeName);
        moneyReport.setTableId(frEducationMemberOrder.getClientInfoId());
        moneyReport.setTableName(tableName);
        moneyReport.setCustomerId(frEducationClientInfo.getMemberId());
        moneyReport.setMemberCardId(frEducationMemberOrder.getMemberCardId());
        moneyReport.setName(frEducationClientInfo.getMemberName());
        moneyReport.setPhone(frEducationClientInfo.getMobile());
        moneyReport.setCreateName(frEducationMemberOrder.getCreateName());
        moneyReport.setCreateTime(date);
        moneyReport.setId(UUIDUtils.generateGUID());
        moneyReport.setShopId(frEducation.getShopId());
        moneyReport.setSdadiumId("--");

        //
        consumeAccountOrder.setCustomerCode(frEducation.getCustomerCode());
        consumeAccountOrder.setOrderNumber(frConsumeMoneyOrderService.getOrderIdByTime());
        consumeAccountOrder.setTableId(frEducationMemberOrder.getClientInfoId());
        consumeAccountOrder.setTableName(tableName);
        consumeAccountOrder.setName(frEducationClientInfo.getMemberName());
        consumeAccountOrder.setPhone(frEducationClientInfo.getMobile());
        consumeAccountOrder.setCreateName(frEducationMemberOrder.getCreateName());
        consumeAccountOrder.setCreateTime(date);
        consumeAccountOrder.setId(UUIDUtils.generateGUID());
        consumeAccountOrder.setShopId(frEducation.getShopId());
        consumeAccountOrder.setSdadiumId("--");
        //
        consumeAccountInfo.setId(UUIDUtils.generateGUID());
        consumeAccountInfo.setCustomerCode(frEducation.getCustomerCode());
        consumeAccountInfo.setTableId(frEducationMemberOrder.getClientInfoId());
        consumeAccountInfo.setConsumeAccountOrderId(consumeAccountOrder.getId());
        consumeAccountInfo.setOrderNumber(frConsumeMoneyOrderService.getOrderIdByTime());
        consumeAccountInfo.setTableName(tableName);
        consumeAccountInfo.setCustomerId(frEducationClientInfo.getMemberId());
        consumeAccountInfo.setMemberCardId(frEducationMemberOrder.getMemberCardId());
        consumeAccountInfo.setProjectId(frEducation.getCourseId());
        consumeAccountInfo.setStartTime(frEducationMemberOrder.getBeginDateReal());
        consumeAccountInfo.setEndTime(frEducationMemberOrder.getEndDateReal());
        consumeAccountInfo.setName(frEducationClientInfo.getMemberName());
        consumeAccountInfo.setPhone(frEducationClientInfo.getMobile());
        consumeAccountInfo.setCreateName(frEducationMemberOrder.getCreateName());
        consumeAccountInfo.setCreateTime(date);
        consumeAccountInfo.setShopId(frEducation.getShopId());
        consumeAccountInfo.setSdadiumId("--");


        frConsumeMoneyOrderService.saveMoneyReport(moneyReport);
        frConsumeMoneyOrderService.saveConsumeAccountInfo(consumeAccountInfo);
        frConsumeMoneyOrderService.saveConsumeAccountOrder(consumeAccountOrder);
    }

    /**
     * 保存修改对课程的修改
     *
     * @param map
     */
    public void saveEduMemberOrder(Map<String, String> map) throws Exception {
        String orderId = UUIDUtils.generateGUID();
        FrEducationMemberOrder frEducationMemberOrder = JSONObject.parseObject(map.get("cardObject"), FrEducationMemberOrder.class);
        try {
            if (null == frEducationMemberOrder) {
                throw new Exception("提交出错，请重试。");
            }
            frEducationMemberOrder.setId(orderId);
            //保存订单
            this.insertEducationMemberOrder(frEducationMemberOrder, map, 3);
        } catch (NumberFormatException e) {
            throw new Exception("预约失败，请重试。。");
        }

    }

    private void saveOrderEntityByDetail(Map<String, String> map, FrEducationMemberOrder frEducationMemberOrder, FrEducationClientInfo frEducationClientInfo){
        MoneyReport moneyReport = JSONObject.parseObject(map.get("moneyReport"), MoneyReport.class);
        ConsumeAccountOrder consumeAccountOrder = JSONObject.parseObject(map.get("consumeAccountOrder"), ConsumeAccountOrder.class);
        ConsumeAccountInfo consumeAccountInfo = JSONObject.parseObject(map.get("consumeAccountInfo"), ConsumeAccountInfo.class);
        FrEducation frEducation = frEducationPublicMapper.selectById(frEducationClientInfo.getEducationId());
        String userTypeName = "团教私教管理";
        String tableName = "fr_education_client_info";
        Date date = new Date();
        moneyReport.setOrderNumber(frConsumeMoneyOrderService.getOrderIdByTime());
        moneyReport.setUseTypeName(userTypeName);
        moneyReport.setTableId(frEducationMemberOrder.getClientInfoId());
        moneyReport.setTableName(tableName);
        moneyReport.setCustomerId(frEducationClientInfo.getMemberId());
        moneyReport.setMemberCardId(frEducationMemberOrder.getMemberCardId());
        moneyReport.setName(frEducationClientInfo.getMemberName());
        moneyReport.setPhone(frEducationClientInfo.getMobile());
        moneyReport.setCreateTime(date);
        moneyReport.setId(UUIDUtils.generateGUID());
        moneyReport.setShopId(frEducation.getShopId());
        moneyReport.setSdadiumId("--");
        //
        consumeAccountOrder.setOrderNumber(frConsumeMoneyOrderService.getOrderIdByTime());
        consumeAccountOrder.setTableId(frEducationMemberOrder.getClientInfoId());
        consumeAccountOrder.setTableName(tableName);
        consumeAccountOrder.setName(frEducationClientInfo.getMemberName());
        consumeAccountOrder.setPhone(frEducationClientInfo.getMobile());
        consumeAccountOrder.setCreateTime(date);
        consumeAccountOrder.setCreateName(frEducationMemberOrder.getCreateName());
        consumeAccountOrder.setId(UUIDUtils.generateGUID());
        consumeAccountOrder.setShopId(frEducation.getShopId());
        consumeAccountOrder.setSdadiumId("--");
        //
        consumeAccountInfo.setId(UUIDUtils.generateGUID());
        consumeAccountInfo.setTableId(frEducationMemberOrder.getClientInfoId());
        consumeAccountInfo.setConsumeAccountOrderId(consumeAccountOrder.getId());
        consumeAccountInfo.setOrderNumber(frConsumeMoneyOrderService.getOrderIdByTime());
        consumeAccountInfo.setTableName(tableName);
        consumeAccountInfo.setCustomerId(frEducationClientInfo.getMemberId());
        consumeAccountInfo.setMemberCardId(frEducationMemberOrder.getMemberCardId());
        consumeAccountInfo.setProjectId(frEducation.getCourseId());
        consumeAccountInfo.setStartTime(frEducationMemberOrder.getBeginDateReal());
        consumeAccountInfo.setEndTime(frEducationMemberOrder.getEndDateReal());
        consumeAccountInfo.setName(frEducationClientInfo.getMemberName());
        consumeAccountInfo.setPhone(frEducationClientInfo.getMobile());
        consumeAccountInfo.setCreateName(frEducationMemberOrder.getCreateName());
        consumeAccountInfo.setCreateTime(date);
        consumeAccountInfo.setShopId(frEducation.getShopId());
        consumeAccountInfo.setSdadiumId("--");

        frConsumeMoneyOrderService.saveMoneyReport(moneyReport);
        frConsumeMoneyOrderService.saveConsumeAccountInfo(consumeAccountInfo);
        frConsumeMoneyOrderService.saveConsumeAccountOrder(consumeAccountOrder);

    }

    /**
     * 更新卡的权益
     *
     * @param map
     */
    public void updateCardDetail(Map<String, String> map) throws Exception {
        String orderId = UUIDUtils.generateGUID();
        List<String> list = new ArrayList<>();
        Double price = 0.0;
        //数组
        JSONArray cardOrderPayModeDate = JSONArray.parseArray(map.get("cardOrderPayModeDate"));
        JSONArray cardOrderDetailList = JSONArray.parseArray(map.get("cardOrderDetailList"));
        FrCardOrderDatail buyEduDetail = JSONObject.parseObject(map.get("buyEduDetail"), FrCardOrderDatail.class);
        FrEducationMemberOrder frEducationMemberOrder = JSONObject.parseObject(map.get("cardObject"), FrEducationMemberOrder.class);
        if (null == frEducationMemberOrder) {
            throw new Exception("提交出错，请重试。");
        }
        //修改支付方式
        FrEducationClientInfo frEducationClientInfo = frEducationClientInfoMapper.selectById(frEducationMemberOrder.getClientInfoId());
        if(null == frEducationClientInfo){
            throw new Exception("提交出错，请重试。");
        }

        frEducationMemberOrder.setId(orderId);
        //保存订单
        this.insertEducationMemberOrder(frEducationMemberOrder, map, 2);
        //订单支付方式及金额
        for (Object object : cardOrderPayModeDate) {
            FrCardOrderPayMode frCardOrderPayMode = JSONObject.parseObject(JSON.toJSONString(object), FrCardOrderPayMode.class);
            frCardOrderPayMode.setId(UUIDUtils.generateGUID());
            frCardOrderPayMode.setOrderId(orderId);
            iFrCardOrderPayModeService.insert(frCardOrderPayMode);
            list.add(setPayType(String.valueOf(frCardOrderPayMode.getPayMode())));
            price += frCardOrderPayMode.getPayPrice();
        }
        //付款方式
        frEducationClientInfo.setSettleType(StringUtils.join(list,","));
        frEducationClientInfo.setDeductionBalance(String.valueOf(price));
        frEducationClientInfoMapper.updateAllColumnById(frEducationClientInfo);

        //资金明细表
        for (Object object : cardOrderDetailList) {
            JSONObject jsonObject = (JSONObject) object;
            FrCardOrderPriceDatail frCardOrderPriceDatail = JSONObject.parseObject(JSON.toJSONString(jsonObject.getJSONObject("orderdetailPrice")), FrCardOrderPriceDatail.class);
            FrCardOrderDatail frCardOrderDatail = JSONObject.parseObject(JSON.toJSONString(jsonObject.getJSONObject("orderDetail")), FrCardOrderDatail.class);
            String priceID = UUIDUtils.generateGUID();
            frCardOrderPriceDatail.setOrderId(orderId);
            frCardOrderPriceDatail.setId(priceID);
            iFrCardOrderPriceDatailService.insert(frCardOrderPriceDatail);

            frCardOrderDatail.setId(UUIDUtils.generateGUID());
            frCardOrderDatail.setOrderId(orderId);
            frCardOrderDatail.setOrderPriceId(priceID);
            iFrCardOrderDatailService.insert(frCardOrderDatail);
        }
        //权益
        buyEduDetail.setId(UUIDUtils.generateGUID());
        buyEduDetail.setOrderId(orderId);
        buyEduDetail.setOrderPriceId(orderId);
        iFrCardOrderDatailService.insert(buyEduDetail);


    }

    /**
     * 设置付款类型
     */
    private String setPayType(String type){
        String back = "";
        //1、支付宝；2、刷卡；3、微信；4、现金；5、转账；6、花呗；7、其他
        switch (type){
            case "1":
                back = "支付宝";
                break;
            case "2":
                back = "刷卡";
                break;
            case "3":
                back = "微信";
                break;
            case "4":
                back = "现金";
                break;
            case "5":
                back = "转账";
                break;
            case "6":
                back = "花呗";
                break;
            default:
                back = "其他";

        }
        return back;

    }


    /**
     * 开始上课
     *
     * @param map
     */
    public void startEduClass(Map<String, String> map) {
        FrEducationClassOrder frEducationClassOrder = JSONObject.parseObject(map.get("classOrder"), FrEducationClassOrder.class);
        FrEducation frEducation = null;
        frEducationClassOrder.setId(UUIDUtils.generateGUID());
        String eduId = frEducationClassOrder.getEducationId();
        String clientInfoId = "";
        //开始计算总人数，失约人数，完成预约人数
        String condition = "education_id='" + eduId + "'";
        //查询全部的信息用户
        List<FrEducationClientInfo> list = frEducationClientInfoMapper.selectList(new EntityWrapper<FrEducationClientInfo>().where(condition));
        frEducation = frEducationPublicMapper.selectById(eduId);
        Integer one = 0, two = 0, three = 0;
        double price = 0.0;
        for (FrEducationClientInfo frEducationClientInfo : list) {
            clientInfoId = frEducationClientInfo.getId();
            FrEducationMemberOrder frEducationMemberOrder = new FrEducationMemberOrder();
            if (frEducationClientInfo.getReserveStatus() == 1) {//预约状态 1/已预约 0/已取消 2/待确认
                //已预约
                // 可用/作废/完成   1/0/2
                one++;
                frEducationMemberOrder.setClientInfoId(clientInfoId);
                frEducationMemberOrder.setEducationId(eduId);
                frEducationMemberOrder.setStatus(1);
                frEducationMemberOrder = frEducationMemberOrderMapper.selectOne(frEducationMemberOrder);
                if(null == frEducationMemberOrder){//未消费

                    //已预约，但是没付钱，也没取消，则将冻结
                    this.freezeEduConfigClient(String.valueOf(frEducation.getType()),
                            frEducation.getCustomerCode(), frEducationClientInfo, frEducation.getShopId(), frEducation.getShopName());
                    //frSettingInfoMapper
                    continue;
                }
                frEducationMemberOrder.setStatus(2);
                price += frEducationMemberOrder.getPrice();
                frEducationMemberOrderMapper.updateAllColumnById(frEducationMemberOrder);
            }
            if (frEducationClientInfo.getReserveStatus() == 2) {
                //待确认
                one++;
                frEducationMemberOrder.setClientInfoId(clientInfoId);
                frEducationMemberOrder.setEducationId(eduId);
                frEducationMemberOrder = frEducationMemberOrderMapper.selectOne(frEducationMemberOrder);
                if (null != frEducationMemberOrder) {
                    price += frEducationMemberOrder.getPrice();
                    frEducationMemberOrder.setStatus(2);
                    frEducationMemberOrderMapper.updateAllColumnById(frEducationMemberOrder);
                }
                frEducationClientInfo.setReserveStatus(1);
                frEducationClientInfoMapper.updateAllColumnById(frEducationClientInfo);
            }
            if (frEducationClientInfo.getReserveStatus() == 0) {
                two++;
            }
        }

        frEducation.setStatus(1);
        //three
        three = one + two;
        frEducationClassOrder.setTotalNum(three);
        frEducationClassOrder.setFinishNum(one);
        frEducationClassOrder.setUndoneNum(two);
        frEducationClassOrder.setTotalGain(price);
        frEducationPublicMapper.updateAllColumnById(frEducation);
        frEducationClassOrderMapper.insert(frEducationClassOrder);


    }


    /**
     *
     * 冻结用户
     */
    private void freezeEduConfigClient(String eduType, String code, FrEducationClientInfo frEducationClientInfo, String shopId, String shopName){
        FrEducationFreezeClient frEducationFreezeClient = new FrEducationFreezeClient();
        FrClient frClient = frClientMapper.selectById(frEducationClientInfo.getReserveClientId());
        String level = frClient.getLevelId();
        FrLevel frLevel = frLevelMapper.selectById(level);
        if(null == frLevel){
            return ;
        }
        String price = "";//
        Date now = new Date();
        Map<String, String> map = new HashMap<>();

        switch (frLevel.getLevelName()) {
            case "普通会员":
                map = frEducationPublicMapper.findSettingCourse(eduType, code, "groupPTJDJY");
                if(null != map){
                    price = map.get("value");
                }
                break;
            case "银卡会员":
                map = frEducationPublicMapper.findSettingCourse(eduType, code, "groupYKJDJY");
                if(null != map){
                    price = map.get("value");
                }
                break;
            case "钻石会员":
                map = frEducationPublicMapper.findSettingCourse(eduType, code, "groupZKJDJY");
                if(null != map){
                    price = map.get("value");
                }
                break;
            case "金卡会员":
                map = frEducationPublicMapper.findSettingCourse(eduType, code, "groupJKJDJY");
                if(null != map){
                    price = map.get("value");
                }
                break;
            default:
               return ;
        }
        //frSettingInfoMapper
        eduType = StringUtils.equals(eduType, "0")? "2" : "1";
        //
        Map<String, String> map1 = frEducationPublicMapper.findSettingCourse(eduType, code, "groupHYN");//多少天
        Map<String, String> map2 = frEducationPublicMapper.findSettingCourse(eduType, code, "groupHYNType");// 时间单位
        Map<String, String> map3 = frEducationPublicMapper.findSettingCourse(eduType, code, "groupYYWQX");//多少次预约未取消未消费则
        Map<String, String> map4 = frEducationPublicMapper.findSettingCourse(eduType, code, "groupJZYY");//禁止预约 时间
        Map<String, String> map5 = frEducationPublicMapper.findSettingCourse(eduType, code, "groupJZYYType");//禁止预约 时间单位

        if(!(null != map1 && null != map2 && null != map3 && null != map4 && null != map5)){
            return;
        }
        //计算冻结结束时间
        Integer map4Value = Integer.parseInt(map4.get("value"));
        String map5Value = map5.get("value");
        Calendar cl = Calendar.getInstance();
        cl.setTime(now);
        if(StringUtils.equals("1", map5Value)){
            cl.add(Calendar.DATE, +map4Value);
        }
        if(StringUtils.equals("2", map5Value)){
            cl.add(Calendar.DATE, +(map4Value * 7));
        }
        if(StringUtils.equals("3", map5Value)){
            cl.add(Calendar.YEAR, +map4Value);
        }
        if(StringUtils.equals("4", map5Value)){
            cl.add(Calendar.MONTH, +map4Value);
        }


        frEducationFreezeClient = new FrEducationFreezeClient();
        frEducationFreezeClient.setClientId(frEducationClientInfo.getReserveClientId());
        frEducationFreezeClient.setEduType(Integer.parseInt(eduType));
        frEducationFreezeClient = frEducationFreezeClientMapper.selectOne(frEducationFreezeClient);
        if(null != frEducationFreezeClient){
            //判断时间内是否包含
            if(now.getTime() - frEducationFreezeClient.getEndDate().getTime() > 0){
                frEducationFreezeClientMapper.deleteById(frEducationFreezeClient.getId());
                return;
            }
            frEducationFreezeClient.setCount(frEducationFreezeClient.getCount() + 1);
            //判断
            if(frEducationFreezeClient.getTotalCount() <= frEducationFreezeClient.getCount()){
                frEducationFreezeClient.setFreezeDate(now);//开始时间
                frEducationFreezeClient.setUnFreezeDate(cl.getTime());
                frEducationFreezeClient.setUse(false); //不可用
            }
            frEducationFreezeClientMapper.updateAllColumnById(frEducationFreezeClient);
        }
        //设置开始时间
        frEducationFreezeClient = new FrEducationFreezeClient();
        map4Value = Integer.parseInt(map1.get("value"));
        map5Value = map2.get("value");
        cl = Calendar.getInstance();
        cl.setTime(now);
        if(StringUtils.equals("1", map5Value)){
            cl.add(Calendar.DATE, +map4Value);
        }
        if(StringUtils.equals("2", map5Value)){
            cl.add(Calendar.DATE, +(map4Value * 7));
        }
        if(StringUtils.equals("3", map5Value)){
            cl.add(Calendar.YEAR, +map4Value);
        }
        if(StringUtils.equals("4", map5Value)){
            cl.add(Calendar.MONTH, +map4Value);
        }
        frEducationFreezeClient.setBeginDate(now);
        frEducationFreezeClient.setEndDate(cl.getTime());
        frEducationFreezeClient.setCount(1);
        frEducationFreezeClient.setTotalCount(Integer.parseInt(map3.get("value")));
        frEducationFreezeClient.setClientId(frEducationClientInfo.getReserveClientId());
        frEducationFreezeClient.setClientName(frEducationClientInfo.getReserveClientName());
        frEducationFreezeClient.setMobile(frEducationClientInfo.getMobile());
        frEducationFreezeClient.setCreateDate(now);
        frEducationFreezeClient.setEduType(Integer.parseInt(eduType));
        frEducationFreezeClient.setPrice(Double.parseDouble(price));
        frEducationFreezeClient.setShopId(shopId);
        frEducationFreezeClient.setShopName(shopName);
        frEducationFreezeClient.setId(UUIDUtils.generateGUID());
        frEducationFreezeClientMapper.insert(frEducationFreezeClient);
    }


    /**
     * 冲销
     *
     * @param map
     */
    public void cancelMemberOrder(Map<String, String> map) {
        String id = map.get("id");
        Double eduClassSalesNum = Double.parseDouble(map.get("eduClassSalesNum"));
        FrEducationMemberOrder frEducationMemberOrder = frEducationMemberOrderMapper.selectById(id);
        FrEducationClientInfo frEducationClientInfo = frEducationClientInfoMapper.selectById(frEducationMemberOrder.getClientInfoId());
        frEducationMemberOrder.setStatus(0);
        //frEducationMemberOrder.setUse(false);
        frEducationMemberOrderMapper.updateAllColumnById(frEducationMemberOrder);
        //更改用户的状态
        //冲销后，设置成null
        frEducationClientInfo.setReserveStatus(null);
        frEducationClientInfoMapper.updateAllColumnById(frEducationClientInfo);

        //再添加一条去抵消
        FrCardOrderDatail buyEduDetail = JSONObject.parseObject(map.get("buyEduDetail"), FrCardOrderDatail.class);
        Map<String, Object> cardOrder = frEducationPublicMapper.findCardOrderDetailFirst(frEducationMemberOrder.getMemberCardId());
        //
        try {
            buyEduDetail.setClientId((String) cardOrder.get("clientId"));
            buyEduDetail.setCardId(frEducationMemberOrder.getMemberCardId());
            buyEduDetail.setOrderRightsNum(eduClassSalesNum);
            buyEduDetail.setOrderAmt(Double.parseDouble(String.valueOf(cardOrder.get("orderAmt"))) + eduClassSalesNum);
            buyEduDetail.setId(UUIDUtils.generateGUID());
            buyEduDetail.setOrderId(frEducationMemberOrder.getId());
            iFrCardOrderDatailService.insert(buyEduDetail);
        } catch (NumberFormatException e) {

        }
    }

    /**
     * 查询教练的课程
     */
    public List<Map<String, Object>> findEducationCoachList(String shopId, String executeDate, String sdaduimId, String CustomerCode, Integer teachType) {
        if (StringUtils.isBlank(executeDate)) {
            //获取当天时间
            executeDate = DateUtil.dateToString(new Date(), "yyyy-MM-dd");
        }else{
            executeDate = executeDate.replace("Z", " UTC");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            Date d = null;
            try {
                d = format.parse(executeDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            executeDate = DateUtil.dateToString(d, "yyyy-MM-dd");
        }
        List<Map<String, Object>> educations = frEducationPublicMapper.findEducationCoachList(shopId, executeDate, sdaduimId, CustomerCode, teachType);

        return educations;
    }

    /**
     * 查询教室的课程
     */
    public List<Map<String, Object>> findEducationRoomList(String shopId, String executeDate, String sdaduimId, String CustomerCode, Integer teachType) {
        if (StringUtils.isBlank(executeDate)) {
            //获取当天时间
            executeDate = DateUtil.dateToString(new Date(), "yyyy-MM-dd");
        }else{
            executeDate = executeDate.replace("Z", " UTC");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            Date d = null;
            try {
                d = format.parse(executeDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            executeDate = DateUtil.dateToString(d, "yyyy-MM-dd");
        }
        List<Map<String, Object>> educations = frEducationPublicMapper.findEducationRoomList(shopId, executeDate, sdaduimId, CustomerCode, teachType);

        return educations;
    }


    /**
     * 查询课程列表 下拉框
     * @param shopId
     * @param code
     * @param teachType
     * @return
     */
    public List<Map<String, Object>> findCourseList(String shopId, String code, Integer teachType){
        List<Map<String, Object>> list = null;
        if(teachType == 0){
            list = frEducationPublicMapper.findCoursePublicList(shopId, code);
        }
        //私教下拉框
      //  findCoursePrivateList();
        return list;
    }


    /**
     * 排课
     * @param map
     * @return
     */
    public void saveEducation(Map<String, String> map) throws Exception {
        FrEducation frEducation = JSONObject.parseObject(map.get("education"), FrEducation.class);
        if(null == frEducation){
            throw new Exception("排课失败，请重试");
        }
        frEducation.setId(UUIDUtils.generateGUID());
        //添加场馆

        //config
        FrEducationConfig frEducationConfig = JSONObject.parseObject(map.get("eduConfig"), FrEducationConfig.class);
        if(null == frEducationConfig){
            throw new Exception("排课失败，请重试");
        }
        frEducationConfig.setId(UUIDUtils.generateGUID());
        frEducationConfig.setEducationId(frEducation.getId());
        //member object
        FrEducationReserveObject frEducationReserveObject = JSONObject.parseObject(map.get("eduReserveObject"), FrEducationReserveObject.class);
//        if(null == frEducationReserveObject){
//            throw new Exception("排课失败，请重试");
//        }
        if(null != frEducationReserveObject){
            frEducationReserveObject.setId(UUIDUtils.generateGUID());
            frEducationReserveObject.setEducationConfigId(frEducationConfig.getId());
        }
        //会员卡设置
        JSONArray cardObjects = JSONArray.parseArray((String) map.get("cardObject")) ;
        if(null != cardObjects){
            for(Object object: cardObjects){
                FrEducationCardObject frEducationCardObject = JSONObject.parseObject(JSON.toJSONString(object), FrEducationCardObject.class);
                frEducationCardObject.setId(UUIDUtils.generateGUID());
                frEducationCardObject.setEducationConfigId(frEducationConfig.getId());
                frEducationCardObjectMapper.insert(frEducationCardObject);
            }
        }
        //设置场馆
        if(frEducation.getType() == 0){
            frEducation.setSdaduimId("fed61d300684ac6c");
            frEducation.setSdaduimName("健身团教");
        }
        if(frEducation.getType() == 1){
            frEducation.setSdaduimId("10c05bec9a74681f");
            frEducation.setSdaduimName("健身私教");
        }



        //保存
        frEducationPublicMapper.insert(frEducation);
        if(null != frEducationReserveObject){
            frEducationReserveObjectMapper.insert(frEducationReserveObject);
        }

        frEducationConfigMapper.insert(frEducationConfig);

    }


    /**
     *
     * @param shopId
     * @param beginDate
     * @param endDate
     * @param code
     * @return
     */
    public List<Map<String, Object>> searchSettlement(String shopId, String searchInput, String beginDate, String endDate, String code, Integer eduType){
        return frEducationPublicMapper.searchSettlement(shopId, searchInput, beginDate, endDate, code, eduType);
    }

    /**
     *查询课程消费明细情况
     * @param shopId
     * @param beginDate
     * @param endDate
     * @param code
     * @return
     */
    public List<Map<String, Object>> findConsumeClass(String beginDate, String endDate, String shopId, String searchCourse, String  searchCoach, String code, Integer eduType){
        return frEducationPublicMapper.findConsumeClass(beginDate, endDate, shopId, searchCourse, searchCoach, code, eduType);

    }

    /**
     *查询个人消费情况
     * @param shopId
     * @param beginDate
     * @param endDate
     * @param code
     * @return
     */
    public List<Map<String, Object>> findConsumeMember(String beginDate, String endDate, String shopId, String searchCourse, String  searchCoach, String code, String cardType, String cardName, Integer eduType){
        return frEducationPublicMapper.findConsumeMember(beginDate, endDate, shopId, searchCourse, searchCoach, code, cardType, cardName, eduType);
    }

    /**
     *查询个人消费情况
     * @return
     */
    public Map<String, Object> findConsumeCondition(){
        Map<String, Object> map = new HashMap<>();
        List<Map<String, String>> list1 = frEducationPublicMapper.findConsumeConditionCardType();
        List<Map<String, String>> list2 = frEducationPublicMapper.findConsumeConditionCardName();
        if(null == list1){
            list1 = new ArrayList<>();
        }else{
            for(int i = 0; i < list1.size(); i++){
                Map<String, String> m = list1.get(i);
                if(StringUtils.isBlank(m.get("cardType"))){
                    list1.remove(i);
                }
            }

        }
        if(null == list2){
            list2 = new ArrayList<>();
        }else{
            for(int i = 0; i < list2.size(); i++){
                Map<String, String> m = list2.get(i);
                if(StringUtils.isBlank(m.get("cardName"))){
                    list2.remove(i);
                }
            }

        }
        map.put("cardType", list1);
        map.put("cardName", list2);
        return map;
    }

    /**
     *查询个人消费情况
     * @return
     */
    public List<Map<String, Object>> findMemberFreezeList(String shopId, String code, String beginDate, String endDate,String searchInput){
        return frEducationPublicMapper.findMemberFreezeList(shopId, code, beginDate, endDate, searchInput);
    }


    /**
     *查询管理统计
     * @return
     */
    public Map<String, Object> findManageCount(String shopId, String beginDate, String endDate){

        return frEducationPublicMapper.findManageCount(shopId, beginDate, endDate);
    }


    /**
     *查询管理统计
     * @return
     */
    public List<Map<String, Object>> getGroupRoomByShopId(String shopId, String code){
        return frEducationPublicMapper.getGroupRoomByShopId(shopId, code);

    }

    /**
     *复制课程排课
     * @return
     */
    public void findEducationToCopy(Map<String, String> map) throws Exception{
        String beginDate = map.get("beginDateOld");
        String endDateOld = map.get("endDateOld");
        String eduType = map.get("eduType");
        String coachId = map.get("coachId");
        String courseId = map.get("courseId");
        String shopId = map.get("shopId");
        String selectToCoachId = map.get("selectToCoachId");
        String selectToCoachName = map.get("selectToCoachName");
        Integer stepTime = Integer.parseInt(map.get("stepTime"));
        Date date = new Date();
        List<Map<String, String>> list = frEducationPublicMapper.findEducationToCopy(beginDate, endDateOld, coachId, courseId, eduType, shopId);
        String eduId = "";
        String configId = "";
//        String reserveId = "";
//        String cardSettleId = "";
        for(Map<String, String> obj: list){
            eduId = obj.get("eduId");
            configId = obj.get("configId");
//            reserveId = obj.get("reserveId");
//            cardSettleId = obj.get("cardSettleId");
            FrEducation frEducation = frEducationPublicMapper.selectById(eduId);
            frEducation.setExecuteDatePlan(dateToAddDate(frEducation.getExecuteDatePlan(), stepTime));
            frEducation.setBeginDatePlan(dateToAddDate(frEducation.getBeginDatePlan(), stepTime));
            frEducation.setEndDatePlan(dateToAddDate(frEducation.getEndDatePlan(), stepTime));
            frEducation.setCoachId(selectToCoachId);
            frEducation.setCoachName(selectToCoachName);
            frEducation.setId(UUIDUtils.generateGUID());
            frEducation.setCreateTime(date);
            frEducation.setUpdateTime(null);
            frEducation.setStatus(0);
            //
            FrEducationConfig frEducationConfig = frEducationConfigMapper.selectById(configId);
            frEducationConfig.setId(UUIDUtils.generateGUID());
            frEducationConfig.setEducationId(frEducation.getId());
            //reserve
            FrEducationReserveObject frEducationReserveObject = new FrEducationReserveObject();
            frEducationReserveObject.setEducationConfigId(configId);
            frEducationReserveObject = frEducationReserveObjectMapper.selectOne(frEducationReserveObject);
            if(null != frEducationReserveObject){
                frEducationReserveObject.setId(UUIDUtils.generateGUID());
                frEducationReserveObject.setEducationConfigId(frEducationConfig.getId());
            }
            //
            List<FrEducationCardObject> frEducationCardObjects = null;
            String condition = "education_config_id={0}";
            frEducationCardObjects = frEducationCardObjectMapper.selectList(new EntityWrapper<FrEducationCardObject>().where(condition, configId));
            if(null != frEducationCardObjects){
                for(FrEducationCardObject frEducationCardObject: frEducationCardObjects){
                    frEducationCardObject.setId(UUIDUtils.generateGUID());
                    frEducationCardObject.setEducationConfigId(frEducationConfig.getId());
                    frEducationCardObjectMapper.insert(frEducationCardObject);
                }
            }

            frEducationPublicMapper.insert(frEducation);
            frEducationConfigMapper.insert(frEducationConfig);
            if(null != frEducationReserveObject){
                frEducationReserveObjectMapper.insert(frEducationReserveObject);
            }


        }

    }

    private Date dateToAddDate(Date date, Integer stepTime){
        //计算结束时间
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(Calendar.DATE, +stepTime);
        return cl.getTime();
    }


    /**
     * 一周的最后一天
     * @param date
     * @param step 加几天就是星期几
     */
    private Date getOneDayOfWeek(Date date, Integer step){
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + step);
        return c.getTime();
    }

    /**
     * 查询一周的课程
     * @param shopId
     * @param code
     * @param coachId
     * @param roomId
     * @return
     */
    public List<Map<String, Object>> findEduListByWeek(String shopId, String code, String coachId, String roomId, String eduType, String beginDate, String endDate){
        return frEducationPublicMapper.findEduListByMonthWeek(shopId, beginDate, endDate, coachId, code, eduType, roomId);
    }
    /**
     * 查询一月的课程
     * @param shopId
     * @param code
     * @param coachId
     * @param beginDate
     * @param endDate
     * @return
     */
    public List<Map<String, Object>> findEduListByMonth(String shopId, String code, String coachId, String beginDate, String endDate, String eduType){
        return frEducationPublicMapper.findEduListByMonthWeek(shopId, beginDate, endDate, coachId, code, eduType, "");
    }


    /**
     * 删除 假删除，逻辑删除
     * @param eduId
     */
    public void deleteEdu(String eduId) throws Exception {
        FrEducation frEducation = frEducationPublicMapper.selectById(eduId);
//        FrEducationConfig frEducationConfig = new FrEducationConfig();
//        FrEducationReserveObject frEducationReserveObject = new FrEducationReserveObject();
//        FrEducationCardObject frEducationCardObject = new FrEducationCardObject();
//        //config
//        frEducationConfig.setEducationId(eduId);
//        frEducationConfig = frEducationConfigMapper.selectOne(frEducationConfig);
//        //
//        frEducationReserveObject.setEducationConfigId(frEducationConfig.getId());
//        frEducationCardObject.setEducationConfigId(frEducationConfig.getId());
//        frEducationReserveObject = frEducationReserveObjectMapper.selectOne(frEducationReserveObject);
//        frEducationCardObject = frEducationCardObjectMapper.selectOne(frEducationCardObject);

        frEducation.setUse(false);
       // delete 假删除
        frEducationPublicMapper.updateById(frEducation);
//        try {
//            frEducationPublicMapper.deleteById(eduId);
//            frEducationConfigMapper.deleteById(frEducationConfig.getId());
//            if(null != frEducationReserveObject){
//                frEducationReserveObjectMapper.deleteById(frEducationReserveObject.getId());
//            }
//            if(null != frEducationCardObject){
//                frEducationCardObjectMapper.deleteById(frEducationCardObject.getId());
//            }
//        } catch (Exception e) {
//            throw new Exception("删除失败");
//        }

    }


    /**
     * 复制课程
     * @param map
     */
    public void copyEducationByEduId(Map<String, String> map){
        String eduId = map.get("eduId");
        String beginDate = map.get("beginDate");
        String endDate = map.get("endDate");
        String configId = "";
        String reserveId = "";
        String cardSettleId = "";
        Date date = new Date();

        FrEducation frEducation = frEducationPublicMapper.selectById(eduId);
        Map<String, String> obj = frEducationPublicMapper.findEducationToCopyByEduId(eduId);

        configId = obj.get("configId");
        reserveId = obj.get("reserveId");
        cardSettleId = obj.get("cardSettleId");

        frEducation.setExecuteDatePlan(DateUtil.stringToDate(beginDate));
        frEducation.setBeginDatePlan(DateUtil.stringToDate(beginDate));
        frEducation.setEndDatePlan(DateUtil.stringToDate(endDate));
        frEducation.setStatus(0);
        frEducation.setId(UUIDUtils.generateGUID());
        frEducation.setCreateTime(date);
        frEducation.setUpdateTime(null);
        //
        FrEducationConfig frEducationConfig = frEducationConfigMapper.selectById(configId);
        frEducationConfig.setId(UUIDUtils.generateGUID());
        frEducationConfig.setEducationId(frEducation.getId());
        //
        FrEducationReserveObject frEducationReserveObject = null;
        if(!StringUtils.isBlank(reserveId)){
            frEducationReserveObject = frEducationReserveObjectMapper.selectById(reserveId);
            frEducationReserveObject.setId(UUIDUtils.generateGUID());
            frEducationReserveObject.setEducationConfigId(frEducationConfig.getId());
        }
        //
        FrEducationCardObject frEducationCardObject = null;
        if(!StringUtils.isBlank(cardSettleId)){
            frEducationCardObject = frEducationCardObjectMapper.selectById(cardSettleId);
            frEducationCardObject.setEducationConfigId(frEducationConfig.getId());
            frEducationCardObject.setId(UUIDUtils.generateGUID());
        }


        frEducationPublicMapper.insert(frEducation);
        frEducationConfigMapper.insert(frEducationConfig);
        if(null != frEducationReserveObject){
            frEducationReserveObjectMapper.insert(frEducationReserveObject);
        }
        if(null != frEducationCardObject){
            frEducationCardObjectMapper.insert(frEducationCardObject);
        }

    }


    /**
     * 查询会所下的门店关联课程
     * @param code
     * @return
     */
    public List<Map<String, String>> findAllCourseForShopSdaduim(String shopId, String code){
        List<Map<String, String>> array = frEducationPublicMapper.findAllCourseForShopSdaduim(shopId, code);
        return array;
    }

    /**
     * 查询冻结用户
     * @param shopId
     * @param eduType
     * @return
     */
    public List<Map<String, Object>> findFrEducationFreezeClient(String shopId, String beginDate, String endDate, String searchInput, String eduType){
        return frEducationFreezeClientMapper.findAllByCondition(shopId, beginDate, endDate, searchInput, eduType);
    }

    /**
     * 查询冻结用户
     * @param id
     * @return
     */
    public void deleteFreezeClient(String id){
        frEducationFreezeClientMapper.deleteById(id);
    }


    /**
     * 预约详情，根据Id 查询数据 全部的数据，用来更新课程
     * @param eduId
     * @return
     */
    public Map<String, Object> findEduCationInfoById(String eduId){
        Map<String, Object> map = new HashMap<>();
        FrEducation frEducation = frEducationPublicMapper.selectById(eduId);
        FrEducationConfig frEducationConfig = new FrEducationConfig();
        FrEducationReserveObject frEducationReserveObject = new FrEducationReserveObject();
        List<FrEducationCardObject> frEducationCardObjects = null;
        String condition = "education_config_id={0}";

        frEducationConfig.setEducationId(eduId);
        frEducationConfig = frEducationConfigMapper.selectOne(frEducationConfig);
        frEducationReserveObject.setEducationConfigId(frEducationConfig.getId());
        frEducationReserveObject = frEducationReserveObjectMapper.selectOne(frEducationReserveObject);
        frEducationCardObjects = frEducationCardObjectMapper.selectList(new EntityWrapper<FrEducationCardObject>().where(condition, frEducationConfig.getId()));
        map.put("education", frEducation);
        map.put("eduConfig", frEducationConfig);
        map.put("eduReserveObject", frEducationReserveObject);
        map.put("eduCardObjects", frEducationCardObjects);
        return map;

    }

    /**
     * 更新排课
     * @param map
     * @throws Exception
     */
    public void updateEducationInfo(Map<String, String> map) throws Exception {
        FrEducation frEducation = JSONObject.parseObject(map.get("education"), FrEducation.class);
        if(null == frEducation){
            throw new Exception("编辑排课失败，请重试");
        }
        //frEducation.setId(UUIDUtils.generateGUID());
        //config
        FrEducationConfig frEducationConfig = JSONObject.parseObject(map.get("eduConfig"), FrEducationConfig.class);
        if(null == frEducationConfig){
            throw new Exception("编辑排课失败，请重试");
        }
//        frEducationConfig.setId(UUIDUtils.generateGUID());
//        frEducationConfig.setEducationId(frEducation.getId());
        //member object
        FrEducationReserveObject frEducationReserveObject = JSONObject.parseObject(map.get("eduReserveObject"), FrEducationReserveObject.class);
//        if(null == frEducationReserveObject){
//            throw new Exception("排课失败，请重试");
//        }
        if(null != frEducationReserveObject){
            frEducationReserveObject.setId(UUIDUtils.generateGUID());
            frEducationReserveObject.setEducationConfigId(frEducationConfig.getId());
        }
        //会员卡设置
        JSONArray cardObjects = JSONArray.parseArray(map.get("cardObject")) ;
        //删除之前设置的card
        String condition = "education_config_id={0}";
        frEducationCardObjectMapper.delete(new EntityWrapper<FrEducationCardObject>().where(condition, frEducationConfig.getId()));
        if(null != cardObjects){
            for(Object object: cardObjects){
                FrEducationCardObject frEducationCardObject = JSONObject.parseObject(JSON.toJSONString(object), FrEducationCardObject.class);
                frEducationCardObject.setId(UUIDUtils.generateGUID());
                frEducationCardObject.setEducationConfigId(frEducationConfig.getId());
                frEducationCardObjectMapper.insert(frEducationCardObject);
            }
        }



        //保存
        frEducation.setUse(true);
        frEducationPublicMapper.updateAllColumnById(frEducation);
        //删除旧的，
        frEducationReserveObjectMapper.delete(new EntityWrapper<FrEducationReserveObject>()
                .where("education_config_id={0}", frEducationConfig.getId()));
        if(null != frEducationReserveObject){
            frEducationReserveObjectMapper.insert(frEducationReserveObject);
        }

        frEducationConfigMapper.updateAllColumnById(frEducationConfig);
    }


    /**
     * 查询这一周的私教课程时间
     * @param eduId
     * @param courseId
     * @param beginDate
     * @param endDate
     * @return
     */
    public List<Map<String, Object>> findEduDateByEduId(String eduId, String courseId, String beginDate, String endDate){
        FrEducation frEducation = frEducationPublicMapper.selectById(eduId);
        Integer reserveType = frEducation.getReserveType();
        return frEducationPublicMapper.findEduDateByEduId(courseId, beginDate, endDate, reserveType);

    }


}