package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.dal.model.FrClientArchivesType;
import com.yj.service.base.BaseService;

import java.util.List;

/**
 * <p>
 * 客户档案（皮肤、头发、塑性抗衰 档案） 类型 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
public interface IFrClientArchivesTypeService extends BaseService<FrClientArchivesType> {

    List<FrClientArchivesType> getTypeAll(Integer type)throws YJException;
}
