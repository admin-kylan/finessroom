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
 * 会员卡 转让订单
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-05
 */
@TableName("fr_card_order_transfer")
public class FrCardOrderTransfer extends Model<FrCardOrderTransfer> {

    private static final long serialVersionUID = 1L;

    /**
     * 会员卡ID
     */
    @TableField("card_id")
    private String cardId;
    /**
     * 基础表门店ID
     */
    @TableField("shop_id")
    private String shopId;
    /**
     * 转让人姓名
     */
    @TableField("transfer_user_name")
    private String transferUserName;
    /**
     * 转让的客户ID
     */
    @TableField("transfer_client_id")
    private String transferClientId;
    /**
     * 承接客户ID
     */
    @TableField("catcher_client_id")
    private String catcherClientId;
    /**
     * 转让费
     */
    @TableField("total_price")
    private Double totalPrice;
    /**
     * 授权人ID
     */
    @TableField("personnel_id")
    private String personnelId;
    /**
     * 备注
     */
    private String flag;
    /**
     * 类型(备用)
     */
    private Integer type;
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
     * 订单状态（0，待付款、1、已付款；2、已冲销）
     */
    @TableField("transfer_status")
    private Integer transferStatus;
    /**
     * 审核状态 （0，待审核1，已审核，2，审核不通过）
     */
    private Integer status;
    /**
     * 复核状态 （0，待审核1，已审核，2，审核不通过）
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
     * 转让人手机
     */
    @TableField("transfer_user_phone")
    private String transferUserPhone;
    /**
     * 承接人姓名
     */
    @TableField("catcher_client_name")
    private String catcherClientName;
    /**
     * 承接人手机
     */
    @TableField("catcher_client_phone")
    private String catcherClientPhone;
    /**
     * 实际付款金额
     */
    @TableField("transfer_price")
    private Double transferPrice;
    @TableId(value = "id", type = IdType.UUID)
    private String id;


    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getTransferUserName() {
        return transferUserName;
    }

    public void setTransferUserName(String transferUserName) {
        this.transferUserName = transferUserName;
    }

    public String getTransferClientId() {
        return transferClientId;
    }

    public void setTransferClientId(String transferClientId) {
        this.transferClientId = transferClientId;
    }

    public String getCatcherClientId() {
        return catcherClientId;
    }

    public void setCatcherClientId(String catcherClientId) {
        this.catcherClientId = catcherClientId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(String personnelId) {
        this.personnelId = personnelId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Integer getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(Integer transferStatus) {
        this.transferStatus = transferStatus;
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

    public String getTransferUserPhone() {
        return transferUserPhone;
    }

    public void setTransferUserPhone(String transferUserPhone) {
        this.transferUserPhone = transferUserPhone;
    }

    public String getCatcherClientName() {
        return catcherClientName;
    }

    public void setCatcherClientName(String catcherClientName) {
        this.catcherClientName = catcherClientName;
    }

    public String getCatcherClientPhone() {
        return catcherClientPhone;
    }

    public void setCatcherClientPhone(String catcherClientPhone) {
        this.catcherClientPhone = catcherClientPhone;
    }

    public Double getTransferPrice() {
        return transferPrice;
    }

    public void setTransferPrice(Double transferPrice) {
        this.transferPrice = transferPrice;
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
        return "FrCardOrderTransfer{" +
                ", cardId=" + cardId +
                ", shopId=" + shopId +
                ", transferUserName=" + transferUserName +
                ", transferClientId=" + transferClientId +
                ", catcherClientId=" + catcherClientId +
                ", totalPrice=" + totalPrice +
                ", personnelId=" + personnelId +
                ", flag=" + flag +
                ", type=" + type +
                ", isUsing=" + isUsing +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createUserName=" + createUserName +
                ", updateUserName=" + updateUserName +
                ", CustomerCode=" + CustomerCode +
                ", createUserId=" + createUserId +
                ", updateUserId=" + updateUserId +
                ", transferStatus=" + transferStatus +
                ", status=" + status +
                ", auditStatus=" + auditStatus +
                ", statusUserId=" + statusUserId +
                ", auditUserId=" + auditUserId +
                ", transferUserPhone=" + transferUserPhone +
                ", catcherClientName=" + catcherClientName +
                ", catcherClientPhone=" + catcherClientPhone +
                ", transferPrice=" + transferPrice +
                ", id=" + id +
                "}";
    }
}
