package com.yj.dal.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yj.dal.model.FrPrivateCourceRelation;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-19
 */
public interface FrPrivateCourceRelationMapper extends BaseMapper<FrPrivateCourceRelation> {

	
	
	List<FrPrivateCourceRelation> getRelationByCourceId(String privateCourceId);
	
}
