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


    public List<Map<String, Object>> findCourseAnalysisList(Map<String, String[]> map){
        String eduType = map.get("eduType")[0];
        String shopId = map.get("shopId")[0];
        String beginDate = map.get("beginDate")[0];
        String endDate = map.get("endDate")[0];
        String weekId = map.get("weekId")[0];
        String timeId = map.get("timeId")[0];
        String courseId = map.get("courseId")[0];
        String coachId = map.get("coachId")[0];
        String coachName = map.get("coachName")[0];
        String code = map.get("code")[0];
        return financialMapper.findCourseAnalysisList(eduType,shopId,beginDate,endDate,weekId,timeId,courseId,coachId,coachName,code);

    }
}
