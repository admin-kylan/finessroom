package com.yj.service.service;

import com.yj.dal.model.FrShopCardConsumePladdset;
import com.yj.dal.model.FrShopCtypeConsumePladdset;
import com.yj.service.base.BaseService;

/**
 * <p>
 * 会员卡购买前，卡权益设置保存 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-19
 */
public interface IFrShopCardConsumePladdsetService extends BaseService<FrShopCardConsumePladdset> {

    FrShopCardConsumePladdset getCardConsumePladdsetInfo(FrShopCtypeConsumePladdset frShopCtypeConsumePladdset, String cardConsumeId);
}
