package com.yj.dal.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @ClassName CardConsumePladdsetDTO
 * @Auther: Sinyu
 * @Version 1.0
 */
public class CardConsumePladdsetDTO {

    //项目设置

    //fr_shop_card_consume_plset
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private String id;
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private String plsetId;
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private Boolean useSelect;
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private int useDays;
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private String startTimeAm;
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private String endTimeAm;
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private String startTimePm;
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private String endTimePm;
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

    public String getPlsetId() {
        return plsetId;
    }

    public void setPlsetId(String plsetId) {
        this.plsetId = plsetId;
    }

    public Boolean getUseSelect() {
        return useSelect;
    }

    public void setUseSelect(Boolean useSelect) {
        this.useSelect = useSelect;
    }

    public int getUseDays() {
        return useDays;
    }

    public void setUseDays(int useDays) {
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
        return "CardConsumePladdsetDTO{" +
                "id='" + id + '\'' +
                ", plsetId='" + plsetId + '\'' +
                ", useSelect=" + useSelect +
                ", useDays=" + useDays +
                ", startTimeAm='" + startTimeAm + '\'' +
                ", endTimeAm='" + endTimeAm + '\'' +
                ", startTimePm='" + startTimePm + '\'' +
                ", endTimePm='" + endTimePm + '\'' +
                ", cardConsumeId='" + cardConsumeId + '\'' +
                ", customerCode='" + customerCode + '\'' +
                '}';
    }
}
