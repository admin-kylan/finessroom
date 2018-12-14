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
 * 体能测试 内容保存
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-16
 */
@TableName("fr_client_physical_test")
public class FrClientPhysicalTest extends Model<FrClientPhysicalTest> {

    private static final long serialVersionUID = 1L;

    /**
     * 身高 CM
     */
    private Integer stature;
    /**
     * 体重 KG
     */
    private Double weight;
    /**
     * 上臂围 左 常态
     */
    @TableField("upperarm_left_normal")
    private String upperarmLeftNormal;
    /**
     * 上臂围 右 常态
     */
    @TableField("upperarm_right_normal")
    private String upperarmRightNormal;
    /**
     * 上臂围 左 紧张
     */
    @TableField("upperarm_left_nervous")
    private String upperarmLeftNervous;
    /**
     * 上臂围 右 紧张
     */
    @TableField("upperarm_right_nervous")
    private String upperarmRightNervous;
    /**
     * 前臂围 左 常态
     */
    @TableField("forearm_left_normal")
    private String forearmLeftNormal;
    /**
     * 前臂围 右 常态
     */
    @TableField("forearm_right_normal")
    private String forearmRightNormal;
    /**
     * 前臂围 左 紧张
     */
    @TableField("forearm_left_nervous")
    private String forearmLeftNervous;
    /**
     * 前臂围 右 紧张
     */
    @TableField("forearm_right_nervous")
    private String forearmRightNervous;
    /**
     * 胸围 常态
     */
    @TableField("bust_normal")
    private String bustNormal;
    /**
     * 胸围 紧张
     */
    @TableField("bust_nervous")
    private String bustNervous;
    /**
     * 臀围
     */
    private String hipline;
    /**
     * 腰围
     */
    private String waistline;
    /**
     * 腰臀比例
     */
    @TableField("waist_hip_ratio")
    private String waistHipRatio;
    /**
     * 12分钟跑测评
     */
    @TableField("twelve_run_evaluation")
    private String twelveRunEvaluation;
    /**
     * 大腿围 左 常态
     */
    @TableField("thigh_left_normal")
    private String thighLeftNormal;
    /**
     * 大腿围 右 常态
     */
    @TableField("thigh_right_normal")
    private String thighRightNormal;
    /**
     * 大腿围 左 紧张
     */
    @TableField("thigh_left_nervous")
    private String thighLeftNervous;
    /**
     * 大腿围 右 紧张
     */
    @TableField("thigh_right_nervous")
    private String thighRightNervous;

    /**
     * 小腿围 左 常态
     */
    @TableField("calf_left_normal")
    private String calfLeftNormal;
    /**
     * 小腿围 右 常态
     */
    @TableField("calf_right_normal")
    private String calfRightNormal;
    /**
     * 小腿围 左 紧张
     */
    @TableField("calf_left_nervous")
    private String calfLeftNervous;
    /**
     * 小腿围 右 紧张
     */
    @TableField("calf_right_nervous")
    private String calfRightNervous;
    /**
     * 体脂比
     */
    private String bodyfat;
    /**
     * 脉搏
     */
    private String pulse;
    /**
     * 血压
     */
    @TableField("blood_pressure ")
    private String bloodPressure;
    /**
     * 最大运动心率
     */
    @TableField("maximal_heart_rate")
    private String maximalHeartRate;
    /**
     * 合适运动心率
     */
    @TableField("proper_heart_rate")
    private String properHeartRate;
    /**
     * 心血管适运心率
     */
    @TableField("cardiovascular_heart_rate")
    private String cardiovascularHeartRate;
    /**
     * 静态心率
     */
    @TableField("resting_heart_rate")
    private String restingHeartRate;
    /**
     * 动态心率
     */
    @TableField("dynamic_heart_rate")
    private String dynamicHeartRate;
    /**
     * 仰卧起做测评
     */
    @TableField("supination_evaluation")
    private String supinationEvaluation;
    /**
     * 肌力 胸
     */
    @TableField("myodynamia_chest")
    private String myodynamiaChest;
    /**
     * 肌力 腹
     */
    @TableField("myodynamia_abdomen")
    private String myodynamiaAbdomen;
    /**
     * 肌力 背
     */
    @TableField("myodynamia_Back")
    private String myodynamiaBack;
    /**
     * 身体柔韧度/体前曲
     */
    @TableField("body_flexibility")
    private String bodyFlexibility;
    /**
     * 背部健康测评
     */
    @TableField("back_health_assessment")
    private String backHealthAssessment;
    /**
     * 基础代谢率
     */
    @TableField("basal_metabolic_rate")
    private String basalMetabolicRate;
    /**
     * BMI 指数
     */
    @TableField("BML_index")
    private String bmlIndex;
    /**
     * 私人教练建议
     */
    @TableField("personal_trainer_advice")
    private String personalTrainerAdvice;
    /**
     * 上级评价
     */
    @TableField("superior_evaluation")
    private String superiorEvaluation;
    /**
     * 体型图片
     */
    @TableField("physical_pic")
    private String physicalPic;
    /**
     * 测评日期
     */
    @TableField("evaluation_date")
    private Date evaluationDate;
    /**
     * 人员信息Id（教练ID）
     */
    @TableField("personal_id")
    private String personalId;
    /**
     * 创建日期
     */
    @TableField("build_date")
    private Date buildDate;
    /**
     * 客户ID
     */
    @TableField("client_id")
    private String clientId;
    /**
     * 测评结果(0：首次测评结果 1：二次测评结果）
     */
    private Boolean result;
    @TableId(value = "id", type = IdType.UUID)
    private String id;


    public Integer getStature() {
        return stature;
    }

    public void setStature(Integer stature) {
        this.stature = stature;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getUpperarmLeftNormal() {
        return upperarmLeftNormal;
    }

    public void setUpperarmLeftNormal(String upperarmLeftNormal) {
        this.upperarmLeftNormal = upperarmLeftNormal;
    }

    public String getUpperarmRightNormal() {
        return upperarmRightNormal;
    }

    public void setUpperarmRightNormal(String upperarmRightNormal) {
        this.upperarmRightNormal = upperarmRightNormal;
    }

    public String getUpperarmLeftNervous() {
        return upperarmLeftNervous;
    }

    public void setUpperarmLeftNervous(String upperarmLeftNervous) {
        this.upperarmLeftNervous = upperarmLeftNervous;
    }

    public String getUpperarmRightNervous() {
        return upperarmRightNervous;
    }

    public void setUpperarmRightNervous(String upperarmRightNervous) {
        this.upperarmRightNervous = upperarmRightNervous;
    }

    public String getForearmLeftNormal() {
        return forearmLeftNormal;
    }

    public void setForearmLeftNormal(String forearmLeftNormal) {
        this.forearmLeftNormal = forearmLeftNormal;
    }

    public String getForearmRightNormal() {
        return forearmRightNormal;
    }

    public void setForearmRightNormal(String forearmRightNormal) {
        this.forearmRightNormal = forearmRightNormal;
    }

    public String getForearmLeftNervous() {
        return forearmLeftNervous;
    }

    public void setForearmLeftNervous(String forearmLeftNervous) {
        this.forearmLeftNervous = forearmLeftNervous;
    }

    public String getForearmRightNervous() {
        return forearmRightNervous;
    }

    public void setForearmRightNervous(String forearmRightNervous) {
        this.forearmRightNervous = forearmRightNervous;
    }

    public String getBustNormal() {
        return bustNormal;
    }

    public void setBustNormal(String bustNormal) {
        this.bustNormal = bustNormal;
    }

    public String getBustNervous() {
        return bustNervous;
    }

    public void setBustNervous(String bustNervous) {
        this.bustNervous = bustNervous;
    }

    public String getHipline() {
        return hipline;
    }

    public void setHipline(String hipline) {
        this.hipline = hipline;
    }

    public String getWaistline() {
        return waistline;
    }

    public void setWaistline(String waistline) {
        this.waistline = waistline;
    }

    public String getWaistHipRatio() {
        return waistHipRatio;
    }

    public void setWaistHipRatio(String waistHipRatio) {
        this.waistHipRatio = waistHipRatio;
    }

    public String getTwelveRunEvaluation() {
        return twelveRunEvaluation;
    }

    public void setTwelveRunEvaluation(String twelveRunEvaluation) {
        this.twelveRunEvaluation = twelveRunEvaluation;
    }

    public String getThighLeftNormal() {
        return thighLeftNormal;
    }

    public void setThighLeftNormal(String thighLeftNormal) {
        this.thighLeftNormal = thighLeftNormal;
    }

    public String getThighRightNormal() {
        return thighRightNormal;
    }

    public void setThighRightNormal(String thighRightNormal) {
        this.thighRightNormal = thighRightNormal;
    }

    public String getThighLeftNervous() {
        return thighLeftNervous;
    }

    public void setThighLeftNervous(String thighLeftNervous) {
        this.thighLeftNervous = thighLeftNervous;
    }

    public String getThighRightNervous() {
        return thighRightNervous;
    }

    public void setThighRightNervous(String thighRightNervous) {
        this.thighRightNervous = thighRightNervous;
    }

    public String getCalfLeftNormal() {
        return calfLeftNormal;
    }

    public void setCalfLeftNormal(String calfLeftNormal) {
        this.calfLeftNormal = calfLeftNormal;
    }

    public String getCalfRightNormal() {
        return calfRightNormal;
    }

    public void setCalfRightNormal(String calfRightNormal) {
        this.calfRightNormal = calfRightNormal;
    }

    public String getCalfLeftNervous() {
        return calfLeftNervous;
    }

    public void setCalfLeftNervous(String calfLeftNervous) {
        this.calfLeftNervous = calfLeftNervous;
    }

    public String getCalfRightNervous() {
        return calfRightNervous;
    }

    public void setCalfRightNervous(String calfRightNervous) {
        this.calfRightNervous = calfRightNervous;
    }

    public String getBodyfat() {
        return bodyfat;
    }

    public void setBodyfat(String bodyfat) {
        this.bodyfat = bodyfat;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getMaximalHeartRate() {
        return maximalHeartRate;
    }

    public void setMaximalHeartRate(String maximalHeartRate) {
        this.maximalHeartRate = maximalHeartRate;
    }

    public String getProperHeartRate() {
        return properHeartRate;
    }

    public void setProperHeartRate(String properHeartRate) {
        this.properHeartRate = properHeartRate;
    }

    public String getCardiovascularHeartRate() {
        return cardiovascularHeartRate;
    }

    public void setCardiovascularHeartRate(String cardiovascularHeartRate) {
        this.cardiovascularHeartRate = cardiovascularHeartRate;
    }

    public String getRestingHeartRate() {
        return restingHeartRate;
    }

    public void setRestingHeartRate(String restingHeartRate) {
        this.restingHeartRate = restingHeartRate;
    }

    public String getDynamicHeartRate() {
        return dynamicHeartRate;
    }

    public void setDynamicHeartRate(String dynamicHeartRate) {
        this.dynamicHeartRate = dynamicHeartRate;
    }

    public String getSupinationEvaluation() {
        return supinationEvaluation;
    }

    public void setSupinationEvaluation(String supinationEvaluation) {
        this.supinationEvaluation = supinationEvaluation;
    }

    public String getMyodynamiaChest() {
        return myodynamiaChest;
    }

    public void setMyodynamiaChest(String myodynamiaChest) {
        this.myodynamiaChest = myodynamiaChest;
    }

    public String getMyodynamiaAbdomen() {
        return myodynamiaAbdomen;
    }

    public void setMyodynamiaAbdomen(String myodynamiaAbdomen) {
        this.myodynamiaAbdomen = myodynamiaAbdomen;
    }

    public String getMyodynamiaBack() {
        return myodynamiaBack;
    }

    public void setMyodynamiaBack(String myodynamiaBack) {
        this.myodynamiaBack = myodynamiaBack;
    }

    public String getBodyFlexibility() {
        return bodyFlexibility;
    }

    public void setBodyFlexibility(String bodyFlexibility) {
        this.bodyFlexibility = bodyFlexibility;
    }

    public String getBackHealthAssessment() {
        return backHealthAssessment;
    }

    public void setBackHealthAssessment(String backHealthAssessment) {
        this.backHealthAssessment = backHealthAssessment;
    }

    public String getBasalMetabolicRate() {
        return basalMetabolicRate;
    }

    public void setBasalMetabolicRate(String basalMetabolicRate) {
        this.basalMetabolicRate = basalMetabolicRate;
    }

    public String getBmlIndex() {
        return bmlIndex;
    }

    public void setBmlIndex(String bmlIndex) {
        this.bmlIndex = bmlIndex;
    }

    public String getPersonalTrainerAdvice() {
        return personalTrainerAdvice;
    }

    public void setPersonalTrainerAdvice(String personalTrainerAdvice) {
        this.personalTrainerAdvice = personalTrainerAdvice;
    }

    public String getSuperiorEvaluation() {
        return superiorEvaluation;
    }

    public void setSuperiorEvaluation(String superiorEvaluation) {
        this.superiorEvaluation = superiorEvaluation;
    }

    public String getPhysicalPic() {
        return physicalPic;
    }

    public void setPhysicalPic(String physicalPic) {
        this.physicalPic = physicalPic;
    }

    public Date getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(Date evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public Date getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(Date buildDate) {
        this.buildDate = buildDate;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
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
        return "FrClientPhysicalTest{" +
                ", stature=" + stature +
                ", weight=" + weight +
                ", upperarmLeftNormal=" + upperarmLeftNormal +
                ", upperarmRightNormal=" + upperarmRightNormal +
                ", upperarmLeftNervous=" + upperarmLeftNervous +
                ", upperarmRightNervous=" + upperarmRightNervous +
                ", forearmLeftNormal=" + forearmLeftNormal +
                ", forearmRightNormal=" + forearmRightNormal +
                ", forearmLeftNervous=" + forearmLeftNervous +
                ", forearmRightNervous=" + forearmRightNervous +
                ", bustNormal=" + bustNormal +
                ", bustNervous=" + bustNervous +
                ", hipline=" + hipline +
                ", waistline=" + waistline +
                ", waistHipRatio=" + waistHipRatio +
                ", twelveRunEvaluation=" + twelveRunEvaluation +
                ", thighLeftNormal=" + thighLeftNormal +
                ", thighRightNormal=" + thighRightNormal +
                ", thighLeftNervous=" + thighLeftNervous +
                ", thighRightNervous=" + thighRightNervous +
                ", calfLeftNormal = " + calfLeftNormal +
                ", calfRightNormal = " + calfRightNormal +
                ", calfLeftNervous = " + calfLeftNervous +
                ", calfRightNervous = " + calfRightNervous +
                ", bodyfat=" + bodyfat +
                ", pulse=" + pulse + ", bloodPressure = " + bloodPressure +
                ", maximalHeartRate=" + maximalHeartRate +
                ", properHeartRate=" + properHeartRate +
                ", cardiovascularHeartRate=" + cardiovascularHeartRate +
                ", restingHeartRate=" + restingHeartRate +
                ", dynamicHeartRate=" + dynamicHeartRate +
                ", supinationEvaluation=" + supinationEvaluation +
                ", myodynamiaChest=" + myodynamiaChest +
                ", myodynamiaAbdomen=" + myodynamiaAbdomen +
                ", myodynamiaBack=" + myodynamiaBack +
                ", bodyFlexibility=" + bodyFlexibility +
                ", backHealthAssessment=" + backHealthAssessment +
                ", basalMetabolicRate=" + basalMetabolicRate +
                ", bmlIndex=" + bmlIndex +
                ", personalTrainerAdvice=" + personalTrainerAdvice +
                ", superiorEvaluation=" + superiorEvaluation +
                ", physicalPic=" + physicalPic +
                ", evaluationDate=" + evaluationDate +
                ", personalId=" + personalId +
                ", buildDate=" + buildDate +
                ", clientId=" + clientId +
                ", result=" + result +
                ", id=" + id +
                "}";
    }
}
