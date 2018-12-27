package com.yj.service.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.PageUtils;
import com.yj.dal.model.*;
import com.yj.service.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员卡补卡 、续卡、转卡、卡升级记录 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-06
 */
public interface IFrCardSupplyRecordService extends BaseService<FrCardSupplyRecord> {

   Map<String,Object> queryCarDsupplyRecordList(FrCardSupplyRecord frCardSupplyRecord) throws YJException;

   boolean toInsertAndAdd(FrCardSupplyRecord frCardSupplyRecord, List<FrCardOrderPayMode> frCardOrderPayModes,String shopId)throws YJException;

   /**
    * 添加续卡订单信息
    * @param frCardSupplyRecord
    * @param frCard
    * @param frCardOrderInfo
    * @param frCardOrderPayModes
    * @param frCardOrderAllotSetList
    * @param mapS
    * @param mapI
    * @return
    * @throws YJException
    */
   JsonResult addContinueList(FrCardSupplyRecord frCardSupplyRecord,FrCard frCard, FrCardOrderInfo frCardOrderInfo,
                              List<FrCardOrderPayMode> frCardOrderPayModes,
                              List<FrCardOrderAllotSet> frCardOrderAllotSetList,
                              Map<String,String> mapS,Map<String,Integer> mapI)throws YJException;


   /**
    * 根据获取指定用户的指定的会员卡的续卡数据
    */
   PageUtils queryContinueCardLis(Page page, Map<String,Object> map) throws YJException;

   /**
    * 添加卡升级或者转卡订单
    * @param frCardSupplyRecord
    * @param frCardOrderPayModes
    * @param mapS
    * @param mapI
    * @return
    * @throws YJException
    */
   JsonResult addTransferCard(FrCardSupplyRecord frCardSupplyRecord, List<FrCardOrderPayMode> frCardOrderPayModes,
                              Map<String,String> mapS,Map<String,Integer> mapI)throws YJException;


   String isFlagNewCardNo(String newCardNo,String code,Map<String,String> map);

   /**
    * 根据指定的会员卡id获取该会员卡的所有续卡记录
    * @param frCardSupplyRecord
    * @return
    */
   List<FrCardSupplyRecord> quereySupplyList(FrCardSupplyRecord frCardSupplyRecord);

   /**
    * 获取会员卡的所有续卡订单里的会员卡ID
    * @param cardId
    * @return
    */
   List<String> getCardIdList(String cardId,Integer type);

   /**
    * 根据续卡信息判断是否开卡
    */
   void updateSupplyRecordTime()throws YJException ;
}
