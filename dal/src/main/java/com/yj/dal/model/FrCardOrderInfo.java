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
 * 会员卡订单记录
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-30
 */
@TableName("fr_card_order_info")
public class FrCardOrderInfo extends Model<FrCardOrderInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 会员卡ID
     */
    @TableField("card_id")
    private String cardId;
    /**
     * 会员卡类型ID
     */
    @TableField("card_type_id")
    private String cardTypeId;
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
     * 付款方式（1、全款；2、定金；3、赠送；4、置换；5、分期付款）
     */
    @TableField("pay_type")
    private Integer payType;
    /**
     * 消费方式（1、会员消费；2、普通消费；3、员工领用；4、协议单位；）
     */
    @TableField("consume_type")
    private Integer consumeType;
    /**
     * 订单状态（1、代付款；2、已付款；3、已结款；4：审核通过已结款；5：复核通过已结款）
     */
    @TableField("order_state")
    private Integer orderState;
    /**
     * 总价
     */
    @TableField("total_price")
    private Double totalPrice;
    /**
     * 优惠折扣
     */
    private Double discount;
    /**
     * 整单去零
     */
    @TableField("change_num")
    private Double changeNum;
    /**
     * 满减优惠类型（0、一次；1、可重复）
     */
    @TableField("full_type")
    private Boolean fullType;
    /**
     * 满
     */
    @TableField("discount_full")
    private Double discountFull;
    /**
     * 减
     */
    @TableField("discount_reduce")
    private Double discountReduce;
    /**
     * 折扣/优惠
     */
    @TableField("discount_price")
    private Double discountPrice;
    /**
     * 抵扣金额
     */
    @TableField("discount_num")
    private Double discountNum;
    /**
     * 应付金额
     */
    @TableField("need_price")
    private Double needPrice;
    /**
     * 未付金额
     */
    @TableField("no_price")
    private Double noPrice;
    /**
     * 找零
     */
    @TableField("ret_change")
    private Double retChange;
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
     * 备用
     */
    private String flag;
    /**
     * 订单类型（1、新购；2、续卡;3、转卡；4、卡升级）
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
     * 购买权益
     */
    @TableField("buy_rights_num")
    private Integer buyRightsNum;
    /**
     * 赠送权益
     */
    @TableField("give_rights_num")
    private Integer giveRightsNum;

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
     * 业绩分配设置（0、无业绩分配；1、有业绩分配）
     */
    @TableField("allot_set_type")
    private Integer allotSetType;
    /**
     * 补余期限
     */
    @TableField("deposit_time")
    private String depositTime;
    /**
     * 是否购置发票（0：否，1：是）
     */
    @TableField("invoice_status")
    private Integer invoiceStatus;
    /**
     * 授权人ID
     */
    @TableField("authorizing_user_Id")
    private String authorizingUserId;
    /**
     * 置换单位
     */
    @TableField("displace_company ")
    private String displaceCompany ;
    /**
     * 置换方式
     */
    @TableField("displace_way")
    private String displaceWay;

    /**
     * 关联的会员卡设置Id
     */
    @TableField("card_set_id")
    private String cardSetId;

    /**
     * 抵扣人的会员卡ID
     */
    @TableField("give_client_card_id")
    private String giveClientCardId;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
	
	 /**
     * 关联会员卡信息
     */
    @TableField(exist = false)
    private FrCardType cardType;

    /**
     * 关联门店信息
     */
    @TableField(exist = false)
    private Shop shop;

    /**
     * 关联销售人员信息
     */
    @TableField(exist = false)
    private PersonnelInfo personnelInfo;


    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(String cardTypeId) {
        this.cardTypeId = cardTypeId;
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

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getConsumeType() {
        return consumeType;
    }

    public void setConsumeType(Integer consumeType) {
        this.consumeType = consumeType;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getChangeNum() {
        return changeNum;
    }

    public void setChangeNum(Double changeNum) {
        this.changeNum = changeNum;
    }

    public Boolean getFullType() {
        return fullType;
    }

    public void setFullType(Boolean fullType) {
        this.fullType = fullType;
    }

    public Double getDiscountFull() {
        return discountFull;
    }

    public void setDiscountFull(Double discountFull) {
        this.discountFull = discountFull;
    }

    public Double getDiscountReduce() {
        return discountReduce;
    }

    public void setDiscountReduce(Double discountReduce) {
        this.discountReduce = discountReduce;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Double getDiscountNum() {
        return discountNum;
    }

    public void setDiscountNum(Double discountNum) {
        this.discountNum = discountNum;
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

    public Double getRetChange() {
        return retChange;
    }

    public void setRetChange(Double retChange) {
        this.retChange = retChange;
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

    public Integer getBuyRightsNum() {
        return buyRightsNum;
    }

    public void setBuyRightsNum(Integer buyRightsNum) {
        this.buyRightsNum = buyRightsNum;
    }

    public Integer getGiveRightsNum() {
        return giveRightsNum;
    }

    public void setGiveRightsNum(Integer giveRightsNum) {
        this.giveRightsNum = giveRightsNum;
    }

    public Integer getAllotSetType() {
        return allotSetType;
    }

    public void setAllotSetType(Integer allotSetType) {
        this.allotSetType = allotSetType;
    }

    public String getDepositTime() {
        return depositTime;
    }

    public void setDepositTime(String depositTime) {
        this.depositTime = depositTime;
    }

    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorizingUserId() {
        return authorizingUserId;
    }

    public void setAuthorizingUserId(String authorizingUserId) {
        this.authorizingUserId = authorizingUserId;
    }

    public String getDisplaceCompany() {
        return displaceCompany;
    }

    public void setDisplaceCompany(String displaceCompany) {
        this.displaceCompany = displaceCompany;
    }

    public String getDisplaceWay() {
        return displaceWay;
    }

    public void setDisplaceWay(String displaceWay) {
        this.displaceWay = displaceWay;
    }

    public String getCardSetId() {
        return cardSetId;
    }

    public void setCardSetId(String cardSetId) {
        this.cardSetId = cardSetId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrCardOrderInfo{" +
                ", cardId=" + cardId +
                ", cardTypeId=" + cardTypeId +
                ", orderNo=" + orderNo +
                ", clientId=" + clientId +
                ", payType=" + payType +
                ", consumeType=" + consumeType +
                ", orderState=" + orderState +
                ", totalPrice=" + totalPrice +
                ", discount=" + discount +
                ", changeNum=" + changeNum +
                ", fullType=" + fullType +
                ", discountFull=" + discountFull +
                ", discountReduce=" + discountReduce +
                ", discountPrice=" + discountPrice +
                ", discountNum=" + discountNum +
                ", needPrice=" + needPrice +
                ", noPrice=" + noPrice +
                ", retChange=" + retChange +
                ", shopId=" + shopId +
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
                ", buyRightsNum=" + buyRightsNum +
                ", giveRightsNum=" + giveRightsNum +
                ", status=" + status +
                ", auditStatus=" + auditStatus +
                ", statusUserId=" + statusUserId +
                ", auditUserId=" + auditUserId +
                ", allotSetType=" + allotSetType +
                ", depositTime=" + depositTime +
                ", invoiceStatus=" + invoiceStatus +
                ", authorizingUserId=" + authorizingUserId +
                ", displaceCompany =" + displaceCompany  +
                ", displaceWay=" + displaceWay +
                ", cardSetId=" + cardSetId +
                ", id=" + id +
                "}";
    }

    public FrCardType getCardType() {
        return cardType;
    }

    public void setCardType(FrCardType cardType) {
        this.cardType = cardType;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public PersonnelInfo getPersonnelInfo() {
        return personnelInfo;
    }

    public void setPersonnelInfo(PersonnelInfo personnelInfo) {
        this.personnelInfo = personnelInfo;
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

    public String getGiveClientCardId() {
        return giveClientCardId;
    }

    public void setGiveClientCardId(String giveClientCardId) {
        this.giveClientCardId = giveClientCardId;
    }
}
