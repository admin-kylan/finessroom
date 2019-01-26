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
 * 教学表，团体教学，私人教学
 * </p>
 *
 * @author MP自动生成
 * @since 2019-01-05
 */
@TableName("fr_education")
public class FrEducation extends Model<FrEducation> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 课程时间
     */
    @TableField("execute_date_plan")
    private Date executeDatePlan;
    /**
     * 课程开始时间
     */
    @TableField("begin_date_plan")
    private Date beginDatePlan;
    /**
     * 课程结束时间
     */
    @TableField("end_date_plan")
    private Date endDatePlan;
    /**
     * 门店id
     */
    @TableField("shop_id")
    private String shopId;
    /**
     * 门店名称
     */
    @TableField("shop_name")
    private String shopName;
    /**
     * 课程Id
     */
    @TableField("course_id")
    private String courseId;
    /**
     * 课程名称
     */
    @TableField("course_name")
    private String courseName;
    /**
     * 课程类型 0/团课 1 /私教
     */
    private Integer type;
    /**
     * 教练id
     */
    @TableField("coach_id")
    private String coachId;
    /**
     * 教练名称
     */
    @TableField("coach_name")
    private String coachName;
    /**
     * 教室Id
     */
    @TableField("room_id")
    private String roomId;
    /**
     * 教室名字
     */
    @TableField("room_name")
    private String roomName;
    /**
     * 预约总人数
     */
    @TableField("reserve_total_num")
    private Integer reserveTotalNum;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 开课状态 1/0/2/3 已开始，未开始，已结束, 已取消
     */
    private Integer status;
    /**
     * 助教名字
     */
    @TableField("assistant_name")
    private String assistantName;
    /**
     * 助教Id
     */
    @TableField("assistant_id")
    private String assistantId;
    /**
     * 是否可用
     */
    @TableField("is_use")
    private Boolean isUse;
    /**
     * 场馆id
     */
    @TableField("sdaduim_id")
    private String sdaduimId;
    /**
     * 场馆名字
     */
    @TableField("sdaduim_name")
    private String sdaduimName;
    /**
     * 私教预约类型，1/2 一对一 /一对多 预约
     */
    @TableField("reserve_type")
    private Integer reserveType;

    /**
     * 课时消费数量
     */
    @TableField("class_sales_num")
    private Integer classSalesNum;

    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    private String createUserId;
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    @TableField(value = "create_user_name", fill = FieldFill.INSERT)
    private String createUserName;
    @TableField(value = "update_user_id", fill = FieldFill.INSERT_UPDATE)
    private String updateUserId;
    @TableField(value = "update_user_name", fill = FieldFill.INSERT_UPDATE)
    private String updateUserName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getExecuteDatePlan() {
        return executeDatePlan;
    }

    public void setExecuteDatePlan(Date executeDatePlan) {
        this.executeDatePlan = executeDatePlan;
    }

    public Date getBeginDatePlan() {
        return beginDatePlan;
    }

    public void setBeginDatePlan(Date beginDatePlan) {
        this.beginDatePlan = beginDatePlan;
    }

    public Date getEndDatePlan() {
        return endDatePlan;
    }

    public void setEndDatePlan(Date endDatePlan) {
        this.endDatePlan = endDatePlan;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getReserveTotalNum() {
        return reserveTotalNum;
    }

    public void setReserveTotalNum(Integer reserveTotalNum) {
        this.reserveTotalNum = reserveTotalNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAssistantName() {
        return assistantName;
    }

    public void setAssistantName(String assistantName) {
        this.assistantName = assistantName;
    }

    public String getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(String assistantId) {
        this.assistantId = assistantId;
    }

    public Boolean getUse() {
        return isUse;
    }

    public void setUse(Boolean isUse) {
        this.isUse = isUse;
    }

    public String getSdaduimId() {
        return sdaduimId;
    }

    public void setSdaduimId(String sdaduimId) {
        this.sdaduimId = sdaduimId;
    }

    public String getSdaduimName() {
        return sdaduimName;
    }

    public void setSdaduimName(String sdaduimName) {
        this.sdaduimName = sdaduimName;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Integer getReserveType() {
        return reserveType;
    }

    public void setReserveType(Integer reserveType) {
        this.reserveType = reserveType;
    }

    public Integer getClassSalesNum() {
        return classSalesNum;
    }

    public void setClassSalesNum(Integer classSalesNum) {
        this.classSalesNum = classSalesNum;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrEducation{" +
        ", id=" + id +
        ", executeDatePlan=" + executeDatePlan +
        ", beginDatePlan=" + beginDatePlan +
        ", endDatePlan=" + endDatePlan +
        ", shopId=" + shopId +
        ", shopName=" + shopName +
        ", courseId=" + courseId +
        ", courseName=" + courseName +
        ", type=" + type +
        ", coachId=" + coachId +
        ", coachName=" + coachName +
        ", roomId=" + roomId +
        ", roomName=" + roomName +
        ", reserveTotalNum=" + reserveTotalNum +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", status=" + status +
        ", assistantName=" + assistantName +
        ", assistantId=" + assistantId +
        ", isUse=" + isUse +
        ", sdaduimId=" + sdaduimId +
        ", sdaduimName=" + sdaduimName +
        ", createUserId=" + createUserId +
        ", CustomerCode=" + CustomerCode +
        ", createUserName=" + createUserName +
        ", updateUserId=" + updateUserId +
        ", updateUserName=" + updateUserName +
        ", classSalesNum=" + classSalesNum +
        "}";
    }
}
