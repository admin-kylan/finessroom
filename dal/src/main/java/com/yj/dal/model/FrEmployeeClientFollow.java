package com.yj.dal.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 现有会员跟进记录
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-28
 */
@TableName("fr_employee_client_follow")
public class FrEmployeeClientFollow extends Model<FrEmployeeClientFollow> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户id
     */
    @TableField("client_id")
    private String clientId;
    /**
     * 员工id
     */
    @TableField("personal_id")
    private String personalId;
    /**
     * 跟进日期
     */
    @TableField("follow_time")
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date followTime;
    /**
     * 跟进类型
     */
    @TableField("follow_type")
    private String followType;
    /**
     * 跟进内容
     */
    @TableField("follow_content")
    private String followContent;
    /**
     * 下次跟进时间
     */
    @TableField("next_follow_time")
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date nextFollowTime;
    /**
     * 计划跟进时间
     */
    @TableField("plan_follow_time")
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date planFollowTime;
    /**
     * 计划来访时间
     */
    @TableField("plan_visit_time")
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date planVisitTime;
    /**
     * 计划购买时间
     */
    @TableField("plan_purchase_time")
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date planPurchaseTime;
    /**
     * 跟进记录
     */
    @TableField("follow_mark")
    private String followMark;
    /**
     * 上级主管id
     */
    @TableField("supper_managment_id")
    private String supperManagmentId;
    /**
     * 审核时间
     */
    @TableField("check_time")
    private Date checkTime;
    /**
     * 跟进调整
     */
    @TableField("follow_adjust_time")
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date followAdjustTime;
    /**
     * 处理建议
     */
    @TableField("handle_advice")
    private String handleAdvice;
    /**
     * 角色
     */
    @TableField("role_info_id")
    private String roleInfoId;
    /**
     * 门店id
     */
    @TableField("shop_id")
    private String shopId;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    @TableField(value = "update_user_name", fill = FieldFill.INSERT_UPDATE)
    private String updateUserName;
    @TableField(value = "update_user_id", fill = FieldFill.INSERT_UPDATE)
    private String updateUserId;
    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    private String createUserId;
    private String flag;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    @TableField(value = "create_user_name", fill = FieldFill.INSERT)
    private String createUserName;
    @TableField("is_using")
    private Boolean isUsing;
    private Integer type;
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    @TableField("actual_visit_time")
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date actualVisitTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Date followTime) {
        this.followTime = followTime;
    }

    public String getFollowType() {
        return followType;
    }

    public void setFollowType(String followType) {
        this.followType = followType;
    }

    public String getFollowContent() {
        return followContent;
    }

    public void setFollowContent(String followContent) {
        this.followContent = followContent;
    }

    public Date getNextFollowTime() {
        return nextFollowTime;
    }

    public void setNextFollowTime(Date nextFollowTime) {
        this.nextFollowTime = nextFollowTime;
    }

    public Date getPlanFollowTime() {
        return planFollowTime;
    }

    public void setPlanFollowTime(Date planFollowTime) {
        this.planFollowTime = planFollowTime;
    }

    public Date getPlanVisitTime() {
        return planVisitTime;
    }

    public void setPlanVisitTime(Date planVisitTime) {
        this.planVisitTime = planVisitTime;
    }

    public Date getPlanPurchaseTime() {
        return planPurchaseTime;
    }

    public void setPlanPurchaseTime(Date planPurchaseTime) {
        this.planPurchaseTime = planPurchaseTime;
    }

    public String getFollowMark() {
        return followMark;
    }

    public void setFollowMark(String followMark) {
        this.followMark = followMark;
    }

    public String getSupperManagmentId() {
        return supperManagmentId;
    }

    public void setSupperManagmentId(String supperManagmentId) {
        this.supperManagmentId = supperManagmentId;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Date getFollowAdjustTime() {
        return followAdjustTime;
    }

    public void setFollowAdjustTime(Date followAdjustTime) {
        this.followAdjustTime = followAdjustTime;
    }

    public String getHandleAdvice() {
        return handleAdvice;
    }

    public void setHandleAdvice(String handleAdvice) {
        this.handleAdvice = handleAdvice;
    }

    public String getRoleInfoId() {
        return roleInfoId;
    }

    public void setRoleInfoId(String roleInfoId) {
        this.roleInfoId = roleInfoId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Boolean getUsing() {
        return isUsing;
    }

    public void setUsing(Boolean isUsing) {
        this.isUsing = isUsing;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }

    public Date getActualVisitTime() {
        return actualVisitTime;
    }

    public void setActualVisitTime(Date actualVisitTime) {
        this.actualVisitTime = actualVisitTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrEmployeeClientFollow{" +
        ", clientId=" + clientId +
        ", personalId=" + personalId +
        ", followTime=" + followTime +
        ", followType=" + followType +
        ", followContent=" + followContent +
        ", nextFollowTime=" + nextFollowTime +
        ", planFollowTime=" + planFollowTime +
        ", planVisitTime=" + planVisitTime +
        ", planPurchaseTime=" + planPurchaseTime +
        ", followMark=" + followMark +
        ", supperManagmentId=" + supperManagmentId +
        ", checkTime=" + checkTime +
        ", followAdjustTime=" + followAdjustTime +
        ", handleAdvice=" + handleAdvice +
        ", roleInfoId=" + roleInfoId +
        ", shopId=" + shopId +
        ", createTime=" + createTime +
        ", updateUserName=" + updateUserName +
        ", updateUserId=" + updateUserId +
        ", createUserId=" + createUserId +
        ", flag=" + flag +
        ", id=" + id +
        ", createUserName=" + createUserName +
        ", isUsing=" + isUsing +
        ", type=" + type +
        ", CustomerCode=" + CustomerCode +
        ", actualVisitTime=" + actualVisitTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
