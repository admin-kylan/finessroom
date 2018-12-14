package com.yj.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CommonUtils;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.StringUtils;
import com.yj.dal.model.FrCardOrderPayMode;
import com.yj.dal.model.FrCardOrderStop;
import com.yj.service.service.IFrCardOrderPayModeService;
import com.yj.service.service.IFrCardOrderStopService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员卡 停止/冻结订单 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
@RestController
@RequestMapping("/frCardOrderStop")
public class FrCardOrderStopController {

    @Resource
    private  IFrCardOrderStopService service;

    @Resource
    private FileController fileController;

    @Resource
    private IFrCardOrderPayModeService iFrCardOrderPayModeService;


    @GetMapping("getStopCardListByType")
    public JsonResult getStopCardListByType(@RequestParam("cardId")String cardId, @RequestParam("clientId")String clientId,
                                            @RequestParam("CustomerCode")String CustomerCode, @RequestParam("type")Integer type,
                                            HttpServletRequest request)throws YJException{
        if(StringUtils.isEmpty(cardId) || StringUtils.isEmpty(clientId) || type == null ){
            return JsonResult.failMessage("客户ID,会员卡ID获取异常");
        }
        if(StringUtils.isEmpty(CustomerCode)){
            CustomerCode = CookieUtils.getCookieValue(request,"code",true);
            if(StringUtils.isEmpty(CustomerCode)){
                return JsonResult.failMessage("客户代码不能为空");
            }
        }
        FrCardOrderStop frCardOrderStop = new FrCardOrderStop();
        frCardOrderStop.setCustomerCode(CustomerCode);
        frCardOrderStop.setCardId(cardId);
        frCardOrderStop.setClientId(clientId);
        frCardOrderStop.setType(type);
        return service.getStopCardListByType(frCardOrderStop);
    }


    /**
     * @Description: 添加停卡记录
     * @Author: zhangxb
     * @Date: 2018/9/6 10:25
     */
    @PostMapping("/toAddUpdateLoad")
    public JsonResult toAddUpdateLoad(@RequestParam("file") MultipartFile[] files,
                                      @RequestParam("childPath")String childPath, @RequestParam("frStop") String frStop,
                                      @RequestParam("payModel") String payModel, HttpServletRequest request) throws YJException {
        if(StringUtils.isEmpty(childPath) || StringUtils.isEmpty(frStop)){
           return JsonResult.failMessage("请求参数异常，未获取成功,请检查路径");
        }
        // 判断必要的数据需要上传
        FrCardOrderStop frCardOrderStop =  JSONObject.parseObject(frStop,FrCardOrderStop.class);
        // 查看是否需要收费
        if(frCardOrderStop.getTotalPrice() >0){
            if(StringUtils.isEmpty(payModel)){
                return JsonResult.failMessage("支付金额有误，请重新核对");
            }
        }
        if(StringUtils.isEmpty(frCardOrderStop.getCardId()) || StringUtils.isEmpty(frCardOrderStop.getClientId())
            || frCardOrderStop.getStartTime() == null || frCardOrderStop.getEstEndTime() == null){
          return   JsonResult.failMessage("会员卡ID,客户ID,开始时间,预计结束时间未获取");
        }
        if(StringUtils.isEmpty(frCardOrderStop.getCustomerCode())){
            String code = CookieUtils.getCookieValue(request,"code",true);
            if(StringUtils.isEmpty(code)){
                return JsonResult.failMessage("客户代码不能为空");
            }
            frCardOrderStop.setCustomerCode(code);
        }
        if(StringUtils.isEmpty(frCardOrderStop.getPersonnelId())){
            String id = CookieUtils.getCookieValue(request,"id",true);
            if(StringUtils.isEmpty(id)){
                return JsonResult.failMessage("未获取到操作人员id");
            }
            frCardOrderStop.setPersonnelId(id);
        }
        FrCardOrderStop frCardOrderStop1 = service.selectOne(new EntityWrapper<FrCardOrderStop>().where("card_id={0}",frCardOrderStop.getCardId())
             .and("CustomerCode={0}",frCardOrderStop.getCustomerCode()).and("start_time={0}",frCardOrderStop.getStartTime())
                .and("est_end_time={0}",frCardOrderStop.getEstEndTime()).and("est_stop_time={0}",frCardOrderStop.getEstStopTime())
                    .and("client_id={0}",frCardOrderStop.getClientId()).and("price_status={0}",1));
        if(frCardOrderStop1 != null){
            return JsonResult.failMessage("请勿重复添加相同的停卡数据");
        }
        //初始化支付信息
        Map<String,Object> payM = JSONObject.parseObject(payModel,Map.class);
        List<FrCardOrderPayMode>  list = iFrCardOrderPayModeService.getPayModeList(payM,"L",CommonUtils.PAY_MODE_ORDER_TYPE_8);
        StringBuffer imagePath = null;
        String image = " ";
        List<String> imagesList = new ArrayList<>();
        if( files != null && files.length>0){
            //上传图片  准备
            imagePath = new StringBuffer(CookieUtils.getCookieValue(request, "url", true)) ;
            imagePath.append(CookieUtils.getCookieValue(request, "imgPath", true));
            imagesList = fileController.getImgUrlList(files,childPath);
            image = imagePath.toString();
        }
        String shopId = CookieUtils.getCookieValue(request,"shopid",true);
        return service.toInterCardOrderStop(frCardOrderStop,list,image,imagesList,shopId);
    }

    /**
     * 设置冻结
     * @param frCardOrderStop
     * @param request
     * @return
     * @throws YJException
     */
    @PostMapping("/toAddStopType")
    public JsonResult toAddStopType( @RequestBody FrCardOrderStop frCardOrderStop,HttpServletRequest request)throws YJException{
        if(frCardOrderStop == null){
            return JsonResult.failMessage("数据获取错误");
        }
        if(StringUtils.isEmpty(frCardOrderStop.getCardId()) || StringUtils.isEmpty(frCardOrderStop.getClientId())
                || StringUtils.isEmpty(frCardOrderStop.getFlag()) || frCardOrderStop.getStopStatus() == null){
            return JsonResult.failMessage("会员ID，客户ID，操作状态，冻结原因未获取");
        }
        if(StringUtils.isEmpty(frCardOrderStop.getCustomerCode())){
            String code = CookieUtils.getCookieValue(request,"code",true);
            if(StringUtils.isEmpty(code)){
                return JsonResult.failMessage("客户代码未获取");
            }
            frCardOrderStop.setCustomerCode(code);
        }
        if(StringUtils.isEmpty(frCardOrderStop.getPersonnelId())){
            String personnelId = CookieUtils.getCookieValue(request,"id",true);
            if(StringUtils.isEmpty(personnelId)){
                return JsonResult.failMessage("操作人员ID，未获取");
            }
            frCardOrderStop.setPersonnelId(personnelId);
        }
        return service.toAddStopType(frCardOrderStop);
    }


    /**
     * 停卡冲销
     * @param id
     * @param CustomerCode
     * @param cardId
     * @param clientId
     * @param request
     * @return
     */
    @GetMapping("getUpdateStop")
    public JsonResult getUpdateStop(@RequestParam("id")String id,@RequestParam("CustomerCode")String CustomerCode,
                                     @RequestParam("cardId")String cardId,@RequestParam("clientId")String clientId,HttpServletRequest request)throws  YJException{
        if(StringUtils.isEmpty(id) || StringUtils.isEmpty(CustomerCode) || StringUtils.isEmpty(cardId) || StringUtils.isEmpty(clientId)){
            return  JsonResult.failMessage("请求参数有误，请核对停卡id，客户代码，会员卡id，客户id是否有误");
        }
        FrCardOrderStop frCardOrderStop = service.selectById(id);
        if( frCardOrderStop == null ){
            return  JsonResult.failMessage("未查询到对应的停卡记录");
        }
        if(!CustomerCode.equals(frCardOrderStop.getCustomerCode()) || !cardId.equals(frCardOrderStop.getCardId())
                || !clientId.equals(frCardOrderStop.getClientId())){
            return JsonResult.failMessage("未查到对应停卡数据");
        }
        String shopid = CookieUtils.getCookieValue(request,"shopid",true);
        return  service.toUpdateStopType(frCardOrderStop,shopid);
    }

    /**
     * 设置终止状态
     * @param frCardOrderStop
     * @param request
     * @return
     * @throws YJException
     */
    @PostMapping("toStopTerminationOK")
    public JsonResult toStopTerminationOK(@RequestBody FrCardOrderStop frCardOrderStop,HttpServletRequest request)throws YJException{
        if(frCardOrderStop == null){
            return  JsonResult.failMessage("参数获取有误");
        }
        if(StringUtils.isEmpty(frCardOrderStop.getId())|| StringUtils.isEmpty(frCardOrderStop.getCustomerCode()) || StringUtils.isEmpty(frCardOrderStop.getCardId())
        || StringUtils.isEmpty(frCardOrderStop.getClientId()) || StringUtils.isEmpty(frCardOrderStop.getStopUserId())){
            return  JsonResult.failMessage("设置的参数有误：订单id，客户代码，会员卡id，客户id，终止操作人员id");
        }
        String shopId = CookieUtils.getCookieValue(request,"shopid",true);
        return service.toStopTerminationOK(frCardOrderStop,shopId);
    }
}

