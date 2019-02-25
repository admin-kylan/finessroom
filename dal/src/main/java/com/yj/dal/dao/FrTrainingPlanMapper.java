package com.yj.dal.dao;

import com.yj.dal.model.FrTrainingPlan;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 训练计划 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-20
 */
public interface FrTrainingPlanMapper extends BaseMapper<FrTrainingPlan> {

    FrTrainingPlan selectByProject(String project);
}
