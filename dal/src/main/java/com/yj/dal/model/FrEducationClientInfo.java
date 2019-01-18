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
 * 课程用户表，用户预约课程
 * </p>
 *
 * @author MP自动生成
 * @since 2019-01-12
 */
@TableName("fr_education_client_info")
public class FrEducationClientInfo extends Model<FrEducationClientInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 约课表Id
     */
    @TableField("education_id")
    private String educationId;
    /**
     * 会员Id
     */
    @TableField("member_id")
    private String memberId;
    /**
     * 会员姓名
     */
    @TableField("member_name")
    private String memberName;
    /**
     * 客户类型名字 例如现有客户
     */
    @TableField("member_type")
    private String memberType;
    /**
     * 会员卡号码
     */
    @TableField("member_card_no")
    private String memberCardNo;
    /**
     * 会员卡Id
     */
    @TableField("member_card_id")
    private String memberCardId;
    /**
     * 电话号码
     */
    private String mobile;
    /**
     * 预约人id
     */
    @TableField("reserve_client_id")
    private String reserveClientId;
    /**
     * 预约人名字
     */
    @TableField("reserve_client_name")
    private String reserveClientName;
    /**
     * 预约时间
     */
    @TableField("reserve_date")
    private Date reserveDate;
    /**
     * 座位表对应的Id
     */
    @TableField("seat_id")
    private String seatId;
    /**
     * 座位名字
     */
    @TableField("seat_name")
    private String seatName;
    /**
     * 结算方式
     */
    @TableField("settle_type")
    private String settleType;
    /**
     * 扣费（余额）
     */
    @TableField("deduction_balance")
    private String deductionBalance;
    /**
     * 预约状态 1/已预约 0/已取消 2/待确认
     */
    @TableField("reserve_status")
    private Integer reserveStatus;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 操作员
     */
    @TableField(value = "create_user_name", fill = FieldFill.INSERT)
    private String createUserName;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 性别
     */
    private String sex;
    /**
     * 是否可用
     */
    @TableField("is_use")
    private Boolean isUse;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    private String createUserId;
    @TableField(value = "update_user_id", fill = FieldFill.INSERT_UPDATE)
    private String updateUserId;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    @TableField(value = "update_user_name", fill = FieldFill.INSERT_UPDATE)
    private String updateUserName;


    public String getEducationId() {
        return educationId;
    }

    public void setEducationId(String educationId) {
        this.educationId = educationId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getReserveClientId() {
        return reserveClientId;
    }

    public void setReserveClientId(String reserveClientId) {
        this.reserveClientId = reserveClientId;
    }

    public String getReserveClientName() {
        return reserveClientName;
    }

    public void setReserveClientName(String reserveClientName) {
        this.reserveClientName = reserveClientName;
    }

    public Date getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(Date reserveDate) {
        this.reserveDate = reserveDate;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public String getSettleType() {
        return settleType;
    }

    public void setSettleType(String settleType) {
        this.settleType = settleType;
    }

    public String getDeductionBalance() {
        return deductionBalance;
    }

    public void setDeductionBalance(String deductionBalance) {
        this.deductionBalance = deductionBalance;
    }

    public Integer getReserveStatus() {
        return reserveStatus;
    }

    public void setReserveStatus(Integer reserveStatus) {
        this.reserveStatus = reserveStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Boolean getUse() {
        return isUse;
    }

    public void setUse(Boolean isUse) {
        this.isUse = isUse;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public String getMemberCardNo() {
        return memberCardNo;
    }

    public void setMemberCardNo(String memberCardNo) {
        this.memberCardNo = memberCardNo;
    }

    public String getMemberCardId() {
        return memberCardId;
    }

    public void setMemberCardId(String memberCardId) {
        this.memberCardId = memberCardId;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrEducationClientInfo{" +
        ", educationId=" + educationId +
        ", memberId=" + memberId +
        ", memberName=" + memberName +
        ", memberType=" + memberType +
        ", mobile=" + mobile +
        ", reserveClientId=" + reserveClientId +
        ", reserveClientName=" + reserveClientName +
        ", reserveDate=" + reserveDate +
        ", seatId=" + seatId +
        ", seatName=" + seatName +
        ", settleType=" + settleType +
        ", deductionBalance=" + deductionBalance +
        ", reserveStatus=" + reserveStatus +
        ", remarks=" + remarks +
        ", createUserName=" + createUserName +
        ", createTime=" + createTime +
        ", sex=" + sex +
        ", isUse=" + isUse +
        ", updateTime=" + updateTime +
        ", createUserId=" + createUserId +
        ", updateUserId=" + updateUserId +
        ", id=" + id +
        ", updateUserName=" + updateUserName +
        "}";
    }
}
