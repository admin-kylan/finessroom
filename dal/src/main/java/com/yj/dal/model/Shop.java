package com.yj.dal.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.yj.dal.base.BaseModel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 门店表
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-25
 */
@TableName("Shop")
public class Shop extends Model<Shop> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户门店表
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /**
     * 客户代码 （方便客户导出资料）ids
     */
    private String customerCode;
    /**
     * 门店名称
     */
    private String shopName;
    /**
     * 城市名称
     */
    private String cityName;
    /**
     * 门店电话
     */
    private String shopTel;
    /**
     * 门店地址
     */
    private String shopAddress;
    /**
     * 省份ID
     */
    private String provinceId;
    /**
     * 省份名称
     */
    private String provinceName;
    /**
     * 城市ID
     */
    private String cityId;
    /**
     * 区名称
     */
    private String areaId;
    /**
     * 区名称
     */
    private String areaName;
    /**
     * 0代表省份 1代表城市 2代表县级市
     */
    private Integer placeType;
    /**
     * ?状态?0-正常
     */
    private Integer status;
    /**
     * 1.总部，2.门店
     */
    private Integer shopStatus;
    /**
     * 面积数
     */
    private Integer areaCount;
    /**
     * 员工数
     */
    private Integer employCount;
    /**
     * 场馆数
     */
    private Integer sdadiumCount;
    /**
     * 年营业额
     */
    private Double money;
    /**
     * 关联现有客户/潜在客户
     */
    private String merchantId;
    /**
     * 潜在客户经营项目 例如 健身&游泳
     */
    private String manageList;
    /**
     * 其它项目
     */
    
	private String otherProject;
    private String extendField1;
    private String createID;
    private String simpleAddrs;
    private String createName;
    private String extendField2;
    private String extendField3;
	@JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;



	/* 自定义对象不再数据库中  不在数据库中*/
	@TableField(exist = false)
    private List<Sdaduim> sdaduimArrayList;
    @TableField(exist = false)
    List<FrCategoryItem> categoryItemlist ;
    @TableField(exist = false)
    List<Map<String,Object>> map ;

    public List<Map<String, Object>> getMap() {
        return map;
    }

    public void setMap(List<Map<String, Object>> map) {
        this.map = map;
    }

    public List<FrCategoryItem> getCategoryItemlist() {
        return categoryItemlist;
    }

    public void setCategoryItemlist(List<FrCategoryItem> categoryItemlist) {
        this.categoryItemlist = categoryItemlist;
    }

    public List<Sdaduim> getSdaduimArrayList() {
        return sdaduimArrayList;
    }

    public void setSdaduimArrayList(List<Sdaduim> sdaduimArrayList) {
        this.sdaduimArrayList = sdaduimArrayList;
    }

    @TableField(exist = false)
    private List<FrCardType> frCardTypeList;

    @TableField(exist = false)
    private String flag;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getShopTel() {
        return shopTel;
    }

    public void setShopTel(String shopTel) {
        this.shopTel = shopTel;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getPlaceType() {
        return placeType;
    }

    public void setPlaceType(Integer placeType) {
        this.placeType = placeType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(Integer shopStatus) {
        this.shopStatus = shopStatus;
    }

    public Integer getAreaCount() {
        return areaCount;
    }

    public void setAreaCount(Integer areaCount) {
        this.areaCount = areaCount;
    }

    public Integer getEmployCount() {
        return employCount;
    }

    public void setEmployCount(Integer employCount) {
        this.employCount = employCount;
    }

    public Integer getSdadiumCount() {
        return sdadiumCount;
    }

    public void setSdadiumCount(Integer sdadiumCount) {
        this.sdadiumCount = sdadiumCount;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getManageList() {
        return manageList;
    }

    public void setManageList(String manageList) {
        this.manageList = manageList;
    }

    public String getOtherProject() {
        return otherProject;
    }

    public void setOtherProject(String otherProject) {
        this.otherProject = otherProject;
    }

    public String getExtendField1() {
        return extendField1;
    }

    public void setExtendField1(String extendField1) {
        this.extendField1 = extendField1;
    }

    public String getCreateID() {
        return createID;
    }

    public void setCreateID(String createID) {
        this.createID = createID;
    }

    public String getSimpleAddrs() {
        return simpleAddrs;
    }

    public void setSimpleAddrs(String simpleAddrs) {
        this.simpleAddrs = simpleAddrs;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getExtendField2() {
        return extendField2;
    }

    public void setExtendField2(String extendField2) {
        this.extendField2 = extendField2;
    }

    public String getExtendField3() {
        return extendField3;
    }

    public void setExtendField3(String extendField3) {
        this.extendField3 = extendField3;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<FrCardType> getFrCardTypeList() {
        return frCardTypeList;
    }

    public void setFrCardTypeList(List<FrCardType> frCardTypeList) {
        this.frCardTypeList = frCardTypeList;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id='" + id + '\'' +
                ", customerCode='" + customerCode + '\'' +
                ", shopName='" + shopName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", shopTel='" + shopTel + '\'' +
                ", shopAddress='" + shopAddress + '\'' +
                ", provinceId='" + provinceId + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", cityId='" + cityId + '\'' +
                ", areaId='" + areaId + '\'' +
                ", areaName='" + areaName + '\'' +
                ", placeType=" + placeType +
                ", status=" + status +
                ", shopStatus=" + shopStatus +
                ", areaCount=" + areaCount +
                ", employCount=" + employCount +
                ", sdadiumCount=" + sdadiumCount +
                ", money=" + money +
                ", merchantId='" + merchantId + '\'' +
                ", manageList='" + manageList + '\'' +
                ", otherProject='" + otherProject + '\'' +
                ", extendField1='" + extendField1 + '\'' +
                ", createID='" + createID + '\'' +
                ", simpleAddrs='" + simpleAddrs + '\'' +
                ", createName='" + createName + '\'' +
                ", extendField2='" + extendField2 + '\'' +
                ", extendField3='" + extendField3 + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
