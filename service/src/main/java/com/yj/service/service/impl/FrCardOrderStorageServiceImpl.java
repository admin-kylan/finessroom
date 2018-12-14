package com.yj.service.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CommonUtils;
import com.yj.common.util.StringUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.model.*;
import com.yj.dal.dao.FrCardOrderStorageMapper;
import com.yj.service.service.*;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 会员卡 储值表 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-20
 */
@Service
public class FrCardOrderStorageServiceImpl extends BaseServiceImpl<FrCardOrderStorageMapper, FrCardOrderStorage> implements IFrCardOrderStorageService {

    @Resource
    private IFrCardOrderDatailService iFrCardOrderDatailService;
    @Resource
    private IFrCardOrderPayModeService iFrCardOrderPayModeService;
    @Resource
    private IFrCardOrderPriceDatailService iFrCardOrderPriceDatailService;
    @Resource
    private IFrCardService iFrCardService;
    @Resource
    private IFrCardOrderInfoService frCardOrderInfoService;

    @Override
    public JsonResult queryStorageCardLis(FrCardOrderStorage frCardOrderStorage) throws YJException {
        if (frCardOrderStorage == null) {
            return JsonResult.failMessage("参数有误");
        }
        if(StringUtils.isEmpty(frCardOrderStorage.getCardId()) || StringUtils.isEmpty(frCardOrderStorage.getCustomerCode())
                || StringUtils.isEmpty(frCardOrderStorage.getClientId())){
            return  JsonResult.failMessage("会员卡ID，客户代码，客户Id不能为空");
        }
        //统计剩余的储值金额
        Double orderPrice = this.getOrderPrice(frCardOrderStorage);
        //获取储值列表
        List<Map<String,Object>> list = baseMapper.queryStorageCardLis(frCardOrderStorage);
        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        map.put("orderPrice",orderPrice);
        return JsonResult.success(map);
    }

    /**
     * 添加储值信息
     * @param frCardOrderStorage
     * @param payModel
     * @return
     * @throws YJException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult toInterStorageCard(FrCardOrderStorage frCardOrderStorage,String payModel) throws YJException {
        if (frCardOrderStorage == null) {
            return JsonResult.failMessage("参数有误");
        }
        if(StringUtils.isEmpty(frCardOrderStorage.getCardId()) || StringUtils.isEmpty(frCardOrderStorage.getClientId()) || StringUtils.isEmpty(frCardOrderStorage.getCustomerCode())
                || StringUtils.isEmpty(frCardOrderStorage.getPersonnelId())|| StringUtils.isEmpty(frCardOrderStorage.getShopId()) || frCardOrderStorage.getStorePrice() == null || frCardOrderStorage.getTotalPrice() == null){
            return JsonResult.failMessage("设置的参数有误，请详细核对");
        }
        if(frCardOrderStorage.getTotalPrice() < 0){
            return JsonResult.failMessage("实际储值金额设置有误");
        }
        if(frCardOrderStorage.getStorePrice() == null || frCardOrderStorage.getStorePrice() <= 0 ){
            return JsonResult.failMessage("储值金额未设置");
        }
        Double givePrice = frCardOrderStorage.getGivePrice();
        if(givePrice == null){
            givePrice = 0.0;
        }
        //储值总金额 = 储值金额+赠送金额
        frCardOrderStorage.setSurplusPrice(givePrice+frCardOrderStorage.getStorePrice());
        if(frCardOrderStorage.getTotalPrice() > 0){
            if(StringUtils.isEmpty(payModel)){
                return JsonResult.failMessage("支付信息有误");
            }
        }
        //有包含票卷抵扣，需核对被抵扣人的信息是否有效
//        FrCard storeCard = null;
        //只有储值抵扣金额大于0 才需要
        if(frCardOrderStorage.getStoragePrice() > 0){
            if(StringUtils.isEmpty(frCardOrderStorage.getStorageCardId())){
                return JsonResult.failMessage("未获取到抵扣的会员卡信息");
            }
//            //验证该被抵扣会员的有效信息
//            storeCard = iFrCardService.selectOne(new EntityWrapper<FrCard>().where("id={0}",frCardOrderStorage.getStorageCardId())
//                            .and("is_using={0}",true).and("CustomerCode={0}",frCardOrderStorage.getCustomerCode()));
//            if(storeCard  == null){
//                return JsonResult.failMessage("未获取到票卷抵扣的会员对象");
//            }
//            if(CommonUtils.CARD_STATUS_6 == storeCard.getStatus()){
//                return JsonResult.failMessage("该会员卡为历史卡，无效操作");
//            }
        }
        //会员信息---操作的会员信息
        FrCard frCard = iFrCardService.selectOne(new EntityWrapper<FrCard>().where("id={0}",frCardOrderStorage.getCardId()).and("is_using={0}",true)
                .and("client_id={0}",frCardOrderStorage.getClientId()).and("CustomerCode={0}",frCardOrderStorage.getCustomerCode()));
        if(frCard == null){
            return  JsonResult.failMessage("未获取到对应的会员卡信息");
        }
        //生成订单编号
        String orderNo = frCardOrderInfoService.getOrderNo();
        //初始化储值信息
        frCardOrderStorage.setId(UUIDUtils.generateGUID());
        frCardOrderStorage.setOrderNo(orderNo);
        //初始化支付信息
        Map<String,Object> payM = JSONObject.parseObject(payModel,Map.class);
        List<FrCardOrderPayMode>  list = iFrCardOrderPayModeService.getPayModeList(payM,"L",CommonUtils.PAY_MODE_ORDER_TYPE_5);
        Double allPrice = 0.0;
        if(list != null && list.size() > 0 ){
            allPrice = iFrCardOrderPayModeService.getAllPrice(list,frCardOrderStorage.getId());
        }
        if(! allPrice.toString().equals(frCardOrderStorage.getTotalPrice().toString())){
            return JsonResult.failMessage("支付金额与应付金额不统一，请重新核对信息");
        }
        FrCardOrderPriceDatail frCardOrderPriceDatail = null;
        if(allPrice > 0 ){
            //初始化资金交易明细
          frCardOrderPriceDatail = this.getOrderPriceDatailInfo(frCardOrderStorage,false,frCard.getType());
        }
        //存放多条订单信息
        List<FrCardOrderDatail> frCardOrderDatailList = new ArrayList<>();
        //初始化设置会员卡订单明细
        FrCardOrderDatail frCardOrderDatail = this.getOrderDatailInfo(frCardOrderStorage,true,frCard.getType(),frCardOrderDatailList);
//        if(storeCard != null){
//            FrCardOrderStorage frCardOrderStorage1 = new FrCardOrderStorage();
//            frCardOrderStorage1.setId(frCardOrderStorage.getId());
//            frCardOrderStorage1.setCardId(storeCard.getId());
//            frCardOrderStorage1.setClientId(storeCard.getClientId());
//            frCardOrderStorage1.setShopId(frCardOrderStorage.getShopId());
//            frCardOrderStorage1.setPersonnelId(frCardOrderStorage.getPersonnelId());
//            frCardOrderStorage1.setSurplusPrice(frCardOrderStorage.getStoragePrice());
//            //先用备用的字段暂存下数据
//            frCardOrderStorage1.setFlag( frCard.getCardNo()+"：储值抵扣");
//            this.getOrderDatailInfo(frCardOrderStorage1,false,storeCard.getType(),frCardOrderDatailList);
//        }
        //数据初始化完毕准备插入数据
        baseMapper.insert(frCardOrderStorage);
        if(frCardOrderPriceDatail != null){
            //统计剩余的储值金额
            iFrCardOrderPriceDatailService.insert(frCardOrderPriceDatail);
        }
        if(frCardOrderDatailList != null && frCardOrderDatailList.size()>0){
            String orderPriceDatail = "";
            if(frCardOrderPriceDatail != null){
                orderPriceDatail = frCardOrderPriceDatail.getId();
            }
            for(FrCardOrderDatail frCardOrderDatail1 : frCardOrderDatailList){
                frCardOrderDatail1.setOrderPriceId(orderPriceDatail);
                iFrCardOrderDatailService.queryOrderPriceAmt(frCardOrderDatail1,false);
            }
        }
        if(list != null && list.size() > 0){
            for(FrCardOrderPayMode  frCardOrderPayMode:list){
                iFrCardOrderPayModeService.insert(frCardOrderPayMode);
            }
        }
        return JsonResult.success(true);
    }

    /**
     * 储值冲销
     * @param frCardOrderStorage
     * @return
     * @throws YJException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult toDelStorageCard(FrCardOrderStorage frCardOrderStorage) throws YJException {
        if (frCardOrderStorage == null) {
            return JsonResult.failMessage("参数有误");
        }
        if(StringUtils.isEmpty(frCardOrderStorage.getCardId()) || StringUtils.isEmpty(frCardOrderStorage.getClientId())
                || StringUtils.isEmpty(frCardOrderStorage.getCustomerCode()) || StringUtils.isEmpty(frCardOrderStorage.getId())
                || frCardOrderStorage.getStorageStatus() == null){
            return JsonResult.failMessage("设置的参数有误，请详细核对");
        }
        if(frCardOrderStorage.getStatus() == 1  || frCardOrderStorage.getAuditStatus() == 1){
            return JsonResult.failMessage("已经审核通过的数据，禁止冲销");
        }
        //获取之前储值的订单交易记录
        FrCardOrderDatail frCardOrderDatail = this.getOrderDatatilOne(frCardOrderStorage);
        if(frCardOrderDatail == null){
            return JsonResult.failMessage("查无此条数据对应订单的操作，冲销有误");
        }
        Integer storageStatus = frCardOrderStorage.getStorageStatus();
        //默认是储值冲销
        Integer storageStatusT = CommonUtils.ORDER_TYPE_2;
        boolean isFlag = true;
        boolean orderStatusPric = true; //默认是收入
        boolean toUpdate = true;
        Integer status = CommonUtils.ORDER_STATUS_0;
        Integer auditStatus = CommonUtils.AUDIT_ORDER_STATUS_0;
        //是转让冲销
        if(CommonUtils.ORDER_TYPE_4 == storageStatus || CommonUtils.ORDER_TYPE_7 == storageStatus || CommonUtils.ORDER_TYPE_8 == storageStatus){
            storageStatusT  = CommonUtils.ORDER_TYPE_1;
            orderStatusPric = false;
            isFlag = false;
            //根据赠送人的订单id查询 且状态是储值状态的，如果是已经有其他操作，禁止客户冲销
            FrCardOrderStorage frCardOrderStorage1 = new FrCardOrderStorage();
            frCardOrderStorage1.setGiveStorageId(frCardOrderStorage.getId());
            frCardOrderStorage1.setCustomerCode(frCardOrderStorage.getCustomerCode());
            frCardOrderStorage1.setUsing(true);
            frCardOrderStorage1.setStorageStatus(CommonUtils.ORDER_TYPE_1);
            frCardOrderStorage1 = baseMapper.selectOne(frCardOrderStorage1);
            if(frCardOrderStorage1 == null){
                return JsonResult.failMessage("查无承接人的有效储值数据，或者承接人已做其他操作");
            }
            //获取承接人的订单数据
            FrCardOrderDatail frCardOrderDatail1 = this.getOrderDatatilOne(frCardOrderStorage1);
            if(frCardOrderDatail1 == null){
                return JsonResult.failMessage("查无承接人的对应订单的操作，冲销有误");
            }
            frCardOrderStorage1.setCancelNote("转让方已冲销数据");
            toUpdate = this.getCleanOrderStorage(isFlag,true,CommonUtils.ORDER_TYPE_2,frCardOrderDatail1,frCardOrderStorage1,status,auditStatus);
            if( CommonUtils.ORDER_TYPE_7 == storageStatus){
                status = CommonUtils.ORDER_STATUS_1;
            }
            if( CommonUtils.ORDER_TYPE_8 == storageStatus){
                status = CommonUtils.ORDER_STATUS_1;
                auditStatus = CommonUtils.AUDIT_ORDER_STATUS_1;
            }
        }
        //是退款冲销
        if(CommonUtils.ORDER_TYPE_3 == storageStatus || CommonUtils.ORDER_TYPE_5 == storageStatus || CommonUtils.ORDER_TYPE_6 == storageStatus){
            if( CommonUtils.ORDER_TYPE_5 == storageStatus){
                status = CommonUtils.ORDER_STATUS_1;
            }
            if( CommonUtils.ORDER_TYPE_6 == storageStatus){
                status = CommonUtils.ORDER_STATUS_1;
                auditStatus = CommonUtils.AUDIT_ORDER_STATUS_1;
            }
            storageStatusT  = CommonUtils.ORDER_TYPE_1;
            orderStatusPric = false;
        }
        toUpdate = this.getCleanOrderStorage(isFlag,orderStatusPric,storageStatusT,frCardOrderDatail,frCardOrderStorage,status,auditStatus);
        return JsonResult.success(toUpdate);
    }

    /**
     *
     * 冲销数据
     * @param isFlage  false 转让冲销不涉及金额交易，只涉及会员卡订单交易明细
     * @param orderStatusPric
     * @param storageStatusT
     * @param frCardOrderDatail
     * @param frCardOrderStorage
     * @return
     * @throws YJException
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean getCleanOrderStorage(boolean isFlage,boolean orderStatusPric,Integer storageStatusT,
                                        FrCardOrderDatail frCardOrderDatail,FrCardOrderStorage frCardOrderStorage,
                                        Integer status, Integer auditStatus )throws YJException{
        boolean orderStatus = true;
        if(orderStatusPric){
            orderStatus = false;
        }
        frCardOrderDatail.setId(UUIDUtils.generateGUID());
        frCardOrderDatail.setStatus(CommonUtils.ORDER_STATUS_0);
        frCardOrderDatail.setAuditStatus(CommonUtils.AUDIT_ORDER_STATUS_0);
        frCardOrderDatail.setOrderStatus(orderStatus);
        frCardOrderDatail.setCreateTime(new Date());
        frCardOrderDatail.setUpdateTime(new Date());
        Double orderPrice = iFrCardOrderDatailService.getOrderPriceByOrderStatus(orderStatus,frCardOrderDatail.getOrderPrice());
        frCardOrderDatail.setOrderPrice(orderPrice);
        FrCardOrderPriceDatail frCardOrderPriceDatail = null;
        if(isFlage){
            //转让不涉及金额交易----------只涉及储值扣款
            //获取之前的储值的资金交易记录
            frCardOrderPriceDatail = this.getOrderPriceOne(frCardOrderStorage,frCardOrderDatail.getOrderPriceId());
            if (frCardOrderPriceDatail == null){
                throw  new YJException(YJExceptionEnum.OBJECT_NOT_FOUND);
            }
            //重新生成ID
            frCardOrderPriceDatail.setId(UUIDUtils.generateGUID());
            frCardOrderPriceDatail.setStatus(CommonUtils.ORDER_STATUS_0);
            frCardOrderPriceDatail.setAuditStatus(CommonUtils.AUDIT_ORDER_STATUS_0);
            //储值撤销是收入，退款撤销是支出，转让不涉及资金交易
            frCardOrderPriceDatail.setOrderStatus(orderStatusPric);
            //设置金额
            orderPrice = iFrCardOrderDatailService.getOrderPriceByOrderStatus(orderStatusPric,frCardOrderPriceDatail.getOrderPrice());
            frCardOrderPriceDatail.setOrderPrice(orderPrice);
            frCardOrderDatail.setOrderPriceId(frCardOrderPriceDatail.getId());
        }
        //更新前数据准备---冲销备注？
        FrCardOrderStorage frCardOrderStorage1 = new FrCardOrderStorage();
        frCardOrderStorage1.setStorageStatus(storageStatusT);
        frCardOrderStorage1.setStatus(status);
        frCardOrderStorage1.setAuditStatus(auditStatus);
        frCardOrderStorage1.setCancelNote(frCardOrderStorage.getCancelNote());
        frCardOrderStorage1.setRefundUserId(frCardOrderStorage.getRefundUserId());
        frCardOrderStorage1.setRefundNote(frCardOrderStorage.getRefundNote());

        baseMapper.update(frCardOrderStorage1,new EntityWrapper<FrCardOrderStorage>().where("id={0}",frCardOrderStorage.getId())
                .and("CustomerCode={0}",frCardOrderStorage.getCustomerCode()).and("client_id={0}",frCardOrderStorage.getClientId())
                .and("card_id={0}",frCardOrderStorage.getCardId()));
        //插入前再设置余额
        orderPrice = this.getOrderPrice(frCardOrderStorage);
        frCardOrderDatail.setOrderAmt(orderPrice + frCardOrderDatail.getOrderPrice());
        iFrCardOrderDatailService.insert(frCardOrderDatail);
        if(frCardOrderPriceDatail != null){
            iFrCardOrderPriceDatailService.insert(frCardOrderPriceDatail);
        }
        return  true;
    }


    /**
     * 添加退款信息
     * @param frCardOrderStorage
     * @return
     * @throws YJException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult toRefundSubimt(FrCardOrderStorage frCardOrderStorage) throws YJException {
        if (frCardOrderStorage == null) {
            return JsonResult.failMessage("参数有误");
        }
        if (StringUtils.isEmpty(frCardOrderStorage.getId()) || StringUtils.isEmpty(frCardOrderStorage.getCustomerCode())
                || StringUtils.isEmpty(frCardOrderStorage.getCardId()) || StringUtils.isEmpty(frCardOrderStorage.getClientId())
                || StringUtils.isEmpty(frCardOrderStorage.getRefundNote()) || StringUtils.isEmpty(frCardOrderStorage.getRefundUserId())
                || StringUtils.isEmpty(frCardOrderStorage.getPersonnelId())) {
            return JsonResult.failMessage("请求参数有误，请重新核对");
        }
        //查询之前的充值记录是否有效
        FrCardOrderStorage frCardOrderStorage1 = this.getOldOrderStorage(frCardOrderStorage);
        if(frCardOrderStorage1 == null){
            return JsonResult.failMessage("查无此储值数据，请重新核对");
        }
        if(frCardOrderStorage1.getAuditStatus() == null || frCardOrderStorage1.getAuditStatus() == null ){
            return  JsonResult.failMessage("数据审核状态设置有误");
        }
        if(CommonUtils.ORDER_TYPE_1 != frCardOrderStorage1.getStorageStatus()){
            return JsonResult.failMessage("只有储值状态才能操作退款");
        }
        //统计剩余的储值金额
        Double orderPrice = this.getOrderPrice(frCardOrderStorage);
        if(frCardOrderStorage1.getSurplusPrice() >orderPrice ){
           //实际付款金额
            return  JsonResult.failMessage("剩余储值金额不足");
        }
        //初始化要更新的储值信息
        frCardOrderStorage1.setRefundUserId(frCardOrderStorage.getRefundUserId());
        frCardOrderStorage1.setRefundNote(frCardOrderStorage.getRefundNote());
        //获取之前储值的订单交易记录
        FrCardOrderDatail frCardOrderDatail = this.getOrderDatatilOne(frCardOrderStorage1);
        if(frCardOrderDatail == null){
            return JsonResult.failMessage("查无此条数据对应订单的操作，冲销有误");
        }
       //状态更新已退款
        Integer storageStatusT = CommonUtils.ORDER_TYPE_3;
        if(CommonUtils.ORDER_STATUS_1 == frCardOrderStorage1.getStatus()){
            storageStatusT = CommonUtils.ORDER_TYPE_5;
            if(CommonUtils.AUDIT_ORDER_STATUS_1 == frCardOrderStorage1.getAuditStatus()){
                storageStatusT = CommonUtils.ORDER_TYPE_6;
            }
        }
        boolean orderStatusPric = true; //默认是收入
        boolean toUpdate = true;
        Integer status = CommonUtils.ORDER_STATUS_0;
        Integer auditStatus = CommonUtils.AUDIT_ORDER_STATUS_0;
        toUpdate = this.getCleanOrderStorage(false,orderStatusPric,storageStatusT,frCardOrderDatail,frCardOrderStorage1,status,auditStatus);
        return JsonResult.success(toUpdate);
    }

    /**
     *   获取单条的订单交易明细，根据时间获取最新的，用于储值的转让或者冲销操作；
     * @param frCardOrderStorage
     * @return
     * @throws YJException
     */
    public FrCardOrderDatail getOrderDatatilOne(FrCardOrderStorage frCardOrderStorage)throws YJException{
        FrCardOrderDatail frCardOrderDatail = new FrCardOrderDatail();
        frCardOrderDatail.setClientId(frCardOrderStorage.getClientId());
        frCardOrderDatail.setCardId(frCardOrderStorage.getCardId());
        frCardOrderDatail.setCustomerCode(frCardOrderStorage.getCustomerCode());
        frCardOrderDatail.setOrderId(frCardOrderStorage.getId());
        //指定查询储值账户的信息
        frCardOrderDatail.setType(CommonUtils.ORDER_DATAIL_TYPE_2);
        frCardOrderDatail.setOrderType(CommonUtils.PAY_MODE_ORDER_TYPE_5);
        frCardOrderDatail = iFrCardOrderDatailService.queryTopOne(frCardOrderDatail);
        return  frCardOrderDatail;
    }

    /**
     * 获取单条的资金交易明细，根据时间获取最新的，用于储值的转让或者冲销操作；
     * @param frCardOrderStorage
     * @return
     * @throws YJException
     */
    public FrCardOrderPriceDatail  getOrderPriceOne(FrCardOrderStorage frCardOrderStorage,String orderDatailId)throws YJException{
        FrCardOrderPriceDatail frCardOrderPriceDatail = new FrCardOrderPriceDatail();
        frCardOrderPriceDatail.setClientId(frCardOrderStorage.getClientId());
        frCardOrderPriceDatail.setCardId(frCardOrderStorage.getCardId());
        frCardOrderPriceDatail.setCustomerCode(frCardOrderStorage.getCustomerCode());
        frCardOrderPriceDatail.setOrderId(frCardOrderStorage.getId());
        frCardOrderPriceDatail.setId(orderDatailId);
        frCardOrderPriceDatail.setOrderType(CommonUtils.PAY_MODE_ORDER_TYPE_5);
        frCardOrderPriceDatail = iFrCardOrderPriceDatailService.queryTopOne(frCardOrderPriceDatail);
        return  frCardOrderPriceDatail;
    }

    /**
     * 初始化订单交易明细
     * @param frCardOrderStorage
     * @param orderStatus
     * @param cardType
     * @return
     */
    public FrCardOrderDatail getOrderDatailInfo(FrCardOrderStorage frCardOrderStorage,boolean orderStatus,Integer cardType, List<FrCardOrderDatail> frCardOrderDatailList)throws YJException{
        //初始化设置会员卡订单明细
        FrCardOrderDatail frCardOrderDatail = null;
        if(frCardOrderStorage != null){
            frCardOrderDatail = new FrCardOrderDatail();
            frCardOrderDatail.setCardId(frCardOrderStorage.getCardId());
            frCardOrderDatail.setClientId(frCardOrderStorage.getClientId());
            frCardOrderDatail.setShopId(frCardOrderStorage.getShopId());
            frCardOrderDatail.setUsing(true);
            frCardOrderDatail.setOrderId(frCardOrderStorage.getId());
            frCardOrderDatail.setType(CommonUtils.ORDER_DATAIL_TYPE_2);
            frCardOrderDatail.setOrderStatus(orderStatus);
            frCardOrderDatail.setId(UUIDUtils.generateGUID());
            frCardOrderDatail.setCustomerCode(frCardOrderStorage.getCustomerCode());
            // 以上是必须设置的参数
            frCardOrderDatail.setPersonnelId(frCardOrderStorage.getPersonnelId());
            //订单交易明细，请设置最后的总获取权益或者储值金额
            Double orderPrice = iFrCardOrderDatailService.getOrderPriceByOrderStatus(orderStatus,frCardOrderStorage.getSurplusPrice());
            frCardOrderDatail.setOrderPrice(orderPrice);
            frCardOrderDatail.setOrderType(CommonUtils.PAY_MODE_ORDER_TYPE_5);
            String flag = "储值";
            if(! StringUtils.isEmpty(frCardOrderStorage.getFlag())){
                flag = frCardOrderStorage.getFlag();
            }
            frCardOrderDatail.setFlag(flag);
            frCardOrderDatail.setCardType(cardType);
        }
        if(frCardOrderDatail != null){
            frCardOrderDatailList.add(frCardOrderDatail);
        }
        return frCardOrderDatail;
    }

    /**
     * 初始化资金交易明细
     * @return
     */
    public FrCardOrderPriceDatail getOrderPriceDatailInfo(FrCardOrderStorage frCardOrderStorage,boolean orderStatus, Integer cardType)throws YJException {
        // 资金交易明细  iFrCardOrderPriceDatailService  此表的付款记录主要是通过现金，获取其他渠道，权益、储值金额请根据订单交易明细
        FrCardOrderPriceDatail frCardOrderPriceDatail = new FrCardOrderPriceDatail();
        frCardOrderPriceDatail.setCardId(frCardOrderStorage.getCardId());
        frCardOrderPriceDatail.setClientId(frCardOrderStorage.getClientId());
        frCardOrderPriceDatail.setCustomerCode(frCardOrderStorage.getCustomerCode());
        frCardOrderPriceDatail.setUsing(true);
        frCardOrderPriceDatail.setOrderId(frCardOrderStorage.getId());
        frCardOrderPriceDatail.setShopId(frCardOrderStorage.getShopId());
        frCardOrderPriceDatail.setId(UUIDUtils.generateGUID());
        frCardOrderPriceDatail.setOrderStatus(orderStatus);
        //资金交易，请注意是根据实际支付金额
        Double orderPrice = iFrCardOrderDatailService.getOrderPriceByOrderStatus(orderStatus,frCardOrderStorage.getTotalPrice());
        frCardOrderPriceDatail.setOrderPrice(orderPrice);
        frCardOrderPriceDatail.setCardType(cardType);
        frCardOrderPriceDatail.setOrderType(CommonUtils.PAY_MODE_ORDER_TYPE_5);
        frCardOrderPriceDatail.setPersonnelId(frCardOrderStorage.getPersonnelId());
        return  frCardOrderPriceDatail;
    }

    /**
     * 获取剩余储值金额
     * @param frCardOrderStorage
     * @return
     * @throws YJException
     */
    public Double getOrderPrice(FrCardOrderStorage frCardOrderStorage)throws YJException{
        FrCardOrderDatail frCardOrderDatail = new FrCardOrderDatail();
        frCardOrderDatail.setCardId(frCardOrderStorage.getCardId());
        frCardOrderDatail.setClientId(frCardOrderStorage.getClientId());
        frCardOrderDatail.setCustomerCode(frCardOrderStorage.getCustomerCode());
        frCardOrderDatail.setType(CommonUtils.ORDER_DATAIL_TYPE_2);
        //统计剩余的储值金额
        Double orderPrice = iFrCardOrderDatailService.querySumOrderPrice(frCardOrderDatail,false);
        if(orderPrice == null){
            orderPrice = 0.0;
        }
        return  orderPrice;
    }

    /**
     * 冲销、退款之前，先确认数据是否存在
     * @param frCardOrderStorage
     * @return
     * @throws YJException
     */
    @Override
    public FrCardOrderStorage getOldOrderStorage(FrCardOrderStorage frCardOrderStorage) throws YJException {
        //查询之前的充值记录是否有效
        FrCardOrderStorage frCardOrderStorage1 = new FrCardOrderStorage();
        frCardOrderStorage1.setId(frCardOrderStorage.getId());
        frCardOrderStorage1.setCardId(frCardOrderStorage.getCardId());
        frCardOrderStorage1.setClientId(frCardOrderStorage.getClientId());
        frCardOrderStorage1.setCustomerCode(frCardOrderStorage.getCustomerCode());
        frCardOrderStorage1 = baseMapper.selectOne(frCardOrderStorage1);
        return frCardOrderStorage1;
    }

    /**
     * 添加转让数据
     * @param frCardOrderStorage
     * @return
     * @throws YJException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult toTransfersStorage(FrCardOrderStorage frCardOrderStorage) throws YJException {
        if(frCardOrderStorage == null){
            return JsonResult.failMessage("数据获取错误");
        }
        if (StringUtils.isEmpty(frCardOrderStorage.getId()) || StringUtils.isEmpty(frCardOrderStorage.getCustomerCode())
                || StringUtils.isEmpty(frCardOrderStorage.getCardId()) || StringUtils.isEmpty(frCardOrderStorage.getClientId())
                || StringUtils.isEmpty(frCardOrderStorage.getGiveName()) || StringUtils.isEmpty(frCardOrderStorage.getGivePhone())
                || StringUtils.isEmpty(frCardOrderStorage.getGiveUserId()) || StringUtils.isEmpty(frCardOrderStorage.getGiveCardId())) {
            return JsonResult.failMessage("请求参数有误，请重新核对");
        }
        // 获取客户转让的数据是否有效数据
        FrCardOrderStorage frCardOrderStorage1 = this.getOldOrderStorage(frCardOrderStorage);
        if(frCardOrderStorage1 == null){
            return JsonResult.failMessage("查无此储值数据，请重新核对");
        }
        if(frCardOrderStorage1.getAuditStatus() == null || frCardOrderStorage1.getAuditStatus() == null ){
            return  JsonResult.failMessage("数据审核状态设置有误");
        }
        if(CommonUtils.ORDER_TYPE_1 != frCardOrderStorage1.getStorageStatus()){
            return JsonResult.failMessage("只有储值状态才能操作转让");
        }
        FrCard frCard1 = iFrCardService.selectOne(new EntityWrapper<FrCard>().where("id={0}",frCardOrderStorage.getCardId()).and("is_using={0}",true)
                .and("client_id={0}",frCardOrderStorage.getClientId()).and("CustomerCode={0}",frCardOrderStorage.getCustomerCode()));
        if(frCard1 == null){
            return  JsonResult.failMessage("未获取到对应的会员卡信息");
        }
        // 获取转让客户信息,是否现有客户，该客户是否有会员卡
        FrCard frCard = iFrCardService.selectOne(new EntityWrapper<FrCard>().where("id={0}",frCardOrderStorage.getGiveCardId()).and("is_using={0}",true)
                .and("client_id={0}",frCardOrderStorage.getGiveUserId()).and("CustomerCode={0}",frCardOrderStorage.getCustomerCode()));
        if(frCard == null){
            return JsonResult.failMessage("查无被转让人的会员卡信息,请重新核对");
        }
        //统计剩余的储值金额
        Double orderPrice = this.getOrderPrice(frCardOrderStorage);
        if(frCardOrderStorage1.getSurplusPrice() > orderPrice){
            //实际付款金额
            return  JsonResult.failMessage("剩余储值金额不足");
        }
        List<FrCardOrderPayMode> list = iFrCardOrderPayModeService.selectList(new EntityWrapper<FrCardOrderPayMode>().where("order_id={0}",frCardOrderStorage1.getId())
                .and("order_type={0}",CommonUtils.PAY_MODE_ORDER_TYPE_5));

       //准备插入操作
//      1、先初始化需更新的数据
        //默认是未审核转让
        Integer storageStatus = CommonUtils.ORDER_TYPE_4;
        if(CommonUtils.ORDER_STATUS_1 == frCardOrderStorage1.getStatus()){
            storageStatus = CommonUtils.ORDER_TYPE_7;
            if(CommonUtils.AUDIT_ORDER_STATUS_1 == frCardOrderStorage1.getAuditStatus()){
                storageStatus = CommonUtils.ORDER_TYPE_8;
            }
        }
        frCardOrderStorage.setStorageStatus(storageStatus);
        frCardOrderStorage.setStatus(CommonUtils.ORDER_STATUS_0);
        frCardOrderStorage.setAuditStatus(CommonUtils.AUDIT_ORDER_STATUS_0);
        //修改转让人的信息
        baseMapper.update(frCardOrderStorage,new EntityWrapper<FrCardOrderStorage>().where("id={0}",frCardOrderStorage1.getId())
        .and("CustomerCode={0}",frCardOrderStorage1.getCustomerCode()).and("is_using={0}",1)
                .and("client_id={0}",frCardOrderStorage1.getClientId()).and("card_id={0}",frCardOrderStorage1.getCardId()));
        //  1.指定客户减去金额 ============================================================================
        FrCardOrderDatail  frCardOrderDatail = this.getOrderDatailInfo(frCardOrderStorage1,false,frCard1.getType(),new ArrayList<>());
        // 转让人资金交易明细 减去金额
        FrCardOrderPriceDatail frCardOrderPriceDatail = this.getOrderPriceDatailInfo(frCardOrderStorage1,true,frCard1.getType());
        frCardOrderDatail.setOrderPriceId(frCardOrderPriceDatail.getId());
        iFrCardOrderDatailService.queryOrderPriceAmt(frCardOrderDatail,false);
        iFrCardOrderPriceDatailService.insert(frCardOrderPriceDatail);
        Integer status = frCardOrderStorage1.getStatus();
        Integer auditStatus = frCardOrderStorage1.getAuditStatus();
        // 2、设置被转让人的储值信息；
        FrCardOrderStorage giveUserStorage = frCardOrderStorage1;
        giveUserStorage.setGiveName("");
        giveUserStorage.setGivePhone("");
        giveUserStorage.setCardId(frCard.getId());
        giveUserStorage.setClientId(frCard.getClientId());
        //生成订单编号
        String orderNo = frCardOrderInfoService.getOrderNo();
        giveUserStorage.setOrderNo(orderNo);
        giveUserStorage.setStatus(status);
        giveUserStorage.setAuditStatus(auditStatus);
        giveUserStorage.setStorageStatus(CommonUtils.ORDER_TYPE_1);
        giveUserStorage.setGiveStorageId(frCardOrderStorage1.getId());
        giveUserStorage.setShopId(frCardOrderStorage.getShopId());
        giveUserStorage.setCancelNote("");
        giveUserStorage.setRefundNote("");
        giveUserStorage.setRefundUserId("");
        giveUserStorage.setId(UUIDUtils.generateGUID());
        //插入被转让人的数据
        baseMapper.insert(giveUserStorage);
        // 2.指定客户加上金额
        FrCardOrderDatail  frCardOrderDatail1 = this.getOrderDatailInfo(giveUserStorage,true,frCard.getType(),new ArrayList<>());
        FrCardOrderPriceDatail frCardOrderPriceDatail1 = this.getOrderPriceDatailInfo(giveUserStorage,false,frCard.getType());
        frCardOrderDatail1.setOrderPriceId(frCardOrderPriceDatail1.getId());
        iFrCardOrderDatailService.queryOrderPriceAmt(frCardOrderDatail1,false);
        iFrCardOrderPriceDatailService.insert(frCardOrderPriceDatail1);
        if(list != null && list.size()>0){
            for(FrCardOrderPayMode frCardOrderPayMode:list){
                frCardOrderPayMode.setId(UUIDUtils.generateGUID());
                frCardOrderPayMode.setOrderId(giveUserStorage.getId());
                iFrCardOrderPayModeService.insert(frCardOrderPayMode);
            }
        }
        return JsonResult.success(true);
    }
}
