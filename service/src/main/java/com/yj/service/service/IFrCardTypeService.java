package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrCardType;
import com.yj.service.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 卡类型表 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-10
 */
public interface IFrCardTypeService extends BaseService<FrCardType> {

    JsonResult updateFrCardType(FrCardType frCardType);

    JsonResult updateFrCard(FrCardType frCardType);

    @Transactional(rollbackFor = Exception.class)
    JsonResult addFrCardType (FrCardType frCardType, String[] id, Integer type);

    @Transactional(rollbackFor = Exception.class)
    JsonResult addCurrentFrCardType(FrCardType frCardType, String[] id, Integer type, String cardType);

    JsonResult deleteFrCardType(String id ,String storeId);

    JsonResult deleteCardType(String id);

    JsonResult getGeneralStoreList(String code);

    JsonResult generalStoreUpdate(FrCardType frCardType);

    JsonResult getFrCardTypeDetails(String id);

    JsonResult selectFrCardTypeTime(Integer type,String code);

    JsonResult getFrCardTypeByPid(String pId, String storeId);

    JsonResult updateIsUsing(String[] ids, FrCardType frCardType, Boolean show);

    JsonResult getGeneralStoreNotList(String code);

    List<FrCardType> queryByShopIdList(String shopId, String code, Integer type,Integer typeSetState) throws YJException;

    FrCardType queryByCardId(String cardId ,String CustomerCode)throws  YJException;

    List<Map<String,Object>> getCardTypeByShopIdList(String code) throws YJException;
}
