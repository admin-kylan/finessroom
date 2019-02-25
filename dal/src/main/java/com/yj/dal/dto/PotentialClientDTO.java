package com.yj.dal.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈现有客户列表筛选参数〉
 *
 * @author 欧俊俊
 * @create 2018/9/15
 */
public class PotentialClientDTO {

    /**
     * 客户id
     */
    private String id;

    /**
     * 头像
     */
    private String picLink;
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
     * 微信
     */
    private String wechat;
    /**
     * 总联系数
     */
    private Integer contactCount;
    /**
     * 建档时间
     */
    @JSONField(format = "yyyy-MM-dd hh:mm")
    private Date createTime;
    /**
     * 最近跟进时间
     */
    @JSONField(format = "yyyy-MM-dd hh:mm")
    private Date followTime;
    /**
     * 客户等级
     */
    private String vipLevel;
    /**
     * 客户来源id
     */
    private String sourceId;
    /**
     * 客户来源
     */
    private String customerSource;
    /**
     * 购买意向
     */
    private String purchaseWill;
    /**
     * 意向卡类别
     */
    private String willingCardType;
    /**
     * 意向会员卡
     */
    private String willingCardName;
    /**
     * 意向价格
     */
    private Double willingPrice;
    /**
     * 下次跟进时间
     */
    @JSONField(format = "yyyy-MM-dd hh:mm")
    private Date nextFollowTime;
    /**
     * 计划访问时间
     */
    @JSONField(format = "yyyy-MM-dd hh:mm")
    private Date planVisitTime;
    /**
     * 最近访问时间
     */
    @JSONField(format = "yyyy-MM-dd hh:mm")
    private Date visitingTime;
    /**
     * 计划购买时间
     */
    @JSONField(format = "yyyy-MM-dd hh:mm")
    private Date planPurchaseTime;
    /**
     * 推荐人
     */
    private String referenceName;
    /**
     * 服务会籍
     */
    private String fwhjName;
    /**
     * 是否预售
     */
    private Integer presaleStatus;
    /**
     * 销售顾问id
     */
    private String consultantId;
    /**
     * 销售顾问
     */
    private String consultantName;
    /**
     * 跟进人
     */
    private String followName;
    /**
     * 操作人id
     */
    private String updateUserId;
    /**
     * 操作人
     */
    private String updateUserName;

    /**
     * 门店名称
     */
    private String shopName;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getPresaleStatus() {
        return presaleStatus;
    }

    public void setPresaleStatus(Integer presaleStatus) {
        this.presaleStatus = presaleStatus;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(String vipLevel) {
        this.vipLevel = vipLevel;
    }

    public String getCustomerSource() {
        return customerSource;
    }

    public void setCustomerSource(String customerSource) {
        this.customerSource = customerSource;
    }

    public String getPurchaseWill() {
        return purchaseWill;
    }

    public void setPurchaseWill(String purchaseWill) {
        this.purchaseWill = purchaseWill;
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

    public String getPicLink() {
        return picLink;
    }

    public void setPicLink(String picLink) {
        this.picLink = picLink;
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

    public Integer getContactCount() {
        return contactCount;
    }

    public void setContactCount(Integer contactCount) {
        this.contactCount = contactCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Date followTime) {
        this.followTime = followTime;
    }

    public Date getNextFollowTime() {
        return nextFollowTime;
    }

    public void setNextFollowTime(Date nextFollowTime) {
        this.nextFollowTime = nextFollowTime;
    }

    public Date getPlanVisitTime() {
        return planVisitTime;
    }

    public void setPlanVisitTime(Date planVisitTime) {
        this.planVisitTime = planVisitTime;
    }

    public Date getVisitingTime() {
        return visitingTime;
    }

    public void setVisitingTime(Date visitingTime) {
        this.visitingTime = visitingTime;
    }

    public Date getPlanPurchaseTime() {
        return planPurchaseTime;
    }

    public void setPlanPurchaseTime(Date planPurchaseTime) {
        this.planPurchaseTime = planPurchaseTime;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public String getFwhjName() {
        return fwhjName;
    }

    public void setFwhjName(String fwhjName) {
        this.fwhjName = fwhjName;
    }

    public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }

    public String getConsultantName() {
        return consultantName;
    }

    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }

    public String getFollowName() {
        return followName;
    }

    public void setFollowName(String followName) {
        this.followName = followName;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }
}
