package com.yj.service.service.impl;

import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.util.CommonUtils;
import com.yj.common.util.StringUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.model.FrCardOrderDatail;
import com.yj.dal.model.FrCardOrderPriceDatail;
import com.yj.dal.dao.FrCardOrderPriceDatailMapper;
import com.yj.service.service.IFrCardOrderDatailService;
import com.yj.service.service.IFrCardOrderPriceDatailService;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 资金交易明细 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-24
 */
@Service
public class FrCardOrderPriceDatailServiceImpl extends BaseServiceImpl<FrCardOrderPriceDatailMapper, FrCardOrderPriceDatail> implements IFrCardOrderPriceDatailService {

    @Resource
    private IFrCardOrderDatailService iFrCardOrderDatailService;

    @Override
    public FrCardOrderPriceDatail queryTopOne(FrCardOrderPriceDatail frCardOrderPriceDatail)throws YJException {
        if(frCardOrderPriceDatail == null){
            throw new YJException(YJExceptionEnum.REQUEST_NULL);
        }
        if(StringUtils.isEmpty(frCardOrderPriceDatail.getCardId()) || StringUtils.isEmpty(frCardOrderPriceDatail.getClientId())
                || StringUtils.isEmpty(frCardOrderPriceDatail.getCustomerCode()) || StringUtils.isEmpty(frCardOrderPriceDatail.getOrderId())){
            throw  new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        return baseMapper.queryTopOne(frCardOrderPriceDatail);
    }

    @Override
    public Double getOrderPriceByOrderStatus(boolean orderStatus, Double storagePrice)throws YJException{
        Double orderPrice = storagePrice;
        //收入
        if(orderStatus){
            if(storagePrice < 0){
                orderPrice = storagePrice*(-1.0);
            }
        }
        if(!orderStatus){
            if(storagePrice > 0){
                orderPrice = storagePrice*(-1.0);
            }
        }
        return  orderPrice;
    }
}
