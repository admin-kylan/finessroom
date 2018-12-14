package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.dal.model.FrCardOrderSplitSet;
import com.yj.service.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 所有会员卡分期付款订单，相关分期付款设置 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
public interface IFrCardOrderSplitSetService extends BaseService<FrCardOrderSplitSet> {

    /**
     * 根据分期设置初始化订单分期，orderId需另外设置
     * @param cardSetId  FrCardTypeSplitSet的主键ID
     * @param code       客户代码
     * @return
     */
    public FrCardOrderSplitSet initFrCardSplitSet(String cardSetId, String code);

    public List<Map<String,Object>> queryFrCardSplitSet(String orderInfoId,String CustomerCode)throws YJException;
}
