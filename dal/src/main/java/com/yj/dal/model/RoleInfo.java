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
 * 角色表
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-23
 */
@TableName("RoleInfo")
public class RoleInfo extends Model<RoleInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户代码
     */
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    /**
     * 角色表
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /**
     * 角色名称
     */
    private String FirstName;
    /**
     * 角色类型：0-默认，1-可分配、可认领角色，。。。
     */
    private String RolRoleName;
    /**
     * 上级ID
     */
    private String ParentId;
    /**
     * 角色类型：0-默认，1-可分配、可认领角色，。。。
     */
    private Integer RoleType;
    /**
     * 是否为教练、教师
     */
    private Integer ISTeach;
    /**
     * 是否服务角色
     */
    private Boolean ServiceRole;
    /**
     * 行业类别
     */
    private String BusinessType;
    /**
     * 人员类别  1销售人员  2服务人员  3教练、技师  4助教  
     */
    private Integer UserType;
    /**
     * 是否删除
     */
    private Boolean IsDel;
    private Boolean CanDel;
    private Integer Sort;


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

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getRolRoleName() {
        return RolRoleName;
    }

    public void setRolRoleName(String RolRoleName) {
        this.RolRoleName = RolRoleName;
    }

    public String getParentId() {
        return ParentId;
    }

    public void setParentId(String ParentId) {
        this.ParentId = ParentId;
    }

    public Integer getRoleType() {
        return RoleType;
    }

    public void setRoleType(Integer RoleType) {
        this.RoleType = RoleType;
    }

    public Integer getISTeach() {
        return ISTeach;
    }

    public void setISTeach(Integer ISTeach) {
        this.ISTeach = ISTeach;
    }

    public Boolean getServiceRole() {
        return ServiceRole;
    }

    public void setServiceRole(Boolean ServiceRole) {
        this.ServiceRole = ServiceRole;
    }

    public String getBusinessType() {
        return BusinessType;
    }

    public void setBusinessType(String BusinessType) {
        this.BusinessType = BusinessType;
    }

    public Integer getUserType() {
        return UserType;
    }

    public void setUserType(Integer UserType) {
        this.UserType = UserType;
    }

    public Boolean getIsDel() {
        return IsDel;
    }

    public void setIsDel(Boolean IsDel) {
        this.IsDel = IsDel;
    }

    public Boolean getCanDel() {
        return CanDel;
    }

    public void setCanDel(Boolean CanDel) {
        this.CanDel = CanDel;
    }

    public Integer getSort() {
        return Sort;
    }

    public void setSort(Integer Sort) {
        this.Sort = Sort;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "RoleInfo{" +
        ", CustomerCode=" + CustomerCode +
        ", id=" + id +
        ", FirstName=" + FirstName +
        ", RolRoleName=" + RolRoleName +
        ", ParentId=" + ParentId +
        ", RoleType=" + RoleType +
        ", ISTeach=" + ISTeach +
        ", ServiceRole=" + ServiceRole +
        ", BusinessType=" + BusinessType +
        ", UserType=" + UserType +
        ", IsDel=" + IsDel +
        ", CanDel=" + CanDel +
        ", Sort=" + Sort +
        "}";
    }
}
