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
 * 完成订单，业绩分配人员比例（订单业绩分配人员设置）
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-17
 */
@TableName("fr_card_order_allot_set")
public class FrCardOrderAllotSet extends Model<FrCardOrderAllotSet> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @TableField("order_id")
    private String orderId;
    /**
     * 订单类型（1、新购；6、补余）
     */
    @TableField("order_type")
    private Integer orderType;
    /**
     * 业绩分配类型（以实际销售价格的多少为销售业绩）（1、分配百分百；2、分配金额）
     */
    @TableField("allot_type")
    private Integer allotType;
    /**
     * 分配数额（百分百或者实际金额，根据分配类型判断）
     */
    @TableField("allot_num")
    private Double allotNum;
    /**
     * 销售业绩分配类型（0、无业绩分配；1、按比例分配；2、按金额分配）
     */
    @TableField("sale_allot_type")
    private Integer saleAllotType;
    /**
     * 员工id
     */
    @TableField("personnel_id")
    private String personnelId;
    /**
     * 员工分配数额（百分百或者实际金额，根据分配类型判断）
     */
    @TableField("sale_allot_num")
    private String saleAllotNum;
    /**
     * 备用
     */
    private String flag;
    /**
     * 类型(备用)
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
    @TableId(value = "id", type = IdType.UUID)
    private String id;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getAllotType() {
        return allotType;
    }

    public void setAllotType(Integer allotType) {
        this.allotType = allotType;
    }

    public Double getAllotNum() {
        return allotNum;
    }

    public void setAllotNum(Double allotNum) {
        this.allotNum = allotNum;
    }

    public Integer getSaleAllotType() {
        return saleAllotType;
    }

    public void setSaleAllotType(Integer saleAllotType) {
        this.saleAllotType = saleAllotType;
    }

    public String getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(String personnelId) {
        this.personnelId = personnelId;
    }

    public String getSaleAllotNum() {
        return saleAllotNum;
    }

    public void setSaleAllotNum(String saleAllotNum) {
        this.saleAllotNum = saleAllotNum;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrCardOrderAllotSet{" +
                ", orderId=" + orderId +
                ", orderType=" + orderType +
                ", allotType=" + allotType +
                ", allotNum=" + allotNum +
                ", saleAllotType=" + saleAllotType +
                ", personnelId=" + personnelId +
                ", saleAllotNum=" + saleAllotNum +
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
                ", id=" + id +
                "}";
    }
}
