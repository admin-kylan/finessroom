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
 * @since 2018-11-15
 */
@TableName("fr_traning_class")
public class FrTraningClass extends Model<FrTraningClass> {

    private static final long serialVersionUID = 1L;

    private Integer count;
    @TableId(value = "id")
    private String id;
    private Integer time;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField("traning_series_id")
    private String traningSeriesId;
    private String strength;
    @TableField("customer_code")
    private String customerCode;
    @TableField("create_user")
    private String createUser;
    @TableField("is_using")
    private Integer isUsing;
    @TableField(value = "own_type")//1或者空|私教 2|团教
    private Integer ownType;
    @TableField(value = "traning_time")//1或者空|私教 2|团教
    private String traningTime;
    private Integer type;
    @TableField("action_id")
    private String actionId;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    private String remark;
    @TableField("update_user")
    private String updateUser;


    public Integer getOwnType() {
        return ownType;
    }

    public void setOwnType(Integer ownType) {
        this.ownType = ownType;
    }

    public String getTraningTime() {
        return traningTime;
    }

    public void setTraningTime(String traningTime) {
        this.traningTime = traningTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTraningSeriesId() {
		return traningSeriesId;
	}

	public void setTraningSeriesId(String traningSeriesId) {
		this.traningSeriesId = traningSeriesId;
	}

	public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    
    public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrTraningClass{" +
        ", count=" + count +
        ", id=" + id +
        ", time=" + time +
        ", updateTime=" + updateTime +
        ", traningSeriesId=" + traningSeriesId +
        ", strength=" + strength +
        ", customerCode=" + customerCode +
        ", createUser=" + createUser +
        ", isUsing=" + isUsing +
        ", type=" + type +
        ", actionId=" + actionId +
        ", createTime=" + createTime +
        ", remark=" + remark +
        ", updateUser=" + updateUser +
        "}";
    }
}
