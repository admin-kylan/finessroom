package com.yj.dal.dto;

import com.yj.dal.model.FrStore;
import com.yj.dal.model.Shop;
import com.yj.dal.param.OperaterParam;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 欧俊俊
 * @create 2018/9/12
 */
public class ShopListDTO {
    private String cityName;
    private List<ShopItemDTO> list;


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<ShopItemDTO> getList() {
        return list;
    }

    public void setList(List<ShopItemDTO> list) {
        this.list = list;
    }
}
