package com.yj.service.service.impl;

import com.yj.common.util.UUIDUtils;
import com.yj.dal.model.FrShopCardConsumePlset;
import com.yj.dal.dao.FrShopCardConsumePlsetMapper;
import com.yj.dal.model.FrShopCtypeConsumePlset;
import com.yj.service.service.IFrShopCardConsumePlsetService;
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
public class FrShopCardConsumePlsetServiceImpl extends BaseServiceImpl<FrShopCardConsumePlsetMapper, FrShopCardConsumePlset> implements IFrShopCardConsumePlsetService {

    @Override
    public FrShopCardConsumePlset getCardConsumePlsetInfo(FrShopCtypeConsumePlset frShopCtypeConsumePlset, String cardConsumeId) {
        FrShopCardConsumePlset frShopCardConsumePlset = null;
        if(frShopCtypeConsumePlset != null){
            frShopCardConsumePlset = new FrShopCardConsumePlset();
            frShopCardConsumePlset.setId(UUIDUtils.generateGUID());
            frShopCardConsumePlset.setCardConsumeId(cardConsumeId);
            frShopCardConsumePlset.setUseSelect(frShopCtypeConsumePlset.getUseSelect());
            frShopCardConsumePlset.setUseDays(frShopCtypeConsumePlset.getUseDays());
            frShopCardConsumePlset.setStartTimeAm(frShopCtypeConsumePlset.getStartTimeAm());
            frShopCardConsumePlset.setEndTimeAm(frShopCtypeConsumePlset.getEndTimeAm());
            frShopCardConsumePlset.setStartTimePm(frShopCtypeConsumePlset.getStartTimeAm());
            frShopCardConsumePlset.setEndTimePm(frShopCtypeConsumePlset.getEndTimePm());
            frShopCardConsumePlset.setCustomerCode(frShopCardConsumePlset.getCustomerCode());
        }
        return frShopCardConsumePlset;
    }
}
