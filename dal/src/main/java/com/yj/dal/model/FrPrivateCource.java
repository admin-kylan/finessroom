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
 * 
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-24
 */
@TableName("fr_private_cource")
public class FrPrivateCource extends Model<FrPrivateCource> {

    private static final long serialVersionUID = 1L;

    private String name;
    @TableField("class_scheduling")
    private Double classScheduling;
    @TableField("class_info")
    private String classInfo;
    @TableField("promotion_price")
    private Double promotionPrice;
    @TableField("valid_time")
    private Integer validTime;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField("member_price")
    private Double memberPrice;
    @TableField("update_user")
    private String updateUser;
    private Integer time;
    @TableField("customer_code")
    private String customerCode;
    @TableField("private_image")
    private String privateImage;
    @TableField("is_account_spending")
    private Integer isAccountSpending;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField("assign_teacher_type")
    private Integer assignTeacherType;
    @TableField("assign_teacher")
    private Double assignTeacher;
    @TableId(value = "id")
    private String id;
    @TableField("create_user")
    private String createUser;
    @TableField("is_show_desk")
    private Integer isShowDesk;
    @TableField("remain_cource_num")
    private Integer remainCourceNum;
    @TableField("market_price")
    private Double marketPrice;
    @TableField("is_using")
    private Integer isUsing;
    @TableField("class_scheduling_type")
    private Integer classSchedulingType;
    @TableField("valid_time_type")
    private Integer validTimeType;
    //场馆ID
    @TableField("sdaduim_id")
    private String sdaduimId;

    public String getSdaduimId() {
        return sdaduimId;
    }

    public void setSdaduimId(String sdaduimId) {
        this.sdaduimId = sdaduimId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getClassScheduling() {
        return classScheduling;
    }

    public void setClassScheduling(Double classScheduling) {
        this.classScheduling = classScheduling;
    }

    public String getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(String classInfo) {
        this.classInfo = classInfo;
    }

    public Double getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(Double promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Integer getValidTime() {
        return validTime;
    }

    public void setValidTime(Integer validTime) {
        this.validTime = validTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(Double memberPrice) {
        this.memberPrice = memberPrice;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getPrivateImage() {
        return privateImage;
    }

    public void setPrivateImage(String privateImage) {
        this.privateImage = privateImage;
    }

    public Integer getIsAccountSpending() {
        return isAccountSpending;
    }

    public void setIsAccountSpending(Integer isAccountSpending) {
        this.isAccountSpending = isAccountSpending;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getAssignTeacherType() {
        return assignTeacherType;
    }

    public void setAssignTeacherType(Integer assignTeacherType) {
        this.assignTeacherType = assignTeacherType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Integer getIsShowDesk() {
        return isShowDesk;
    }

    public void setIsShowDesk(Integer isShowDesk) {
        this.isShowDesk = isShowDesk;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getIsUsing() {
        return isUsing;
    }

    public void setIsUsing(Integer isUsing) {
        this.isUsing = isUsing;
    }

    public Integer getClassSchedulingType() {
        return classSchedulingType;
    }

    public void setClassSchedulingType(Integer classSchedulingType) {
        this.classSchedulingType = classSchedulingType;
    }

    public Integer getValidTimeType() {
        return validTimeType;
    }

    public void setValidTimeType(Integer validTimeType) {
        this.validTimeType = validTimeType;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrPrivateCource{" +
        ", name=" + name +
        ", classScheduling=" + classScheduling +
        ", classInfo=" + classInfo +
        ", promotionPrice=" + promotionPrice +
        ", validTime=" + validTime +
        ", createTime=" + createTime +
        ", memberPrice=" + memberPrice +
        ", updateUser=" + updateUser +
        ", time=" + time +
        ", customerCode=" + customerCode +
        ", privateImage=" + privateImage +
        ", isAccountSpending=" + isAccountSpending +
        ", updateTime=" + updateTime +
        ", assignTeacherType=" + assignTeacherType +
        ", id=" + id +
        ", createUser=" + createUser +
        ", isShowDesk=" + isShowDesk +
        ", marketPrice=" + marketPrice +
        ", isUsing=" + isUsing +
        ", classSchedulingType=" + classSchedulingType +
        ", validTimeType=" + validTimeType +
        "}";
    }

	public Double getAssignTeacher() {
		return assignTeacher;
	}

	public void setAssignTeacher(Double assignTeacher) {
		this.assignTeacher = assignTeacher;
	}

	public Integer getRemainCourceNum() {
		return remainCourceNum;
	}

	public void setRemainCourceNum(Integer remainCourceNum) {
		this.remainCourceNum = remainCourceNum;
	}
}
