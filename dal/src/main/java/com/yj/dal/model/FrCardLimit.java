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
 * 会员卡使用限定
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-31
 */
@TableName("fr_card_limit")
public class FrCardLimit extends Model<FrCardLimit> {

    private static final long serialVersionUID = 1L;

    /**
     * 会员卡ID
     */
    @TableField("card_id")
    private String cardId;
    /**
     * 使用者姓名
     */
    @TableField("use_name")
    private String useName;
    /**
     * 使用者联系方式
     */
    @TableField("use_phone")
    private String usePhone;
    /**
     * 使用密码
     */
    @TableField("use_passwd")
    private String usePasswd;
    /**
     * 使用限定
     */
    @TableField("use_limit1")
    private String useLimit1;
    /**
     * 使用限定
     */
    @TableField("use_limit2")
    private String useLimit2;
    /**
     * 使用限定
     */
    @TableField("use_limit3")
    private String useLimit3;
    /**
     * 使用限定
     */
    @TableField("use_limit4")
    private String useLimit4;
    /**
     * 使用限定
     */
    @TableField("use_limit5")
    private String useLimit5;
    /**
     * 备注
     */
    private String note;
    /**
     * 备用
     */
    private String flag;
    /**
     * 类型(备用)
     */
    private Integer type;
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
    @TableId(value = "id", type = IdType.UUID)
    private String id;


    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public String getUsePhone() {
        return usePhone;
    }

    public void setUsePhone(String usePhone) {
        this.usePhone = usePhone;
    }

    public String getUsePasswd() {
        return usePasswd;
    }

    public void setUsePasswd(String usePasswd) {
        this.usePasswd = usePasswd;
    }

    public String getUseLimit1() {
        return useLimit1;
    }

    public void setUseLimit1(String useLimit1) {
        this.useLimit1 = useLimit1;
    }

    public String getUseLimit2() {
        return useLimit2;
    }

    public void setUseLimit2(String useLimit2) {
        this.useLimit2 = useLimit2;
    }

    public String getUseLimit3() {
        return useLimit3;
    }

    public void setUseLimit3(String useLimit3) {
        this.useLimit3 = useLimit3;
    }

    public String getUseLimit4() {
        return useLimit4;
    }

    public void setUseLimit4(String useLimit4) {
        this.useLimit4 = useLimit4;
    }

    public String getUseLimit5() {
        return useLimit5;
    }

    public void setUseLimit5(String useLimit5) {
        this.useLimit5 = useLimit5;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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
        return "FrCardLimit{" +
        ", cardId=" + cardId +
        ", useName=" + useName +
        ", usePhone=" + usePhone +
        ", usePasswd=" + usePasswd +
        ", useLimit1=" + useLimit1 +
        ", useLimit2=" + useLimit2 +
        ", useLimit3=" + useLimit3 +
        ", useLimit4=" + useLimit4 +
        ", useLimit5=" + useLimit5 +
        ", note=" + note +
        ", flag=" + flag +
        ", type=" + type +
        ", isUsing=" + isUsing +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", createUserName=" + createUserName +
        ", updateUserName=" + updateUserName +
        ", CustomerCode=" + CustomerCode +
        ", createUserId=" + createUserId +
        ", updateUserId=" + updateUserId +
        ", id=" + id +
        "}";
    }
}
