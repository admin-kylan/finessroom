package com.yj.service.service.impl;

import com.yj.common.exception.YJException;
import com.yj.common.util.CommonUtils;
import com.yj.common.util.StringUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.model.FrCardOrderPayMode;
import com.yj.dal.dao.FrCardOrderPayModeMapper;
import com.yj.service.base.BaseServiceImpl;
import com.yj.service.service.IFrCardOrderPayModeService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 所有订单支付方式及金额 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-06
 */
@Service
public class FrCardOrderPayModeServiceImpl extends BaseServiceImpl<FrCardOrderPayModeMapper, FrCardOrderPayMode> implements IFrCardOrderPayModeService {


    @Override
    public List<FrCardOrderPayMode> getPayModeList(Map<String, Object> map, String index,Integer orderType) throws YJException {
        List<FrCardOrderPayMode> frCardOrderPayModes = new ArrayList<>();
        StringBuffer key;
        Object money;
        if(map != null){
            for(int i = 1; i< CommonUtils.PAY_MODE_COUNT; i++){
                key = new StringBuffer(index).append(i);
                String getK = key.toString();
                money = map.get(getK);
                if(money != null ){
                    Double toM = Double.valueOf(money.toString());
                    if( toM > 0 ){
                        FrCardOrderPayMode frCardOrderPayMode = new FrCardOrderPayMode();
                        frCardOrderPayMode.setOrderType(orderType);
                        frCardOrderPayMode.setPayMode(i);
                        frCardOrderPayMode.setPayPrice(toM);
                        frCardOrderPayModes.add(frCardOrderPayMode);
                    }
                }
            }
        }
        return frCardOrderPayModes;
    }

    /**
     * 获取支付总金额并初始化
     * @param frCardOrderPayModes
     * @return
     */
    public Double getAllPrice(List<FrCardOrderPayMode> frCardOrderPayModes,String orderId){
        Double allPrice = 0.0;
        if(frCardOrderPayModes != null && frCardOrderPayModes.size()>0){
            for(FrCardOrderPayMode frCardOrderPayMode : frCardOrderPayModes){
                frCardOrderPayMode.setId(UUIDUtils.generateGUID());
                frCardOrderPayMode.setOrderId(orderId);
                allPrice += frCardOrderPayMode.getPayPrice();
            }
        }
        return allPrice;
    }

    @Override
    public List<FrCardOrderPayMode> getStringForFrCardMode(String str, Integer orderType) throws YJException {
        List<FrCardOrderPayMode> frCardOrderPayModeList = new ArrayList<>();
        FrCardOrderPayMode frCardOrderPayMode;
        if(!StringUtils.isEmpty(str)){
            String[] modeList = str.split(",");
            if(modeList!=null && modeList.length>0){
                for (String s :modeList){
                    frCardOrderPayMode = new FrCardOrderPayMode();
                    String[] t = s.split(":");
                    if(t != null && t.length>0){
                        String payMode = t[0];
                        String payPrice = t[1];
                        if(this.getStringIsNum(payMode)){
                            frCardOrderPayMode.setPayMode(Integer.valueOf(payMode));
                        }
                        if(this.getStringIsNum(payPrice)){
                            frCardOrderPayMode.setPayPrice(Double.valueOf(payPrice));
                        }
                    }
                    frCardOrderPayMode.setOrderType(orderType);
                    frCardOrderPayModeList.add(frCardOrderPayMode);
                }
            }
        }
        return  frCardOrderPayModeList;
    }

    /**
     * 判断字符串是否为纯数字
     * @param str
     * @return
     */
    public boolean getStringIsNum(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
