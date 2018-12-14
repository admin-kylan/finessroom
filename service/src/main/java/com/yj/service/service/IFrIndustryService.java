package com.yj.service.service;

import com.yj.dal.model.FrIndustry;
import com.yj.service.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 行业表 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-04
 */
public interface IFrIndustryService extends BaseService<FrIndustry> {

    List<FrIndustry> selectProtectionDaysList(String customerCode);

    List<FrIndustry> selectClaimSetList(String customerCode);

    List<FrIndustry> selectFollowList(Integer customerType, String customerCode);

    Boolean updateIndustrySetList(List<FrIndustry> frIndustries);

    FrIndustry getClientCount(String clientType)  ;
}
