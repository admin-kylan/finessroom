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
 * @since 2018-11-24
 */
@TableName("fr_private_cource_relation")
public class FrPrivateCourceRelation extends Model<FrPrivateCourceRelation> {

    private static final long serialVersionUID = 1L;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField("training_series_id")
    private String trainingSeriesId;
    @TableField("customer_code")
    private String customerCode;
    @TableField("is_using")
    private Integer isUsing;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    @TableField("create_user")
    private String createUser;
    @TableField("private_cource_id")
    private String privateCourceId;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField("update_user")
    private String updateUser;


    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTrainingSeriesId() {
        return trainingSeriesId;
    }

    public void setTrainingSeriesId(String trainingSeriesId) {
        this.trainingSeriesId = trainingSeriesId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrPrivateCourceRelation{" +
        ", updateTime=" + updateTime +
        ", trainingSeriesId=" + trainingSeriesId +
        ", customerCode=" + customerCode +
        ", isUsing=" + isUsing +
        ", id=" + id +
        ", createUser=" + createUser +
        ", privateCourceId=" + privateCourceId +
        ", createTime=" + createTime +
        ", updateUser=" + updateUser +
        "}";
    }
}
