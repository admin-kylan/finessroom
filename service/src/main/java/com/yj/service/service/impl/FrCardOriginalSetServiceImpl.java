package com.yj.service.service.impl;

import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.util.StringUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.model.FrCardOriginalSet;
import com.yj.dal.dao.FrCardOriginalSetMapper;
import com.yj.dal.model.FrCardType;
import com.yj.service.service.IFrCardOriginalSetService;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 会员卡购买前，后台设置保存 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-10
 */
@Service
public class FrCardOriginalSetServiceImpl extends BaseServiceImpl<FrCardOriginalSetMapper, FrCardOriginalSet> implements IFrCardOriginalSetService {

    @Override
    public Map<String,Object> querySelectOneFrCard(FrCardOriginalSet frCardOriginalSet)throws YJException {
        if(frCardOriginalSet == null){
            throw new YJException(YJExceptionEnum.OBJECT_NOT_FOUND);
        }
        if(StringUtils.isEmpty(frCardOriginalSet.getCardId())){
            throw new YJException(YJExceptionEnum.VIPCARDNO_ISNOTBLANK);
        }
        Map<String,Object> map = baseMapper.querySelectOneFrCard(frCardOriginalSet);
        return map;
    }

    @Override
    public void initFrCardOrgiginalSet(FrCardOriginalSet frCardOriginalSet, FrCardType frCardType) {
        if(frCardOriginalSet != null && frCardType != null){
            frCardOriginalSet.setServiceLife(frCardType.getServiceLife());
            frCardOriginalSet.setSwipingInterval(frCardType.getSwipingInterval());
            frCardOriginalSet.setUsedNum(frCardType.getUsedNum());
            frCardOriginalSet.setPzfs(frCardType.getPzfs());
            frCardOriginalSet.setOriginalPrice(frCardType.getOriginalPrice());
            frCardOriginalSet.setSalesPrice(frCardType.getSalesPrice());
            frCardOriginalSet.setTotalNum(frCardType.getTotalNum());
            frCardOriginalSet.setStopNum(frCardType.getStopNum());
            frCardOriginalSet.setStopDays(frCardType.getStopDays());
            frCardOriginalSet.setTotalDays(frCardType.getTotalDays());
            frCardOriginalSet.setOutPrice(frCardType.getOutPrice());
            frCardOriginalSet.setQtXxjgUpdate(frCardType.getQtXxjgUpdate());
            frCardOriginalSet.setQtXxqyUpdate(frCardType.getQtXxqyUpdate());
            frCardOriginalSet.setQtZsqyUpdate(frCardType.getQtZsqyUpdate());
            frCardOriginalSet.setQtLrzpUpdate(frCardType.getQtLrzpUpdate());
            frCardOriginalSet.setQtZdxxjg(frCardType.getQtZdxxjg());
            frCardOriginalSet.setQtZdqy(frCardType.getQtZdqy());
            frCardOriginalSet.setCanTransfer(frCardType.getCanTransfer());
            frCardOriginalSet.setCanChangeType(frCardType.getCanChangeType());
            frCardOriginalSet.setTransferFee(frCardType.getTransferFee());
            frCardOriginalSet.setRenewCommonPrice(frCardType.getRenewCommonPrice());
            frCardOriginalSet.setRenewLevel1Price(frCardType.getRenewLevel1Price());
            frCardOriginalSet.setRenewLevel2Price(frCardType.getRenewLevel2Price());
            frCardOriginalSet.setRenewLevel3Price(frCardType.getRenewLevel3Price());
            frCardOriginalSet.setRenewLevel4Price(frCardType.getRenewLevel4Price());
            frCardOriginalSet.setQtCanRenew(frCardType.getQtCanRenew());
            frCardOriginalSet.setZkNum(frCardType.getZkNum());
            frCardOriginalSet.setZkCyfs(frCardType.getZkCyfs());
            frCardOriginalSet.setZkTkqy(frCardType.getZkTkqy());
            frCardOriginalSet.setZkXffs(frCardType.getZkXffs());
            frCardOriginalSet.setZkClfs(frCardType.getZkClfs());
            frCardOriginalSet.setZkSjjg(frCardType.getZkSjjg());
            frCardOriginalSet.setCardTypeName(frCardType.getCardTypeName());
            frCardOriginalSet.setCardType(frCardType.getType());
            frCardOriginalSet.setCardTypeId(frCardType.getId());
            frCardOriginalSet.setCustomerCode(frCardType.getCustomerCode());
            frCardOriginalSet.setCardFlagId(frCardType.getpId());
            frCardOriginalSet.setChangeTransferFee(frCardType.getChangeTransferFee());
            frCardOriginalSet.setId(UUIDUtils.generateGUID());
        }
    }


}
