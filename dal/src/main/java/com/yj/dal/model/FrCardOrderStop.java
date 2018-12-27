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
 * 会员卡 停止/冻结订单
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-20
 */
@TableName("fr_card_order_stop")
public class FrCardOrderStop extends Model<FrCardOrderStop> {

    private static final long serialVersionUID = 1L;

    /**
     * 会员卡ID
     */
    @TableField("card_id")
    private String cardId;
    /**
     * 停止开始时间
     */
    @TableField("start_time")
    private Date startTime;
    /**
     * 预计停止结束时间
     */
    @TableField("est_end_time")
    private Date estEndTime;
    /**
     * 原因
     */
    private String flag;
    /**
     * 人员（员工）ID
     */
    @TableField("personnel_id")
    private String personnelId;
    /**
     * 实际停卡结束时间
     */
    @TableField("end_time")
    private Date endTime;
    /**
     * 预计停卡时长
     */
    @TableField("est_stop_time")
    private Integer estStopTime;
    /**
     * 实际停卡时长
     */
    @TableField("stop_time")
    private Integer stopTime;
    /**
     * 失效时间
     */
    @TableField("invalid_time")
    private String invalidTime;
    /**
     * 收费
     */
    @TableField("total_price")
    private Double totalPrice;
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
     * 创建人姓名
     */
    @TableField(value = "create_user_name", fill = FieldFill.INSERT)
    private String createUserName;
    /**
     * 更新人姓名
     */
    @TableField(value = "update_user_name", fill = FieldFill.INSERT_UPDATE)
    private String updateUserName;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 财务审核 （0，待审核1，已审核，2，审核不通过）
     */
    private Integer status;
    /**
     * 相关复核 （0，待审核1，已审核，2，审核不通过）
     */
    @TableField("audit_status")
    private Integer auditStatus;
    /**
     * 审核人员ID
     */
    @TableField("status_user_id")
    private String statusUserId;
    /**
     * 复核人员ID
     */
    @TableField("audit_user_id")
    private String auditUserId;
    /**
     * 是否启用(0：不启用 1：启用）默认1
     */
    @TableField("is_using")
    private Boolean isUsing;
    /**
     * 客户ID
     */
    @TableField("client_id")
    private String clientId;
    /**
     * 订单状态（0，待付款、1、已付款；2、已冲销）
     */
    @TableField("price_status")
    private Integer priceStatus;
    /**
     * 状态（1：停卡、2、冻结）
     */
    private Integer type;
    /**
     * 状态（0: 正常 1：停卡/冻结 2：终止/解冻）
     */
    @TableField("stop_status")
    private Integer stopStatus;
    /**
     * 终止操作人员ID
     */
    @TableField("stop_user_id")
    private String stopUserId;
    @TableId(value = "id", type = IdType.UUID)
    private String id;


    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEstEndTime() {
        return estEndTime;
    }

    public void setEstEndTime(Date estEndTime) {
        this.estEndTime = estEndTime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(String personnelId) {
        this.personnelId = personnelId;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getEstStopTime() {
        return estStopTime;
    }

    public void setEstStopTime(Integer estStopTime) {
        this.estStopTime = estStopTime;
    }

    public Integer getStopTime() {
        return stopTime;
    }

    public void setStopTime(Integer stopTime) {
        this.stopTime = stopTime;
    }

    public String getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(String invalidTime) {
        this.invalidTime = invalidTime;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getStatusUserId() {
        return statusUserId;
    }

    public void setStatusUserId(String statusUserId) {
        this.statusUserId = statusUserId;
    }

    public String getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(String auditUserId) {
        this.auditUserId = auditUserId;
    }

    public Boolean getUsing() {
        return isUsing;
    }

    public void setUsing(Boolean isUsing) {
        this.isUsing = isUsing;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Integer getPriceStatus() {
        return priceStatus;
    }

    public void setPriceStatus(Integer priceStatus) {
        this.priceStatus = priceStatus;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStopStatus() {
        return stopStatus;
    }

    public void setStopStatus(Integer stopStatus) {
        this.stopStatus = stopStatus;
    }

    public String getStopUserId() {
        return stopUserId;
    }

    public void setStopUserId(String stopUserId) {
        this.stopUserId = stopUserId;
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
        return "FrCardOrderStop{" +
                ", cardId=" + cardId +
                ", startTime=" + startTime +
                ", estEndTime=" + estEndTime +
                ", flag=" + flag +
                ", personnelId=" + personnelId +
                ", endTime=" + endTime +
                ", estStopTime=" + estStopTime +
                ", stopTime=" + stopTime +
                ", invalidTime=" + invalidTime +
                ", totalPrice=" + totalPrice +
                ", CustomerCode=" + CustomerCode +
                ", createUserId=" + createUserId +
                ", updateUserId=" + updateUserId +
                ", createUserName=" + createUserName +
                ", updateUserName=" + updateUserName +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", auditStatus=" + auditStatus +
                ", statusUserId=" + statusUserId +
                ", auditUserId=" + auditUserId +
                ", isUsing=" + isUsing +
                ", clientId=" + clientId +
                ", priceStatus=" + priceStatus +
                ", type=" + type +
                ", stopStatus=" + stopStatus +
                ", stopUserId=" + stopUserId +
                ", id=" + id +
                "}";
    }
}
