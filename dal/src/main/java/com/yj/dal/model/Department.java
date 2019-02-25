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
 * 部门表
 * </p>
 *
 * @author MP自动生成
 * @since 2019-02-25
 */
@TableName("Department")
public class Department extends Model<Department> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户部门表
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /**
     * 客户代码 （方便客户导出资料）ids
     */
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    /**
     * 父级ID
     */
    private String ParentId;
    /**
     * 项目名称
     */
    private String DepartName;
    /**
     * 是否门店
     */
    private Integer IsShop;
    /**
     * 属性
     */
    private Integer Attribute;
    /**
     * 删除标识?1已删除
     */
    private Integer IsDel;
    private String ExtendField1;
    private String ExtendField2;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }

    public String getParentId() {
        return ParentId;
    }

    public void setParentId(String ParentId) {
        this.ParentId = ParentId;
    }

    public String getDepartName() {
        return DepartName;
    }

    public void setDepartName(String DepartName) {
        this.DepartName = DepartName;
    }

    public Integer getIsShop() {
        return IsShop;
    }

    public void setIsShop(Integer IsShop) {
        this.IsShop = IsShop;
    }

    public Integer getAttribute() {
        return Attribute;
    }

    public void setAttribute(Integer Attribute) {
        this.Attribute = Attribute;
    }

    public Integer getIsDel() {
        return IsDel;
    }

    public void setIsDel(Integer IsDel) {
        this.IsDel = IsDel;
    }

    public String getExtendField1() {
        return ExtendField1;
    }

    public void setExtendField1(String ExtendField1) {
        this.ExtendField1 = ExtendField1;
    }

    public String getExtendField2() {
        return ExtendField2;
    }

    public void setExtendField2(String ExtendField2) {
        this.ExtendField2 = ExtendField2;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Department{" +
        ", id=" + id +
        ", CustomerCode=" + CustomerCode +
        ", ParentId=" + ParentId +
        ", DepartName=" + DepartName +
        ", IsShop=" + IsShop +
        ", Attribute=" + Attribute +
        ", IsDel=" + IsDel +
        ", ExtendField1=" + ExtendField1 +
        ", ExtendField2=" + ExtendField2 +
        "}";
    }
}
