package com.yj.dal.param;

public class ClientInformationParam {
    /**
     * 性别
     */
    private String sex;

    /**
     * 渠道
     */
    private String sourceId;
    /**
     * 行业
     */
    private String industryId;
    /**
     * 交通工具
     */
    private String vehicleId;
    /**
     * 累计金额
     */
    private String beginMoney;
    /**
     * 累计金额
     */
    private String endMoney;
    /**
     * 购卡数量
     */
    private String cardCount;
    /**
     * 搜索条件 卡系列 卡名称 姓名 服务会稽
     */
    private String keyword;
    /**
     * 操作人门店
     */
    private String shopId;
    /**
     * 客户代码
     */
    private String customerCode;
    /**
     * 操作人id
     */
    private String personalId;
    /**
     * 当前页
     */
    private String page = "1" ;
    /**
     * 每页多少条
     */
    private String limit = "10";

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getIndustryId() {
        return industryId;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getBeginMoney() {
        return beginMoney;
    }

    public void setBeginMoney(String beginMoney) {
        this.beginMoney = beginMoney;
    }

    public String getEndMoney() {
        return endMoney;
    }

    public void setEndMoney(String endMoney) {
        this.endMoney = endMoney;
    }

    public String getCardCount() {
        return cardCount;
    }

    public void setCardCount(String cardCount) {
        this.cardCount = cardCount;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
