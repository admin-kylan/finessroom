package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.util.PageUtils;
import com.yj.dal.model.FrPrivatePackage;
import com.yj.service.base.BaseService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-18
 */
public interface IFrPrivatePackageService extends BaseService<FrPrivatePackage> {
    public PageUtils queryPage(Map<String, Object> params) throws YJException;
    public Object findProjectInfo(Map<String, Object> params);
}
