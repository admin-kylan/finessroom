package com.yj.web.controller;


import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrClientLifeRelate;
import com.yj.dal.model.FrClientMotionRelate;
import com.yj.service.service.IFrClientMotionRelateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 客户运动档案  内容保存 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-02
 */
@RestController
@RequestMapping("/frClientMotionRelate")
public class FrClientMotionRelateController {

    @Autowired
    IFrClientMotionRelateService frClientMotionRelateService;

    @GetMapping("/saveSports")
    public JsonResult saveSports(String[] frClientSportTypeIds, String cid, String sportType) throws YJException {

        return frClientMotionRelateService.saveSports( frClientSportTypeIds, cid, sportType);
    }

    @GetMapping("/getSports")
    public JsonResult getSports(String clientId){
        List<FrClientMotionRelate> frClientLifeRelates= frClientMotionRelateService.getSports(clientId);
        return  JsonResult.success(frClientLifeRelates);
    }
}

