package com.yj.dal.param;

import java.util.Date;

/**
 * 未认领客户参数
 */
public class CollarClientParam {

    private String type = null;

    /**
     * 服务会计
     */
    private String fwhjNameId;
    /**
     * 销售顾问ID
     */
    private String consultantId;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 教练
     */
    private String personalId;
    /**
     * 筛选日期类型：0.建档日期时间、1.更新时间
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
     * 搜索关键字：手机号码、姓名
     */
    private String keyword;
    /**
     * 客户代码
     */
    private String customerCode;
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
     * 手动跟进次数
     */
    private Integer manualCount;
    /**
     * 自动跟进次数
     */
    private Integer automaticCount;

    /**
     * 门店ID
     */
    private String shopId;

    /**
     * 当前时间
     */
    private String newDate;

    /**
     * 客户类型  客户分配参数
     */
    private String clientType;

    /**
     * 条件教练类型  客户分配参数
     */
    private String coachId;
    /**
     * 条件门店类型  客户分配参数
     */
    private String storeId;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public void setAutomaticCount(Integer automaticCount) {
        this.automaticCount = automaticCount;
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

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getFwhjNameId() {
        return fwhjNameId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFwhjNameId(String fwhjNameId) {
        this.fwhjNameId = fwhjNameId;
    }

    public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
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

    public Integer getNoFollow() {
        return noFollow;
    }

    public void setNoFollow(Integer noFollow) {
        this.noFollow = noFollow;
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

    public Integer getManualCount() {
        return manualCount;
    }

    public void setManualCount(Integer manualCount) {
        this.manualCount = manualCount;
    }

    public Integer getAutomaticCount() {
        return automaticCount;
    }
}