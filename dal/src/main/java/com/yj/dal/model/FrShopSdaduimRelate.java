package com.yj.dal.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.yj.dal.base.BaseModel;

import java.io.Serializable;

/**
 * <p>
 * 门店-门店场馆关联表
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-21
 */
@TableName("fr_shop_sdaduim_relate")
public class FrShopSdaduimRelate extends Model<FrShopSdaduimRelate> {

    private static final long serialVersionUID = 1L;

    /**
     * 基础表门店ID
     */
    @TableField("shop_id")
    private String shopId;
    /**
     * 客户场馆ID
     */
    @TableField("sdaduim_id")
    private String sdaduimId;
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
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
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
        return "FrShopSdaduimRelate{" +
        ", shopId=" + shopId +
        ", sdaduimId=" + sdaduimId +
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
