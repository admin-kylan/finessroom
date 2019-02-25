package com.yj.service.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.FrCardLimitMapper;
import com.yj.dal.dao.FrClientMapper;
import com.yj.dal.dao.FrEducationAccessInfoMapper;
import com.yj.dal.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * className FrEducationAccessInfoServiceImpl
 * by Kylan
 *
 * @author Kylan
 * @date 2019-02-16 17:40
 */
@Service
public class FrEducationAccessInfoServiceImpl {

    @Autowired
    private FrEducationAccessInfoMapper frEducationAccessInfoMapper;

    @Autowired
    private FrClientMapper frClientMapper;
    @Autowired
    private FrCardLimitMapper frCardLimitMapper;

    @Autowired
    private FrConsumeMoneyOrderServiceImpl frConsumeMoneyOrderService;


    /**
     * 验证用户
     * @param code
     * @param shopId
     * @param mobile
     * @throws Exception
     */
    public void versionClient(String code, String shopId, String mobile, String password) throws Exception {
        if(StringUtils.isBlank(mobile)){
            throw new Exception("验证出错，输入的号码为空");
        }
        FrClient frClient = new FrClient();
        frClient.setCustomerCode(code);
        frClient.setMobile(mobile);
        frClient = frClientMapper.selectOne(frClient);
        if(null == frClient){
            throw new Exception("该手机号码不存在，请重试！！！");
        }

        List<Map<String, String>> list = frEducationAccessInfoMapper.versionClient(frClient.getId());
        if(null == list){
            throw new Exception("输入凭证错误，请重试！！！");
        }
        for(Map<String, String> map: list){
            if(StringUtils.isBlank(map.get("pass")) && StringUtils.equals(password, "0")){
                return;
            }
            if(StringUtils.equals(map.get("pass"), password)){
                return;
            }
        }

        throw new Exception("输入凭证错误，请重试！！！");
    }

    /**
     * 保存出入信息
     * @param map
     * @throws Exception
     */
    public void saveAccessInfo(Map<String, Object> map) throws Exception {
        try {
            FrEducationAccessInfo frEducationAccessInfo = JSONObject.parseObject(JSONObject.toJSONString(map.get("accessInfo")), FrEducationAccessInfo.class);
            JSONObject orderObj = JSONObject.parseObject((String) map.get("orderObj"));
            frEducationAccessInfo.setId(UUIDUtils.generateGUID());
            frEducationAccessInfoMapper.insert(frEducationAccessInfo);
            //新增销售订单表
            this.saveOrderEntity(frEducationAccessInfo, orderObj);
        } catch (Exception e) {
            throw new Exception("添加失败，请重试");
        }

    }

    /**
     *
     * 新增销售订单表
     * @param frEducationAccessInfo
     * @param orderObj
     */
    private void saveOrderEntity(FrEducationAccessInfo frEducationAccessInfo, Map orderObj){
        Date date = new Date();
        MoneyReport moneyReport = new MoneyReport();
        ConsumeAccountOrder consumeAccountOrder = new ConsumeAccountOrder();
        ConsumeAccountInfo consumeAccountInfo = new ConsumeAccountInfo();
        String userTypeName = "出入消费";
        String tableName = "fr_education_access_info";

        moneyReport.setCustomerCode(frEducationAccessInfo.getCustomerCode());
        moneyReport.setOrderNumber(frConsumeMoneyOrderService.getOrderIdByTime());
        moneyReport.setUseType(1);
        moneyReport.setUseTypeName(userTypeName);
        moneyReport.setTableId(frEducationAccessInfo.getId());
        moneyReport.setTableName(tableName);
        moneyReport.setCustomerId(frEducationAccessInfo.getClientId());
        moneyReport.setMemberCardId(String.valueOf(orderObj.get("cardId")));
        moneyReport.setName(frEducationAccessInfo.getClientName());
        moneyReport.setPhone(String.valueOf(orderObj.get("mobile")));
        moneyReport.setCreateId(frEducationAccessInfo.getCreateUserId());
        moneyReport.setCreateName(frEducationAccessInfo.getCreateUserName());
        moneyReport.setCreateTime(date);
        moneyReport.setId(UUIDUtils.generateGUID());
        moneyReport.setShopId(frEducationAccessInfo.getShopId());
        moneyReport.setSdadiumId("--");
        //
        consumeAccountOrder.setCustomerCode(frEducationAccessInfo.getCustomerCode());
        consumeAccountOrder.setOrderNumber(frConsumeMoneyOrderService.getOrderIdByTime());
        consumeAccountOrder.setTableId(frEducationAccessInfo.getId());
        consumeAccountOrder.setTableName(tableName);
        consumeAccountOrder.setName(frEducationAccessInfo.getClientName());
        consumeAccountOrder.setPhone(String.valueOf(orderObj.get("mobile")));
        consumeAccountOrder.setCreateId(frEducationAccessInfo.getCreateUserId());
        consumeAccountOrder.setCreateName(frEducationAccessInfo.getCreateUserName());
        consumeAccountOrder.setCreateTime(date);
        consumeAccountOrder.setId(UUIDUtils.generateGUID());
        consumeAccountOrder.setShopId(frEducationAccessInfo.getShopId());
        consumeAccountOrder.setSdadiumId("--");
        //
        consumeAccountInfo.setId(UUIDUtils.generateGUID());
        consumeAccountInfo.setCustomerCode(frEducationAccessInfo.getCustomerCode());
        consumeAccountInfo.setTableId(frEducationAccessInfo.getId());
        consumeAccountInfo.setConsumeAccountOrderId(consumeAccountOrder.getId());
        consumeAccountInfo.setOrderNumber(frConsumeMoneyOrderService.getOrderIdByTime());
        consumeAccountInfo.setTableName(tableName);
        consumeAccountInfo.setCustomerId(frEducationAccessInfo.getClientId());
        consumeAccountInfo.setMemberCardId(String.valueOf(orderObj.get("cardId")));
        consumeAccountInfo.setProjectId(frEducationAccessInfo.getSaleProjectId());
        consumeAccountInfo.setCreateTime(frEducationAccessInfo.getCreateDate());
        consumeAccountInfo.setName(frEducationAccessInfo.getClientName());
        consumeAccountInfo.setPhone(String.valueOf(orderObj.get("mobile")));
        consumeAccountInfo.setCreateId(frEducationAccessInfo.getCreateUserId());
        consumeAccountInfo.setCreateName(frEducationAccessInfo.getCreateUserName());
        consumeAccountInfo.setCreateTime(date);
        consumeAccountInfo.setShopId(frEducationAccessInfo.getShopId());
        consumeAccountInfo.setSdadiumId("--");

        frConsumeMoneyOrderService.saveMoneyReport(moneyReport);
        frConsumeMoneyOrderService.saveConsumeAccountInfo(consumeAccountInfo);
        frConsumeMoneyOrderService.saveConsumeAccountOrder(consumeAccountOrder);
    }


    /**
     * 加载出入信息
     * @param code
     * @param shopId
     * @param searchInput
     * @return
     */
    public List<FrEducationAccessInfo> loadAccessInfoList(String code, String shopId, String searchInput) {
        String beginDate = "";
        String endDate = "";
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        beginDate = new StringBuffer(simpleDateFormat.format(date)).append(" 00:00:00").toString();
        endDate = new StringBuffer(simpleDateFormat.format(date)).append(" 23:59:59").toString();
        return frEducationAccessInfoMapper.findList(shopId, code, searchInput, beginDate, endDate);
    }

    /**
     * 冲销
     * @param id
     * @throws Exception
     */
    public void cancelAccessInfo(String id) throws Exception{
        if(StringUtils.isBlank(id)){
            throw new Exception("冲销对象id不能为空");
        }
        FrEducationAccessInfo frEducationAccessInfo = frEducationAccessInfoMapper.selectById(id);
        if(null == frEducationAccessInfo){
            throw new Exception("冲销数据错误，未找到冲销数据");
        }
        frEducationAccessInfo.setUse(false);
        frEducationAccessInfoMapper.updateAllColumnById(frEducationAccessInfo);
        //frEducationAccessInfo.setUse(true);
        frEducationAccessInfo.setDone(true);
        frEducationAccessInfo.setId(UUIDUtils.generateGUID());
        frEducationAccessInfoMapper.insert(frEducationAccessInfo);
    }

    /**
     * 出场验证
     * @param code
     * @param shopId
     * @param outputSearch
     * @throws Exception
     */
    public void outputSearch(String code, String shopId, String outputSearch, String createId, String createName) throws Exception{
        String beginDate = "";
        String endDate = "";
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        beginDate = new StringBuffer(simpleDateFormat.format(date)).append(" 00:00:00").toString();
        endDate = new StringBuffer(simpleDateFormat.format(date)).append(" 23:59:59").toString();
        List<FrEducationAccessInfo> list = frEducationAccessInfoMapper.findInfoSearch(shopId, code, beginDate, endDate, outputSearch);
        if(null == list){
            throw new Exception("出场错误，数据未找到");
        }
        if(list.size() == 0){
            throw new Exception("温馨提示，输入不正确，未找到！！");
        }
        FrEducationAccessInfo frEducationAccessInfo = JSONObject.parseObject(JSONObject.toJSONString(list.get(0)), FrEducationAccessInfo.class);;
        frEducationAccessInfo.setStatus(true);
        frEducationAccessInfo.setOutputUserId(createId);
        frEducationAccessInfo.setOutputUserName(createName);
        frEducationAccessInfo.setOutputDate(date);
        frEducationAccessInfoMapper.updateAllColumnById(frEducationAccessInfo);

        //加载出场时间
        frConsumeMoneyOrderService.updateEndTime(frEducationAccessInfo.getId(), date);
    }

    /**
     * 出场查询
     * @param code
     * @param shopId
     * @return
     */
    public List<FrEducationAccessInfo> loadOutputInfoList(String code, String shopId) {
        String beginDate = "";
        String endDate = "";
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        beginDate = new StringBuffer(simpleDateFormat.format(date)).append(" 00:00:00").toString();
        endDate = new StringBuffer(simpleDateFormat.format(date)).append(" 23:59:59").toString();
        return frEducationAccessInfoMapper.findOutputList(shopId, code, beginDate, endDate);
    }

    /**
     * 更换手牌
     * @param id
     * @param num
     * @return
     */
    public void changeHandNum(String id, String num) throws Exception {
        FrEducationAccessInfo frEducationAccessInfo = frEducationAccessInfoMapper.selectById(id);
        if(null == frEducationAccessInfo){
            throw new Exception("更换手牌数据错误，未找到更换的数据");
        }
        if(org.apache.commons.lang3.StringUtils.equals(frEducationAccessInfo.getHandNum(), num)){
            throw new Exception("更换手牌错误，手牌更换前后必须不一样");
        }
        frEducationAccessInfo.setHandNum(num);
        frEducationAccessInfoMapper.updateAllColumnById(frEducationAccessInfo);
    }

    /**
     * 查询数量
     * @param shopId
     * @param code
     * @return
     */
    public Map<String, Object> calcNum(String shopId, String code){
        String beginDate = "";
        String endDate = "";
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        beginDate = new StringBuffer(simpleDateFormat.format(date)).append(" 00:00:00").toString();
        endDate = new StringBuffer(simpleDateFormat.format(date)).append(" 23:59:59").toString();
        return frEducationAccessInfoMapper.calcNum(shopId, code, beginDate, endDate);
    }


    /**
     * 根据手机号查询list
     * @param shopId
     * @param code
     * @return
     */
    public List<Map<String, Object>> loadCardList(String shopId, String code, String mobile){
        List<Map<String, Object>> list = frEducationAccessInfoMapper.loadCardList(shopId, code, mobile, null);
        this.findItemByShopId(shopId, code, list);
        return list;
    }

    /**
     * 根据卡号查询当个
     * @param shopId
     * @param code
     * @return
     */
    public List<Map<String, Object>> loadCardListOfCardNo(String shopId, String code, String cardNo){
        List<Map<String, Object>> list = frEducationAccessInfoMapper.loadCardList(shopId, code, null, cardNo);
        this.findItemByShopId(shopId, code, list);
        return list;
    }

    /**
     * 查询项目
     * @param shopId
     * @param code
     * @return
     */
    private void findItemByShopId(String shopId, String code, List<Map<String, Object>> list){
        for(Map<String, Object> map: list){
            map.put("items", frEducationAccessInfoMapper.findItemByShopId(shopId, code));
        }
    }

    /**
     * 查询数量
     * @param shopId
     * @param code
     * @return
     */
    public Map<String, Object> loadInfo(String shopId, String code, String mobile, String cardNo){
        return frEducationAccessInfoMapper.loadInfo(shopId, code, mobile, cardNo);
    }


}
