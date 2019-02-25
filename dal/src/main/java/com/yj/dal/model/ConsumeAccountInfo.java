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
@TableName("ConsumeAccountInfo")
public class ConsumeAccountInfo extends Model<ConsumeAccountInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户代码
     */
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    /**
     * 消费账单Id
     */
    private String ConsumeAccountOrderId;
    /**
     *
     */
    private String ShopId;
    /**
     *
     */
    private String SdadiumId;
    /**
     * 关联的表名称（例如私教开始表名称）
     */
    private String TableName;
    /**
     * 客户Id
     */
    private String CustomerId;
    /**
     * 会员卡Id
     */
    private String MemberCardId;
    /**
     * 增购Id
     */
    private String AddprojectId;
    /**
     * 协议客户Id
     */
    private String AgreeId;
    /**
     * 项目Id
     */
    private String ProjectId;
    /**
     * 课程Id
     */
    private String CourseId;
    /**
     * 单位
     */
    private String Unit;
    /**
     * 弹夹
     */
    private Double Price;
    /**
     * 数量
     */
    private Double Count;
    /**
     * 小计
     */
    private Double Total;
    /**
     * 开始时间
     */
    private Date StartTime;
    /**
     * 结束时间
     */
    private Date EndTime;
    /**
     * 状态 0 未付款 1 已付款
     */
    private Integer State;
    /**
     * 0普通消费 1 会员消费 2员工消费 3 挂账 4挂单
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
     * 付款状态 0 全款 1 定金
     */
    private Boolean PayState;
    /**
     * 会员姓名
     */
    private String Name;
    /**
     * 会员手机号
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
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    private String OrderNumber;
    private String CreateName;
    private String TableId;

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

    public String getConsumeAccountOrderId() {
        return ConsumeAccountOrderId;
    }

    public void setConsumeAccountOrderId(String ConsumeAccountOrderId) {
        this.ConsumeAccountOrderId = ConsumeAccountOrderId;
    }

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String TableName) {
        this.TableName = TableName;
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

    public String getProjectId() {
        return ProjectId;
    }

    public void setProjectId(String ProjectId) {
        this.ProjectId = ProjectId;
    }

    public String getCourseId() {
        return CourseId;
    }

    public void setCourseId(String CourseId) {
        this.CourseId = CourseId;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double Price) {
        this.Price = Price;
    }

    public Double getCount() {
        return Count;
    }

    public void setCount(Double Count) {
        this.Count = Count;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double Total) {
        this.Total = Total;
    }

    public Date getStartTime() {
        return StartTime;
    }

    public void setStartTime(Date StartTime) {
        this.StartTime = StartTime;
    }

    public Date getEndTime() {
        return EndTime;
    }

    public void setEndTime(Date EndTime) {
        this.EndTime = EndTime;
    }

    public Integer getState() {
        return State;
    }

    public void setState(Integer State) {
        this.State = State;
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

    public Boolean getPayState() {
        return PayState;
    }

    public void setPayState(Boolean PayState) {
        this.PayState = PayState;
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

    public String getCreateName() {
        return CreateName;
    }

    public void setCreateName(String CreateName) {
        this.CreateName = CreateName;
    }

    public String getTableId() {
        return TableId;
    }

    public void setTableId(String TableId) {
        this.TableId = TableId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ConsumeAccountInfo{" +
        ", CustomerCode=" + CustomerCode +
        ", ConsumeAccountOrderId=" + ConsumeAccountOrderId +
        ", TableName=" + TableName +
        ", CustomerId=" + CustomerId +
        ", MemberCardId=" + MemberCardId +
        ", AddprojectId=" + AddprojectId +
        ", AgreeId=" + AgreeId +
        ", ProjectId=" + ProjectId +
        ", CourseId=" + CourseId +
        ", Unit=" + Unit +
        ", Price=" + Price +
        ", Count=" + Count +
        ", Total=" + Total +
        ", StartTime=" + StartTime +
        ", EndTime=" + EndTime +
        ", State=" + State +
        ", ConsumeState=" + ConsumeState +
        ", PaymentMoney=" + PaymentMoney +
        ", OtherPayMoney=" + OtherPayMoney +
        ", CardMoney=" + CardMoney +
        ", CardTime=" + CardTime +
        ", AgreeMoney=" + AgreeMoney +
        ", AddProjectTime=" + AddProjectTime +
        ", PayState=" + PayState +
        ", Name=" + Name +
        ", Phone=" + Phone +
        ", SaleId=" + SaleId +
        ", CreateId=" + CreateId +
        ", CreateTime=" + CreateTime +
        ", id=" + id +
        ", OrderNumber=" + OrderNumber +
        ", CreateName=" + CreateName +
        ", TableId=" + TableId +
        "}";
    }
}
