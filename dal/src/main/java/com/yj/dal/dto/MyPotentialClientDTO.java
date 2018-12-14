package com.yj.dal.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class MyPotentialClientDTO {

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
     * 保护天数
     */
    private Integer protectDay;
    /**
     * 微信
     */
    private String wechat;
    /**
     * 总联系数
     */
    private Integer contactCount;
    /**
     * 客户等级
     */
    private String vipLevel;
    /**
     * 意向门店
     */
    private String shopName;
    /**
     * 客户来源
     */
    private String customerSource;

    /**
     * 跟进总次数
     */
    private Integer followCount;
    /**
     * 最早消费时间
     */
    @JSONField(format = "yyyy-MM-dd hh:mm")
    private Date firstConsumption;
    /**
     * 最近消费时间
     */
    @JSONField(format = "yyyy-MM-dd hh:mm")
    private Date recentlyConsumption;
    /**
     * 消费总数
     */
    private String consumptionCount;
    /**
     * 频率（天/次)
     */
    private String frequency;
    /**
     * 建档时间
     */
    @JSONField(format = "yyyy-MM-dd hh:mm")
    private Date buildDate;
    /**
     * 最近来访时间
     */
    @JSONField(format = "yyyy-MM-dd hh:mm")
    private Date visitingTime;
    /**
     * 购买意向
     */
    private String purchaseWill;
    /**
     * 服务会籍
     */
    private String fwhjName;

    /**
     * 跟进人
     */
    private String followName;
    /**
     * 首次跟进时间
     */
    @JSONField(format = "yyyy-MM-dd hh:mm")
    private Date firstFollowTime;
    /**
     * 最近跟进日期
     */
    @JSONField(format = "yyyy-MM-dd hh:mm")
    private Date followTime;
    /**
     * 跟进内容
     */
    private String followContent;
    /**
     * 意向卡类别
     */
    private String willingCardType;
    /**
     * 意向卡名称
     */
    private String willingCardName;
    /**
     * 意向价格
     */
    private Double willingPrice;
    /**
     * 客户id
     */
    private String id;
    /**
     * 服务会籍电话
     */
    private String fwhjTel;
    /**
     * 客户来源id
     */
    private String sourceId;


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

    public Integer getProtectDay() {
        return protectDay;
    }

    public void setProtectDay(Integer protectDay) {
        this.protectDay = protectDay;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public Integer getContactCount() {
        return contactCount;
    }

    public void setContactCount(Integer contactCount) {
        this.contactCount = contactCount;
    }

    public String getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(String vipLevel) {
        this.vipLevel = vipLevel;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getCustomerSource() {
        return customerSource;
    }

    public void setCustomerSource(String customerSource) {
        this.customerSource = customerSource;
    }

    public Integer getFollowCount() {
        return followCount;
    }

    public void setFollowCount(Integer followCount) {
        this.followCount = followCount;
    }

    public Date getFirstConsumption() {
        return firstConsumption;
    }

    public void setFirstConsumption(Date firstConsumption) {
        this.firstConsumption = firstConsumption;
    }

    public Date getRecentlyConsumption() {
        return recentlyConsumption;
    }

    public void setRecentlyConsumption(Date recentlyConsumption) {
        this.recentlyConsumption = recentlyConsumption;
    }

    public String getConsumptionCount() {
        return consumptionCount;
    }

    public void setConsumptionCount(String consumptionCount) {
        this.consumptionCount = consumptionCount;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Date getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(Date buildDate) {
        this.buildDate = buildDate;
    }

    public Date getVisitingTime() {
        return visitingTime;
    }

    public void setVisitingTime(Date visitingTime) {
        this.visitingTime = visitingTime;
    }

    public String getPurchaseWill() {
        return purchaseWill;
    }

    public void setPurchaseWill(String purchaseWill) {
        this.purchaseWill = purchaseWill;
    }

    public String getFwhjName() {
        return fwhjName;
    }

    public void setFwhjName(String fwhjName) {
        this.fwhjName = fwhjName;
    }

    public String getFollowName() {
        return followName;
    }

    public void setFollowName(String followName) {
        this.followName = followName;
    }

    public Date getFirstFollowTime() {
        return firstFollowTime;
    }

    public void setFirstFollowTime(Date firstFollowTime) {
        this.firstFollowTime = firstFollowTime;
    }

    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Date followTime) {
        this.followTime = followTime;
    }

    public String getFollowContent() {
        return followContent;
    }

    public void setFollowContent(String followContent) {
        this.followContent = followContent;
    }

    public String getWillingCardType() {
        return willingCardType;
    }

    public void setWillingCardType(String willingCardType) {
        this.willingCardType = willingCardType;
    }

    public String getWillingCardName() {
        return willingCardName;
    }

    public void setWillingCardName(String willingCardName) {
        this.willingCardName = willingCardName;
    }

    public Double getWillingPrice() {
        return willingPrice;
    }

    public void setWillingPrice(Double willingPrice) {
        this.willingPrice = willingPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFwhjTel() {
        return fwhjTel;
    }

    public void setFwhjTel(String fwhjTel) {
        this.fwhjTel = fwhjTel;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }
}
