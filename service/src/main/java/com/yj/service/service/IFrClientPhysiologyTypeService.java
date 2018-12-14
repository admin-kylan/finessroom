package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.dal.model.FrClientPhysiologyType;
import com.yj.service.base.BaseService;

import java.util.List;

/**
 * <p>
 * 客户生理状况 类型 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-08
 */
public interface IFrClientPhysiologyTypeService extends BaseService<FrClientPhysiologyType> {

    List<FrClientPhysiologyType> getPsychology()throws YJException;
}
