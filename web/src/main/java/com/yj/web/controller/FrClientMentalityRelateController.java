package com.yj.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrClientMentalityRelate;
import com.yj.service.service.IFrClientMentalityRelateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 客户心理状况 内容保存 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-08
 */
@RestController
@RequestMapping("/frClientMentalityRelate")
public class FrClientMentalityRelateController {

    @Autowired
    IFrClientMentalityRelateService iFrClientMentalityRelateService;

    @GetMapping("/saveMentality")
    public JsonResult saveMentality(String[] frClientMentalityTypeIds, String cid, String familyLife, String emotionalState) throws YJException {
        return iFrClientMentalityRelateService.saveMentality(frClientMentalityTypeIds, cid, familyLife, emotionalState);
    }
    @GetMapping("/getMentality")
    public JsonResult getMentality(){
        List<FrClientMentalityRelate> frClientMentalityRelates = iFrClientMentalityRelateService.getMentality();
        return JsonResult.success(frClientMentalityRelates);
    }
}

