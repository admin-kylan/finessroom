package com.yj.dal.param;

import com.yj.dal.model.FrClient;

public class AddCustomerParam {
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

    public FrClient getBase() {
        return base;
    }

    public void setBase(FrClient base) {
        this.base = base;
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
}
