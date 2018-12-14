package com.yj.service.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yj.dal.model.FrCardOrderAllotSet;
import com.yj.dal.dao.FrCardOrderAllotSetMapper;
import com.yj.service.service.IFrCardOrderAllotSetService;
import com.yj.service.base.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 完成订单，业绩分配人员比例（订单业绩分配人员设置） 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-15
 */
@Service
public class FrCardOrderAllotSetServiceImpl extends BaseServiceImpl<FrCardOrderAllotSetMapper, FrCardOrderAllotSet> implements IFrCardOrderAllotSetService {


    @Override
    public List<FrCardOrderAllotSet> initFrCardOrderAllotSetData(String orderAllotSetList, String orderAllotSet,Integer orderType) {
        List<FrCardOrderAllotSet> frCardOrderAllotSetList = null;
        if( !StringUtils.isEmpty(orderAllotSetList) && !StringUtils.isEmpty(orderAllotSet)){
            FrCardOrderAllotSet frCardOrderAllotSet =  JSONObject.parseObject(orderAllotSet,FrCardOrderAllotSet.class);
            frCardOrderAllotSetList = JSONArray.parseArray(orderAllotSetList,FrCardOrderAllotSet.class);
            if(frCardOrderAllotSetList!=null && frCardOrderAllotSet!=null){
                for(FrCardOrderAllotSet frCardOrderAllotSet1 : frCardOrderAllotSetList){
                    frCardOrderAllotSet1.setAllotType(frCardOrderAllotSet.getAllotType());
                    frCardOrderAllotSet1.setAllotNum(frCardOrderAllotSet.getAllotNum());
                    frCardOrderAllotSet1.setSaleAllotType(frCardOrderAllotSet.getSaleAllotType());
                    frCardOrderAllotSet1.setOrderType(orderType);
                    frCardOrderAllotSet1.setUsing(true);
                }
            }
        }
        return frCardOrderAllotSetList;
    }
}
