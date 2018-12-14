package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.dal.model.FrClientMentalityType;
import com.yj.service.base.BaseService;

import java.util.List;

/**
 * <p>
 * 客户心理情况 类型 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-08
 */
public interface IFrClientMentalityTypeService extends BaseService<FrClientMentalityType> {

    List<FrClientMentalityType> getMentality()throws YJException;
}
