package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrCardLimit;
import com.yj.service.base.BaseService;


/**
 * <p>
 * 会员卡使用限定 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-31
 */
public interface IFrCardLimitService extends BaseService<FrCardLimit> {

    JsonResult addCardLimit(FrCardLimit frCardLimit,String code)throws  YJException;

    JsonResult getCardLimitList(String code,String cardId);

    JsonResult deleteCardLimit(String code,String id,String clientId);

    /**
     * 查询指定客户的通用限定记录
     * @param code
     * @param clientId
     * @return
     * @throws YJException
     */
    FrCardLimit getLimitInfoByClient(String code,String clientId)throws YJException;

}
