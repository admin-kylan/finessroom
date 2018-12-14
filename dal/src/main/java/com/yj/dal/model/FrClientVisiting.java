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
 * 客户到访记录
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-28
 */
@TableName("fr_client_visiting")
public class FrClientVisiting extends Model<FrClientVisiting> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户ID
     */
    @TableField("client_id")
    private String clientId;
    /**
     * 来访时间
     */
    @TableField("visiting_time")
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date visitingTime;
    /**
     * 离开时间
     */
    @TableField("leave_time")
    private Date leaveTime;
    /**
     * 创建人时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    /**
     * 创建人名称
     */
    @TableField(value = "create_user_name", fill = FieldFill.INSERT)
    private String createUserName;
    /**
     * 创建人ID
     */
    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    private String createUserId;
    /**
     * 扩展字段
     */
    private String ExtendField1;
    /**
     * 扩展字段
     */
    private String ExtendField2;
    /**
     * 扩展字段
     */
    private String ExtendField3;
    @TableId(value = "id", type = IdType.UUID)
    private String id;


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Date getVisitingTime() {
        return visitingTime;
    }

    public void setVisitingTime(Date visitingTime) {
        this.visitingTime = visitingTime;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getExtendField1() {
        return ExtendField1;
    }

    public void setExtendField1(String ExtendField1) {
        this.ExtendField1 = ExtendField1;
    }

    public String getExtendField2() {
        return ExtendField2;
    }

    public void setExtendField2(String ExtendField2) {
        this.ExtendField2 = ExtendField2;
    }

    public String getExtendField3() {
        return ExtendField3;
    }

    public void setExtendField3(String ExtendField3) {
        this.ExtendField3 = ExtendField3;
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
        return "FrClientVisiting{" +
        ", clientId=" + clientId +
        ", visitingTime=" + visitingTime +
        ", leaveTime=" + leaveTime +
        ", createTime=" + createTime +
        ", createUserName=" + createUserName +
        ", createUserId=" + createUserId +
        ", ExtendField1=" + ExtendField1 +
        ", ExtendField2=" + ExtendField2 +
        ", ExtendField3=" + ExtendField3 +
        ", id=" + id +
        "}";
    }
}
