package com.yj.dal.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 所有订单支付方式及金额
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-20
 */
@TableName("fr_card_order_pay_mode")
public class FrCardOrderPayMode extends Model<FrCardOrderPayMode> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @TableField("order_id")
    private String orderId;
    /**
     * 订单类型（1、新购；2、续卡；3、转卡；4、补卡（暂无）；5、储值；6、补余；7、退卡； 8、停卡；9、转让）
     */
    @TableField("order_type")
    private Integer orderType;
    /**
     *  支付方式（1、支付宝；2、刷卡；3、微信；4、现金；5、转账；6、花呗；7、其他）
     */
    @TableField("pay_mode")
    private Integer payMode;
    /**
     * 支付金额
     */
    @TableField("pay_price")
    private Double payPrice;
    /**
     * 订单状态（0：支出 1：收入）
     */
    private Boolean type;
    @TableId(value = "id", type = IdType.UUID)
    private String id;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getPayMode() {
        return payMode;
    }

    public void setPayMode(Integer payMode) {
        this.payMode = payMode;
    }

    public Double getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(Double payPrice) {
        this.payPrice = payPrice;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
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
        return "FrCardOrderPayMode{" +
                ", orderId=" + orderId +
                ", orderType=" + orderType +
                ", payMode=" + payMode +
                ", payPrice=" + payPrice +
                ", type=" + type +
                ", id=" + id +
                "}";
    }
}
