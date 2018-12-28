package com.yj.dal.param;

import com.yj.dal.model.FrPlanClass;

import java.util.List;
import java.util.Map;

public class AddProjectParam {

    private List<FrPlanClass> actionInfo;
    private String fid;
    //私教项目
    private String project;
    //训练日期
    private String trainDate;
    //训练开始时间
    private String trainStartDate;
    //训练结束时间
    private String trainEndDate;
    //改进方案
    private String improvementPlan;
    //课程详情
    private String[] classIds;
    //客户ID
    private String cid;
    //实际时间
    private String actualStartDate;
    //实际开始时间
    private String actualEndDate;
    //实际结束时间
    private String actualDate;
    //会员自我感觉
    private String memberFeel;
    //教练小结
    private String coachSummary;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public List<FrPlanClass> getActionInfo() {
        return actionInfo;
    }

    public void setActionInfo(List<FrPlanClass> actionInfo) {
        this.actionInfo = actionInfo;
    }

    public String getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(String actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public String getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(String actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    public String getActualDate() {
        return actualDate;
    }

    public void setActualDate(String actualDate) {
        this.actualDate = actualDate;
    }

    public String getMemberFeel() {
        return memberFeel;
    }

    public void setMemberFeel(String memberFeel) {
        this.memberFeel = memberFeel;
    }

    public String getCoachSummary() {
        return coachSummary;
    }

    public void setCoachSummary(String coachSummary) {
        this.coachSummary = coachSummary;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getTrainDate() {
        return trainDate;
    }

    public void setTrainDate(String trainDate) {
        this.trainDate = trainDate;
    }

    public String getTrainStartDate() {
        return trainStartDate;
    }

    public void setTrainStartDate(String trainStartDate) {
        this.trainStartDate = trainStartDate;
    }

    public String getTrainEndDate() {
        return trainEndDate;
    }

    public void setTrainEndDate(String trainEndDate) {
        this.trainEndDate = trainEndDate;
    }

    public String getImprovementPlan() {
        return improvementPlan;
    }

    public void setImprovementPlan(String improvementPlan) {
        this.improvementPlan = improvementPlan;
    }

    public String[] getClassIds() {
        return classIds;
    }

    public void setClassIds(String[] classIds) {
        this.classIds = classIds;
    }
}
