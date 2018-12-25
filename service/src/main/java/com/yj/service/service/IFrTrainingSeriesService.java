package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.dal.model.FrTrainingSeries;
import com.yj.service.base.BaseService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-15
 */
public interface IFrTrainingSeriesService extends BaseService<FrTrainingSeries> {

    List<FrTrainingSeries> getCourse(String type)throws YJException;
}
