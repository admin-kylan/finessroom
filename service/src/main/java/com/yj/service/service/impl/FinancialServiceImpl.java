package com.yj.service.service.impl;

import com.yj.common.util.StringUtils;
import com.yj.dal.dao.FinancialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className FinancialServiceImpl
 * by Kylan
 *
 * @author Kylan
 * @date 2019-03-03 12:09
 */
@Service
public class FinancialServiceImpl {


    @Autowired
    private FinancialMapper financialMapper;


    public List<Map<String, Object>> findEducationFinancialList(String shopId, String eduType, String beginDate, String endDate, String name, String code){
        List list = financialMapper.findEducationFinancialList(shopId, eduType, beginDate, endDate, name, code);
        return list;
    }


    public List<Map<String, Object>> findCourseAnalysisList(Map<String, String> map){
        //星期几
        if(!StringUtils.isBlank(map.get("weekId"))){
            map.put("weekId", String.valueOf(Integer.parseInt(map.get("weekId")) + 1));
        }
        //时间段
        if(!StringUtils.isBlank(map.get("timeId"))){

        }
        return financialMapper.findCourseAnalysisList(map);

    }
}
