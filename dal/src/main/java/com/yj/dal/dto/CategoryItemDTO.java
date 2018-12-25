package com.yj.dal.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yj.dal.model.FrShopCtypeConsumePladdset;
import com.yj.dal.model.FrShopCtypeConsumePlset;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 欧俊俊
 * @create 2018/9/12
 */
public class CategoryItemDTO {
    /**
     * 门店id
     */
    private String id;
    private Boolean checked;
    private String itemName;
    private Date createTime;
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private String sccId;   //fr_shop_ctype_consume 表id
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private Boolean kzje;
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private Integer usageMode;
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private Double modePrice;
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private Integer modeWay;
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private Float modeDiscount;
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private Boolean plSet;
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private Integer plLeft;
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private Float plTime;
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private Integer plRight;
//    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
//    private String plSetId;
//    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
//    private String plAddedSetId;
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private String customerCode;
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private Boolean setChecked;

    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private String modeNum;
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private Double modeTime;
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)



    private  List<FrShopCtypeConsumePladdset > cardConsumePladdsetDTO;
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private  List<FrShopCtypeConsumePlset> cardConsumePlsetDTO;




    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSccId() {
        return sccId;
    }

    public void setSccId(String sccId) {
        this.sccId = sccId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Boolean getKzje() {
        return kzje;
    }

    public void setKzje(Boolean kzje) {
        this.kzje = kzje;
    }

    public Integer getUsageMode() {
        return usageMode;
    }

    public void setUsageMode(Integer usageMode) {
        this.usageMode = usageMode;
    }

    public Double getModePrice() {
        return modePrice;
    }

    public void setModePrice(Double modePrice) {
        this.modePrice = modePrice;
    }

    public Integer getModeWay() {
        return modeWay;
    }

    public void setModeWay(Integer modeWay) {
        this.modeWay = modeWay;
    }

    public Float getModeDiscount() {
        return modeDiscount;
    }

    public void setModeDiscount(Float modeDiscount) {
        this.modeDiscount = modeDiscount;
    }

    public Boolean getPlSet() {
        return plSet;
    }

    public void setPlSet(Boolean plSet) {
        this.plSet = plSet;
    }

    public Integer getPlLeft() {
        return plLeft;
    }

    public void setPlLeft(Integer plLeft) {
        this.plLeft = plLeft;
    }

    public Float getPlTime() {
        return plTime;
    }

    public void setPlTime(Float plTime) {
        this.plTime = plTime;
    }

    public Integer getPlRight() {
        return plRight;
    }

    public void setPlRight(Integer plRight) {
        this.plRight = plRight;
    }


    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Boolean getSetChecked() {
        return setChecked;
    }

    public void setSetChecked(Boolean setChecked) {
        this.setChecked = setChecked;
    }

    public String getModeNum() {
        return modeNum;
    }

    public void setModeNum(String modeNum) {
        this.modeNum = modeNum;
    }

    public Double getModeTime() {
        return modeTime;
    }

    public void setModeTime(Double modeTime) {
        this.modeTime = modeTime;
    }

    public List<FrShopCtypeConsumePladdset> getCardConsumePladdsetDTO() {
        return cardConsumePladdsetDTO;
    }

    public void setCardConsumePladdsetDTO(List<FrShopCtypeConsumePladdset> cardConsumePladdsetDTO) {
        this.cardConsumePladdsetDTO = cardConsumePladdsetDTO;
    }

    public List<FrShopCtypeConsumePlset> getCardConsumePlsetDTO() {
        return cardConsumePlsetDTO;
    }

    public void setCardConsumePlsetDTO(List<FrShopCtypeConsumePlset> cardConsumePlsetDTO) {
        this.cardConsumePlsetDTO = cardConsumePlsetDTO;
    }
}
