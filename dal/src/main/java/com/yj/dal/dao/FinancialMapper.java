package com.yj.dal.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * className FinancialMapper
 * by Kylan
 *
 * @author Kylan
 * @date 2019-03-03 12:09
 */
public interface FinancialMapper {

    List<Map<String, Object>> findEducationFinancialList(@Param("shopId")String shopId, @Param("eduType")String eduType,
                                                         @Param("beginDate")String beginDate, @Param("endDate")String endDate,
                                                         @Param("coachName")String coachName, @Param("code")String code);

    List<Map<String, Object>> findCourseAnalysisList(@Param("params") Map<String, String> map);
}
