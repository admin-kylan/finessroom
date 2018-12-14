package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrClientPersonnelRelate;
import com.yj.dal.model.FrClientPhysiologyRelate;
import com.yj.dal.param.ClientDistributionParam;
import com.yj.service.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户员工关系表 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-31
 */
public interface IFrClientPersonnelRelateService extends BaseService<FrClientPersonnelRelate> {


    JsonResult claiming(String[] cids ,String type)throws YJException;

    List<Map<String,String>> getService(String cid, String type)throws YJException;

    JsonResult clientDistribution(ClientDistributionParam params)throws YJException;
}
