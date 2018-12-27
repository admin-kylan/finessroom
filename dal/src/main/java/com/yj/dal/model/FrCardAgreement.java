package com.yj.dal.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 会员卡协议号关系表
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-26
 */
@TableName("fr_card_agreement")
public class FrCardAgreement extends Model<FrCardAgreement> {

    private static final long serialVersionUID = 1L;

    /**
     * 协议规则ID
     */
    @TableField("agreement_id")
    private String agreementId;
    /**
     * 会员卡ID
     */
    @TableField("card_id")
    private String cardId;
    /**
     * 协议号
     */
    @TableField("agreement_no")
    private String agreementNo;
    /**
     * 是否启用(0：不启用 1：启用）默认1
     */
    @TableField("is_using")
    private Boolean isUsing;
    /**
     * 客户代码
     */
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    /**
     * 项目/课程 ID
     */
    @TableField("project_id")
    private String projectId;
    @TableId(value = "id", type = IdType.UUID)
    private String id;


    public String getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(String agreementId) {
        this.agreementId = agreementId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getAgreementNo() {
        return agreementNo;
    }

    public void setAgreementNo(String agreementNo) {
        this.agreementNo = agreementNo;
    }

    public Boolean getUsing() {
        return isUsing;
    }

    public void setUsing(Boolean isUsing) {
        this.isUsing = isUsing;
    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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
        return "FrCardAgreement{" +
                ", agreementId=" + agreementId +
                ", cardId=" + cardId +
                ", agreementNo=" + agreementNo +
                ", isUsing=" + isUsing +
                ", CustomerCode=" + CustomerCode +
                ", projectId=" + projectId +
                ", id=" + id +
                "}";
    }
}
