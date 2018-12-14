package com.yj.service.service;

import com.yj.dal.model.FrCardOrderAllotSet;
import com.yj.service.base.BaseService;

import java.util.List;

/**
 * <p>
 * 完成订单，业绩分配人员比例（订单业绩分配人员设置） 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-15
 */
public interface IFrCardOrderAllotSetService extends BaseService<FrCardOrderAllotSet> {

    /**
     * 初始化业绩分配表信息，从前端获取的
     * @param orderAllotSetList  保存的人员比例分配信息 JSON 格式的字符串
     * @param orderAllotSet       业绩表数据  JSON 格式的字符串
     * @param orderType           订单类型
     * @return
     */
    public List<FrCardOrderAllotSet> initFrCardOrderAllotSetData(String orderAllotSetList,String orderAllotSet,Integer orderType);

}
