package com.yj.web.controller;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.dao.FrPrivateCourceMapper;
import com.yj.dal.dao.FrTraningClassMapper;
import com.yj.dal.dto.FrTrainingClassDTO;
import com.yj.dal.dto.FrTrainingSeriesListDTO;
import com.yj.dal.model.FrTrainingSeries;
import com.yj.dal.model.FrTraningClass;
import com.yj.service.service.IFrTrainingSeriesService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-15
 */
@RestController
@RequestMapping("/frTrainingSeries")
public class FrTrainingSeriesController {

	
	@Autowired
	IFrTrainingSeriesService iFrTrainingSeriesService;
	
	/**
	 * 功能：训练计划和训练 系列  列表
	 *	   	
	 *  <p>
	 *		创建日期：2018年11月15日下午9:03:34
	 *  </p>
	 * @param request
	 * @param parentId  如果为训练计划指向训练系列id 否则为0
	 * @param type    类型   1|单节训练课程   2|训练套餐
	 * @return
	 * @throws YJException
	 */
	 @RequestMapping(value="/list",method={RequestMethod.GET,RequestMethod.POST})
	 public JsonResult list(HttpServletRequest request,@RequestParam(value="parentId",required=false,defaultValue="")String parentId,Integer type,Integer ownType) throws YJException {
         EntityWrapper<FrTrainingSeries> wrapper = new EntityWrapper<>();
         if(StringUtils.isNotBlank(parentId)){
        	 wrapper.setSqlSelect(" * ").where("is_using = 1 and type = {0} and parent_id = {1} and own_type={2}",type,parentId,ownType);
         }else{
        	 wrapper.setSqlSelect(" * ").where("is_using = 1 and type = {0} and parent_id is null and own_type={1}",type,ownType);

         }
         List<FrTrainingSeries> frTrainingSeriess = iFrTrainingSeriesService.selectList(wrapper);
         return JsonResult.success(frTrainingSeriess);
	 }
	 
	 /**
	  * 功能： 更新或者新增
	  *	   	
	  *  <p>
	  *		创建日期：2018年11月15日下午9:10:29
	  *  </p>
	  * @param frTrainingSeries
	  * @return
	  * @throws YJException
	  */
	
	 @RequestMapping(value="/addOrUpdate",method={RequestMethod.GET,RequestMethod.POST})
	 public JsonResult addOrUpdate(@RequestBody FrTrainingSeries frTrainingSeries) throws YJException {
		if(StringUtils.isNotBlank(frTrainingSeries.getId())){
			Wrapper<FrTrainingSeries> entityWrapper = new EntityWrapper<>();
			entityWrapper.where("id = {0}",frTrainingSeries.getId());
	 		boolean update =iFrTrainingSeriesService.update(frTrainingSeries, entityWrapper);
	 		if(update){
	 			return JsonResult.success(frTrainingSeries);
	 		}else{
	 			return JsonResult.fail(frTrainingSeries);
	 		}
		}else{
			//新增
			boolean insertOrUpdateAllColumn = iFrTrainingSeriesService.insertOrUpdateAllColumn(frTrainingSeries);
			if(insertOrUpdateAllColumn){
				return JsonResult.success(frTrainingSeries);
	 		}else{
	 			return JsonResult.fail(frTrainingSeries);
	 		}
			
		}
	 }
	 
	 
	 @Autowired
	 FrPrivateCourceMapper frPrivateCourceMapper;
	 
	 
	 
	 @RequestMapping(value="/seriesAndActionList",method={RequestMethod.GET,RequestMethod.POST})
	 public JsonResult list(HttpServletRequest request,Integer type,Integer ownType) throws YJException {
         
		 FrTrainingSeriesListDTO dto = new FrTrainingSeriesListDTO();
		 
		 EntityWrapper<FrTrainingSeries> wrapper = new EntityWrapper<>();
         wrapper.setSqlSelect(" * ").where("is_using = 1 and type = {0} and parent_id is null and own_type={1}",type,ownType);
         // 一级系列列表
         List<FrTrainingSeries> frTrainingSeriess = iFrTrainingSeriesService.selectList(wrapper);
         if(null != frTrainingSeriess && !frTrainingSeriess.isEmpty()){
        	 dto.setSeriesList(frTrainingSeriess);
        	 //一级系列索引第一个对象
        	 FrTrainingSeries frTrainingSeries = frTrainingSeriess.get(0);
        	
        	 EntityWrapper<FrTrainingSeries> wrapper2 = new EntityWrapper<>();
        	 wrapper2.setSqlSelect(" * ").where("is_using = 1 and type = {0} and parent_id = {1}",type,frTrainingSeries.getId());
        	 // 二级系列列表
              List<FrTrainingSeries> seriess2 = iFrTrainingSeriesService.selectList(wrapper2);
              if(null != seriess2 && !seriess2.isEmpty()){
            	  dto.setSubSeriesList(seriess2);
            	  //二级系列索引第一个对象
            	  FrTrainingSeries series = seriess2.get(0);
            	  //二级系列索引第一个对象 对应的动作集合
            	  List<FrTrainingClassDTO> classDtoList = frPrivateCourceMapper.getActionsByseriesId(series.getId(),type);
            	  dto.setClassDtoList(classDtoList);
              }

        	 // 二级系列动作
        	 
         }
         return JsonResult.success(dto);
	 }
	 

}

