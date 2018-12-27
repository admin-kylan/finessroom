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
 * 项目停止或可以使用表

 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-27
 */
@TableName("ProjectTimeUseAndStop")
public class ProjectTimeUseAndStop extends Model<ProjectTimeUseAndStop> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户代码 （方便客户导出资料）ids
     */
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    /**
     * ID PK
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /**
     * 项目详细ID
     */
    private String ProjectInfoId;
    /**
     * 禁用/可用
     */
    private String UseLimit;
    /**
     * 农历或阳历
     */
    private String UseDate;
    /**
     * 开始日期
     */
    private Date UseDayStar;
    /**
     * 结束日期
     */
    private Date UseDayEnd;
    /**
     * 开始时间
     */
    private String UseTimeStar;
    /**
     * 结束时间
     */
    private String UseTimeEnd;
    /**
     * 0-不限时?1-限时
     */
    private Boolean UseSelect;


    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectInfoId() {
        return ProjectInfoId;
    }

    public void setProjectInfoId(String ProjectInfoId) {
        this.ProjectInfoId = ProjectInfoId;
    }

    public String getUseLimit() {
        return UseLimit;
    }

    public void setUseLimit(String UseLimit) {
        this.UseLimit = UseLimit;
    }

    public String getUseDate() {
        return UseDate;
    }

    public void setUseDate(String UseDate) {
        this.UseDate = UseDate;
    }

    public Date getUseDayStar() {
        return UseDayStar;
    }

    public void setUseDayStar(Date UseDayStar) {
        this.UseDayStar = UseDayStar;
    }

    public Date getUseDayEnd() {
        return UseDayEnd;
    }

    public void setUseDayEnd(Date UseDayEnd) {
        this.UseDayEnd = UseDayEnd;
    }

    public String getUseTimeStar() {
        return UseTimeStar;
    }

    public void setUseTimeStar(String UseTimeStar) {
        this.UseTimeStar = UseTimeStar;
    }

    public String getUseTimeEnd() {
        return UseTimeEnd;
    }

    public void setUseTimeEnd(String UseTimeEnd) {
        this.UseTimeEnd = UseTimeEnd;
    }

    public Boolean getUseSelect() {
        return UseSelect;
    }

    public void setUseSelect(Boolean UseSelect) {
        this.UseSelect = UseSelect;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ProjectTimeUseAndStop{" +
        ", CustomerCode=" + CustomerCode +
        ", id=" + id +
        ", ProjectInfoId=" + ProjectInfoId +
        ", UseLimit=" + UseLimit +
        ", UseDate=" + UseDate +
        ", UseDayStar=" + UseDayStar +
        ", UseDayEnd=" + UseDayEnd +
        ", UseTimeStar=" + UseTimeStar +
        ", UseTimeEnd=" + UseTimeEnd +
        ", UseSelect=" + UseSelect +
        "}";
    }
}
