package com.yj.web.controller;


import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.dto.PhysicalDateDTO;
import com.yj.dal.model.FrClientPhysicalTest;
import com.yj.service.service.IFrClientPhysicalTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 体能测试 内容保存 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-16
 */
@RestController
@RequestMapping("/frClientPhysicalTest")
public class FrClientPhysicalTestController {

    @Autowired
    IFrClientPhysicalTestService iFrClientPhysicalTestService;

    @PostMapping("/savePhysical")
    public JsonResult savePhysical(@RequestBody List<FrClientPhysicalTest> frClientPhysicalTests)throws YJException {
        return iFrClientPhysicalTestService.savePhysical(frClientPhysicalTests);
    }

    @PostMapping("/getPhysical")
    public JsonResult getPhysical(String cid, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date) throws YJException {
        List<FrClientPhysicalTest> frClientPhysicalTests = iFrClientPhysicalTestService.getPhysical(cid, date);
        return JsonResult.success(frClientPhysicalTests);
    }

    @GetMapping("/getSaveDate")
    public JsonResult getSaveDate(String cid) throws YJException {
        List<PhysicalDateDTO> physicalDates = iFrClientPhysicalTestService.getSaveDate(cid);
        return JsonResult.success(physicalDates);
    }
}

