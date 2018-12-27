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
 * 项目单位表

 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-27
 */
@TableName("ProjectUnit")
public class ProjectUnit extends Model<ProjectUnit> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户代码 （方便客户导出资料）
     */
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /**
     * 门店ID
     */
    private String ShopId;
    /**
     * 场馆ID
     */
    private String SdadiumId;
    /**
     * 项目单位
     */
    private String UnitName;


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

    public String getUnitName() {
        return UnitName;
    }

    public void setUnitName(String UnitName) {
        this.UnitName = UnitName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ProjectUnit{" +
        ", CustomerCode=" + CustomerCode +
        ", id=" + id +
        ", ShopId=" + ShopId +
        ", SdadiumId=" + SdadiumId +
        ", UnitName=" + UnitName +
        "}";
    }
}
