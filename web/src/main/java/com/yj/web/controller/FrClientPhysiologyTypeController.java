package com.yj.web.controller;


import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrClientPhysiologyType;
import com.yj.service.service.IFrClientPhysiologyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 客户生理状况 类型 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-08
 */
@RestController
@RequestMapping("/frClientPhysiologyType")
public class FrClientPhysiologyTypeController {

    @Autowired
    IFrClientPhysiologyTypeService iFrClientPhysiologyTypeService;

    @GetMapping("/getPsychology")
    public JsonResult getPsychology() throws YJException {
        List<FrClientPhysiologyType> frClientPhysiologyTypes = iFrClientPhysiologyTypeService.getPsychology();

        return JsonResult.success(frClientPhysiologyTypes);
    }


}

