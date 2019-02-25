package com.yj.dal.dto;

import com.yj.dal.model.FrGroupSeries;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 29155 on 2018/12/8.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrGroupSeriesDTO extends FrGroupSeries {

    private String shopIds;

	public String getShopIds() {
		return shopIds;
	}

	public void setShopIds(String shopIds) {
		this.shopIds = shopIds;
	}
    
}
