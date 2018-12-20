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
 * 训练计划关联动作(课程详情)
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-20
 */
@TableName("fr_training_action")
public class FrTrainingAction extends Model<FrTrainingAction> {

    private static final long serialVersionUID = 1L;

    /**
     * 动作ID
     */
    @TableField("action_id")
    private String actionId;
    /**
     * 训练计划ID
     */
    @TableField("training_id")
    private String trainingId;
    /**
     * 是否启用(0：不启用 1：启用）默认1
     */
    @TableField("is_using")
    private Boolean isUsing;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableId(value = "id", type = IdType.UUID)
    private String id;


    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(String trainingId) {
        this.trainingId = trainingId;
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
        return "FrTrainingAction{" +
        ", actionId=" + actionId +
        ", trainingId=" + trainingId +
        ", isUsing=" + isUsing +
        ", createTime=" + createTime +
        ", id=" + id +
        "}";
    }
}
