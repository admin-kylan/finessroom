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
 * 子卡
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-30
 */
@TableName("fr_child_card")
public class FrChildCard extends Model<FrChildCard> {

    private static final long serialVersionUID = 1L;

    /**
     * 卡号
     */
    @TableField("card_no")
    private String cardNo;
    /**
     * 门店id
     */
    @TableField("shop_id")
    private String shopId;
    /**
     * 父卡会员卡ID
     */
    @TableField("parent_card_id")
    private String parentCardId;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 分享权益
     */
    @TableField("share_benefit")
    private Integer shareBenefit;
    /**
     * 卡处理
     */
    @TableField("card_handle")
    private Integer cardHandle;
    /**
     * 操作人id
     */
    @TableField("handle_personal_id")
    private String handlePersonalId;
    /**
     * 持有人姓名
     */
    @TableField("user_name")
    private String userName;
    /**
     * 外部卡号
     */
    @TableField("out_card_no")
    private String outCardNo;
    /**
     * 子卡状态：0，禁用  1 正常  2 退卡
     */
    private Integer type;
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
     * 销售人员id
     */
    @TableField("sale_man_id")
    private String saleManId;
    /**
     * 独立分享的权益
     */
    @TableField("share_num")
    private Integer shareNum;
    /**
     * 子卡持卡人客户ID
     */
    @TableField("child_client_id")
    private String childClientId;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField(value = "create_user_name", fill = FieldFill.INSERT)
    private String createUserName;
    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    private String createUserId;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    private String flag;
    @TableField(value = "update_user_name", fill = FieldFill.INSERT_UPDATE)
    private String updateUserName;
    @TableField(value = "update_user_id", fill = FieldFill.INSERT_UPDATE)
    private String updateUserId;
    @TableId(value = "id", type = IdType.UUID)
    private String id;


    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getParentCardId() {
        return parentCardId;
    }

    public void setParentCardId(String parentCardId) {
        this.parentCardId = parentCardId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getShareBenefit() {
        return shareBenefit;
    }

    public void setShareBenefit(Integer shareBenefit) {
        this.shareBenefit = shareBenefit;
    }

    public Integer getCardHandle() {
        return cardHandle;
    }

    public void setCardHandle(Integer cardHandle) {
        this.cardHandle = cardHandle;
    }

    public String getHandlePersonalId() {
        return handlePersonalId;
    }

    public void setHandlePersonalId(String handlePersonalId) {
        this.handlePersonalId = handlePersonalId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOutCardNo() {
        return outCardNo;
    }

    public void setOutCardNo(String outCardNo) {
        this.outCardNo = outCardNo;
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

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }

    public String getSaleManId() {
        return saleManId;
    }

    public void setSaleManId(String saleManId) {
        this.saleManId = saleManId;
    }

    public Integer getShareNum() {
        return shareNum;
    }

    public void setShareNum(Integer shareNum) {
        this.shareNum = shareNum;
    }

    public String getChildClientId() {
        return childClientId;
    }

    public void setChildClientId(String childClientId) {
        this.childClientId = childClientId;
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

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
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
        return "FrChildCard{" +
                ", cardNo=" + cardNo +
                ", shopId=" + shopId +
                ", parentCardId=" + parentCardId +
                ", mobile=" + mobile +
                ", shareBenefit=" + shareBenefit +
                ", cardHandle=" + cardHandle +
                ", handlePersonalId=" + handlePersonalId +
                ", userName=" + userName +
                ", outCardNo=" + outCardNo +
                ", type=" + type +
                ", isUsing=" + isUsing +
                ", CustomerCode=" + CustomerCode +
                ", saleManId=" + saleManId +
                ", shareNum=" + shareNum +
                ", childClientId=" + childClientId +
                ", updateTime=" + updateTime +
                ", createUserName=" + createUserName +
                ", createUserId=" + createUserId +
                ", createTime=" + createTime +
                ", flag=" + flag +
                ", updateUserName=" + updateUserName +
                ", updateUserId=" + updateUserId +
                ", id=" + id +
                "}";
    }
}
