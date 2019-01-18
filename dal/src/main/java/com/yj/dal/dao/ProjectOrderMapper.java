package com.yj.dal.dao;

import com.yj.dal.model.ProjectOrder;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 项目订单表
 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-27
 */
public interface ProjectOrderMapper extends BaseMapper<ProjectOrder> {


    List<Map<String, Object>> findCourseByClientId(@Param("courseId") String courseId, @Param("clientId") String clientId,
                                                   @Param("cardId") String cardId, @Param("code") String code,
                                                   @Param("num") String num, @Param("shopId") String shopId);
}
