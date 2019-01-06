package com.yj.dal.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 课程预约对象
 * </p>
 *
 * @author MP自动生成
 * @since 2019-01-05
 */
@TableName("fr_education_reserve_object")
public class FrEducationReserveObject extends Model<FrEducationReserveObject> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 课程设置表Id
     */
    @TableField("education_config_id")
    private String educationConfigId;
    /**
     * 新用户
     */
    @TableField("is_new_user")
    private Boolean isNewUser;
    /**
     * 普通会员
     */
    @TableField("is_general_member")
    private Boolean isGeneralMember;
    /**
     * 银卡会员
     */
    @TableField("is_silver_member")
    private Boolean isSilverMember;
    /**
     * 黄金会员
     */
    @TableField("is_gold_member")
    private Boolean isGoldMember;
    /**
     * 钻石会员
     */
    @TableField("is_diamond_member")
    private Boolean isDiamondMember;
    /**
     * 潜在会员
     */
    @TableField("is_latence_member")
    private Boolean isLatenceMember;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEducationConfigId() {
        return educationConfigId;
    }

    public void setEducationConfigId(String educationConfigId) {
        this.educationConfigId = educationConfigId;
    }

    public Boolean getNewUser() {
        return isNewUser;
    }

    public void setNewUser(Boolean isNewUser) {
        this.isNewUser = isNewUser;
    }

    public Boolean getGeneralMember() {
        return isGeneralMember;
    }

    public void setGeneralMember(Boolean isGeneralMember) {
        this.isGeneralMember = isGeneralMember;
    }

    public Boolean getSilverMember() {
        return isSilverMember;
    }

    public void setSilverMember(Boolean isSilverMember) {
        this.isSilverMember = isSilverMember;
    }

    public Boolean getGoldMember() {
        return isGoldMember;
    }

    public void setGoldMember(Boolean isGoldMember) {
        this.isGoldMember = isGoldMember;
    }

    public Boolean getDiamondMember() {
        return isDiamondMember;
    }

    public void setDiamondMember(Boolean isDiamondMember) {
        this.isDiamondMember = isDiamondMember;
    }

    public Boolean getLatenceMember() {
        return isLatenceMember;
    }

    public void setLatenceMember(Boolean isLatenceMember) {
        this.isLatenceMember = isLatenceMember;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrEducationReserveObject{" +
        ", id=" + id +
        ", educationConfigId=" + educationConfigId +
        ", isNewUser=" + isNewUser +
        ", isGeneralMember=" + isGeneralMember +
        ", isSilverMember=" + isSilverMember +
        ", isGoldMember=" + isGoldMember +
        ", isDiamondMember=" + isDiamondMember +
        ", isLatenceMember=" + isLatenceMember +
        "}";
    }
}
