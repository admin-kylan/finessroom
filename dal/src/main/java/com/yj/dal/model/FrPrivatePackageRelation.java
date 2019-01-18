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
 * @since 2018-11-28
 */
@TableName("fr_private_package_relation")
public class FrPrivatePackageRelation extends Model<FrPrivatePackageRelation> {

    private static final long serialVersionUID = 1L;

    /**
     * 私教套餐关系表ID
     */
    @TableId(value = "id")
    private String id;
    /**
     * 店铺ID
     */
    @TableField("shop_id")
    private String shopId;
    /**
     * 场馆ID
     */
    @TableField("sdaduim_id")
    private String sdaduimId;
    /**
     * 套餐ID
     */
    @TableField("package_id")
    private String packageId;
    /**
     * 课程ID
     */
    @TableField("cource_id")
    private String courceId;
    /**
     * 课程数量
     */
    @TableField("cource_count")
    private Integer courceCount;
    /**
     * 每天限用数量
     */
    @TableField("day_limit_count")
    private Integer dayLimitCount;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField("update_user")
    private String updateUser;
    @TableField("create_user")
    private String createUser;
    @TableField("is_using")
    private Integer isUsing;
    @TableField("customer_code")
    private String customerCode;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getSdaduimId() {
        return sdaduimId;
    }

    public void setSdaduimId(String sdaduimId) {
        this.sdaduimId = sdaduimId;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getCourceId() {
        return courceId;
    }

    public void setCourceId(String courceId) {
        this.courceId = courceId;
    }

    public Integer getCourceCount() {
        return courceCount;
    }

    public void setCourceCount(Integer courceCount) {
        this.courceCount = courceCount;
    }

    public Integer getDayLimitCount() {
        return dayLimitCount;
    }

    public void setDayLimitCount(Integer dayLimitCount) {
        this.dayLimitCount = dayLimitCount;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Integer getIsUsing() {
        return isUsing;
    }

    public void setIsUsing(Integer isUsing) {
        this.isUsing = isUsing;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrPrivatePackageRelation{" +
        ", id=" + id +
        ", shopId=" + shopId +
        ", sdaduimId=" + sdaduimId +
        ", packageId=" + packageId +
        ", courceId=" + courceId +
        ", courceCount=" + courceCount +
        ", dayLimitCount=" + dayLimitCount +
        ", updateTime=" + updateTime +
        ", createTime=" + createTime +
        ", updateUser=" + updateUser +
        ", createUser=" + createUser +
        ", isUsing=" + isUsing +
        ", customerCode=" + customerCode +
        "}";
    }
}
