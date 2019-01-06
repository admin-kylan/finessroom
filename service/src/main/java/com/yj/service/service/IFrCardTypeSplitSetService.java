package com.yj.service.service;

import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrCardTypeSplitSet;
import com.yj.service.base.BaseService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员卡分期付款设置 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
public interface IFrCardTypeSplitSetService extends BaseService<FrCardTypeSplitSet> {

    JsonResult insertCardTypeSplit(FrCardTypeSplitSet frCardTypeSplitSet);
}
