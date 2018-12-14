package com.yj.web.controller;


import com.yj.common.result.JsonResult;
import com.yj.service.service.IFrCardAgreementService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 会员卡协议号关系表 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-24
 */
@RestController
@RequestMapping("/frCardAgreement")
public class FrCardAgreementController {

    @Resource
    private IFrCardAgreementService service;



}

