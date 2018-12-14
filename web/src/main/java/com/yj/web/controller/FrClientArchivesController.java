package com.yj.web.controller;


import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrClientArchives;
import com.yj.service.service.IFrClientArchivesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 客户档案（皮肤、头发、塑性抗衰 档案） 区分 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
@RestController
@RequestMapping("/frClientArchives")
public class FrClientArchivesController {

    @Autowired
    IFrClientArchivesService iFrClientArchivesService;

    @PostMapping("/getArchives")
    public JsonResult getArchives(String cid, Integer type) throws YJException {
        List<FrClientArchives> frClientArchives = iFrClientArchivesService.getArchives(cid, type);

        return JsonResult.success(frClientArchives);
    }
}

