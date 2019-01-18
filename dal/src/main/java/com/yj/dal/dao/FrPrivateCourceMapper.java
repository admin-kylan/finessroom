package com.yj.dal.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.yj.dal.dto.FrTrainingClassDTO;
import com.yj.dal.model.FrPrivateCource;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-14
 */
public interface FrPrivateCourceMapper extends BaseMapper<FrPrivateCource> {

	List<FrPrivateCource> findPrivateCource(Page page,@Param("sdaduimId") String sdaduimId);
	List<FrPrivateCource> findCource(Page page,@Param("shopId") String shopId);
	List<FrTrainingClassDTO> getActionsByseriesId(@Param("traningSeriesId")String traningSeriesId,@Param("type")Integer type);
	List<FrTrainingClassDTO> getActionsByseriesId2(@Param("traningSeriesId")String traningSeriesId,@Param("type")Integer type);
}
