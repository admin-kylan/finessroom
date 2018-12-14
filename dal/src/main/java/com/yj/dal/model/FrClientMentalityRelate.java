package com.yj.dal.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 客户心理状况 内容保存
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-08
 */
@TableName("fr_client_mentality_relate")
public class FrClientMentalityRelate extends Model<FrClientMentalityRelate> {

    private static final long serialVersionUID = 1L;

    /**
     * 心理状况  类型ID
     */
    @TableField("mentality_type_id")
    private String mentalityTypeId;
    /**
     * 心理状况内容
     */
    @TableField("mentality_type_content")
    private String mentalityTypeContent;
    /**
     * 类型(1、家庭生活状况；2、睡眠情况；3、睡眠情况；4、情绪状况）
     */
    private Integer type;
    /**
     * 是否启用(0：不启用 1：启用）默认1
     */
    @TableField("is_using")
    private Boolean isUsing;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    @TableField("client_id")
    private String clientId;


    public String getMentalityTypeId() {
        return mentalityTypeId;
    }

    public void setMentalityTypeId(String mentalityTypeId) {
        this.mentalityTypeId = mentalityTypeId;
    }

    public String getMentalityTypeContent() {
        return mentalityTypeContent;
    }

    public void setMentalityTypeContent(String mentalityTypeContent) {
        this.mentalityTypeContent = mentalityTypeContent;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrClientMentalityRelate{" +
        ", mentalityTypeId=" + mentalityTypeId +
        ", mentalityTypeContent=" + mentalityTypeContent +
        ", type=" + type +
        ", isUsing=" + isUsing +
        ", id=" + id +
        ", clientId=" + clientId +
        "}";
    }
}
