package com.yj.web.appController;


import com.yj.common.result.JsonResult;
import com.yj.service.AppService.IAppCardTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 卡类型表 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-10
 */
@RestController
@RequestMapping("/app")
public class AppCardTypeController{

    @Resource
    private IAppCardTypeService appCardTypeService;


    /**
     * 获取 城市/门店/卡种系列/对应卡名称
     * @param shopId                            门店ID
     * @param cityId                            城市ID
     * @param customerCode                      客户代码
     * @param cardTypeId                        卡系列ID
     * @param cardTypeName                      卡类型名称
     * @return
     */
    @GetMapping("/getCardTypeList")
    public JsonResult getGeneralStoreList(String shopId,String cityId,String customerCode,String cardTypeId,String cardTypeName) {
        return appCardTypeService.getGeneralStoreList(shopId,cityId,customerCode,cardTypeId,cardTypeName);
    }
}

