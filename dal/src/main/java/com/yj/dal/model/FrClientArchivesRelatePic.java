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
 * 客户档案（皮肤、头发、塑性抗衰 档案）  内容保存图片
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
@TableName("fr_client_archives_relate_pic")
public class FrClientArchivesRelatePic extends Model<FrClientArchivesRelatePic> {

    private static final long serialVersionUID = 1L;

    /**
     * 区分ID
     */
    @TableField("archives_id")
    private String archivesId;
    /**
     * 图片类型：
皮肤档案：（1、实况图片）
头发档案：（1、实况图片）
塑性抗衰：（1、原形图片；2、之后图片）
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


    public String getArchivesId() {
        return archivesId;
    }

    public void setArchivesId(String archivesId) {
        this.archivesId = archivesId;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrClientArchivesRelatePic{" +
        ", archivesId=" + archivesId +
        ", picType=" + picType +
        ", picState=" + picState +
        ", picLink=" + picLink +
        ", createTime=" + createTime +
        ", id=" + id +
        "}";
    }
}
