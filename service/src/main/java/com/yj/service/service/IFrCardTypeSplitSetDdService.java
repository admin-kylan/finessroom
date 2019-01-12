package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrCardTypeSplitSet;
import com.yj.dal.model.FrCardTypeSplitSetDd;
import com.yj.service.base.BaseService;

/**
 * <p>
 * 会员卡分期付款设置 详细 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
public interface IFrCardTypeSplitSetDdService extends BaseService<FrCardTypeSplitSetDd> {

    /**
     * 根据主键id查询分期信息详情（单条数据）
     * @param id
     * @return
     */
    FrCardTypeSplitSetDd get(String id)throws YJException;

    /**
     * 根据主键id修改数据
     * @param frCardTypeSplitSetDd
     * @return
     * @throws YJException
     */
    JsonResult update(FrCardTypeSplitSetDd frCardTypeSplitSetDd)throws YJException;
}
