package com.yj.dal.model;

import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;
import java.util.Date;

public class ProjectOrder extends Model<ProjectOrder> {
    private static final long serialVersionUID = 1L;
    //客户代码 （方便客户导出资料）
    private String CustomerCode;
    //id
    private String ID;
    //订单编号
    private String OrderNumber;
    //对象ID
    private String ObjectId;
    //0-增购 1-增购消费 2-增购套餐
    private Integer OrderType;
    //售价
    private Double Price;
    //购买数量
    private Integer BuyCount;
    //赠送数量
    private Integer SendCount;
    //总数量
    private Integer AllCount;
    //0-未付款 1-已付款 2-退款中 3-已退款
    private Integer PayStatus;
    //销售员ID
    private String SalesId;
    //销售人员
    private String SalesName;

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

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        OrderNumber = orderNumber;
    }

    public String getObjectId() {
        return ObjectId;
    }

    public void setObjectId(String objectId) {
        ObjectId = objectId;
    }

    public Integer getOrderType() {
        return OrderType;
    }

    public void setOrderType(Integer orderType) {
        OrderType = orderType;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public Integer getBuyCount() {
        return BuyCount;
    }

    public void setBuyCount(Integer buyCount) {
        BuyCount = buyCount;
    }

    public Integer getSendCount() {
        return SendCount;
    }

    public void setSendCount(Integer sendCount) {
        SendCount = sendCount;
    }

    public Integer getAllCount() {
        return AllCount;
    }

    public void setAllCount(Integer allCount) {
        AllCount = allCount;
    }

    public Integer getPayStatus() {
        return PayStatus;
    }

    public void setPayStatus(Integer payStatus) {
        PayStatus = payStatus;
    }

    public String getSalesId() {
        return SalesId;
    }

    public void setSalesId(String salesId) {
        SalesId = salesId;
    }

    public String getSalesName() {
        return SalesName;
    }

    public void setSalesName(String salesName) {
        SalesName = salesName;
    }

    @Override
    protected Serializable pkVal() {
        return this.ID;
    }

    public String toString() {
        return "ProjectOrder{" +
                "CustomerCode='" + CustomerCode + '\'' +
                ", ID='" + ID + '\'' +
                ", OrderNumber='" + OrderNumber + '\'' +
                ", ObjectId='" + ObjectId + '\'' +
                ", OrderType=" + OrderType +
                ", Price=" + Price +
                ", BuyCount=" + BuyCount +
                ", SendCount=" + SendCount +
                ", AllCount=" + AllCount +
                ", PayStatus=" + PayStatus +
                ", SalesId='" + SalesId + '\'' +
                ", SalesName='" + SalesName + '\'' +
                '}';
    }
}