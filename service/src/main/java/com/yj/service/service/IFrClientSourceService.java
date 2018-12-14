package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.dal.model.FrClientSource;
import com.yj.service.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户来源渠道表 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-09
 */
public interface IFrClientSourceService extends BaseService<FrClientSource> {

    Map<String,Object> getSource(String cid)throws YJException;
}
