package com.yj.service.service.impl;

import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.util.StringUtils;
import com.yj.dal.dao.FrStoreCommonMapper;
import com.yj.dal.model.FrStoreCommon;
import com.yj.service.service.IFrStoreCommonService;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 门店通用设置表 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-04
 */
@Service
public class FrStoreCommonServiceImpl extends BaseServiceImpl<FrStoreCommonMapper, FrStoreCommon> implements IFrStoreCommonService {

    @Override
    public FrStoreCommon selectTopOne(FrStoreCommon frStoreCommon) throws YJException {
        if(frStoreCommon == null){
            throw new YJException(YJExceptionEnum.REQUEST_NULL);
        }
        if(StringUtils.isEmpty(frStoreCommon.getCustomerCode())){
            throw  new  YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        return baseMapper.selectTopOne(frStoreCommon);
    }
}
