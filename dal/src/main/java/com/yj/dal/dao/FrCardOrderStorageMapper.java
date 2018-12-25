package com.yj.dal.dao;

import com.yj.dal.model.FrCardOrderStorage;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员卡 储值表 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-20
 */
public interface FrCardOrderStorageMapper extends BaseMapper<FrCardOrderStorage> {

    List<Map<String,Object>> queryStorageCardLis(FrCardOrderStorage frCardOrderStorage);

    List<FrCardOrderStorage> queryStorgeList(Map<String,Object> map);

    Map<String,Double> getCardStoragePrice(FrCardOrderStorage frCardOrderStorage);
}
