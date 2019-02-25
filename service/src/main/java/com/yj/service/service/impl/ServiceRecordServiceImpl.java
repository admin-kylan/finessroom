package com.yj.service.service.impl;

import com.yj.dal.model.FrTrainingPlan;
import com.yj.dal.model.ServiceRecord;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.exception.YJException;
import com.yj.common.util.PageUtils;
import com.yj.common.util.Query;
import com.yj.dal.dao.FrTrainingPlanMapper;
import com.yj.dal.dao.ServiceRecordMapper;
import com.yj.service.service.IServiceRecordService;
import com.yj.service.base.BaseServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2019-02-18
 */
@Service
public class ServiceRecordServiceImpl  extends BaseServiceImpl<ServiceRecordMapper, ServiceRecord>  implements IServiceRecordService {
	 @Autowired
	ServiceRecordMapper ServiceRecordMapper;
	
	@Override
	public int insertSelective(ServiceRecord record) {
		// TODO Auto-generated method stub
		return ServiceRecordMapper.insertSelective(record);
	}
	 
	
	public PageUtils getServiceRecordList(String cid, String currPage ,String status) throws YJException {
		   Map<String, Object> map = new HashMap<>();
	        if (currPage == null) {
	            currPage = "1";
	        }
	        map.put("page", currPage);
	        map.put("limit", "10");
	        Page page = new Query<ServiceRecord>(map).getPage();
	        
	        if("100".equals(status)){
	        	 page = selectPage(page,
	 	                new EntityWrapper<ServiceRecord>().where(" customerId ={0}   ", cid));
	        }else{
	        	 page = selectPage(page,
	 	                new EntityWrapper<ServiceRecord>().where(" customerId ={0}  and   status ={1} ", cid,status));
	        }
	       
	        List<FrTrainingPlan> frTrainingPlans = page.getRecords();
	        page.setRecords(frTrainingPlans);
	        return new PageUtils(page);
	}
	 
}
