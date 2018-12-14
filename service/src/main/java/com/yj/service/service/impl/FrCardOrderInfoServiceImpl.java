package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.*;
import com.yj.dal.dao.FrCardTypeMapper;
import com.yj.dal.dao.PersonnelInfoMapper;
import com.yj.dal.model.*;
import com.yj.dal.dao.FrCardOrderInfoMapper;
import com.yj.service.service.*;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * 会员卡订单记录 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-26
 */
@Service
public class FrCardOrderInfoServiceImpl extends BaseServiceImpl<FrCardOrderInfoMapper, FrCardOrderInfo> implements IFrCardOrderInfoService {

    @Resource
    private IFrCardOrderDatailService iFrCardOrderDatailService;
    @Resource
    private IFrCardOrderBackService iFrCardOrderBackService;

    @Override
    public String getOrderNo() {
        //判断重复
        List<FrCardOrderInfo> list = baseMapper.selectList(
                new EntityWrapper<FrCardOrderInfo>().where("is_using = 1")
        );
        int n = 0;
        Random r = new Random();
        String orderNo;
        while (true){
            orderNo = DateUtil.dateToString(new Date(), "yyyyMMddHHmm")+(r.nextInt(99999-10000)+10000);;
//        System.out.println(orderNo);
            if(list == null || list.size()<1){
                break;
            }
            for(FrCardOrderInfo f:list){
                if(orderNo.equals(f.getOrderNo())){
                    n = 1;
                }
            }
            if(n == 0){
                break;
            }

        }
        return orderNo;
    }

    @Override
    public PageUtils getCardOrederList(String code,PageUtil<FrCard> pageUtil) throws YJException {
        Map map = new HashMap();
        map.put("limit",pageUtil.getRows());//每页多少条
        map.put("page",pageUtil.getPage());//当前页
        Page page = new Query<FrCardOrderInfo>(map).getPage();
        //查询该会员卡订单列表总数据
        List<FrCardOrderInfo> list = baseMapper.getCardOrederList(page,pageUtil);
        page.setRecords(list);
        return new PageUtils(page);
    }

    @Override
    public Map<String, Object> queryOrderInfoPrice(String cardId, String CustomerCode)throws YJException {
        if(StringUtils.isEmpty(CustomerCode)){
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        if(StringUtils.isEmpty(cardId)){
            throw new YJException(YJExceptionEnum.VIPCARDNO_NOT_EXISTED);
        }
        FrCardOrderInfo frCardOrderInfo = new FrCardOrderInfo();
        frCardOrderInfo.setCardId(cardId);
        frCardOrderInfo.setCustomerCode(CustomerCode);
        return baseMapper.queryOrderInfoPrice(frCardOrderInfo);
    }

    /**
     * 获取退卡数据
     * @param frCardOrderInfo
     * @return
     * @throws YJException
     */
    @Override
    public Map<String, Object> getBlackCardData(FrCardOrderInfo frCardOrderInfo) throws YJException {
        if(frCardOrderInfo == null){
            throw new  YJException(YJExceptionEnum.OBJECT_NOT_FOUND);
        }
        Double complementPrice = 0.0;
        frCardOrderInfo.setStatus(CommonUtils.ORDER_STATUS_1);
        frCardOrderInfo.setAuditStatus(CommonUtils.AUDIT_ORDER_STATUS_1);
        frCardOrderInfo.setUsing(true);
        Map<String, Object> map = baseMapper.getBlackCardData(frCardOrderInfo);
        if(map != null && map.get("zkXffs") != null){
           String num = map.get("zkXffs").toString();
           boolean isFlag = false;
           if("1".equals(num)){  //子卡与主卡分割额度使用
               isFlag = true;
           }
            FrCardOrderBack frCardOrderBack = new FrCardOrderBack();
            frCardOrderBack.setClientId(frCardOrderInfo.getClientId());
            frCardOrderBack.setCardId(frCardOrderInfo.getCardId());
            frCardOrderBack.setCustomerCode(frCardOrderInfo.getCustomerCode());
            Map<String,Double>  getHaveNum = iFrCardOrderBackService.getHaveNum(frCardOrderBack,isFlag);
            Double orderPrice = getHaveNum.get("orderPrice");
            Double orderRightsNum = getHaveNum.get("orderRightsNum");
            Double childPrice = getHaveNum.get("childPrice");
            Double childRightsNum = getHaveNum.get("childRightsNum");
           //剩余总权益 = 父卡剩余权益+子卡剩余权益
           map.put("haveRightsNum",orderRightsNum+childRightsNum);
           map.put("orderPrice",orderPrice+childPrice);
           //根据支付方式查询需分期或者补余的金额
           String payType = map.get("payType").toString();
           if(CommonUtils.ORDER_INFO_PAY_TYPE_2.toString().equals(payType) || CommonUtils.ORDER_INFO_PAY_TYPE_5.toString().equals(payType)){
                 FrCardOrderInfo frCardOrderInfo1 = new FrCardOrderInfo();
                 frCardOrderInfo1.setCardId(frCardOrderInfo.getCardId());
                 frCardOrderInfo1.setCustomerCode(frCardOrderInfo.getCustomerCode());
                 complementPrice = baseMapper.queryOrderInfoAllPrice(frCardOrderInfo1);
           }
            map.put("complementPrice",complementPrice);  //补余金额
        }
        FrCardOrderDatail frCardOrderDatail = new FrCardOrderDatail();
        frCardOrderDatail.setCardId(frCardOrderInfo.getCardId());
        frCardOrderDatail.setClientId(frCardOrderInfo.getClientId());
        frCardOrderDatail.setCustomerCode(frCardOrderInfo.getCustomerCode());
        frCardOrderDatail.setType(CommonUtils.ORDER_DATAIL_TYPE_2);
        //可退剩余总储值金额
        Double allPrice = iFrCardOrderDatailService.querySumOrderPrice(frCardOrderDatail,false);
        if(allPrice == null){
            allPrice = 0.0;
        }
        if( map == null){
            map = new HashMap<>();
        }
        map.put("allPrice",allPrice);
        return map;
    }


    /**
     * 根据会员卡信息获取订单信息及卡种信息
     * @param frCardOrderInfo
     * @return
     * @throws YJException
     */
    @Override
    public Map<String,Object> queryCardAndType( FrCardOrderInfo frCardOrderInfo) throws YJException {
        if(frCardOrderInfo == null){
            throw  new YJException(YJExceptionEnum.REQUEST_NULL);
        }
        if(StringUtils.isEmpty(frCardOrderInfo.getCardId()) || StringUtils.isEmpty(frCardOrderInfo.getCustomerCode()) || StringUtils.isEmpty(frCardOrderInfo.getClientId())){
           throw  new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        return baseMapper.queryCardAndType(frCardOrderInfo);
    }
}
