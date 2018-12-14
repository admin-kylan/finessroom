package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.dal.model.FrStoreCommon;
import com.yj.service.base.BaseService;

/**
 * <p>
 * 门店通用设置表 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-04
 */
public interface IFrStoreCommonService extends BaseService<FrStoreCommon> {

    /**
     * 查询最新的一条通用门店设置
     * @param frStoreCommon
     * @return
     * @throws YJException
     */
    FrStoreCommon  selectTopOne(FrStoreCommon frStoreCommon)throws YJException;

}
