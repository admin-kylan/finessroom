package com.yj.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.StringUtils;
import com.yj.dal.model.FrCardTypeSplitSet;
import com.yj.dal.model.FrCardTypeSplitSetDd;
import com.yj.service.service.IFrCardTypeSplitSetDdService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 会员卡分期付款设置 详细 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
@RestController
@RequestMapping("/frCardTypeSplitSetDd")
public class FrCardTypeSplitSetDdController {

    @Resource
    private IFrCardTypeSplitSetDdService service;

    @GetMapping("getCardTypeSplitDd")
    public JsonResult getCardTypeSplitList(@RequestParam("splitSetId")String splitSetId)throws YJException {
        if(StringUtils.isEmpty(splitSetId)){
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        List<FrCardTypeSplitSetDd> list = service.selectList(new EntityWrapper<FrCardTypeSplitSetDd>().where("split_set_id={0}",splitSetId));
        return JsonResult.success(list);
    }

}

