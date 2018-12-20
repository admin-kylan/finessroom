package com.yj.dal.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 会员卡购买前，卡权益时间设置保存
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-19
 */
@TableName("fr_shop_card_consume_plset")
public class FrShopCardConsumePlset extends Model<FrShopCardConsumePlset> {

    private static final long serialVersionUID = 1L;

    /**
     * 是否限时(0、是；1、否)
     */
    @TableField("use_select")
    private Boolean useSelect;
    /**
     * 星期几（1-7 ，1为星期一、7为星期日）
     */
    @TableField("use_days")
    private Integer useDays;
    /**
     * 早上开始时间
     */
    @TableField("start_time_am")
    private String startTimeAm;
    /**
     * 早上结束时间
     */
    @TableField("end_time_am")
    private String endTimeAm;
    /**
     * 下午开始时间
     */
    @TableField("start_time_pm")
    private String startTimePm;
    /**
     * 下午结束时间
     */
    @TableField("end_time_pm")
    private String endTimePm;
    /**
     * 客户代码
     */
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    /**
     * 会员卡权益设置相关外键
     */
    @TableField("card_consume_id")
    private String cardConsumeId;
    @TableId(value = "id", type = IdType.UUID)
    private String id;


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

    public String getCardConsumeId() {
        return cardConsumeId;
    }

    public void setCardConsumeId(String cardConsumeId) {
        this.cardConsumeId = cardConsumeId;
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
        return "FrShopCardConsumePlset{" +
                ", useSelect=" + useSelect +
                ", useDays=" + useDays +
                ", startTimeAm=" + startTimeAm +
                ", endTimeAm=" + endTimeAm +
                ", startTimePm=" + startTimePm +
                ", endTimePm=" + endTimePm +
                ", CustomerCode=" + CustomerCode +
                ", cardConsumeId=" + cardConsumeId +
                ", id=" + id +
                "}";
    }
}
