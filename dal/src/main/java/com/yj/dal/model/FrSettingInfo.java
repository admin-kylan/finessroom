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
 * @since 2018-12-03
 */
@TableName("fr_setting_info")
public class FrSettingInfo extends Model<FrSettingInfo> {

    private static final long serialVersionUID = 1L;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField("customer_code")
    private String customerCode;
    private String note;
    private String key1;
    @TableField("create_user")
    private Long createUser;
    @TableField("is_using")
    private Integer isUsing;
    @TableField("update_user")
    private Long updateUser;
    private String value;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    private Integer type;
    @TableId(value = "id")
    private String id;
    //场馆ID
    @TableField("sdaduim_id")
    private String sdaduimId;

    public String getSdaduimId() {
        return sdaduimId;
    }

    public void setSdaduimId(String sdaduimId) {
        this.sdaduimId = sdaduimId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getKey1() {
        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Integer getIsUsing() {
        return isUsing;
    }

    public void setIsUsing(Integer isUsing) {
        this.isUsing = isUsing;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrSettingInfo{" +
        ", updateTime=" + updateTime +
        ", customerCode=" + customerCode +
        ", note=" + note +
        ", key=" + key1 +
        ", createUser=" + createUser +
        ", isUsing=" + isUsing +
        ", updateUser=" + updateUser +
        ", value=" + value +
        ", createTime=" + createTime +
        ", type=" + type +
        ", id=" + id +
        "}";
    }
}
