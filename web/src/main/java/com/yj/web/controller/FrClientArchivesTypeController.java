package com.yj.web.controller;


import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrClientArchivesType;
import com.yj.service.service.IFrClientArchivesTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 客户档案（皮肤、头发、塑性抗衰 档案） 类型 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
@RestController
@RequestMapping("/frClientArchivesType")
public class FrClientArchivesTypeController {

    @Autowired
    IFrClientArchivesTypeService iFrClientArchivesTypeService;

    @GetMapping("/getTypeAll")
    public JsonResult getTypeAll(Integer type)throws YJException {
        List<FrClientArchivesType> frClientArchivesTypes = iFrClientArchivesTypeService.getTypeAll(type);
        return JsonResult.success(frClientArchivesTypes);
    }

}

