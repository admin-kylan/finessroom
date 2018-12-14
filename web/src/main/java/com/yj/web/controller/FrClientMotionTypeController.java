package com.yj.web.controller;


import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrClientLifeType;
import com.yj.dal.model.FrClientMotionType;
import com.yj.service.service.IFrClientMotionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 客户运动档案 类型 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-02
 */
@RestController
@RequestMapping("/frClientMotionType")
public class FrClientMotionTypeController {

    @Autowired
    IFrClientMotionTypeService iFrClientMotionTypeService;

    @GetMapping("/getSports")
    public JsonResult getSports() throws YJException {
        List<FrClientMotionType> frClientMotionTypes = iFrClientMotionTypeService.getSports();
        return JsonResult.success(frClientMotionTypes);
    }


}

