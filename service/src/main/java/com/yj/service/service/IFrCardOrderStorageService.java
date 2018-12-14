package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrCardOrderStorage;
import com.yj.service.base.BaseService;

/**
 * <p>
 * 会员卡 储值表 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-20
 */
public interface IFrCardOrderStorageService extends BaseService<FrCardOrderStorage> {

    JsonResult queryStorageCardLis(FrCardOrderStorage frCardOrderStorage)throws YJException;

    JsonResult toInterStorageCard(FrCardOrderStorage frCardOrderStorage,String payModel)throws YJException;

    JsonResult toDelStorageCard(FrCardOrderStorage frCardOrderStorage)throws YJException;

    JsonResult toRefundSubimt(FrCardOrderStorage frCardOrderStorage)throws YJException;

    /**
     * 冲销、退款之前，先确认数据是否存在
     * @param frCardOrderStorage
     * @return
     * @throws YJException
     */
    public FrCardOrderStorage getOldOrderStorage(FrCardOrderStorage frCardOrderStorage)throws  YJException;

    /**
     * 转让储值信息
     * @param frCardOrderStorage
     * @return
     * @throws YJException
     */
    public JsonResult toTransfersStorage(FrCardOrderStorage frCardOrderStorage)throws  YJException;
}
