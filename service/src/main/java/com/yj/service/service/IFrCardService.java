package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.PageUtil;
import com.yj.common.util.PageUtils;
import com.yj.dal.model.*;
import com.yj.dal.param.NumParam;
import com.yj.service.base.BaseService;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员卡表 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-04
 */
public interface IFrCardService extends BaseService<FrCard> {

    PageUtils queryPage(Map<String, Object> params) throws YJException;

    Boolean insertList(NumParam param) throws YJException;

    Boolean updateInvalid(String cardNo, Integer type) throws YJException;

    Boolean updateInvalidById(String id, Integer type) throws YJException;

    JsonResult getMemberCard(String id);

    JsonResult addMemberCardType(FrCardType cardType);

    boolean addInvalidCardNo(FrCard frCard, String code) throws YJException;

    JsonResult saveInvalidTime(FrCard frCard);

    Map<String,Object> queryCardType(String cardId);

    /**
     * 获取指定客户的会员卡信息（新）
     * @param pageUtil
     * @return
     * @throws YJException
     */
    JsonResult queryUserCardList(PageUtil<FrCard> pageUtil) throws YJException;

    /**
     * 根据会员卡信息获取单挑数据
     * @param code
     * @param cardId
     * @return
     * @throws YJException
     */
    JsonResult queryUserCardOne(String code,String cardId) throws YJException;

    JsonResult queryByFrCardList(FrCard frCard) throws YJException;


    /**
     * 会员卡订单
     * @param frCardAgreement   协议规则
     * @param frCard              会员卡信息
     * @param frCardOrderInfo    订单信息
     * @param frCardOrderPayModes  支付订单
     * @param frCardOrderAllotSetList  业绩分配
     * @param mapS   :orderSplitId  分期订单ID    messFlag 订单交易内容
     * @param mapI   infoType   订单类型（1、新购；2、续卡;3、转卡；4、卡升级）
     * @return
     * @throws YJException
     */
    JsonResult toAddFrCard(FrCardAgreement frCardAgreement,
                           FrCard frCard, FrCardOrderInfo frCardOrderInfo,
                           List<FrCardOrderPayMode> frCardOrderPayModes,
                           List<FrCardOrderAllotSet> frCardOrderAllotSetList,
                           Map<String,String> mapS,Map<String,Integer> mapI) throws YJException;

    /**
     * 关联会员卡前数据，获取单条会员卡信息 会员卡Id 需设置
     * @param frCard
     * @return
     * @throws YJException
     */
    Map<String,Object> queryCollectionOriginalSet(FrCard frCard)throws YJException;


    List<FrCard> getCardInformation(String cid)throws YJException;


//    ==================================调用方法
    /**
     * 根据开卡时间获取失效时间
     * @param serviceLifeS
     * @param start
     * @return
     * @throws YJException
     */
    String getInv(String serviceLifeS,String start) throws YJException;

    FrCardOrderPriceDatail getCardDatailAndPrice(FrCardOrderInfo frCardOrderInfo,boolean orderStatus,Integer cardType,Integer orderType)throws YJException;

    /**
     * 添加续卡订单信息
     * @param frClient
     * @param frCard
     * @param frCardOrderInfo
     * @param frCardOrderPayModes
     * @param frCardOrderAllotSetList
     * @param mapS
     * @param mapI
     * @return
     * @throws YJException
     */
    JsonResult addSaveCustomer(FrClient frClient,FrCard frCard, FrCardOrderInfo frCardOrderInfo,
                               List<FrCardOrderPayMode> frCardOrderPayModes,
                               List<FrCardOrderAllotSet> frCardOrderAllotSetList,
                               Map<String,String> mapS,Map<String,Integer> mapI,FrClient temp)throws YJException;

    List<Map<String,Object>> getClientCardList(String client,String code)throws YJException;


    /**
     * 计算时间卡的剩余权益
     * @param type  会员卡类型
     * @param bindTime   会员卡开卡时间
     * @param haveNum    会员卡剩余权益
     * @return
     */
    Double getHaveNumByType(Integer type, String bindTime,Double haveNum)throws YJException;

    boolean addOpenCardClient(FrCard frCard, List<String> imagesList,List<String> priceIdList, List<FrClientPersonnelRelate> frClientPersonnelRelateList,boolean isFlag,StringBuffer imagePath)throws YJException;



    /**
     * 更新会员卡状态的定时任务
     * @throws YJException
     */
    void updateCardTime()throws YJException;

    /**
     * 根据指定的会员卡ID批量更新
     * @param stopCardList
     */
    void toUpdateStopTime(List<String> stopCardList,Integer status)throws YJException;

    void toUpdateCardStop()throws YJException;

    Map<String,Object> getNumCard(String clientId,String CustomerCode,Integer num,boolean isFlag)throws YJException;


}

