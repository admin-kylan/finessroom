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
 * @since 2018-12-29
 */
@TableName("ReturnAddProject")
public class ReturnAddProject extends Model<ReturnAddProject> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户代码 （方便客户导出资料）ids
     */
    @TableField(fill = FieldFill.INSERT)
    private String CustomerCode;
    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /**
     * 用户id
     */
    private String clientId;
    /**
     * 用户名字
     */
    private String clientName;

    /**
     * 增购项目ID
     */
    private String AddProjectID;
    /**
     * 退还次数
     */
    private Integer ReturnCount;
    /**
     * 退还金额
     */
    private Double ReturnMoney;
    /**
     * 现金
     */
    private Double Cash;
    /**
     * 转账
     */
    private Double TurnMoney;
    /**
     * 管理员ID
     */
    private String EmployID;
    /**
     * 管理员
     */
    private String EmployName;
    /**
     * 会员卡ID
     */
    private String CardId;
    /**
     * 会员授权
     */
    private String CustomerPwd;
    /**
     * 员工授权
     */
    private String PersonalPwd;
    /**
     * 操作时间
     */
    private Date CreateTime;
    /**
     * 操作人
     */
    private String CreateName;
    /**
     * 操作人ID
     */
    private String CreateNameID;
    /**
     * 收款金额
     */
    private Double WeChat;
    /**
     * 算业绩的门店
     */
    @TableField("RA_ShopID")
    private String raShopid;
    /**
     * 会员卡金额
     */
    private Double CardMoney;
    /**
     * 销售人员ID
     */
    private String SaleID;
    /**
     * 销售人员名称
     */
    private String SaleName;
    /**
     * 退款账号
     */
    private String ReturnNumber;
    /**
     * 付款类型
     */
    private String PayType;

    private String courseName;


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }



    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddProjectID() {
        return AddProjectID;
    }

    public void setAddProjectID(String AddProjectID) {
        this.AddProjectID = AddProjectID;
    }

    public Integer getReturnCount() {
        return ReturnCount;
    }

    public void setReturnCount(Integer ReturnCount) {
        this.ReturnCount = ReturnCount;
    }

    public Double getReturnMoney() {
        return ReturnMoney;
    }

    public void setReturnMoney(Double ReturnMoney) {
        this.ReturnMoney = ReturnMoney;
    }

    public Double getCash() {
        return Cash;
    }

    public void setCash(Double Cash) {
        this.Cash = Cash;
    }

    public Double getTurnMoney() {
        return TurnMoney;
    }

    public void setTurnMoney(Double TurnMoney) {
        this.TurnMoney = TurnMoney;
    }

    public String getEmployID() {
        return EmployID;
    }

    public void setEmployID(String EmployID) {
        this.EmployID = EmployID;
    }

    public String getEmployName() {
        return EmployName;
    }

    public void setEmployName(String EmployName) {
        this.EmployName = EmployName;
    }

    public String getCardId() {
        return CardId;
    }

    public void setCardId(String CardId) {
        this.CardId = CardId;
    }

    public String getCustomerPwd() {
        return CustomerPwd;
    }

    public void setCustomerPwd(String CustomerPwd) {
        this.CustomerPwd = CustomerPwd;
    }

    public String getPersonalPwd() {
        return PersonalPwd;
    }

    public void setPersonalPwd(String PersonalPwd) {
        this.PersonalPwd = PersonalPwd;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getCreateName() {
        return CreateName;
    }

    public void setCreateName(String CreateName) {
        this.CreateName = CreateName;
    }

    public String getCreateNameID() {
        return CreateNameID;
    }

    public void setCreateNameID(String CreateNameID) {
        this.CreateNameID = CreateNameID;
    }

    public Double getWeChat() {
        return WeChat;
    }

    public void setWeChat(Double WeChat) {
        this.WeChat = WeChat;
    }

    public String getRaShopid() {
        return raShopid;
    }

    public void setRaShopid(String raShopid) {
        this.raShopid = raShopid;
    }

    public Double getCardMoney() {
        return CardMoney;
    }

    public void setCardMoney(Double CardMoney) {
        this.CardMoney = CardMoney;
    }

    public String getSaleID() {
        return SaleID;
    }

    public void setSaleID(String SaleID) {
        this.SaleID = SaleID;
    }

    public String getSaleName() {
        return SaleName;
    }

    public void setSaleName(String SaleName) {
        this.SaleName = SaleName;
    }

    public String getReturnNumber() {
        return ReturnNumber;
    }

    public void setReturnNumber(String ReturnNumber) {
        this.ReturnNumber = ReturnNumber;
    }

    public String getPayType() {
        return PayType;
    }

    public void setPayType(String PayType) {
        this.PayType = PayType;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public String toString() {
        return "ReturnAddProject{" +
        ", CustomerCode=" + CustomerCode +
        ", id=" + id +
        ", AddProjectID=" + AddProjectID +
        ", ReturnCount=" + ReturnCount +
        ", ReturnMoney=" + ReturnMoney +
        ", Cash=" + Cash +
        ", TurnMoney=" + TurnMoney +
        ", EmployID=" + EmployID +
        ", EmployName=" + EmployName +
        ", CardId=" + CardId +
        ", CustomerPwd=" + CustomerPwd +
        ", PersonalPwd=" + PersonalPwd +
        ", CreateTime=" + CreateTime +
        ", CreateName=" + CreateName +
        ", CreateNameID=" + CreateNameID +
        ", WeChat=" + WeChat +
        ", raShopid=" + raShopid +
        ", CardMoney=" + CardMoney +
        ", SaleID=" + SaleID +
        ", SaleName=" + SaleName +
        ", ReturnNumber=" + ReturnNumber +
        ", PayType=" + PayType +
        "}";
    }
}
