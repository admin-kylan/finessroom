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
 * 项目启用操作记录表
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-29
 */
@TableName("fr_project_start_record")
public class FrProjectStartRecord extends Model<FrProjectStartRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户代码 （方便客户导出资料）
     */
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    /**
     * id
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /**
     * 用户id
     */
    private String clientId;
    /**
     * 用户名字
     */
    private String clientName;
    /**
     * 操作时间
     */
    private Date operateDate;
    /**
     * 操作人Id
     */
    private String operatePersonId;
    /**
     * 操作人名字
     */
    private String operatePersonName;
    /**
     * 项目Id
     */
    private String projectOrderId;
    /**
     * 项目Name
     */
    private String courseName;
    /**
     * 开始时间
     */
    private Date startDate;
    /**
     * 结束时间
     */
    private Date endDate;
    /**
     * 原来的开始时间
     */
    private Date oldStartDate;
    /**
     * 原来的结束时间
     */
    private Date oldEndDate;
    /**
     * 1|可用 0|删除
     */
    @TableField("is_using")
    private Integer isUsing;
    @TableField("create_user")
    private String createUser;
    @TableField("update_user")
    private String updateUser;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

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

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public String getOperatePersonId() {
        return operatePersonId;
    }

    public void setOperatePersonId(String operatePersonId) {
        this.operatePersonId = operatePersonId;
    }

    public String getOperatePersonName() {
        return operatePersonName;
    }

    public void setOperatePersonName(String operatePersonName) {
        this.operatePersonName = operatePersonName;
    }

    public String getProjectOrderId() {
        return projectOrderId;
    }

    public void setProjectOrderId(String projectOrderId) {
        this.projectOrderId = projectOrderId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getOldStartDate() {
        return oldStartDate;
    }

    public void setOldStartDate(Date oldStartDate) {
        this.oldStartDate = oldStartDate;
    }

    public Date getOldEndDate() {
        return oldEndDate;
    }

    public void setOldEndDate(Date oldEndDate) {
        this.oldEndDate = oldEndDate;
    }

    public Integer getIsUsing() {
        return isUsing;
    }

    public void setIsUsing(Integer isUsing) {
        this.isUsing = isUsing;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public String toString() {
        return "FrProjectStartRecord{" +
        ", CustomerCode=" + CustomerCode +
        ", id=" + id +
        ", operateDate=" + operateDate +
        ", operatePersonId=" + operatePersonId +
        ", operatePersonName=" + operatePersonName +
        ", projectOrderId=" + projectOrderId +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        ", oldStartDate=" + oldStartDate +
        ", oldEndDate=" + oldEndDate +
        ", isUsing=" + isUsing +
        ", createUser=" + createUser +
        ", updateUser=" + updateUser +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
