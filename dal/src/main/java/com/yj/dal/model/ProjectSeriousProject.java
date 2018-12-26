package com.yj.dal.model;

import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;
import java.util.Date;

public class ProjectSeriousProject extends Model<ProjectSeriousProject> {
    private static final long serialVersionUID = 1L;
    //客户代码 （方便客户导出资料）
    private String CustomerCode;
    //id
    private String id;
    //套餐明细ID 关联ProjectSeriousInfo表
    private String ProjectSeriousInfoId;
    //门店ID
    private String ShopId;
    //场馆ID
    private String SdadiumId;
    //项目设置ID
    private String ProjectSetId;
    //指定数量
    private Integer ReferCount;
    //每天限用数量
    private Integer DayCount;
    //创建时间
    private Date CreateTime;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String customerCode) {
        CustomerCode = customerCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectSeriousInfoId() {
        return ProjectSeriousInfoId;
    }

    public void setProjectSeriousInfoId(String projectSeriousInfoId) {
        ProjectSeriousInfoId = projectSeriousInfoId;
    }

    public String getShopId() {
        return ShopId;
    }

    public void setShopId(String shopId) {
        ShopId = shopId;
    }

    public String getSdadiumId() {
        return SdadiumId;
    }

    public void setSdadiumId(String sdadiumId) {
        SdadiumId = sdadiumId;
    }

    public String getProjectSetId() {
        return ProjectSetId;
    }

    public void setProjectSetId(String projectSetId) {
        ProjectSetId = projectSetId;
    }

    public Integer getReferCount() {
        return ReferCount;
    }

    public void setReferCount(Integer referCount) {
        ReferCount = referCount;
    }

    public Integer getDayCount() {
        return DayCount;
    }

    public void setDayCount(Integer dayCount) {
        DayCount = dayCount;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ProjectSeriousProject{" +
                "CustomerCode='" + CustomerCode + '\'' +
                ", id='" + id + '\'' +
                ", ProjectSeriousInfoId='" + ProjectSeriousInfoId + '\'' +
                ", ShopId='" + ShopId + '\'' +
                ", SdadiumId='" + SdadiumId + '\'' +
                ", ProjectSetId='" + ProjectSetId + '\'' +
                ", ReferCount=" + ReferCount +
                ", DayCount=" + DayCount +
                ", CreateTime=" + CreateTime +
                '}';
    }
}