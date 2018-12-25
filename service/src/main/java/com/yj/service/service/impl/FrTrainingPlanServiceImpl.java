package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.Query;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.model.FrTrainingAction;
import com.yj.dal.model.FrTrainingPlan;
import com.yj.dal.dao.FrTrainingPlanMapper;
import com.yj.dal.param.AddProjectParam;
import com.yj.service.service.IFrTrainingActionService;
import com.yj.service.service.IFrTrainingPlanService;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 训练计划 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-20
 */
@Service
public class FrTrainingPlanServiceImpl extends BaseServiceImpl<FrTrainingPlanMapper, FrTrainingPlan> implements IFrTrainingPlanService {

    @Autowired
    IFrTrainingActionService frTrainingActionService;

    @Override
    public JsonResult saveProject(AddProjectParam params) throws YJException {
        FrTrainingPlan frTrainingPlan = new FrTrainingPlan();
        frTrainingPlan.setClientId(params.getCid());
        frTrainingPlan.setImprovementPlan(params.getImprovementPlan());
        frTrainingPlan.setProject(params.getProject());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
        try {
            frTrainingPlan.setTrainDate(sdf.parse(params.getTrainDate()));
            frTrainingPlan.setTrainStartDate(sdf2.parse(params.getTrainStartDate()));
            frTrainingPlan.setTrainEndDate(sdf2.parse(params.getTrainEndDate()));
            frTrainingPlan.setId(UUIDUtils.generateGUID());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        boolean insert = insert(frTrainingPlan);
        if (insert) {
            FrTrainingAction frTrainingAction = null;
            for (String id : params.getClassIds()) {
                frTrainingAction = new FrTrainingAction();
                frTrainingAction.setId(UUIDUtils.generateGUID());
                frTrainingAction.setClassId(id);
                frTrainingAction.setTrainingId(frTrainingPlan.getId());
                insert = frTrainingActionService.insert(frTrainingAction);
            }
        }
        if (insert) {
            return JsonResult.success();
        }

        return JsonResult.fail();
    }

    @Override
    public JsonResult updateProject(AddProjectParam params) throws YJException {
        FrTrainingPlan frTrainingPlan = selectOne(
                new EntityWrapper<FrTrainingPlan>().where("id={0}", params.getId())
        );
        frTrainingPlan.setProject(params.getProject());
        frTrainingPlan.setCoachSummary(params.getCoachSummary());
        frTrainingPlan.setMemberFeel(params.getMemberFeel());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
        try {
            frTrainingPlan.setActualDate(sdf.parse(params.getActualDate()));
            frTrainingPlan.setActualStartDate(sdf2.parse(params.getActualStartDate()));
            frTrainingPlan.setActualEndDate(sdf2.parse(params.getActualEndDate()));
            frTrainingPlan.setTrainDate(sdf.parse(params.getTrainDate()));
            frTrainingPlan.setTrainStartDate(sdf2.parse(params.getTrainStartDate()));
            frTrainingPlan.setTrainEndDate(sdf2.parse(params.getTrainEndDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        boolean b = updateById(frTrainingPlan);
        if (b) {
            frTrainingActionService.selectOne(
                    new EntityWrapper<FrTrainingAction>().where("id={0}")
            );
            FrTrainingAction frTrainingAction = null;
            for (String id : params.getClassIds()) {
                frTrainingAction = new FrTrainingAction();
                frTrainingAction.setId(UUIDUtils.generateGUID());
                frTrainingAction.setClassId(id);
                frTrainingAction.setTrainingId(frTrainingPlan.getId());
                b = frTrainingActionService.insert(frTrainingAction);
            }
        }
        if (b) {
            return JsonResult.success();
        }

        return null;
    }
}
