package com.yj.dal.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @ClassName CardConsumePlsetDTO
 * @Auther: Sinyu
 * @Version 1.0
 */
public class CardConsumePlsetDTO {

    //项目设置
    // fr_shop_card_consume_pladdset
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private String id;
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private Boolean useLimit;//禁用/可以(0、禁止；1、可以)
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private String useDayStar;   //开始日期
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private String useDayEnd; //结束日期
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private Boolean UseSelect;//是否限时(0、是；1、否)
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private String useTimeStar;
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private String useTimeEnd;
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private String cardConsumeId;//会员卡权益设置相关外键
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private String customerCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public void setUseSelect(Boolean useSelect) {
        UseSelect = useSelect;
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

    public String getCardConsumeId() {
        return cardConsumeId;
    }

    public void setCardConsumeId(String cardConsumeId) {
        this.cardConsumeId = cardConsumeId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    @Override
    public String toString() {
        return "CardConsumePlsetDTO{" +
                "id='" + id + '\'' +
                ", useLimit=" + useLimit +
                ", useDayStar='" + useDayStar + '\'' +
                ", useDayEnd='" + useDayEnd + '\'' +
                ", UseSelect=" + UseSelect +
                ", useTimeStar='" + useTimeStar + '\'' +
                ", useTimeEnd='" + useTimeEnd + '\'' +
                ", cardConsumeId='" + cardConsumeId + '\'' +
                ", customerCode='" + customerCode + '\'' +
                '}';
    }
}
