package com.yj.dal.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 课程结算，会员卡设置表
 * </p>
 *
 * @author MP自动生成
 * @since 2019-01-06
 */
@TableName("fr_education_card_object")
public class FrEducationCardObject extends Model<FrEducationCardObject> {

    private static final long serialVersionUID = 1L;

    /**
     * 课程结算设置表
     */
    @TableField("education_config_id")
    private String educationConfigId;
    /**
     * 门店ID
     */
    @TableField("shop_id")
    private String shopId;
    /**
     * 场馆ID
     */
    @TableField("sdadium_id")
    private String sdadiumId;
    /**
     * 项目Id
     */
    @TableField("project_id")
    private String projectId;
    /**
     * 项目售价 元
     */
    private Double price;
    /**
     * 门店名字
     */
    @TableField("shop_name")
    private String shopName;
    /**
     * 场馆名字
     */
    @TableField("sdadium_name")
    private String sdadiumName;
    /**
     * 团教课程名字
     */
    @TableField("project_name")
    private String projectName;
    /**
     * 课程类型 0/团课 1 /私教
     */
    @TableField("education_type")
    private Integer educationType;
    @TableId(value = "id", type = IdType.UUID)
    private String id;


    public String getEducationConfigId() {
        return educationConfigId;
    }

    public void setEducationConfigId(String educationConfigId) {
        this.educationConfigId = educationConfigId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getSdadiumId() {
        return sdadiumId;
    }

    public void setSdadiumId(String sdadiumId) {
        this.sdadiumId = sdadiumId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getSdadiumName() {
        return sdadiumName;
    }

    public void setSdadiumName(String sdadiumName) {
        this.sdadiumName = sdadiumName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getEducationType() {
        return educationType;
    }

    public void setEducationType(Integer educationType) {
        this.educationType = educationType;
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
        return "FrEducationCardObject{" +
        ", educationConfigId=" + educationConfigId +
        ", shopId=" + shopId +
        ", sdadiumId=" + sdadiumId +
        ", projectId=" + projectId +
        ", price=" + price +
        ", shopName=" + shopName +
        ", sdadiumName=" + sdadiumName +
        ", projectName=" + projectName +
        ", educationType=" + educationType +
        ", id=" + id +
        "}";
    }
}
