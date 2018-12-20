package com.yj.web.controller;


import com.alibaba.fastjson.JSON;
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
import java.util.Map;

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

    @Autowired
    FrTraningClassController frTraningClassController;

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

    @GetMapping("/getTrainClass")
    public JsonResult getTrainClass() throws YJException {
        List<Map<String,Object>> list= iFrClientPhysicalTestService.getTrainClass();
        for (Map map : list) {
            String id = (String) map.get("id");
            JsonResult list2 = frTraningClassController.list(null, id, "2");
            map.put("actionList",list2.getData());
        }

        return JsonResult.success(list);
    }

}

