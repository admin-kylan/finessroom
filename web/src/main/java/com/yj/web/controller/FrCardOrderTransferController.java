package com.yj.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CommonUtils;
import com.yj.common.util.StringUtils;
import com.yj.dal.model.FrCardOrderPayMode;
import com.yj.dal.model.FrCardOrderTransfer;
import com.yj.service.service.IFrCardOrderPayModeService;
import com.yj.service.service.IFrCardOrderTransferService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员卡 转让订单 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-23
 */
@RestController
@RequestMapping("/frCardOrderTransfer")
public class FrCardOrderTransferController {

    @Resource
    private IFrCardOrderTransferService service;
    @Resource
    private  IFrCardOrderPayModeService iFrCardOrderPayModeService;


    /**
     * 获取转让列表
     * @param cardId
     * @param CustomerCode
     * @param request
     * @return
     * @throws YJException
     */
    @GetMapping("getTransferList")
    public JsonResult getTransferList(@RequestParam("cardId")String cardId, @RequestParam("CustomerCode")String CustomerCode, HttpServletRequest request)throws YJException {
        if(StringUtils.isEmpty(cardId) || StringUtils.isEmpty(CustomerCode)){
            return JsonResult.failMessage("会员卡ID、客户代码需提供");
        }
        return service.quereyTransferList(cardId,CustomerCode);
    }

    /**
     * 添加转让信息
     * @param map
     * @param request
     * @return
     * @throws YJException
     */
    @PostMapping("addTransferSub")
    public JsonResult addTransferSub(@RequestBody Map<String,String> map, HttpServletRequest request)throws YJException{
        if(map == null ){
            throw  new YJException(YJExceptionEnum.OBJECT_NOT_FOUND);
        }
        String orderTransfer = map.get("orderTransfer");
        String payModel = map.get("payModel");
        if(StringUtils.isEmpty(orderTransfer) ){
            return JsonResult.failMessage("参数设置有误");
        }
        //初始化转让信息
        FrCardOrderTransfer frCardOrderTransfer = JSONObject.parseObject(orderTransfer,FrCardOrderTransfer.class);
        if(frCardOrderTransfer == null){
            throw  new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        if(frCardOrderTransfer.getTotalPrice() != null && frCardOrderTransfer.getTotalPrice() >0){
            if(StringUtils.isEmpty(payModel)){
                return JsonResult.failMessage("请支付转让手续费");
            }
        }
        List<FrCardOrderPayMode> frCardOrderPayModes = null;
        if(!StringUtils.isEmpty(payModel)){
            //初始化支付信息
            Map<String,Object> payM = JSONObject.parseObject(payModel,Map.class);
            frCardOrderPayModes = iFrCardOrderPayModeService.getPayModeList(payM,"L", CommonUtils.PAY_MODE_ORDER_TYPE_9);
        }
        return  service.addTransferSub(frCardOrderTransfer,frCardOrderPayModes);
    }


    /**
     *  转让冲销
     * @param map
     * @param request
     * @return
     * @throws YJException
     */
    @PostMapping("toDelTransferCard")
    public JsonResult toDelTransferCard(@RequestBody Map<String,Object> map,HttpServletRequest request)throws  YJException {
        if(map == null ){
            throw  new YJException(YJExceptionEnum.OBJECT_NOT_FOUND);
        }
        String CustomerCode = this.getToString(map,"CustomerCode");
        String cardId = this.getToString(map,"cardId");
        String clientId = this.getToString(map,"clientId");
        String id = this.getToString(map,"id");
        String personnelId = this.getToString(map,"personnelId");
        String shopId = this.getToString(map,"shopId");
        String status = this.getToString(map,"transferStatus");
        Integer transferStatus = null;
        if(!StringUtils.isEmpty(status)){
            try{
                transferStatus = Integer.valueOf(status);
            }catch(Exception e){
                transferStatus = null;
            }
        }
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(CustomerCode) || StringUtils.isEmpty(cardId)
                || StringUtils.isEmpty(clientId) || transferStatus == null) {
            return JsonResult.failMessage("请求参数有误，请核对储值ID，客户代码，会员卡ID，客户ID是否有误");
        }
        FrCardOrderTransfer frCardOrderTransfer = service.selectOne(new EntityWrapper<FrCardOrderTransfer>().where("id={0}",id)
                .and("card_id={0}",cardId).and("catcher_client_id={0}",clientId).and("CustomerCode={0}",CustomerCode));
        if(frCardOrderTransfer == null){
            return JsonResult.failMessage("查无此转让数据，请重新核对");
        }
        if(transferStatus != frCardOrderTransfer.getTransferStatus()){
            return  JsonResult.failMessage("参数有变化，请刷新后重新操作");
        }
        return service.toDelTransferCard(frCardOrderTransfer,shopId,personnelId);
    }

    public String getToString(Map<String,Object> map,String key){
        String val = "";
        if(map != null){
            Object value = map.get(key);
            if(value != null){
                val = value.toString();
            }
        }
        return  val;
    }


}

