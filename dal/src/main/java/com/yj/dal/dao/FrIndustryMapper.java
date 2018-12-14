package com.yj.dal.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yj.dal.model.FrIndustry;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 行业表 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-12
 */
public interface FrIndustryMapper extends BaseMapper<FrIndustry> {

    List<FrIndustry> selectProtectionDaysList(String customerCode);

    List<FrIndustry> selectClaimSetList(String customerCode);

    List<FrIndustry> selectFollowList(@Param("customerType") Integer customerType, @Param("customerCode") String customerCode);

    int updateByEntity(@Param("frIndustry") FrIndustry frIndustry);
}
