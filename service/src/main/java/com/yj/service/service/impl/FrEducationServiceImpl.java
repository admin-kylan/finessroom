package com.yj.service.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.util.DateUtil;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.*;
import com.yj.dal.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
public class FrEducationServiceImpl {

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
    private FrEducationMapper frEducationMapper;

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

    @Autowired
    private FrEducationCardObjectMapper frEducationCardObjectMapper;

    /**
     * 添加预约
     *
     * @param map
     */
    public void addReserveGroup(Map<String, String> map, Integer teachType) throws Exception {
        //预约课程Id
        String eduId = map.get("eduId");
        //会员卡Id
        String cardId = map.get("cardId");
        //座位Id
        String seatId = map.get("seatId");
        //座位名字
        String seatName = map.get("seatName");
        //会员Id
        String memberId = map.get("memberId");
        //预约人id
        String reserveClientId = map.get("reserveClientId");
        //结算方式
        String settleType = map.get("settleType");
        //扣费（余额）
        String deductionBalance = map.get("deductionBalance");
        //操作员
        String createUserName = map.get("createUserName");
        //操作员Id
        String createUserId = map.get("createUserId");
        if(teachType == 0){
            if (StringUtils.isBlank(eduId) || StringUtils.isBlank(seatId) || StringUtils.isBlank(seatName) ||
                    StringUtils.isBlank(memberId)|| StringUtils.isBlank(cardId)) {
                throw new Exception("参数错误");
            }
        }
        //私教
        if(teachType == 1){
            if (StringUtils.isBlank(eduId) || StringUtils.isBlank(memberId)|| StringUtils.isBlank(cardId)) {
                throw new Exception("参数错误");
            }
        }

        Date now = new Date();
        FrClient frClient = frClientMapper.selectById(memberId);
        FrCard frCard = new FrCard();
        PersonnelInfo personnelInfo = null;
        String level = "";
        String reserveClientName = "";
        //判断当前课程是否开始
        FrEducation frEducation = frEducationMapper.selectById(eduId);
        if (frEducation.getStatus() == 2) {
            throw new Exception("预约失败，课程已结束");
        }
        //判断当前时间是否可以预约
        FrEducationConfig frEducationConfig = new FrEducationConfig();
        frEducationConfig.setEducationId(eduId);
        frEducationConfig = frEducationConfigMapper.selectOne(frEducationConfig);
        if ((frEducationConfig.getReserveTime().getTime() - now.getTime()) > 0) {
            throw new Exception("预约失败，课程还未开放预约");
        }
        //判断会员等级预约限制
        FrClientPersonal frClientPersonal = new FrClientPersonal();
        frClientPersonal.setClientId(frClient.getId());
        frClientPersonal.setUsing(true);
        frClientPersonal = frClientPersonalMapper.selectOne(frClientPersonal);
        if (frEducationConfig.getLimitReserve()) {
            level = frClient.getLevelId();
            this.checkLevel(level, frEducationConfig.getId(), frClientPersonal);
        }
        if (!StringUtils.equals(memberId, reserveClientId)) {
            //判断如果相同，同一个人预约。不同则是web预约，由操作员预约的
            personnelInfo = personnelInfoMapper.selectById(reserveClientId);
            if (null != personnelInfo) {
                reserveClientName = personnelInfo.getRelName();
            }
        } else {
            reserveClientName = frClient.getClientName();
        }
//        //设置扣款方式
//        FrEducationCardObject frEducationCardObject = new FrEducationCardObject();
//        frEducationCardObject.setEducationConfigId(frEducationConfig.getId());
//        frEducationCardObject.setProjectId(frEducation.getCourseId());
//        frEducationCardObject = frEducationCardObjectMapper.selectOne(frEducationCardObject);
//        //判断消费类型
//        if(frEducationConfig.getLimitReserve()){
//
//        }

        //开始做预约操作
        frCard = frCardMapper.selectById(cardId);


        FrEducationClientInfo frEducationClientInfo = new FrEducationClientInfo();
        frEducationClientInfo.setId(UUIDUtils.generateGUID());
        frEducationClientInfo.setEducationId(eduId);
        frEducationClientInfo.setMemberId(memberId);
        frEducationClientInfo.setMemberName(frClient.getClientName());
        frEducationClientInfo.setMemberType(null != frClientPersonal ? "现有客户" : "潜在客户");
        frEducationClientInfo.setMemberCardNo(frCard.getCardNo());
        frEducationClientInfo.setMobile(frClient.getMobile());
        frEducationClientInfo.setReserveClientId(reserveClientId);
        frEducationClientInfo.setReserveClientName(reserveClientName);
        frEducationClientInfo.setReserveDate(now);
        if(teachType == 0){
            frEducationClientInfo.setSeatId(seatId);
            frEducationClientInfo.setSeatName(seatName);
        }

        //结算方式
        frEducationClientInfo.setSettleType(settleType);
        //扣费余额
        frEducationClientInfo.setDeductionBalance(deductionBalance);
        frEducationClientInfo.setReserveStatus(1);
        frEducationClientInfo.setCreateUserId(createUserId);
        frEducationClientInfo.setCreateUserName(createUserName);
        frEducationClientInfo.setCreateTime(now);
        frEducationClientInfo.setSex(frClient.getSex() == 1 ? "女" : "男");

        frEducationClientInfoMapper.insert(frEducationClientInfo);
    }

    /**
     * 预约等级限制
     *
     * @param level
     * @param configId
     * @throws Exception
     */
    private void checkLevel(String level, String configId, FrClientPersonal frClientPersonal) throws Exception {
        FrEducationReserveObject frEducationReserveObject = new FrEducationReserveObject();
        frEducationReserveObject.setEducationConfigId(configId);
        frEducationReserveObject = frEducationReserveObjectMapper.selectOne(frEducationReserveObject);
        boolean isLimit = false;
        FrLevel frLevel = frLevelMapper.selectById(level);
        if (null != frLevel) {
            switch (frLevel.getLevelName()) {
                case "普通会员":
                    isLimit = frEducationReserveObject.getGeneralMember();
                    break;
                case "银卡会员":
                    isLimit = frEducationReserveObject.getSilverMember();
                    break;
                case "钻石会员":
                    isLimit = frEducationReserveObject.getDiamondMember();
                    break;
                case "金卡会员":
                    isLimit = frEducationReserveObject.getGoldMember();
                    break;
                default:
                    //判断是否是现有客户，还是潜在客户
                    if (null == frClientPersonal) {
                        //不是现有客户
                        isLimit = frEducationReserveObject.getLatenceMember();
                    } else {
                        //是现有客户
                        isLimit = frEducationReserveObject.getNewUser();
                    }
            }
        }

        if (!isLimit) {
            throw new Exception("预约失败，不匹配当前会员等级！");
        }

    }



    /**
     * 取消预约
     *
     * @param map
     */
    public void cancelReserveGroup(Map<String, String> map) {
        String eduId = map.get("eduId");
        String remarks = map.get("remarks");
        FrEducationClientInfo frEducationClientInfo = new FrEducationClientInfo();
        frEducationClientInfo.setEducationId(eduId);
        frEducationClientInfo = frEducationClientInfoMapper.selectOne(frEducationClientInfo);
        frEducationClientInfo.setRemarks(remarks);
        frEducationClientInfo.setReserveStatus(0);
        frEducationClientInfoMapper.updateAllColumnById(frEducationClientInfo);

    }


    /**
     * 查询教练的课程
     */
    public List<Map<String, Object>> findEducationPublicListCoach(String shopId, String executeDate, String sdaduimId, String CustomerCode, Integer teachType) {
        if (StringUtils.isBlank(executeDate)) {
            //获取当天时间
            executeDate = DateUtil.dateToString(new Date(), "yyyy-MM-dd");
        }
        List<Map<String, Object>> educations = frEducationMapper.findEducationPublicListCoach(shopId, executeDate, sdaduimId, CustomerCode, teachType);
        this.fetchEducationPlan(educations);
        //  educations = (List<Map<String, Object>>)JSONUtils.convert(educations);
        return educations;
    }

    /**
     * 查询教练的课程
     */
    public List<Map<String, Object>> findEducationPublicListRoom(String shopId, String sdaduimId, String executeDate, String CustomerCode, Integer teachType) {
        if (StringUtils.isBlank(executeDate)) {
            //获取当天时间
            executeDate = DateUtil.dateToString(new Date(), "yyyy-MM-dd");
        }
        List<Map<String, Object>> educations = frEducationMapper.findEducationPublicListRoom(shopId, sdaduimId, executeDate, CustomerCode, teachType);
        this.fetchEducationPlan(educations);
        //   educations = (List<Map<String, Object>>)JSONUtils.convert(educations);
        return educations;
    }

    /**
     * 根据时间段预约
     * @param shopId
     * @param sdaduimId
     * @param executeBeginDate
     * @param executeEndDate
     * @param CustomerCode
     * @param teachType
     * @return
     */
    public List<Map<String, Object>> findEducationPublicListDate(String shopId, String sdaduimId, String executeBeginDate, String executeEndDate, String CustomerCode, Integer teachType){
        List<Map<String, Object>> educations = frEducationMapper.findEducationPublicListDate(shopId, sdaduimId, executeBeginDate, executeEndDate, CustomerCode, teachType);
        this.fetchEducationPlan(educations);
        //   educations = (List<Map<String, Object>>)JSONUtils.convert(educations);
        return educations;

    }


    /**
     * 获取课程所对应的课程计划 和课程设置
     *
     * @param educations
     */
    private void fetchEducationPlan(List<Map<String, Object>> educations) {
        String eduId = "", condition = "";
        for (Object item : educations) {
            List<FrEducationPlan> frEducationPlans = null;
            Map<String, Object> map = (Map<String, Object>) item;
            eduId = (String) map.get("eduId");
            condition = "education_id = '" + eduId + "'";
            //课程计划
            frEducationPlans = frEducationPlanMapper.selectList(new EntityWrapper<FrEducationPlan>().where(condition));
            if (null == frEducationPlans) {
                frEducationPlans = new ArrayList<>();
            }
            //课程设置
            this.fetchEducationConfig(eduId, map);
            map.put("eduPlan", frEducationPlans);
        }

    }


    /**
     * 查询团教设置
     *
     * @param eduId
     */
    private void fetchEducationConfig(String eduId, Map<String, Object> map) {
        String conditon = "";
        // Map<String, Object> map = new JSONObject();
        FrEducationConfig frEducationConfig = new FrEducationConfig();
        FrEducationReserveObject frEducationReserveObject = new FrEducationReserveObject();
        List<FrEducationCardObject> frEducationCardObjects = null;
        //设置
        frEducationConfig.setEducationId(eduId);
        frEducationConfig = frEducationConfigMapper.selectOne(frEducationConfig);
        //查询配置
        frEducationReserveObject.setEducationConfigId(frEducationConfig.getId());
        frEducationReserveObject = frEducationReserveObjectMapper.selectOne(frEducationReserveObject);
        //会员卡使用设置
        conditon = "education_config_id = '" + frEducationConfig.getId() + "'";
        frEducationCardObjects = frEducationCardObjectMapper.selectList(new EntityWrapper<FrEducationCardObject>().where(conditon));

        map.put("eduConfig", frEducationConfig);
        map.put("eduConfigReserve", frEducationReserveObject);
        map.put("eduConfigCard", null == frEducationCardObjects ? new JSONArray() : frEducationCardObjects);

    }

}