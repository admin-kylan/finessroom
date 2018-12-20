package com.yj.dal.model;

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
 * 会员卡类型-门店-场馆-项目关系表（消费门店会员卡权益设置）
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-29
 */
@TableName("fr_shop_ctype_consume")
public class FrShopCtypeConsume extends Model<FrShopCtypeConsume> {

    private static final long serialVersionUID = 1L;

    /**
     * 卡类型ID
     */
    @TableField("card_type_id")
    private String cardTypeId;
    /**
     * 基础表门店ID
     */
    @TableField("shop_id")
    private String shopId;
    /**
     * 基础表场馆ID
     */
    @TableField("sdaduim_id")
    private String sdaduimId;
    /**
     * 场馆项目ID
     */
    @TableField("item_id")
    private String itemId;
    /**
     * 卡类型设置状态（1、连锁店通用设置；2、相关门店设置）
     */
    @TableField("type_set_state")
    private Integer typeSetState;
    /**
     * 卡种金额是否为扣卡消费（0、否；1、是）
     */
    private Boolean kzje;
    /**
     * 使用方式（0：免费通用 1：按X元/次 2：按X价X折消费）
     */
    @TableField("usage_mode")
    private Integer usageMode;
    /**
     * 按X元/次消费
     */
    @TableField("mode_price")
    private Double modePrice;
    /**
     * 价格方式（0：市场价 1：促销价 2：会员价）默认0
     */
    @TableField("mode_way")
    private Integer modeWay;
    /**
     * 折扣
     */
    @TableField("mode_discount")
    private Float modeDiscount;
    /**
     * 使用限制（0、无；1、开启）
     */
    @TableField("pl_set")
    private Boolean plSet;
    /**
     * 使用频率（0：年 1：月 2：天）
     */
    @TableField("pl_left")
    private Integer plLeft;
    /**
     * 最多使用时间
     */
    @TableField("pl_time")
    private Float plTime;
    /**
     * 使用频率（0：小时 1：分钟  2：次）
     */
    @TableField("pl_right")
    private Integer plRight;
    /**
     * 客户代码
     */
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;

    /**
     *   每小时扣多少次( 次 )
     */
    @TableField("mode_time")
    private Double modeTime;

    /**
     *   每次扣多少小时(  小时 分钟  次 )
     */
    @TableField("mode_num")
    private String modeNum;

    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /* 自定义对象不再数据库中  不在数据库中*/
    @TableField(exist = false)
    private List<FrShopCtypeConsumePladdset> frShopCtypeConsumePladdsetList;
    @TableField(exist = false)
    private List<FrShopCtypeConsumePlset> frShopCtypeConsumePlsetList;

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

    public String getSdaduimId() {
        return sdaduimId;
    }

    public void setSdaduimId(String sdaduimId) {
        this.sdaduimId = sdaduimId;
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
        return CustomerCode;
    }

    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
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

    public Double getModeTime() {
        return modeTime;
    }

    public void setModeTime(Double modeTime) {
        this.modeTime = modeTime;
    }

    public String getModeNum() {
        return modeNum;
    }

    public void setModeNum(String modeNum) {
        this.modeNum = modeNum;
    }

    @Override
    public String toString() {
        return "FrShopCtypeConsume{" +
                "cardTypeId='" + cardTypeId + '\'' +
                ", shopId='" + shopId + '\'' +
                ", sdaduimId='" + sdaduimId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", typeSetState=" + typeSetState +
                ", kzje=" + kzje +
                ", usageMode=" + usageMode +
                ", modePrice=" + modePrice +
                ", modeWay=" + modeWay +
                ", modeDiscount=" + modeDiscount +
                ", plSet=" + plSet +
                ", plLeft=" + plLeft +
                ", plTime=" + plTime +
                ", plRight=" + plRight +
                ", CustomerCode='" + CustomerCode + '\'' +
                ", modeTime=" + modeTime +
                ", modeNum='" + modeNum + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public List<FrShopCtypeConsumePladdset> getFrShopCtypeConsumePladdsetList() {
        return frShopCtypeConsumePladdsetList;
    }

    public void setFrShopCtypeConsumePladdsetList(List<FrShopCtypeConsumePladdset> frShopCtypeConsumePladdsetList) {
        this.frShopCtypeConsumePladdsetList = frShopCtypeConsumePladdsetList;
    }

    public List<FrShopCtypeConsumePlset> getFrShopCtypeConsumePlsetList() {
        return frShopCtypeConsumePlsetList;
    }

    public void setFrShopCtypeConsumePlsetList(List<FrShopCtypeConsumePlset> frShopCtypeConsumePlsetList) {
        this.frShopCtypeConsumePlsetList = frShopCtypeConsumePlsetList;
    }
}
