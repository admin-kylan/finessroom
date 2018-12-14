package com.yj.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sun.org.apache.regexp.internal.RE;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.StringUtils;
import com.yj.dal.model.FrCardTypeSplitSet;
import com.yj.service.service.IFrCardTypeSplitSetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 会员卡分期付款设置 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
@RestController
@RequestMapping("/frCardTypeSplitSet")
public class FrCardTypeSplitSetController {

    @Resource
    private IFrCardTypeSplitSetService service;

    @GetMapping("getCardTypeSplitList")
    public JsonResult getCardTypeSplitList(@RequestParam("cardTypeId")String cardTypeId,
                                            @RequestParam("code") String code)throws YJException{
        if(StringUtils.isEmpty(cardTypeId) || StringUtils.isEmpty(code)){
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        List<FrCardTypeSplitSet> list = service.selectList(new EntityWrapper<FrCardTypeSplitSet>()
                               .where("CustomerCode={0}",code).and("is_using={0}",1).and("card_type_id={0}",cardTypeId));
        return JsonResult.success(list);
    }

}

