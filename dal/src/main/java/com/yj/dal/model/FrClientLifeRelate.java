package com.yj.dal.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 客户生活详情 内容保存
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-02
 */
@TableName("fr_client_life_relate")
public class FrClientLifeRelate extends Model<FrClientLifeRelate> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户ID
     */
    @TableField("client_id")
    private String clientId;
    /**
     * 生活详情 类型ID
     */
    @TableField("life_type_id")
    private String lifeTypeId;
    /**
     * 类型(1、性格描述；2、用餐习惯；3、饮食偏好；4、交通工具；5、阅读类型；6、喜欢香味；7、购物习惯；8、兴趣爱好)
     */
    private Integer type;
    /**
     * 是否启用(0：不启用 1：启用）默认1
     */
    @TableField("is_using")
    private Boolean isUsing;
    /**
     * 生活详情内容
     */
    @TableField("life_type_content")
    private String lifeTypeContent;
    @TableId(value = "id", type = IdType.UUID)
    private String id;


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getLifeTypeId() {
        return lifeTypeId;
    }

    public void setLifeTypeId(String lifeTypeId) {
        this.lifeTypeId = lifeTypeId;
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

    public String getLifeTypeContent() {
        return lifeTypeContent;
    }

    public void setLifeTypeContent(String lifeTypeContent) {
        this.lifeTypeContent = lifeTypeContent;
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
        return "FrClientLifeRelate{" +
        ", clientId=" + clientId +
        ", lifeTypeId=" + lifeTypeId +
        ", type=" + type +
        ", isUsing=" + isUsing +
        ", lifeTypeContent=" + lifeTypeContent +
        ", id=" + id +
        "}";
    }
}
