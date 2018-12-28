package com.yj.service.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.PageUtils;
import com.yj.dal.model.FrTrainingPlan;
import com.yj.dal.param.AddProjectParam;
import com.yj.service.base.BaseService;

import java.util.List;

/**
 * <p>
 * 训练计划 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-20
 */
public interface IFrTrainingPlanService extends BaseService<FrTrainingPlan> {

    JsonResult saveProject(AddProjectParam params)throws YJException;

    JsonResult updateProject(AddProjectParam params)throws YJException;

    JsonResult delPlan(String id)throws YJException;

    PageUtils getTrainingPlanList(String cid, String currPage) throws YJException;
}
