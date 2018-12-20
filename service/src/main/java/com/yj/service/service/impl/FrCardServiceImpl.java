package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.*;
import com.yj.dal.dao.*;
import com.yj.dal.model.*;
import com.yj.dal.dao.FrCardMapper;
import com.yj.dal.param.NumParam;
import com.yj.service.service.*;
import com.yj.service.base.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 会员卡表 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-04
 */
@Service
public class FrCardServiceImpl extends BaseServiceImpl<FrCardMapper, FrCard> implements IFrCardService  {

    private final Logger log = LoggerFactory.getLogger(FrCardServiceImpl.class);

    @Resource
    private FrShopCardTypeRelateMapper shopCardTypeRelateMapper;
    @Resource
    private FrCardTypeMapper frCardTypeMapper; //卡类型关联表
//    @Resource
//    private PersonnelInfoMapper personnelInfoMapper;
    @Resource
    private IFrAgreementService iFrAgreementService;
    @Resource
    private  IFrCardNumService iFrCardNumService;
    @Resource
    private IFrCardOrderInfoService iFrCardOrderInfoService;
    @Resource
    private IFrCardOrderPayModeService iFrCardOrderPayModeService;
    @Resource
    private FrCardAgreementMapper frCardAgreementMapper;
    @Resource
    private IFrCardOriginalSetService iFrCardOriginalSetService;
    @Resource
    private IFrCardOrderSplitSetService iFrCardOrderSplitSetService;
    @Resource
    private IFrCardOrderSplitSetDdService iFrCardOrderSplitSetDdService;
    @Resource
    private IFrCardOrderAllotSetService iFrCardOrderAllotSetService;
    @Resource
    private IFrCardOrderDatailService iFrCardOrderDatailService;
    @Resource
    private IFrCardOrderPriceDatailService iFrCardOrderPriceDatailService;
    @Resource
    private IFrClientService iFrClientService;
    @Resource
    private IFrLevelService iFrLevelService;
    @Resource
    private IFrClientPicService iFrClientPicService;
    @Resource
    private  IFrShopCardConsumeService iFrShopCardConsumeService;




    //@Resource
    //private FrStoreCardTypeRelateMapper storeCardTypeRelateMapper;//门店-卡类型关联表
//    @Resource
//    private FrStoreMapper storeMapper;//门店
    @Resource
    private  ShopMapper  shopMapper;//门店


    //@Resource
    //private  FrStoreCategoryRelateMapper  storeCategoryRelateMapper;//门店-门店场馆关联表


    @Override
    public PageUtils queryPage(Map<String, Object> params) throws YJException {
        String code = String.valueOf(params.get("code"));
        if (StringUtils.isEmpty(code)) {
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        Page page = this.selectPage(
                new Query<FrCard>(params).getPage(),
                new EntityWrapper<FrCard>()
                        .where("is_using = 0 and CustomerCode = {0}",code)
                        .orderBy("create_time desc")
                        .setSqlSelect("id,card_no cardNo,create_time createTime")
        );
        return new PageUtils(page);
    }


    @Override
    public Boolean insertList(NumParam param) throws YJException {
        //Long begin = Long.valueOf(param.getStartNo());
        //Long end = Long.valueOf(param.getEndNo());
        //if (begin > end) {
        //    throw new YJException(YJExceptionEnum.NUMBER_ERROR);
        //}
        ////检查交集
        //List<NumberGroupDTO> frCards = baseMapper.selectListByGroup();
        //int i = 0;
        //NumberGroupDTO entity = new NumberGroupDTO();
        //for (i = 0; i < frCards.size(); i++) {
        //    entity = frCards.get(i);
        //    Long startNo = Long.valueOf(entity.getStartNo());
        //    Long endNo = Long.valueOf(entity.getEndNo());
        //    Boolean cross = CardUtils.isCross(begin, end, startNo, endNo);
        //    if (cross) {
        //        throw new YJException(YJExceptionEnum.NUMBER_EXISTED);
        //    }
        //}
        ////生成编号集合
        //List list = CardUtils.genByBenginAndNum(begin, param.getBatchNum(), param.getNotInNum(), param.getPosition());
        //Set set = new HashSet<>(list);
        //log.info("list.size = {}", list.size());
        //log.info("set.size = {}", list.size());
        ////组装实体类
        //FrCard frCard = new FrCard();
        //frCard.setStartNo(String.valueOf(list.get(0)));
        //frCard.setEndNo(String.valueOf(list.get(list.size() - 1)));
        //frCard.setBatchNum(list.size());
        //log.info("数组的长度为 = {}", param.getPosition().length);
        //if (param.getPosition().length > 0) {
        //    log.info("有去数字{}", param.getNotInNum());
        //    frCard.setNotinNum(param.getNotInNum());
        //}
        ////批量插入
        //Integer count = 0;
        //for (i = 0; i < list.size(); i++) {
        //    frCard.setCardNo(String.valueOf(list.get(i)));
        //    frCard.setId(UUIDUtils.generateGUID());
        //    Integer insert = baseMapper.insert(frCard);
        //    count = count + insert;
        //}
        //log.info("成功执行了{}条", count);
        //if (count > 0) {
        //    return true;
        //}
        return false;
    }

    @Override
    public Boolean updateInvalid(String cardNo, Integer type) throws YJException {
        //查询是否有这个会员号
        FrCard frCard = new FrCard();
        frCard.setCardNo(cardNo);
        FrCard one = baseMapper.selectOne(frCard);
        if (one == null) {
            throw new YJException(YJExceptionEnum.VIPCARDNO_NOT_EXISTED);
        }
        if (one.getType() == type) {
            throw new YJException(YJExceptionEnum.VIPCARDNO_ABOLISHED);
        }
        //更新会员号
        one.setType(type);
        Integer integer = baseMapper.updateById(one);
        if (integer > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateInvalidById(String id, Integer type) throws YJException {
        //查询是否有这个会员号
        FrCard one = baseMapper.selectById(id);
        if (one == null) {
            throw new YJException(YJExceptionEnum.VIPCARDNO_NOT_EXISTED);
        }
        //更新会员号
        one.setType(type);
        Integer integer = baseMapper.updateById(one);
        if (integer > 0) {
            return true;
        }
        return false;
    }


    //加载会员卡权益
    @Override
    public JsonResult getMemberCard(String id) {
        List<Shop> storeList = new ArrayList<>();//门店
        FrCardType cardType = frCardTypeMapper.selectById(id);//搜索会员卡系列
        System.out.println("cardType=="+cardType);

        ////搜索门店-卡类型关联表
        EntityWrapper ew = new EntityWrapper<>();
        ew.where("card_type_id = {0}", cardType.getId());
        List<FrShopCardTypeRelate> frStoreCategories = shopCardTypeRelateMapper.selectList(ew);//搜索系列门店
        // 搜索门店
        System.out.println("frStoreCategories=="+frStoreCategories);

        for (int i = 0; i < frStoreCategories.size(); i++) {
            Shop shop=new Shop();
            shop=shopMapper.selectById(frStoreCategories.get(i).getShopId());
            storeList.add(shop);//加入门店
        }
        Map map = new HashMap();
        map.put("cardType", cardType);//卡类型
        map.put("store", storeList);//门店
        return JsonResult.success(map);
    }

    //卡类型
    @Override
    public JsonResult addMemberCardType(FrCardType cardType) {
        Integer row = frCardTypeMapper.updateById(cardType);
        if (row > 0) {
            JsonResult.failMessage("数据保存失败");
        }
        return JsonResult.success();
    }
    
    /**
     * @Description: 增加作废会员号
     * @Author: 欧俊俊
     * @Date: 2018-09-28 16:53
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addInvalidCardNo(FrCard frCard, String code) throws YJException {
        //会员卡号是否为空
        if (StringUtils.isEmpty(frCard.getCardNo())) {
            throw new YJException(YJExceptionEnum.VIPCARDNO_ISNOTBLANK);
        }
        //设置客户代码
        frCard.setCustomerCode(code);
        FrCard card = baseMapper.selectOne(frCard);
        //判断会员卡号是否存在
        if (card == null) {
            throw new YJException(YJExceptionEnum.VIPCARDNO_NOT_EXISTED);
        }
        //判断会员卡是否已经作废
        if (!card.getUsing()) {
            throw new YJException(YJExceptionEnum.VIPCARDNO_ABOLISHED);
        }
        //作废会员卡
        card.setUsing(false);
        return SqlHelper.retBool(baseMapper.updateById(card));
    }

    @Override
    public Map<String, Object> queryCardType(String cardId) {
        Map<String,Object> map = baseMapper.queryCardType(cardId);
        return map;
    }

    /**
     * 获取指定客户的会员卡信息（新）
     * @param pageUtil
     * @return
     * @throws YJException
     */
    @Override
    public JsonResult queryUserCardList(PageUtil<FrCard> pageUtil) throws YJException {
        Map map = this.getPaginationPage(pageUtil);
        if(pageUtil == null){
            throw  new YJException(YJExceptionEnum.OBJECT_NOT_FOUND);
        }
        if(StringUtils.isEmpty(pageUtil.getCode()) || StringUtils.isEmpty(pageUtil.getClientId())){
            throw  new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        FrCard frCard = new FrCard();
        frCard.setCustomerCode(pageUtil.getCode());
        frCard.setClientId(pageUtil.getClientId());
        frCard.setUsing(true);
        if(pageUtil.getType() != null ){
            frCard.setStatus(pageUtil.getType());
        }
        List<FrCard> list = new ArrayList<>();
        Map<String , Object> map1 = new HashMap<>();
        Integer beOverdue = getNumCard(frCard.getClientId(),frCard.getCustomerCode(),31,true);
        if(beOverdue == null){
            beOverdue = 0;
        }
        map1.put("beOverdue",beOverdue);
        if(map != null ){
            Page page = new Query<FrClient>(map).getPage();
            //查询该会员卡列表总数据
            list = baseMapper.queryUserCardList(page,frCard);
            page.setRecords(list);
            map1.put("list",new PageUtils(page));
           return JsonResult.success(map1);
        }
        list = baseMapper.queryUserCardList(frCard);
        map1.put("list",list);
        return JsonResult.success(list);
    }

    /**
     *  根据会员卡信息获取单条数据
     * @param code
     * @param cardId
     * @return
     * @throws YJException
     */
    @Override
    public JsonResult queryUserCardOne(String code, String cardId) throws YJException {
        if(StringUtils.isEmpty(code) || StringUtils.isEmpty(cardId)){
            throw  new YJException(YJExceptionEnum.REQUEST_NULL);
        }
        FrCard frCard = new FrCard();
        frCard.setCustomerCode(code);
        frCard.setId(cardId);
        List<FrCard> list = baseMapper.queryUserCardList(frCard);
        if(list != null && list.size() > 0){
            return JsonResult.success(list.get(0));
        }
        return JsonResult.failMessage("会员卡订单信息不存在");
    }

    /**
     * 获取客户会员卡信息
     * @param frCard
     * @return
     * @throws YJException
     */
    @Override
    public JsonResult queryByFrCardList(FrCard frCard) throws YJException {
        if(frCard == null){
            throw new YJException(YJExceptionEnum.OBJECT_NOT_FOUND);
        }
        if(!StringUtils.isEmpty(frCard.getClientId()) && !StringUtils.isEmpty(frCard.getCustomerCode())){
            List<FrCard>  list = baseMapper.queryByFrCardList(frCard);
            return JsonResult.success(list);
        }
        return JsonResult.failMessage("客户ID或者客户代码错误");
    }

    /**
     * 会员卡设置保存失效时间

     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult saveInvalidTime(FrCard frCard) {
        Integer b = baseMapper.update(
                frCard,new EntityWrapper<FrCard>().where("id = {0}",frCard.getId())
                        .and(" CustomerCode = {0}",frCard.getCustomerCode())
        );
        if(b > 0){
            return JsonResult.success();
        }
        return JsonResult.failMessage("保存失败！");
    }

    /**
     * 判断是否需要分页
     * @param pageUtil
     * @return
     */
    public Map getPaginationPage(PageUtil pageUtil){
        Map map = null;
        String limit,page;
        limit = pageUtil.getRows();
        page = pageUtil.getPage();
        if( !StringUtils.isEmpty(limit) &&  !StringUtils.isEmpty(page)){
            if(!"-1".equals(limit) && !"-1".equals(page)){
                map = new HashMap();
                map.put("limit",limit);//每页多少条
                map.put("page",page);//当前页
            }
        }
        return  map;
    }

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
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult toAddFrCard(FrCardAgreement frCardAgreement, FrCard frCard, FrCardOrderInfo frCardOrderInfo,
                                  List<FrCardOrderPayMode> frCardOrderPayModes, List<FrCardOrderAllotSet> frCardOrderAllotSetList,
                                  Map<String, String> mapS, Map<String, Integer> mapI) throws YJException {
        String orderSplitId = "",messFlag = "";
        Integer infoType = null;
        //续卡  --开卡方式，是否更换卡
        Integer cardOpening = null,replacementCard = null;
        //开卡时间
        String bindTime = "";
        if(mapS != null){
            //分期ID
            orderSplitId = mapS.get("orderSplitId");
            messFlag = mapS.get("messFlag");
            bindTime = mapS.get("bindTime");
        }
        if(mapI != null){
            //操作的订单类型
            infoType = mapI.get("infoType");
            mapS.put("oldCardId",frCard.getId());
            //续卡初始化参数
            if(CommonUtils.CARD_SUPPLY_RECORD_TYPE_2 == infoType){
                cardOpening = mapI.get("cardOpening");
                replacementCard = mapI.get("replacementCard");
                //续卡状态，先保存旧卡号ID
//                mapS.put("oldCardId",frCard.getId());
            }
        }
        //如果协议规则，会员卡，会员卡订单未获取，返回
        if(frCardAgreement == null || frCard == null || frCardOrderInfo == null){
            throw  new YJException(YJExceptionEnum.REQUEST_NULL);
        }
        if(StringUtils.isEmpty(frCardAgreement.getAgreementNo())){
            return JsonResult.failMessage("协议编号不能为空，请填写协议编号");
        }
        //会员卡号，客户ID，会员卡、会员卡种信息，会员外部卡号，操作店铺ID，客户代码 需提供
        if(StringUtils.isEmpty(frCard.getCardNo()) || StringUtils.isEmpty(frCard.getClientId())
                || StringUtils.isEmpty(frCard.getCardTypeId())|| StringUtils.isEmpty(frCard.getExternalNo())
                || StringUtils.isEmpty(frCard.getShopId())||StringUtils.isEmpty(frCard.getCustomerCode())){
            return JsonResult.failMessage("会员卡信息有误，请重新核对购买的会员卡信息");
        }
        //支付方式、售价、实际付款、操作人员ID
        if(frCardOrderInfo.getPayType()==null || frCardOrderInfo.getTotalPrice() == null
                || frCardOrderInfo.getNeedPrice() == null || StringUtils.isEmpty(frCardOrderInfo.getPersonnelId())) {
            return JsonResult.failMessage("订单信息有误，请重新核对下支付信息");
        }
        frCard.setId(UUIDUtils.generateGUID());
        //获取卡种类型ID
        String cardTypeId = frCard.getCardTypeId();
        if(StringUtils.isEmpty(cardTypeId)){
            cardTypeId = frCardOrderInfo.getCardTypeId();
            if(StringUtils.isEmpty(cardTypeId)){
                return  JsonResult.failMessage("未获取相应的卡种类型");
            }
            frCard.setCardTypeId(cardTypeId);
        }
        //初始化订单信息
        frCardOrderInfo.setId(UUIDUtils.generateGUID());
        frCardOrderInfo.setCardId(frCard.getId());
        frCardOrderInfo.setCardTypeId(cardTypeId);
        frCardOrderInfo.setType(infoType);
        if(StringUtils.isEmpty(frCardOrderInfo.getClientId())){
            frCardOrderInfo.setClientId(frCard.getClientId());
        }
        //店铺业绩需调整
        if(StringUtils.isEmpty(frCardOrderInfo.getShopId())){
            frCardOrderInfo.setShopId(frCard.getShopId());
        }
        //订单状态默认已付款
        frCardOrderInfo.setOrderState(CommonUtils.CARD_ORDRE_STATE_2);
        frCardOrderInfo.setUsing(true);
        frCardOrderInfo.setCustomerCode(frCard.getCustomerCode());
        //审核状态默认未审核
        frCardOrderInfo.setStatus(CommonUtils.ORDER_STATUS_0);
        frCardOrderInfo.setAuditStatus(CommonUtils.AUDIT_ORDER_STATUS_0);
        if(frCardOrderInfo.getConsumeType() == null){
            //如果没有默认会员消费方式
            frCardOrderInfo.setConsumeType(CommonUtils.CONSUME_TYPE_1);
        }
        Integer orderState = CommonUtils.CARD_ORDRE_STATE_2;
        Double needPrice = frCardOrderInfo.getNeedPrice();
        Double totalPrice = frCardOrderInfo.getTotalPrice();
        //获取总支付金额
        Double allPrice  = iFrCardOrderPayModeService.getAllPrice(frCardOrderPayModes,frCardOrderInfo.getId());
        //未付金额
        Double noPrice = frCardOrderInfo.getNoPrice();
        //找零
        Double reChange = frCardOrderInfo.getRetChange();
        if(noPrice != null && noPrice > 0.0 ){
            if(reChange != null && reChange > 0.0){
                return  JsonResult.failMessage("支付错误，不应同时存在未付金额和找零");
            }
            if(noPrice > needPrice){
                return  JsonResult.failMessage("支付错误，未付金额不应大于需付金额");
            }
        }
        if(reChange != null && reChange > 0.0){
            if( allPrice < needPrice ){
                return  JsonResult.failMessage("支付错误，总支付金额不应小于应付金额");
            }
            if(reChange > (allPrice-needPrice)){
                return JsonResult.failMessage("支付错误,支付金额减去需付金额不等于找零金额");
            }
        }
        Integer payType =  frCardOrderInfo.getPayType();
//      1、全款；2、定金(可能有补余记录)；3、赠送；4、置换；5、分期付款(可能有补余记录)
        boolean isSplitSetFlag = false;
        if(CommonUtils.ORDER_INFO_PAY_TYPE_1 == payType || CommonUtils.ORDER_INFO_PAY_TYPE_5  == payType){
            if(CommonUtils.ORDER_INFO_PAY_TYPE_1 == payType ){
                orderState = CommonUtils.CARD_ORDRE_STATE_3;
            }
            if(CommonUtils.ORDER_INFO_PAY_TYPE_5  == payType){
                if( StringUtils.isEmpty(orderSplitId)){
                    return JsonResult.failMessage("请先选择分期付款方式");
                }
                isSplitSetFlag = true;
            }
//          1、全款款支付
            if(allPrice < needPrice){
                return  JsonResult.failMessage("支付金额不应少于应付金额");
            }
        }
        if(CommonUtils.ORDER_INFO_PAY_TYPE_2 == payType ){
//          2、定金(可能有补余记录)
            if(StringUtils.isEmpty(frCardOrderInfo.getDepositTime())){
                return JsonResult.failMessage("定金支付，需选择补余期限");
            }
            if( allPrice <= 0.0){
                return JsonResult.failMessage("请输入定金，支付金额不能为空");
            }
            if(needPrice != null && needPrice.toString().equals(allPrice.toString())){
                orderState = CommonUtils.CARD_ORDRE_STATE_3;
            }
        }
        if(CommonUtils.ORDER_INFO_PAY_TYPE_3 == payType || CommonUtils.ORDER_INFO_PAY_TYPE_4 ==  payType){
            orderState = CommonUtils.CARD_ORDRE_STATE_3;
          //授权人ID不能为空
           if(StringUtils.isEmpty(frCardOrderInfo.getAuthorizingUserId())){
                return JsonResult.failMessage("授权人ID不能为空");
            }
           if(CommonUtils.ORDER_INFO_PAY_TYPE_4 == payType){
                //置换
                if(StringUtils.isEmpty(frCardOrderInfo.getDisplaceCompany()) || StringUtils.isEmpty(frCardOrderInfo.getDisplaceWay())){
                    return JsonResult.failMessage("置换单位，置换方式需填写");
                }
           }
           // 若赠送或者置换的支付金额未填写的，默认现金支付0
           if(frCardOrderPayModes.size()<= 0){
                FrCardOrderPayMode frCardOrderPayMode = new FrCardOrderPayMode();
                frCardOrderPayMode.setOrderId(frCardOrderInfo.getId());
                frCardOrderPayMode.setPayPrice(0.0);
                frCardOrderPayMode.setId(UUIDUtils.generateGUID());
                frCardOrderPayMode.setOrderType(1);
                frCardOrderPayMode.setPayMode(4);
           }
        }
        frCardOrderInfo.setOrderState(orderState);
        //1表示已启用业绩分配
        if(frCardOrderInfo.getAllotSetType() == 1){
            if(frCardOrderAllotSetList == null || frCardOrderAllotSetList.size() < 0){
                return JsonResult.failMessage("业绩分配数据有误，请重新设置");
            }
        }
        if( StringUtils.isEmpty(frCardOrderInfo.getOrderNo())){
            //若无订单编号，重新生成订单编号
            frCardOrderInfo.setOrderNo(iFrCardOrderInfoService.getOrderNo());;
        }
        //如果会员卡规则ID为空，检索会员卡号
        if(StringUtils.isEmpty(frCard.getCardNumId())){
            this.checkCardNum(frCard);
        }
        //获取卡种信息，初始化卡前设置
        FrCardType frCardType = new FrCardType();
        frCardType.setId(cardTypeId);
        frCardType.setCustomerCode(frCard.getCustomerCode());
        frCardType = frCardTypeMapper.selectOne(frCardType);
        if(frCardType == null){
            return  JsonResult.failMessage("卡种不存在，请重新操作");
        }
        if(StringUtils.isEmpty(frCardType.getServiceLife())){
            return  JsonResult.failMessage("卡种使用期限未设置，请选择其他卡种");
        }

        FrCardOriginalSet  frCardOriginalSet = new FrCardOriginalSet();
        frCardOriginalSet.setCardId(frCard.getId());
        //初始化卡前设置
        iFrCardOriginalSetService.initFrCardOrgiginalSet(frCardOriginalSet,frCardType);
        frCardOrderInfo.setCardSetId(frCardOriginalSet.getId());

        if(frCardOrderInfo.getBuyRightsNum() == null){
            frCardOrderInfo.setBuyRightsNum(frCardType.getTotalNum());
        }
        if(frCardOrderInfo.getBuyRightsNum() > frCardType.getTotalNum()){
            return  JsonResult.failMessage("购买权益与权益不符，请刷新后重新核对");
        }
        //如果没有赠送权益，设置为0
        if(frCardOrderInfo.getGiveRightsNum() == null){
            frCardOrderInfo.setGiveRightsNum(0);
        }
        //总权益 = 购买权益+赠送权益
        Integer allNum = frCardOrderInfo.getBuyRightsNum()+frCardOrderInfo.getGiveRightsNum();
        frCard.setHaveRightsNum(0.0+allNum);
        frCard.setOriginalId(frCardOriginalSet.getId());
        //会员卡状态需调整
        frCard.setStatus(CommonUtils.CARD_STATUS_4);
        frCard.setType(frCardType.getType());
        frCard.setUsing(true);
        //    开卡时间未设置，不设置失效时间
        if(!StringUtils.isEmpty(frCard.getBindTime())){
            frCard.setStatus(CommonUtils.CARD_STATUS_0);
            //若开卡了设置失效时间 = 开卡时间+使用期限；
            String inv = frCardType.getServiceLife();
            String serviceLisfe = this.getInv(inv,frCard.getBindTime());
            if(!StringUtils.isEmpty(serviceLisfe)){
                frCard.setInvalidTime(serviceLisfe);
            }
        }
        //续卡的话，--直接延续重置开卡时间
        if(CommonUtils.CARD_SUPPLY_RECORD_TYPE_2 == infoType){
            if(StringUtils.isEmpty(bindTime)){
                frCard.setBindTime(bindTime);
            }
        }
        //如果协议编号ID为空就，检索协议编号
        if(StringUtils.isEmpty(frCardAgreement.getAgreementId())){
            this.checkCardAgreement(frCardAgreement,frCard.getCustomerCode());
        }
        frCardAgreement.setCustomerCode(frCard.getCustomerCode());
        frCardAgreement.setUsing(true);
        frCardAgreement.setCardId(frCard.getId());
        frCardAgreement.setId(UUIDUtils.generateGUID());

        FrCardOrderSplitSet frCardOrderSplitSet = null;
        List<FrCardOrderSplitSetDd> frCardOrderSplitSetDds = null;
        //订单状态为分期，初始化分期信息
        if(isSplitSetFlag){
            frCardOrderSplitSet = iFrCardOrderSplitSetService.initFrCardSplitSet(orderSplitId,frCard.getCustomerCode());
            if(frCardOrderSplitSet != null ){
                frCardOrderSplitSet.setOrderId(frCardOrderInfo.getId());
                frCardOrderSplitSetDds =  iFrCardOrderSplitSetDdService.initFrCardOrderSplitSetDd(orderSplitId,frCardOrderSplitSet.getId());
            }
        }
        Integer orderType = infoType;
        if(CommonUtils.CARD_ORDRE_INFO_TYPE_4 == orderType){
            orderType = CommonUtils.PAY_MODE_ORDER_TYPE_10;
        }
        //初始化交易明细
        FrCardOrderPriceDatail frCardOrderPriceDatail = this.getCardDatailAndPrice(frCardOrderInfo,false,frCard.getType(),orderType);
        mapS.put("priceDatail",frCardOrderPriceDatail.getId());
        // 获取剩余的权益信息
        FrCardOrderDatail frCardOrderDatail = new FrCardOrderDatail();
        frCardOrderDatail.setCardId(frCardOrderInfo.getCardId());
        frCardOrderDatail.setClientId(frCardOrderInfo.getClientId());
        frCardOrderDatail.setCustomerCode(frCardOrderInfo.getCustomerCode());
        frCardOrderDatail.setType(CommonUtils.ORDER_DATAIL_TYPE_1);
        //初始化部分订单明细
        frCardOrderDatail =  iFrCardOrderDatailService.getOrderDatailInfo(frCardOrderPriceDatail);
        frCardOrderDatail.setFlag(messFlag);
        frCardOrderDatail.setType(CommonUtils.ORDER_DATAIL_TYPE_1);
        frCardOrderDatail.setOrderStatus(true);
        Integer buyRights = frCardOrderInfo.getBuyRightsNum();
        Integer giveRights = frCardOrderInfo.getGiveRightsNum();
        if(buyRights == null){   buyRights = 0;   }
        if(giveRights == null){  giveRights = 0;   }
        Double allRightsNum = 0.0+(buyRights+giveRights);
        Double rightsNum = iFrCardOrderDatailService.getOrderPriceByOrderStatus(true, allRightsNum);
        frCardOrderDatail.setOrderRightsNum(rightsNum);

        //会员卡表
        baseMapper.insert(frCard);
        //订单表
        iFrCardOrderInfoService.insert(frCardOrderInfo);
        //会员卡前设置
        iFrCardOriginalSetService.insert(frCardOriginalSet);
        //规则表
        frCardAgreementMapper.insert(frCardAgreement);
        if(frCardOrderPayModes.size()>0){
            for(FrCardOrderPayMode  frCardOrderPayMode:frCardOrderPayModes ){
                iFrCardOrderPayModeService.insert(frCardOrderPayMode);
            }
        }
        if(isSplitSetFlag){
            if(frCardOrderSplitSet != null && frCardOrderSplitSetDds != null && frCardOrderSplitSetDds.size()>0 ){
                iFrCardOrderSplitSetService.insert(frCardOrderSplitSet);
                for(FrCardOrderSplitSetDd frCardOrderSplitSetDd : frCardOrderSplitSetDds){
                    iFrCardOrderSplitSetDdService.insert(frCardOrderSplitSetDd);
                }
            }
        }
        if(frCardOrderInfo.getAllotSetType() == 1 && frCardOrderAllotSetList != null && frCardOrderAllotSetList.size()>0){
            for(FrCardOrderAllotSet frCardOrderAllotSet:frCardOrderAllotSetList){
                frCardOrderAllotSet.setOrderId(frCardOrderInfo.getId());
                frCardOrderAllotSet.setCustomerCode(frCard.getCustomerCode());
                frCardOrderAllotSet.setId(UUIDUtils.generateGUID());
                iFrCardOrderAllotSetService.insert(frCardOrderAllotSet);
            }
        }
        iFrCardOrderPriceDatailService.insert(frCardOrderPriceDatail);
        //要更新前再设置余额
        FrCardOrderDatail frCardOrderDatail1 = new FrCardOrderDatail();
        frCardOrderDatail1.setCustomerCode(frCardOrderDatail.getCustomerCode());
        frCardOrderDatail1.setClientId(frCardOrderDatail.getClientId());
        frCardOrderDatail1.setCardId(frCardOrderDatail.getCardId());
        frCardOrderDatail1.setCardType(frCardOrderDatail.getCardType());
        frCardOrderDatail1.setType(frCardOrderDatail.getType());
        Double amt = iFrCardOrderDatailService.querySumOrderPrice(frCardOrderDatail1,false);
        frCardOrderDatail.setOrderAmt(rightsNum+amt);
        iFrCardOrderDatailService.insert(frCardOrderDatail);
        //设置会员卡的使用权益范围；
        iFrShopCardConsumeService.queryByCardTypeId(cardTypeId,frCard.getId(),frCard.getCustomerCode());
        return JsonResult.success(true);
    }


    /**
     * 检索协议号
     * @param frCardAgreement
     * @throws YJException
     */
    public void checkCardAgreement(FrCardAgreement frCardAgreement,String code)throws YJException{
        //如果协议编号ID为空就，检索协议编号
        if(StringUtils.isEmpty(frCardAgreement.getAgreementId())){
            String id = iFrAgreementService.checkCardAgreement(frCardAgreement.getAgreementNo(),code);
            if("".equals(id) || "1".equals(id)){
               throw new YJException(YJExceptionEnum.PROTOCOLNO_NOT_CONFORMITY);
            }
            frCardAgreement.setAgreementId(id);
        }
    }

    /**
     * 检索会员卡号
     * @param frCard
     * @throws YJException
     */
    public void checkCardNum(FrCard frCard)throws YJException{
        //如果会员卡规则ID为空，检索会员卡号
        if(StringUtils.isEmpty(frCard.getCardNumId())){
            String id = iFrCardNumService.checkCardNum(frCard.getCardNo(),frCard.getCustomerCode());
            if("".equals(id) || "1".equals(id)){
                throw new YJException(YJExceptionEnum.VIPCARDNO_NOT_CONFORMITY);
            }
            frCard.setCardNumId(id);
        }
    }

    /**
     * 根据会员卡获取卡前设置的停卡设置信息
     * @param frCard
     * @return
     * @throws YJException
     */
    @Override
    public  Map<String,Object> queryCollectionOriginalSet(FrCard frCard) throws YJException {
        if(frCard == null){
            throw  new YJException(YJExceptionEnum.OBJECT_NOT_FOUND);
        }
        if(StringUtils.isEmpty(frCard.getId()) || StringUtils.isEmpty(frCard.getCustomerCode())){
            throw  new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        Map<String,Object> map = baseMapper.queryCollectionOriginalSet(frCard);
        return map;
    }

    @Override
    public  List<FrCard> getCardInformation(String cid) throws YJException {
        //校验参数
        if ( cid== null) {
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取当前登录用户的id
        String customerCode = CookieUtils.getCookieValue(req, "code", true);
        FrCard frCard = new FrCard();
        frCard.setCustomerCode("003");
        frCard.setClientId(cid);
        frCard.setUsing(true);
        List<FrCard> frCards = baseMapper.queryUserCardList(frCard);
        return frCards;
    }



    /**
     * 初始化资金交易
     * @param frCardOrderInfo
     * @param orderStatus
     * @param cardType
     * @param orderType
     * @return
     * @throws YJException
     */
    public FrCardOrderPriceDatail getCardDatailAndPrice(FrCardOrderInfo frCardOrderInfo,boolean orderStatus,Integer cardType,Integer orderType)throws YJException{
        //初始化订单交易明细
        FrCardOrderPriceDatail  frCardOrderPriceDatail = new FrCardOrderPriceDatail();
        frCardOrderPriceDatail.setId(UUIDUtils.generateGUID());
        frCardOrderPriceDatail.setStatus(CommonUtils.ORDER_STATUS_0);
        frCardOrderPriceDatail.setAuditStatus(CommonUtils.AUDIT_ORDER_STATUS_0);
        frCardOrderPriceDatail.setCardId(frCardOrderInfo.getCardId());
        frCardOrderPriceDatail.setClientId(frCardOrderInfo.getClientId());
        frCardOrderPriceDatail.setPersonnelId(frCardOrderInfo.getPersonnelId());
        frCardOrderPriceDatail.setCustomerCode(frCardOrderInfo.getCustomerCode());
        frCardOrderPriceDatail.setOrderId(frCardOrderInfo.getId());
        frCardOrderPriceDatail.setShopId(frCardOrderInfo.getShopId());
//        支付状态（0：支出 1：收入）
        frCardOrderPriceDatail.setOrderStatus(orderStatus);
        Double orderPrice = iFrCardOrderPriceDatailService.getOrderPriceByOrderStatus(orderStatus,frCardOrderInfo.getNeedPrice());
        frCardOrderPriceDatail.setOrderPrice(orderPrice);
        frCardOrderPriceDatail.setCardType(cardType);
        frCardOrderPriceDatail.setOrderType(orderType);
        return frCardOrderPriceDatail;
    }


    /**
     * 统计指定会员下的指定时间内快过期的会员卡
     * @param clientId
     * @param CustomerCode
     * @return
     */
    public Integer getNumCard(String clientId,String CustomerCode,Integer num,boolean isFlag){
        //获取当前时间的前一个月
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String endTime = new StringBuffer(sdf.format(date)).append(" 23:59:59").toString();
        String startTime = DateUtil.getBeforeDay(endTime,num,isFlag);

        Map<String,Object> map1 = new HashMap<>();
        map1.put("endTime",endTime);
        map1.put("startTime",startTime);
        map1.put("clientId",clientId);
        map1.put("CustomerCode",CustomerCode);
        Integer numCard = baseMapper.queryNumCard(map1);
        return numCard;
    }

//===================================== 新添的以上未修改
    /**
     * 根据开卡时间设置失效时间
     * @param serviceLifeS
     * @param start
     * @return
     * @throws YJException
     */
    @Override
    public String getInv(String serviceLifeS,String start) throws YJException{
        String  serviceL = null;
        //若开卡了设置失效时间 = 开卡时间+使用期限；
        if(serviceLifeS.indexOf(",") != -1){
            String[] day = serviceLifeS.split(",");
            String num = day[0];
            String data = day[1];
            Date serviceLisfe = DateUtil.stringToDate(start,"yyyy-MM-dd HH:mm:ss");
            if(StringUtils.isEmpty(num) || StringUtils.isEmpty(data)){
                throw  new YJException(YJExceptionEnum.CARD_TYPE_TOTAL_EXISTED);
            }
            Integer n = Integer.valueOf(num);
            serviceLisfe = DateUtil.getServiceLifeDate(serviceLisfe,data,n);
            serviceL = DateUtil.dateToString(serviceLisfe);
        }
        return  serviceL;
    }

    /**
     * 新建现有客户
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
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult addSaveCustomer(FrClient frClient, FrCard frCard, FrCardOrderInfo frCardOrderInfo,
                                      List<FrCardOrderPayMode> frCardOrderPayModes, List<FrCardOrderAllotSet> frCardOrderAllotSetList,
                                      Map<String, String> mapS, Map<String, Integer> mapI) throws YJException {
        //如果会员信息未空，会员卡，会员卡订单未获取，返回
        if(frClient == null || frCard == null || frCardOrderInfo == null){
            throw  new YJException(YJExceptionEnum.REQUEST_NULL);
        }
        //会员卡号，客户ID，会员卡、会员卡种信息，会员外部卡号，操作店铺ID，客户代码 需提供
        if(StringUtils.isEmpty(frCard.getShopId())|| StringUtils.isEmpty(frCard.getCustomerCode())
                || StringUtils.isEmpty(frCard.getExternalNo()) || StringUtils.isEmpty(frCard.getCardNo()) || StringUtils.isEmpty(frClient.getClientName())
                || StringUtils.isEmpty(frClient.getMobile()) || StringUtils.isEmpty(frClient.getConsultantId()) || frClient.getSex() == null){
            return JsonResult.failMessage("信息设置有误，会员卡，");
        }
        //生成协议编号
        FrCardAgreement frCardAgreement = iFrAgreementService.getAgreement(frCard.getCustomerCode());
        if(frCardAgreement == null){
            return  JsonResult.failMessage("订单规则生成有误");
        }
        String userURL = "";
        String shopId = "";
        if(mapS != null){
            userURL = mapS.get("userURL");
            shopId = mapS.get("shopId");
        }
        frClient.setCustomerCode(frCard.getCustomerCode());
        //判断是否现有客户，已经是现有客户了直接返回，
        List<FrClient> frClientList = iFrClientService.queryByClient(frClient);
        if(frClientList != null && frClientList.size() >0){
            for(FrClient frClientL:frClientList){
                if(frClientL.getType()!= null && frClientL.getType() >0){
                    return JsonResult.failMessage("该现有客户已存在");
                }
            }
        }
        FrClient frClient1 = iFrClientService.addClientPersonal(frClient,true,shopId);
        frCard.setClientId(frClient1.getId());
        JsonResult json = this.toAddFrCard(frCardAgreement,frCard,frCardOrderInfo,frCardOrderPayModes,frCardOrderAllotSetList,mapS,mapI);
        FrClient frClient2 = null;
        //更新为普通会员
        List<FrLevel> frLevelList = iFrLevelService.selectList(new EntityWrapper<FrLevel>().where("CustomerCode={0}",frCard.getCustomerCode()).and("is_using={0}",1).orderBy("low_integral DESC"));
        if(frLevelList!=null){
            for(FrLevel frLevel: frLevelList){
                if(frCardOrderInfo.getNeedPrice() >= frLevel.getLowMoney()){
                    frClient2 = new FrClient();
                    frClient2.setLevelId(frLevel.getId());
                    break;
                }
            }
        }
        if(frClient2 != null){
            iFrClientService.update(frClient2,new EntityWrapper<FrClient>().where("id={0}",frClient1.getId()).and("is_using={0}",1).and("CustomerCode={0}",frClient1.getCustomerCode()));
        }
        //如果客户有上传头像，插入头像地址
        if(!StringUtils.isEmpty(userURL)){
            FrClientPic frClientPic = new FrClientPic();
            frClientPic.setId(UUIDUtils.generateGUID());
            frClientPic.setPicType(CommonUtils.PIC_TYPE_1);
            frClientPic.setClientId(frClient1.getId());
            frClientPic.setPicState(true);
            frClientPic.setPicLink(userURL);
            iFrClientPicService.insert(frClientPic);
        }
        return JsonResult.success(true);
    }
}
