package com.yj.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.*;
import com.yj.dal.model.*;
import com.yj.service.service.IFrCardNumService;
import com.yj.service.service.IFrCardOrderAllotSetService;
import com.yj.service.service.IFrCardOrderPayModeService;
import com.yj.service.service.IFrCardSupplyRecordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员卡补卡 、续卡、转卡、卡升级记录 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-06
 */
@RestController
@RequestMapping("/frCardSupplyRecord")
public class FrCardSupplyRecordController {

    @Resource
    private IFrCardSupplyRecordService service;
    @Resource
    private IFrCardOrderPayModeService iFrCardOrderPayModeService;
    @Resource
    private IFrCardOrderAllotSetService iFrCardOrderAllotSetService;


    @GetMapping("getCarDsupplyRecordList")
    public JsonResult getCarDsupplyRecordList(@RequestParam("clientId") String clientId, @RequestParam("CustomerCode") String CustomerCode,
                                              @RequestParam("type") Integer type,HttpServletRequest request)throws YJException{
        FrCardSupplyRecord frCardSupplyRecord = new FrCardSupplyRecord();
        frCardSupplyRecord.setCustomerCode(CustomerCode);
        frCardSupplyRecord.setClientId(clientId);
        frCardSupplyRecord.setType(type);
        return JsonResult.success(service.queryCarDsupplyRecordList(frCardSupplyRecord));
    }

    /**
     *  添加补卡信息
     * @param map
     * @param request
     * @return
     * @throws YJException
     */
    @PostMapping("addSupply1Ok")
    public JsonResult addSupply1Ok(@RequestBody Map<String,String> map,HttpServletRequest request)throws YJException{
        if(map == null){
            return JsonResult.failMessage("数据获取错误");
        }
        String supply = map.get("supply1");
        String payModel = map.get("payModel");
        FrCardSupplyRecord supply1 = null;
        if(!StringUtils.isEmpty(supply)){
            supply1 = JSONObject.parseObject(supply,FrCardSupplyRecord.class);
        }
        if(supply1 == null){
            return JsonResult.failMessage("未获取到补卡信息");
        }
        //补卡
        supply1.setType(CommonUtils.CARD_SUPPLY_RECORD_TYPE_1);
        if(StringUtils.isEmpty(supply1.getClientId()) || StringUtils.isEmpty(supply1.getOriCardId())
                || StringUtils.isEmpty(supply1.getNewCardNo()) || StringUtils.isEmpty(supply1.getExternalNo())){
            return JsonResult.failMessage("参数有误");
        }
        if(StringUtils.isEmpty(supply1.getCustomerCode())){
            String code = CookieUtils.getCookieValue(request,"code",true);
            if(StringUtils.isEmpty(code)){
                return JsonResult.failMessage("客户代码不能为空");
            }
            supply1.setCustomerCode(code);
        }
        String isFlage = service.isFlagNewCardNo(supply1.getNewCardNo(),supply1.getCustomerCode(),new HashMap<>());
        if(!"true".equals(isFlage)){
            return JsonResult.failMessage(isFlage);
        }
        Map<String,Object> payM = JSONObject.parseObject(payModel,Map.class);
        List<FrCardOrderPayMode> frCardOrderPayModes = iFrCardOrderPayModeService.getPayModeList(payM,"L", CommonUtils.PAY_MODE_ORDER_TYPE_4);
        String shopId = CookieUtils.getCookieValue(request,"shopid",true);
        return  JsonResult.success(service.toInsertAndAdd(supply1,frCardOrderPayModes,shopId));
    }



    /**
     * 添加续卡订单信息         -------------------------------需修改
     * @param map
     * @param request
     * @return
     * @throws YJException
     */
    @PostMapping("addContinueList")
    public JsonResult addContinueList(@RequestBody Map<String,String> map,HttpServletRequest request)throws YJException{
        if(map == null ){
            throw  new YJException(YJExceptionEnum.OBJECT_NOT_FOUND);
        }
        //续卡信息
        String supply = map.get("continueMap");
        // 会员卡
        String cardMap = map.get("cardMap");
        // 会员卡订单
        String cardInfoMap = map.get("cardInfoMap");
        // 支付
        String payModel = map.get("payModel");
        // 分期
        String orderSplitId = map.get("orderSplitId");
        //业绩分配
        String orderAllotSetList = map.get("orderAllotSetList");
        //业绩分配
        String orderAllotSet = map.get("orderAllotSet");
        if(StringUtils.isEmpty(supply) || StringUtils.isEmpty(cardMap) || StringUtils.isEmpty(cardInfoMap) || StringUtils.isEmpty(payModel)){
            throw new  YJException(YJExceptionEnum.PARAM_ERROR);
        }
        //续卡信息
        FrCardSupplyRecord supply1 =  JSONObject.parseObject(supply,FrCardSupplyRecord.class);
        //会员卡
        FrCard frCard  = JSONObject.parseObject(cardMap,FrCard.class);
        //业绩分配
        List<FrCardOrderAllotSet> frCardOrderAllotSetList = iFrCardOrderAllotSetService.initFrCardOrderAllotSetData(orderAllotSetList,orderAllotSet,1);
        //会员卡订单
        FrCardOrderInfo frCardOrderInfo  = JSONObject.parseObject(cardInfoMap,FrCardOrderInfo.class);
        //初始化支付信息
        Map<String,Object> payM = JSONObject.parseObject(payModel,Map.class);
        List<FrCardOrderPayMode> frCardOrderPayModes = iFrCardOrderPayModeService.getPayModeList(payM,"L", CommonUtils.PAY_MODE_ORDER_TYPE_2);
        if(frCard == null){
            throw new  YJException(YJExceptionEnum.OBJECT_NOT_FOUND);
        }
        if(StringUtils.isEmpty(frCard.getCustomerCode())){
            String code = CookieUtils.getCookieValue(request,"code",true);
            frCard.setCustomerCode(code);
        }
        Map<String,String> mapS = new HashMap<>();
        mapS.put("orderSplitId",orderSplitId);
        mapS.put("messFlag","会员卡续卡订单");
        Map<String,Integer> mapI = new HashMap<>();
        mapI.put("infoType",CommonUtils.CARD_ORDRE_INFO_TYPE_2);
        return  service.addContinueList(supply1,frCard,frCardOrderInfo,frCardOrderPayModes,frCardOrderAllotSetList,mapS,mapI);
    }

    /**
     * 获取指定会员的指定会员卡续卡列表
     * @param pages
     * @param rows
     * @param clientId
     * @param cardId
     * @param CustomerCode
     * @return
     * @throws YJException
     */
    @GetMapping("getContinueCardList")
    public JsonResult getContinueCardList(@RequestParam("pages")String pages, @RequestParam("rows")String rows,
                                          @RequestParam("clientId")String clientId, @RequestParam("cardId")String cardId,
                                          @RequestParam("CustomerCode")String CustomerCode) throws YJException {
        if(StringUtils.isEmpty(CustomerCode) ||  StringUtils.isEmpty(clientId) || StringUtils.isEmpty(cardId) ){
            JsonResult.parameterError();
        }
        Page page = null;
        if(!StringUtils.isEmpty(pages) && !StringUtils.isEmpty(rows)) {
            if (!"-1".equals(pages) && !"-1".equals(rows)) {
                Map map = new HashMap();
                map.put("limit", rows);//每页多少条
                map.put("page", pages);//当前页
                page = new Query<FrClient>(map).getPage();
            }
        }
        Map<String,Object>  map =  new HashMap<>();
        map.put("cardTypeId","");
        map.put("CustomerCode",CustomerCode);
        map.put("clientId",clientId);
        map.put("newCardId",cardId);
        PageUtils pageUtil = service.queryContinueCardLis(page,map);
        map.put("data",pageUtil);
        return JsonResult.success(map);
    }



    /**
     * 添加转卡、卡升级订单
     * @param map
     * @param request
     * @return
     * @throws YJException
     */
    @PostMapping("addTransferCard")
    public JsonResult addTransferCard(@RequestBody Map<String,String> map,HttpServletRequest request)throws YJException{
        if(map == null ){
            throw  new YJException(YJExceptionEnum.OBJECT_NOT_FOUND);
        }
        //续卡信息
        String transferCard = map.get("transferCard");
        // 支付
        String payModel = map.get("payModel");
        // 支付计算细节
        String payMentMoney = map.get("payMentMoney");
        // 要转卡的类型卡的Id
        String newCardTypeId = map.get("newCardTypeId");
        // 销售此类型卡的门店
        String shopId = map.get("shopId");
        if(StringUtils.isEmpty(transferCard) || StringUtils.isEmpty(payModel) || StringUtils.isEmpty(payMentMoney)
                || StringUtils.isEmpty(newCardTypeId) || StringUtils.isEmpty(shopId)){
            throw new  YJException(YJExceptionEnum.PARAM_ERROR);
        }
        //转卡或者卡升级
        FrCardSupplyRecord supply3 =  JSONObject.parseObject(transferCard,FrCardSupplyRecord.class);
        if(supply3 == null){
            return  JsonResult.failMessage("数据获取失败");
        }
        if(supply3.getType() == null || StringUtils.isEmpty(supply3.getCustomerCode())){
            return  JsonResult.failMessage("请设置操作类型是转卡还是卡升级？客户代码不能为空");
        }
        //初始化支付信息
        Map<String,Object> payM = JSONObject.parseObject(payModel,Map.class);
        List<FrCardOrderPayMode> frCardOrderPayModes = iFrCardOrderPayModeService.getPayModeList(payM,"L", CommonUtils.PAY_MODE_ORDER_TYPE_2);
        Map<String,String> mapS = new HashMap<>();
//        mapS.put("orderSplitId","");
        String  mess  = "会员卡转卡订单";
        Integer type = supply3.getType();
        if(CommonUtils.CARD_SUPPLY_RECORD_TYPE_4 == type){
            mess  = "会员卡卡升级订单";
        }
        mapS.put("messFlag",mess);
        mapS.put("payMentMoney",payMentMoney);
        mapS.put("newCardTypeId",newCardTypeId);
        mapS.put("shopId",shopId);
        Map<String,Integer> mapI = new HashMap<>();
        mapI.put("infoType",type);
        return  service.addTransferCard(supply3,frCardOrderPayModes,mapS,mapI);
    }
}

