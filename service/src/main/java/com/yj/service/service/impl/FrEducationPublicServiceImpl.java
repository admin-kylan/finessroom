package com.yj.service.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.*;
import com.yj.dal.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private FrEducationCardObjectMapper frEducationCardObjectMapper;


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
     * @return
     */
    public List findEducationList(String code, String shopId, String coachId, String sdaduimId, String beginDate, String endDate, String eduType, String reserveType) {
        //私教一对一
        if(StringUtils.equals(eduType, "1") && StringUtils.equals(reserveType, "1")){
            return frEducationPublicMapper.findEducationOneToOneList(shopId, code,  coachId, sdaduimId, beginDate, endDate, eduType);
        }
        return frEducationPublicMapper.findEducationList(shopId, code,  coachId, sdaduimId, beginDate, endDate, eduType);
    }

    /**
     * 查询指定教学中的待确认会员
     * @param eduId
     * @param reserveStatus
     * @return
     */
    public List findMemberReserveStatusList(String eduId, String reserveStatus, String searchInput){
        return frEducationClientInfoMapper.findMemberReserveStatusList(eduId, reserveStatus, searchInput);
    }

    /**
     * 更改会员预约状态
     * @param map
     * @return
     */
    public void changeEduClientStatus(Map<String, String> map){
        FrEducationClientInfo frEducationClientInfo = new FrEducationClientInfo();
        frEducationClientInfo.setId(map.get("clientInfoId"));
        frEducationClientInfo.setReserveStatus(Integer.parseInt(map.get("status")));
        frEducationClientInfoMapper.updateById(frEducationClientInfo);
    }

    /**
     * 预约详情，根据Id 查询数据
     * @param eduId
     * @return
     */
    public Map findEducationById(String eduId){
        return frEducationPublicMapper.findEducationById(eduId);
    }


    /**
     * 查询会员
     * @param CustomerCode
     * @param mobile
     * @return
     */
    public List<Map<String, Object>> getClientList(String CustomerCode, String mobile) {
        List<Map<String, Object>> lists = frEducationPublicMapper.getClientByMobileName(CustomerCode, mobile);
        //查询会员卡
        String clientId = "";
        for(Map<String, Object> map: lists){
            clientId = (String) map.get("id");
            String condition = "client_id='" + clientId + "' and is_using=1 and status=0 and CustomerCode='" + CustomerCode + "'";
            List cards = frCardMapper.selectList(new EntityWrapper<FrCard>().where(condition));
            map.put("cards", cards);
        }


        return lists;
    }

    /**
     * 查询会员
     * @param roomId
     * @return
     */
    public Map getGroupRoomSeatById(String roomId) {
        return frEducationPublicMapper.getGroupRoomSeatById(roomId);
    }

    /**
     * 保存预约
     * @param map
     */
    public void saveEducationItem(Map<String, Object> map) throws Exception {
        FrEducationClientInfo frEducationClientInfo = JSONObject.parseObject(JSONObject.toJSONString(map), FrEducationClientInfo.class);
        FrEducationReserveObject frEducationReserveObject = new FrEducationReserveObject();
        frEducationReserveObject.setEducationConfigId(frEducationClientInfo.getEducationId());
        //判断当前用户卡号是否已经预约
        FrEducationClientInfo frEducationClientInfo1 = new FrEducationClientInfo();
        frEducationClientInfo1.setEducationId(frEducationClientInfo.getEducationId());
        frEducationClientInfo1.setSeatId(frEducationClientInfo.getSeatId());
        frEducationClientInfo1.setMemberCardId(frEducationClientInfo.getMemberCardId());
        frEducationClientInfo1 = frEducationClientInfoMapper.selectOne(frEducationClientInfo1);
        if(null != frEducationClientInfo1 && frEducationClientInfo1.getReserveStatus() != 0){
            throw new Exception("该会员已经预约");
        }
        //判断限制
        frEducationReserveObject = frEducationReserveObjectMapper.selectOne(frEducationReserveObject);

        //保存
        frEducationClientInfo.setId(UUIDUtils.generateGUID());
        //查找座位 {replace}
        FrGroupClassRoomSeat frGroupClassRoomSeat = frGroupClassRoomSeatMapper.selectById(frEducationClientInfo.getSeatId());
        frGroupClassRoomSeat.setSeatNum(JSONArray.toJSONString(map.get("seatNum")).replace("{replace}",frEducationClientInfo.getId()));
        frEducationClientInfoMapper.insert(frEducationClientInfo);
        frGroupClassRoomSeatMapper.updateAllColumnById(frGroupClassRoomSeat);
    }
}