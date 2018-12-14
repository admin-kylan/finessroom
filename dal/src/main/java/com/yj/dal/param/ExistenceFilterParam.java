package com.yj.dal.param;

/**
 * 〈一句话功能简述〉<br>
 * 〈现有客户列表筛选参数〉
 *
 * @author 欧俊俊
 * @create 2018/9/15
 */
public class ExistenceFilterParam {
    /**
     * 会员等级
     */
    private String levelId;
    /**
     * 会员卡状态
     */
    private String status;
    /**
     * 销售顾问/教练
     */
    private String coachName;
    /**
     *  套餐
     */
    private String meal;
    /**
     * 保护天数
     */
    private Integer protectDay;
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
     * 搜索关键字：手机号码、会员卡号、姓名
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
     * 当前页
     */
    private String page = "1" ;
    /**
     * 每页多少条
     */
    private String limit = "10";

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

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public Integer getProtectDay() {
        return protectDay;
    }

    public void setProtectDay(Integer protectDay) {
        this.protectDay = protectDay;
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
}
