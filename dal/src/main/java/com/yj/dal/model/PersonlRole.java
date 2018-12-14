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
 * 人员担任角色表
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-23
 */
@TableName("PersonlRole")
public class PersonlRole extends Model<PersonlRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID 关联RoleInfo角色
     */
    private String RoleInfoId;
    /**
     * PersonnelInfo?表的ID
     */
    private String PersonnelInfoId;
    /**
     * 可以使用门店ID
     */
    private String ShopId;
    /**
     * 是否主要
     */
    private Boolean IsMain;
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    private Boolean IsDel;
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;


    public String getRoleInfoId() {
        return RoleInfoId;
    }

    public void setRoleInfoId(String RoleInfoId) {
        this.RoleInfoId = RoleInfoId;
    }

    public String getPersonnelInfoId() {
        return PersonnelInfoId;
    }

    public void setPersonnelInfoId(String PersonnelInfoId) {
        this.PersonnelInfoId = PersonnelInfoId;
    }

    public String getShopId() {
        return ShopId;
    }

    public void setShopId(String ShopId) {
        this.ShopId = ShopId;
    }

    public Boolean getIsMain() {
        return IsMain;
    }

    public void setIsMain(Boolean IsMain) {
        this.IsMain = IsMain;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIsDel() {
        return IsDel;
    }

    public void setIsDel(Boolean IsDel) {
        this.IsDel = IsDel;
    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "PersonlRole{" +
        ", RoleInfoId=" + RoleInfoId +
        ", PersonnelInfoId=" + PersonnelInfoId +
        ", ShopId=" + ShopId +
        ", IsMain=" + IsMain +
        ", id=" + id +
        ", IsDel=" + IsDel +
        ", CustomerCode=" + CustomerCode +
        "}";
    }
}
