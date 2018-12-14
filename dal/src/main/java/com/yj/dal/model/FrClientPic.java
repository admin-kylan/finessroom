package com.yj.dal.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.yj.dal.base.BaseModel;

import java.io.Serializable;

/**
 * <p>
 * 客户图片表
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-21
 */
@TableName("fr_client_pic")
public class FrClientPic extends Model<FrClientPic> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户ID
     */
    @TableField("client_id")
    private String clientId;
    /**
     * 图片类型（1、头像）
     */
    @TableField("pic_type")
    private Integer picType;
    /**
     * 是否在使用（0、未使用；1、使用中）
     */
    @TableField("pic_state")
    private Boolean picState;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    @TableField("pic_link")
    private String picLink;


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

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

    public String getPicLink() {
        return picLink;
    }

    public void setPicLink(String picLink) {
        this.picLink = picLink;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrClientPic{" +
        ", clientId=" + clientId +
        ", picType=" + picType +
        ", picState=" + picState +
        ", id=" + id +
        ", createTime=" + createTime +
        ", picLink=" + picLink +
        "}";
    }
}
