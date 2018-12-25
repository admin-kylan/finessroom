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
import com.yj.service.service.IFrActionService;
import com.yj.service.service.IFrTrainingActionService;
import com.yj.service.service.IFrTrainingPlanService;
import com.yj.service.service.IFrTrainingSeriesService;
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
    IFrTrainingActionService trainingActionService;

    @Autowired
    FrActionMapper frActionMapper;

    @Autowired
    IFrTrainingSeriesService trainingSeriesService;

    @Autowired
    FrTraningClassMapper frTraningClassMapper;

    @GetMapping("/getTrainingPlanList")
    public JsonResult getTrainingPlanList(String cid, String currPage) throws YJException {
        Map<String, Object> map = new HashMap<>();
        if (currPage == null) {
            currPage = "1";
        }
        map.put("page", currPage);
        map.put("limit", "10");
        Page page = new Query<FrTrainingPlan>(map).getPage();
        page = service.selectPage(page,
                new EntityWrapper<FrTrainingPlan>()
                        .where("is_using = 1 and client_id={0}", cid));
        List<FrTrainingPlan> frTrainingPlans = page.getRecords();
        if (frTrainingPlans.size() <= 0) {
            return JsonResult.failMessage("数据有误");
        }
        for (FrTrainingPlan frTrainingPlan : frTrainingPlans) {
            List<FrTrainingAction> frTrainingActions = trainingActionService.selectList(
                    new EntityWrapper<FrTrainingAction>()
                            .where("is_using = 1 and training_id={0}", frTrainingPlan.getId())
            );
            List<Map<String, Object>> frActions = new ArrayList<>();
            for (FrTrainingAction frTrainingAction : frTrainingActions) {
                Map<String, Object> frAction = frActionMapper.getAction(
                        frTrainingAction.getClassId()
                );
                System.out.println(frAction);
                if (frAction != null) {
                    frActions.add(frAction);
                }

            }
            frTrainingPlan.setFrActions(frActions);
        }
        page.setRecords(frTrainingPlans);

        return JsonResult.success(new PageUtils(page));
    }

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

    @PostMapping("/saveProject")
    public JsonResult saveProject(@RequestBody AddProjectParam params)throws YJException {

        return service.saveProject(params);
    }

    @PostMapping("/updateProject")
    public JsonResult updateProject(@RequestBody AddProjectParam params)throws YJException {

        return service.updateProject(params);
    }

}

