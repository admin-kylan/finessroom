package com.yj.dal.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 认领客户
 */
public class CollarClientDTO {

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
     * 所属门店
     */
    private String shopName;
    /**
     * 手动跟进
     */
    private Integer manualCount;
    /**
     * 自动跟进
     */
    private Integer automaticCount;
    /**
     * 建档时间
     */
    @JSONField(format = "yyyy-MM-dd hh:mm")
    private Date buildDate;
    /**
     * 服务会籍
     */
    private String fwhjName;
    /**
     * 服务会籍电话
     */
    private String fwhjTel;
    /**
     * 销售顾问
     */
    private String consultantName;
    /**
     * /**
     * 销售顾问ID
     */
    private String consultantId;
    /**
     */
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
     * 无人跟进天数
     * @return
     */
    private Integer onFollow;
    /**
     * 客户类型 客户分配
     */
    private String clientType;

    /**
     * 操作人  客户分配
     */
    private String operator;

    public Integer getOnFollow() {
        return onFollow;
    }

    public void setOnFollow(Integer onFollow) {
        this.onFollow = onFollow;
    }

    public Integer getAutomaticCount() {
        return automaticCount;
    }

    public void setAutomaticCount(Integer automaticCount) {
        this.automaticCount = automaticCount;
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

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getManualCount() {
        return manualCount;
    }

    public void setManualCount(Integer manualCount) {
        this.manualCount = manualCount;
    }

    public Date getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(Date buildDate) {
        this.buildDate = buildDate;
    }

    public String getFwhjName() {
        return fwhjName;
    }

    public void setFwhjName(String fwhjName) {
        this.fwhjName = fwhjName;
    }

    public String getFwhjTel() {
        return fwhjTel;
    }

    public void setFwhjTel(String fwhjTel) {
        this.fwhjTel = fwhjTel;
    }

    public String getConsultantName() {
        return consultantName;
    }

    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }

    public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
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

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
