package com.yj.dal.param;

/**
 * 〈一句话功能简述〉<br>
 * 〈我的现有客户列表筛选参数〉
 */
public class MyExistenceFilterParam {
    /**
     * 会员等级
     */
    private String levelId;
    /**
     * 会员卡多少天到期
     */
    private Integer expireDay;
    /**
     * 跟进人ID
     */
    private String followPerson;

    /**
     * 多少天无人跟进
     */
    private Integer noFollow;
    /**
     * 超过多少天无人跟进
     */
    private Integer noFollowDay;
    /**
     * 筛选日期类型：申请时间、开卡时间、失效日期、客户认领、客户分配、下次跟进日期
     */
    private String dateType;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 搜索关键字：手机号码、跟进内容、姓名
     */
    private String keyword;
    /**
     * 客户代码
     */
    private String customerCode;
    /**
     * 当前操作人id
     */
    private String personalId;
    /**
     * 门店id
     */
    private String shopId;
    /**
     * 当前页
     */
    private String page = "1" ;
    /**
     * 每页多少条
     */
    private String limit = "10";

    /**
     * 客户类型 1.今日到访 2.今日生日 3.本月未到访 4.已设置提醒 5.未设置提醒 6.7天内卡到期 7.今日待跟进 8.昨天跟进
     */
    private String clientType;

    /**
     * 当前日期
     */
    private String newDate;

    /**
     * 多少天内未消费
     */
    private String noConsumption;
    /**
     * 消费门店
     */
    private String consumerStore;
    /**
     * 消费场馆
     */
    private String consumptionVenues;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getFollowPerson() {
        return followPerson;
    }

    public void setFollowPerson(String followPerson) {
        this.followPerson = followPerson;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public Integer getExpireDay() {
        return expireDay;
    }

    public void setExpireDay(Integer expireDay) {
        this.expireDay = expireDay;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public Integer getNoFollow() {
        return noFollow;
    }

    public void setNoFollow(Integer noFollow) {
        this.noFollow = noFollow;
    }

    public Integer getNoFollowDay() {
        return noFollowDay;
    }

    public void setNoFollowDay(Integer noFollowDay) {
        this.noFollowDay = noFollowDay;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

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

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getNewDate() {
        return newDate;
    }

    public void setNewDate(String newDate) {
        this.newDate = newDate;
    }

    public String getNoConsumption() {
        return noConsumption;
    }

    public void setNoConsumption(String noConsumption) {
        this.noConsumption = noConsumption;
    }

    public String getConsumerStore() {
        return consumerStore;
    }

    public void setConsumerStore(String consumerStore) {
        this.consumerStore = consumerStore;
    }

    public String getConsumptionVenues() {
        return consumptionVenues;
    }

    public void setConsumptionVenues(String consumptionVenues) {
        this.consumptionVenues = consumptionVenues;
    }
}
