package com.yj.dal.model;

import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;
import java.util.Date;

public class AddConsumeProject extends Model<AddConsumeProject> {
    private static final long serialVersionUID = 1L;
    //客户代码 （方便客户导出资料）
    private String CustomerCode;
    //id
    private String id;
    //后台ProjectSeriousProject
    private String ProjectSeriousProjectId;
    //增购项目ID
    private String AddProjectId;
    //门店ID
    private String ShopId;
    //场馆ID
    private String SdadiumId;
    //项目ID
    private String ProjectId;
    //指定数量 0 不限制数量
    private Integer ReferCount;
    //每天限用数量
    private Integer EveryDayCount;
    //开始时间
    private Date StartTime;
    //结束时间
    private Date EndTime;
    //创建时间
    private Date CreateTime;
    //状态
    private Integer State;
    //剩余数量
    private Integer SurplusCount;
    //已消费的数量
    private Integer ExpenseCount;

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String customerCode) {
        CustomerCode = customerCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectSeriousProjectId() {
        return ProjectSeriousProjectId;
    }

    public void setProjectSeriousProjectId(String projectSeriousProjectId) {
        ProjectSeriousProjectId = projectSeriousProjectId;
    }

    public String getAddProjectId() {
        return AddProjectId;
    }

    public void setAddProjectId(String addProjectId) {
        AddProjectId = addProjectId;
    }

    public String getShopId() {
        return ShopId;
    }

    public void setShopId(String shopId) {
        ShopId = shopId;
    }

    public String getSdadiumId() {
        return SdadiumId;
    }

    public void setSdadiumId(String sdadiumId) {
        SdadiumId = sdadiumId;
    }

    public String getProjectId() {
        return ProjectId;
    }

    public void setProjectId(String projectId) {
        ProjectId = projectId;
    }

    public Integer getReferCount() {
        return ReferCount;
    }

    public void setReferCount(Integer referCount) {
        ReferCount = referCount;
    }

    public Integer getEveryDayCount() {
        return EveryDayCount;
    }

    public void setEveryDayCount(Integer everyDayCount) {
        EveryDayCount = everyDayCount;
    }

    public Date getStartTime() {
        return StartTime;
    }

    public void setStartTime(Date startTime) {
        StartTime = startTime;
    }

    public Date getEndTime() {
        return EndTime;
    }

    public void setEndTime(Date endTime) {
        EndTime = endTime;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public Integer getState() {
        return State;
    }

    public void setState(Integer state) {
        State = state;
    }

    public Integer getSurplusCount() {
        return SurplusCount;
    }

    public void setSurplusCount(Integer surplusCount) {
        SurplusCount = surplusCount;
    }

    public Integer getExpenseCount() {
        return ExpenseCount;
    }

    public void setExpenseCount(Integer expenseCount) {
        ExpenseCount = expenseCount;
    }
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "AddConsumeProject{" +
                "CustomerCode='" + CustomerCode + '\'' +
                ", id='" + id + '\'' +
                ", ProjectSeriousProjectId='" + ProjectSeriousProjectId + '\'' +
                ", AddProjectId='" + AddProjectId + '\'' +
                ", ShopId='" + ShopId + '\'' +
                ", SdadiumId='" + SdadiumId + '\'' +
                ", ProjectId='" + ProjectId + '\'' +
                ", ReferCount=" + ReferCount +
                ", EveryDayCount=" + EveryDayCount +
                ", StartTime=" + StartTime +
                ", EndTime=" + EndTime +
                ", CreateTime='" + CreateTime + '\'' +
                ", State=" + State +
                ", SurplusCount=" + SurplusCount +
                ", ExpenseCount=" + ExpenseCount +
                '}';
    }
}