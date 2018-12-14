package com.yj.dal.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
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
 * 门店通用设置表
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-21
 */
@TableName("fr_store_common")
public class FrStoreCommon extends Model<FrStoreCommon> {

    private static final long serialVersionUID = 1L;

    /**
     * 现有客户显示手机号码（0：显示 1：不显示）默认1
     */
    @TableField("xy_show_mobile")
    private Boolean xyShowMobile;
    /**
     * 显示外部卡号（0：显示 1：不显示）默认1
     */
    @TableField("show_card_num")
    private Boolean showCardNum;
    /**
     * 可否会员转让（0：不可转让 1：可以转让）默认1
     */
    @TableField("member_transfer")
    private Boolean memberTransfer;
    /**
     * 其他店办卡销售人员是否可修改（0：不可 1：可以）默认0
     */
    @TableField("xs_update")
    private Boolean xsUpdate;
    /**
     * 失效前X天红字显示
     */
    @TableField("red_disabled")
    private Integer redDisabled;
    /**
     * 生日前X天红字显示
     */
    @TableField("red_birthday")
    private Integer redBirthday;
    /**
     * 可否修改开卡时间（0：不可 1：可以）默认0
     */
    @TableField("open_update")
    private Boolean openUpdate;
    /**
     * 可否修改停卡开始时间（0：不可 1：可以）默认0
     */
    @TableField("stop_update")
    private Boolean stopUpdate;
    /**
     * 储值退款（0：禁止 1：允许按比例 2：允许全部）默认0
     */
    private Integer cztk;
    /**
     * 可否修改发票信息（0：不可 1：可以）默认0
     */
    @TableField("invoice_update")
    private Boolean invoiceUpdate;
    /**
     * 转让合并（0：禁止 1：允许按比例 2：允许按现金 3：允许全部）默认0
     */
    private Integer zrhb;
    /**
     * 潜在客户显示手机号码（0：显示 1：不显示）默认1
     */
    @TableField("qz_show_mobile")
    private Boolean qzShowMobile;
    /**
     * 可否修改潜在客户销售顾问（0：不可 1：可以）默认0
     */
    @TableField("qz_xsgw_update")
    private Boolean qzXsgwUpdate;
    /**
     * 潜在客户等级（用|分隔）
     */
    @TableField("qz_level")
    private String qzLevel;
    /**
     * 可否修改现有推荐人（0：不可 1：可以）默认0
     */
    @TableField("xytjr_update")
    private Boolean xytjrUpdate;
    /**
     * 可录入推荐人时间(0分钟则视为必须建立的时候录入)
     */
    @TableField("tjy_input_time")
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private Integer tjyInputTime;
    /**
     * 是否所有报表手机号显示（0：否 1：是）默认1
     */
    @TableField("show_mobile")
    private Boolean showMobile;
    /**
     * 会员储值金额可使用卡种（0：正常 1：停卡 2：冻结 3：已过期 4：未开卡 5：待补卡 6：历史）如有多个用逗号分隔
     */
    @TableField("czje_use")
    private String czjeUse;
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
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
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

    /**
     * 会员储值金额可使用卡种（0：正常 1：停卡 2：冻结 3：已过期 4：未开卡 5：待补卡 6：历史）
     */
    @TableField(exist = false)
    private String[] czjeUses={""};

    @TableField(exist = false)
    private String[] qzLevels;

    public String[] getCzjeUses() {
        return czjeUses;
    }

    public void setCzjeUses(String[] czjeUses) {
        this.czjeUses = czjeUses;
    }

    public String[] getQzLevels() {
        return qzLevels;
    }

    public void setQzLevels(String[] qzLevels) {
        this.qzLevels = qzLevels;
    }

    public Boolean getXyShowMobile() {
        return xyShowMobile;
    }

    public void setXyShowMobile(Boolean xyShowMobile) {
        this.xyShowMobile = xyShowMobile;
    }

    public Boolean getShowCardNum() {
        return showCardNum;
    }

    public void setShowCardNum(Boolean showCardNum) {
        this.showCardNum = showCardNum;
    }

    public Boolean getMemberTransfer() {
        return memberTransfer;
    }

    public void setMemberTransfer(Boolean memberTransfer) {
        this.memberTransfer = memberTransfer;
    }

    public Boolean getXsUpdate() {
        return xsUpdate;
    }

    public void setXsUpdate(Boolean xsUpdate) {
        this.xsUpdate = xsUpdate;
    }

    public Integer getRedDisabled() {
        return redDisabled;
    }

    public void setRedDisabled(Integer redDisabled) {
        this.redDisabled = redDisabled;
    }

    public Integer getRedBirthday() {
        return redBirthday;
    }

    public void setRedBirthday(Integer redBirthday) {
        this.redBirthday = redBirthday;
    }

    public Boolean getOpenUpdate() {
        return openUpdate;
    }

    public void setOpenUpdate(Boolean openUpdate) {
        this.openUpdate = openUpdate;
    }

    public Boolean getStopUpdate() {
        return stopUpdate;
    }

    public void setStopUpdate(Boolean stopUpdate) {
        this.stopUpdate = stopUpdate;
    }

    public Integer getCztk() {
        return cztk;
    }

    public void setCztk(Integer cztk) {
        this.cztk = cztk;
    }

    public Boolean getInvoiceUpdate() {
        return invoiceUpdate;
    }

    public void setInvoiceUpdate(Boolean invoiceUpdate) {
        this.invoiceUpdate = invoiceUpdate;
    }

    public Integer getZrhb() {
        return zrhb;
    }

    public void setZrhb(Integer zrhb) {
        this.zrhb = zrhb;
    }

    public Boolean getQzShowMobile() {
        return qzShowMobile;
    }

    public void setQzShowMobile(Boolean qzShowMobile) {
        this.qzShowMobile = qzShowMobile;
    }

    public Boolean getQzXsgwUpdate() {
        return qzXsgwUpdate;
    }

    public void setQzXsgwUpdate(Boolean qzXsgwUpdate) {
        this.qzXsgwUpdate = qzXsgwUpdate;
    }

    public String getQzLevel() {
        return qzLevel;
    }

    public void setQzLevel(String qzLevel) {
        this.qzLevel = qzLevel;
    }

    public Boolean getXytjrUpdate() {
        return xytjrUpdate;
    }

    public void setXytjrUpdate(Boolean xytjrUpdate) {
        this.xytjrUpdate = xytjrUpdate;
    }

    public Integer getTjyInputTime() {
        return tjyInputTime;
    }

    public void setTjyInputTime(Integer tjyInputTime) {
        this.tjyInputTime = tjyInputTime;
    }

    public Boolean getShowMobile() {
        return showMobile;
    }

    public void setShowMobile(Boolean showMobile) {
        this.showMobile = showMobile;
    }

    public String getCzjeUse() {
        return czjeUse;
    }

    public void setCzjeUse(String czjeUse) {
        this.czjeUse = czjeUse;
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
        return "FrStoreCommon{" +
        ", xyShowMobile=" + xyShowMobile +
        ", showCardNum=" + showCardNum +
        ", memberTransfer=" + memberTransfer +
        ", xsUpdate=" + xsUpdate +
        ", redDisabled=" + redDisabled +
        ", redBirthday=" + redBirthday +
        ", openUpdate=" + openUpdate +
        ", stopUpdate=" + stopUpdate +
        ", cztk=" + cztk +
        ", invoiceUpdate=" + invoiceUpdate +
        ", zrhb=" + zrhb +
        ", qzShowMobile=" + qzShowMobile +
        ", qzXsgwUpdate=" + qzXsgwUpdate +
        ", qzLevel=" + qzLevel +
        ", xytjrUpdate=" + xytjrUpdate +
        ", tjyInputTime=" + tjyInputTime +
        ", showMobile=" + showMobile +
        ", czjeUse=" + czjeUse +
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
