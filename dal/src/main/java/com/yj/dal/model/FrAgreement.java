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
 * 协议号规则表
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-27
 */
@TableName("fr_agreement")
public class FrAgreement extends Model<FrAgreement> {

    private static final long serialVersionUID = 1L;

    /**
     * 作废协议号
     */
    @TableField("protocol_no")
    private String protocolNo;
    /**
     * 开始编号
     */
    @TableField("start_no")
    private String startNo;
    /**
     * 结束编号
     */
    @TableField("end_no")
    private String endNo;
    /**
     * 本批次数量
     */
    @TableField("batch_num")
    private Integer batchNum;
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
    /**
     * 是否去除对应协议号（0、是；1、否）
     */
    @TableField("use_limit_num")
    private Boolean useLimitNum;
    /**
     * 协议号
     */
    @TableField("limit_num")
    private String limitNum;
    /**
     * 是否去除中间包含某一数字的协议号（0、是；1、否）
     */
    @TableField("use_middle_num")
    private Boolean useMiddleNum;
    /**
     * 去除中间内容
     */
    @TableField("middle_num")
    private String middleNum;
    /**
     * 是否去除尾部包含某一数字的协议号（0、是；1、否）
     */
    @TableField("use_tail_num")
    private Boolean useTailNum;
    /**
     * 去除尾部内容
     */
    @TableField("tail_num")
    private String tailNum;
    /**
     * 是否去除头部包含某一数字的协议号（0、是；1、否）
     */
    @TableField("use_head_num")
    private Boolean useHeadNum;
    /**
     * 去除头部内容
     */
    @TableField("head_num")
    private String headNum;
    @TableId(value = "id")
    private String id;


    public String getProtocolNo() {
        return protocolNo;
    }

    public void setProtocolNo(String protocolNo) {
        this.protocolNo = protocolNo;
    }

    public String getStartNo() {
        return startNo;
    }

    public void setStartNo(String startNo) {
        this.startNo = startNo;
    }

    public String getEndNo() {
        return endNo;
    }

    public void setEndNo(String endNo) {
        this.endNo = endNo;
    }

    public Integer getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(Integer batchNum) {
        this.batchNum = batchNum;
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

    public Boolean getUseLimitNum() {
        return useLimitNum;
    }

    public void setUseLimitNum(Boolean useLimitNum) {
        this.useLimitNum = useLimitNum;
    }

    public String getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(String limitNum) {
        this.limitNum = limitNum;
    }

    public Boolean getUseMiddleNum() {
        return useMiddleNum;
    }

    public void setUseMiddleNum(Boolean useMiddleNum) {
        this.useMiddleNum = useMiddleNum;
    }

    public String getMiddleNum() {
        return middleNum;
    }

    public void setMiddleNum(String middleNum) {
        this.middleNum = middleNum;
    }

    public Boolean getUseTailNum() {
        return useTailNum;
    }

    public void setUseTailNum(Boolean useTailNum) {
        this.useTailNum = useTailNum;
    }

    public String getTailNum() {
        return tailNum;
    }

    public void setTailNum(String tailNum) {
        this.tailNum = tailNum;
    }

    public Boolean getUseHeadNum() {
        return useHeadNum;
    }

    public void setUseHeadNum(Boolean useHeadNum) {
        this.useHeadNum = useHeadNum;
    }

    public String getHeadNum() {
        return headNum;
    }

    public void setHeadNum(String headNum) {
        this.headNum = headNum;
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
        return "FrAgreement{" +
        ", protocolNo=" + protocolNo +
        ", startNo=" + startNo +
        ", endNo=" + endNo +
        ", batchNum=" + batchNum +
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
        ", useLimitNum=" + useLimitNum +
        ", limitNum=" + limitNum +
        ", useMiddleNum=" + useMiddleNum +
        ", middleNum=" + middleNum +
        ", useTailNum=" + useTailNum +
        ", tailNum=" + tailNum +
        ", useHeadNum=" + useHeadNum +
        ", headNum=" + headNum +
        ", id=" + id +
        "}";
    }
}
