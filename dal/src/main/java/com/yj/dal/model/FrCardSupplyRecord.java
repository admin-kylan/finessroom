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
 * 会员卡补卡、转卡、卡升、续卡记录
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-11
 */
@TableName("fr_card_supply_record")
public class FrCardSupplyRecord extends Model<FrCardSupplyRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * 原会员卡ID
     */
    @TableField("ori_card_id")
    private String oriCardId;
    /**
     * 新会员卡ID
     */
    @TableField("new_card_id")
    private String newCardId;
    /**
     * 人员（员工）ID
     */
    @TableField("personnel_id")
    private String personnelId;
    /**
     * 补卡金额
     */
    @TableField("pay_price")
    private Double payPrice;
    /**
     * 补卡备注
     */
    @TableField("supply_remarks")
    private String supplyRemarks;
    /**
     * 备用
     */
    private String flag;
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
     * 外部卡号
     */
    @TableField("external_no")
    private String externalNo;
    /**
     * 客户ID
     */
    @TableField("client_id")
    private String clientId;
    /**
     * 审核状态（0，待审核1，已审核，2，审核不通过）
     */
    private Integer status;
    /**
     * 复核状态（0，待审核1，已审核，2，审核不通过）
     */
    @TableField("audit_status")
    private Integer auditStatus;
    /**
     * 审核人员ID
     */
    @TableField("status_user_id")
    private String statusUserId;
    /**
     *  复核审核人员ID
     */
    @TableField("audit_user_id")
    private String auditUserId;
    /**
     * 类型（1、补卡；2、续卡；3、转卡；4、卡升级；）
     */
    private Integer type;
    /**
     * 原会员卡号
     */
    @TableField("ori_card_no")
    private String oriCardNo;
    /**
     * 新会员卡号
     */
    @TableField("new_card_no")
    private String newCardNo;
    /**
     *  卡名称
     */
    @TableField("card_name")
    private String cardName;
    /**
     * 卡系列
     */
    @TableField("card_flag")
    private String cardFlag;
    /**
     * 失效时间
     */
    @TableField("invalid_time")
    private String invalidTime;
    /**
     * 开卡方式（0，直接延续 ； 1，另行开卡）
     */
    @TableField("card_opening")
    private Boolean cardOpening;
    /**
     * 是否更换新卡(0、否；1、是)
     */
    @TableField("replacement_card")
    private Boolean replacementCard;
    /**
     * 订单状态（0、代付款；1、已付款；2.已冲销）
     */
    @TableField("order_state")
    private Integer orderState;
    /**
     * 订单ID 补卡的订单id就是补卡表的id
     */
    @TableField("order_info_id")
    private String orderInfoId;
    /**
     * 开卡时间
     */
    @TableField("bind_time")
    private String bindTime;
    /**
     * 新会员卡号的规则ID
     */
    @TableField("new_card_num_id")
    private String newCardNumId;
    @TableId(value = "id", type = IdType.UUID)
    private String id;


    public String getOriCardId() {
        return oriCardId;
    }

    public void setOriCardId(String oriCardId) {
        this.oriCardId = oriCardId;
    }

    public String getNewCardId() {
        return newCardId;
    }

    public void setNewCardId(String newCardId) {
        this.newCardId = newCardId;
    }

    public String getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(String personnelId) {
        this.personnelId = personnelId;
    }

    public Double getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(Double payPrice) {
        this.payPrice = payPrice;
    }

    public String getSupplyRemarks() {
        return supplyRemarks;
    }

    public void setSupplyRemarks(String supplyRemarks) {
        this.supplyRemarks = supplyRemarks;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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

    public String getExternalNo() {
        return externalNo;
    }

    public void setExternalNo(String externalNo) {
        this.externalNo = externalNo;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOriCardNo() {
        return oriCardNo;
    }

    public void setOriCardNo(String oriCardNo) {
        this.oriCardNo = oriCardNo;
    }

    public String getNewCardNo() {
        return newCardNo;
    }

    public void setNewCardNo(String newCardNo) {
        this.newCardNo = newCardNo;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardFlag() {
        return cardFlag;
    }

    public void setCardFlag(String cardFlag) {
        this.cardFlag = cardFlag;
    }

    public String getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(String invalidTime) {
        this.invalidTime = invalidTime;
    }

    public Boolean getCardOpening() {
        return cardOpening;
    }

    public void setCardOpening(Boolean cardOpening) {
        this.cardOpening = cardOpening;
    }

    public Boolean getReplacementCard() {
        return replacementCard;
    }

    public void setReplacementCard(Boolean replacementCard) {
        this.replacementCard = replacementCard;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public String getOrderInfoId() {
        return orderInfoId;
    }

    public void setOrderInfoId(String orderInfoId) {
        this.orderInfoId = orderInfoId;
    }

    public String getBindTime() {
        return bindTime;
    }

    public void setBindTime(String bindTime) {
        this.bindTime = bindTime;
    }

    public String getNewCardNumId() {
        return newCardNumId;
    }

    public void setNewCardNumId(String newCardNumId) {
        this.newCardNumId = newCardNumId;
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
        return "FrCardSupplyRecord{" +
                ", oriCardId=" + oriCardId +
                ", newCardId=" + newCardId +
                ", personnelId=" + personnelId +
                ", payPrice=" + payPrice +
                ", supplyRemarks=" + supplyRemarks +
                ", flag=" + flag +
                ", isUsing=" + isUsing +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createUserName=" + createUserName +
                ", updateUserName=" + updateUserName +
                ", CustomerCode=" + CustomerCode +
                ", createUserId=" + createUserId +
                ", updateUserId=" + updateUserId +
                ", externalNo=" + externalNo +
                ", clientId=" + clientId +
                ", status=" + status +
                ", auditStatus=" + auditStatus +
                ", statusUserId=" + statusUserId +
                ", auditUserId=" + auditUserId +
                ", type=" + type +
                ", oriCardNo=" + oriCardNo +
                ", newCardNo=" + newCardNo +
                ", cardName=" + cardName +
                ", cardFlag=" + cardFlag +
                ", invalidTime=" + invalidTime +
                ", cardOpening=" + cardOpening +
                ", replacementCard=" + replacementCard +
                ", orderState=" + orderState +
                ", orderInfoId=" + orderInfoId +
                ", bindTime=" + bindTime +
                ", newCardNumId=" + newCardNumId +
                ", id=" + id +
                "}";
    }
}
