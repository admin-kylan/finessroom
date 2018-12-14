package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.dal.model.FrClientWorkType;
import com.yj.service.base.BaseService;

import java.util.List;

/**
 * <p>
 * 客户工作行业 类型 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-08
 */
public interface IFrClientWorkTypeService extends BaseService<FrClientWorkType> {

    List<FrClientWorkType> getWorks()throws YJException;
}
