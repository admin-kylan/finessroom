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
 * 课程消费结算单
 * </p>
 *
 * @author MP自动生成
 * @since 2019-01-05
 */
@TableName("fr_education_class_order")
public class FrEducationClassOrder extends Model<FrEducationClassOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 课程Id
     */
    @TableField("education_id")
    private String educationId;
    /**
     * 总时长
     */
    @TableField("total_time")
    private Integer totalTime;
    /**
     * 总收益
     */
    @TableField("total_gain")
    private Double totalGain;
    /**
     * 总人数
     */
    @TableField("total_num")
    private Integer totalNum;
    /**
     * 完成上课的人数
     */
    @TableField("finish_num")
    private Integer finishNum;
    /**
     * 失约人数
     */
    @TableField("undone_num")
    private Integer undoneNum;
    /**
     * 结算员
     */
    @TableField("create_user")
    private String createUser;
    /**
     * 结算员id
     */
    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    private String createUserId;
    /**
     * 评分
     */
    private Double score;
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
     * 真实开始时间
     */
    @TableField("begin_date_real")
    private Date beginDateReal;
    /**
     * 真实结束时间
     */
    @TableField("end_date_real")
    private Date endDateReal;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEducationId() {
        return educationId;
    }

    public void setEducationId(String educationId) {
        this.educationId = educationId;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public Double getTotalGain() {
        return totalGain;
    }

    public void setTotalGain(Double totalGain) {
        this.totalGain = totalGain;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getFinishNum() {
        return finishNum;
    }

    public void setFinishNum(Integer finishNum) {
        this.finishNum = finishNum;
    }

    public Integer getUndoneNum() {
        return undoneNum;
    }

    public void setUndoneNum(Integer undoneNum) {
        this.undoneNum = undoneNum;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrEducationClassOrder{" +
        ", id=" + id +
        ", educationId=" + educationId +
        ", totalTime=" + totalTime +
        ", totalGain=" + totalGain +
        ", totalNum=" + totalNum +
        ", finishNum=" + finishNum +
        ", undoneNum=" + undoneNum +
        ", createUser=" + createUser +
        ", createUserId=" + createUserId +
        ", score=" + score +
        ", memberAssess=" + memberAssess +
        ", coachSummary=" + coachSummary +
        ", programBetter=" + programBetter +
        ", beginDateReal=" + beginDateReal +
        ", endDateReal=" + endDateReal +
        "}";
    }
}
