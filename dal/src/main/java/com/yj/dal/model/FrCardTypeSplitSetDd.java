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
 * 会员卡分期付款设置 详细
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
@TableName("fr_card_type_split_set_dd")
public class FrCardTypeSplitSetDd extends Model<FrCardTypeSplitSetDd> {

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
    private Integer splitDate;
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
    @TableId(value = "id", type = IdType.UUID)
    private String id;


    public String getSplitSetId() {
        return splitSetId;
    }

    public void setSplitSetId(String splitSetId) {
        this.splitSetId = splitSetId;
    }

    public Integer getSplitDate() {
        return splitDate;
    }

    public void setSplitDate(Integer splitDate) {
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
        return "FrCardTypeSplitSetDd{" +
        ", splitSetId=" + splitSetId +
        ", splitDate=" + splitDate +
        ", splitNum=" + splitNum +
        ", splitOrder=" + splitOrder +
        ", id=" + id +
        "}";
    }
}
