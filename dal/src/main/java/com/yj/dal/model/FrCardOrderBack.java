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
 * 会员卡退卡订单
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-19
 */
@TableName("fr_card_order_back")
public class FrCardOrderBack extends Model<FrCardOrderBack> {

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
     * 人员（员工）ID
     */
    @TableField("personnel_id")
    private String personnelId;
    /**
     * 退款总金额
     */
    @TableField("total_price")
    private Double totalPrice;
    /**
     * 退卡金额
     */
    @TableField("need_price")
    private Double needPrice;
    /**
     * 退卡手续费
     */
    @TableField("proced_prcie")
    private Double procedPrcie;
    /**
     * 是否扣除当月业绩
     */
    @TableField("allot_set_type")
    private Boolean allotSetType;
    /**
     * 退款账号
     */
    @TableField("account_number")
    private String accountNumber;
    /**
     * 退款类型 （1、支付宝；2、刷卡；3、微信；4、现金；5、转账；6、花呗；7、其他）
     */
    @TableField("refund_type")
    private Integer refundType;
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
     * 备用
     */
    private String flag;
    /**
     * 备用
     */
    private Integer type;
    /**
     * 卡系列
     */
    @TableField("card_flag")
    private String cardFlag;
    /**
     * 卡名称
     */
    @TableField("card_type_name")
    private String cardTypeName;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    @TableField("is_using")
    private Boolean isUsing;


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

    public Double getNeedPrice() {
        return needPrice;
    }

    public void setNeedPrice(Double needPrice) {
        this.needPrice = needPrice;
    }

    public Double getProcedPrcie() {
        return procedPrcie;
    }

    public void setProcedPrcie(Double procedPrcie) {
        this.procedPrcie = procedPrcie;
    }

    public Boolean getAllotSetType() {
        return allotSetType;
    }

    public void setAllotSetType(Boolean allotSetType) {
        this.allotSetType = allotSetType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getRefundType() {
        return refundType;
    }

    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
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

    public String getCardFlag() {
        return cardFlag;
    }

    public void setCardFlag(String cardFlag) {
        this.cardFlag = cardFlag;
    }

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getUsing() {
        return isUsing;
    }

    public void setUsing(Boolean isUsing) {
        this.isUsing = isUsing;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrCardOrderBack{" +
                ", cardId=" + cardId +
                ", clientId=" + clientId +
                ", personnelId=" + personnelId +
                ", totalPrice=" + totalPrice +
                ", needPrice=" + needPrice +
                ", procedPrcie=" + procedPrcie +
                ", allotSetType=" + allotSetType +
                ", accountNumber=" + accountNumber +
                ", refundType=" + refundType +
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
                ", flag=" + flag +
                ", type=" + type +
                ", cardFlag=" + cardFlag +
                ", cardTypeName=" + cardTypeName +
                ", id=" + id +
                ", isUsing=" + isUsing +
                "}";
    }
}
