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
 * 人员信息表
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-31
 */
@TableName("PersonnelInfo")
public class PersonnelInfo extends Model<PersonnelInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户代码
     */
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    /**
     * 捷径系统用户信息表
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /**
     * 用户名
     */
    private String UserName;
    /**
     * 密码
     */
    private String PassWord;
    /**
     * 姓名
     */
    private String RelName;
    /**
     * 性别 true男 false女
     */
    private Boolean Sex;
    /**
     * 客户手机号码
     */
    private String Mobile;
    /**
     * 创建时间
     */
    private Date CreateTime;
    /**
     * 入职时间
     */
    private Date EntryTime;
    /**
     * 离职时间
     */
    private Date LeaveTime;
    /**
     * 门店ID
     */
    private String ShopId;
    /**
     * 0-超级管理员 1-其他用户
     */
    private String DepartmentId;
    /**
     * 0-正常 1-离职
     */
    private Integer Status;
    /**
     * 生日
     */
    private Date Birthday;
    /**
     * 工号
     */
    private String JobNumber;
    /**
     * 邮箱
     */
    private String Email;
    /**
     * 内部分机
     */
    private String IndoorTel;
    /**
     * 直线电话
     */
    private String DirectTel;
    /**
     * 集团短号
     */
    private String GroupNumber;
    /**
     * 指纹
     */
    private String FingerPrint;
    /**
     * 人脸识别
     */
    private String Veriface;
    /**
     * 是否删除 0-否 1-是
     */
    private Boolean IsDel;
    /**
     * 删除时间
     */
    private Date DeleteTime;
    /**
     * 删除者姓名
     */
    private String DeletedBy;
    /**
     * 删除者ID
     */
    private String DeletedById;
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

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String PassWord) {
        this.PassWord = PassWord;
    }

    public String getRelName() {
        return RelName;
    }

    public void setRelName(String RelName) {
        this.RelName = RelName;
    }

    public Boolean getSex() {
        return Sex;
    }

    public void setSex(Boolean Sex) {
        this.Sex = Sex;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }

    public Date getEntryTime() {
        return EntryTime;
    }

    public void setEntryTime(Date EntryTime) {
        this.EntryTime = EntryTime;
    }

    public Date getLeaveTime() {
        return LeaveTime;
    }

    public void setLeaveTime(Date LeaveTime) {
        this.LeaveTime = LeaveTime;
    }

    public String getShopId() {
        return ShopId;
    }

    public void setShopId(String ShopId) {
        this.ShopId = ShopId;
    }

    public String getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(String DepartmentId) {
        this.DepartmentId = DepartmentId;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date Birthday) {
        this.Birthday = Birthday;
    }

    public String getJobNumber() {
        return JobNumber;
    }

    public void setJobNumber(String JobNumber) {
        this.JobNumber = JobNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getIndoorTel() {
        return IndoorTel;
    }

    public void setIndoorTel(String IndoorTel) {
        this.IndoorTel = IndoorTel;
    }

    public String getDirectTel() {
        return DirectTel;
    }

    public void setDirectTel(String DirectTel) {
        this.DirectTel = DirectTel;
    }

    public String getGroupNumber() {
        return GroupNumber;
    }

    public void setGroupNumber(String GroupNumber) {
        this.GroupNumber = GroupNumber;
    }

    public String getFingerPrint() {
        return FingerPrint;
    }

    public void setFingerPrint(String FingerPrint) {
        this.FingerPrint = FingerPrint;
    }

    public String getVeriface() {
        return Veriface;
    }

    public void setVeriface(String Veriface) {
        this.Veriface = Veriface;
    }

    public Boolean getIsDel() {
        return IsDel;
    }

    public void setIsDel(Boolean IsDel) {
        this.IsDel = IsDel;
    }

    public Date getDeleteTime() {
        return DeleteTime;
    }

    public void setDeleteTime(Date DeleteTime) {
        this.DeleteTime = DeleteTime;
    }

    public String getDeletedBy() {
        return DeletedBy;
    }

    public void setDeletedBy(String DeletedBy) {
        this.DeletedBy = DeletedBy;
    }

    public String getDeletedById() {
        return DeletedById;
    }

    public void setDeletedById(String DeletedById) {
        this.DeletedById = DeletedById;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "PersonnelInfo{" +
                ", CustomerCode=" + CustomerCode +
                ", id=" + id +
                ", UserName=" + UserName +
                ", PassWord=" + PassWord +
                ", RelName=" + RelName +
                ", Sex=" + Sex +
                ", Mobile=" + Mobile +
                ", CreateTime=" + CreateTime +
                ", EntryTime=" + EntryTime +
                ", LeaveTime=" + LeaveTime +
                ", ShopId=" + ShopId +
                ", DepartmentId=" + DepartmentId +
                ", Status=" + Status +
                ", Birthday=" + Birthday +
                ", JobNumber=" + JobNumber +
                ", Email=" + Email +
                ", IndoorTel=" + IndoorTel +
                ", DirectTel=" + DirectTel +
                ", GroupNumber=" + GroupNumber +
                ", FingerPrint=" + FingerPrint +
                ", Veriface=" + Veriface +
                ", IsDel=" + IsDel +
                ", DeleteTime=" + DeleteTime +
                ", DeletedBy=" + DeletedBy +
                ", DeletedById=" + DeletedById +
                ", ExtendField1=" + ExtendField1 +
                ", ExtendField2=" + ExtendField2 +
                ", ExtendField3=" + ExtendField3 +
                "}";
    }
}
