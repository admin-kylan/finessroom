package com.yj.web.controller;


import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrCardOriginalSet;
import com.yj.service.service.IFrCardOriginalSetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员卡购买前，后台设置保存 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-10
 */
@RestController
@RequestMapping("/frCardOriginalSet")
public class FrCardOriginalSetController {

    @Resource
    private IFrCardOriginalSetService service;

}

