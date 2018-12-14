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
 * 
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-27
 */
@TableName("fr_shop_card_consume_pladdset")
public class FrShopCardConsumePladdset extends Model<FrShopCardConsumePladdset> {

    private static final long serialVersionUID = 1L;

    /**
     * 禁用/可以(0、禁止；1、可以)
     */
    @TableField("use_limit")
    private Boolean useLimit;
    /**
     * 开始日期
     */
    @TableField("use_day_star")
    private String useDayStar;
    /**
     * 结束日期
     */
    @TableField("use_day_end")
    private String useDayEnd;
    /**
     * 是否限时(0、是；1、否)
     */
    private Boolean UseSelect;
    /**
     * 开始时间
     */
    @TableField("use_time_star")
    private String useTimeStar;
    /**
     * 结束时间
     */
    @TableField("use_time_end")
    private String useTimeEnd;
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


    public Boolean getUseLimit() {
        return useLimit;
    }

    public void setUseLimit(Boolean useLimit) {
        this.useLimit = useLimit;
    }

    public String getUseDayStar() {
        return useDayStar;
    }

    public void setUseDayStar(String useDayStar) {
        this.useDayStar = useDayStar;
    }

    public String getUseDayEnd() {
        return useDayEnd;
    }

    public void setUseDayEnd(String useDayEnd) {
        this.useDayEnd = useDayEnd;
    }

    public Boolean getUseSelect() {
        return UseSelect;
    }

    public void setUseSelect(Boolean UseSelect) {
        this.UseSelect = UseSelect;
    }

    public String getUseTimeStar() {
        return useTimeStar;
    }

    public void setUseTimeStar(String useTimeStar) {
        this.useTimeStar = useTimeStar;
    }

    public String getUseTimeEnd() {
        return useTimeEnd;
    }

    public void setUseTimeEnd(String useTimeEnd) {
        this.useTimeEnd = useTimeEnd;
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
        return "FrShopCardConsumePladdset{" +
        ", useLimit=" + useLimit +
        ", useDayStar=" + useDayStar +
        ", useDayEnd=" + useDayEnd +
        ", UseSelect=" + UseSelect +
        ", useTimeStar=" + useTimeStar +
        ", useTimeEnd=" + useTimeEnd +
        ", CustomerCode=" + CustomerCode +
        ", cardConsumeId=" + cardConsumeId +
        ", id=" + id +
        "}";
    }
}
