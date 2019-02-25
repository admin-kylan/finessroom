package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.util.PageUtils;
import com.yj.dal.model.ServiceRecord;
import com.yj.service.base.BaseService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2019-02-18
 */
public interface IServiceRecordService extends BaseService<ServiceRecord> {
	int insertSelective(ServiceRecord record);
	
	public PageUtils getServiceRecordList(String cid, String currPage ,String status)throws YJException ;
}
