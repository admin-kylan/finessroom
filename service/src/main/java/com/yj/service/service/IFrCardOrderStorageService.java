package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrCardOrderStorage;
import com.yj.service.base.BaseService;

import java.util.List;

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

    JsonResult toRefundAllPrice(FrCardOrderStorage frCardOrderStorage)throws YJException;

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

    /**
     * 根据指定会员卡信息获取可退的储值金额
     * @param frCardOrderStorage   注意需包含会员卡号，子卡即为子卡id
     * @param isFlage  是否子卡 ：true 是， false，不是
     * @param allPrice  剩余金额
     * @param cardList  根据会员卡查询的续卡订单中的原先的会员卡信息，如果未null 会根据frCardOrderStorage里这是的会员卡id 查询
     * @param toFlag   如果cardList是null 是否需要查询 toFlag，true 需要，false 不需要
     * @return
     */
    public Double refundStorage(FrCardOrderStorage frCardOrderStorage, boolean isFlage, Double allPrice, List<String> cardList, boolean toFlag);

    /**
     * 根据指定会员卡获取，会员卡的可退储值金额及信息
     * @param code
     * @param cardId
     * @return
     */
    public Double getAllBlackStorage(String code,String cardId,String clientId)throws YJException;
}
