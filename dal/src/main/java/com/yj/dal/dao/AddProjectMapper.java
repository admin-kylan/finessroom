package com.yj.dal.dao;

import com.yj.dal.model.AddProject;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员增购项目表
 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-27
 */
public interface AddProjectMapper extends BaseMapper<AddProject> {



    List<Map<String, Object>> getProjectList(@Param("status") String status, @Param("shopId") String shopId,
                                             @Param("timeType") String timeType, @Param("beginDate") String startDate,
                                             @Param("endDate") String endDate, @Param("courseName") String courseName,
                                             @Param("code") String code, @Param("clientId") String clientId);

    List<Map<String, Object>> getProjectList2(@Param("status") String status, @Param("shopId") String shopId,
                                             @Param("timeType") String timeType, @Param("beginDate") String startDate,
                                             @Param("endDate") String endDate, @Param("courseName") String courseName,
                                             @Param("code") String code, @Param("clientId") String clientId);

    List<Map<String, Object>> getProjectListSelect(@Param("shopId") String shopId, @Param("code") String code,
                                               @Param("clientId") String clientId);

    List<Map<String, Object>> findStartProjectRecord(@Param("shopId") String shopId, @Param("clientId") String clientId,
                                                     @Param("code") String code);

    List<Map<String, Object>> findCustomerRemnantRecord(@Param("shopId") String shopId, @Param("clientId") String clientId,
                                                     @Param("code") String code);

    List<Map<String, Object>> findCustomerExtensionRecord(@Param("shopId") String shopId, @Param("clientId") String clientId,
                                                     @Param("code") String code);

    List<Map<String, Object>> getListTurnProject(@Param("shopId") String shopId, @Param("clientId") String clientId,
                                                     @Param("code") String code);

    List<Map<String, Object>> getListReturnAddProject(@Param("shopId") String shopId, @Param("clientId") String clientId,
                                                     @Param("code") String code);

}
