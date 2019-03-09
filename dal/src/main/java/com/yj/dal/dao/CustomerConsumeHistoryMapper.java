package com.yj.dal.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * className CustomerConsumeHistoryMapper
 * by Kylan
 *
 * @author Kylan
 * @date 2019-03-01 23:28
 */
public interface CustomerConsumeHistoryMapper {

    List<Map<String, Object>> findConsumeList(@Param("pageSize") String pageSize,@Param("clientId") String clientId,
                                              @Param("code") String code,@Param("beginDate") String beginDate,
                                              @Param("endDate") String endDate,@Param("pageIndex") String pageIndex);

    Map<String, Integer> findConsumeListCount(@Param("pageSize") String pageSize,@Param("clientId") String clientId,
                                          @Param("code") String code,@Param("beginDate") String beginDate,
                                          @Param("endDate") String endDate,@Param("pageIndex") String pageIndex);
}
