package com.yj.web.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.PageUtils;
import com.yj.common.util.Query;
import com.yj.dal.model.FrTrainingPlan;
import com.yj.dal.model.ServiceRecord;
import com.yj.service.service.IServiceRecordService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2019-02-18
 */
@RestController
@RequestMapping("/serviceRecord")
public class ServiceRecordController {
	@Resource
	 private IServiceRecordService service;
	
	   @PostMapping("/addServiceRecord")
	    public JsonResult addServiceRecord(@RequestBody ServiceRecord serviceRecord) throws YJException {
		   serviceRecord.setStatus("0"); //初始化状态
		  try {
			  if(service.insert(serviceRecord)) {
			 // if( service.insertSelective(serviceRecord)>0) {
				   return JsonResult.success(); 
			   }else {
				   return JsonResult.fail();
			   }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JsonResult.fail(e.getMessage());
		}
		  
	    }
	   /**
	    * 处理1 \处理2
	    * 
	    */
	   @PostMapping("/serviceProcess")
	    public JsonResult serviceProcess(@RequestBody ServiceRecord serviceRecord) throws YJException {
		   if(service.updateById(serviceRecord)) {
			   return JsonResult.success(); 

		   }else {
			   return JsonResult.fail();

		   }
	   }
	   
	 
	   @GetMapping("/queryServiceRecordList")
	    public JsonResult getTrainingPlanList(String cid, String currPage ,String status) throws YJException {
		
		   PageUtils planList =  service.getServiceRecordList(cid, currPage,status);
	        if (planList != null) {
	            return JsonResult.success(planList);
	        } else {
	            return JsonResult.fail();
	        }

	    }
	   

}

