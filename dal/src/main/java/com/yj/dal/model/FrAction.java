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
@TableName("fr_action")
public class FrAction extends Model<FrAction> {

    private static final long serialVersionUID = 1L;

    @TableField("action_princeple")
    private String actionPrinceple;
    private String name;
    @TableField("is_using")
    private Integer isUsing;
    @TableField("create_user")
    private String createUser;
    private String image;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField("customer_code")
    private String customerCode;
    @TableField("series_id")
    private String seriesId;
    @TableField(value = "own_type")//1或者空|私教 2|团教
    private Integer ownType;
    private String diff;
    @TableField("update_user")
    private String updateUser;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    public Integer getOwnType() {
        return ownType;
    }

    public void setOwnType(Integer ownType) {
        this.ownType = ownType;
    }

    public String getActionPrinceple() {
        return actionPrinceple;
    }

    public void setActionPrinceple(String actionPrinceple) {
        this.actionPrinceple = actionPrinceple;
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
    
    public String getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(String seriesId) {
		this.seriesId = seriesId;
	}

	public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
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
        return "FrAction{" +
        ", actionPrinceple=" + actionPrinceple +
        ", name=" + name +
        ", isUsing=" + isUsing +
        ", createUser=" + createUser +
        ", image=" + image +
        ", id=" + id +
        ", updateTime=" + updateTime +
        ", customerCode=" + customerCode +
        ", seriesId=" + seriesId +
        ", diff=" + diff +
        ", updateUser=" + updateUser +
        ", createTime=" + createTime +
        "}";
    }
}
