package com.yj.dal.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 客户训练计划的课程详情保存
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-26
 */
@TableName("fr_plan_class")
public class FrPlanClass extends Model<FrPlanClass> {

    private static final long serialVersionUID = 1L;

    /**
     * 动作名称
     */
    private String name;
    /**
     * 动作难度
     */
    private String diff;
    /**
     * 动作图片
     */
    private String image;
    /**
     * 时间/分
     */
    private Integer time;
    /**
     * 强度
     */
    private String strength;
    /**
     * 次数
     */
    private Integer count;
    /**
     * 备注
     */
    private String remark;
    /**
     * 内容类型
     */
    @TableField("action_type")
    private String sname;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 是否启用(0：不启用 1：启用）默认1
     */
    @TableField("is_using")
    private Boolean isUsing;
    //关联表id
    @TableField(exist = false)
    private String tid;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public Boolean getUsing() {
        return isUsing;
    }

    public void setUsing(Boolean using) {
        isUsing = using;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
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


}
