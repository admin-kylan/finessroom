package com.yj.web.controller;


import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrClientWorkRelate;
import com.yj.service.service.IFrClientWorkRelateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 客户工作行业 内容保存 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-08
 */
@RestController
@RequestMapping("/frClientWorkRelate")
public class FrClientWorkRelateController {

    @Autowired
    IFrClientWorkRelateService iFrClientWorkRelateService;

    @GetMapping("/saveWorks")
    public JsonResult saveWorks(String[] frClientWorkTypeIds, String cid, String workSituation, String workHistory) throws YJException {
        return iFrClientWorkRelateService.saveWorks(frClientWorkTypeIds, cid, workHistory, workSituation);
    }

    @GetMapping("/getWorks")
    public JsonResult getWorks(String clientId){
      List<FrClientWorkRelate> frClientWorkRelates= iFrClientWorkRelateService.getWorks(clientId);
        return JsonResult.success(frClientWorkRelates);
    }
}

