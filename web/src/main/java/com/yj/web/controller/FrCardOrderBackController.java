package com.yj.web.controller;


import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.StringUtils;
import com.yj.dal.model.FrCardOrderBack;
import com.yj.dal.model.FrCardOrderInfo;
import com.yj.service.service.IFrCardOrderBackService;
import com.yj.service.service.IFrCardOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.Year;
import java.util.Map;

/**
 * <p>
 * 会员卡退卡订单 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-19
 */
@RestController
@RequestMapping("/frCardOrderBack")
public class FrCardOrderBackController {

    @Autowired
    IFrCardOrderBackService service;

    /**
     * 获取退卡信息
     * @param clientId
     * @param CustomerCode
     * @param request
     * @return
     * @throws YJException
     */
    @GetMapping("getBlackCardList")
    public JsonResult getBlackCardList(@RequestParam("clientId")String clientId,
                                       @RequestParam("CustomerCode")String CustomerCode,HttpServletRequest request)throws YJException{
        if(StringUtils.isEmpty(clientId)){
            throw  new YJException(YJExceptionEnum.CLIENTID_NOT_FOUND);
        }
        if(StringUtils.isEmpty(CustomerCode)){
            CustomerCode = CookieUtils.getCookieValue(request,"code",true);
            if(StringUtils.isEmpty(CustomerCode)){
                throw  new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
            }
        }
        FrCardOrderBack frCardOrderBack = new FrCardOrderBack();
        frCardOrderBack.setClientId(clientId);
        frCardOrderBack.setCustomerCode(CustomerCode);
        return JsonResult.success(service.queryBlackCardList(frCardOrderBack));
    }

    /**
     * 添加退卡信息
     * @param frCardOrderBack
     * @param request
     * @return
     * @throws YJException
     */
    @PostMapping("toBlackSubimt")
    public JsonResult toBlackSubimt(@RequestBody FrCardOrderBack frCardOrderBack,HttpServletRequest request)throws  YJException{
        if(frCardOrderBack == null){
            throw new YJException(YJExceptionEnum.OBJECT_NOT_FOUND);
        }
        if(StringUtils.isEmpty(frCardOrderBack.getClientId()) || StringUtils.isEmpty(frCardOrderBack.getCardId())){
            throw new YJException(YJExceptionEnum.REQUEST_NULL);
        }
        if(StringUtils.isEmpty(frCardOrderBack.getCustomerCode())){
            String code = CookieUtils.getCookieValue(request,"code",true);
            if(StringUtils.isEmpty(code)){
                throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
            }
            frCardOrderBack.setCustomerCode(code);
        }
        String creatId = CookieUtils.getCookieValue(request,"id",true);
        String shopId = CookieUtils.getCookieValue(request,"shopid",true);
        frCardOrderBack.setCreateUserId(creatId);
        return service.toInsertBlace(frCardOrderBack,shopId);
    }

}

