package com.yj.dal.param;

/**
 * 〈一句话功能简述〉<br>
 * 〈现有客户列表筛选参数〉
 *
 * @author 欧俊俊
 * @create 2018/9/15
 */
public class PotentialFilterParam {
    /**
     * 客户等级
     */
    private Integer customerLevel;
    /**
     * 购买意向
     */
    private Integer purchaseWill;
    /**
     * 来源类型
     */
    private String sourceId;
    /**
     *  是否有推荐人
     */
    private Integer  isReferrer;
    /**
     *  预售状态
     */
    private Integer presaleStatus;
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
     * 销售顾问/教练名字
     */
    private String consultantName;
    /**
     * 操作人姓名
     */
    private String updateUserName;
    /**
     * 搜索关键字：手机号码、姓名
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


    public Integer getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(Integer customerLevel) {
        this.customerLevel = customerLevel;
    }

    public Integer getPurchaseWill() {
        return purchaseWill;
    }

    public void setPurchaseWill(Integer purchaseWill) {
        this.purchaseWill = purchaseWill;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getIsReferrer() {
        return isReferrer;
    }

    public void setIsReferrer(Integer referrer) {
        isReferrer = referrer;
    }

    public Integer getPresaleStatus() {
        return presaleStatus;
    }

    public void setPresaleStatus(Integer presaleStatus) {
        this.presaleStatus = presaleStatus;
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

    public String getConsultantName() {
        return consultantName;
    }

    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
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

    @Override
    public String toString() {
        return "PotentialFilterParam{" +
                "customerLevel=" + customerLevel +
                ", purchaseWill=" + purchaseWill +
                ", sourceId='" + sourceId + '\'' +
                ", isReferrer=" + isReferrer +
                ", presaleStatus=" + presaleStatus +
                ", dateType='" + dateType + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", consultantName='" + consultantName + '\'' +
                ", updateUserName='" + updateUserName + '\'' +
                ", keyword='" + keyword + '\'' +
                ", customerCode='" + customerCode + '\'' +
                ", shopId='" + shopId + '\'' +
                ", page='" + page + '\'' +
                ", limit='" + limit + '\'' +
                '}';
    }
}
