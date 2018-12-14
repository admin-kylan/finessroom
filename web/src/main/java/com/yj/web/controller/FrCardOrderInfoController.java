package com.yj.web.controller;


import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.PageUtil;
import com.yj.common.util.StringUtils;
import com.yj.dal.dao.FrCardOrderInfoMapper;
import com.yj.dal.model.FrCard;
import com.yj.dal.model.FrCardOrderInfo;
import com.yj.service.service.IFrCardOrderInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 会员卡订单记录 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-26
 */
@RestController
@RequestMapping("/frCardOrderInfo")
public class FrCardOrderInfoController {
    @Resource
    private IFrCardOrderInfoService frCardOrderInfoService;

    /**
     * 生成随机订单号（判断是否已存在）
     * @return
     */
    @GetMapping("getOrderNo")
    public JsonResult getOrderNo(){
        return JsonResult.success(frCardOrderInfoService.getOrderNo());
    }

    /**
     * 获取会员卡订单列表
     * @param request
     * @return
     */
    @GetMapping("getCardOrederList")
    public JsonResult getCardOrederList(HttpServletRequest request,PageUtil<FrCard> pageUtil) throws YJException {
        if(StringUtils.isEmpty(pageUtil.getClientId())){
            throw new YJException(YJExceptionEnum.CLIENTID_NOT_FOUND);
        }
        if(StringUtils.isEmpty(pageUtil.getCode()) && StringUtils.isEmpty(pageUtil.getCustomerCode())){
            String code = CookieUtils.getCookieValue(request, "code",true);
            if(StringUtils.isEmpty(code)){
                throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
            }
            pageUtil.setCode(code);
            pageUtil.setCustomerCode(code);
        }
        return JsonResult.success(frCardOrderInfoService.getCardOrederList(pageUtil.getCode(),pageUtil));
    }

    /**
     * 获取退卡信息
     * @param cardId
     * @param code
     * @param clientId
     * @return
     * @throws YJException
     */
    @GetMapping("getBlaceCardData")
    public JsonResult getBlaceCardData(@RequestParam("cardId")String cardId, @RequestParam("code")String code, @RequestParam("clientId")String clientId)throws YJException{
        if(StringUtils.isEmpty(cardId) || StringUtils.isEmpty(code) || StringUtils.isEmpty(clientId)){
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        FrCardOrderInfo frCardOrderInfo = new FrCardOrderInfo();
        frCardOrderInfo.setCustomerCode(code);
        frCardOrderInfo.setCardId(cardId);
        frCardOrderInfo.setClientId(clientId);
        return  JsonResult.success(frCardOrderInfoService.getBlackCardData(frCardOrderInfo));
    }


    /**
     * 根据会员卡和客户信息获取订单及卡种信息
     * @param cardId
     * @param code
     * @param clientId
     * @param request
     * @return
     * @throws YJException
     */
    @GetMapping("queryCardAndType")
    public JsonResult queryCardAndType(@RequestParam("cardId")String cardId, @RequestParam("code")String code,
                                       @RequestParam("clientId")String clientId,HttpServletRequest request)throws YJException{
        FrCardOrderInfo frCardOrderInfo = new FrCardOrderInfo();
        frCardOrderInfo.setClientId(clientId);
        frCardOrderInfo.setCardId(cardId);
        frCardOrderInfo.setCustomerCode(code);
        return JsonResult.success(frCardOrderInfoService.queryCardAndType(frCardOrderInfo));
    }
}

