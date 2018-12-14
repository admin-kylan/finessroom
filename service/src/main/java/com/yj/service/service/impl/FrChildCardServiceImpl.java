package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.*;
import com.yj.dal.dao.*;
import com.yj.dal.model.*;
import com.yj.service.base.BaseServiceImpl;
import com.yj.service.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员卡表 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-04
 */
@Service
public class FrChildCardServiceImpl extends BaseServiceImpl<FrChildCardMapper, FrChildCard> implements IFrChildCardService {

    private final Logger log = LoggerFactory.getLogger(FrCardServiceImpl.class);
    @Resource
    private IFrCardOriginalSetService iFrCardOriginalSetService;
    @Resource
    private IFrCardOrderDatailService iFrCardOrderDatailService;
    @Resource
    private IFrClientService iFrClientService;
    @Resource
    private IFrCardService iFrCardService;

    @Override
    public List<Map<String,Object>> selectChildCardList(FrChildCard frChildCard) throws YJException {
        return baseMapper.selectChildCardList(frChildCard);
    }

    /**
     * 查询单条数据
     * @param frChildCard
     * @return
     */
    @Override
    public FrChildCard selectChildCardOne(FrChildCard frChildCard) {
       return baseMapper.selectOne(frChildCard);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveChildCard(FrChildCard frChildCard) throws YJException  {
        Integer successCount  = 0;
        if(frChildCard == null){
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        boolean isFlage = true;

        //插入
        if(StringUtils.isEmpty(frChildCard.getId())){
            isFlage = false;
            successCount = this.toSetInters(frChildCard);
        }
        //更新
        if(isFlage){
            successCount = this.toSetUpdate(frChildCard);
        }
        return SqlHelper.retBool(successCount);
    }

    /**
     * 插入
     * @param frChildCard
     * @return
     * @throws YJException
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer toSetInters(FrChildCard frChildCard)throws YJException  {
        Integer successCount  = 0;
        if(frChildCard != null){
            if(StringUtils.isEmpty(frChildCard.getCardNo()) || frChildCard.getShareBenefit()==null || frChildCard.getCardHandle()==null
                || StringUtils.isEmpty(frChildCard.getUserName()) || StringUtils.isEmpty(frChildCard.getMobile()) || StringUtils.isEmpty(frChildCard.getSaleManId())
                    || StringUtils.isEmpty(frChildCard.getCustomerCode()) || StringUtils.isEmpty(frChildCard.getHandlePersonalId()) || StringUtils.isEmpty(frChildCard.getParentCardId())){
                throw new YJException(YJExceptionEnum.REQUEST_NULL);
            }
            if(StringUtils.isEmpty(frChildCard.getOutCardNo())){
                frChildCard.setOutCardNo("");
            }
            //判断子卡持卡客户是否存在-----------如果没有生成现有客户信息；
            FrClient frClient = this.getClientUser(frChildCard);
            if(StringUtils.isEmpty(frChildCard.getChildClientId())){
                frChildCard.setChildClientId(frClient.getId());
            }
            frChildCard.setUsing(true);
            frChildCard.setId(UUIDUtils.generateGUID());
            //查询父卡的卡前设置，如果是与子卡独立分割使用，父卡需要扣除对应的卡权益
            FrCardOriginalSet frCardOriginalSet = iFrCardOriginalSetService.selectOne(new EntityWrapper<FrCardOriginalSet>().where("card_id={0}",frChildCard.getParentCardId()).and("CustomerCode={0}",frChildCard.getCustomerCode()));
            if(frCardOriginalSet == null ){
                throw  new YJException(YJExceptionEnum.PARENT_CARD_ID_EXISTED);
            }
            FrCard frCard = null;
            Double havaRightsNum = 0.0;
            Double shareNum = 0.0;
            if( CommonUtils.ORIGINALSET_ZKXFFS_1.equals(frCardOriginalSet.getZkXffs())){
                if(frChildCard.getShareNum() != null && frChildCard.getShareNum() > 0){
                    frCard = iFrCardService.selectById(frChildCard.getParentCardId());
                    if (frCard == null){
                        throw  new YJException(YJExceptionEnum.PARENT_CARD_ID_EXISTED);
                    }
                    if(CommonUtils.CARD_STATUS_2 == frCard.getStatus() || CommonUtils.CARD_STATUS_3 == frCard.getStatus() || CommonUtils.CARD_STATUS_6 == frCard.getStatus()){
                        throw  new  YJException(YJExceptionEnum.FRCARD_STATUS_EXISTED);
                    }
                    //获取父卡的剩余权益
                    FrCardOrderDatail frCardOrderDatail = new FrCardOrderDatail();
                    frCardOrderDatail.setCardId(frCard.getId());
                    frCardOrderDatail.setClientId(frCard.getClientId());
                    frCardOrderDatail.setCustomerCode(frCard.getCustomerCode());
                    frCardOrderDatail.setType(CommonUtils.ORDER_DATAIL_TYPE_1);
                    havaRightsNum = iFrCardOrderDatailService.querySumOrderPrice(frCardOrderDatail, false);
                    shareNum = (frChildCard.getShareNum()+0.0);
                    if(havaRightsNum == null || havaRightsNum <= 0.0){
                        throw  new YJException(YJExceptionEnum.PARENT_CARD_NUM_EXISTED);
                    }
                    //判断父类剩余权益是否足够
                    if(havaRightsNum < shareNum){
                        throw  new YJException(YJExceptionEnum.PARENT_CARD_NUM_EXISTED);
                    }
                    //父卡的剩余权益需扣除指定数据
                    frCardOrderDatail.setCardType(frCard.getType());
                    frCardOrderDatail.setOrderStatus(false);
                    Double have = iFrCardOrderDatailService.getOrderPriceByOrderStatus(false, shareNum);
                    frCardOrderDatail.setOrderRightsNum(have);
                    frCardOrderDatail.setShopId(frChildCard.getShopId());
                    frCardOrderDatail.setPersonnelId(frChildCard.getHandlePersonalId());
                    frCardOrderDatail.setFlag("子卡独立分割父卡权益："+have);
                    frCardOrderDatail.setStatus(CommonUtils.ORDER_STATUS_0);
                    frCardOrderDatail.setAuditStatus(CommonUtils.AUDIT_ORDER_STATUS_0);
                    frCardOrderDatail.setOrderType(CommonUtils.PAY_MODE_ORDER_TYPE_11);
                    frCardOrderDatail.setId(UUIDUtils.generateGUID());
                    frCardOrderDatail.setOrderId(frChildCard.getId());
                    iFrCardOrderDatailService.queryOrderPriceAmt(frCardOrderDatail,false);
                    //子卡权益设置-------------------------后期可能需调整，子卡是否有单独的会员卡号？
                    //子卡的卡类型暂定是父卡的卡类型
                    FrCardOrderDatail frCardOrderDatail1 = new FrCardOrderDatail();
                    frCardOrderDatail1.setCardId(frChildCard.getId());
                    frCardOrderDatail1.setClientId(frClient.getId());
                    frCardOrderDatail1.setCustomerCode(frChildCard.getCustomerCode());
                    frCardOrderDatail1.setType(CommonUtils.ORDER_DATAIL_TYPE_1);
                    frCardOrderDatail1.setOrderStatus(true);
                    have = iFrCardOrderDatailService.getOrderPriceByOrderStatus(true, shareNum);
                    frCardOrderDatail1.setOrderRightsNum(have);
                    frCardOrderDatail1.setShopId(frChildCard.getShopId());
                    frCardOrderDatail1.setPersonnelId(frChildCard.getHandlePersonalId());
                    frCardOrderDatail1.setFlag("子卡分割父卡权益："+have);
                    frCardOrderDatail1.setStatus(CommonUtils.ORDER_STATUS_0);
                    frCardOrderDatail1.setAuditStatus(CommonUtils.AUDIT_ORDER_STATUS_0);
                    frCardOrderDatail1.setOrderType(CommonUtils.PAY_MODE_ORDER_TYPE_11);
                    frCardOrderDatail1.setOrderId(frChildCard.getId());
                    frCardOrderDatail1.setCardType(frCard.getType());
                    frCardOrderDatail1.setId(UUIDUtils.generateGUID());
                    iFrCardOrderDatailService.queryOrderPriceAmt(frCardOrderDatail1,false);
                }
            }
            //插入子卡信息
            successCount =baseMapper.insert(frChildCard);
            //只有子卡独立分割才需要跟新数据
            if(frCard != null){
                FrCard frCard1 = new FrCard();
                iFrCardService.update(frCard1,new EntityWrapper<FrCard>().where("id={0}",frCard.getId()).and("CustomerCode={0}",frCard.getCustomerCode()));
            }
        }
        return successCount;
    }

    /**
     * 更新（待实现）
     * @param frChildCard
     * @return
     * @throws YJException
     */
    public Integer toSetUpdate(FrChildCard frChildCard){
        Integer successCount  = 0;

        return successCount;
    }

    @Override
    public Double queryChildCardShareNum(FrChildCard frChildCard) throws YJException {
        if(frChildCard == null ){
            throw new YJException(YJExceptionEnum.OBJECT_NOT_FOUND);
        }
        return  baseMapper.queryChildCardShareNum(frChildCard);
    }


    @Transactional(rollbackFor = Exception.class)
    public FrClient getClientUser(FrChildCard frChildCard)throws YJException {
        FrClient frClient = new FrClient();
        frClient.setClientName(frChildCard.getUserName());
        frClient.setMobile(frChildCard.getMobile());
        frClient.setCustomerCode(frChildCard.getCustomerCode());
        frClient.setId(frChildCard.getChildClientId());
        frClient = iFrClientService.addClientPersonal(frClient,true,frChildCard.getShopId());
        return  frClient;
    }

    /**
     * 统计了全部子卡的
     * @param frChildCard
     * @return
     * @throws YJException
     */
    @Override
    public Map<String,Double> queryChildCardAmt(FrChildCard frChildCard) throws YJException {
        FrChildCard frChildCard1 = new FrChildCard();
        frChildCard1.setCustomerCode(frChildCard.getCustomerCode());
        frChildCard1.setParentCardId(frChildCard.getParentCardId());
        List<Map<String, Object>> maps = baseMapper.queryChildCardAmt(frChildCard1);
        Double shareNum = 0.0;
        Double orderPrice = 0.0;
        Double orderRightsNum = 0.0;
        Map<String,Double>  m = new HashMap<>();
        if(maps != null && maps.size()>0){
            for(Map<String, Object> map : maps){
                Double shar = NumberUtilsTwo.getDouNum("shareNum",map);
                Double order = NumberUtilsTwo.getDouNum("orderPrice",map);
                Double orderRights =  NumberUtilsTwo.getDouNum("orderRightsNum",map);
                shareNum += shar;
                orderPrice += order;
                orderRightsNum += orderRights;
            }
        }
        m.put("shareNum",shareNum);
        m.put("orderPrice",orderPrice);
        m.put("orderRightsNum",orderRightsNum);
        return m;
    }

    @Override
    public List<Map<String, Object>> queryChildCardAmtList(FrChildCard frChildCard) throws YJException {
        FrChildCard frChildCard1 = new FrChildCard();
        frChildCard1.setCustomerCode(frChildCard.getCustomerCode());
        frChildCard1.setParentCardId(frChildCard.getParentCardId());
        List<Map<String, Object>> maps = baseMapper.queryChildCardAmt(frChildCard1);
        return maps;
    }
}
