package com.yj.web.controller;


import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrClientMentalityType;
import com.yj.service.service.IFrClientMentalityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 客户心理情况 类型 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-08
 */
@RestController
@RequestMapping("/frClientMentalityType")
public class FrClientMentalityTypeController {

    @Autowired
    IFrClientMentalityTypeService frClientMentalityTypeService;

    @GetMapping("/getMentality")
    public JsonResult getMentality() throws YJException {
        List<FrClientMentalityType> frClientMentalityTypes = frClientMentalityTypeService.getMentality();
        return JsonResult.success(frClientMentalityTypes);
    }

}

