package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.dal.model.FrCardOrderPayMode;
import com.yj.service.base.BaseService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 所有订单支付方式及金额 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-06
 */
public interface IFrCardOrderPayModeService extends BaseService<FrCardOrderPayMode> {

    /**
     * 字符串转换成payMode对象
     * @param map
     * @param index
     * @return
     * @throws YJException
     */
    public List<FrCardOrderPayMode> getPayModeList(Map<String,Object> map, String index,Integer orderType)throws YJException;


    /**
     * 获取支付总金额并初始化
     * @param frCardOrderPayModes
     * @return
     */
    public Double getAllPrice(List<FrCardOrderPayMode> frCardOrderPayModes, String orderId);


    /**
     * 根据字符串获取支付数据
     * @param str  格式："1:3.00,2:400,3:500...."
     * @param orderType
     * @return
     * @throws YJException
     */
    public List<FrCardOrderPayMode> getStringForFrCardMode(String str,Integer orderType)throws YJException;


}
