package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.dal.model.FrCardOrderSplitSetDd;
import com.yj.service.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 所有会员卡分期付款订单，相关分期付款设置 详细 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
public interface IFrCardOrderSplitSetDdService extends BaseService<FrCardOrderSplitSetDd> {

    /**
     * 初始化会员卡分期详情
     * @param cardTypeSplitSetId    FrCardTypeSplitSet 的主键ID
     * @param cardOrderSplitSetId  FrCardOrderSplitSet 的主键ID
     * @return
     * @throws YJException
     */
    public List<FrCardOrderSplitSetDd> initFrCardOrderSplitSetDd(String cardTypeSplitSetId,String cardOrderSplitSetId)throws YJException;

}
