package com.yj.dal.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 增购项目表

 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-27
 */
@TableName("AddConsumeProject")
public class AddConsumeProject extends Model<AddConsumeProject> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户代码 （方便客户导出资料）
     */
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /**
     * 后台ProjectSeriousProject
     */
    private String ProjectSeriousProjectId;
    /**
     * 增购项目ID
     */
    private String AddProjectId;
    /**
     * 门店ID
     */
    private String ShopId;
    /**
     * 场馆ID
     */
    private String SdadiumId;
    /**
     * 项目ID
     */
    private String ProjectId;
    /**
     * 指定数量?0?不限制数量
     */
    private Integer ReferCount;
    /**
     * 每天限用数量
     */
    private Integer EveryDayCount;
    /**
     * 开始时间
     */
    private Date StartTime;
    /**
     * 结束时间
     */
    private Date EndTime;
    /**
     * 创建时间
     */
    private Date CreateTime;
    /**
     * 状态
     */
    private Integer State;
    /**
     * 剩余数量
     */
    private Integer SurplusCount;
    /**
     * 已消费的数量
     */
    private Integer ExpenseCount;


    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
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

    public void setProjectSeriousProjectId(String ProjectSeriousProjectId) {
        this.ProjectSeriousProjectId = ProjectSeriousProjectId;
    }

    public String getAddProjectId() {
        return AddProjectId;
    }

    public void setAddProjectId(String AddProjectId) {
        this.AddProjectId = AddProjectId;
    }

    public String getShopId() {
        return ShopId;
    }

    public void setShopId(String ShopId) {
        this.ShopId = ShopId;
    }

    public String getSdadiumId() {
        return SdadiumId;
    }

    public void setSdadiumId(String SdadiumId) {
        this.SdadiumId = SdadiumId;
    }

    public String getProjectId() {
        return ProjectId;
    }

    public void setProjectId(String ProjectId) {
        this.ProjectId = ProjectId;
    }

    public Integer getReferCount() {
        return ReferCount;
    }

    public void setReferCount(Integer ReferCount) {
        this.ReferCount = ReferCount;
    }

    public Integer getEveryDayCount() {
        return EveryDayCount;
    }

    public void setEveryDayCount(Integer EveryDayCount) {
        this.EveryDayCount = EveryDayCount;
    }

    public Date getStartTime() {
        return StartTime;
    }

    public void setStartTime(Date StartTime) {
        this.StartTime = StartTime;
    }

    public Date getEndTime() {
        return EndTime;
    }

    public void setEndTime(Date EndTime) {
        this.EndTime = EndTime;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }

    public Integer getState() {
        return State;
    }

    public void setState(Integer State) {
        this.State = State;
    }

    public Integer getSurplusCount() {
        return SurplusCount;
    }

    public void setSurplusCount(Integer SurplusCount) {
        this.SurplusCount = SurplusCount;
    }

    public Integer getExpenseCount() {
        return ExpenseCount;
    }

    public void setExpenseCount(Integer ExpenseCount) {
        this.ExpenseCount = ExpenseCount;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "AddConsumeProject{" +
        ", CustomerCode=" + CustomerCode +
        ", id=" + id +
        ", ProjectSeriousProjectId=" + ProjectSeriousProjectId +
        ", AddProjectId=" + AddProjectId +
        ", ShopId=" + ShopId +
        ", SdadiumId=" + SdadiumId +
        ", ProjectId=" + ProjectId +
        ", ReferCount=" + ReferCount +
        ", EveryDayCount=" + EveryDayCount +
        ", StartTime=" + StartTime +
        ", EndTime=" + EndTime +
        ", CreateTime=" + CreateTime +
        ", State=" + State +
        ", SurplusCount=" + SurplusCount +
        ", ExpenseCount=" + ExpenseCount +
        "}";
    }
}
