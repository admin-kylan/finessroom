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
 * 会员卡表
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-30
 */
@TableName("fr_card")
public class FrCard extends Model<FrCard> {

    private static final long serialVersionUID = 1L;

    /**
     * 会员卡号
     */
    @TableField("card_no")
    private String cardNo;
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
     * 卡类型ID
     */
    @TableField("card_type_id")
    private String cardTypeId;
    /**
     * 会员卡状态[0:正常，1:停卡，2:冻结，3:已过期，4:未开卡，5:待补余，6:历史]
     */
    private Integer status;
    /**
     * 会员卡失效日期
     */
    @TableField("invalid_time")
    private String invalidTime;
    /**
     * 会员卡开卡日期[客户与会员卡号绑定的时间]
     */
    @TableField("bind_time")
    private String bindTime;
    /**
     * 备用
     */
    private String flag;
    /**
     * 种类型（1：时间卡 2：小时卡 3：次卡 4：储值卡 5：充值卡 6：折扣卡）
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
     * 会员卡号规则ID
     */
    @TableField("card_num_id")
    private String cardNumId;
    /**
     * 拥有权益（拥有次数、拥有时长、拥有卡金额）
     */
    @TableField("have_rights_num")
    private Double haveRightsNum;
    /**
     * 外部卡号
     */
    @TableField("external_no")
    private String externalNo;
    /**
     * 卡前设置表ID
     */
    @TableField("original_id")
    private String originalId;
    /**
     * 会员卡备注
     */
    private String note;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
	
	/**
     * 关联门店信息
     */
    @TableField(exist = false)
    private Shop shop;

    /**
     * 关联卡类型信息
     */
    @TableField(exist = false)
    private FrCardType frCardType;
    /**
     * 关联卡订单信息
     */
    @TableField(exist = false)
    private FrCardOrderInfo frCardOrderInfo;
    /**
     * 关联卡协议编号
     */
    @TableField(exist = false)
    private FrCardAgreement frCardAgreement;


    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
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

    public String getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(String cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(String invalidTime) {
        this.invalidTime = invalidTime;
    }

    public String getBindTime() {
        return bindTime;
    }

    public void setBindTime(String bindTime) {
        this.bindTime = bindTime;
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

    public String getCardNumId() {
        return cardNumId;
    }

    public void setCardNumId(String cardNumId) {
        this.cardNumId = cardNumId;
    }

    public Double getHaveRightsNum() {
        return haveRightsNum;
    }

    public void setHaveRightsNum(Double haveRightsNum) {
        this.haveRightsNum = haveRightsNum;
    }

    public String getExternalNo() {
        return externalNo;
    }

    public void setExternalNo(String externalNo) {
        this.externalNo = externalNo;
    }

    public String getOriginalId() {
        return originalId;
    }

    public void setOriginalId(String originalId) {
        this.originalId = originalId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrCard{" +
                ", cardNo=" + cardNo +
                ", clientId=" + clientId +
                ", shopId=" + shopId +
                ", cardTypeId=" + cardTypeId +
                ", status=" + status +
                ", invalidTime=" + invalidTime +
                ", bindTime=" + bindTime +
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
                ", cardNumId=" + cardNumId +
                ", haveRightsNum=" + haveRightsNum +
                ", externalNo=" + externalNo +
                ", originalId=" + originalId +
                ", note=" + note +
                ", id=" + id +
                "}";
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public FrCardType getFrCardType() {
        return frCardType;
    }

    public void setFrCardType(FrCardType frCardType) {
        this.frCardType = frCardType;
    }

    public FrCardOrderInfo getFrCardOrderInfo() {
        return frCardOrderInfo;
    }

    public void setFrCardOrderInfo(FrCardOrderInfo frCardOrderInfo) {
        this.frCardOrderInfo = frCardOrderInfo;
    }

    public FrCardAgreement getFrCardAgreement() {
        return frCardAgreement;
    }

    public void setFrCardAgreement(FrCardAgreement frCardAgreement) {
        this.frCardAgreement = frCardAgreement;
    }
}
