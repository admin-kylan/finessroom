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
 * @since 2018-12-02
 */
@TableName("fr_private_package")
public class FrPrivatePackage extends Model<FrPrivatePackage> {

    private static final long serialVersionUID = 1L;

    @TableField("promtion_price")
    private Double promtionPrice;
    @TableField("valid_value")
    private Integer validValue;
    @TableField("class_count")
    private String classCount;
    private String name;
    @TableField("create_user")
    private String createUser;
    @TableId(value = "id")
    private String id;
    @TableField("valid_type")
    private Integer validType;
    @TableField("is_using")
    private Integer isUsing;
    @TableField("market_price")
    private Double marketPrice;
    @TableField("remain_count_notice")
    private Integer remainCountNotice;
    @TableField("class_count_desc")
    private String classCountDesc;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField("member_price")
    private Double memberPrice;
    @TableField("is_show_desk")
    private Integer isShowDesk;
    @TableField("customer_code")
    private String customerCode;
    private Integer count;
    @TableField("is_account_spending")
    private Integer isAccountSpending;
    @TableField("update_user")
    private String updateUser;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;


    public Double getPromtionPrice() {
        return promtionPrice;
    }

    public void setPromtionPrice(Double promtionPrice) {
        this.promtionPrice = promtionPrice;
    }

    public Integer getValidValue() {
        return validValue;
    }

    public void setValidValue(Integer validValue) {
        this.validValue = validValue;
    }

    public String getClassCount() {
        return classCount;
    }

    public void setClassCount(String classCount) {
        this.classCount = classCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getValidType() {
        return validType;
    }

    public void setValidType(Integer validType) {
        this.validType = validType;
    }

    public Integer getIsUsing() {
        return isUsing;
    }

    public void setIsUsing(Integer isUsing) {
        this.isUsing = isUsing;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getRemainCountNotice() {
        return remainCountNotice;
    }

    public void setRemainCountNotice(Integer remainCountNotice) {
        this.remainCountNotice = remainCountNotice;
    }

    public String getClassCountDesc() {
        return classCountDesc;
    }

    public void setClassCountDesc(String classCountDesc) {
        this.classCountDesc = classCountDesc;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Double getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(Double memberPrice) {
        this.memberPrice = memberPrice;
    }

    public Integer getIsShowDesk() {
        return isShowDesk;
    }

    public void setIsShowDesk(Integer isShowDesk) {
        this.isShowDesk = isShowDesk;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getIsAccountSpending() {
        return isAccountSpending;
    }

    public void setIsAccountSpending(Integer isAccountSpending) {
        this.isAccountSpending = isAccountSpending;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrPrivatePackage{" +
        ", promtionPrice=" + promtionPrice +
        ", validValue=" + validValue +
        ", classCount=" + classCount +
        ", name=" + name +
        ", createUser=" + createUser +
        ", id=" + id +
        ", validType=" + validType +
        ", isUsing=" + isUsing +
        ", marketPrice=" + marketPrice +
        ", remainCountNotice=" + remainCountNotice +
        ", classCountDesc=" + classCountDesc +
        ", updateTime=" + updateTime +
        ", memberPrice=" + memberPrice +
        ", isShowDesk=" + isShowDesk +
        ", customerCode=" + customerCode +
        ", count=" + count +
        ", isAccountSpending=" + isAccountSpending +
        ", updateUser=" + updateUser +
        ", createTime=" + createTime +
        "}";
    }
}
