package com.yj.service.service;

import java.util.Map;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.PageUtils;
import com.yj.dal.model.FrAction;
import com.yj.service.base.BaseService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-14
 */
public interface IFrActionService extends BaseService<FrAction> {
	
	
	PageUtils queryPage(Map<String, Object> params) throws YJException;

	JsonResult updateFrAction(FrAction frAction);

	JsonResult batchFrAction(String actionIds, String seriesId);

}
