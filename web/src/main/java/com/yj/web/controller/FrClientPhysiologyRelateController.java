package com.yj.web.controller;


import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrClientPhysiologyRelate;
import com.yj.service.service.IFrClientPersonnelRelateService;
import com.yj.service.service.IFrClientPhysiologyRelateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 客户生理状况 内容保存 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-08
 */
@RestController
@RequestMapping("/frClientPhysiologyRelate")
public class FrClientPhysiologyRelateController {

    @Autowired
    IFrClientPhysiologyRelateService iFrClientPhysiologyRelateService;

    @GetMapping("/savePsychology")
    public JsonResult savePsychology(String[] frClientPsychologyTypeIds, String cid, String medicalHistory, String drug, String theHormone, String bloodPressure,String menstruation) throws YJException {
        return iFrClientPhysiologyRelateService.savePsychology(frClientPsychologyTypeIds, cid, medicalHistory, drug, theHormone, bloodPressure,menstruation);
    }

    @GetMapping("/getPsychology")
    public JsonResult getPsychology(String clientId) {
        List<FrClientPhysiologyRelate> frClientPhysiologyRelates=iFrClientPhysiologyRelateService.getPsychology(clientId);
        return JsonResult.success(frClientPhysiologyRelates);
    }
}

