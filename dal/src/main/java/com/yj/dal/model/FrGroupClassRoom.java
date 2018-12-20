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
 * @since 2018-12-08
 */
@TableName("fr_group_class_room")
public class FrGroupClassRoom extends Model<FrGroupClassRoom> {

    private static final long serialVersionUID = 1L;

    @TableField("create_user")
    private String createUser;
    @TableField("shop_id")
    private String shopId;
    @TableField("is_need_seat")
    private Integer isNeedSeat;
    @TableField("customer_code")
    private String customerCode;
    private Double area;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    @TableField("market_price")
    private Double marketPrice;
    @TableField("sdaduim_id")
    private String sdaduimId;
    private String remark;
    @TableField("contain_num")
    private Integer containNum;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField("update_user")
    private String updateUser;
    private String name;
    @TableField("is_using")
    private Integer isUsing;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField("member_price")
    private Double memberPrice;
    @TableField("sdaduim_using")
    private String sdaduimUsing;

    public String getSdaduimUsing() {
        return sdaduimUsing;
    }

    public void setSdaduimUsing(String sdaduimUsing) {
        this.sdaduimUsing = sdaduimUsing;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Integer getIsNeedSeat() {
        return isNeedSeat;
    }

    public void setIsNeedSeat(Integer isNeedSeat) {
        this.isNeedSeat = isNeedSeat;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getSdaduimId() {
        return sdaduimId;
    }

    public void setSdaduimId(String sdaduimId) {
        this.sdaduimId = sdaduimId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getContainNum() {
        return containNum;
    }

    public void setContainNum(Integer containNum) {
        this.containNum = containNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsUsing() {
        return isUsing;
    }

    public void setIsUsing(Integer isUsing) {
        this.isUsing = isUsing;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrGroupClassRoom{" +
        ", createUser=" + createUser +
        ", shopId=" + shopId +
        ", isNeedSeat=" + isNeedSeat +
        ", customerCode=" + customerCode +
        ", area=" + area +
        ", id=" + id +
        ", marketPrice=" + marketPrice +
        ", sdaduimId=" + sdaduimId +
        ", remark=" + remark +
        ", containNum=" + containNum +
        ", createTime=" + createTime +
        ", updateUser=" + updateUser +
        ", name=" + name +
        ", isUsing=" + isUsing +
        ", updateTime=" + updateTime +
        ", memberPrice=" + memberPrice +
        "}";
    }
}
