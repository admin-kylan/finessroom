package com.yj.dal.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 课程开放预约时间设置
 * </p>
 *
 * @author MP自动生成
 * @since 2019-01-05
 */
@TableName("fr_education_config")
public class FrEducationConfig extends Model<FrEducationConfig> {

    private static final long serialVersionUID = 1L;

    /**
     * 教学id
     */
    @TableField("education_id")
    private String educationId;
    /**
     * 预约时间
     */
    @TableField("reserve_time")
    private Date reserveTime;
    /**
     * 预约设置修改仅限在预约时间发生前多久内修改
     */
    @TableField("setting_time")
    private Integer settingTime;
    /**
     * 未经允许是否可以上课
     */
    @TableField("is_start_class")
    private Boolean isStartClass;
    /**
     * 前台预约是否允许选择教练
     */
    @TableField("is_choose_coach")
    private Boolean isChooseCoach;
    /**
     * 团会员APP预约是否需要确认
     */
    @TableField("is_reserve_confirm")
    private Boolean isReserveConfirm;
    /**
     * 团课开始之前多少分钟，团操老师可刷团会员APP二维码入场
     */
    @TableField("admission_time")
    private Integer admissionTime;
    /**
     * 团会员预约与扣费【pc端不能预约】
     */
    @TableField("is_online_reserve")
    private Boolean isOnlineReserve;
    /**
     * 每节课多少元
     */
    @TableField("lesson_price")
    private Double lessonPrice;
    /**
     * 是否限制约课
     */
    @TableField("is_limit_reserve")
    private Boolean isLimitReserve;
    /**
     * 是否会员卡结算
     */
    @TableField("is_card_settle")
    private Boolean isCardSettle;
    /**
     * 课程结算
     */
    @TableField("is_course_settle")
    private Boolean isCourseSettle;
    @TableId(value = "id", type = IdType.UUID)
    private String id;


    public String getEducationId() {
        return educationId;
    }

    public void setEducationId(String educationId) {
        this.educationId = educationId;
    }

    public Date getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(Date reserveTime) {
        this.reserveTime = reserveTime;
    }

    public Integer getSettingTime() {
        return settingTime;
    }

    public void setSettingTime(Integer settingTime) {
        this.settingTime = settingTime;
    }

    public Boolean getStartClass() {
        return isStartClass;
    }

    public void setStartClass(Boolean isStartClass) {
        this.isStartClass = isStartClass;
    }

    public Boolean getChooseCoach() {
        return isChooseCoach;
    }

    public void setChooseCoach(Boolean isChooseCoach) {
        this.isChooseCoach = isChooseCoach;
    }

    public Boolean getReserveConfirm() {
        return isReserveConfirm;
    }

    public void setReserveConfirm(Boolean isReserveConfirm) {
        this.isReserveConfirm = isReserveConfirm;
    }

    public Integer getAdmissionTime() {
        return admissionTime;
    }

    public void setAdmissionTime(Integer admissionTime) {
        this.admissionTime = admissionTime;
    }

    public Boolean getOnlineReserve() {
        return isOnlineReserve;
    }

    public void setOnlineReserve(Boolean isOnlineReserve) {
        this.isOnlineReserve = isOnlineReserve;
    }

    public Double getLessonPrice() {
        return lessonPrice;
    }

    public void setLessonPrice(Double lessonPrice) {
        this.lessonPrice = lessonPrice;
    }

    public Boolean getLimitReserve() {
        return isLimitReserve;
    }

    public void setLimitReserve(Boolean isLimitReserve) {
        this.isLimitReserve = isLimitReserve;
    }

    public Boolean getCardSettle() {
        return isCardSettle;
    }

    public void setCardSettle(Boolean isCardSettle) {
        this.isCardSettle = isCardSettle;
    }

    public Boolean getCourseSettle() {
        return isCourseSettle;
    }

    public void setCourseSettle(Boolean isCourseSettle) {
        this.isCourseSettle = isCourseSettle;
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
        return "FrEducationConfig{" +
        ", educationId=" + educationId +
        ", reserveTime=" + reserveTime +
        ", settingTime=" + settingTime +
        ", isStartClass=" + isStartClass +
        ", isChooseCoach=" + isChooseCoach +
        ", isReserveConfirm=" + isReserveConfirm +
        ", admissionTime=" + admissionTime +
        ", isOnlineReserve=" + isOnlineReserve +
        ", lessonPrice=" + lessonPrice +
        ", isLimitReserve=" + isLimitReserve +
        ", isCardSettle=" + isCardSettle +
        ", isCourseSettle=" + isCourseSettle +
        ", id=" + id +
        "}";
    }
}
