package com.yj.dal.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import com.yj.dal.base.BaseModel;
import com.yj.dal.dto.CategoryItemDTO;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-26
 */
@TableName("Sdaduim")
public class Sdaduim  extends Model<Sdaduim> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户场馆表
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /**
     * 客户代码 （方便客户导出资料）ids
     */
    private String CustomerCode;
    /**
     * 场馆ID ids
     */
    private String NumId;
    /**
     * 门店ID
     */
    private String ShopId;
    /**
     * 场馆名称
     */
    private String Name;
    /**
     * 场馆状态?0：正常，1：不正常
     */
    private Integer Status;
    /**
     * 是否有手牌
     */
    private Boolean IsHand;
    /**
     * 序号
     */
    private Integer Num;
    /**
     * 是否私教（0-普通模块，1-私教模块）
     */
    private Integer IsPrivateEducation;
    /**
     * 是否后台
     */
    private Boolean IsBackstage;
    /**
     * 0：自动入场?1：否
     */
    private Boolean AutomaticEntry;
    /**
     * 1球类?2自动入场?3私教?4餐饮?5?团教?6培训教育?7?综合收银-酒店?8物业?9?行政管理?10?工程项目管理?11?供应商/采购商管理?12?票券?13?工作执行计划?14酒店模块?15租赁模块?16婚纱摄影?17美容院?18人力资源?19美发?20美甲?21足浴
     */
    private Integer Module;
    /**
     * 报表模块是否显示该场馆（1-显示，0-不显示）
     */
    private Boolean IsShow;
    /**
     * 该场馆是否涉及金额消费?0不涉及?1涉及
     */
    private Boolean IsConsume;
    /**
     * 场馆aes加密验证
     */
    private String AesEncrypt;
    /**
     * 该模块?教练是否验证指纹
     */
    private Integer checkFinger;
    /**
     * 0-终身 1-期限 2-次数
     */
    private Integer UseType;
    /**
     * 开始时间
     */
    private Date StartTime;
    /**
     * 结束时间
     */
    private Date EndTime;
    /**
     * 剩余次数
     */
    private Integer BalanceNum;
    /**
     * 总次数
     */
    private Integer TotalNum;
    private String CreateId;
    private String CreateName;
    private String BusinessTypeId;
    private String ModelCode;
    private Date CreateTime;

    @TableField(exist = false)
    private String shopName;
    @TableField(exist = false)
    private  List<CategoryItemDTO> categoryItemlist;

    @TableField(exist = false)   //关系
    @JSONField(serialzeFeatures= SerializerFeature.WriteMapNullValue)
    private  List<FrShopCtypeConsume> shopCtypeConsumes;

    public List<FrShopCtypeConsume> getShopCtypeConsumes() {
        return shopCtypeConsumes;
    }

    public void setShopCtypeConsumes(List<FrShopCtypeConsume> shopCtypeConsumes) {
        this.shopCtypeConsumes = shopCtypeConsumes;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<CategoryItemDTO> getCategoryItemlist() {
        return categoryItemlist;
    }

    public void setCategoryItemlist(List<CategoryItemDTO> categoryItemlist) {
        this.categoryItemlist = categoryItemlist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    public String getNumId() {
        return NumId;
    }

    public void setNumId(String NumId) {
        this.NumId = NumId;
    }

    public String getShopId() {
        return ShopId;
    }

    public void setShopId(String ShopId) {
        this.ShopId = ShopId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }

    public Boolean getIsHand() {
        return IsHand;
    }

    public void setIsHand(Boolean IsHand) {
        this.IsHand = IsHand;
    }

    public Integer getNum() {
        return Num;
    }

    public void setNum(Integer Num) {
        this.Num = Num;
    }

    public Integer getIsPrivateEducation() {
        return IsPrivateEducation;
    }

    public void setIsPrivateEducation(Integer IsPrivateEducation) {
        this.IsPrivateEducation = IsPrivateEducation;
    }

    public Boolean getIsBackstage() {
        return IsBackstage;
    }

    public void setIsBackstage(Boolean IsBackstage) {
        this.IsBackstage = IsBackstage;
    }

    public Boolean getAutomaticEntry() {
        return AutomaticEntry;
    }

    public void setAutomaticEntry(Boolean AutomaticEntry) {
        this.AutomaticEntry = AutomaticEntry;
    }

    public Integer getModule() {
        return Module;
    }

    public void setModule(Integer Module) {
        this.Module = Module;
    }

    public Boolean getIsShow() {
        return IsShow;
    }

    public void setIsShow(Boolean IsShow) {
        this.IsShow = IsShow;
    }

    public Boolean getIsConsume() {
        return IsConsume;
    }

    public void setIsConsume(Boolean IsConsume) {
        this.IsConsume = IsConsume;
    }

    public String getAesEncrypt() {
        return AesEncrypt;
    }

    public void setAesEncrypt(String AesEncrypt) {
        this.AesEncrypt = AesEncrypt;
    }

    public Integer getCheckFinger() {
        return checkFinger;
    }

    public void setCheckFinger(Integer checkFinger) {
        this.checkFinger = checkFinger;
    }

    public Integer getUseType() {
        return UseType;
    }

    public void setUseType(Integer UseType) {
        this.UseType = UseType;
    }

    public Date getStartTime() {
        return StartTime;
    }

    public void setStartTime(Date StartTime) {
        this.StartTime = StartTime;
    }

    public Date getEndTime() {
        return EndTime;
    }

    public void setEndTime(Date EndTime) {
        this.EndTime = EndTime;
    }

    public Integer getBalanceNum() {
        return BalanceNum;
    }

    public void setBalanceNum(Integer BalanceNum) {
        this.BalanceNum = BalanceNum;
    }

    public Integer getTotalNum() {
        return TotalNum;
    }

    public void setTotalNum(Integer TotalNum) {
        this.TotalNum = TotalNum;
    }

    public String getCreateId() {
        return CreateId;
    }

    public void setCreateId(String CreateId) {
        this.CreateId = CreateId;
    }

    public String getCreateName() {
        return CreateName;
    }

    public void setCreateName(String CreateName) {
        this.CreateName = CreateName;
    }

    public String getBusinessTypeId() {
        return BusinessTypeId;
    }

    public void setBusinessTypeId(String BusinessTypeId) {
        this.BusinessTypeId = BusinessTypeId;
    }

    public String getModelCode() {
        return ModelCode;
    }

    public void setModelCode(String ModelCode) {
        this.ModelCode = ModelCode;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }



    @Override
    public String toString() {
        return "Sdaduim{" +
        ", id=" + id +
        ", CustomerCode=" + CustomerCode +
        ", NumId=" + NumId +
        ", ShopId=" + ShopId +
        ", Name=" + Name +
        ", Status=" + Status +
        ", IsHand=" + IsHand +
        ", Num=" + Num +
        ", IsPrivateEducation=" + IsPrivateEducation +
        ", IsBackstage=" + IsBackstage +
        ", AutomaticEntry=" + AutomaticEntry +
        ", Module=" + Module +
        ", IsShow=" + IsShow +
        ", IsConsume=" + IsConsume +
        ", AesEncrypt=" + AesEncrypt +
        ", checkFinger=" + checkFinger +
        ", UseType=" + UseType +
        ", StartTime=" + StartTime +
        ", EndTime=" + EndTime +
        ", BalanceNum=" + BalanceNum +
        ", TotalNum=" + TotalNum +
        ", CreateId=" + CreateId +
        ", CreateName=" + CreateName +
        ", BusinessTypeId=" + BusinessTypeId +
        ", ModelCode=" + ModelCode +
        ", CreateTime=" + CreateTime +
        "}";
    }
}
