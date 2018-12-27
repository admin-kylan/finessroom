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
 * 项目订单表

 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-27
 */
@TableName("ProjectOrder")
public class ProjectOrder extends Model<ProjectOrder> {

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
     * 订单编号
     */
    private String OrderNumber;
    /**
     * 对象ID
     */
    private String ObjectId;
    /**
     * 0-增购 1-增购消费 2-增购套餐
     */
    private Integer OrderType;
    /**
     * 售价
     */
    private Double Price;
    /**
     * 购买数量
     */
    private Integer BuyCount;
    /**
     * 赠送数量
     */
    private Integer SendCount;
    /**
     * 总数量
     */
    private Integer AllCount;
    /**
     * 0-未付款 1-已付款 2-退款中 3-已退款
     */
    private Integer PayStatus;
    /**
     * 销售员ID
     */
    private String SalesId;
    /**
     * 销售人员
     */
    private String SalesName;
    /**
     * 付款方式（1、全款；2、定金；3、赠送；4、置换；5、分期付款）
     */
    private Integer PayType;
    /**
     * 消费方式（1、会员消费；2、普通消费；3、员工领用；4、协议单位；）
     */
    private Integer ConsumeType;
    /**
     * 总价
     */
    private Double TotalPrice;
    /**
     * 支付宝支付金额
     */
    private Double AlipayPrice;
    /**
     * 刷卡支付金额
     */
    private Double PayCardPrice;
    /**
     * 微信支付金额
     */
    private Double WechatPrice;
    /**
     * 现金支付金额
     */
    private Double CashPrice;
    /**
     * 转账支付金额
     */
    private Double AccountsPrice;
    /**
     * 花呗支付金额
     */
    private Double FlowerPrice;
    /**
     * 其他支付金额
     */
    private Double OtherPrice;
    /**
     * 优惠折扣
     */
    private Double Discount;
    /**
     * 整单去零
     */
    private Double ChangeNum;
    /**
     * 满减优惠类型（0、一次；1、可重复）
     */
    private Boolean FullType;
    /**
     * 满
     */
    private Double DiscountFull;
    /**
     * 减
     */
    private Double DiscountReduce;
    /**
     * 折扣/优惠
     */
    private Double DiscountPrice;
    /**
     * 抵扣金额
     */
    private Double DiscountNum;
    /**
     * 应付金额
     */
    private Double NeedPrice;
    /**
     * 未付金额
     */
    private Double NoPrice;
    /**
     * 找零
     */
    private Double RetChange;
    /**
     * 基础表门店ID
     */
    private String ShopId;
    /**
     * 人员（员工）ID
     */
    private String PersonnelId;
    /**
     * 备用
     */
    private String Flag;
    /**
     * 类型(备用)
     */
    private Integer Type;
    /**
     * 创建人时间
     */
    private Date CreateTime;
    /**
     * 更新时间
     */
    private Date UpdateTime;
    /**
     * 创建人名称
     */
    private String CreateUserName;
    /**
     * 更新人名称
     */
    private String UpdateUserName;
    /**
     * 创建人ID
     */
    private String CreateUserId;
    /**
     * 更新人ID
     */
    private String UpdateUserId;
    /**
     * 财务审核 （0，待审核1，已审核，2，审核不通过）
     */
    private Integer Status;
    /**
     * 相关复核 （0，待审核1，已审核，2，审核不通过）
     */
    private Integer AuditStatus;
    /**
     * 审核人员ID
     */
    private String StatusUserId;
    /**
     * 复审核人员ID
     */
    private String AuditUserId;
    /**
     * 补余期限
     */
    private String DeposiTime;
    /**
     * 是否购置税发票设置
     */
    private Integer InvoiceStatus;
    /**
     * 业绩分配类型（以实际销售价格的多少为销售业绩）（0、无业绩分配；1、分配百分百；2、分配金额）
     */
    private Integer AllotType;
    /**
     * 分配数额（百分百或者实际金额，根据分配类型判断）
     */
    private Double AllotNum;
    /**
     * 销售业绩分配类型（0、无业绩分配；1、按比例分配；2、按金额分配）
     */
    private Integer SaleAllotType;
    /**
     * 分配数额（百分百或者实际金额，根据分配类型判断）
     */
    private Double SaleAllotNum;


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

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String OrderNumber) {
        this.OrderNumber = OrderNumber;
    }

    public String getObjectId() {
        return ObjectId;
    }

    public void setObjectId(String ObjectId) {
        this.ObjectId = ObjectId;
    }

    public Integer getOrderType() {
        return OrderType;
    }

    public void setOrderType(Integer OrderType) {
        this.OrderType = OrderType;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double Price) {
        this.Price = Price;
    }

    public Integer getBuyCount() {
        return BuyCount;
    }

    public void setBuyCount(Integer BuyCount) {
        this.BuyCount = BuyCount;
    }

    public Integer getSendCount() {
        return SendCount;
    }

    public void setSendCount(Integer SendCount) {
        this.SendCount = SendCount;
    }

    public Integer getAllCount() {
        return AllCount;
    }

    public void setAllCount(Integer AllCount) {
        this.AllCount = AllCount;
    }

    public Integer getPayStatus() {
        return PayStatus;
    }

    public void setPayStatus(Integer PayStatus) {
        this.PayStatus = PayStatus;
    }

    public String getSalesId() {
        return SalesId;
    }

    public void setSalesId(String SalesId) {
        this.SalesId = SalesId;
    }

    public String getSalesName() {
        return SalesName;
    }

    public void setSalesName(String SalesName) {
        this.SalesName = SalesName;
    }

    public Integer getPayType() {
        return PayType;
    }

    public void setPayType(Integer PayType) {
        this.PayType = PayType;
    }

    public Integer getConsumeType() {
        return ConsumeType;
    }

    public void setConsumeType(Integer ConsumeType) {
        this.ConsumeType = ConsumeType;
    }

    public Double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(Double TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public Double getAlipayPrice() {
        return AlipayPrice;
    }

    public void setAlipayPrice(Double AlipayPrice) {
        this.AlipayPrice = AlipayPrice;
    }

    public Double getPayCardPrice() {
        return PayCardPrice;
    }

    public void setPayCardPrice(Double PayCardPrice) {
        this.PayCardPrice = PayCardPrice;
    }

    public Double getWechatPrice() {
        return WechatPrice;
    }

    public void setWechatPrice(Double WechatPrice) {
        this.WechatPrice = WechatPrice;
    }

    public Double getCashPrice() {
        return CashPrice;
    }

    public void setCashPrice(Double CashPrice) {
        this.CashPrice = CashPrice;
    }

    public Double getAccountsPrice() {
        return AccountsPrice;
    }

    public void setAccountsPrice(Double AccountsPrice) {
        this.AccountsPrice = AccountsPrice;
    }

    public Double getFlowerPrice() {
        return FlowerPrice;
    }

    public void setFlowerPrice(Double FlowerPrice) {
        this.FlowerPrice = FlowerPrice;
    }

    public Double getOtherPrice() {
        return OtherPrice;
    }

    public void setOtherPrice(Double OtherPrice) {
        this.OtherPrice = OtherPrice;
    }

    public Double getDiscount() {
        return Discount;
    }

    public void setDiscount(Double Discount) {
        this.Discount = Discount;
    }

    public Double getChangeNum() {
        return ChangeNum;
    }

    public void setChangeNum(Double ChangeNum) {
        this.ChangeNum = ChangeNum;
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

    public Double getDiscountPrice() {
        return DiscountPrice;
    }

    public void setDiscountPrice(Double DiscountPrice) {
        this.DiscountPrice = DiscountPrice;
    }

    public Double getDiscountNum() {
        return DiscountNum;
    }

    public void setDiscountNum(Double DiscountNum) {
        this.DiscountNum = DiscountNum;
    }

    public Double getNeedPrice() {
        return NeedPrice;
    }

    public void setNeedPrice(Double NeedPrice) {
        this.NeedPrice = NeedPrice;
    }

    public Double getNoPrice() {
        return NoPrice;
    }

    public void setNoPrice(Double NoPrice) {
        this.NoPrice = NoPrice;
    }

    public Double getRetChange() {
        return RetChange;
    }

    public void setRetChange(Double RetChange) {
        this.RetChange = RetChange;
    }

    public String getShopId() {
        return ShopId;
    }

    public void setShopId(String ShopId) {
        this.ShopId = ShopId;
    }

    public String getPersonnelId() {
        return PersonnelId;
    }

    public void setPersonnelId(String PersonnelId) {
        this.PersonnelId = PersonnelId;
    }

    public String getFlag() {
        return Flag;
    }

    public void setFlag(String Flag) {
        this.Flag = Flag;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer Type) {
        this.Type = Type;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }

    public Date getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(Date UpdateTime) {
        this.UpdateTime = UpdateTime;
    }

    public String getCreateUserName() {
        return CreateUserName;
    }

    public void setCreateUserName(String CreateUserName) {
        this.CreateUserName = CreateUserName;
    }

    public String getUpdateUserName() {
        return UpdateUserName;
    }

    public void setUpdateUserName(String UpdateUserName) {
        this.UpdateUserName = UpdateUserName;
    }

    public String getCreateUserId() {
        return CreateUserId;
    }

    public void setCreateUserId(String CreateUserId) {
        this.CreateUserId = CreateUserId;
    }

    public String getUpdateUserId() {
        return UpdateUserId;
    }

    public void setUpdateUserId(String UpdateUserId) {
        this.UpdateUserId = UpdateUserId;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }

    public Integer getAuditStatus() {
        return AuditStatus;
    }

    public void setAuditStatus(Integer AuditStatus) {
        this.AuditStatus = AuditStatus;
    }

    public String getStatusUserId() {
        return StatusUserId;
    }

    public void setStatusUserId(String StatusUserId) {
        this.StatusUserId = StatusUserId;
    }

    public String getAuditUserId() {
        return AuditUserId;
    }

    public void setAuditUserId(String AuditUserId) {
        this.AuditUserId = AuditUserId;
    }

    public String getDeposiTime() {
        return DeposiTime;
    }

    public void setDeposiTime(String DeposiTime) {
        this.DeposiTime = DeposiTime;
    }

    public Integer getInvoiceStatus() {
        return InvoiceStatus;
    }

    public void setInvoiceStatus(Integer InvoiceStatus) {
        this.InvoiceStatus = InvoiceStatus;
    }

    public Integer getAllotType() {
        return AllotType;
    }

    public void setAllotType(Integer AllotType) {
        this.AllotType = AllotType;
    }

    public Double getAllotNum() {
        return AllotNum;
    }

    public void setAllotNum(Double AllotNum) {
        this.AllotNum = AllotNum;
    }

    public Integer getSaleAllotType() {
        return SaleAllotType;
    }

    public void setSaleAllotType(Integer SaleAllotType) {
        this.SaleAllotType = SaleAllotType;
    }

    public Double getSaleAllotNum() {
        return SaleAllotNum;
    }

    public void setSaleAllotNum(Double SaleAllotNum) {
        this.SaleAllotNum = SaleAllotNum;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ProjectOrder{" +
        ", CustomerCode=" + CustomerCode +
        ", id=" + id +
        ", OrderNumber=" + OrderNumber +
        ", ObjectId=" + ObjectId +
        ", OrderType=" + OrderType +
        ", Price=" + Price +
        ", BuyCount=" + BuyCount +
        ", SendCount=" + SendCount +
        ", AllCount=" + AllCount +
        ", PayStatus=" + PayStatus +
        ", SalesId=" + SalesId +
        ", SalesName=" + SalesName +
        ", PayType=" + PayType +
        ", ConsumeType=" + ConsumeType +
        ", TotalPrice=" + TotalPrice +
        ", AlipayPrice=" + AlipayPrice +
        ", PayCardPrice=" + PayCardPrice +
        ", WechatPrice=" + WechatPrice +
        ", CashPrice=" + CashPrice +
        ", AccountsPrice=" + AccountsPrice +
        ", FlowerPrice=" + FlowerPrice +
        ", OtherPrice=" + OtherPrice +
        ", Discount=" + Discount +
        ", ChangeNum=" + ChangeNum +
        ", FullType=" + FullType +
        ", DiscountFull=" + DiscountFull +
        ", DiscountReduce=" + DiscountReduce +
        ", DiscountPrice=" + DiscountPrice +
        ", DiscountNum=" + DiscountNum +
        ", NeedPrice=" + NeedPrice +
        ", NoPrice=" + NoPrice +
        ", RetChange=" + RetChange +
        ", ShopId=" + ShopId +
        ", PersonnelId=" + PersonnelId +
        ", Flag=" + Flag +
        ", Type=" + Type +
        ", CreateTime=" + CreateTime +
        ", UpdateTime=" + UpdateTime +
        ", CreateUserName=" + CreateUserName +
        ", UpdateUserName=" + UpdateUserName +
        ", CreateUserId=" + CreateUserId +
        ", UpdateUserId=" + UpdateUserId +
        ", Status=" + Status +
        ", AuditStatus=" + AuditStatus +
        ", StatusUserId=" + StatusUserId +
        ", AuditUserId=" + AuditUserId +
        ", DeposiTime=" + DeposiTime +
        ", InvoiceStatus=" + InvoiceStatus +
        ", AllotType=" + AllotType +
        ", AllotNum=" + AllotNum +
        ", SaleAllotType=" + SaleAllotType +
        ", SaleAllotNum=" + SaleAllotNum +
        "}";
    }
}
