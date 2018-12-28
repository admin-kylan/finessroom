package com.yj.dal.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 训练计划
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-20
 */
@TableName("fr_training_plan")
public class FrTrainingPlan extends Model<FrTrainingPlan> {

    private static final long serialVersionUID = 1L;

    /**
     * 私教项目
     */
    private String project;
    /**
     * 训练日期
     */
    @TableField("train_date")
    private Date trainDate;
    /**
     * 训练开始时间
     */
    @TableField("train_start_date")
    private Date trainStartDate;
    /**
     * 训练结束时间
     */
    @TableField("train_end_date")
    private Date trainEndDate;
    /**
     * 改进方案
     */
    @TableField("improvement_plan")
    private String improvementPlan;
    /**
     * 客户ID
     */
    @TableField("client_id")
    private String clientId;
    /**
     * 是否启用(0：不启用 1：启用）默认1
     */
    @TableField("is_using")
    private Boolean isUsing;
    /**
     * 创建人时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 创建人名称
     */
    @TableField(value = "create_user_name", fill = FieldFill.INSERT)
    private String createUserName;
    /**
     * 更新人名称
     */
    @TableField(value = "update_user_name", fill = FieldFill.INSERT_UPDATE)
    private String updateUserName;
    /**
     * 客户代码
     */
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    /**
     * 创建人ID
     */
    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    private String createUserId;
    /**
     * 更新人ID
     */
    @TableField(value = "update_user_id", fill = FieldFill.INSERT_UPDATE)
    private String updateUserId;
    /**
     * 实际时间
     */
    @TableField("actual_date")
    private Date actualDate;
    /**
     * 实际开始时间
     */
    @TableField("actual_start_date")
    private Date actualStartDate;
    /**
     * 实际结束时间
     */
    @TableField("actual_end_date")
    private Date actualEndDate;
    /**
     * 会员自我感觉
     */
    @TableField("member_feel")
    private String memberFeel;
    /**
     * 教练小结
     */
    @TableField("coach_summary")
    private String coachSummary;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    @TableField(exist = false)
    private List<FrPlanClass> frPlanClasses;

    public List<FrPlanClass> getFrPlanClasses() {
        return frPlanClasses;
    }

    public void setFrPlanClasses(List<FrPlanClass> frPlanClasses) {
        this.frPlanClasses = frPlanClasses;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Date getTrainDate() {
        return trainDate;
    }

    public void setTrainDate(Date trainDate) {
        this.trainDate = trainDate;
    }

    public Date getTrainStartDate() {
        return trainStartDate;
    }

    public void setTrainStartDate(Date trainStartDate) {
        this.trainStartDate = trainStartDate;
    }

    public Date getTrainEndDate() {
        return trainEndDate;
    }

    public void setTrainEndDate(Date trainEndDate) {
        this.trainEndDate = trainEndDate;
    }

    public String getImprovementPlan() {
        return improvementPlan;
    }

    public void setImprovementPlan(String improvementPlan) {
        this.improvementPlan = improvementPlan;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Boolean getUsing() {
        return isUsing;
    }

    public void setUsing(Boolean isUsing) {
        this.isUsing = isUsing;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getActualDate() {
        return actualDate;
    }

    public void setActualDate(Date actualDate) {
        this.actualDate = actualDate;
    }

    public Date getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(Date actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public Date getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(Date actualEndDate) {
        this.actualEndDate = actualEndDate;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrTrainingPlan{" +
        ", project=" + project +
        ", trainDate=" + trainDate +
        ", trainStartDate=" + trainStartDate +
        ", trainEndDate=" + trainEndDate +
        ", improvementPlan=" + improvementPlan +
        ", clientId=" + clientId +
        ", isUsing=" + isUsing +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", createUserName=" + createUserName +
        ", updateUserName=" + updateUserName +
        ", CustomerCode=" + CustomerCode +
        ", createUserId=" + createUserId +
        ", updateUserId=" + updateUserId +
        ", actualDate=" + actualDate +
        ", actualStartDate=" + actualStartDate +
        ", actualEndDate=" + actualEndDate +
        ", memberFeel=" + memberFeel +
        ", coachSummary=" + coachSummary +
        ", id=" + id +
        "}";
    }
}
