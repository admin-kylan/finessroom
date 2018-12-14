package com.yj.service.AppService;

import com.yj.common.result.JsonResult;

public interface IAppCardTypeService {

    /**
     * 获取 城市/门店/卡种系列/对应卡名称
     * @param shopId                            门店ID
     * @param cityId                            城市ID
     * @param customerCode                      客户代码
     * @param cardTypeId                        卡系列ID
     * @param cardTypeName                      卡类型名称
     * @return
     */
    JsonResult getGeneralStoreList(String shopId,String cityId,String customerCode,String cardTypeId,String cardTypeName);
}
