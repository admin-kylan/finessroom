package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.dal.model.FrClientMotionType;
import com.yj.service.base.BaseService;

import java.util.List;

/**
 * <p>
 * 客户运动档案 类型 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-02
 */
public interface IFrClientMotionTypeService extends BaseService<FrClientMotionType> {


    List<FrClientMotionType> getSports()throws YJException;
}
