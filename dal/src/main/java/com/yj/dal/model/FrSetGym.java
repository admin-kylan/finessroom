package com.yj.dal.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 健身馆设置保存
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-28
 */
@TableName("fr_set_gym")
public class FrSetGym extends Model<FrSetGym> {

    private static final long serialVersionUID = 1L;

    /**
     * 项目名称
     */
    @TableField("project_name")
    private String projectName;
    /**
     * 所属门店
     */
    @TableField("shop_name")
    private String shopName;
    /**
     * 门店id
     */
    @TableField("shop_id")
    private String shopId;
    /**
     * 单位
     */
    private String company;
    /**
     * 市场价格
     */
    @TableField("market_price")
    private Double marketPrice;
    /**
     * 促销价格
     */
    @TableField("promotion_price")
    private Double promotionPrice;
    /**
     * 会员价格
     */
    @TableField("member_price")
    private Double memberPrice;
    /**
     * 押金
     */
    private Double deposit;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 是否启用
     */
    @TableField("is_using")
    private Boolean isUsing;
    /**
     * 是否连锁店通用  0.否 1.是 默认否
     */
    @TableField("is_currency")
    private Boolean isCurrency;
    /**
     * 场馆id
     */
    @TableField("model_id")
    private String modelId;
    /**
     * 是否为门店默认项目 0.否 1.是 默认否  为是的时候项目不可删除,新建门店时插入
     */
    @TableField("is_Mel")
    private Boolean isMel;
    /**
     * 儿童馆才有此设置 是否限时  不限时为空 不为空的值为限时的时间
     */
    @TableField("isTime")
    private String isTime;

    /**
     * 儿童馆才有此设置  限时才有效 计消费一次为空 不为空为每小时计费的金额
     */
    @TableField("overtime")
    private String overtime;
    @TableField(value = "create_user_name", fill = FieldFill.INSERT)
    private String createUserName;
    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    private String createUserId;
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField(value = "update_user_id", fill = FieldFill.INSERT_UPDATE)
    private String updateUserId;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_user_name", fill = FieldFill.INSERT_UPDATE)
    private String updateUserName;

    public String getIsTime() {
        return isTime;
    }

    public void setIsTime(String isTime) {
        this.isTime = isTime;
    }

    public String getOvertime() {
        return overtime;
    }

    public void setOvertime(String overtime) {
        this.overtime = overtime;
    }


    public Boolean getMel() {
        return isMel;
    }

    public void setMel(Boolean mel) {
        isMel = mel;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Double getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(Double promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Double getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(Double memberPrice) {
        this.memberPrice = memberPrice;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean getUsing() {
        return isUsing;
    }

    public void setUsing(Boolean isUsing) {
        this.isUsing = isUsing;
    }

    public Boolean getCurrency() {
        return isCurrency;
    }

    public void setCurrency(Boolean isCurrency) {
        this.isCurrency = isCurrency;
    }


    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrSetGym{" +
                ", projectName=" + projectName +
                ", shopName=" + shopName +
                ", shopId=" + shopId +
                ", company=" + company +
                ", marketPrice=" + marketPrice +
                ", promotionPrice=" + promotionPrice +
                ", memberPrice=" + memberPrice +
                ", deposit=" + deposit +
                ", remarks=" + remarks +
                ", isUsing=" + isUsing +
                ", isCurrency=" + isCurrency +
                ", createUserName=" + createUserName +
                ", createUserId=" + createUserId +
                ", CustomerCode=" + CustomerCode +
                ", updateTime=" + updateTime +
                ", updateUserId=" + updateUserId +
                ", id=" + id +
                ", createTime=" + createTime +
                ", updateUserName=" + updateUserName +
                "}";
    }
}
