package com.yj.dal.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 客户档案（皮肤、头发、塑性抗衰 档案）  内容保存
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
@TableName("fr_client_archives_relate")
public class FrClientArchivesRelate extends Model<FrClientArchivesRelate> {

    private static final long serialVersionUID = 1L;

    /**
     * 区分ID
     */
    @TableField("archives_id")
    private String archivesId;
    /**
     * 选择类型ID
     */
    @TableField("archives_type_id")
    private String archivesTypeId;
    /**
     * 填写内容
     */
    @TableField("archives_type_content")
    private String archivesTypeContent;
    /**
     * 类型： 
皮肤档案：(1、护理状态；2、皮肤类型；3、皮肤观察；4、额头；5、眼部；6、面颊；7、鼻子；8、嘴唇；9、颈部；10、胸部；11、腰部；12、面疱原因；13、黑斑原因；14、皱纹原因；15、过敏原因；16、毛孔粗大原因；17、维他命需要；18、过敏史；19、皮肤意愿；20、身体意愿；(以下为文本框内容类型)21、其他原因；22、使用过护肤品；23、正使用护肤品；24、其他说明；25、希望改善问题；26、建议及注意事项；27、护理建议及注意事项；28、产品搭配建议) 
头发档案：(1、头发颜色；2、颜色原因；3、头发种类；4、头发质地；5、头
     */
    private Integer type;
    /**
     * 是否启用(0：不启用 1：启用）默认1
     */
    @TableField("is_using")
    private Boolean isUsing;
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    public String getArchivesId() {
        return archivesId;
    }

    public void setArchivesId(String archivesId) {
        this.archivesId = archivesId;
    }

    public String getArchivesTypeId() {
        return archivesTypeId;
    }

    public void setArchivesTypeId(String archivesTypeId) {
        this.archivesTypeId = archivesTypeId;
    }

    public String getArchivesTypeContent() {
        return archivesTypeContent;
    }

    public void setArchivesTypeContent(String archivesTypeContent) {
        this.archivesTypeContent = archivesTypeContent;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrClientArchivesRelate{" +
        ", archivesId=" + archivesId +
        ", archivesTypeId=" + archivesTypeId +
        ", archivesTypeContent=" + archivesTypeContent +
        ", type=" + type +
        ", isUsing=" + isUsing +
        ", id=" + id +
        "}";
    }
}
