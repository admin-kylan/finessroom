package com.yj.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CommonUtils;
import com.yj.common.util.StringUtils;
import com.yj.dal.model.FrCard;
import com.yj.dal.model.FrCardOrderStorage;
import com.yj.service.service.IFrCardOrderStorageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 会员卡 储值表 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-20
 */
@RestController
@RequestMapping("/frCardOrderStorage")
public class FrCardOrderStorageController {

    @Resource
    private IFrCardOrderStorageService service;

    /**
     * 获取储值列表数据
     * @param cardId
     * @param clientId
     * @param CustomerCode
     * @param request
     * @return
     * @throws YJException
     */
    @GetMapping("getStorageCardList")
    public JsonResult getStorageCardList(@RequestParam("cardId")String cardId, @RequestParam("clientId")String clientId,
                                         @RequestParam("CustomerCode")String CustomerCode, HttpServletRequest request)throws YJException{
        if(StringUtils.isEmpty(cardId) || StringUtils.isEmpty(CustomerCode) || StringUtils.isEmpty(clientId)){
            return  JsonResult.failMessage("数据获取错误");
        }
        FrCardOrderStorage frCardOrderStorage = new FrCardOrderStorage();
        frCardOrderStorage.setCustomerCode(CustomerCode);
        frCardOrderStorage.setCardId(cardId);
        frCardOrderStorage.setClientId(clientId);
        return service.queryStorageCardLis(frCardOrderStorage);
    }

    /**
     * 添加储值信息
     * @param map
     * @param request
     * @return
     * @throws YJException
     */
    @PostMapping("addStorageCard")
    public JsonResult addStorageCard(@RequestBody Map<String,String> map,HttpServletRequest request)throws YJException{
        if(map == null){
            return JsonResult.failMessage("数据获取错误");
        }
        String storage = map.get("storage");
        String payModel = map.get("payModel");
        FrCardOrderStorage frCardOrderStorage = null;
        if(!StringUtils.isEmpty(storage)){
            frCardOrderStorage = JSONObject.parseObject(storage,FrCardOrderStorage.class);
        }
        if(frCardOrderStorage == null){
            return JsonResult.failMessage("未获取到储值信息");
        }
        return service.toInterStorageCard(frCardOrderStorage,payModel);
    }

    /**
     * 储值冲销
     * @param id
     * @param CustomerCode
     * @param cardId
     * @param clientId
     * @param request
     * @return
     * @throws YJException
     */
    @GetMapping("toDelStorageCard")
    public JsonResult toDelStorageCard(@RequestParam("id")String id,@RequestParam("CustomerCode")String CustomerCode,
                                    @RequestParam("cardId")String cardId,@RequestParam("clientId")String clientId,
                                       @RequestParam("storageStatus")Integer storageStatus, HttpServletRequest request)throws  YJException {
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(CustomerCode) || StringUtils.isEmpty(cardId)
                || StringUtils.isEmpty(clientId) || storageStatus == null) {
            return JsonResult.failMessage("请求参数有误，请核对储值ID，客户代码，会员卡ID，客户ID是否有误");
        }
        FrCardOrderStorage frCardOrderStorage = new FrCardOrderStorage();
        frCardOrderStorage.setId(id);
        frCardOrderStorage.setCardId(cardId);
        frCardOrderStorage.setClientId(clientId);
        frCardOrderStorage.setCustomerCode(CustomerCode);
        frCardOrderStorage = service.getOldOrderStorage(frCardOrderStorage);
        if(frCardOrderStorage == null){
            return JsonResult.failMessage("查无此储值数据，请重新核对");
        }
        if(frCardOrderStorage.getStatus() == null || frCardOrderStorage.getAuditStatus() == null ){
            return  JsonResult.failMessage("数据审核状态设置有误");
        }
        if(frCardOrderStorage.getStatus() == 1  || frCardOrderStorage.getAuditStatus() == 1){
            return JsonResult.failMessage("已经审核通过的数据，禁止冲销");
        }
        if(storageStatus != frCardOrderStorage.getStorageStatus()){
            return  JsonResult.failMessage("参数有变化，请刷新后重新操作");
        }
        return service.toDelStorageCard(frCardOrderStorage);

    }

    /**
     * 添加退款信息
     * @param frCardOrderStorage
     * @param request
     * @return
     * @throws YJException
     */
    @PostMapping("toRefundSubimt")
    public JsonResult toRefundSubimt(@RequestBody FrCardOrderStorage frCardOrderStorage,HttpServletRequest request)throws YJException{
        if(frCardOrderStorage == null){
            return JsonResult.failMessage("数据获取错误");
        }
        if (StringUtils.isEmpty(frCardOrderStorage.getId()) || StringUtils.isEmpty(frCardOrderStorage.getCustomerCode())
                || StringUtils.isEmpty(frCardOrderStorage.getCardId()) || StringUtils.isEmpty(frCardOrderStorage.getClientId())
                || StringUtils.isEmpty(frCardOrderStorage.getRefundNote()) || StringUtils.isEmpty(frCardOrderStorage.getRefundUserId())
                || StringUtils.isEmpty(frCardOrderStorage.getPersonnelId()) || StringUtils.isEmpty(frCardOrderStorage.getShopId())) {
            return JsonResult.failMessage("请求参数有误，请重新核对");
        }
        if(CommonUtils.STORAGE_PRICE.equals(frCardOrderStorage.getId())){
            return  service.toRefundAllPrice(frCardOrderStorage);
        }
        return service.toRefundSubimt(frCardOrderStorage);
    }

    /**
     * 转让储值
     * @param frCardOrderStorage
     * @param request
     * @return
     * @throws YJException
     */
    @PostMapping("toTransfersStorage")
    public JsonResult toTransfersStorage(@RequestBody FrCardOrderStorage frCardOrderStorage,HttpServletRequest request)throws YJException{
        if(frCardOrderStorage == null){
            return JsonResult.failMessage("数据获取错误");
        }
        if (StringUtils.isEmpty(frCardOrderStorage.getId()) || StringUtils.isEmpty(frCardOrderStorage.getCustomerCode())
                || StringUtils.isEmpty(frCardOrderStorage.getCardId()) || StringUtils.isEmpty(frCardOrderStorage.getClientId())
                || StringUtils.isEmpty(frCardOrderStorage.getGiveName()) || StringUtils.isEmpty(frCardOrderStorage.getGivePhone())
                || StringUtils.isEmpty(frCardOrderStorage.getGiveUserId()) || StringUtils.isEmpty(frCardOrderStorage.getGiveCardId())) {
            return JsonResult.failMessage("请求参数有误，请重新核对");
        }
        return  service.toTransfersStorage(frCardOrderStorage);
    }

    /**
     * 获取指定会员卡的可退总储值金额
     * @param CustomerCode
     * @param cardId
     * @param request
     * @return
     */
    @GetMapping("getAllBlackStorage")
    public JsonResult getAllBlackStorage(@RequestParam("CustomerCode")String CustomerCode,
                                         @RequestParam("cardId")String cardId,@RequestParam("clientId")String clientId,
                                         HttpServletRequest request)throws  YJException{
        return JsonResult.success(service.getAllBlackStorage(CustomerCode,cardId,clientId));
    }

}

