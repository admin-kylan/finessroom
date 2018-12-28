package com.yj.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrPlanClass;
import com.yj.dal.model.FrTrainingAction;
import com.yj.service.service.IFrPlanClassService;
import com.yj.service.service.IFrTrainingActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 客户训练计划的课程详情保存 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-26
 */
@RestController
@RequestMapping("/frPlanClass")
public class FrPlanClassController {

    @Autowired
    IFrPlanClassService service;

    @Autowired
    IFrTrainingActionService frTrainingActionService;

    @PostMapping("/updateAction")
    public JsonResult updateAction(@RequestBody FrPlanClass frPlanClass) {
        boolean b = service.updateById(frPlanClass);
        if (b) {
            return JsonResult.success();
        }
        return JsonResult.fail();
    }

    @GetMapping("/delAction")
    public JsonResult delAction(String id, String tid) {
        boolean b = false;
        if (id != null && id != "") {
            FrPlanClass frPlanClass = service.selectOne(
                    new EntityWrapper<FrPlanClass>().where("is_using=1 and id={0}", id)
            );
            if (frPlanClass != null) {
                frPlanClass.setUsing(false);
                b = service.updateById(frPlanClass);
            }
        }
        if (b) {
            FrTrainingAction frTrainingAction = frTrainingActionService.selectOne(
                    new EntityWrapper<FrTrainingAction>().where("is_using=1 and id={0}", tid)
            );
            if (frTrainingAction != null) {
                frTrainingAction.setUsing(false);
                b = frTrainingActionService.updateById(frTrainingAction);
            }

        }
        if (b) {
            return JsonResult.success();
        }
        return JsonResult.fail();
    }
}

