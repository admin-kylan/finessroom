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
 * 
 * </p>
 *
 * @author MP自动生成
 * @since 2019-02-23
 */
@TableName("ConsumeAccountOrder")
public class ConsumeAccountOrder extends Model<ConsumeAccountOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户代码
     */
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    /**
     * 订单号
     */
    private String OrderNumber;
    /**
     *
     */
    private String ShopId;
    /**
     *
     */
    private String SdadiumId;
    /**
     * 关联具体财务主表Id:例如（账单ProjectOrder表id）
     */
    private String TableId;
    /**
     * 关联具体财务主表:例如（账单ProjectOrder）
     */
    private String TableName;
    /**
     * 增购Id
     */
    private String AddprojectId;
    /**
     * 协议客户Id
     */
    private String AgreeId;
    /**
     * 优惠折扣
     */
    private Double Discount;
    /**
     * 满减优惠类型（0、没有 1 优惠折扣；2、满减）
     */
    private Boolean FullType;
    /**
     * 慢
     */
    private Double DiscountFull;
    /**
     * 减
     */
    private Double DiscountReduce;
    /**
     * 抵扣金额(票卷抵扣）
     */
    private Double TicketPrice;
    /**
     * 实收金额
     */
    private Double ShouldMoney;
    /**
     * 原价
     */
    private Double OriginalPrice;
    /**
     * 应付金额
     */
    private Double CostMoney;
    /**
     * 优惠金额
     */
    private Double CouponMoney;
    /**
     * 找零
     */
    private Double RemainMoney;
    /**
     *  0普通消费 1 会员消费 2员工消费 3 挂账 4挂单
     */
    private Integer ConsumeState;
    /**
     * 挂账金额
     */
    private Double PaymentMoney;
    /**
     * 其他金额
     */
    private Double OtherPayMoney;
    /**
     * 扣卡金额
     */
    private Double CardMoney;
    /**
     * 扣卡次数或小时数
     */
    private Double CardTime;
    /**
     * 扣协议金额
     */
    private Double AgreeMoney;
    /**
     * 扣增购次数或小时数
     */
    private Double AddProjectTime;
    /**
     * 付款类型 0 全款 1 定金
     */
    private Boolean PayType;
    /**
     * 付款状态 0-未付款 1-已付款
     */
    private Integer PayStatus;
    /**
     * 会员手机号码
     */
    private String Phone;
    /**
     * 销售人员id
     */
    private String SaleId;
    /**
     * 操作人Id
     */
    private String CreateId;
    /**
     * 操作时间
     */
    private Date CreateTime;
    private String Name;
    private String CustomerId;
    private String CreateName;
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    private Double ChangeMoney;

    public String getShopId() {
        return ShopId;
    }

    public void setShopId(String shopId) {
        ShopId = shopId;
    }

    public String getSdadiumId() {
        return SdadiumId;
    }

    public void setSdadiumId(String sdadiumId) {
        SdadiumId = sdadiumId;
    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String OrderNumber) {
        this.OrderNumber = OrderNumber;
    }

    public String getTableId() {
        return TableId;
    }

    public void setTableId(String TableId) {
        this.TableId = TableId;
    }

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String TableName) {
        this.TableName = TableName;
    }

    public String getAddprojectId() {
        return AddprojectId;
    }

    public void setAddprojectId(String AddprojectId) {
        this.AddprojectId = AddprojectId;
    }

    public String getAgreeId() {
        return AgreeId;
    }

    public void setAgreeId(String AgreeId) {
        this.AgreeId = AgreeId;
    }

    public Double getDiscount() {
        return Discount;
    }

    public void setDiscount(Double Discount) {
        this.Discount = Discount;
    }

    public Boolean getFullType() {
        return FullType;
    }

    public void setFullType(Boolean FullType) {
        this.FullType = FullType;
    }

    public Double getDiscountFull() {
        return DiscountFull;
    }

    public void setDiscountFull(Double DiscountFull) {
        this.DiscountFull = DiscountFull;
    }

    public Double getDiscountReduce() {
        return DiscountReduce;
    }

    public void setDiscountReduce(Double DiscountReduce) {
        this.DiscountReduce = DiscountReduce;
    }

    public Double getTicketPrice() {
        return TicketPrice;
    }

    public void setTicketPrice(Double TicketPrice) {
        this.TicketPrice = TicketPrice;
    }

    public Double getShouldMoney() {
        return ShouldMoney;
    }

    public void setShouldMoney(Double ShouldMoney) {
        this.ShouldMoney = ShouldMoney;
    }

    public Double getOriginalPrice() {
        return OriginalPrice;
    }

    public void setOriginalPrice(Double OriginalPrice) {
        this.OriginalPrice = OriginalPrice;
    }

    public Double getCostMoney() {
        return CostMoney;
    }

    public void setCostMoney(Double CostMoney) {
        this.CostMoney = CostMoney;
    }

    public Double getCouponMoney() {
        return CouponMoney;
    }

    public void setCouponMoney(Double CouponMoney) {
        this.CouponMoney = CouponMoney;
    }

    public Double getRemainMoney() {
        return RemainMoney;
    }

    public void setRemainMoney(Double RemainMoney) {
        this.RemainMoney = RemainMoney;
    }

    public Integer getConsumeState() {
        return ConsumeState;
    }

    public void setConsumeState(Integer ConsumeState) {
        this.ConsumeState = ConsumeState;
    }

    public Double getPaymentMoney() {
        return PaymentMoney;
    }

    public void setPaymentMoney(Double PaymentMoney) {
        this.PaymentMoney = PaymentMoney;
    }

    public Double getOtherPayMoney() {
        return OtherPayMoney;
    }

    public void setOtherPayMoney(Double OtherPayMoney) {
        this.OtherPayMoney = OtherPayMoney;
    }

    public Double getCardMoney() {
        return CardMoney;
    }

    public void setCardMoney(Double CardMoney) {
        this.CardMoney = CardMoney;
    }

    public Double getCardTime() {
        return CardTime;
    }

    public void setCardTime(Double CardTime) {
        this.CardTime = CardTime;
    }

    public Double getAgreeMoney() {
        return AgreeMoney;
    }

    public void setAgreeMoney(Double AgreeMoney) {
        this.AgreeMoney = AgreeMoney;
    }

    public Double getAddProjectTime() {
        return AddProjectTime;
    }

    public void setAddProjectTime(Double AddProjectTime) {
        this.AddProjectTime = AddProjectTime;
    }

    public Boolean getPayType() {
        return PayType;
    }

    public void setPayType(Boolean PayType) {
        this.PayType = PayType;
    }

    public Integer getPayStatus() {
        return PayStatus;
    }

    public void setPayStatus(Integer PayStatus) {
        this.PayStatus = PayStatus;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getSaleId() {
        return SaleId;
    }

    public void setSaleId(String SaleId) {
        this.SaleId = SaleId;
    }

    public String getCreateId() {
        return CreateId;
    }

    public void setCreateId(String CreateId) {
        this.CreateId = CreateId;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String CustomerId) {
        this.CustomerId = CustomerId;
    }

    public String getCreateName() {
        return CreateName;
    }

    public void setCreateName(String CreateName) {
        this.CreateName = CreateName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getChangeMoney() {
        return ChangeMoney;
    }

    public void setChangeMoney(Double ChangeMoney) {
        this.ChangeMoney = ChangeMoney;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ConsumeAccountOrder{" +
        ", CustomerCode=" + CustomerCode +
        ", OrderNumber=" + OrderNumber +
        ", TableId=" + TableId +
        ", TableName=" + TableName +
        ", AddprojectId=" + AddprojectId +
        ", AgreeId=" + AgreeId +
        ", Discount=" + Discount +
        ", FullType=" + FullType +
        ", DiscountFull=" + DiscountFull +
        ", DiscountReduce=" + DiscountReduce +
        ", TicketPrice=" + TicketPrice +
        ", ShouldMoney=" + ShouldMoney +
        ", OriginalPrice=" + OriginalPrice +
        ", CostMoney=" + CostMoney +
        ", CouponMoney=" + CouponMoney +
        ", RemainMoney=" + RemainMoney +
        ", ConsumeState=" + ConsumeState +
        ", PaymentMoney=" + PaymentMoney +
        ", OtherPayMoney=" + OtherPayMoney +
        ", CardMoney=" + CardMoney +
        ", CardTime=" + CardTime +
        ", AgreeMoney=" + AgreeMoney +
        ", AddProjectTime=" + AddProjectTime +
        ", PayType=" + PayType +
        ", PayStatus=" + PayStatus +
        ", Phone=" + Phone +
        ", SaleId=" + SaleId +
        ", CreateId=" + CreateId +
        ", CreateTime=" + CreateTime +
        ", Name=" + Name +
        ", CustomerId=" + CustomerId +
        ", CreateName=" + CreateName +
        ", id=" + id +
        ", ChangeMoney=" + ChangeMoney +
        "}";
    }
}
