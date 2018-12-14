package com.yj.dal.dto;

import com.yj.dal.model.FrCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientInformationDTO {
    /**
     * 客户类型
     */
    private String clientType;
    /**
     * 客户排名
     */
    private String clientRanking;
    /**
     * 客户id
     */
    private String id;
    /**
     * 姓名
     */
    private String clientName;

    /**
     * 性别
     */
    private Integer sex;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 服务会籍
     */
    private String fwhjName;
    /**
     * 客户来源
     */
    private String customerSource;
    /**
     * 客户来源id
     */
    private String sourceId;

    /**
     * 购卡数
     */
    private String cardCount;
    /**
     * 购卡总金额
     */
    private String cardMoneyCount;

    /**
     * 最贵会员卡
     */
    private String mostPriceCard;
    /**
     * 最低会员卡
     */
    private String mostCheapCard;
    /**
     * 消费总次数
     */
    private String consumptionCount;
    /**
     * 消费总金额
     */
    private String consumptionMoney;
    /**
     * 项目总金额
     */
    private String projectMoney;
    /**
     * 最高消费
     */
    private String maximumConsumption;

    /**
     * 最低消费
     */
    private String minimumConsumption;

    /**
     * 服务人员
     */
    private List<Map<String, String>> servicePersonal = new ArrayList<>();

    /**
     * 购卡信息
     */
    private List<FrCard> cardInformation;
    /**
     * 客户信息
     */
    private Map<String, String> clientInformation = new HashMap();

    public Map<String, String> getClientInformation() {
        return clientInformation;
    }

    public void setClientInformation(Map<String, String> clientInformation) {
        this.clientInformation = clientInformation;
    }

    public List<Map<String, String>> getServicePersonal() {
        return servicePersonal;
    }

    public void setServicePersonal(List<Map<String, String>> servicePersonal) {
        this.servicePersonal = servicePersonal;
    }

    public List<FrCard> getCardInformation() {
        return cardInformation;
    }

    public void setCardInformation(List<FrCard> cardInformation) {
        this.cardInformation = cardInformation;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getClientRanking() {
        return clientRanking;
    }

    public void setClientRanking(String clientRanking) {
        this.clientRanking = clientRanking;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFwhjName() {
        return fwhjName;
    }

    public void setFwhjName(String fwhjName) {
        this.fwhjName = fwhjName;
    }

    public String getCustomerSource() {
        return customerSource;
    }

    public void setCustomerSource(String customerSource) {
        this.customerSource = customerSource;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getCardCount() {
        return cardCount;
    }

    public void setCardCount(String cardCount) {
        this.cardCount = cardCount;
    }

    public String getCardMoneyCount() {
        return cardMoneyCount;
    }

    public void setCardMoneyCount(String cardMoneyCount) {
        this.cardMoneyCount = cardMoneyCount;
    }

    public String getMostPriceCard() {
        return mostPriceCard;
    }

    public void setMostPriceCard(String mostPriceCard) {
        this.mostPriceCard = mostPriceCard;
    }

    public String getMostCheapCard() {
        return mostCheapCard;
    }

    public void setMostCheapCard(String mostCheapCard) {
        this.mostCheapCard = mostCheapCard;
    }

    public String getConsumptionCount() {
        return consumptionCount;
    }

    public void setConsumptionCount(String consumptionCount) {
        this.consumptionCount = consumptionCount;
    }

    public String getConsumptionMoney() {
        return consumptionMoney;
    }

    public void setConsumptionMoney(String consumptionMoney) {
        this.consumptionMoney = consumptionMoney;
    }

    public String getProjectMoney() {
        return projectMoney;
    }

    public void setProjectMoney(String projectMoney) {
        this.projectMoney = projectMoney;
    }

    public String getMaximumConsumption() {
        return maximumConsumption;
    }

    public void setMaximumConsumption(String maximumConsumption) {
        this.maximumConsumption = maximumConsumption;
    }

    public String getMinimumConsumption() {
        return minimumConsumption;
    }

    public void setMinimumConsumption(String minimumConsumption) {
        this.minimumConsumption = minimumConsumption;
    }
}
