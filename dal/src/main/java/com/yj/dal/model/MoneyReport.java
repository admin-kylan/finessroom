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
@TableName("MoneyReport")
public class MoneyReport extends Model<MoneyReport> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /**
     * 客户代码
     */
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    /**
     *
     */
    private String ShopId;
    /**
     *
     */
    private String SdadiumId;
    /**
     * 订单号
     */
    private String OrderNumber;
    /**
     * 使用类型 0 商品 1  消费表 /体验 2 新购  3  转卡 4  续卡  5  子卡  6 会员卡补余 7 转让卡
     * 8 补卡 9 新购项目 10 新购项目 新购项目补余 11 新购项目退费  12 新购项目转让 13 停卡  14 储值
     * 15 租赁  16 票券  17 企业客户  18 企业客户充值 19  私教预约冻结/解冻  20 企业客户停止 21 增购卡
     * 22 租赁  23餐饮结账 24 球类押金 25 预售定金     (各个模块的消费统一用 1 )
     */
    private Integer UseType;
    /**
     * 使用类型名称
     */
    private String UseTypeName;
    /**
     * 关联具体财务主表Id:ProjectOrder表id）
     */
    private String TableId;
    /**
     * 客户Id
     */
    private String CustomerId;
    /**
     * 会员卡Id
     */
    private String MemberCardId;
    /**
     * 协议客户Id
     */
    private String AgreeId;
    /**
     * 优惠折扣
     */
    private Double Discount;
    /**
     * 整单去零
     */
    private Double ChangeMoney;
    /**
     * 满减优惠类型（0、没有 1 优惠折扣；2、满减;3 全部）
     */
    private Integer FullType;
    /**
     * 满
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
     * 其它支付
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
     * 会员姓名
     */
    private String Name;
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
    private String TableName;
    private String CreateName;
    private String AddprojectId;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getUseType() {
        return UseType;
    }

    public void setUseType(Integer UseType) {
        this.UseType = UseType;
    }

    public String getUseTypeName() {
        return UseTypeName;
    }

    public void setUseTypeName(String UseTypeName) {
        this.UseTypeName = UseTypeName;
    }

    public String getTableId() {
        return TableId;
    }

    public void setTableId(String TableId) {
        this.TableId = TableId;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String CustomerId) {
        this.CustomerId = CustomerId;
    }

    public String getMemberCardId() {
        return MemberCardId;
    }

    public void setMemberCardId(String MemberCardId) {
        this.MemberCardId = MemberCardId;
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

    public Double getChangeMoney() {
        return ChangeMoney;
    }

    public void setChangeMoney(Double ChangeMoney) {
        this.ChangeMoney = ChangeMoney;
    }

    public Integer getFullType() {
        return FullType;
    }

    public void setFullType(Integer FullType) {
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

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
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

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String TableName) {
        this.TableName = TableName;
    }

    public String getCreateName() {
        return CreateName;
    }

    public void setCreateName(String CreateName) {
        this.CreateName = CreateName;
    }

    public String getAddprojectId() {
        return AddprojectId;
    }

    public void setAddprojectId(String AddprojectId) {
        this.AddprojectId = AddprojectId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MoneyReport{" +
        ", id=" + id +
        ", CustomerCode=" + CustomerCode +
        ", OrderNumber=" + OrderNumber +
        ", UseType=" + UseType +
        ", UseTypeName=" + UseTypeName +
        ", TableId=" + TableId +
        ", CustomerId=" + CustomerId +
        ", MemberCardId=" + MemberCardId +
        ", AgreeId=" + AgreeId +
        ", Discount=" + Discount +
        ", ChangeMoney=" + ChangeMoney +
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
        ", Name=" + Name +
        ", Phone=" + Phone +
        ", SaleId=" + SaleId +
        ", CreateId=" + CreateId +
        ", CreateTime=" + CreateTime +
        ", TableName=" + TableName +
        ", CreateName=" + CreateName +
        ", AddprojectId=" + AddprojectId +
        "}";
    }
}
