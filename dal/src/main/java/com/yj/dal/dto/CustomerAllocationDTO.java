package com.yj.dal.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class CustomerAllocationDTO {
    /**
     * 客户类型 客户分配
     */
    private String clientType;
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
     * 建档时间
     */
    @JSONField(format = "yyyy-MM-dd hh:mm")
    private Date buildDate;
    /**
     */
    /**
     * 最近跟进日期
     */
    @JSONField(format = "yyyy-MM-dd hh:mm")
    private Date followTime;
    /**
     * 手动跟进
     */
    private Integer manualCount;
    /**
     * 自动跟进
     */
    private Integer automaticCount;

    /**
     * 跟进内容
     */
    private String followContent;

    /**
     * 无人跟进天数
     *
     * @return
     */
    private Integer onFollow;

    /**
     * 销售顾问
     */
    private String consultantName;
    /**
     * 服务会籍
     */
    private String fwhjName;
    /**
     * 操作人  客户分配
     */
    private String operator;

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
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

    public Date getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(Date buildDate) {
        this.buildDate = buildDate;
    }

    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Date followTime) {
        this.followTime = followTime;
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

    public void setAutomaticCount(Integer automaticCount) {
        this.automaticCount = automaticCount;
    }

    public String getFollowContent() {
        return followContent;
    }

    public void setFollowContent(String followContent) {
        this.followContent = followContent;
    }

    public Integer getOnFollow() {
        return onFollow;
    }

    public void setOnFollow(Integer onFollow) {
        this.onFollow = onFollow;
    }

    public String getConsultantName() {
        return consultantName;
    }

    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }

    public String getFwhjName() {
        return fwhjName;
    }

    public void setFwhjName(String fwhjName) {
        this.fwhjName = fwhjName;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
