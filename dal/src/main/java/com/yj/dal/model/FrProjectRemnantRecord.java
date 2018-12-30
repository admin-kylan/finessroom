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
 * 项目补余操作记录表
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-29
 */
@TableName("fr_project_remnant_record")
public class FrProjectRemnantRecord extends Model<FrProjectRemnantRecord> {

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
     * 1|可用 0|删除
     */
    @TableField("is_using")
    private Integer isUsing;
    /**
     * 欠款金额
     */
    private Double arrearsPrice;
    /**
     * 补余金额
     */
    private Double remnantPrice;
    @TableField("create_user")
    private String createUser;
    @TableField("update_user")
    private String updateUser;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


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

    public Integer getIsUsing() {
        return isUsing;
    }

    public void setIsUsing(Integer isUsing) {
        this.isUsing = isUsing;
    }

    public Double getArrearsPrice() {
        return arrearsPrice;
    }

    public void setArrearsPrice(Double arrearsPrice) {
        this.arrearsPrice = arrearsPrice;
    }

    public Double getRemnantPrice() {
        return remnantPrice;
    }

    public void setRemnantPrice(Double remnantPrice) {
        this.remnantPrice = remnantPrice;
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

    @Override
    public String toString() {
        return "FrProjectRemnantRecord{" +
        ", CustomerCode=" + CustomerCode +
        ", id=" + id +
        ", operateDate=" + operateDate +
        ", operatePersonId=" + operatePersonId +
        ", operatePersonName=" + operatePersonName +
        ", projectOrderId=" + projectOrderId +
        ", isUsing=" + isUsing +
        ", arrearsPrice=" + arrearsPrice +
        ", remnantPrice=" + remnantPrice +
        ", createUser=" + createUser +
        ", updateUser=" + updateUser +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
