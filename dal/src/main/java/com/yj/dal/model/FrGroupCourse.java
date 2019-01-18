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
 * @since 2018-12-08
 */
@TableName("fr_group_course")
public class FrGroupCourse extends Model<FrGroupCourse> {

    private static final long serialVersionUID = 1L;

    @TableField("promtion_price")
    private Double promtionPrice;
    private String info;
    @TableField("create_user")
    private String createUser;
    private String name;
    @TableField("valid_time")
    private Integer validTime;
    @TableField("customer_code")
    private String customerCode;
    private Integer time;
    @TableField("can_reserve_num")
    private Integer canReserveNum;
    @TableField("member_price")
    private Double memberPrice;
    @TableField("class_status")
    private Integer classStatus;
    @TableId(value = "id")
    private String id;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField("market_price")
    private Double marketPrice;
    @TableField("update_user")
    private String updateUser;
    @TableField("valid_time_type")
    private Integer validTimeType;
    @TableField("is_using")
    private Integer isUsing;
    @TableField("least_class_num")
    private Integer leastClassNum;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField("image_url")
    private String imageUrl;
    @TableField("series_id")
    private String seriesId;
    //课时数
    @TableField("class_count")
    private String classCount;

    public String getClassCount() {
        return classCount;
    }

    public void setClassCount(String classCount) {
        this.classCount = classCount;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getPromtionPrice() {
        return promtionPrice;
    }

    public void setPromtionPrice(Double promtionPrice) {
        this.promtionPrice = promtionPrice;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValidTime() {
        return validTime;
    }

    public void setValidTime(Integer validTime) {
        this.validTime = validTime;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getCanReserveNum() {
        return canReserveNum;
    }

    public void setCanReserveNum(Integer canReserveNum) {
        this.canReserveNum = canReserveNum;
    }

    public Double getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(Double memberPrice) {
        this.memberPrice = memberPrice;
    }

    public Integer getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(Integer classStatus) {
        this.classStatus = classStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getValidTimeType() {
        return validTimeType;
    }

    public void setValidTimeType(Integer validTimeType) {
        this.validTimeType = validTimeType;
    }

    public Integer getIsUsing() {
        return isUsing;
    }

    public void setIsUsing(Integer isUsing) {
        this.isUsing = isUsing;
    }

    public Integer getLeastClassNum() {
        return leastClassNum;
    }

    public void setLeastClassNum(Integer leastClassNum) {
        this.leastClassNum = leastClassNum;
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
        return "FrGroupCourse{" +
        ", promtionPrice=" + promtionPrice +
        ", info=" + info +
        ", createUser=" + createUser +
        ", name=" + name +
        ", validTime=" + validTime +
        ", customerCode=" + customerCode +
        ", time=" + time +
        ", canReserveNum=" + canReserveNum +
        ", memberPrice=" + memberPrice +
        ", classStatus=" + classStatus +
        ", id=" + id +
        ", createTime=" + createTime +
        ", marketPrice=" + marketPrice +
        ", updateUser=" + updateUser +
        ", validTimeType=" + validTimeType +
        ", isUsing=" + isUsing +
        ", leastClassNum=" + leastClassNum +
        ", updateTime=" + updateTime +
        "}";
    }
}
