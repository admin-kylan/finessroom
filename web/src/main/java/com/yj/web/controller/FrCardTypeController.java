package com.yj.web.controller;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.dal.model.FrCardType;
import com.yj.dal.model.FrShopCardTypeRelate;
import com.yj.service.service.IFrCardTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 卡类型表 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-10
 */
@RestController
@RequestMapping("/frCardType")
public class FrCardTypeController {

    @Resource
    private IFrCardTypeService frCardTypeService;

    /**
     * 加入卡系列、卡种
     * @param frCardType
     * @param ids 关联门店id
     * @param type  0为卡系列，1为卡种
     * @return
     */
    @PostMapping("/addFrCardType")
    public JsonResult addFrCardType(HttpServletRequest request,FrCardType frCardType, String[] ids,@RequestParam Integer type) throws YJException {
        String code = CookieUtils.getCookieValue(request, "code",true);
        frCardType.setCustomerCode(code);
        if(frCardType == null){
            return JsonResult.failMessage("参数错误");
        }
        return JsonResult.success(frCardTypeService.addFrCardType(frCardType,ids,type));
    }

    /**
     * 通用门店添加卡系列或卡种
     * @param frCardType
     * @param ids
     * @param type
     * @return
     * @throws YJException
     */
    @PostMapping("/addCurrentFrCardType")
    public JsonResult addCurrentFrCardType(HttpServletRequest request,FrCardType frCardType, String[] ids,@RequestParam Integer type) throws YJException {
        String code = CookieUtils.getCookieValue(request, "code",true);
        frCardType.setCustomerCode(code);
        if(frCardType == null){
            return JsonResult.failMessage("参数错误");
        }
        return JsonResult.success(frCardTypeService.addCurrentFrCardType(frCardType,ids,type));

    }

    /**
     * 修改卡系列
     * @param frCardType
     * @return
     * @throws YJException
     */
    @PostMapping("/updateFrCardType")
    public JsonResult updateFrCardType(FrCardType frCardType) throws YJException {
        if(frCardType == null){
            return JsonResult.failMessage("参数错误");
        }
        return JsonResult.success(frCardTypeService.updateFrCardType(frCardType));

    }


    /**
     * 修改卡种
     * @param frCardType
     * @param
     * @return
     * @throws YJException
     */
    @PostMapping("/updateFrCard")
    public JsonResult updateFrCard(FrCardType frCardType) throws YJException {
        if(frCardType == null){
            return JsonResult.failMessage("参数错误");
        }
        return JsonResult.success(frCardTypeService.updateFrCard(frCardType));

    }

    /**
     * 删除卡系列
     * @param id
     * @return
     * @throws YJException
     */
    @GetMapping("/deleteFrCardType")
    public JsonResult deleteFrCardType(@RequestParam String id,@RequestParam String storeId) throws YJException {
        //查询其下关联用户，做提示是否删除
        return frCardTypeService.deleteFrCardType(id,storeId);
    }

    /**
     * 删除卡种
     * @param id
     * @return
     * @throws YJException
     */
    @GetMapping("/deleteCardType")
    public JsonResult deleteCardType(@RequestParam String id) throws YJException {
        //查询其下关联用户，做提示是否删除
        return frCardTypeService.deleteCardType(id);
    }

    /**
     * 通用门店获取所有卡系列及卡种
     * @return
     * @throws YJException
     */
    @GetMapping("/getGeneralStoreList")
    public JsonResult getGeneralStoreList(HttpServletRequest request) throws YJException {
        String code = CookieUtils.getCookieValue(request, "code",true);
        return JsonResult.success(frCardTypeService.getGeneralStoreList(code));
    }


    /**
     * 通用门店修改卡种
     * @param frCardType
     * @return
     * @throws YJException
     */
    @PostMapping("/generalStoreUpdate")
    public JsonResult generalStoreUpdate(FrCardType frCardType) throws YJException {
        if(frCardType == null){
            return JsonResult.failMessage("参数错误");
        }
        return JsonResult.success(frCardTypeService.generalStoreUpdate(frCardType));
    }

    /**
     * 查询卡系列，卡种详情
     * @param id
     * @return
     * @throws YJException
     */
    @GetMapping("/getFrCardTypeDetails")
    public JsonResult getFrCardTypeDetails(@RequestParam String id) throws YJException {
        return frCardTypeService.getFrCardTypeDetails(id);
    }

    /**
     * 查询卡种添加或修改的最后一次操作时间
     * @param type
     * @return
     * @throws YJException
     */
    @GetMapping("/selectFrCardTypeTime")
    public JsonResult selectFrCardTypeTime(@RequestParam Integer type,HttpServletRequest request) throws YJException {
        String code = CookieUtils.getCookieValue(request, "code",true);
        return JsonResult.success(frCardTypeService.selectFrCardTypeTime(type,code));
    }

    /**
     * 根据pid获取卡系列
     * @param pId
     * @return
     * @throws YJException
     */
    @GetMapping("/getFrCardTypeByPid")
    public JsonResult getFrCardTypeByPid(@RequestParam String pId,@RequestParam String storeId) throws YJException {
        return JsonResult.success(frCardTypeService.getFrCardTypeByPid(pId,storeId));
    }

    /**
     * 前台不显示设置（修改卡系列或卡种的is_using状态）
     * @param Ids 卡系列或卡种被选中的id
     * @return
     * @throws YJException
     */
    @GetMapping("/updateIsUsing")
    public JsonResult updateIsUsing(String[] Ids,FrCardType frCardType,Boolean show) throws YJException {
        if(Ids==null || Ids.length<1){
            return JsonResult.failMessage("参数错误");
        }
        if(frCardType == null){
            return JsonResult.failMessage("参数错误");
        }
        return frCardTypeService.updateIsUsing(Ids,frCardType,show);
    }


    /**
     * 获取前台通用门店不显示列表
     * @return
     * @throws YJException
     */
    @GetMapping("/getGeneralStoreNotList")
    public JsonResult getGeneralStoreNotList(HttpServletRequest request) throws YJException {
        String code = CookieUtils.getCookieValue(request, "code",true);
        return frCardTypeService.getGeneralStoreNotList(code);
    }

    /**
     * @Description: 根据客户代码查询卡种列表
     * @Author: 欧俊俊
     * @Date: 2018-10-09 9:33
     */
    @GetMapping("/getCardTypeListByCode")
    public JsonResult getCardTypeListByCode(HttpServletRequest request) throws YJException {
        String code = CookieUtils.getCookieValue(request, "code", true);
        if (StringUtils.isEmpty(code)) {
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        EntityWrapper entityWrapper = new EntityWrapper<>();
        entityWrapper.setSqlSelect("id,card_type_Name cardTypeName").where("p_id = '0' and CustomerCode = {0}",code);
        return JsonResult.success(frCardTypeService.selectList(entityWrapper));
    }


    /**
     * 根据店铺ID获取对应门店的系列卡
     * @param shopId
     * @param type
     * @param request
     * @return
     * @throws YJException
     */
    @GetMapping("/getByShopIdList")
    public JsonResult getByShopIdList(@RequestParam("shopId")String shopId,
                                      @RequestParam("CustomerCode")String CustomerCode,
                                      @RequestParam("type")Integer type, HttpServletRequest request)throws YJException{

        if(StringUtils.isEmpty(shopId) || StringUtils.isEmpty(CustomerCode) || type == null){
           throw new YJException(YJExceptionEnum.REQUEST_NULL);
        }
        return JsonResult.success(frCardTypeService.queryByShopIdList(shopId,CustomerCode,type,2));
    }


    /**
     * @Description: 根据卡类别查询卡名称
     * @Author: 欧俊俊
     * @Date: 2018-10-09 9:33
     */
    @GetMapping("/getCardNameListByCode")
    public JsonResult getCardNameListByCode( String cardId,HttpServletRequest request) throws YJException {
        String code = CookieUtils.getCookieValue(request, "code", true);
        if (StringUtils.isEmpty(code)) {
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        EntityWrapper entityWrapper = new EntityWrapper<>();
        entityWrapper.setSqlSelect("id,card_type_Name cardTypeName")
        .where(" CustomerCode = {0}",code).where("p_id = {0} ",cardId);
        return JsonResult.success(frCardTypeService.selectList(entityWrapper));
    }


    /**
     * 根据店铺ID获取对应门店的系列卡
     * @param request
     * @return
     * @throws YJException
     */
    @GetMapping("/getCardTypeByShopIdList")
    public JsonResult getCardTypeByShopIdList(@RequestParam("CustomerCode")String CustomerCode, HttpServletRequest request)throws YJException{
        if(StringUtils.isEmpty(CustomerCode)){
            throw new YJException(YJExceptionEnum.REQUEST_NULL);
        }
        return JsonResult.success(frCardTypeService.getCardTypeByShopIdList(CustomerCode));
    }


    @GetMapping("/getByShopIdAllList")
    public JsonResult getByShopIdAllList(@RequestParam("shopId")String shopId,
                                      @RequestParam("CustomerCode")String CustomerCode,
                                      @RequestParam("type")Integer type, HttpServletRequest request)throws YJException{
        if(StringUtils.isEmpty(shopId) || StringUtils.isEmpty(CustomerCode) || type == null){
            throw new YJException(YJExceptionEnum.REQUEST_NULL);
        }
        return JsonResult.success(frCardTypeService.queryByShopIdList(shopId,CustomerCode,type,null));
    }

}

