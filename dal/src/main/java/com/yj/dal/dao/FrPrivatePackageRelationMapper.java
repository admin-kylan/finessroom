package com.yj.dal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yj.dal.model.FrPrivateCource;
import com.yj.dal.model.FrPrivatePackageRelation;
import com.yj.dal.model.Shop;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-28
 */
public interface FrPrivatePackageRelationMapper extends BaseMapper<FrPrivatePackageRelation> {

	
	
	List<Shop> getShopList(String CustomerCode);
	
	List<FrPrivateCource> courceList(@Param("maps") Map<String,Object> map);
}
