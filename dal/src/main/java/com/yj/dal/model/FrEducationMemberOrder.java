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
 * 个人消费明细表
 * </p>
 *
 * @author MP自动生成
 * @since 2019-01-12
 */
@TableName("fr_education_member_order")
public class FrEducationMemberOrder extends Model<FrEducationMemberOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 课程用户表，用户预约课程Id
     */
    @TableField("client_info_id")
    private String clientInfoId;
    /**
     * 课时数 消费数量
     */
    @TableField("class_num")
    private Integer classNum;
    /**
     * 真实开始时间
     */
    @TableField("begin_date_real")
    private Date beginDateReal;
    /**
     * 真实结束时间
     */
    @TableField("end_date_real")
    private Date endDateReal;
    /**
     * 使用人
     */
    @TableField("use_user_name")
    private String useUserName;
    /**
     * 总时长
     */
    @TableField("total_time")
    private Integer totalTime;
    /**
     * 消费时间
     */
    @TableField("create_date")
    private Date createDate;
    /**
     * 消费金额
     */
    private Double price;
    /**
     * 卡类型
     */
    @TableField("card_type")
    private String cardType;
    /**
     * 卡名称
     */
    @TableField("card_name")
    private String cardName;
    /**
     * 可用权益
     */
    @TableField("use_benefit")
    private Integer useBenefit;
    /**
     * 剩余权益
     */
    @TableField("remain_benefit")
    private Integer remainBenefit;
    /**
     * 结算员/操作员
     */
    @TableField("create_name")
    private String createName;
    /**
     * 销售员
     */
    @TableField("sale_user_name")
    private String saleUserName;
    /**
     * 评分
     */
    private Double score;
    /**
     * 评分内容
     */
    private String description;
    /**
     * 票据号
     */
    @TableField("bill_number")
    private String billNumber;
    /**
     * 是否可用
     */
    @TableField("is_use")
    private Boolean isUse;
    /**
     * 会员自我感觉
     */
    @TableField("member_assess")
    private String memberAssess;
    /**
     * 教练小结
     */
    @TableField("coach_summary")
    private String coachSummary;
    /**
     * 方案改进
     */
    @TableField("program_better")
    private String programBetter;
    /**
     * 会员卡Id
     */
    @TableField("member_card_id")
    private String memberCardId;
    /**
     * 会员卡号
     */
    @TableField("member_card_no")
    private String memberCardNo;
    @TableId(value = "id", type = IdType.UUID)
    private String id;


    public String getClientInfoId() {
        return clientInfoId;
    }

    public void setClientInfoId(String clientInfoId) {
        this.clientInfoId = clientInfoId;
    }

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    public Date getBeginDateReal() {
        return beginDateReal;
    }

    public void setBeginDateReal(Date beginDateReal) {
        this.beginDateReal = beginDateReal;
    }

    public Date getEndDateReal() {
        return endDateReal;
    }

    public void setEndDateReal(Date endDateReal) {
        this.endDateReal = endDateReal;
    }

    public String getUseUserName() {
        return useUserName;
    }

    public void setUseUserName(String useUserName) {
        this.useUserName = useUserName;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Integer getUseBenefit() {
        return useBenefit;
    }

    public void setUseBenefit(Integer useBenefit) {
        this.useBenefit = useBenefit;
    }

    public Integer getRemainBenefit() {
        return remainBenefit;
    }

    public void setRemainBenefit(Integer remainBenefit) {
        this.remainBenefit = remainBenefit;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getSaleUserName() {
        return saleUserName;
    }

    public void setSaleUserName(String saleUserName) {
        this.saleUserName = saleUserName;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public Boolean getUse() {
        return isUse;
    }

    public void setUse(Boolean isUse) {
        this.isUse = isUse;
    }

    public String getMemberAssess() {
        return memberAssess;
    }

    public void setMemberAssess(String memberAssess) {
        this.memberAssess = memberAssess;
    }

    public String getCoachSummary() {
        return coachSummary;
    }

    public void setCoachSummary(String coachSummary) {
        this.coachSummary = coachSummary;
    }

    public String getProgramBetter() {
        return programBetter;
    }

    public void setProgramBetter(String programBetter) {
        this.programBetter = programBetter;
    }

    public String getMemberCardId() {
        return memberCardId;
    }

    public void setMemberCardId(String memberCardId) {
        this.memberCardId = memberCardId;
    }

    public String getMemberCardNo() {
        return memberCardNo;
    }

    public void setMemberCardNo(String memberCardNo) {
        this.memberCardNo = memberCardNo;
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
        return "FrEducationMemberOrder{" +
        ", clientInfoId=" + clientInfoId +
        ", classNum=" + classNum +
        ", beginDateReal=" + beginDateReal +
        ", endDateReal=" + endDateReal +
        ", useUserName=" + useUserName +
        ", totalTime=" + totalTime +
        ", createDate=" + createDate +
        ", price=" + price +
        ", cardType=" + cardType +
        ", cardName=" + cardName +
        ", useBenefit=" + useBenefit +
        ", remainBenefit=" + remainBenefit +
        ", createName=" + createName +
        ", saleUserName=" + saleUserName +
        ", score=" + score +
        ", description=" + description +
        ", billNumber=" + billNumber +
        ", isUse=" + isUse +
        ", memberAssess=" + memberAssess +
        ", coachSummary=" + coachSummary +
        ", programBetter=" + programBetter +
        ", memberCardId=" + memberCardId +
        ", memberCardNo=" + memberCardNo +
        ", id=" + id +
        "}";
    }
}
