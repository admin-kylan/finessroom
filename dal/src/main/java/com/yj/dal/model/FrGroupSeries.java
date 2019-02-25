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
 * @since 2018-12-06
 */
@TableName("fr_group_series")
public class FrGroupSeries extends Model<FrGroupSeries> {

    private static final long serialVersionUID = 1L;

    @TableField("is_using")
    private Integer isUsing;
    private String note;
    @TableId(value = "id")
    private String id;
    @TableField("create_user")
    private Long createUser;
    @TableField("is_chain")
    private Integer isChain;
    @TableField("update_user")
    private Long updateUser;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    private String name;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField("parent_id")
    private String parentId;
    @TableField("customer_code")
    private String customerCode;

    //场馆ID
    @TableField("sdaduim_id")
    private String sdaduimId;

    public String getSdaduimId() {
        return sdaduimId;
    }

    public void setSdaduimId(String sdaduimId) {
        this.sdaduimId = sdaduimId;
    }
    public Integer getIsUsing() {
        return isUsing;
    }

    public void setIsUsing(Integer isUsing) {
        this.isUsing = isUsing;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Integer getIsChain() {
        return isChain;
    }

    public void setIsChain(Integer isChain) {
        this.isChain = isChain;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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
        return "FrGroupSeries{" +
        ", isUsing=" + isUsing +
        ", note=" + note +
        ", id=" + id +
        ", createUser=" + createUser +
        ", isChain=" + isChain +
        ", updateUser=" + updateUser +
        ", createTime=" + createTime +
        ", name=" + name +
        ", updateTime=" + updateTime +
        ", parentId=" + parentId +
        ", customerCode=" + customerCode +
        "}";
    }
}
