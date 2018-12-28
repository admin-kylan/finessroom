package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.PageUtils;
import com.yj.common.util.Query;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.FrActionMapper;
import com.yj.dal.model.FrPlanClass;
import com.yj.dal.model.FrTrainingAction;
import com.yj.dal.model.FrTrainingPlan;
import com.yj.dal.dao.FrTrainingPlanMapper;
import com.yj.dal.param.AddProjectParam;
import com.yj.service.service.IFrPlanClassService;
import com.yj.service.service.IFrTrainingActionService;
import com.yj.service.service.IFrTrainingPlanService;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    IFrTrainingActionService trainingActionService;

    @Autowired
    IFrTrainingActionService frTrainingActionService;

    @Autowired
    FrActionMapper frActionMapper;

    @Autowired
    IFrPlanClassService frPlanClassService;

    @Override
    public PageUtils getTrainingPlanList(String cid, String currPage) throws YJException {

        Map<String, Object> map = new HashMap<>();
        if (currPage == null) {
            currPage = "1";
        }
        map.put("page", currPage);
        map.put("limit", "10");
        Page page = new Query<FrTrainingPlan>(map).getPage();
        page = selectPage(page,
                new EntityWrapper<FrTrainingPlan>()
                        .where("is_using = 1 and client_id={0}", cid));
        List<FrTrainingPlan> frTrainingPlans = page.getRecords();
        if (frTrainingPlans.size() <= 0) {
            return null;
        }
        for (FrTrainingPlan frTrainingPlan : frTrainingPlans) {
            List<FrTrainingAction> frTrainingActions = trainingActionService.selectList(
                    new EntityWrapper<FrTrainingAction>()
                            .where("is_using = 1 and training_id={0}", frTrainingPlan.getId())
            );
            List<FrPlanClass> frPlanClasses = new ArrayList<>();
            for (FrTrainingAction frTrainingAction : frTrainingActions) {
                FrPlanClass frPlanClass = frPlanClassService.selectOne(
                        new EntityWrapper<FrPlanClass>().where("is_using=1 and id={0}", frTrainingAction.getPlanClassId())
                );
                System.out.println(frPlanClass);
                if (frPlanClass != null) {
                    frPlanClass.setTid(frTrainingAction.getId());
                    frPlanClasses.add(frPlanClass);
                }

            }
            frTrainingPlan.setFrPlanClasses(frPlanClasses);
        }
        page.setRecords(frTrainingPlans);

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult saveProject(AddProjectParam params) throws YJException {
        FrTrainingPlan frTrainingPlan = new FrTrainingPlan();
        frTrainingPlan.setClientId(params.getCid());
        frTrainingPlan.setImprovementPlan(params.getImprovementPlan());
        frTrainingPlan.setProject(params.getProject());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
        try {
            if (params.getTrainDate() != null && params.getTrainDate() != "") {
                frTrainingPlan.setTrainDate(sdf.parse(params.getTrainDate()));
            }
            if (params.getTrainStartDate() != null && params.getTrainDate() != "") {
                frTrainingPlan.setTrainStartDate(sdf2.parse(params.getTrainStartDate()));
            }
            if (params.getTrainEndDate() != null && params.getTrainEndDate() != "") {
                frTrainingPlan.setTrainEndDate(sdf2.parse(params.getTrainEndDate()));
            }
            frTrainingPlan.setId(UUIDUtils.generateGUID());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        boolean insert = insert(frTrainingPlan);
        if (insert) {
            FrTrainingAction frTrainingAction = null;
            FrPlanClass frPlanClass = null;
            for (String id : params.getClassIds()) {
                frPlanClass = new FrPlanClass();
                Map<String, Object> action = frActionMapper.getAction(id);
                frPlanClass.setId(UUIDUtils.generateGUID());
                frPlanClass.setSname((String) action.get("sname"));
                frPlanClass.setCount((Integer) action.get("count"));
                frPlanClass.setTime((Integer) action.get("time"));
                frPlanClass.setDiff((String) action.get("diff"));
                frPlanClass.setImage((String) action.get("image"));
                frPlanClass.setName((String) action.get("name"));
                frPlanClass.setRemark((String) action.get("remark"));
                frPlanClass.setStrength((String) action.get("strength"));
                boolean insert1 = frPlanClassService.insert(frPlanClass);
                if (insert1) {
                    frTrainingAction = new FrTrainingAction();
                    frTrainingAction.setId(UUIDUtils.generateGUID());
                    frTrainingAction.setPlanClassId(frPlanClass.getId());
                    frTrainingAction.setTrainingId(frTrainingPlan.getId());
                    insert = frTrainingActionService.insert(frTrainingAction);
                }

            }
        }
        if (insert) {
            return JsonResult.success();
        }

        return JsonResult.fail();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult updateProject(AddProjectParam params) throws YJException {
        FrTrainingPlan frTrainingPlan = selectOne(
                new EntityWrapper<FrTrainingPlan>().where("is_using=1 and id={0}", params.getFid())
        );
        if (frTrainingPlan != null) {
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
        }
        //修改训练计划
        boolean b = updateById(frTrainingPlan);
        if (b) {
            for (FrPlanClass frPlanClass : params.getActionInfo()) {
                FrPlanClass planClass = frPlanClassService.selectOne(
                        new EntityWrapper<FrPlanClass>().where("is_using=1 and id={0}", frPlanClass.getId())
                );
                FrTrainingAction frTrainingAction = null;
                if (planClass == null) {
                    //新增课程详情
                    frPlanClass.setId(UUIDUtils.generateGUID());
                    b = frPlanClassService.insert(frPlanClass);
                    //添加关联
                    frTrainingAction = new FrTrainingAction();
                    frTrainingAction.setTrainingId(frTrainingPlan.getId());
                    frTrainingAction.setPlanClassId(frPlanClass.getId());
                    frTrainingAction.setId(UUIDUtils.generateGUID());
                    frTrainingActionService.insert(frTrainingAction);
                } else {
                    //修改课程详情
                    b = frPlanClassService.updateById(frPlanClass);
                }

            }
        }
        if (b) {
            return JsonResult.success();
        }
        return JsonResult.fail();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult delPlan(String id) throws YJException {
        FrTrainingPlan frTrainingPlan = selectOne(
                new EntityWrapper<FrTrainingPlan>().where("is_using=1 and id={0}", id)
        );
        boolean b = false;
        if (frTrainingPlan != null) {
            frTrainingPlan.setUsing(false);
            b = updateById(frTrainingPlan);
        }
        if (b) {
            List<FrTrainingAction> frTrainingActions = frTrainingActionService.selectList(
                    new EntityWrapper<FrTrainingAction>().where("is_using=1 and training_id={0}", id)
            );
            for (FrTrainingAction frTrainingAction : frTrainingActions) {
                if (frTrainingAction != null) {
                    frTrainingAction.setUsing(false);
                    b = frTrainingActionService.updateById(frTrainingAction);
                    if (b == false) {
                        return JsonResult.fail();
                    }
                    FrPlanClass frPlanClass = frPlanClassService.selectOne(
                            new EntityWrapper<FrPlanClass>().where("is_using=1 and id={0}", frTrainingAction.getPlanClassId())
                    );
                    if (frPlanClass != null) {
                        frPlanClass.setUsing(false);
                        b = frPlanClassService.updateById(frPlanClass);
                    }
                    if (b == false) {
                        return JsonResult.fail();
                    }
                }
            }
        }
        if (b) {
            return JsonResult.success();
        }

        return JsonResult.fail();
    }


}
