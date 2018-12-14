package com.yj.service.service.impl;

import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.FrCardTypeSplitSetMapper;
import com.yj.dal.model.FrCardOrderSplitSet;
import com.yj.dal.dao.FrCardOrderSplitSetMapper;
import com.yj.dal.model.FrCardTypeSplitSet;
import com.yj.service.service.IFrCardOrderSplitSetService;
import com.yj.service.base.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 所有会员卡分期付款订单，相关分期付款设置 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
@Service
public class FrCardOrderSplitSetServiceImpl extends BaseServiceImpl<FrCardOrderSplitSetMapper, FrCardOrderSplitSet> implements IFrCardOrderSplitSetService {

    @Resource
    private FrCardTypeSplitSetMapper frCardTypeSplitSetMapper;

    /**
     * 初始化订单分期 （orderId 需设置）
     * @param cardSetId  FrCardTypeSplitSet的主键ID
     * @param code       客户代码
     * @return
     */
    public FrCardOrderSplitSet initFrCardSplitSet(String cardSetId, String code){
        FrCardTypeSplitSet frCardTypeSplitSet = new FrCardTypeSplitSet();
        frCardTypeSplitSet.setId(cardSetId);
        frCardTypeSplitSet.setUsing(true);
        frCardTypeSplitSet.setCustomerCode(code);
        FrCardOrderSplitSet frCardOrderSplitSet = null ;
        if(!StringUtils.isEmpty(cardSetId)){
            frCardTypeSplitSet = frCardTypeSplitSetMapper.selectOne(frCardTypeSplitSet);
            if(frCardTypeSplitSet != null){
                frCardOrderSplitSet = new FrCardOrderSplitSet();
                frCardOrderSplitSet.setId(UUIDUtils.generateGUID());
                frCardOrderSplitSet.setFirstPrice(frCardTypeSplitSet.getFirstPrice());
                frCardOrderSplitSet.setSplitNum(frCardTypeSplitSet.getSplitNum());
                frCardOrderSplitSet.setSplitType(frCardTypeSplitSet.getSplitType());
                frCardOrderSplitSet.setSplitContent(frCardTypeSplitSet.getSplitContent());
                frCardOrderSplitSet.setUsing(true);
                frCardOrderSplitSet.setCustomerCode(frCardTypeSplitSet.getCustomerCode());
                // 1 是新购
                frCardOrderSplitSet.setOrderType(1);
                frCardOrderSplitSet.setSplitPrice(frCardTypeSplitSet.getSplitPrice());
                frCardOrderSplitSet.setTotalPrice(frCardTypeSplitSet.getTotalPrice());
            }
        }
        return frCardOrderSplitSet;
    }


    @Override
    public List<Map<String, Object>> queryFrCardSplitSet(String orderInfoId, String code)throws YJException {
        if(StringUtils.isEmpty(code)){
            throw  new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        if(StringUtils.isEmpty(orderInfoId)){
            throw  new YJException(YJExceptionEnum.VIPCARDNO_NOT_EXISTED);
        }
        FrCardOrderSplitSet frCardOrderSplitSet = new FrCardOrderSplitSet();
        frCardOrderSplitSet.setOrderId(orderInfoId);
        frCardOrderSplitSet.setCustomerCode(code);
        return baseMapper.queryFrCardSplitSet(frCardOrderSplitSet);
    }

}
