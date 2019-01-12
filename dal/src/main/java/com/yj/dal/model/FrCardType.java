package com.yj.dal.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 卡类型表
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-21
 */
@TableName("fr_card_type")
public class FrCardType extends Model<FrCardType> {

    private static final long serialVersionUID = 1L;

    /**
     * 父级ID（0：表示最顶级）
     */
    @TableField("p_id")
    private String pId;
    /**
     * 卡类型名称
     */
    @TableField("card_type_name")
    private String cardTypeName;
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
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private Integer usedNum;
    /**
     * 凭证方式（0：刷卡 1：密码 2：指纹 3：静脉 4：脸谱 5：二维码）多选，用逗号分隔，如：1,2
     */
    private String pzfs;
    /**
     * 原价
     */
    @TableField("original_price")
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private Double originalPrice;
    /**
     * 销售价
     */
    @TableField("sales_price")
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private Double salesPrice;
    /**
     * 卡权益（总次数、总时长、卡金额）
     */
    @TableField("total_num")
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private Integer totalNum;
    /**
     * 可停卡次数
     */
    @TableField("stop_num")
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private Integer stopNum;
    /**
     * 每次停卡最多天数
     */
    @TableField("stop_days")
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private Integer stopDays;
    /**
     * 总停卡天数
     */
    @TableField("total_days")
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private Integer totalDays;
    /**
         * 超出时间每天费用
     */
    @TableField("out_price")
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
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
     * 转让手续费（0或者空时，前台可以自行设置）
     */
    @TableField("change_transfer_fee")
    private Double changeTransferFee;

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
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private Double renewCommonPrice;
    /**
     * 普通会员续卡价格
     */
    @TableField("renew_level1_price")
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private Double renewLevel1Price;
    /**
     * 银卡会员续卡价格
     */
    @TableField("renew_level2_price")
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private Double renewLevel2Price;
    /**
     * 金卡会员续卡价格
     */
    @TableField("renew_level3_price")
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private Double renewLevel3Price;
    /**
     * 钻卡会员续卡价格
     */
    @TableField("renew_level4_price")
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private Double renewLevel4Price;
    /**
     * 前台是否可修改会员卡续卡价格（0：不可修改 1：可修改）默认1
     */
    @TableField("qt_can_renew")
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private Boolean qtCanRenew;
    /**
     * 可设置子卡数量
     */
    @TableField("zk_num")
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
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
    private Integer zkXffs;
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
     * 基础设置说明
     */
    @TableField("zk_jcszsm")
    private String zkJcszsm;
    /**
     * 卡类型设置状态（1、连锁店通用设置；2、相关门店设置）
     */
    @TableField("type_set_state")
    private Integer typeSetState;
    /**
     * 备用
     */
    private String flag;
    /**`
     * 卡种类型（1：时间卡 2：小时卡 3：次卡 4：储值卡 5：充值卡 6：折扣卡）
     */
    private Integer type;
    /**
     * 是否启用(0：不启用 1：启用）默认1
     */
    @TableField("is_using")
    private Boolean isUsing;
    /**
     * 创建人时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 创建人名称
     */
    @TableField(value = "create_user_name", fill = FieldFill.INSERT)
    private String createUserName;
    /**
     * 更新人名称
     */
    @TableField(value = "update_user_name", fill = FieldFill.INSERT_UPDATE)
    private String updateUserName;
    /**
     * 客户代码
     */
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    /**
     * 创建人ID
     */
    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    private String createUserId;
    /**
     * 更新人ID
     */
    @TableField(value = "update_user_id", fill = FieldFill.INSERT_UPDATE)
    private String updateUserId;
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 基础表门店ID（归属门店）
     */
    @TableField("shop_id")
    private String shopId;

    /**
     * 卡使用类型设置（1、连锁店通用设置；2、相关门店设置）
     */
    @TableField("card_type_set")
    private Boolean cardTypeSet;

    /**
     * 存储卡种列表
     */
    @TableField(exist = false)
    private List<FrCardType> frCardTypeList;

    public List<FrCardType> getFrCardTypeList() {
        return frCardTypeList;
    }

    public void setFrCardTypeList(List<FrCardType> frCardTypeList) {
        this.frCardTypeList = frCardTypeList;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName;
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

    public Integer getZkXffs() {
        return zkXffs;
    }

    public void setZkXffs(Integer zkXffs) {
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

    public String getZkJcszsm() {
        return zkJcszsm;
    }

    public void setZkJcszsm(String zkJcszsm) {
        this.zkJcszsm = zkJcszsm;
    }

    public Integer getTypeSetState() {
        return typeSetState;
    }

    public void setTypeSetState(Integer typeSetState) {
        this.typeSetState = typeSetState;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getUsing() {
        return isUsing;
    }

    public void setUsing(Boolean isUsing) {
        this.isUsing = isUsing;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getChangeTransferFee() {
        return changeTransferFee;
    }

    public void setChangeTransferFee(Double changeTransferFee) {
        this.changeTransferFee = changeTransferFee;
    }

    public Boolean getCardTypeSet() {
        return cardTypeSet;
    }

    public void setCardTypeSet(Boolean cardTypeSet) {
        this.cardTypeSet = cardTypeSet;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrCardType{" +
                ", pId=" + pId +
                ", cardTypeName=" + cardTypeName +
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
                ", zkJcszsm=" + zkJcszsm +
                ", typeSetState=" + typeSetState +
                ", flag=" + flag +
                ", type=" + type +
                ", isUsing=" + isUsing +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createUserName=" + createUserName +
                ", updateUserName=" + updateUserName +
                ", CustomerCode=" + CustomerCode +
                ", createUserId=" + createUserId +
                ", updateUserId=" + updateUserId +
                ", cardTypeSet=" + cardTypeSet +
                ", shopId=" + shopId +
                ", changeTransferFee=" + changeTransferFee +
                ", id=" + id +
                "}";
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
}
