package com.yj.dal.dao;

import com.yj.dal.model.FrEducationFreezeClient;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员冻结列表 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2019-01-26
 */
public interface FrEducationFreezeClientMapper extends BaseMapper<FrEducationFreezeClient> {


    List<Map<String, Object>> findAllByCondition(@Param("shopId") String shopId,@Param("beginDate") String beginDate,
                                                 @Param("endDate")String endDate, @Param("searchInput") String searchInput,
                                                 @Param("eduType") String eduType);

}
