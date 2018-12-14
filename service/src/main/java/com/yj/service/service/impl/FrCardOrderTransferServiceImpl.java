package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CommonUtils;
import com.yj.common.util.StringUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.FrCardOriginalSetMapper;
import com.yj.dal.model.*;
import com.yj.dal.dao.FrCardOrderTransferMapper;
import com.yj.service.service.*;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员卡 转让订单 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-23
 */
@Service
public class FrCardOrderTransferServiceImpl extends BaseServiceImpl<FrCardOrderTransferMapper, FrCardOrderTransfer> implements IFrCardOrderTransferService {

    @Resource
    private IFrStoreCommonService iFrStoreCommonService;
    @Resource
    private FrCardOriginalSetMapper frCardOriginalSetMapper;
    @Resource
    private IFrClientService iFrClientService;
    @Resource
    private IFrCardService iFrCardService;
    @Resource
    private IFrCardOrderPayModeService iFrCardOrderPayModeService;
    @Resource
    private IFrCardOrderPriceDatailService iFrCardOrderPriceDatailService;


    /**
     * 获取转让列表，及转让手续费
     * @param cardId
     * @param CustomerCode
     * @return
     * @throws YJException
     */
    @Override
    public JsonResult quereyTransferList(String cardId, String CustomerCode) throws YJException {
        if(StringUtils.isEmpty(cardId) || StringUtils.isEmpty(CustomerCode)){
            return JsonResult.failMessage("会员卡ID、客户代码需提供");
        }
        FrCardOrderTransfer frCardOrderTransfer = new FrCardOrderTransfer();
        frCardOrderTransfer.setCustomerCode(CustomerCode);
        frCardOrderTransfer.setCardId(cardId);
        //转让手续费
        Double changeTransferFee = this.getOrginalSet(frCardOrderTransfer);
        Double totalPrice = 0.0;
        //获取转让列表
        List<Map<String,Object>> list =  baseMapper.quereyTransferList(frCardOrderTransfer);
        if(list != null && list.size() >0){
            for(Map<String,Object> map : list){
                Object price = map.get("transferPrice");
                if (price!=null){
                    totalPrice += Double.valueOf(price.toString()) ;
                }
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        map.put("transferFee",changeTransferFee);
        map.put("totalPrice",totalPrice);
        return JsonResult.success(map);
    }

    /**
     * 添加转让会员卡
     * @param frCardOrderTransfer
     * @param frCardOrderPayModes
     * @return
     * @throws YJException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult addTransferSub(FrCardOrderTransfer frCardOrderTransfer, List<FrCardOrderPayMode> frCardOrderPayModes) throws YJException {
         if(frCardOrderTransfer == null){
             return JsonResult.failMessage("参数获取异常");
         }
         if(StringUtils.isEmpty(frCardOrderTransfer.getCardId()) || StringUtils.isEmpty(frCardOrderTransfer.getShopId())
                 || StringUtils.isEmpty(frCardOrderTransfer.getTransferClientId()) || StringUtils.isEmpty(frCardOrderTransfer.getPersonnelId())
                 || frCardOrderTransfer.getTotalPrice() == null || StringUtils.isEmpty(frCardOrderTransfer.getCustomerCode())
                 || StringUtils.isEmpty(frCardOrderTransfer.getCatcherClientName()) || StringUtils.isEmpty(frCardOrderTransfer.getCatcherClientPhone())){
             return  JsonResult.failMessage("参数设置有误");
         }
         //判断要转让的会员卡是否有效
         FrCard  frCard = iFrCardService.selectOne(new EntityWrapper<FrCard>().where("id={0}",frCardOrderTransfer.getCardId())
         .and("client_id={0}",frCardOrderTransfer.getTransferClientId()).and("is_using={0}",1)
                 .and("CustomerCode={0}",frCardOrderTransfer.getCustomerCode()));
         if(frCard == null){
             return  JsonResult.failMessage("查无此会员卡的有效信息");
         }
         if(CommonUtils.CARD_STATUS_2 == frCard.getStatus() || CommonUtils.CARD_STATUS_3 == frCard.getStatus() || CommonUtils.CARD_STATUS_6 == frCard.getStatus()){
             return JsonResult.failMessage("冻结、过期、历史卡禁止转让卡操作");
         }
        //转让手续费
        frCardOrderTransfer.setId(UUIDUtils.generateGUID());
        //重新获取转让手续费
        Double changeTransferFee = this.getOrginalSet(frCardOrderTransfer);
        if(changeTransferFee != null  && changeTransferFee > 0 ){
            if(frCardOrderPayModes == null || frCardOrderTransfer.getTransferPrice() == null || frCardOrderTransfer.getTransferPrice() < 0 ){
                return JsonResult.failMessage("此卡种转让需要支付手续费，请重新核对");
            }
        }
        //查询并初始化支付金额
        Double payPrice = iFrCardOrderPayModeService.getAllPrice(frCardOrderPayModes,frCardOrderTransfer.getId());
        if( payPrice < changeTransferFee ){
            return JsonResult.failMessage("支付金额不足");
        }
         boolean  isFlag = true;
//       查询是否可转让潜在客户
         FrStoreCommon frStoreCommon = new FrStoreCommon();
         frStoreCommon.setCustomerCode(frCardOrderTransfer.getCustomerCode());
         frStoreCommon =  iFrStoreCommonService.selectTopOne(frStoreCommon);
         //默认可以转让潜在客户;
         if(frStoreCommon != null){
             isFlag = frStoreCommon.getMemberTransfer();
         }
         FrClient frClient = new FrClient();
         frClient.setClientName(frCardOrderTransfer.getCatcherClientName());
         frClient.setMobile(frCardOrderTransfer.getCatcherClientPhone());
         frClient.setCustomerCode(frCardOrderTransfer.getCustomerCode());
         frClient.setId(frCardOrderTransfer.getCatcherClientId());
         FrClient frClient1 = iFrClientService.addClientPersonal(frClient,isFlag,frCardOrderTransfer.getShopId());
         if(frClient1 == null && !isFlag){
             return JsonResult.failMessage("不能转让非现有客户呦");
         }
         FrClient frClient2 = iFrClientService.selectById(frCard.getClientId());
         //初始化信息
        frCardOrderTransfer.setCatcherClientId(frClient1.getId());
        frCardOrderTransfer.setTransferUserName(frClient2.getClientName());
        frCardOrderTransfer.setTransferUserPhone(frClient2.getMobile());
        frCardOrderTransfer.setStatus(CommonUtils.ORDER_STATUS_0);
        frCardOrderTransfer.setAuditStatus(CommonUtils.AUDIT_ORDER_STATUS_0);
        FrCard frCard1 = new FrCard();
        frCard1.setClientId(frCardOrderTransfer.getCatcherClientId());
        iFrCardService.update(frCard1,new EntityWrapper<FrCard>().where("id={0}",frCardOrderTransfer.getCardId())
                .and("client_id={0}",frCardOrderTransfer.getTransferClientId()).and("is_using={0}",1)
                .and("CustomerCode={0}",frCardOrderTransfer.getCustomerCode()));
        //插入转让卡数据
        baseMapper.insert(frCardOrderTransfer);
        //插入支付信息
        if(frCardOrderPayModes != null && frCardOrderPayModes.size()>0){
            for(FrCardOrderPayMode frCardOrderPayMode:frCardOrderPayModes){
                frCardOrderPayMode.setId(UUIDUtils.generateGUID());
                frCardOrderPayMode.setOrderId(frCardOrderTransfer.getId());
                iFrCardOrderPayModeService.insert(frCardOrderPayMode);
            }
            //初始化资金表数据
            FrCardOrderPriceDatail frCardOrderPriceDatail = this.getCardDatailAndPrice(frCardOrderTransfer,frCard.getType());
            iFrCardOrderPriceDatailService.insert(frCardOrderPriceDatail);
        }
        return JsonResult.success(true);
    }

    /**
     * 查询卡前设置的转让手续费
     * @param frCardOrderTransfer
     * @return
     */
    public Double getOrginalSet(FrCardOrderTransfer frCardOrderTransfer) {
        //转让手续费
        FrCardOriginalSet frCardOriginalSet = new FrCardOriginalSet();
        frCardOriginalSet.setCardId(frCardOrderTransfer.getCardId());
        frCardOriginalSet.setCustomerCode(frCardOrderTransfer.getCustomerCode());
        frCardOriginalSet = frCardOriginalSetMapper.selectOne(frCardOriginalSet);
        Double changeTransferFee = 0.0;
        if(frCardOriginalSet != null){
            changeTransferFee = frCardOriginalSet.getChangeTransferFee();
            if(changeTransferFee == null){
                changeTransferFee = 0.0;
            }
        }
        return  changeTransferFee;
    }

    public FrCardOrderPriceDatail getCardDatailAndPrice(FrCardOrderTransfer frCardOrderTransfer,Integer cardType)throws YJException{
        FrCardOrderPriceDatail frCardOrderPriceDatail = new FrCardOrderPriceDatail();
        frCardOrderPriceDatail.setStatus(CommonUtils.ORDER_STATUS_0);
        frCardOrderPriceDatail.setAuditStatus(CommonUtils.AUDIT_ORDER_STATUS_0);
        frCardOrderPriceDatail.setId(UUIDUtils.generateGUID());
        frCardOrderPriceDatail.setCardId(frCardOrderTransfer.getCardId());
        frCardOrderPriceDatail.setClientId(frCardOrderTransfer.getTransferClientId());
        frCardOrderPriceDatail.setCustomerCode(frCardOrderTransfer.getCustomerCode());
        frCardOrderPriceDatail.setCardType(cardType);
//      支付状态（0：支出 1：收入）
        frCardOrderPriceDatail.setOrderStatus(false);
        Double orderPrice = iFrCardOrderPriceDatailService.getOrderPriceByOrderStatus(false,frCardOrderTransfer.getTransferPrice());
        frCardOrderPriceDatail.setOrderPrice(orderPrice);
        frCardOrderPriceDatail.setPersonnelId(frCardOrderTransfer.getPersonnelId());
        frCardOrderPriceDatail.setOrderId(frCardOrderTransfer.getId());
        frCardOrderPriceDatail.setOrderType(CommonUtils.PAY_MODE_ORDER_TYPE_9);
        frCardOrderPriceDatail.setShopId(frCardOrderTransfer.getShopId());
        return frCardOrderPriceDatail;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult toDelTransferCard(FrCardOrderTransfer frCardOrderTransfer,String shopId,String personnelId) throws YJException {
        if(frCardOrderTransfer == null){
            return JsonResult.failMessage("参数获取异常");
        }
        if(StringUtils.isEmpty(frCardOrderTransfer.getId()) || StringUtils.isEmpty(frCardOrderTransfer.getCatcherClientId())
            || StringUtils.isEmpty(frCardOrderTransfer.getTransferClientId()) || StringUtils.isEmpty(frCardOrderTransfer.getCardId())){
            return JsonResult.failMessage("转让信息的ID、承接客户ID、转让客户ID、转让的会员卡ID，未设置");
        }
        if(frCardOrderTransfer.getStatus() == null || frCardOrderTransfer.getAuditStatus() == null ){
            return  JsonResult.failMessage("数据审核状态设置有误");
        }
        if(CommonUtils.ORDER_STATUS_1 == frCardOrderTransfer.getStatus() || CommonUtils.AUDIT_ORDER_STATUS_1 ==  frCardOrderTransfer.getAuditStatus()){
            return JsonResult.failMessage("已经审核通过的数据，禁止冲销");
        }
        if(CommonUtils.ORDER_TYPE_2 == frCardOrderTransfer.getTransferStatus()){
            return JsonResult.failMessage("已冲销的数据，禁止操作");
        }
        FrCard frCard = iFrCardService.selectOne(new EntityWrapper<FrCard>().where("id={0}",frCardOrderTransfer.getCardId()).and("is_using={0}",1)
                .and("client_id={0}",frCardOrderTransfer.getCatcherClientId()).and("CustomerCode={0}",frCardOrderTransfer.getCustomerCode()));
        if(frCard == null){
            return JsonResult.failMessage("会员卡信息获取有误");
        }
        if(CommonUtils.CARD_STATUS_6 == frCard.getStatus()){
            return JsonResult.failMessage("历史卡禁止任何操作");
        }
        //准备更新会员卡信息
        FrCard frCard1 = new FrCard();
        frCard1.setClientId(frCardOrderTransfer.getTransferClientId());
        iFrCardService.update(frCard1,new EntityWrapper<FrCard>().where("id={0}",frCardOrderTransfer.getCardId())
                .and("client_id={0}",frCardOrderTransfer.getCatcherClientId()).and("is_using={0}",1)
                .and("CustomerCode={0}",frCardOrderTransfer.getCustomerCode()));
        //更新转让冲销数据
        FrCardOrderTransfer frCardOrderTransfer1 = new FrCardOrderTransfer();
        frCardOrderTransfer1.setTransferStatus(CommonUtils.ORDER_TYPE_2);
        baseMapper.update(frCardOrderTransfer1,new EntityWrapper<FrCardOrderTransfer>().where("id={0}",frCardOrderTransfer.getId())
                .and("CustomerCode={0}",frCardOrderTransfer.getCustomerCode()));
        //如果之前有收费，需插入一条退款的资金明细
        //初始化资金表数据
        if(frCardOrderTransfer.getTransferPrice() != null && frCardOrderTransfer.getTransferPrice() >0){
            FrCardOrderPriceDatail frCardOrderPriceDatail
                    = iFrCardOrderPriceDatailService.selectOne(new EntityWrapper<FrCardOrderPriceDatail>().where("card_id={0}",frCardOrderTransfer.getCardId())
                    .and("client_id={0}",frCardOrderTransfer.getTransferClientId()).and("CustomerCode={0}",frCardOrderTransfer.getCustomerCode())
                    .and("order_type={0}",CommonUtils.PAY_MODE_ORDER_TYPE_9).and("order_id={0}",frCardOrderTransfer.getId()).and("order_status={0}",0));
            if(frCardOrderPriceDatail != null){
                FrCardOrderPriceDatail frCardOrderPriceDatail1 = new FrCardOrderPriceDatail();
                frCardOrderPriceDatail1.setId(UUIDUtils.generateGUID());
                frCardOrderPriceDatail1.setStatus(CommonUtils.ORDER_STATUS_0);
                frCardOrderPriceDatail1.setAuditStatus(CommonUtils.AUDIT_ORDER_STATUS_0);
                frCardOrderPriceDatail1.setOrderType(CommonUtils.PAY_MODE_ORDER_TYPE_9);
                frCardOrderPriceDatail1.setCardId(frCardOrderPriceDatail.getCardId());
                frCardOrderPriceDatail1.setClientId(frCardOrderPriceDatail.getClientId());
                frCardOrderPriceDatail1.setCustomerCode(frCardOrderPriceDatail.getCustomerCode());
                frCardOrderPriceDatail1.setCardType(frCardOrderPriceDatail.getCardType());
                frCardOrderPriceDatail1.setOrderStatus(true);
                Double orderPrice = iFrCardOrderPriceDatailService.getOrderPriceByOrderStatus(true,frCardOrderPriceDatail.getOrderPrice());
                frCardOrderPriceDatail1.setOrderPrice(orderPrice);
                frCardOrderPriceDatail1.setOrderId(frCardOrderPriceDatail.getOrderId());
                frCardOrderPriceDatail1.setShopId(shopId);
                frCardOrderPriceDatail1.setPersonnelId(personnelId);
                iFrCardOrderPriceDatailService.insert(frCardOrderPriceDatail1);
            }
        }
        return JsonResult.success(true);
    }
}
