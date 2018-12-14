package com.yj.dal.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 门店单个设置表
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-27
 */
@TableName("fr_store_single")
public class FrStoreSingle extends Model<FrStoreSingle> {

    private static final long serialVersionUID = 1L;

    /**
     * 新建现有客户X小时内没有跟进记录 默认24
     */
    @TableField("xy_gj_hour")
    private Integer xyGjHour;
    /**
     * 现有认领状态（0：进入会员认领平台 1：不进入认领平台）默认1
     */
    @TableField("xy_gj_status")
    private Boolean xyGjStatus;
    /**
     * 新建潜在客户X小时内没有跟进记录 默认24
     */
    @TableField("qz_gj_hour")
    private Integer qzGjHour;
    /**
     * 潜在认领状态（0：进入会员认领平台 1：不进入认领平台）默认1
     */
    @TableField("qz_gj_status")
    private Boolean qzGjStatus;
    /**
     * 授权人显示（0：显示所有门店授权人 1：仅显示本店授权人）默认1
     */
    @TableField("show_sqr")
    private Boolean showSqr;
    /**
     * 销售人员显示（0：显示所有门店销售人员 1：仅显示本店销售人员）默认1
     */
    @TableField("show_xsr")
    private Boolean showXsr;
    /**
     * 备用
     */
    private String flag;
    /**
     * 类型(备用)
     */
    private Integer type;
    /**
     * 是否启用(0：不启用 1：启用）默认1
     */
    @TableField("is_using")
    private Boolean isUsing;
    /**
     * 创建人时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;
    /**
     * 创建人名称
     */
    @TableField(value = "create_user_name", fill = FieldFill.INSERT)
    private String createUserName;
    /**
     * 更新人名称
     */
    @TableField(value = "update_user_name", fill = FieldFill.INSERT_UPDATE)
    private String updateUserName;
    /**
     * 客户代码
     */
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    /**
     * 创建人ID
     */
    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    private String createUserId;
    /**
     * 更新人ID
     */
    @TableField(value = "update_user_id", fill = FieldFill.INSERT_UPDATE)
    private String updateUserId;
    @TableId(value = "id", type = IdType.UUID)
    private String id;


    public Integer getXyGjHour() {
        return xyGjHour;
    }

    public void setXyGjHour(Integer xyGjHour) {
        this.xyGjHour = xyGjHour;
    }

    public Boolean getXyGjStatus() {
        return xyGjStatus;
    }

    public void setXyGjStatus(Boolean xyGjStatus) {
        this.xyGjStatus = xyGjStatus;
    }

    public Integer getQzGjHour() {
        return qzGjHour;
    }

    public void setQzGjHour(Integer qzGjHour) {
        this.qzGjHour = qzGjHour;
    }

    public Boolean getQzGjStatus() {
        return qzGjStatus;
    }

    public void setQzGjStatus(Boolean qzGjStatus) {
        this.qzGjStatus = qzGjStatus;
    }

    public Boolean getShowSqr() {
        return showSqr;
    }

    public void setShowSqr(Boolean showSqr) {
        this.showSqr = showSqr;
    }

    public Boolean getShowXsr() {
        return showXsr;
    }

    public void setShowXsr(Boolean showXsr) {
        this.showXsr = showXsr;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getUsing() {
        return isUsing;
    }

    public void setUsing(Boolean isUsing) {
        this.isUsing = isUsing;
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

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrStoreSingle{" +
        ", xyGjHour=" + xyGjHour +
        ", xyGjStatus=" + xyGjStatus +
        ", qzGjHour=" + qzGjHour +
        ", qzGjStatus=" + qzGjStatus +
        ", showSqr=" + showSqr +
        ", showXsr=" + showXsr +
        ", flag=" + flag +
        ", type=" + type +
        ", isUsing=" + isUsing +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", createUserName=" + createUserName +
        ", updateUserName=" + updateUserName +
        ", CustomerCode=" + CustomerCode +
        ", createUserId=" + createUserId +
        ", updateUserId=" + updateUserId +
        ", id=" + id +
        "}";
    }
}
