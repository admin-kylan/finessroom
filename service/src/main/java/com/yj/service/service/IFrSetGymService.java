package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrSetGym;
import com.yj.service.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 健身馆设置保存 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-28
 */
public interface IFrSetGymService extends BaseService<FrSetGym> {

    List<Map<String, Object>> getShop(String code,String modelId) throws YJException;

    List<FrSetGym> getChainStore(String modelId);

    Map<String, Object> getTime(String modelId);

    List<Map<String, Object>> getCityShop(String code)throws YJException;

    JsonResult saveProject(Map<String, Object> map)throws YJException;

    JsonResult updateProject(FrSetGym frSetGym)throws YJException;

    JsonResult delProject(String id)throws YJException;
}
