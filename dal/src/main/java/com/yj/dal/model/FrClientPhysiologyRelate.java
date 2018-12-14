package com.yj.dal.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 客户生理状况 内容保存
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-08
 */
@TableName("fr_client_physiology_relate")
public class FrClientPhysiologyRelate extends Model<FrClientPhysiologyRelate> {

    private static final long serialVersionUID = 1L;

    /**
     * 生理状况 类型ID
     */
    @TableField("physiology_type_id")
    private String physiologyTypeId;
    /**
     * 生理状况内容
     */
    @TableField("physiology_type_content")
    private String physiologyTypeContent;
    /**
     * 类型(1、医疗史；2、家庭疾病史 ;3、体型；4、健康状况；5、身体状况；6、以往病史；7、曾服药物；8、荷尔蒙情况；9、血压状况；10、月经状况；）
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


    public String getPhysiologyTypeId() {
        return physiologyTypeId;
    }

    public void setPhysiologyTypeId(String physiologyTypeId) {
        this.physiologyTypeId = physiologyTypeId;
    }

    public String getPhysiologyTypeContent() {
        return physiologyTypeContent;
    }

    public void setPhysiologyTypeContent(String physiologyTypeContent) {
        this.physiologyTypeContent = physiologyTypeContent;
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
        return "FrClientPhysiologyRelate{" +
        ", physiologyTypeId=" + physiologyTypeId +
        ", physiologyTypeContent=" + physiologyTypeContent +
        ", type=" + type +
        ", isUsing=" + isUsing +
        ", id=" + id +
        ", clientId=" + clientId +
        "}";
    }
}
