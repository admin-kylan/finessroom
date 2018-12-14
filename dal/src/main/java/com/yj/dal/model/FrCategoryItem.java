package com.yj.dal.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import com.yj.dal.base.BaseModel;

/**
 * <p>
 * 场馆项目表
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-27
 */
@TableName("fr_category_item")
public class FrCategoryItem   extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 类别
     */
    private String category;
    /**
     * 使用项目名称
     */
    @TableField("item_name")
    private String itemName;
    /**
     * 客户场馆ID
     */
    @TableField("sdaduim_id")
    private String sdaduimId;
    @TableField(value = "create_time", fill = FieldFill.INSERT_UPDATE)
    private Date createTime;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    @TableField(value = "update_user_name", fill = FieldFill.INSERT_UPDATE)
    private String updateUserName;
    private String flag;
    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    private String createUserId;
    @TableField(value = "create_user_name", fill = FieldFill.INSERT)
    private String createUserName;
    @TableField("is_using")
    private Boolean isUsing;
    @TableField(value = "CustomerCode", fill = FieldFill.INSERT)
    private String CustomerCode;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private Integer type;
    @TableField(value = "update_user_id", fill = FieldFill.INSERT_UPDATE)
    private String updateUserId;

 //////////////////////////////   //不在数据库表中对象/////////////////////////////////

    /**
     * 会员卡类型-门店-场馆-项目关系表（消费门店会员卡权益设置）ID
     */
    @TableField(exist = false)
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
        private String consumeId;

    /**
     * 卡类型ID
     */
    @TableField(exist = false)
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private String cardTypeId;
    /**
     * 基础表门店ID
     */
    @TableField(exist = false)
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private String shopId;
    /**
     * 场馆项目ID
     */
    @TableField(exist = false)
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private String itemId;
    /**
     * 卡类型设置状态（1、连锁店通用设置；2、相关门店设置）
     */
    @TableField(exist = false)
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private Integer typeSetState;
    /**
     * 卡种金额
     */
    @TableField(exist = false)
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private String kzje;
    /**
     * 使用方式（0：免费通用 1：按X元/次 2：按X价X折消费）
     */
    @TableField(exist = false)
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private Integer usageMode;
    /**
     * 按X元/次消费
     */
    @TableField(exist = false)
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private Double modePrice;
    /**
     * 价格方式（0：市场价 1：促销价 2：会员价）默认0
     */
    @TableField(exist = false)
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private Integer modeWay;
    /**
     * 折扣
     */
    @TableField(exist = false)
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private Float modeDiscount;
    /**
     * 使用限制（0、无；1、开启）
     */
    @TableField(exist = false)
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private Boolean plSet;
    /**
     * 使用频率（0：年 1：月 2：天）
     */
    @TableField(exist = false)
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private Integer plLeft;
    /**
     * 最多使用时间
     */
    @TableField(exist = false)
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private Float plTime;
    /**
     * 使用频率（0：小时 1：分钟  2：次）
     */
    @TableField(exist = false)
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private Integer plRight;
    /**
     * 相关设置ID
     */
    @TableField(exist = false)
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private String plSetId;
    /**
     * 额外相关设置ID
     */
    @TableField(exist = false)
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private String plAddedSetId;



    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSdaduimId() {
        return sdaduimId;
    }

    public void setSdaduimId(String sdaduimId) {
        this.sdaduimId = sdaduimId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Boolean getUsing() {
        return isUsing;
    }

    public void setUsing(Boolean isUsing) {
        this.isUsing = isUsing;
    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }


    @Override
    public String toString() {
        return "FrCategoryItem{" +
                "category='" + category + '\'' +
                ", itemName='" + itemName + '\'' +
                ", sdaduimId='" + sdaduimId + '\'' +
                ", createTime=" + createTime +
                ", id='" + id + '\'' +
                ", updateUserName='" + updateUserName + '\'' +
                ", flag='" + flag + '\'' +
                ", createUserId='" + createUserId + '\'' +
                ", createUserName='" + createUserName + '\'' +
                ", isUsing=" + isUsing +
                ", CustomerCode='" + CustomerCode + '\'' +
                ", updateTime=" + updateTime +
                ", type=" + type +
                ", updateUserId='" + updateUserId + '\'' +
                ", cardTypeId='" + cardTypeId + '\'' +
                ", shopId='" + shopId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", typeSetState=" + typeSetState +
                ", kzje='" + kzje + '\'' +
                ", usageMode=" + usageMode +
                ", modePrice=" + modePrice +
                ", modeWay=" + modeWay +
                ", modeDiscount=" + modeDiscount +
                ", plSet=" + plSet +
                ", plLeft=" + plLeft +
                ", plTime=" + plTime +
                ", plRight=" + plRight +
                ", plSetId='" + plSetId + '\'' +
                ", plAddedSetId='" + plAddedSetId + '\'' +
                '}';
    }

    //////////////////////////////   //不在数据库表中对象/////////////////////////////////

    public String getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(String cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getTypeSetState() {
        return typeSetState;
    }

    public void setTypeSetState(Integer typeSetState) {
        this.typeSetState = typeSetState;
    }

    public String getKzje() {
        return kzje;
    }

    public void setKzje(String kzje) {
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

    public String getPlSetId() {
        return plSetId;
    }

    public void setPlSetId(String plSetId) {
        this.plSetId = plSetId;
    }

    public String getPlAddedSetId() {
        return plAddedSetId;
    }

    public void setPlAddedSetId(String plAddedSetId) {
        this.plAddedSetId = plAddedSetId;
    }
}
