package com.yj.dal.model;

import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;
import java.util.Date;

public class SysConsumeOrder extends Model<SysConsumeOrder> {
    private static final long serialVersionUID = 1L;
    //客户代码 （方便客户导出资料）
    private String CustomerCode;
    //id
    private String ID;
    //客户ID
    private String CustomerId;
    //创建时间
    private Date CreateTime;
    //操作员ID
    private String CreateId;
    //创建人姓名
    private String CreateName;
    //单号
    private String OrderNumber;
    //门店ID
    private Integer ShopId;
    //场馆ID
    private Integer SdadiumId;
    //折扣
    private double Discount;
    //减去金额
    private double ReduceMoney;
    //满额金额
    private double AllReduceMoney;
    //满额减去金额
    private double RealMoney;
    //实收金额
    private double ShouldMoney;
    //原价
    private double OriginalPrice;
    //应付金额
    private double CostMoney;
    //优惠金额
    private double CouponMoney;
    //找零
    private double RemainMoney;
    //消费状态 0普通消费 1 会员消费 2员工消费 3 挂账 4挂单
    private Integer ConsumeState;
    //会员卡ID
    private String MemberCardId;
    //协议单位ID
    private double AgreeId;
    //协议金额
    private double AgreeMoney;
    //付款状态0-未付款 1-已付款 2-退款中 3-已退款
    private Integer PayState;
    //手机号码
    private String Phone;
    //姓名
    private String Name;
    //优惠授权人名字
    private String NameId;
    //挂账授权人部门
    private String BuyerNamePart;
    //授权人名字
    private String BuyerNameName;
    //开场ID
    private String OpeningId;
    //是否冲销 0 否 1 是
    private Integer IfOffset;
    //押金
    private String CashPledge;
    //审核时间
    private Date CheckTime;
    //审核状态(0-未审核，1-审核通过，2-审核不通过)
    private Integer CheckState;
    //审核员ID
    private String CheckPeople;
    //审核时间1
    private Date CheckTime1;
    //审核状态(0-未审核，1-审核通过，2-审核不通过)
    private Integer CheckState1;
    //审核原因1
    private String CheckReason1;
    //审核员1
    private String CheckOperator1;
    //审核时间2
    private Date CheckTime2;
    //审核状态(0-未审核，1-审核通过，2-审核不通过)
    private Integer CheckState2;
    //审核原因2
    private String CheckReason2;
    //审核员2
    private String CheckOperator2;
    //销售人员
    private String SaleId;
    //销售人员姓名
    private String SaleName;
    //冲销账单ID
    private String OffsetId;
    //其他支付对应
    private String ReturnMoneyWay;
    //其他支付对应ID
    private Date ReturnMoneyWayId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String customerCode) {
        CustomerCode = customerCode;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public String getCreateId() {
        return CreateId;
    }

    public void setCreateId(String createId) {
        CreateId = createId;
    }

    public String getCreateName() {
        return CreateName;
    }

    public void setCreateName(String createName) {
        CreateName = createName;
    }

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        OrderNumber = orderNumber;
    }

    public Integer getShopId() {
        return ShopId;
    }

    public void setShopId(Integer shopId) {
        ShopId = shopId;
    }

    public Integer getSdadiumId() {
        return SdadiumId;
    }

    public void setSdadiumId(Integer sdadiumId) {
        SdadiumId = sdadiumId;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }

    public double getReduceMoney() {
        return ReduceMoney;
    }

    public void setReduceMoney(double reduceMoney) {
        ReduceMoney = reduceMoney;
    }

    public double getAllReduceMoney() {
        return AllReduceMoney;
    }

    public void setAllReduceMoney(double allReduceMoney) {
        AllReduceMoney = allReduceMoney;
    }

    public double getRealMoney() {
        return RealMoney;
    }

    public void setRealMoney(double realMoney) {
        RealMoney = realMoney;
    }

    public double getShouldMoney() {
        return ShouldMoney;
    }

    public void setShouldMoney(double shouldMoney) {
        ShouldMoney = shouldMoney;
    }

    public double getOriginalPrice() {
        return OriginalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        OriginalPrice = originalPrice;
    }

    public double getCostMoney() {
        return CostMoney;
    }

    public void setCostMoney(double costMoney) {
        CostMoney = costMoney;
    }

    public double getCouponMoney() {
        return CouponMoney;
    }

    public void setCouponMoney(double couponMoney) {
        CouponMoney = couponMoney;
    }

    public double getRemainMoney() {
        return RemainMoney;
    }

    public void setRemainMoney(double remainMoney) {
        RemainMoney = remainMoney;
    }

    public Integer getConsumeState() {
        return ConsumeState;
    }

    public void setConsumeState(Integer consumeState) {
        ConsumeState = consumeState;
    }

    public String getMemberCardId() {
        return MemberCardId;
    }

    public void setMemberCardId(String memberCardId) {
        MemberCardId = memberCardId;
    }

    public double getAgreeId() {
        return AgreeId;
    }

    public void setAgreeId(double agreeId) {
        AgreeId = agreeId;
    }

    public double getAgreeMoney() {
        return AgreeMoney;
    }

    public void setAgreeMoney(double agreeMoney) {
        AgreeMoney = agreeMoney;
    }

    public Integer getPayState() {
        return PayState;
    }

    public void setPayState(Integer payState) {
        PayState = payState;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNameId() {
        return NameId;
    }

    public void setNameId(String nameId) {
        NameId = nameId;
    }

    public String getBuyerNamePart() {
        return BuyerNamePart;
    }

    public void setBuyerNamePart(String buyerNamePart) {
        BuyerNamePart = buyerNamePart;
    }

    public String getBuyerNameName() {
        return BuyerNameName;
    }

    public void setBuyerNameName(String buyerNameName) {
        BuyerNameName = buyerNameName;
    }

    public String getOpeningId() {
        return OpeningId;
    }

    public void setOpeningId(String openingId) {
        OpeningId = openingId;
    }

    public Integer getIfOffset() {
        return IfOffset;
    }

    public void setIfOffset(Integer ifOffset) {
        IfOffset = ifOffset;
    }

    public String getCashPledge() {
        return CashPledge;
    }

    public void setCashPledge(String cashPledge) {
        CashPledge = cashPledge;
    }

    public Date getCheckTime() {
        return CheckTime;
    }

    public void setCheckTime(Date checkTime) {
        CheckTime = checkTime;
    }

    public Integer getCheckState() {
        return CheckState;
    }

    public void setCheckState(Integer checkState) {
        CheckState = checkState;
    }

    public String getCheckPeople() {
        return CheckPeople;
    }

    public void setCheckPeople(String checkPeople) {
        CheckPeople = checkPeople;
    }

    public Date getCheckTime1() {
        return CheckTime1;
    }

    public void setCheckTime1(Date checkTime1) {
        CheckTime1 = checkTime1;
    }

    public Integer getCheckState1() {
        return CheckState1;
    }

    public void setCheckState1(Integer checkState1) {
        CheckState1 = checkState1;
    }

    public String getCheckReason1() {
        return CheckReason1;
    }

    public void setCheckReason1(String checkReason1) {
        CheckReason1 = checkReason1;
    }

    public String getCheckOperator1() {
        return CheckOperator1;
    }

    public void setCheckOperator1(String checkOperator1) {
        CheckOperator1 = checkOperator1;
    }

    public Date getCheckTime2() {
        return CheckTime2;
    }

    public void setCheckTime2(Date checkTime2) {
        CheckTime2 = checkTime2;
    }

    public Integer getCheckState2() {
        return CheckState2;
    }

    public void setCheckState2(Integer checkState2) {
        CheckState2 = checkState2;
    }

    public String getCheckReason2() {
        return CheckReason2;
    }

    public void setCheckReason2(String checkReason2) {
        CheckReason2 = checkReason2;
    }

    public String getCheckOperator2() {
        return CheckOperator2;
    }

    public void setCheckOperator2(String checkOperator2) {
        CheckOperator2 = checkOperator2;
    }

    public String getSaleId() {
        return SaleId;
    }

    public void setSaleId(String saleId) {
        SaleId = saleId;
    }

    public String getSaleName() {
        return SaleName;
    }

    public void setSaleName(String saleName) {
        SaleName = saleName;
    }

    public String getOffsetId() {
        return OffsetId;
    }

    public void setOffsetId(String offsetId) {
        OffsetId = offsetId;
    }

    public String getReturnMoneyWay() {
        return ReturnMoneyWay;
    }

    public void setReturnMoneyWay(String returnMoneyWay) {
        ReturnMoneyWay = returnMoneyWay;
    }

    public Date getReturnMoneyWayId() {
        return ReturnMoneyWayId;
    }

    public void setReturnMoneyWayId(Date returnMoneyWayId) {
        ReturnMoneyWayId = returnMoneyWayId;
    }

    @Override
    protected Serializable pkVal() {
        return this.ID;
    }

    @Override
    public String toString() {
        return "SysConsumeOrder{" +
                "CustomerCode='" + CustomerCode + '\'' +
                ", ID='" + ID + '\'' +
                ", CustomerId='" + CustomerId + '\'' +
                ", CreateTime=" + CreateTime +
                ", CreateId='" + CreateId + '\'' +
                ", CreateName='" + CreateName + '\'' +
                ", OrderNumber='" + OrderNumber + '\'' +
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
                ", MemberCardId='" + MemberCardId + '\'' +
                ", AgreeId=" + AgreeId +
                ", AgreeMoney=" + AgreeMoney +
                ", PayState=" + PayState +
                ", Phone='" + Phone + '\'' +
                ", Name='" + Name + '\'' +
                ", NameId='" + NameId + '\'' +
                ", BuyerNamePart='" + BuyerNamePart + '\'' +
                ", BuyerNameName='" + BuyerNameName + '\'' +
                ", OpeningId='" + OpeningId + '\'' +
                ", IfOffset=" + IfOffset +
                ", CashPledge='" + CashPledge + '\'' +
                ", CheckTime=" + CheckTime +
                ", CheckState=" + CheckState +
                ", CheckPeople='" + CheckPeople + '\'' +
                ", CheckTime1=" + CheckTime1 +
                ", CheckState1=" + CheckState1 +
                ", CheckReason1='" + CheckReason1 + '\'' +
                ", CheckOperator1='" + CheckOperator1 + '\'' +
                ", CheckTime2=" + CheckTime2 +
                ", CheckState2=" + CheckState2 +
                ", CheckReason2='" + CheckReason2 + '\'' +
                ", CheckOperator2='" + CheckOperator2 + '\'' +
                ", SaleId='" + SaleId + '\'' +
                ", SaleName='" + SaleName + '\'' +
                ", OffsetId='" + OffsetId + '\'' +
                ", ReturnMoneyWay='" + ReturnMoneyWay + '\'' +
                ", ReturnMoneyWayId=" + ReturnMoneyWayId +
                '}';
    }
}