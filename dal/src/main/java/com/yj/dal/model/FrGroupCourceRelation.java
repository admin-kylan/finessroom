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
 * @since 2018-12-09
 */
@TableName("fr_group_cource_relation")
public class FrGroupCourceRelation extends Model<FrGroupCourceRelation> {

    private static final long serialVersionUID = 1L;

    @TableField("private_cource_id")
    private String privateCourceId;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField("update_user")
    private String updateUser;
    @TableField("training_series_id")
    private String trainingSeriesId;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField("customer_code")
    private String customerCode;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    @TableField("create_user")
    private String createUser;
    @TableField("is_using")
    private Integer isUsing;


    public String getPrivateCourceId() {
        return privateCourceId;
    }

    public void setPrivateCourceId(String privateCourceId) {
        this.privateCourceId = privateCourceId;
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

    public String getTrainingSeriesId() {
        return trainingSeriesId;
    }

    public void setTrainingSeriesId(String trainingSeriesId) {
        this.trainingSeriesId = trainingSeriesId;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrGroupCourceRelation{" +
        ", privateCourceId=" + privateCourceId +
        ", createTime=" + createTime +
        ", updateUser=" + updateUser +
        ", trainingSeriesId=" + trainingSeriesId +
        ", updateTime=" + updateTime +
        ", customerCode=" + customerCode +
        ", id=" + id +
        ", createUser=" + createUser +
        ", isUsing=" + isUsing +
        "}";
    }
}
