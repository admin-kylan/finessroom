package com.yj.service.service;

import com.yj.dal.model.FrShopCardConsumePlset;
import com.yj.dal.model.FrShopCtypeConsumePlset;
import com.yj.service.base.BaseService;

/**
 * <p>
 * 会员卡购买前，卡权益时间设置保存 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-19
 */
public interface IFrShopCardConsumePlsetService extends BaseService<FrShopCardConsumePlset> {

    FrShopCardConsumePlset getCardConsumePlsetInfo(FrShopCtypeConsumePlset frShopCtypeConsumePlset, String cardConsumeId);

}
