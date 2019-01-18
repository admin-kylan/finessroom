package com.yj.service.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.*;
import com.yj.dal.model.*;
import com.yj.service.service.IFrCardOrderDatailService;
import com.yj.service.service.IFrCardOrderPayModeService;
import com.yj.service.service.IFrCardOrderPriceDatailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

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
        if (StringUtils.equals(eduType, "1")) {
            if (StringUtils.equals(reserveType, "1")) {
                return frEducationPublicMapper.findEducationOneToOneList(shopId, code, coachId, sdaduimId, beginDate, endDate, eduType);
            }
            return frEducationPublicMapper.findEducationOneToManyList(shopId, code, coachId, sdaduimId, beginDate, endDate, eduType);

        }

        //团教
        return frEducationPublicMapper.findEducationList(shopId, code, coachId, sdaduimId, beginDate, endDate, eduType);
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
        FrEducationClientInfo frEducationClientInfo = JSONObject.parseObject(JSONObject.toJSONString(map), FrEducationClientInfo.class);
        FrEducationConfig frEducationConfig = new FrEducationConfig();
        frEducationConfig.setEducationId(frEducationClientInfo.getEducationId());
        frEducationConfig = frEducationConfigMapper.selectOne(frEducationConfig);
        //判断会员等级预约限制
        Map<String, Object> mapClient = frEducationPublicMapper.getClientMemberType(frEducationClientInfo.getMemberId());

        //判断当前用户卡号是否已经预约
        List<FrEducationClientInfo> frEducationClientInfos = frEducationClientInfoMapper.findMemberNotCancel(frEducationClientInfo.getEducationId(), frEducationClientInfo.getMemberId());
        if (frEducationClientInfos.size() > 0) {
            throw new Exception("该会员已经预约");
        }
        //判断限制
        //frEducationReserveObject = frEducationReserveObjectMapper.selectOne(frEducationReserveObject);
        FrClient frClient = frClientMapper.selectById(frEducationClientInfo.getMemberId());
        level = frClient.getLevelId();
        frEducationService.checkLevel(level, frEducationConfig.getId(), (String) mapClient.get("memberType"));

        //保存
        frEducationClientInfo.setId(UUIDUtils.generateGUID());
        //查找座位 {replace}
        FrGroupClassRoomSeat frGroupClassRoomSeat = frGroupClassRoomSeatMapper.selectById(frEducationClientInfo.getSeatId());
        frGroupClassRoomSeat.setSeatNum(JSONArray.toJSONString(map.get("seatNum")).replace("{replace}", frEducationClientInfo.getId()));
        frEducationClientInfoMapper.insert(frEducationClientInfo);
        frGroupClassRoomSeatMapper.updateAllColumnById(frGroupClassRoomSeat);
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
            projectOrder.setAllCount(Integer.parseInt(endAmt));

            this.insertEducationMemberOrder(frEducationMemberOrder, map);
            //修改课程数
            projectOrderMapper.updateById(projectOrder);


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
    private void insertEducationMemberOrder(FrEducationMemberOrder frEducationMemberOrder, Map<String, String> map) throws Exception {


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

        //关联订单
        FrEducationClientInfo frEducationClientInfo1 = new FrEducationClientInfo();
        frEducationClientInfo1.setMemberId(frEducationClientInfo.getMemberId());
        frEducationClientInfo1.setEducationId(frEducationClientInfo.getEducationId());
        frEducationClientInfo1.setReserveStatus(1);

        String clientInfoId = UUIDUtils.generateGUID();
        frEducationClientInfo1 = frEducationClientInfoMapper.selectOne(frEducationClientInfo1);
        //判断是否已经存在
        if (null != frEducationClientInfo1) {
            //查看当前的memberorder 是否存在
            FrEducationMemberOrder frEducationMemberOrder1 = frEducationMemberOrderMapper.findMemberOrderNormal(frEducationClientInfo1.getEducationId(), frEducationClientInfo1.getId());
            if (null != frEducationMemberOrder1) {
                //已经存在 ，则不能重复交钱扣款
                throw new Exception("该会员已经付款扣费,请勿重复提交");
            }
            clientInfoId = frEducationClientInfo1.getId();
        } else {
            frEducationClientInfo.setId(clientInfoId);
            frEducationClientInfoMapper.insert(frEducationClientInfo);
        }

        //给订单添加id
        frEducationMemberOrder.setClientInfoId(clientInfoId);
        //保存订单
        frEducationMemberOrderMapper.insert(frEducationMemberOrder);


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
            this.insertEducationMemberOrder(frEducationMemberOrder, map);
        } catch (NumberFormatException e) {
            throw new Exception("预约失败，请重试。。");
        }

    }


    /**
     * 更新卡的权益
     *
     * @param map
     */
    public void updateCardDetail(Map<String, String> map) throws Exception {
        String orderId = UUIDUtils.generateGUID();
        //数组
        JSONArray cardOrderPayModeDate = JSONArray.parseArray(map.get("cardOrderPayModeDate"));
        JSONArray cardOrderDetailList = JSONArray.parseArray(map.get("cardOrderDetailList"));
        FrCardOrderDatail buyEduDetail = JSONObject.parseObject(map.get("buyEduDetail"), FrCardOrderDatail.class);
        FrEducationMemberOrder frEducationMemberOrder = JSONObject.parseObject(map.get("cardObject"), FrEducationMemberOrder.class);
        if (null == frEducationMemberOrder) {
            throw new Exception("提交出错，请重试。");
        }
        frEducationMemberOrder.setId(orderId);
        //保存订单
        this.insertEducationMemberOrder(frEducationMemberOrder, map);
        //订单支付方式及金额
        for (Object object : cardOrderPayModeDate) {
            FrCardOrderPayMode frCardOrderPayMode = JSONObject.parseObject(JSON.toJSONString(object), FrCardOrderPayMode.class);
            frCardOrderPayMode.setId(UUIDUtils.generateGUID());
            frCardOrderPayMode.setOrderId(orderId);
            iFrCardOrderPayModeService.insert(frCardOrderPayMode);
        }

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
        Integer one = 0, two = 0, three = 0;
        double price = 0.0;
        for (FrEducationClientInfo frEducationClientInfo : list) {
            clientInfoId = frEducationClientInfo.getId();
            FrEducationMemberOrder frEducationMemberOrder = new FrEducationMemberOrder();
            if (frEducationClientInfo.getReserveStatus() == 1) {

                //已预约
                one++;
                frEducationMemberOrder.setClientInfoId(clientInfoId);
                frEducationMemberOrder.setEducationId(eduId);
                frEducationMemberOrder.setStatus(1);
                frEducationMemberOrder = frEducationMemberOrderMapper.selectOne(frEducationMemberOrder);
                if(null == frEducationMemberOrder){
                    continue;
                }
                frEducationMemberOrder.setStatus(2);
                price += frEducationMemberOrder.getPrice();
                frEducationMemberOrderMapper.updateAllColumnById(frEducationMemberOrder);
            }
            if (frEducationClientInfo.getReserveStatus() == 2) {
                //已预约
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
        frEducation = frEducationPublicMapper.selectById(eduId);
        frEducation.setStatus(2);
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
     * 冲销
     *
     * @param map
     */
    public void cancelMemberOrder(Map<String, String> map) {
        String id = map.get("id");
        Double eduClassSalesNum = Double.parseDouble(map.get("eduClassSalesNum"));
        FrEducationMemberOrder frEducationMemberOrder = frEducationMemberOrderMapper.selectById(id);
        frEducationMemberOrder.setStatus(0);
        frEducationMemberOrderMapper.updateAllColumnById(frEducationMemberOrder);
        //再添加一条去抵消
        FrCardOrderDatail buyEduDetail = JSONObject.parseObject(map.get("buyEduDetail"), FrCardOrderDatail.class);
        Map<String, Object> cardOrder = frEducationPublicMapper.findCardOrderDetailFirst(frEducationMemberOrder.getMemberCardId());
        //
        buyEduDetail.setClientId((String) cardOrder.get("clientId"));
        buyEduDetail.setCardId(frEducationMemberOrder.getMemberCardId());
        buyEduDetail.setOrderRightsNum(eduClassSalesNum);
        buyEduDetail.setOrderAmt(Double.parseDouble(String.valueOf(cardOrder.get("orderAmt"))) + eduClassSalesNum);
        buyEduDetail.setId(UUIDUtils.generateGUID());
        buyEduDetail.setOrderId(frEducationMemberOrder.getId());
        iFrCardOrderDatailService.insert(buyEduDetail);
    }
}