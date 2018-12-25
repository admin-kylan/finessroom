package com.yj.dal.param;

import com.yj.dal.model.FrClient;

/**
 * 〈一句话功能简述〉<br>
 * 〈新增潜在客户参数〉
 *
 * @author 欧俊俊
 * @create 2018/9/15
 */
public class AddPotentialParam {
    private String picLink;
    private String shopId;
    private String salespersonId;
    private FrClient base;

    public String getPicLink() {
        return picLink;
    }

    public void setPicLink(String picLink) {
        this.picLink = picLink;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }


    public String getSalespersonId() {
        return salespersonId;
    }

    public void setSalespersonId(String salespersonId) {
        this.salespersonId = salespersonId;
    }

    public FrClient getBase() {
        return base;
    }

    public void setBase(FrClient base) {
        this.base = base;
    }
}
