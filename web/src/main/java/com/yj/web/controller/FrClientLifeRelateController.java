package com.yj.web.controller;


import com.alibaba.fastjson.JSON;
import com.yj.common.exception.YJException;
import com.yj.dal.model.FrClientLifeRelate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yj.common.result.JsonResult;
import com.yj.service.service.IFrClientLifeRelateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * <p>
 * 客户生活详情 内容保存 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-02
 */
@RestController
@RequestMapping("/frClientLifeRelate")
public class FrClientLifeRelateController {

    @Autowired
    IFrClientLifeRelateService iFrClientLifeRelateService;

    @GetMapping("/saveDetails")
    public JsonResult saveDetails(String[] frClientLifeTypeIds, String cid ,String source,String sid) throws YJException {
        return iFrClientLifeRelateService.saveDetails(frClientLifeTypeIds, cid,source,sid);
    }

    @GetMapping("/getDetails")
    public JsonResult getDetails() {
        List<FrClientLifeRelate> frClientLifeRelates=iFrClientLifeRelateService.getDetails();
        return JsonResult.success(frClientLifeRelates);
    }
}

