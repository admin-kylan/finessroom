package com.yj.dal.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 客户工作行业 内容保存
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-08
 */
@TableName("fr_client_work_relate")
public class FrClientWorkRelate extends Model<FrClientWorkRelate> {

    private static final long serialVersionUID = 1L;

    /**
     * 描述内容，存储（运动档案 类型ID，以，分割）
     */
    @TableField("work_type_id")
    private String workTypeId;
    /**
     * 描述内容，存储（运动档案 类型ID，以，分割）
     */
    @TableField("work_type_content")
    private String workTypeContent;
    /**
     * 类型(1、从事行业；2、工作性质；3、工作情况；4、工作历史）
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


    public String getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(String workTypeId) {
        this.workTypeId = workTypeId;
    }

    public String getWorkTypeContent() {
        return workTypeContent;
    }

    public void setWorkTypeContent(String workTypeContent) {
        this.workTypeContent = workTypeContent;
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
        return "FrClientWorkRelate{" +
        ", workTypeId=" + workTypeId +
        ", workTypeContent=" + workTypeContent +
        ", type=" + type +
        ", isUsing=" + isUsing +
        ", id=" + id +
        ", clientId=" + clientId +
        "}";
    }
}
