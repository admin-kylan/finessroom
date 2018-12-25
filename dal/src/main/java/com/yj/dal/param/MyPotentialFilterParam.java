package com.yj.dal.param;

import java.util.Date;

/**
 * 我的潜在客户参数
 */
public class MyPotentialFilterParam {
    /**
     * 客户等级
     */
    private Integer customerLevel;
    /**
     * 服务会计
     */
    private String fwhjNameId;
    /**
     * 跟进人ID
     */
    private String followPerson;
    /**
     * 筛选日期类型：0.下次跟进日期时间、1.计划来访时间、2.实际来访时间、3.计划购买时间、4.建档日期时间、5.客户认领时间、6.客户分配时间
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
     * 搜索关键字：手机号码、姓名 跟进内容关键字,意向卡类别,意向卡名称
     */
    private String keyword;
    /**
     * 客户代码
     */
    private String customerCode;
    /**
     * 门店id
     */
    private String shopId;
    /**
     * 操作人
     */
    private String personalId;
    /**
     * 多少天无人跟进
     */
    private Integer noFollow;
    /**
     * 当前页
     */
    private String page = "1";
    /**
     * 每页多少条
     */
    private String limit = "10";

    /**
     * 保护天数
     *
     * @return
     */
    private String protectDay;

    /**
     * 客户类型
     *
     * @return
     */
    private String clientType;

    /**
     * 当前日期
     *
     * @return
     */
    private String newDate;

    public String getNewDate() {
        return newDate;
    }

    public void setNewDate(String newDate) {
        this.newDate = newDate;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String getProtectDay() {
        return protectDay;
    }

    public void setProtectDay(String protectDay) {
        this.protectDay = protectDay;
    }

    public Integer getNoFollow() {
        return noFollow;
    }

    public void setNoFollow(Integer noFollow) {
        this.noFollow = noFollow;
    }

    public Integer getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(Integer customerLevel) {
        this.customerLevel = customerLevel;
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

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
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

    public String getFwhjNameId() {
        return fwhjNameId;
    }

    public void setFwhjNameId(String fwhjNameId) {
        this.fwhjNameId = fwhjNameId;
    }

    public String getFollowPerson() {
        return followPerson;
    }

    public void setFollowPerson(String followPerson) {
        this.followPerson = followPerson;
    }
}
