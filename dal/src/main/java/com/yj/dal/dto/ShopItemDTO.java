package com.yj.dal.dto;

import com.yj.dal.model.Shop;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 欧俊俊
 * @create 2018/9/12
 */
public class ShopItemDTO {
    /**
     * 门店id
     */
    private String id;
    /**
     * 门店名称
     */
    private String shopName;
    /**
     * 是否关联单个门店设置
     */
    private Boolean singleStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Boolean getSingleStatus() {
        return singleStatus;
    }

    public void setSingleStatus(Boolean singleStatus) {
        this.singleStatus = singleStatus;
    }
}
