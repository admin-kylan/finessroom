package com.yj.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sun.net.httpserver.HttpsServer;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.JsonXMLUtils;
import com.yj.dal.dto.ShopListDTO;
import com.yj.dal.model.Shop;
import com.yj.service.service.IShopService;
import com.yj.service.service.impl.ShopServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 门店表 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-25
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    IShopService service;

    /**
     * @Description: 根据客户代码查询所在门店列表(单个门店设置)
     * @Author: 欧俊俊
     * @Date: 2018-09-25 9:38
     */
    @GetMapping("/getShopList")
    public JsonResult getShopList(HttpServletRequest request) throws YJException {
        String code = CookieUtils.getCookieValue(request, "code", true);
        if (StringUtils.isEmpty(code)) {
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        return service.selectShopList(code);
    }

    /**
     * @Description: 根据客户代码查询所在门店列表(不是树形结构)
     * @Author: 欧俊俊
     * @Date: 2018-09-25 9:38
     */
    @GetMapping("/getShopListNoTree")
    public JsonResult getShopListNoTree(HttpServletRequest request) throws YJException {
        String code = CookieUtils.getCookieValue(request, "code", true);
        if (StringUtils.isEmpty(code)) {
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        EntityWrapper entityWrapper = new EntityWrapper<>();
        entityWrapper.setSqlSelect("id,ShopName").where("CustomerCode = {0} AND Status = 0", code);
        return JsonResult.success(service.selectList(entityWrapper));
    }


    /**
     * @Description: 修改所在门店设置
     * @Author: 欧俊俊
     * @Date: 2018-09-25 17:51
     */
    @PostMapping("/postUpdateWhereShop")
    public JsonResult updateWhereShop(@RequestBody List<ShopListDTO> list) throws YJException {
        Boolean b = service.updateWhereShop(list);
        if (b) {
            return JsonResult.successMessage("修改成功");
        }
        return JsonResult.failMessage("修改失败");
    }

    @Resource
    private IShopService shopService;


    /**
     * 获取所有门店列表及其下卡系列与卡种
     *
     * @return
     * @throws YJException
     */
    @GetMapping("/getFrCardTypeList")
    public JsonResult getFrCardTypeList(HttpServletRequest request) throws YJException {
        String code = CookieUtils.getCookieValue(request, "code", true);
        return JsonResult.success(shopService.getFrCardType(code));
    }


    /**
     * 获取所有门店
     *
     * @param type 0：获取上次添加时间  1：获取上次修改时间
     * @return
     * @throws YJException
     */
    @GetMapping("/getStoreList")
    public JsonResult getStoreList(HttpServletRequest request, @RequestParam Integer type,String shopId) throws YJException {
        String code = CookieUtils.getCookieValue(request, "code", true);
        return JsonResult.success(shopService.getStoreList(type, code,shopId));
    }

    /**
     * 获取所有门店（不排序）
     *
     * @return
     * @throws YJException
     */
    @GetMapping("/getMarketShopList")
    public JsonResult getMarketShopList(String code, HttpServletRequest request) throws YJException {
        if(StringUtils.isEmpty(code)){
            code = CookieUtils.getCookieValue(request, "code", true);
            if(StringUtils.isEmpty(code)){
                return JsonResult.failMessage("客户代码获取异常");
            }
        }
        return JsonResult.success(shopService.getShopList(code));
    }


    /**
     * 搜索门店关联的场馆
     *
     * @param sids 门店
     * @return
     * @throws YJException
     */
    @PostMapping("/ShopSdaduimList")
    public JsonResult getShopSdaduimList(@RequestParam(value = "sids[]") String[] sids) throws YJException {
        System.out.println("--------------------------------------------");

        return shopService.getShopSdaduimList(sids);
    }


    /**
     * 获取前台不显示列表
     *
     * @return
     * @throws YJException
     */
    @GetMapping("/getIsUsingIsTrue")
    public JsonResult getIsUsingIsTrue(HttpServletRequest request) throws YJException {
        String code = CookieUtils.getCookieValue(request, "code", true);
        return JsonResult.success(shopService.getIsUsingIsTrue(code));
    }

    /**
     * 获取指定门店下的所有卡类型卡种
     *
     * @param request
     * @param shopId
     * @return
     * @throws YJException
     */
    @GetMapping("/getByShopIdList")
    public JsonResult getByShopIdList(HttpServletRequest request, @RequestParam String shopId, @RequestParam Integer type) throws YJException {
        String code = CookieUtils.getCookieValue(request, "code", true);
        return JsonResult.success(shopService.getByShopIdList(code, shopId, type));
    }

    @GetMapping("/getShopAll")
    public JsonResult getShopAll(HttpServletRequest request) {
        String code = CookieUtils.getCookieValue(request, "code", true);
        return JsonResult.success(shopService.selectList(new EntityWrapper<Shop>().setSqlSelect("ID", "ShopName").where("CustomerCode = {0}", code)));
    }
//
//    /**
//     * 获取门店下所有场馆项目
//     * @param code
//     * @param shopId
//     * @return
//     */
//    @GetMapping("/getShopAndCategoryItem")
//    public JsonResult getShopAndCategoryItem(String code,String shopId){
//
//
//        return shopService.getShopAndCategoryItem(code,shopId);
//    }

}

