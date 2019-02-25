package com.yj.dal.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yj.dal.model.ServiceRecord;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2019-02-18
 */
public interface ServiceRecordMapper extends BaseMapper<ServiceRecord> {

//	  int deleteByPrimaryKey(Integer id);
//
//	    void insert(ServiceRecord record);
//
	    int insertSelective(ServiceRecord record);
//
//	    ServiceRecord selectByPrimaryKey(Integer id);
//
//	    int updateByPrimaryKeySelective(ServiceRecord record);
//
//	    int updateByPrimaryKey(ServiceRecord record);
}
