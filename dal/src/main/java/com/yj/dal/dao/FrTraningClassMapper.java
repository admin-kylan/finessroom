package com.yj.dal.dao;

import com.yj.dal.dto.FrTraningClassListDTO;
import com.yj.dal.model.FrTraningClass;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-15
 */
public interface FrTraningClassMapper extends BaseMapper<FrTraningClass> {

	
	
	List<FrTraningClassListDTO> frTraningClassList(String traningSeriesId);

	List<FrTraningClassListDTO> frTraningClassList2(String traningSeriesId);
}
