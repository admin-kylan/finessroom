package com.yj.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.PageUtils;
import com.yj.common.util.Query;
import com.yj.dal.dao.FrActionMapper;
import com.yj.dal.dao.FrTraningClassMapper;
import com.yj.dal.model.*;
import com.yj.dal.param.AddProjectParam;
import com.yj.service.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 训练计划 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-20
 */
@RestController
@RequestMapping("/frTrainingPlan")
public class FrTrainingPlanController {

    @Autowired
    IFrTrainingPlanService service;

    @Autowired
    IFrTrainingSeriesService trainingSeriesService;

    @Autowired
    FrTraningClassMapper frTraningClassMapper;

    @Autowired
    IFrPlanClassService frPlanClassService;

    /**
     * 查询训练计划
     *
     * @param cid
     * @param currPage
     * @return
     * @throws YJException
     */
    @GetMapping("/getTrainingPlanList")
    public JsonResult getTrainingPlanList(String cid, String currPage) throws YJException {
        PageUtils planList = service.getTrainingPlanList(cid, currPage);
        if (planList != null) {
            return JsonResult.success(planList);
        } else {
            return JsonResult.fail();
        }

    }

    /**
     * 获取导入 1.训练计划 2.训练套餐内容
     *
     * @param type
     * @return
     * @throws YJException
     */
    @GetMapping("/getCourse")
    public JsonResult getCourse(String type) throws YJException {
        List<FrTrainingSeries> frTrainingSeries = trainingSeriesService.getCourse(type);
        return JsonResult.success(frTrainingSeries);
    }

    @GetMapping("/getPlanById")
    public JsonResult getPlanById(String[] ids) throws YJException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (String id : ids) {
            Map<String, Object> plan = frTraningClassMapper.getPlanById(id);
            if (plan != null) {
                list.add(plan);
            }
        }
        return JsonResult.success(list);
    }

    /**
     * 训练计划添加
     *
     * @param params
     * @return
     * @throws YJException
     */
    @PostMapping("/saveProject")
    public JsonResult saveProject(@RequestBody AddProjectParam params) throws YJException {

        return service.saveProject(params);
    }

    /**
     * 训练计划修改
     *
     * @param params
     * @return
     * @throws YJException
     */
    @PostMapping("/updateProject")
    public JsonResult updateProject(@RequestBody AddProjectParam params) throws YJException {

        return service.updateProject(params);
    }

    /**
     * 删除训练计划
     *
     * @param id
     * @return
     * @throws YJException
     */
    @GetMapping("/delPlan")
    public JsonResult delPlan(String id) throws YJException {
        return service.delPlan(id);
    }

}

