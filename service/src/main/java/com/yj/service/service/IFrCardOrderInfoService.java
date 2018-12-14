package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.PageUtil;
import com.yj.common.util.PageUtils;
import com.yj.dal.model.FrCard;
import com.yj.dal.model.FrCardOrderInfo;
import com.yj.service.base.BaseService;

import java.util.Map;

/**
 * <p>
 * 会员卡订单记录 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-26
 */
public interface IFrCardOrderInfoService extends BaseService<FrCardOrderInfo> {

    String getOrderNo();

    PageUtils getCardOrederList(String code, PageUtil<FrCard> pageUtil) throws YJException;

    Map<String,Object> queryOrderInfoPrice(String cardId, String CustomerCode)throws YJException;

    /**
     * 获取退卡金额，及信息
     * @param frCardOrderInfo
     * @return
     * @throws YJException
     */
    Map<String,Object> getBlackCardData(FrCardOrderInfo frCardOrderInfo)throws YJException;

    Map<String,Object> queryCardAndType(FrCardOrderInfo frCardOrderInfo)throws YJException;
}
