package com.yj.dal.dto;

import com.baomidou.mybatisplus.annotations.TableField;

/**
 * 教练vo类
 */
public class CoachDTO {

    private String firstName;//默认角色名

    private String rolRoleName;//自定义角色名

    private String phone;//教练手机号码

    private String roleId;//角色ID

    private String personalId;//人员ID

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getRolRoleName() {
        return rolRoleName;
    }

    public void setRolRoleName(String rolRoleName) {
        this.rolRoleName = rolRoleName;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Override
    public String toString() {
        return "CoachDTO{" +
                "firstName='" + firstName + '\'' +
                ", rolRoleName='" + rolRoleName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }


}
