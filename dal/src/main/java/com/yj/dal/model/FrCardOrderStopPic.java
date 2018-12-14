package com.yj.dal.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 会员卡 停止/冻结订单 图片
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-20
 */
@TableName("fr_card_order_stop_pic")
public class FrCardOrderStopPic extends Model<FrCardOrderStopPic> {

    private static final long serialVersionUID = 1L;

    /**
     * 图片类型(备用）
     */
    @TableField("pic_type")
    private Integer picType;
    /**
     * 是否在使用（0、未使用；1、使用中）
     */
    @TableField("pic_state")
    private Boolean picState;
    /**
     * 图片地址
     */
    @TableField("pic_link")
    private String picLink;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    @TableField("stop_id")
    private String stopId;


    public Integer getPicType() {
        return picType;
    }

    public void setPicType(Integer picType) {
        this.picType = picType;
    }

    public Boolean getPicState() {
        return picState;
    }

    public void setPicState(Boolean picState) {
        this.picState = picState;
    }

    public String getPicLink() {
        return picLink;
    }

    public void setPicLink(String picLink) {
        this.picLink = picLink;
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

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrCardOrderStopPic{" +
                ", picType=" + picType +
                ", picState=" + picState +
                ", picLink=" + picLink +
                ", createTime=" + createTime +
                ", id=" + id +
                ", stopId=" + stopId +
                "}";
    }
}
