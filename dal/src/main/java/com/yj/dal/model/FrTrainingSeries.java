package com.yj.dal.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-15
 */
@TableName("fr_training_series")
public class FrTrainingSeries extends Model<FrTrainingSeries> {

    private static final long serialVersionUID = 1L;

    @TableField("create_user")
    private String createUser;
    private Integer type;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    private String name;
    @TableField("update_user")
    private String updateUser;
    @TableField("parent_id")
    private String parentId;
    @TableField(value = "own_type")//1或者空|私教 2|团教
    private Integer ownType;
    @TableField("customer_code")
    private String customerCode;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField("is_using")
    private Integer isUsing;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    @TableField(exist = false)
    private List<FrTrainingSeries> frTrainingSeries;

    public List<FrTrainingSeries> getFrTrainingSeries() {
        return frTrainingSeries;
    }

    public void setFrTrainingSeries(List<FrTrainingSeries> frTrainingSeries) {
        this.frTrainingSeries = frTrainingSeries;
    }

    public Integer getOwnType() {
        return ownType;
    }

    public void setOwnType(Integer ownType) {
        this.ownType = ownType;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsUsing() {
        return isUsing;
    }

    public void setIsUsing(Integer isUsing) {
        this.isUsing = isUsing;
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
        return "FrTrainingSeries{" +
        ", createUser=" + createUser +
        ", type=" + type +
        ", createTime=" + createTime +
        ", name=" + name +
        ", updateUser=" + updateUser +
        ", parentId=" + parentId +
        ", customerCode=" + customerCode +
        ", updateTime=" + updateTime +
        ", isUsing=" + isUsing +
        ", id=" + id +
        "}";
    }
}
