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
 * 
 * </p>
 *
 * @author MP自动生成
 * @since 2019-02-16
 */
@TableName("fr_education_access_info")
public class FrEducationAccessInfo extends Model<FrEducationAccessInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 会员姓名
     */
    @TableField("client_name")
    private String clientName;
    /**
     * 会员id
     */
    @TableField("client_id")
    private String clientId;
    /**
     * 销售人员
     */
    @TableField("sale_use_name")
    private String saleUseName;
    /**
     * 入场方式 刷卡入场/刷手机号入场 /刷票券入场
     */
    private String type;
    /**
     * 消费项目名称
     */
    @TableField("sale_project")
    private String saleProject;
    /**
     * 消费项目id
     */
    @TableField("sale_project_id")
    private String saleProjectId;
    /**
     * 使用人姓名
     */
    @TableField("use_people_name")
    private String usePeopleName;
    /**
     * 使用人id
     */
    @TableField("use_people_id")
    private String usePeopleId;
    /**
     * 是否出场 0/1
     */
    private Boolean status;
    /**
     * 入场时间，消费时间
     */
    @TableField("create_date")
    private Date createDate;
    /**
     * 出场时间，结束时间
     */
    @TableField("output_date")
    private Date outputDate;
    /**
     * 操作人
     */
    @TableField(value = "create_user_name", fill = FieldFill.INSERT)
    private String createUserName;
    /**
     * 操作人Id
     */
    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    private String createUserId;
    /**
     * 性别 男 女 0 /1
     */
    private Integer sex;
    /**
     * 手牌号吗
     */
    @TableField("hand_num")
    private String handNum;
    /**
     * 是否冲销
     */
    @TableField("is_done")
    private Boolean isDone;
    /**
     * 备注
     */
    private String remark;
    /**
     * 异常信息
     */
    @TableField("error_msg")
    private String errorMsg;
    /**
     * 差价
     */
    @TableField("spread_price")
    private Double spreadPrice;
    /**
     * 入场输入的卡号，票券，号吗
     */
    private String account;
    /**
     * 挂帐金额
     */
    @TableField("less_price")
    private Double lessPrice;
    /**
     * 出场操作人
     */
    @TableField("output_user_name")
    private String outputUserName;
    /**
     * 出场操作人id
     */
    @TableField("output_user_id")
    private String outputUserId;
    /**
     * 门店id
     */
    @TableField("shop_id")
    private String shopId;
    /**
     * 门店名字
     */
    @TableField("shop_name")
    private String shopName;
    @TableField("is_use")
    private Boolean isUse;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    @TableField("update_date")
    private Date updateDate;
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;


    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSaleUseName() {
        return saleUseName;
    }

    public void setSaleUseName(String saleUseName) {
        this.saleUseName = saleUseName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSaleProject() {
        return saleProject;
    }

    public void setSaleProject(String saleProject) {
        this.saleProject = saleProject;
    }

    public String getSaleProjectId() {
        return saleProjectId;
    }

    public void setSaleProjectId(String saleProjectId) {
        this.saleProjectId = saleProjectId;
    }

    public String getUsePeopleName() {
        return usePeopleName;
    }

    public void setUsePeopleName(String usePeopleName) {
        this.usePeopleName = usePeopleName;
    }

    public String getUsePeopleId() {
        return usePeopleId;
    }

    public void setUsePeopleId(String usePeopleId) {
        this.usePeopleId = usePeopleId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getOutputDate() {
        return outputDate;
    }

    public void setOutputDate(Date outputDate) {
        this.outputDate = outputDate;
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getHandNum() {
        return handNum;
    }

    public void setHandNum(String handNum) {
        this.handNum = handNum;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Double getSpreadPrice() {
        return spreadPrice;
    }

    public void setSpreadPrice(Double spreadPrice) {
        this.spreadPrice = spreadPrice;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Double getLessPrice() {
        return lessPrice;
    }

    public void setLessPrice(Double lessPrice) {
        this.lessPrice = lessPrice;
    }

    public String getOutputUserName() {
        return outputUserName;
    }

    public void setOutputUserName(String outputUserName) {
        this.outputUserName = outputUserName;
    }

    public String getOutputUserId() {
        return outputUserId;
    }

    public void setOutputUserId(String outputUserId) {
        this.outputUserId = outputUserId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Boolean getUse() {
        return isUse;
    }

    public void setUse(Boolean isUse) {
        this.isUse = isUse;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrEducationAccessInfo{" +
        ", clientName=" + clientName +
        ", clientId=" + clientId +
        ", saleUseName=" + saleUseName +
        ", type=" + type +
        ", saleProject=" + saleProject +
        ", saleProjectId=" + saleProjectId +
        ", usePeopleName=" + usePeopleName +
        ", usePeopleId=" + usePeopleId +
        ", status=" + status +
        ", createDate=" + createDate +
        ", outputDate=" + outputDate +
        ", createUserName=" + createUserName +
        ", createUserId=" + createUserId +
        ", sex=" + sex +
        ", handNum=" + handNum +
        ", isDone=" + isDone +
        ", remark=" + remark +
        ", errorMsg=" + errorMsg +
        ", spreadPrice=" + spreadPrice +
        ", account=" + account +
        ", lessPrice=" + lessPrice +
        ", outputUserName=" + outputUserName +
        ", outputUserId=" + outputUserId +
        ", shopId=" + shopId +
        ", shopName=" + shopName +
        ", isUse=" + isUse +
        ", id=" + id +
        ", updateDate=" + updateDate +
        ", CustomerCode=" + CustomerCode +
        "}";
    }
}
