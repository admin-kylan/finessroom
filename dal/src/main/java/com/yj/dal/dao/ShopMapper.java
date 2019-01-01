package com.yj.dal.dao;

import com.yj.dal.dto.ShopItemDTO;
import com.yj.dal.model.FrClient;
import com.yj.dal.model.Shop;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 门店表 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-25
 */
public interface ShopMapper extends BaseMapper<Shop> {

    List<Shop> selectShopListByCity(@Param("shopId") String shopId, @Param("cityId") String cityId, @Param("customerCode") String customerCode);

    List<Shop> getFrCardType(String code);

    List<ShopItemDTO> selectShopItemList(@Param("customerCode") String customerCode, @Param("cityName") String cityName);

    List<Shop> getAllFrCardType(String code);

    String selectShopIdByPersonnelId(@Param("id") String id);

    Shop selectShopByClientId(@Param("id") String id);

    Shop getByShopIdList(@Param("code") String code, @Param("shopId") String shopId, @Param("type") Integer type);

    String getShopName(@Param("cid") String cid);

}


