package com.yj.service.service.impl;

import com.yj.common.util.UUIDUtils;
import com.yj.dal.model.FrShopCardConsumePladdset;
import com.yj.dal.dao.FrShopCardConsumePladdsetMapper;
import com.yj.dal.model.FrShopCtypeConsumePladdset;
import com.yj.service.service.IFrShopCardConsumePladdsetService;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-27
 */
@Service
public class FrShopCardConsumePladdsetServiceImpl extends BaseServiceImpl<FrShopCardConsumePladdsetMapper, FrShopCardConsumePladdset> implements IFrShopCardConsumePladdsetService {

    @Override
    public FrShopCardConsumePladdset getCardConsumePladdsetInfo(FrShopCtypeConsumePladdset frShopCtypeConsumePladdset, String cardConsumeId) {
        FrShopCardConsumePladdset frShopCardConsumePladdset = null;
        if(frShopCtypeConsumePladdset != null){
            frShopCardConsumePladdset = new FrShopCardConsumePladdset();
            frShopCardConsumePladdset.setId(UUIDUtils.generateGUID());
            frShopCardConsumePladdset.setCardConsumeId(cardConsumeId);
            frShopCardConsumePladdset.setUseLimit(frShopCtypeConsumePladdset.getUseLimit());
            frShopCardConsumePladdset.setUseDayStar(frShopCtypeConsumePladdset.getUseDayStar());
            frShopCardConsumePladdset.setUseDayEnd(frShopCtypeConsumePladdset.getUseDayEnd());
            frShopCardConsumePladdset.setUseSelect(frShopCtypeConsumePladdset.getUseSelect());
            frShopCardConsumePladdset.setUseTimeStar(frShopCtypeConsumePladdset.getUseTimeStar());
            frShopCardConsumePladdset.setUseTimeEnd(frShopCtypeConsumePladdset.getUseTimeEnd());
            frShopCardConsumePladdset.setCustomerCode(frShopCtypeConsumePladdset.getCustomerCode());
        }
        return frShopCardConsumePladdset;
    }
}
