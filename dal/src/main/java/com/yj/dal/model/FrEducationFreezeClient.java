package com.yj.dal.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 会员冻结列表
 * </p>
 *
 * @author MP自动生成
 * @since 2019-01-26
 */
@TableName("fr_education_freeze_client")
public class FrEducationFreezeClient extends Model<FrEducationFreezeClient> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 需缴纳的钱
     */
    private Double price;
    /**
     * 冻结时间
     */
    @TableField("freeze_date")
    private Date freezeDate;
    /**
     * 解冻时间
     */
    @TableField("un_freeze_date")
    private Date unFreezeDate;
    /**
     * 备注
     */
    private String remake;
    /**
     * 卡id
     */
    @TableField("card_id")
    private String cardId;
    /**
     * 卡号
     */
    @TableField("card_no")
    private String cardNo;
    /**
     * 当前预约的次数
     */
    private Integer count;
    /**
     * 团教或者私教 0/1
     */
    @TableField("edu_type")
    private Integer eduType;
    /**
     * 是否可用
     */
    @TableField("is_use")
    private Boolean isUse;
    /**
     * 可预约的时间
     */
    @TableField("begin_date")
    private Date beginDate;
    /**
     * 可预约的结束时间
     */
    @TableField("end_date")
    private Date endDate;
    /**
     * 指定时间未取消未消费的次数
     */
    @TableField("total_count")
    private Integer totalCount;
    @TableField("client_name")
    private String clientName;
    @TableField("shop_id")
    private String shopId;
    @TableField("shop_name")
    private String shopName;
    private String mobile;
    @TableField("client_id")
    private String clientId;
    @TableField("create_date")
    private Date createDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getFreezeDate() {
        return freezeDate;
    }

    public void setFreezeDate(Date freezeDate) {
        this.freezeDate = freezeDate;
    }

    public Date getUnFreezeDate() {
        return unFreezeDate;
    }

    public void setUnFreezeDate(Date unFreezeDate) {
        this.unFreezeDate = unFreezeDate;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getEduType() {
        return eduType;
    }

    public void setEduType(Integer eduType) {
        this.eduType = eduType;
    }

    public Boolean getUse() {
        return isUse;
    }

    public void setUse(Boolean isUse) {
        this.isUse = isUse;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FrEducationFreezeClient{" +
        ", id=" + id +
        ", price=" + price +
        ", freezeDate=" + freezeDate +
        ", unFreezeDate=" + unFreezeDate +
        ", remake=" + remake +
        ", cardId=" + cardId +
        ", cardNo=" + cardNo +
        ", count=" + count +
        ", eduType=" + eduType +
        ", isUse=" + isUse +
        ", beginDate=" + beginDate +
        ", endDate=" + endDate +
        ", totalCount=" + totalCount +
        ", clientName=" + clientName +
        ", shopId=" + shopId +
        ", shopName=" + shopName +
        ", mobile=" + mobile +
        ", clientId=" + clientId +
        ", createDate=" + createDate +
        "}";
    }
}
