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
 * 客户档案（皮肤、头发、塑性抗衰 档案） 类型
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
@TableName("fr_client_archives_type")
public class FrClientArchivesType extends Model<FrClientArchivesType> {

    private static final long serialVersionUID = 1L;

    /**
     * 描述名称
     */
    @TableField("archives_name")
    private String archivesName;
    /**
     * 备用
     */
    private String flag;
    /**
     * 类型：
头发档案：(1、头发颜色；2、颜色原因；3、头发种类；4、头发质地；5、头发受损原因；6、烫发方式；7、护发频率；8、护理方式；9、喜欢头发类型；10、洗发水类型；)
皮肤档案：(1、护理状态；2、皮肤类型；3、皮肤观察；4、额头；5、眼部；6、面颊；7、鼻子；8、嘴唇；9、颈部；10、胸部；11、腰部；12、面疱原因；13、黑斑原因；14、皱纹原因；15、过敏原因；16、毛孔粗大原因；17、维他命需要；18、过敏史；19、皮肤意愿；20、身体意愿)
塑性抗衰：(1、脸型；2、鼻型；3、胸型；4、腰型；5、手型；6、腿型；7、臀型；8、过敏
     */
    @TableField("archives_type")
    private Integer archivesType;
    /**
     * 是否启用(0：不启用 1：启用）默认1
     */
    @TableField("is_using")
    private Boolean isUsing;
    /**
     * 创建人时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 创建人名称
     */
    @TableField(value = "create_user_name", fill = FieldFill.INSERT)
    private String createUserName;
    /**
     * 更新人名称
     */
    @TableField(value = "update_user_name", fill = FieldFill.INSERT_UPDATE)
    private String updateUserName;
    /**
     * 客户代码
     */
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    /**
     * 创建人ID
     */
    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    private String createUserId;
    /**
     * 更新人ID
     */
    @TableField(value = "update_user_id", fill = FieldFill.INSERT_UPDATE)
    private String updateUserId;
    /**
     * 类型(1、皮肤档案；2、头发档案；3、塑性抗衰)
     */
    private Integer type;
    @TableId(value = "id", type = IdType.UUID)
    private String id;


    public String getArchivesName() {
        return archivesName;
    }

    public void setArchivesName(String archivesName) {
        this.archivesName = archivesName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getArchivesType() {
        return archivesType;
    }

    public void setArchivesType(Integer archivesType) {
        this.archivesType = archivesType;
    }

    public Boolean getUsing() {
        return isUsing;
    }

    public void setUsing(Boolean isUsing) {
        this.isUsing = isUsing;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
        return "FrClientArchivesType{" +
        ", archivesName=" + archivesName +
        ", flag=" + flag +
        ", archivesType=" + archivesType +
        ", isUsing=" + isUsing +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", createUserName=" + createUserName +
        ", updateUserName=" + updateUserName +
        ", CustomerCode=" + CustomerCode +
        ", createUserId=" + createUserId +
        ", updateUserId=" + updateUserId +
        ", type=" + type +
        ", id=" + id +
        "}";
    }
}
