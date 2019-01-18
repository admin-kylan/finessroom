package com.yj.dal.dao;

import com.yj.dal.model.FrEducationMemberOrder;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 个人消费明细表 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2019-01-05
 */
public interface FrEducationMemberOrderMapper extends BaseMapper<FrEducationMemberOrder> {


    List<Map<String, Object>> findMemberOrderList(@Param("eduId") String eduId, @Param("searchInput") String searchInput);

    FrEducationMemberOrder findMemberOrderNormal(@Param("eduId") String eduId, @Param("clientInfoId") String clientInfoId);


}
