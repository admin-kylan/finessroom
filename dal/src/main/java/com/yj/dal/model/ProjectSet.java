package com.yj.dal.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 项目设置表

 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-27
 */
@TableName("ProjectSet")
public class ProjectSet extends Model<ProjectSet> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户代码 （方便客户导出资料） ids
     */
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    /**
     * ID PK
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /**
     * 项目名称
     */
    private String ProjectName;
    /**
     * 门店ID
     */
    private String ShopId;
    /**
     * 场馆ID
     */
    private String SdadiumId;
    /**
     * 美容项目系列ID
     */
    private String ProjectSeriesId;
    /**
     * 0项目?1产品
     */
    private Boolean IsProduct;
    /**
     * 是否私教
     */
    private Integer ProjectType;


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

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
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

    public String getProjectSeriesId() {
        return ProjectSeriesId;
    }

    public void setProjectSeriesId(String ProjectSeriesId) {
        this.ProjectSeriesId = ProjectSeriesId;
    }

    public Boolean getIsProduct() {
        return IsProduct;
    }

    public void setIsProduct(Boolean IsProduct) {
        this.IsProduct = IsProduct;
    }

    public Integer getProjectType() {
        return ProjectType;
    }

    public void setProjectType(Integer ProjectType) {
        this.ProjectType = ProjectType;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ProjectSet{" +
        ", CustomerCode=" + CustomerCode +
        ", id=" + id +
        ", ProjectName=" + ProjectName +
        ", ShopId=" + ShopId +
        ", SdadiumId=" + SdadiumId +
        ", ProjectSeriesId=" + ProjectSeriesId +
        ", IsProduct=" + IsProduct +
        ", ProjectType=" + ProjectType +
        "}";
    }
}
