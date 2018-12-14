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
 * 订单交易明细
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-27
 */
@TableName("fr_card_order_datail")
public class FrCardOrderDatail extends Model<FrCardOrderDatail> {

    private static final long serialVersionUID = 1L;

    /**
     * 会员卡ID
     */
    @TableField("card_id")
    private String cardId;
    /**
     * 客户ID
     */
    @TableField("client_id")
    private String clientId;
    /**
     * 卡种类型（1：时间卡 2：小时卡 3：次卡 4：储值卡 5：充值卡 6：折扣卡）
     */
    @TableField("card_type")
    private Integer cardType;
    /**
     * 支付状态（0：支出 1：收入）
     */
    @TableField("order_status")
    private Boolean orderStatus;
    /**
     * 金额（注意正负 支出就是  -0.00）
     */
    @TableField("order_price")
    private Double orderPrice;
    /**
     * 权益 注意正负
     */
    @TableField("order_rights_num")
    private Double orderRightsNum;
    /**
     * 基础表门店ID
     */
    @TableField("shop_id")
    private String shopId;
    /**
     * 操作员工id
     */
    @TableField("personnel_id")
    private String personnelId;
    /**
     * 消费内容
     */
    private String flag;
    /**
     * 支付类型（1:权益 2:储值 3:其他消费）
     */
    private Integer type;
    /**
     * 是否启用(0：不启用 1：启用）默认1
     */
    @TableField("is_using")
    private Boolean isUsing;
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
     * 审核人员id
     */
    @TableField("status_user_id")
    private String statusUserId;
    /**
     * 复核人员id
     */
    @TableField("audit_user_id")
    private String auditUserId;
    /**
     * 对应的订单id
     */
    @TableField("order_id")
    private String orderId;
    /**
     * 订单类型（1、新购；2、续卡；3、转卡；4、补卡（暂无）；5、储值；6、补余；7、退卡, 8、停卡，9、转让【若有其他的，类型数字请往后添加】）
     */
    @TableField("order_type")
    private Integer orderType;
    /**
     * 资金交易表ID
     */
    @TableField("order_price_id")
    private String orderPriceId;
    /**
     * 剩余权益\剩余储值金额
     */
    @TableField("order_amt")
    private Double orderAmt;
    @TableField(value = "update_user_name", fill = FieldFill.INSERT_UPDATE)
    private String updateUserName;
    @TableField(value = "update_user_id", fill = FieldFill.INSERT_UPDATE)
    private String updateUserId;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    private String createUserId;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    @TableField(value = "create_user_name", fill = FieldFill.INSERT)
    private String createUserName;


    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public Boolean getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Double getOrderRightsNum() {
        return orderRightsNum;
    }

    public void setOrderRightsNum(Double orderRightsNum) {
        this.orderRightsNum = orderRightsNum;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getOrderPriceId() {
        return orderPriceId;
    }

    public void setOrderPriceId(String orderPriceId) {
        this.orderPriceId = orderPriceId;
    }

    public Double getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(Double orderAmt) {
        this.orderAmt = orderAmt;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrCardOrderDatail{" +
                ", cardId=" + cardId +
                ", clientId=" + clientId +
                ", cardType=" + cardType +
                ", orderStatus=" + orderStatus +
                ", orderPrice=" + orderPrice +
                ", orderRightsNum=" + orderRightsNum +
                ", shopId=" + shopId +
                ", personnelId=" + personnelId +
                ", flag=" + flag +
                ", type=" + type +
                ", isUsing=" + isUsing +
                ", status=" + status +
                ", auditStatus=" + auditStatus +
                ", statusUserId=" + statusUserId +
                ", auditUserId=" + auditUserId +
                ", orderId=" + orderId +
                ", orderType=" + orderType +
                ", orderPriceId=" + orderPriceId +
                ", orderAmt=" + orderAmt +
                ", updateUserName=" + updateUserName +
                ", updateUserId=" + updateUserId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", CustomerCode=" + CustomerCode +
                ", createUserId=" + createUserId +
                ", id=" + id +
                ", createUserName=" + createUserName +
                "}";
    }
}
