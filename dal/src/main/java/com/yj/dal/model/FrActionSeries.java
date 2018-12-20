package com.yj.dal.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
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
@TableName("fr_action_series")
public class FrActionSeries extends Model<FrActionSeries> {

    private static final long serialVersionUID = 1L;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField("update_user")
    private Long updateUser;
    private String name;
    @TableField("is_using")
    private Integer isUsing;
    @TableField(value = "own_type")//1或者空|私教 2|团教
    private Integer ownType;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    @TableField("customer_code")
    private String customerCode;
    @TableField("create_user")
    private Long createUser;
    private String note;

    public Integer getOwnType() {
        return ownType;
    }

    public void setOwnType(Integer ownType) {
        this.ownType = ownType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
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
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrActionSeries{" +
        ", createTime=" + createTime +
        ", updateUser=" + updateUser +
        ", name=" + name +
        ", isUsing=" + isUsing +
        ", updateTime=" + updateTime +
        ", id=" + id +
        ", customerCode=" + customerCode +
        ", createUser=" + createUser +
        ", note=" + note +
        "}";
    }
}
