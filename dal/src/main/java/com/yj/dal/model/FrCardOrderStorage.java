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
 * 会员卡 储值表
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-02
 */
@TableName("fr_card_order_storage")
public class FrCardOrderStorage extends Model<FrCardOrderStorage> {

    private static final long serialVersionUID = 1L;

    /**
     * 会员卡ID
     */
    @TableField("card_id")
    private String cardId;
    /**
     * 订单编号
     */
    @TableField("order_no")
    private String orderNo;
    /**
     * 客户ID
     */
    @TableField("client_id")
    private String clientId;
    /**
     * 基础表门店ID
     */
    @TableField("shop_id")
    private String shopId;
    /**
     * 人员（员工）ID
     */
    @TableField("personnel_id")
    private String personnelId;
    /**
     * 储值金额
     */
    @TableField("store_price")
    private Double storePrice;
    /**
     * 票卷抵扣
     */
    @TableField("ticket_price")
    private Double ticketPrice;
    /**
     * 赠送金额
     */
    @TableField("give_price")
    private Double givePrice;
    /**
     * 实际付款金额
     */
    @TableField("total_price")
    private Double totalPrice;
    /**
     * 备用
     */
    private String flag;
    /**
     * 储值状态（0：待付款 1：已付款，2：已冲销 ，3：已退款 、
     *           4：已转让、5 ：审核通过退款、6：复核通过退款、
     *           7：审核通过转让、8：复核通过转让、9：储值退全款）
     */
    @TableField("storage_status")
    private Integer storageStatus;
    /**
     * 备用
     */
    private Integer type;
    /**
     * 转让人姓名
     */
    @TableField("give_name")
    private String giveName;
    /**
     * 转让人手机
     */
    @TableField("give_phone")
    private String givePhone;
    /**
     * 冲销备注
     */
    @TableField("cancel_note")
    private String cancelNote;
    /**
     * 退款授权人Id
     */
    @TableField("refund_user_id")
    private String refundUserId;
    /**
     * 退款原因
     */
    @TableField("refund_note")
    private String refundNote;
    /**
     * 是否启用(0：不启用 1：启用）默认1
     */
    @TableField("is_using")
    private Boolean isUsing;
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
     * 储值总金额
     */
    @TableField("surplus_price")
    private Double surplusPrice;
    /**
     * 转让人id
     */
    @TableField("give_user_id")
    private String giveUserId;
    /**
     * 转让人的会员卡ID
     */
    @TableField("give_card_id")
    private String giveCardId;
    /**
     * 赠送人的储值订单id
     */
    @TableField("give_storage_id")
    private String giveStorageId;
    /**
     * 储值抵扣
     */
    @TableField("storage_price")
    private Double storagePrice;
    /**
     * 抵扣的会员卡id
     */
    @TableField("storage_card_id")
    private String storageCardId;
    @TableId(value = "id", type = IdType.UUID)
    private String id;


    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(String personnelId) {
        this.personnelId = personnelId;
    }

    public Double getStorePrice() {
        return storePrice;
    }

    public void setStorePrice(Double storePrice) {
        this.storePrice = storePrice;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Double getGivePrice() {
        return givePrice;
    }

    public void setGivePrice(Double givePrice) {
        this.givePrice = givePrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getStorageStatus() {
        return storageStatus;
    }

    public void setStorageStatus(Integer storageStatus) {
        this.storageStatus = storageStatus;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getGiveName() {
        return giveName;
    }

    public void setGiveName(String giveName) {
        this.giveName = giveName;
    }

    public String getGivePhone() {
        return givePhone;
    }

    public void setGivePhone(String givePhone) {
        this.givePhone = givePhone;
    }

    public String getCancelNote() {
        return cancelNote;
    }

    public void setCancelNote(String cancelNote) {
        this.cancelNote = cancelNote;
    }

    public String getRefundUserId() {
        return refundUserId;
    }

    public void setRefundUserId(String refundUserId) {
        this.refundUserId = refundUserId;
    }

    public String getRefundNote() {
        return refundNote;
    }

    public void setRefundNote(String refundNote) {
        this.refundNote = refundNote;
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

    public Double getSurplusPrice() {
        return surplusPrice;
    }

    public void setSurplusPrice(Double surplusPrice) {
        this.surplusPrice = surplusPrice;
    }

    public String getGiveUserId() {
        return giveUserId;
    }

    public void setGiveUserId(String giveUserId) {
        this.giveUserId = giveUserId;
    }

    public String getGiveCardId() {
        return giveCardId;
    }

    public void setGiveCardId(String giveCardId) {
        this.giveCardId = giveCardId;
    }

    public String getGiveStorageId() {
        return giveStorageId;
    }

    public void setGiveStorageId(String giveStorageId) {
        this.giveStorageId = giveStorageId;
    }

    public Double getStoragePrice() {
        return storagePrice;
    }

    public void setStoragePrice(Double storagePrice) {
        this.storagePrice = storagePrice;
    }

    public String getStorageCardId() {
        return storageCardId;
    }

    public void setStorageCardId(String storageCardId) {
        this.storageCardId = storageCardId;
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
        return "FrCardOrderStorage{" +
                ", cardId=" + cardId +
                ", orderNo=" + orderNo +
                ", clientId=" + clientId +
                ", shopId=" + shopId +
                ", personnelId=" + personnelId +
                ", storePrice=" + storePrice +
                ", ticketPrice=" + ticketPrice +
                ", givePrice=" + givePrice +
                ", totalPrice=" + totalPrice +
                ", flag=" + flag +
                ", storageStatus=" + storageStatus +
                ", type=" + type +
                ", giveName=" + giveName +
                ", givePhone=" + givePhone +
                ", cancelNote=" + cancelNote +
                ", refundUserId=" + refundUserId +
                ", refundNote=" + refundNote +
                ", isUsing=" + isUsing +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createUserName=" + createUserName +
                ", updateUserName=" + updateUserName +
                ", CustomerCode=" + CustomerCode +
                ", createUserId=" + createUserId +
                ", updateUserId=" + updateUserId +
                ", status=" + status +
                ", auditStatus=" + auditStatus +
                ", statusUserId=" + statusUserId +
                ", auditUserId=" + auditUserId +
                ", surplusPrice=" + surplusPrice +
                ", giveUserId=" + giveUserId +
                ", giveCardId=" + giveCardId +
                ", giveStorageId=" + giveStorageId +
                ", storagePrice=" + storagePrice +
                ", storageCardId=" + storageCardId +
                ", id=" + id +
                "}";
    }
}
