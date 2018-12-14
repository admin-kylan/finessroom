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
 * 会员卡补余订单
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-04
 */
@TableName("fr_card_order_complement")
public class FrCardOrderComplement extends Model<FrCardOrderComplement> {

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
     * 客户ID
     */
    @TableField("client_id")
    private String clientId;
    /**
     * 人员（员工）ID
     */
    @TableField("personnel_id")
    private String personnelId;
    /**
     * 总价
     */
    @TableField("total_price")
    private Double totalPrice;
    /**
     * 备用
     */
    private String flag;
    /**
     * 类型(1、定金补余；2、分期补余)
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
     * 支付备注
     */
    @TableField("pay_remarks")
    private String payRemarks;
    /**
     * 订单状态（0，待付款、1、已付款；2、已冲销）
     */
    @TableField("order_state")
    private Integer orderState;
    /**
     * 会员卡订单表Id
     */
    @TableField("split_set_id")
    private String splitSetId;
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
     * 是否设置业绩分配（0，未设置，1，已设置）
     */
    @TableField("allot_set_type")
    private Integer allotSetType;
    /**
     * 票卷抵扣金额
     */
    @TableField("ticket_price")
    private Double ticketPrice;
    /**
     * 应付金额
     */
    @TableField("need_price")
    private Double needPrice;
    /**
     * 剩余欠款金额
     */
    @TableField("no_price")
    private Double noPrice;
    /**
     * 种类型（1：时间卡 2：小时卡 3：次卡 4：储值卡 5：充值卡 6：折扣卡）
     */
    @TableField("card_type")
    private Integer cardType;
    /**
     * 找零
     */
    @TableField("ret_change")
    private Double retChange;
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

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(String personnelId) {
        this.personnelId = personnelId;
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

    public String getPayRemarks() {
        return payRemarks;
    }

    public void setPayRemarks(String payRemarks) {
        this.payRemarks = payRemarks;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public String getSplitSetId() {
        return splitSetId;
    }

    public void setSplitSetId(String splitSetId) {
        this.splitSetId = splitSetId;
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

    public Integer getAllotSetType() {
        return allotSetType;
    }

    public void setAllotSetType(Integer allotSetType) {
        this.allotSetType = allotSetType;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Double getNeedPrice() {
        return needPrice;
    }

    public void setNeedPrice(Double needPrice) {
        this.needPrice = needPrice;
    }

    public Double getNoPrice() {
        return noPrice;
    }

    public void setNoPrice(Double noPrice) {
        this.noPrice = noPrice;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public Double getRetChange() {
        return retChange;
    }

    public void setRetChange(Double retChange) {
        this.retChange = retChange;
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
        return "FrCardOrderComplement{" +
                ", cardId=" + cardId +
                ", shopId=" + shopId +
                ", clientId=" + clientId +
                ", personnelId=" + personnelId +
                ", totalPrice=" + totalPrice +
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
                ", payRemarks=" + payRemarks +
                ", orderState=" + orderState +
                ", splitSetId=" + splitSetId +
                ", status=" + status +
                ", auditStatus=" + auditStatus +
                ", statusUserId=" + statusUserId +
                ", auditUserId=" + auditUserId +
                ", allotSetType=" + allotSetType +
                ", ticketPrice=" + ticketPrice +
                ", needPrice=" + needPrice +
                ", noPrice=" + noPrice +
                ", cardType=" + cardType +
                ", retChange=" + retChange +
                ", storagePrice=" + storagePrice +
                ", storageCardId=" + storageCardId +
                ", id=" + id +
                "}";
    }
}
