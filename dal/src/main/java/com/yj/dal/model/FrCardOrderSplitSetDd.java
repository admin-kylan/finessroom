package com.yj.dal.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 所有会员卡分期付款订单，相关分期付款设置 详细
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-15
 */
@TableName("fr_card_order_split_set_dd")
public class FrCardOrderSplitSetDd extends Model<FrCardOrderSplitSetDd> {

    private static final long serialVersionUID = 1L;

    /**
     * 分期付款设置ID
     */
    @TableField("split_set_id")
    private String splitSetId;
    /**
     * 分期天数
     */
    @TableField("split_date")
    private Date splitDate;
    /**
     * 该分期需付（金额/比例）
     */
    @TableField("split_num")
    private Double splitNum;
    /**
     * 第几期
     */
    @TableField("split_order")
    private Integer splitOrder;
    /**
     * 状态（0：未还款 1 ：已还款）
     */
    private Integer status;
    /**
     * 对应的补余订单Id
     */
    @TableField("complement_id")
    private String complementId;
    @TableId(value = "id", type = IdType.UUID)
    private String id;


    public String getSplitSetId() {
        return splitSetId;
    }

    public void setSplitSetId(String splitSetId) {
        this.splitSetId = splitSetId;
    }

    public Date getSplitDate() {
        return splitDate;
    }

    public void setSplitDate(Date splitDate) {
        this.splitDate = splitDate;
    }

    public Double getSplitNum() {
        return splitNum;
    }

    public void setSplitNum(Double splitNum) {
        this.splitNum = splitNum;
    }

    public Integer getSplitOrder() {
        return splitOrder;
    }

    public void setSplitOrder(Integer splitOrder) {
        this.splitOrder = splitOrder;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getComplementId() {
        return complementId;
    }

    public void setComplementId(String complementId) {
        this.complementId = complementId;
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
        return "FrCardOrderSplitSetDd{" +
                ", splitSetId=" + splitSetId +
                ", splitDate=" + splitDate +
                ", splitNum=" + splitNum +
                ", splitOrder=" + splitOrder +
                ", status=" + status +
                ", id=" + id +
                ", complementId=" + complementId +
                "}";
    }
}
