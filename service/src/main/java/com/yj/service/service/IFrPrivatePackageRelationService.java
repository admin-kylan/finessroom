package com.yj.service.service;

import java.util.Map;

import com.yj.common.exception.YJException;
import com.yj.dal.model.FrPrivatePackageRelation;
import com.yj.service.base.BaseService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-28
 */
public interface IFrPrivatePackageRelationService extends BaseService<FrPrivatePackageRelation> {
	
	
	
	public Object getShopList(Map<String, Object> params)  throws YJException;
	
	
	Object courceList(Map<String, Object> params)  throws YJException;

	Object shopSdaduim(Map<String, Object> params);
}
