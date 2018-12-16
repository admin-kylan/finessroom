package com.yj.dal.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class MyExistenceClientDTO {

    /**
     * 姓名
     */
    private String clientName;
    /**
     * 客户等级
     */
    private String levelName;
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
     * 保护天数
     */
    private Integer protectDay;

    /**
     * 总联系数
     */
    private Integer contactCount;


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
     * 服务会籍
     */
    private String fwhjName;

    /**
     * 销售顾问
     */
    private String consultantName;

    /**
     * 跟进人
     */
    private String followName;
    /**
     * 跟进总次数
     */
    private Integer followCount;

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
     * 客户id
     */
    private String id;
    /**
     * 跟进人id
     */
    private String personalId;
    /**
     * 等级ID
     */
    private String levelId;
    /**
     * 销售顾问ID
     */
    private String consultantId;
    /**
     * 教练
     */
    List<Map<String, String>> coachList;
    /**
     * 服务会籍电话
     */
    private String fwhjTel;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
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

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public Integer getProtectDay() {
        return protectDay;
    }

    public void setProtectDay(Integer protectDay) {
        this.protectDay = protectDay;
    }

    public Integer getContactCount() {
        return contactCount;
    }

    public void setContactCount(Integer contactCount) {
        this.contactCount = contactCount;
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

    public String getFwhjName() {
        return fwhjName;
    }

    public void setFwhjName(String fwhjName) {
        this.fwhjName = fwhjName;
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

    public Integer getFollowCount() {
        return followCount;
    }

    public void setFollowCount(Integer followCount) {
        this.followCount = followCount;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }

    public List<Map<String, String>> getCoachList() {
        return coachList;
    }

    public void setCoachList(List<Map<String, String>> coachList) {
        this.coachList = coachList;
    }

    public String getFwhjTel() {
        return fwhjTel;
    }

    public void setFwhjTel(String fwhjTel) {
        this.fwhjTel = fwhjTel;
    }
}
