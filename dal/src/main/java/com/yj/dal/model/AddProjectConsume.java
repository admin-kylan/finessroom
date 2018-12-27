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
 * 增购项目消费表

 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-27
 */
@TableName("AddProjectConsume")
public class AddProjectConsume extends Model<AddProjectConsume> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户代码 （方便客户导出资料）
     */
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /**
     * 开场ID
     */
    private String OpenId;
    /**
     * 开场ID
     */
    private String SysConsumeOrderId;
    /**
     * 会员卡ID
     */
    private String MemberCardId;
    /**
     * 增购项目ID
     */
    private String AddProjectId;
    /**
     * 增购项目名称
     */
    private String AddProjectName;
    /**
     * 门店ID
     */
    private String ShopId;
    /**
     * 场馆ID
     */
    private String SdaduimId;
    /**
     * 单位
     */
    private String Unit;
    /**
     * 开始时间
     */
    private Date StartTime;
    /**
     * 结束时间
     */
    private Date EndTime;
    /**
     * 是否冲销?0-否?1-有
     */
    private Boolean IfOffset;
    /**
     * 销售人员ID
     */
    private String SalesId;
    /**
     * 销售人员名称
     */
    private String SalesName;
    /**
     * 服务人员ID
     */
    private String ServiceId;
    /**
     * 服务人员姓名
     */
    private String ServiceName;
    /**
     * 创建时间
     */
    private Date CreateTime;


    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpenId() {
        return OpenId;
    }

    public void setOpenId(String OpenId) {
        this.OpenId = OpenId;
    }

    public String getSysConsumeOrderId() {
        return SysConsumeOrderId;
    }

    public void setSysConsumeOrderId(String SysConsumeOrderId) {
        this.SysConsumeOrderId = SysConsumeOrderId;
    }

    public String getMemberCardId() {
        return MemberCardId;
    }

    public void setMemberCardId(String MemberCardId) {
        this.MemberCardId = MemberCardId;
    }

    public String getAddProjectId() {
        return AddProjectId;
    }

    public void setAddProjectId(String AddProjectId) {
        this.AddProjectId = AddProjectId;
    }

    public String getAddProjectName() {
        return AddProjectName;
    }

    public void setAddProjectName(String AddProjectName) {
        this.AddProjectName = AddProjectName;
    }

    public String getShopId() {
        return ShopId;
    }

    public void setShopId(String ShopId) {
        this.ShopId = ShopId;
    }

    public String getSdaduimId() {
        return SdaduimId;
    }

    public void setSdaduimId(String SdaduimId) {
        this.SdaduimId = SdaduimId;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    public Date getStartTime() {
        return StartTime;
    }

    public void setStartTime(Date StartTime) {
        this.StartTime = StartTime;
    }

    public Date getEndTime() {
        return EndTime;
    }

    public void setEndTime(Date EndTime) {
        this.EndTime = EndTime;
    }

    public Boolean getIfOffset() {
        return IfOffset;
    }

    public void setIfOffset(Boolean IfOffset) {
        this.IfOffset = IfOffset;
    }

    public String getSalesId() {
        return SalesId;
    }

    public void setSalesId(String SalesId) {
        this.SalesId = SalesId;
    }

    public String getSalesName() {
        return SalesName;
    }

    public void setSalesName(String SalesName) {
        this.SalesName = SalesName;
    }

    public String getServiceId() {
        return ServiceId;
    }

    public void setServiceId(String ServiceId) {
        this.ServiceId = ServiceId;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String ServiceName) {
        this.ServiceName = ServiceName;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "AddProjectConsume{" +
        ", CustomerCode=" + CustomerCode +
        ", id=" + id +
        ", OpenId=" + OpenId +
        ", SysConsumeOrderId=" + SysConsumeOrderId +
        ", MemberCardId=" + MemberCardId +
        ", AddProjectId=" + AddProjectId +
        ", AddProjectName=" + AddProjectName +
        ", ShopId=" + ShopId +
        ", SdaduimId=" + SdaduimId +
        ", Unit=" + Unit +
        ", StartTime=" + StartTime +
        ", EndTime=" + EndTime +
        ", IfOffset=" + IfOffset +
        ", SalesId=" + SalesId +
        ", SalesName=" + SalesName +
        ", ServiceId=" + ServiceId +
        ", ServiceName=" + ServiceName +
        ", CreateTime=" + CreateTime +
        "}";
    }
}
