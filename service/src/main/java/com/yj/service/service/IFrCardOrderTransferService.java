package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrCardOrderPayMode;
import com.yj.dal.model.FrCardOrderTransfer;
import com.yj.service.base.BaseService;

import java.util.List;

/**
 * <p>
 * 会员卡 转让订单 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-23
 */
public interface IFrCardOrderTransferService extends BaseService<FrCardOrderTransfer> {

    JsonResult quereyTransferList(String cardId,String  CustomerCode)throws YJException;

    JsonResult addTransferSub(FrCardOrderTransfer frCardOrderTransfer, List<FrCardOrderPayMode> frCardOrderPayModes )throws YJException;

    JsonResult toDelTransferCard(FrCardOrderTransfer frCardOrderTransfer,String shopId,String personnelId)throws YJException;
}
