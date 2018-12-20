package com.yj.web.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
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
import com.yj.common.util.StringUtils;
import com.yj.dal.dao.FrTraningClassMapper;
import com.yj.dal.dto.FrTraningClassListDTO;
import com.yj.dal.dto.FrTraningClassDTO;
import com.yj.dal.model.FrTrainingSeries;
import com.yj.dal.model.FrTraningClass;
import com.yj.service.service.IFrTraningClassService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-15
 */
@RestController
@RequestMapping("/frTraningClass")
public class FrTraningClassController {

	
	@Autowired
	IFrTraningClassService iFrTraningClassService;
	
	@Autowired
	FrTraningClassMapper frTraningClassMapper;
	
	/**
	 * @TODO:  根据单节课程获取对应的动作
	 * @author hujz
	 * @date 2018年11月15日 上午11:23:50 
	 * 
	 * @param request
	 * @return
	 * @throws YJException
	 */
    @RequestMapping(value="/list",method={RequestMethod.GET,RequestMethod.POST})
	public JsonResult list(HttpServletRequest request,@RequestParam("traningSeriesId") String traningSeriesId,@RequestParam("type") String type) throws YJException {
//         EntityWrapper<FrTraningClass> wrapper = new EntityWrapper<>();
//         wrapper.setSqlSelect(" * ").where("is_using = 1 and and traning_series_id = {0}",traningSeriesId);
//         List<FrTraningClass> frTraningClasss = iFrTraningClassService.selectList(wrapper);
        List<FrTraningClassListDTO> frTraningClassList=new ArrayList<FrTraningClassListDTO>();
        if("1".equals(type)){
            frTraningClassList = frTraningClassMapper.frTraningClassList(traningSeriesId);
        }else if("2".equals(type)){
            frTraningClassList = frTraningClassMapper.frTraningClassList2(traningSeriesId);
        }
         return JsonResult.success(frTraningClassList);

    }
    
    /**
     * @TODO:  新增单节训练课程(含修改和删除)
     * @author hujz
     * @date 2018年11月15日 上午11:24:28 
     * 
     * @return
     * @throws YJException
     */
    @RequestMapping(value="/addOrUpdate",method={RequestMethod.GET,RequestMethod.POST})
   	public JsonResult addFrTraningClass(FrTraningClass frTraningClass) throws YJException {
    	
    	if(StringUtils.isNotBlank(frTraningClass.getId())){
			Wrapper<FrTraningClass> entityWrapper = new EntityWrapper<>();
			entityWrapper.where("id = {0}",frTraningClass.getId());
	 		Integer update = frTraningClassMapper.update(frTraningClass, entityWrapper);
	 		if(update>0){
	 			return JsonResult.successMessage("操作成功");
	 		}else{
	 			return JsonResult.failMessage("操作失败");
	 		}
		}else{
			//新增
			Integer insert = frTraningClassMapper.insert(frTraningClass);
			if(insert>0){
	 			return JsonResult.successMessage("操作成功");
	 		}else{
	 			return JsonResult.failMessage("操作失败");
	 		}
			
		}

    }
    
	 
	 /**
	  * 功能： 为训练计划添加训练课程
	  *	   	
	  *  <p>
	  *		创建日期：2018年11月18日下午8:36:01
	  *  </p>
	  * @return
	  * @throws YJException
	  */
	 @RequestMapping(value="/batchAddFrTraningClass",method={RequestMethod.GET,RequestMethod.POST})
//	 public JsonResult list(@RequestParam("actionIds")String actionIds,@RequestParam("traningSeriesId")String traningSeriesId,@RequestParam("type")Integer type) throws YJException {
	 public JsonResult batchAddFrTraningClass(@RequestBody Map<String,Object> params) throws YJException {	
		 String actionIds = (String) params.get("actionIds");
		 String traningSeriesId = (String) params.get("traningSeriesId");
		 Integer ownType = (Integer)params.get("ownType");

		 Integer type = (Integer) params.get("type");
		 String[] actionIdArr = actionIds.split(",");
		 List<FrTraningClass> list = new ArrayList<FrTraningClass>();
		 for (int i = 0; i < actionIdArr.length; i++) {
			 FrTraningClass tclass = new FrTraningClass();
			 tclass.setActionId(actionIdArr[i]);
			 tclass.setTraningSeriesId(traningSeriesId);
			 tclass.setCreateTime(new Date());
			 tclass.setIsUsing(1);
			 tclass.setType(type);
			 tclass.setOwnType(ownType);
			 tclass.setUpdateTime(new Date());
			 Integer insert = frTraningClassMapper.insert(tclass);
			 if(insert>0)
				 list.add(tclass);
			
		}
		 return JsonResult.success(list);
	 }
    
	 /**
	  * @TODO: 批量保存动作类型  
	  * @author hujz
	  * @date 2018年11月24日 下午5:52:00 
	  * 
	  * @param params
	  * @return
	  * @throws YJException
	  */
	 @RequestMapping(value="/batchUpdateFrTraningClass",method={RequestMethod.GET,RequestMethod.POST})
	 //List<FrTraningClass> classList
//	 public JsonResult list(@RequestParam("actionIds")String actionIds,@RequestParam("traningSeriesId")String traningSeriesId,@RequestParam("type")Integer type) throws YJException { @RequestBody(required=false)
	 public JsonResult batchUpdateFrTraningClass(@RequestBody List<FrTraningClassDTO> classList) throws YJException {
		 for (FrTraningClass frTraningClass : classList) {
			 frTraningClassMapper.updateById(frTraningClass);
		}
		 return JsonResult.success(classList);
	 }

	 public static void main(String[] args){
	 	int[][] arr = {{1,1,1,1},{1,1,1,1}};
	 	System.out.println(JSON.toJSONString(arr));
	 	String arrStr = "[[1,1,1,1],[1,1,1,1]]";
		 int[][] i = JSON.parseObject(arrStr, int[][].class);
		 System.out.println(i);
	 }
}

