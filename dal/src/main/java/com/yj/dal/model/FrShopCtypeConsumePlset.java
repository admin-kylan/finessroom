package com.yj.dal.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-09
 */
@TableName("fr_shop_ctype_consume_plset")
public class FrShopCtypeConsumePlset extends Model<FrShopCtypeConsumePlset> {

    private static final long serialVersionUID = 1L;

    /**
     * 是否限时(0、是；1、否)
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    @TableField("use_select")
    private Boolean useSelect;
    /**
     * 星期几（1-7 ，1为星期一、7为星期日）
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    @TableField("use_days")
    private Integer useDays;
    /**
     * 早上开始时间
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    @TableField("start_time_am")
    private String startTimeAm;
    /**
     * 早上结束时间
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    @TableField("end_time_am")
    private String endTimeAm;
    /**
     * 下午开始时间
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    @TableField("start_time_pm")
    private String startTimePm;
    /**
     * 下午结束时间
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    @TableField("end_time_pm")
    private String endTimePm;
    /**
     * 客户代码
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    /**
     * 卡权益设置相关外键
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    @TableField("consume_id")
    private String consumeId;
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    @TableId(value = "id", type = IdType.UUID)
    private String id;




    @TableField(exist = false)
    private List<FrShopCtypeConsumePlset> setTimeList;
    @TableField(exist = false)
    private String AM;
    @TableField(exist = false)
    private String   PM;

    public String getAM() {
        return AM;
    }

    public void setAM(String AM) {
        this.AM = AM;
    }

    public String getPM() {
        return PM;
    }

    public void setPM(String PM) {
        this.PM = PM;
    }

    public List<FrShopCtypeConsumePlset> getSetTimeList() {
        return setTimeList;
    }

    public void setSetTimeList(List<FrShopCtypeConsumePlset> setTimeList) {
        this.setTimeList = setTimeList;
    }

    public Boolean getUseSelect() {
        return useSelect;
    }

    public void setUseSelect(Boolean useSelect) {
        this.useSelect = useSelect;
    }

    public Integer getUseDays() {
        return useDays;
    }

    public void setUseDays(Integer useDays) {
        this.useDays = useDays;
    }

    public String getStartTimeAm() {
        return startTimeAm;
    }

    public void setStartTimeAm(String startTimeAm) {
        this.startTimeAm = startTimeAm;
    }

    public String getEndTimeAm() {
        return endTimeAm;
    }

    public void setEndTimeAm(String endTimeAm) {
        this.endTimeAm = endTimeAm;
    }

    public String getStartTimePm() {
        return startTimePm;
    }

    public void setStartTimePm(String startTimePm) {
        this.startTimePm = startTimePm;
    }

    public String getEndTimePm() {
        return endTimePm;
    }

    public void setEndTimePm(String endTimePm) {
        this.endTimePm = endTimePm;
    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }

    public String getConsumeId() {
        return consumeId;
    }

    public void setConsumeId(String consumeId) {
        this.consumeId = consumeId;
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
        return "FrShopCtypeConsumePlset{" +
        ", useSelect=" + useSelect +
        ", useDays=" + useDays +
        ", startTimeAm=" + startTimeAm +
        ", endTimeAm=" + endTimeAm +
        ", startTimePm=" + startTimePm +
        ", endTimePm=" + endTimePm +
        ", CustomerCode=" + CustomerCode +
        ", consumeId=" + consumeId +
        ", id=" + id +
        "}";
    }
}
