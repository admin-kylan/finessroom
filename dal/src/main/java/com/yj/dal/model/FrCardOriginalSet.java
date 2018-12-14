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
 * 会员卡购买前，后台设置保存
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-22
 */
@TableName("fr_card_original_set")
public class FrCardOriginalSet extends Model<FrCardOriginalSet> {

    private static final long serialVersionUID = 1L;

    /**
     * 会员卡ID
     */
    @TableField("card_id")
    private String cardId;
    /**
     * 使用期限（小时、天、周、月、年，中间用逗号分隔，如：12,天）
     */
    @TableField("service_life")
    private String serviceLife;
    /**
     * 刷卡间隔（秒、分钟、小时、天，中间用逗号分隔，如：10,分钟）
     */
    @TableField("swiping_interval")
    private String swipingInterval;
    /**
     * 刷卡次数（秒、分钟、小时、天，用逗号分隔，如：分钟,3）
     */
    @TableField("swiping_time")
    private String swipingTime;
    /**
     * 本卡使用人数
     */
    @TableField("used_num")
    private Integer usedNum;
    /**
     * 凭证方式（0：刷卡 1：密码 2：指纹 3：静脉 4：脸谱 5：二维码）多选，用逗号分隔，如：1,2
     */
    private String pzfs;
    /**
     * 原价
     */
    @TableField("original_price")
    private Double originalPrice;
    /**
     * 销售价
     */
    @TableField("sales_price")
    private Double salesPrice;
    /**
     * 卡权益（总次数、总时长、卡金额）
     */
    @TableField("total_num")
    private Integer totalNum;
    /**
     * 可停卡次数
     */
    @TableField("stop_num")
    private Integer stopNum;
    /**
     * 每次停卡最多天数
     */
    @TableField("stop_days")
    private Integer stopDays;
    /**
     * 总停卡天数
     */
    @TableField("total_days")
    private Integer totalDays;
    /**
     * 超出时间每天费用
     */
    @TableField("out_price")
    private Double outPrice;
    /**
     * 前台是否可修改实际销售价格（0：不可以 1：可以）默认0
     */
    @TableField("qt_xxjg_update")
    private Boolean qtXxjgUpdate;
    /**
     * 前台是否可修改销售权益有效期（0：不可以 1：可以）默认0
     */
    @TableField("qt_xxqy_update")
    private Boolean qtXxqyUpdate;
    /**
     * 前台是否可修改赠送权益（0：不可以 1：可以）默认0
     */
    @TableField("qt_zsqy_update")
    private Boolean qtZsqyUpdate;
    /**
     * 前台开卡后是否需要录入照片（0：不必须 1：必须）默认1
     */
    @TableField("qt_lrzp_update")
    private Boolean qtLrzpUpdate;
    /**
     * 前台可修改最低销售价格
     */
    @TableField("qt_zdxxjg")
    private Double qtZdxxjg;
    /**
     * 前台可赠送的最大权益
     */
    @TableField("qt_zdqy")
    private Double qtZdqy;
    /**
     * 是否可装让他人（0：不可转让 1：可转让）默认0
     */
    @TableField("can_transfer")
    private Boolean canTransfer;
    /**
     * 是否可转成其他卡种（0：不可转卡 1：可转卡）
     */
    @TableField("can_change_type")
    private Boolean canChangeType;
    /**
     * 转卡手续费（0或者空时，前台可以自行设置）
     */
    @TableField("transfer_fee")
    private Double transferFee;
    /**
     * 通用续卡价格
     */
    @TableField("renew_common_price")
    private Double renewCommonPrice;
    /**
     * 普通会员续卡价格
     */
    @TableField("renew_level1_price")
    private Double renewLevel1Price;
    /**
     * 银卡会员续卡价格
     */
    @TableField("renew_level2_price")
    private Double renewLevel2Price;
    /**
     * 金卡会员续卡价格
     */
    @TableField("renew_level3_price")
    private Double renewLevel3Price;
    /**
     * 钻卡会员续卡价格
     */
    @TableField("renew_level4_price")
    private Double renewLevel4Price;
    /**
     * 前台是否可修改会员卡续卡价格（0：不可修改 1：可修改）默认1
     */
    @TableField("qt_can_renew")
    private Boolean qtCanRenew;
    /**
     * 可设置子卡数量
     */
    @TableField("zk_num")
    private Integer zkNum;
    /**
     * 子卡持有方式（0：不限人员使用 1：指定人员持有）默认0
     */
    @TableField("zk_cyfs")
    private Integer zkCyfs;
    /**
     * 子卡停卡权益（0：子卡与主卡同步停卡 1：子卡独立停卡）默认1
     */
    @TableField("zk_tkqy")
    private Integer zkTkqy;
    /**
     * 子卡消费方式（0：随主卡同步附属使用 1：子卡与主卡分割额度使用 2：子卡与主卡享有同等权益）
     */
    @TableField("zk_xffs")
    private String zkXffs;
    /**
     * 子卡处理方式（0：子卡存在 1：独立分卡）默认0
     */
    @TableField("zk_clfs")
    private Integer zkClfs;
    /**
     * 最长时间间隔 单位分钟
     */
    @TableField("zk_sjjg")
    private Integer zkSjjg;
    /**
     * 卡系列
     */
    @TableField("card_flag_id")
    private String cardFlagId;
    /**
     * 卡名称
     */
    @TableField("card_type_name")
    private String cardTypeName;
    /**
     * 种类型（1：时间卡 2：小时卡 3：次卡 4：储值卡 5：充值卡 6：折扣卡）
     */
    @TableField("card_type")
    private Integer cardType;
    /**
     * 卡类型ID
     */
    @TableField("card_type_id")
    private String cardTypeId;
    /**
     * 客户代码
     */
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    /**
     * 转让手续费（0或者空时，前台可以自行设置）
     */
    @TableField("change_transfer_fee")
    private Double changeTransferFee;
    @TableId(value = "id", type = IdType.UUID)
    private String id;


    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getServiceLife() {
        return serviceLife;
    }

    public void setServiceLife(String serviceLife) {
        this.serviceLife = serviceLife;
    }

    public String getSwipingInterval() {
        return swipingInterval;
    }

    public void setSwipingInterval(String swipingInterval) {
        this.swipingInterval = swipingInterval;
    }

    public String getSwipingTime() {
        return swipingTime;
    }

    public void setSwipingTime(String swipingTime) {
        this.swipingTime = swipingTime;
    }

    public Integer getUsedNum() {
        return usedNum;
    }

    public void setUsedNum(Integer usedNum) {
        this.usedNum = usedNum;
    }

    public String getPzfs() {
        return pzfs;
    }

    public void setPzfs(String pzfs) {
        this.pzfs = pzfs;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(Double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getStopNum() {
        return stopNum;
    }

    public void setStopNum(Integer stopNum) {
        this.stopNum = stopNum;
    }

    public Integer getStopDays() {
        return stopDays;
    }

    public void setStopDays(Integer stopDays) {
        this.stopDays = stopDays;
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    public Double getOutPrice() {
        return outPrice;
    }

    public void setOutPrice(Double outPrice) {
        this.outPrice = outPrice;
    }

    public Boolean getQtXxjgUpdate() {
        return qtXxjgUpdate;
    }

    public void setQtXxjgUpdate(Boolean qtXxjgUpdate) {
        this.qtXxjgUpdate = qtXxjgUpdate;
    }

    public Boolean getQtXxqyUpdate() {
        return qtXxqyUpdate;
    }

    public void setQtXxqyUpdate(Boolean qtXxqyUpdate) {
        this.qtXxqyUpdate = qtXxqyUpdate;
    }

    public Boolean getQtZsqyUpdate() {
        return qtZsqyUpdate;
    }

    public void setQtZsqyUpdate(Boolean qtZsqyUpdate) {
        this.qtZsqyUpdate = qtZsqyUpdate;
    }

    public Boolean getQtLrzpUpdate() {
        return qtLrzpUpdate;
    }

    public void setQtLrzpUpdate(Boolean qtLrzpUpdate) {
        this.qtLrzpUpdate = qtLrzpUpdate;
    }

    public Double getQtZdxxjg() {
        return qtZdxxjg;
    }

    public void setQtZdxxjg(Double qtZdxxjg) {
        this.qtZdxxjg = qtZdxxjg;
    }

    public Double getQtZdqy() {
        return qtZdqy;
    }

    public void setQtZdqy(Double qtZdqy) {
        this.qtZdqy = qtZdqy;
    }

    public Boolean getCanTransfer() {
        return canTransfer;
    }

    public void setCanTransfer(Boolean canTransfer) {
        this.canTransfer = canTransfer;
    }

    public Boolean getCanChangeType() {
        return canChangeType;
    }

    public void setCanChangeType(Boolean canChangeType) {
        this.canChangeType = canChangeType;
    }

    public Double getTransferFee() {
        return transferFee;
    }

    public void setTransferFee(Double transferFee) {
        this.transferFee = transferFee;
    }

    public Double getRenewCommonPrice() {
        return renewCommonPrice;
    }

    public void setRenewCommonPrice(Double renewCommonPrice) {
        this.renewCommonPrice = renewCommonPrice;
    }

    public Double getRenewLevel1Price() {
        return renewLevel1Price;
    }

    public void setRenewLevel1Price(Double renewLevel1Price) {
        this.renewLevel1Price = renewLevel1Price;
    }

    public Double getRenewLevel2Price() {
        return renewLevel2Price;
    }

    public void setRenewLevel2Price(Double renewLevel2Price) {
        this.renewLevel2Price = renewLevel2Price;
    }

    public Double getRenewLevel3Price() {
        return renewLevel3Price;
    }

    public void setRenewLevel3Price(Double renewLevel3Price) {
        this.renewLevel3Price = renewLevel3Price;
    }

    public Double getRenewLevel4Price() {
        return renewLevel4Price;
    }

    public void setRenewLevel4Price(Double renewLevel4Price) {
        this.renewLevel4Price = renewLevel4Price;
    }

    public Boolean getQtCanRenew() {
        return qtCanRenew;
    }

    public void setQtCanRenew(Boolean qtCanRenew) {
        this.qtCanRenew = qtCanRenew;
    }

    public Integer getZkNum() {
        return zkNum;
    }

    public void setZkNum(Integer zkNum) {
        this.zkNum = zkNum;
    }

    public Integer getZkCyfs() {
        return zkCyfs;
    }

    public void setZkCyfs(Integer zkCyfs) {
        this.zkCyfs = zkCyfs;
    }

    public Integer getZkTkqy() {
        return zkTkqy;
    }

    public void setZkTkqy(Integer zkTkqy) {
        this.zkTkqy = zkTkqy;
    }

    public String getZkXffs() {
        return zkXffs;
    }

    public void setZkXffs(String zkXffs) {
        this.zkXffs = zkXffs;
    }

    public Integer getZkClfs() {
        return zkClfs;
    }

    public void setZkClfs(Integer zkClfs) {
        this.zkClfs = zkClfs;
    }

    public Integer getZkSjjg() {
        return zkSjjg;
    }

    public void setZkSjjg(Integer zkSjjg) {
        this.zkSjjg = zkSjjg;
    }

    public String getCardFlagId() {
        return cardFlagId;
    }

    public void setCardFlagId(String cardFlagId) {
        this.cardFlagId = cardFlagId;
    }

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(String cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }

    public Double getChangeTransferFee() {
        return changeTransferFee;
    }

    public void setChangeTransferFee(Double changeTransferFee) {
        this.changeTransferFee = changeTransferFee;
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
        return "FrCardOriginalSet{" +
                ", cardId=" + cardId +
                ", serviceLife=" + serviceLife +
                ", swipingInterval=" + swipingInterval +
                ", swipingTime=" + swipingTime +
                ", usedNum=" + usedNum +
                ", pzfs=" + pzfs +
                ", originalPrice=" + originalPrice +
                ", salesPrice=" + salesPrice +
                ", totalNum=" + totalNum +
                ", stopNum=" + stopNum +
                ", stopDays=" + stopDays +
                ", totalDays=" + totalDays +
                ", outPrice=" + outPrice +
                ", qtXxjgUpdate=" + qtXxjgUpdate +
                ", qtXxqyUpdate=" + qtXxqyUpdate +
                ", qtZsqyUpdate=" + qtZsqyUpdate +
                ", qtLrzpUpdate=" + qtLrzpUpdate +
                ", qtZdxxjg=" + qtZdxxjg +
                ", qtZdqy=" + qtZdqy +
                ", canTransfer=" + canTransfer +
                ", canChangeType=" + canChangeType +
                ", transferFee=" + transferFee +
                ", renewCommonPrice=" + renewCommonPrice +
                ", renewLevel1Price=" + renewLevel1Price +
                ", renewLevel2Price=" + renewLevel2Price +
                ", renewLevel3Price=" + renewLevel3Price +
                ", renewLevel4Price=" + renewLevel4Price +
                ", qtCanRenew=" + qtCanRenew +
                ", zkNum=" + zkNum +
                ", zkCyfs=" + zkCyfs +
                ", zkTkqy=" + zkTkqy +
                ", zkXffs=" + zkXffs +
                ", zkClfs=" + zkClfs +
                ", zkSjjg=" + zkSjjg +
                ", cardFlagId=" + cardFlagId +
                ", cardTypeName=" + cardTypeName +
                ", cardType=" + cardType +
                ", cardTypeId=" + cardTypeId +
                ", CustomerCode=" + CustomerCode +
                ", changeTransferFee=" + changeTransferFee +
                ", id=" + id +
                "}";
    }
}
