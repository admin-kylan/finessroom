package com.yj.dal.dao;

import com.yj.dal.model.FrActionSeries;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-14
 */
public interface FrActionSeriesMapper extends BaseMapper<FrActionSeries> {

	
	List<FrActionSeries> getByList();
}
