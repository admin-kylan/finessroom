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
 * 会员增购项目表

 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-27
 */
@TableName("AddProject")
public class AddProject extends Model<AddProject> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户代码 （方便客户导出资料）ids
     */
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    /**
     * ID PK
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /**
     * 合同编号
     */
    private String ContractNumber;
    /**
     * 日期
     */
    private Date UseDay;
    /**
     * 销售类型?0?POS销售?1?新会员购买?2?续课?3?场地开课
     */
    private Integer SaleType;
    /**
     * 门店ID
     */
    private String ShopId;
    /**
     * 场馆ID
     */
    private String SdadiumId;
    /**
     * 卡ID
     */
    private String CardId;
    /**
     * 项目ID
     */
    private String ProjectId;
    /**
     * 课程系列ID
     */
    private String CourseSeriousId;
    /**
     * 课程ID
     */
    private String CourseId;
    /**
     * 有效期
     */
    private String Useful;
    /**
     * 开始时间
     */
    private Date StartTime;
    /**
     * 结束时间
     */
    private Date EndTime;
    /**
     * 销售人员
     */
    private String SaleId;
    /**
     * 销售人员姓名
     */
    private String SaleName;
    /**
     * 消费类型??0?普通消费?1?会员消费?2?员工消费??3?挂账?4?挂单
     */
    private Integer ConsumeType;
    /**
     * 状态?0?正常?1欠款?2?过期?3?历史?4?未开始
     */
    private Integer State;


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

    public String getContractNumber() {
        return ContractNumber;
    }

    public void setContractNumber(String ContractNumber) {
        this.ContractNumber = ContractNumber;
    }

    public Date getUseDay() {
        return UseDay;
    }

    public void setUseDay(Date UseDay) {
        this.UseDay = UseDay;
    }

    public Integer getSaleType() {
        return SaleType;
    }

    public void setSaleType(Integer SaleType) {
        this.SaleType = SaleType;
    }

    public String getShopId() {
        return ShopId;
    }

    public void setShopId(String ShopId) {
        this.ShopId = ShopId;
    }

    public String getSdadiumId() {
        return SdadiumId;
    }

    public void setSdadiumId(String SdadiumId) {
        this.SdadiumId = SdadiumId;
    }

    public String getCardId() {
        return CardId;
    }

    public void setCardId(String CardId) {
        this.CardId = CardId;
    }

    public String getProjectId() {
        return ProjectId;
    }

    public void setProjectId(String ProjectId) {
        this.ProjectId = ProjectId;
    }

    public String getCourseSeriousId() {
        return CourseSeriousId;
    }

    public void setCourseSeriousId(String CourseSeriousId) {
        this.CourseSeriousId = CourseSeriousId;
    }

    public String getCourseId() {
        return CourseId;
    }

    public void setCourseId(String CourseId) {
        this.CourseId = CourseId;
    }

    public String getUseful() {
        return Useful;
    }

    public void setUseful(String Useful) {
        this.Useful = Useful;
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

    public String getSaleId() {
        return SaleId;
    }

    public void setSaleId(String SaleId) {
        this.SaleId = SaleId;
    }

    public String getSaleName() {
        return SaleName;
    }

    public void setSaleName(String SaleName) {
        this.SaleName = SaleName;
    }

    public Integer getConsumeType() {
        return ConsumeType;
    }

    public void setConsumeType(Integer ConsumeType) {
        this.ConsumeType = ConsumeType;
    }

    public Integer getState() {
        return State;
    }

    public void setState(Integer State) {
        this.State = State;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "AddProject{" +
        ", CustomerCode=" + CustomerCode +
        ", id=" + id +
        ", ContractNumber=" + ContractNumber +
        ", UseDay=" + UseDay +
        ", SaleType=" + SaleType +
        ", ShopId=" + ShopId +
        ", SdadiumId=" + SdadiumId +
        ", CardId=" + CardId +
        ", ProjectId=" + ProjectId +
        ", CourseSeriousId=" + CourseSeriousId +
        ", CourseId=" + CourseId +
        ", Useful=" + Useful +
        ", StartTime=" + StartTime +
        ", EndTime=" + EndTime +
        ", SaleId=" + SaleId +
        ", SaleName=" + SaleName +
        ", ConsumeType=" + ConsumeType +
        ", State=" + State +
        "}";
    }
}
