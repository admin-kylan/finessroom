package com.yj.web.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CommonUtils;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.StringUtils;
import com.yj.dal.model.*;
import com.yj.service.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员卡补余订单 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-06
 */
@RestController
@RequestMapping("/frCardOrderComplement")
public class FrCardOrderComplementController {

    @Autowired
    IFrCardOrderComplementService service;

    @Autowired
    IFrCardOrderInfoService iFrCardOrderInfoService;

    @Autowired
    IFrCardOrderSplitSetService iFrCardOrderSplitSetService;

    @Autowired
    IFrCardOrderPayModeService iFrCardOrderPayModeService;

    @Autowired
    IFrCardOrderAllotSetService iFrCardOrderAllotSetService;

    /**
     * 获取补余列表
     * @param cardId
     * @param CustomerCode
     * @param clientId
     * @param request
     * @return
     * @throws YJException
     */
    @GetMapping("getComplementList")
    public JsonResult getComplementList(@RequestParam("cardId") String cardId, @RequestParam("CustomerCode") String CustomerCode,
                                        @RequestParam("clientId") String clientId,HttpServletRequest request)throws YJException{
        if(StringUtils.isEmpty(cardId) || StringUtils.isEmpty(clientId)){
            throw  new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        //未获取到尝试冲cookie获取
        if(StringUtils.isEmpty(CustomerCode)){
            CustomerCode = CookieUtils.getCookieValue(request,"code",true);
            if(StringUtils.isEmpty(CustomerCode)){
                throw  new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
            }
        }
        //获取补余列表
        FrCardOrderComplement frCardOrderComplement = new FrCardOrderComplement();
        frCardOrderComplement.setCardId(cardId);
        frCardOrderComplement.setClientId(clientId);
        frCardOrderComplement.setCustomerCode(CustomerCode);
        List<Map<String, Object>> list = service.getComplementList(frCardOrderComplement);
        //获取会员卡需补余的信息
        Map<String,Object> mapOrder = iFrCardOrderInfoService.queryOrderInfoPrice(cardId,CustomerCode);
        Integer num = null;
        String orderInfoId = null;
        List<Map<String,Object>> frOrderSetList = new ArrayList<>();
        if(mapOrder != null){
            //获取支付类型
           Object payType = mapOrder.get("payType");
           //获取订单id
           Object orderId = mapOrder.get("id");
           if(payType != null && payType instanceof  Integer){
               num = (Integer)payType;
           }
            if(orderId != null){
                orderInfoId = orderId.toString();
            }
            if(num != null && !StringUtils.isEmpty(orderInfoId)){
               if(num == 2 || num ==5){
                   frOrderSetList = iFrCardOrderSplitSetService.queryFrCardSplitSet(orderInfoId,CustomerCode);
               }
            }
        }

        Map<String,Object> map = new HashMap<>();
        map.put("orderMap",mapOrder);
        map.put("list",list);
        map.put("frOrderSetList",frOrderSetList);
        return JsonResult.success(map);
    }

    /**
     * 添加补余信息
     * @param map
     * @param request
     * @return
     * @throws YJException
     */
    @PostMapping("addComplementList")
    public JsonResult addCardDataList(@RequestBody Map<String,String> map,HttpServletRequest request)throws YJException{
        if(map == null ){
            throw  new YJException(YJExceptionEnum.OBJECT_NOT_FOUND);
        }
        //补余信息
        String complement = map.get("complement");
        //支付信息
        String payModel = map.get("payModel");
        //补余的订单Id----- 一般用于分期补余，定金补余没有订单
        String orderSetIdList = map.get("orderSetIdList");
        //业绩分配
        String orderAllotSetList = map.get("orderAllotSetList");
        //业绩分配
        String orderAllotSet = map.get("orderAllotSet");
        if(StringUtils.isEmpty(complement) || StringUtils.isEmpty(orderSetIdList)){
          return JsonResult.failMessage("补余信息异常");
        }
        //初始化补订单信息
        FrCardOrderComplement frCardOrderComplement = JSONObject.parseObject(complement,FrCardOrderComplement.class);
        if(frCardOrderComplement == null){
            throw new  YJException(YJExceptionEnum.SERVER_ERROR);
        }
        //若对象的客户代码未获取，尝试冲cookie获取
        if(StringUtils.isEmpty(frCardOrderComplement.getCustomerCode())){
            String code = CookieUtils.getCookieValue(request,"code",true);
            frCardOrderComplement.setCustomerCode(code);
        }
        List<FrCardOrderPayMode>  frCardOrderPayModes = null;
        if(!StringUtils.isEmpty(payModel)){
            //支付金额初始化
            Map<String,Object> payM = JSONObject.parseObject(payModel,Map.class);
            frCardOrderPayModes = iFrCardOrderPayModeService.getPayModeList(payM,"L",CommonUtils.PAY_MODE_ORDER_TYPE_6);
        }
        //业绩分配初始化
        List<FrCardOrderAllotSet> frCardOrderAllotSetList = iFrCardOrderAllotSetService.initFrCardOrderAllotSetData(orderAllotSetList,orderAllotSet,CommonUtils.ALLOT_SET_ORDER_TYPE_6);
        return  service.toAddComplemen(frCardOrderComplement,frCardOrderPayModes,frCardOrderAllotSetList,orderSetIdList);
    }

    /**
     * 补余冲销
     * @param id
     * @param clientId
     * @param cardId
     * @param code
     * @param personnelId
     * @param request
     * @return
     */
    @GetMapping("toUpdateOrderStart")
    public JsonResult toUpdateOrderStart(@RequestParam("id") String id, @RequestParam("clientId")String clientId,
                                         @RequestParam("cardId")String cardId, @RequestParam("CustomerCode")String code,
                                         @RequestParam("personnelId")String personnelId, HttpServletRequest request)throws YJException {
        if(StringUtils.isEmpty(id) || StringUtils.isEmpty(clientId)|| StringUtils.isEmpty(cardId)){
            return  JsonResult.failMessage("ID,客户ID，会员卡ID 获取异常");
        }
        if(StringUtils.isEmpty(code) ||  StringUtils.isEmpty(personnelId)){
            code = CookieUtils.getCookieValue(request,"code",true);
            personnelId = CookieUtils.getCookieValue(request,"id",true);
            if(StringUtils.isEmpty(code)){
                return  JsonResult.failMessage("客户代码获取异常");
            }
            if(StringUtils.isEmpty(personnelId)){
                return  JsonResult.failMessage("员工ID获取异常");
            }
        }
        FrCardOrderComplement frCardOrderComplement =
                service.selectOne(new EntityWrapper<FrCardOrderComplement>().where("id={0}",id).
                                and("CustomerCode={0}",code).and("card_id={0}",cardId).and("client_id={0}",clientId));
        if(frCardOrderComplement == null){
            return  JsonResult.failMessage("查无此订单信息");
        }
        frCardOrderComplement.setUpdateUserId(personnelId);
        return  JsonResult.success(service.toUpdateOrderStart(frCardOrderComplement));
    }
}

