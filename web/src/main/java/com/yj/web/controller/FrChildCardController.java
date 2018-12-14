package com.yj.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.StringUtils;
import com.yj.dal.model.FrCard;
import com.yj.dal.model.FrCardOriginalSet;
import com.yj.dal.model.FrChildCard;
import com.yj.service.service.IFrCardOriginalSetService;
import com.yj.service.service.IFrCardService;
import com.yj.service.service.IFrChildCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 子卡 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-04
 */
@RestController
@RequestMapping("/frChildCard")
public class FrChildCardController {

    @Autowired
    IFrChildCardService service;

    @Autowired
    IFrCardOriginalSetService iFrCardOriginalSetService;


    /**
     * 获取指定子卡列表
     * @param cardId
     * @param code
     * @param request
     * @return
     * @throws YJException
     */
    @GetMapping("/getChildCardList")
    public JsonResult getChildCardList(@RequestParam("cardId") String cardId,@RequestParam("code")String code,HttpServletRequest request) throws YJException{
        if(StringUtils.isBlank(cardId)){
            return JsonResult.parameterError();
        }
        if(StringUtils.isEmpty(code)){
            code = CookieUtils.getCookieValue(request, "code", true);
            if(StringUtils.isEmpty(code)){
                throw  new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
            }
        }
        FrCardOriginalSet frCardOriginalSet= new FrCardOriginalSet();
        frCardOriginalSet.setCardId(cardId);
        Map<String,Object> map = iFrCardOriginalSetService.querySelectOneFrCard(frCardOriginalSet);
        List<Map<String,Object>> frChildList = new ArrayList<>();
        if( map != null){
            FrChildCard frChildCard = new FrChildCard();
            frChildCard.setParentCardId(cardId);
            frChildCard.setCustomerCode(code);
            frChildList = service.selectChildCardList(frChildCard);
        }
        Map<String,Object> list = new HashMap<>();
        list.put("list",frChildList);
        list.put("frSet",map);
        return JsonResult.success(list);
    }

    /**
     * 添加子卡
     * @param request
     * @return
     */
    @PostMapping("insertOrUpdateChildCard")
    public JsonResult insertOrUpdateChildCard(HttpServletRequest request,FrChildCard frChildCard) throws YJException {
        if(frChildCard == null){
            return JsonResult.failMessage("参数错误");
        }
        if(StringUtils.isEmpty(frChildCard.getCustomerCode())){
            String code = CookieUtils.getCookieValue(request, "code", true);
            if(StringUtils.isEmpty(code)){
                throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
            }
            frChildCard.setCustomerCode(code);
        }
        if(StringUtils.isEmpty(frChildCard.getHandlePersonalId())){
            String handlePersonalId = CookieUtils.getCookieValue(request, "id", true);
            if(StringUtils.isEmpty(handlePersonalId)){
                throw new YJException(YJExceptionEnum.PERSONNEL_ID_NOT_NULL);
            }
            frChildCard.setHandlePersonalId(handlePersonalId);
        }
        FrChildCard frChildCardTwo = service.selectChildCardOne(frChildCard);
        if(frChildCardTwo != null && !StringUtils.isEmpty(frChildCardTwo.getId())){
           return JsonResult.failMessage("请勿重复添加相同信息");
        }
        boolean toInsert = service.saveChildCard(frChildCard);
        if(toInsert){
            return JsonResult.successMessage("添加成功");
        }
        return JsonResult.failMessage("添加失败");
    }





}

