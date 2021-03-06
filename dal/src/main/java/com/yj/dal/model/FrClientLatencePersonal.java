package com.yj.dal.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 潜在客户关系表
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-28
 */
@TableName("fr_client_latence_personal")
public class FrClientLatencePersonal extends Model<FrClientLatencePersonal> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户id
     */
    @TableField("client_id")
    private String clientId;
    /**
     * 门店id
     */
    @TableField("shop_id")
    private String shopId;
    /**
     * 角色id
     */
    @TableField("role_info_id")
    private String roleInfoId;
    /**
     * 员工id
     */
    @TableField("personal_id")
    private String personalId;
    /**
     * 保护天数
     */
    @TableField("protect_day")
    private Integer protectDay;
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;
    @TableField(value = "update_user_id", fill = FieldFill.INSERT_UPDATE)
    private String updateUserId;
    private Integer type;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    private String flag;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    @TableField(value = "update_user_name", fill = FieldFill.INSERT_UPDATE)
    private String updateUserName;
    @TableField(value = "create_user_name", fill = FieldFill.INSERT)
    private String createUserName;
    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    private String createUserId;
    @TableField("is_using")
    private Boolean isUsing;


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getRoleInfoId() {
        return roleInfoId;
    }

    public void setRoleInfoId(String roleInfoId) {
        this.roleInfoId = roleInfoId;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public Integer getProtectDay() {
        return protectDay;
    }

    public void setProtectDay(Integer protectDay) {
        this.protectDay = protectDay;
    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Boolean getUsing() {
        return isUsing;
    }

    public void setUsing(Boolean isUsing) {
        this.isUsing = isUsing;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrClientLatencePersonal{" +
        ", clientId=" + clientId +
        ", shopId=" + shopId +
        ", roleInfoId=" + roleInfoId +
        ", personalId=" + personalId +
        ", protectDay=" + protectDay +
        ", CustomerCode=" + CustomerCode +
        ", updateTime=" + updateTime +
        ", updateUserId=" + updateUserId +
        ", type=" + type +
        ", id=" + id +
        ", flag=" + flag +
        ", createTime=" + createTime +
        ", updateUserName=" + updateUserName +
        ", createUserName=" + createUserName +
        ", createUserId=" + createUserId +
        ", isUsing=" + isUsing +
        "}";
    }
}
