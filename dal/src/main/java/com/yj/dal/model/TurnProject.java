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
 * 增购转让表

 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-27
 */
@TableName("TurnProject")
public class TurnProject extends Model<TurnProject> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户代码 （方便客户导出资料）
     */
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /**
     * 旧卡Id
     */
    private String OldCardId;
    /**
     * 新卡Id
     */
    private String NewCardId;
    /**
     * 增购项目ID
     */
    private String AddProjectId;
    /**
     * 创建时间
     */
    private Double CreateTime;
    /**
     * 创建人ID
     */
    private String CreateId;
    /**
     * 创建人姓名
     */
    private String CreateName;
    /**
     * 0?现金?1?刷卡?2?微信?3?支付宝
     */
    private Integer PayType;
    /**
     * 支付金额
     */
    private Double PayMoney;
    /**
     * 手续费
     */
    private Double Fee;
    /**
     * 算业绩的门店
     */
    private String PerShopId;


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

    public String getOldCardId() {
        return OldCardId;
    }

    public void setOldCardId(String OldCardId) {
        this.OldCardId = OldCardId;
    }

    public String getNewCardId() {
        return NewCardId;
    }

    public void setNewCardId(String NewCardId) {
        this.NewCardId = NewCardId;
    }

    public String getAddProjectId() {
        return AddProjectId;
    }

    public void setAddProjectId(String AddProjectId) {
        this.AddProjectId = AddProjectId;
    }

    public Double getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Double CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getCreateId() {
        return CreateId;
    }

    public void setCreateId(String CreateId) {
        this.CreateId = CreateId;
    }

    public String getCreateName() {
        return CreateName;
    }

    public void setCreateName(String CreateName) {
        this.CreateName = CreateName;
    }

    public Integer getPayType() {
        return PayType;
    }

    public void setPayType(Integer PayType) {
        this.PayType = PayType;
    }

    public Double getPayMoney() {
        return PayMoney;
    }

    public void setPayMoney(Double PayMoney) {
        this.PayMoney = PayMoney;
    }

    public Double getFee() {
        return Fee;
    }

    public void setFee(Double Fee) {
        this.Fee = Fee;
    }

    public String getPerShopId() {
        return PerShopId;
    }

    public void setPerShopId(String PerShopId) {
        this.PerShopId = PerShopId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TurnProject{" +
        ", CustomerCode=" + CustomerCode +
        ", id=" + id +
        ", OldCardId=" + OldCardId +
        ", NewCardId=" + NewCardId +
        ", AddProjectId=" + AddProjectId +
        ", CreateTime=" + CreateTime +
        ", CreateId=" + CreateId +
        ", CreateName=" + CreateName +
        ", PayType=" + PayType +
        ", PayMoney=" + PayMoney +
        ", Fee=" + Fee +
        ", PerShopId=" + PerShopId +
        "}";
    }
}
