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
 * 增购套餐项目表

 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-27
 */
@TableName("ProjectSeriousProject")
public class ProjectSeriousProject extends Model<ProjectSeriousProject> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户代码 （方便客户导出资料）
     */
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    /**
     * 套餐明细ID?关联ProjectSeriousInfo表
     */
    private String ProjectSeriousInfoId;
    /**
     * 门店ID
     */
    private String ShopId;
    /**
     * 场馆ID
     */
    private String SdadiumId;
    /**
     * 项目设置ID
     */
    private String ProjectSetId;
    /**
     * 指定数量
     */
    private Integer ReferCount;
    /**
     * 每天限用数量
     */
    private Integer DayCount;
    /**
     * 创建时间
     */
    private Date CreateTime;
    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;


    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }

    public String getProjectSeriousInfoId() {
        return ProjectSeriousInfoId;
    }

    public void setProjectSeriousInfoId(String ProjectSeriousInfoId) {
        this.ProjectSeriousInfoId = ProjectSeriousInfoId;
    }

    public String getShopId() {
        return ShopId;
    }

    public void setShopId(String ShopId) {
        this.ShopId = ShopId;
    }

    public String getSdadiumId() {
        return SdadiumId;
    }

    public void setSdadiumId(String SdadiumId) {
        this.SdadiumId = SdadiumId;
    }

    public String getProjectSetId() {
        return ProjectSetId;
    }

    public void setProjectSetId(String ProjectSetId) {
        this.ProjectSetId = ProjectSetId;
    }

    public Integer getReferCount() {
        return ReferCount;
    }

    public void setReferCount(Integer ReferCount) {
        this.ReferCount = ReferCount;
    }

    public Integer getDayCount() {
        return DayCount;
    }

    public void setDayCount(Integer DayCount) {
        this.DayCount = DayCount;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
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
        return "ProjectSeriousProject{" +
        ", CustomerCode=" + CustomerCode +
        ", ProjectSeriousInfoId=" + ProjectSeriousInfoId +
        ", ShopId=" + ShopId +
        ", SdadiumId=" + SdadiumId +
        ", ProjectSetId=" + ProjectSetId +
        ", ReferCount=" + ReferCount +
        ", DayCount=" + DayCount +
        ", CreateTime=" + CreateTime +
        ", id=" + id +
        "}";
    }
}
