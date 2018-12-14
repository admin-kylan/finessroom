package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrCardOrderBack;
import com.yj.dal.model.FrCardOrderDatail;
import com.yj.service.base.BaseService;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 * 会员卡退卡订单 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-19
 */
public interface IFrCardOrderBackService extends BaseService<FrCardOrderBack> {

    Map<String,Object> queryBlackCardList(FrCardOrderBack frCardOrderBack)throws YJException;

    /**
     * 插入
     * @param frCardOrderBack
     * @return
     * @throws YJException
     */
    JsonResult toInsertBlace(FrCardOrderBack frCardOrderBack,String shopId)throws YJException;

    /**
     * 获取会员卡，及名下子卡的所有储值、权益信息
     * @param frCardOrderBack
     * @param zkxffs
     * @return
     * @throws YJException
     */
    Map<String,Double>  getHaveNum(FrCardOrderBack frCardOrderBack, boolean zkxffs)throws  YJException;
}
