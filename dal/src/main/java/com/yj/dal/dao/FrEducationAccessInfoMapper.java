package com.yj.dal.dao;

import com.yj.dal.model.FrCardLimit;
import com.yj.dal.model.FrEducationAccessInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2019-02-16
 */
public interface FrEducationAccessInfoMapper extends BaseMapper<FrEducationAccessInfo> {


    List<FrEducationAccessInfo> findList(@Param("shopId") String shopId, @Param("code") String code,
                                                @Param("searchInput") String searchInput,
                                                @Param("beginDate") String beginDate, @Param("endDate") String endDate);
    List<FrEducationAccessInfo> findOutputList(@Param("shopId") String shopId, @Param("code") String code,
                                                @Param("beginDate") String beginDate, @Param("endDate") String endDate);

    List<FrEducationAccessInfo> findInfoSearch(@Param("shopId") String shopId, @Param("code") String code,
                                               @Param("beginDate") String beginDate, @Param("endDate") String endDate, @Param("outputSearch") String outputSearch);

    Map<String, Object> calcNum(@Param("shopId") String shopId, @Param("code") String code,
                                      @Param("beginDate") String beginDate, @Param("endDate") String endDate);

    List<Map<String, Object>> loadCardList(@Param("shopId") String shopId, @Param("code") String code,
                                      @Param("mobile") String mobile, @Param("cardNo") String cardNo);

    Map<String, Object> loadInfo(@Param("shopId") String shopId, @Param("code") String code,
                                      @Param("mobile") String mobile, @Param("cardNo") String cardNo);

    List<Map<String, Object>> findItemByShopId(@Param("shopId") String shopId, @Param("code") String code);

    List<Map<String, String>> versionClient(@Param("clientId") String clientId);
}
