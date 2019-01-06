package com.yj.dal.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 课程计划
 * </p>
 *
 * @author MP自动生成
 * @since 2019-01-05
 */
@TableName("fr_education_plan")
public class FrEducationPlan extends Model<FrEducationPlan> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 课程ID
     */
    @TableField("education_id")
    private String educationId;
    /**
     * 动作类型
     */
    @TableField("action_type")
    private String actionType;
    /**
     * 动作名称
     */
    @TableField("action_name")
    private String actionName;
    /**
     * 动作时间（分）
     */
    @TableField("action_period")
    private Integer actionPeriod;
    /**
     * 动作强度 难易程度按数字划分 1,2,3,4,5等
     */
    @TableField("action_strength")
    private Integer actionStrength;
    /**
     * 次数
     */
    private Integer num;
    /**
     * 备注
     */
    private String remarks;
    @TableField("create_date")
    private Date createDate;
    @TableField("update_date")
    private Date updateDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEducationId() {
        return educationId;
    }

    public void setEducationId(String educationId) {
        this.educationId = educationId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public Integer getActionPeriod() {
        return actionPeriod;
    }

    public void setActionPeriod(Integer actionPeriod) {
        this.actionPeriod = actionPeriod;
    }

    public Integer getActionStrength() {
        return actionStrength;
    }

    public void setActionStrength(Integer actionStrength) {
        this.actionStrength = actionStrength;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrEducationPlan{" +
        ", id=" + id +
        ", educationId=" + educationId +
        ", actionType=" + actionType +
        ", actionName=" + actionName +
        ", actionPeriod=" + actionPeriod +
        ", actionStrength=" + actionStrength +
        ", num=" + num +
        ", remarks=" + remarks +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        "}";
    }
}
