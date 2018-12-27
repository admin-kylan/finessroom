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
 * 消费结账单表

 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-27
 */
@TableName("SysConsumeOrder")
public class SysConsumeOrder extends Model<SysConsumeOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户代码 （方便客户导出资料）
     */
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /**
     * 客户ID
     */
    private String CustomerId;
    /**
     * 创建时间
     */
    private Date CreateTime;
    /**
     * 操作员ID
     */
    private String CreateId;
    /**
     * 创建人姓名
     */
    private String CreateName;
    /**
     * 单号
     */
    private String OrderNumber;
    /**
     * 门店ID
     */
    private String ShopId;
    /**
     * 场馆ID
     */
    private String SdadiumId;
    /**
     * 折扣
     */
    private Double Discount;
    /**
     * 减去金额
     */
    private Double ReduceMoney;
    /**
     * 满额金额
     */
    private Double AllReduceMoney;
    /**
     * 满额减去金额
     */
    private Double RealMoney;
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
     * 消费状态 0普通消费 1 会员消费 2员工消费 3 挂账 4挂单
     */
    private Integer ConsumeState;
    /**
     * 会员卡ID
     */
    private String MemberCardId;
    /**
     * 协议单位ID
     */
    private String AgreeId;
    /**
     * 协议金额
     */
    private Double AgreeMoney;
    /**
     * 付款状态0-未付款 1-已付款 2-退款中 3-已退款
     */
    private Integer PayState;
    /**
     * 手机号码
     */
    private String Phone;
    /**
     * 姓名
     */
    private String Name;
    /**
     * 优惠授权人名字
     */
    private String NameId;
    /**
     * 挂账授权人部门
     */
    private String BuyerNamePart;
    /**
     * 授权人名字
     */
    private String BuyerNameName;
    /**
     * 开场ID
     */
    private String OpeningId;
    /**
     * 是否冲销 0 否 1 是
     */
    private Integer IfOffset;
    /**
     * 押金
     */
    private String CashPledge;
    /**
     * 审核时间
     */
    private Date CheckTime;
    /**
     * 审核状态(0-未审核，1-审核通过，2-审核不通过)
     */
    private Integer CheckState;
    /**
     * 审核员ID
     */
    private String CheckPeople;
    /**
     * 审核时间1
     */
    private Date CheckTime1;
    /**
     * 审核状态（0-未审核，1-审核通过，2-审核不通过）
     */
    private Integer CheckState1;
    /**
     * 审核原因1
     */
    private String CheckReason1;
    /**
     * 审核员1
     */
    private String CheckOperator1;
    /**
     * 审核时间2
     */
    private Date CheckTime2;
    /**
     * 审核状态（0-未审核，1-审核通过，2-审核不通过）
     */
    private Integer CheckState2;
    /**
     * 审核原因2
     */
    private String CheckReason2;
    /**
     * 审核员2
     */
    private String CheckOperator2;
    /**
     * 销售人员
     */
    private String SaleId;
    /**
     * 销售人员姓名
     */
    private String SaleName;
    /**
     * 冲销账单ID
     */
    private String OffsetId;
    /**
     * 其他支付对应
     */
    private String ReturnMoneyWay;
    /**
     * 其他支付对应ID
     */
    private String ReturnMoneyWayId;


    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String CustomerId) {
        this.CustomerId = CustomerId;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getCreateId() {
        return CreateId;
    }

    public void setCreateId(String CreateId) {
        this.CreateId = CreateId;
    }

    public String getCreateName() {
        return CreateName;
    }

    public void setCreateName(String CreateName) {
        this.CreateName = CreateName;
    }

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String OrderNumber) {
        this.OrderNumber = OrderNumber;
    }

    public String getShopId() {
        return ShopId;
    }

    public void setShopId(String ShopId) {
        this.ShopId = ShopId;
    }

    public String getSdadiumId() {
        return SdadiumId;
    }

    public void setSdadiumId(String SdadiumId) {
        this.SdadiumId = SdadiumId;
    }

    public Double getDiscount() {
        return Discount;
    }

    public void setDiscount(Double Discount) {
        this.Discount = Discount;
    }

    public Double getReduceMoney() {
        return ReduceMoney;
    }

    public void setReduceMoney(Double ReduceMoney) {
        this.ReduceMoney = ReduceMoney;
    }

    public Double getAllReduceMoney() {
        return AllReduceMoney;
    }

    public void setAllReduceMoney(Double AllReduceMoney) {
        this.AllReduceMoney = AllReduceMoney;
    }

    public Double getRealMoney() {
        return RealMoney;
    }

    public void setRealMoney(Double RealMoney) {
        this.RealMoney = RealMoney;
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

    public Double getAgreeMoney() {
        return AgreeMoney;
    }

    public void setAgreeMoney(Double AgreeMoney) {
        this.AgreeMoney = AgreeMoney;
    }

    public Integer getPayState() {
        return PayState;
    }

    public void setPayState(Integer PayState) {
        this.PayState = PayState;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getNameId() {
        return NameId;
    }

    public void setNameId(String NameId) {
        this.NameId = NameId;
    }

    public String getBuyerNamePart() {
        return BuyerNamePart;
    }

    public void setBuyerNamePart(String BuyerNamePart) {
        this.BuyerNamePart = BuyerNamePart;
    }

    public String getBuyerNameName() {
        return BuyerNameName;
    }

    public void setBuyerNameName(String BuyerNameName) {
        this.BuyerNameName = BuyerNameName;
    }

    public String getOpeningId() {
        return OpeningId;
    }

    public void setOpeningId(String OpeningId) {
        this.OpeningId = OpeningId;
    }

    public Integer getIfOffset() {
        return IfOffset;
    }

    public void setIfOffset(Integer IfOffset) {
        this.IfOffset = IfOffset;
    }

    public String getCashPledge() {
        return CashPledge;
    }

    public void setCashPledge(String CashPledge) {
        this.CashPledge = CashPledge;
    }

    public Date getCheckTime() {
        return CheckTime;
    }

    public void setCheckTime(Date CheckTime) {
        this.CheckTime = CheckTime;
    }

    public Integer getCheckState() {
        return CheckState;
    }

    public void setCheckState(Integer CheckState) {
        this.CheckState = CheckState;
    }

    public String getCheckPeople() {
        return CheckPeople;
    }

    public void setCheckPeople(String CheckPeople) {
        this.CheckPeople = CheckPeople;
    }

    public Date getCheckTime1() {
        return CheckTime1;
    }

    public void setCheckTime1(Date CheckTime1) {
        this.CheckTime1 = CheckTime1;
    }

    public Integer getCheckState1() {
        return CheckState1;
    }

    public void setCheckState1(Integer CheckState1) {
        this.CheckState1 = CheckState1;
    }

    public String getCheckReason1() {
        return CheckReason1;
    }

    public void setCheckReason1(String CheckReason1) {
        this.CheckReason1 = CheckReason1;
    }

    public String getCheckOperator1() {
        return CheckOperator1;
    }

    public void setCheckOperator1(String CheckOperator1) {
        this.CheckOperator1 = CheckOperator1;
    }

    public Date getCheckTime2() {
        return CheckTime2;
    }

    public void setCheckTime2(Date CheckTime2) {
        this.CheckTime2 = CheckTime2;
    }

    public Integer getCheckState2() {
        return CheckState2;
    }

    public void setCheckState2(Integer CheckState2) {
        this.CheckState2 = CheckState2;
    }

    public String getCheckReason2() {
        return CheckReason2;
    }

    public void setCheckReason2(String CheckReason2) {
        this.CheckReason2 = CheckReason2;
    }

    public String getCheckOperator2() {
        return CheckOperator2;
    }

    public void setCheckOperator2(String CheckOperator2) {
        this.CheckOperator2 = CheckOperator2;
    }

    public String getSaleId() {
        return SaleId;
    }

    public void setSaleId(String SaleId) {
        this.SaleId = SaleId;
    }

    public String getSaleName() {
        return SaleName;
    }

    public void setSaleName(String SaleName) {
        this.SaleName = SaleName;
    }

    public String getOffsetId() {
        return OffsetId;
    }

    public void setOffsetId(String OffsetId) {
        this.OffsetId = OffsetId;
    }

    public String getReturnMoneyWay() {
        return ReturnMoneyWay;
    }

    public void setReturnMoneyWay(String ReturnMoneyWay) {
        this.ReturnMoneyWay = ReturnMoneyWay;
    }

    public String getReturnMoneyWayId() {
        return ReturnMoneyWayId;
    }

    public void setReturnMoneyWayId(String ReturnMoneyWayId) {
        this.ReturnMoneyWayId = ReturnMoneyWayId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysConsumeOrder{" +
        ", CustomerCode=" + CustomerCode +
        ", id=" + id +
        ", CustomerId=" + CustomerId +
        ", CreateTime=" + CreateTime +
        ", CreateId=" + CreateId +
        ", CreateName=" + CreateName +
        ", OrderNumber=" + OrderNumber +
        ", ShopId=" + ShopId +
        ", SdadiumId=" + SdadiumId +
        ", Discount=" + Discount +
        ", ReduceMoney=" + ReduceMoney +
        ", AllReduceMoney=" + AllReduceMoney +
        ", RealMoney=" + RealMoney +
        ", ShouldMoney=" + ShouldMoney +
        ", OriginalPrice=" + OriginalPrice +
        ", CostMoney=" + CostMoney +
        ", CouponMoney=" + CouponMoney +
        ", RemainMoney=" + RemainMoney +
        ", ConsumeState=" + ConsumeState +
        ", MemberCardId=" + MemberCardId +
        ", AgreeId=" + AgreeId +
        ", AgreeMoney=" + AgreeMoney +
        ", PayState=" + PayState +
        ", Phone=" + Phone +
        ", Name=" + Name +
        ", NameId=" + NameId +
        ", BuyerNamePart=" + BuyerNamePart +
        ", BuyerNameName=" + BuyerNameName +
        ", OpeningId=" + OpeningId +
        ", IfOffset=" + IfOffset +
        ", CashPledge=" + CashPledge +
        ", CheckTime=" + CheckTime +
        ", CheckState=" + CheckState +
        ", CheckPeople=" + CheckPeople +
        ", CheckTime1=" + CheckTime1 +
        ", CheckState1=" + CheckState1 +
        ", CheckReason1=" + CheckReason1 +
        ", CheckOperator1=" + CheckOperator1 +
        ", CheckTime2=" + CheckTime2 +
        ", CheckState2=" + CheckState2 +
        ", CheckReason2=" + CheckReason2 +
        ", CheckOperator2=" + CheckOperator2 +
        ", SaleId=" + SaleId +
        ", SaleName=" + SaleName +
        ", OffsetId=" + OffsetId +
        ", ReturnMoneyWay=" + ReturnMoneyWay +
        ", ReturnMoneyWayId=" + ReturnMoneyWayId +
        "}";
    }
}
