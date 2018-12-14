package com.yj.dal.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 客户运动档案  内容保存
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-02
 */
@TableName("fr_client_motion_relate")
public class FrClientMotionRelate extends Model<FrClientMotionRelate> {

    private static final long serialVersionUID = 1L;

    /**
     * 运动档案 类型ID
     */
    @TableField("motion_type_id")
    private String motionTypeId;
    /**
     * 运动档案内容
     */
    @TableField("motion_type_content")
    private String motionTypeContent;
    /**
     * 类型(1、体型；2、运动爱好；3、喜欢舞蹈类型；4、健身目的；5、想锻炼部位；6、对私教的了解；7、运动历史状况；8、可以运动时间；9、希望运动类型/项目)
     */
    private Integer type;
    /**
     * 是否启用(0：不启用 1：启用）默认1
     */
    @TableField("is_using")
    private Boolean isUsing;
    @TableField("client_id")
    private String clientId;
    @TableId(value = "id", type = IdType.UUID)
    private String id;


    public String getMotionTypeId() {
        return motionTypeId;
    }

    public void setMotionTypeId(String motionTypeId) {
        this.motionTypeId = motionTypeId;
    }

    public String getMotionTypeContent() {
        return motionTypeContent;
    }

    public void setMotionTypeContent(String motionTypeContent) {
        this.motionTypeContent = motionTypeContent;
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

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
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
        return "FrClientMotionRelate{" +
        ", motionTypeId=" + motionTypeId +
        ", motionTypeContent=" + motionTypeContent +
        ", type=" + type +
        ", isUsing=" + isUsing +
        ", clientId=" + clientId +
        ", id=" + id +
        "}";
    }
}
