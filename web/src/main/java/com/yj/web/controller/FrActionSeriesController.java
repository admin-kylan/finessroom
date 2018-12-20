package com.yj.web.controller;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.dal.dao.FrActionMapper;
import com.yj.dal.dao.FrActionSeriesMapper;
import com.yj.dal.dto.FrActionSeriesListDTO;
import com.yj.dal.model.FrAction;
import com.yj.dal.model.FrActionSeries;
import com.yj.service.service.IFrActionSeriesService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-14
 */
@RestController
@RequestMapping("/frActionSeries")
public class FrActionSeriesController{

	@Autowired
	IFrActionSeriesService iFrActionSeriesService;
	
	
	@Autowired
	FrActionSeriesMapper frActionSeriesMapper;
	
	@Autowired
	FrActionMapper frActionMapper;
	/**
	 *  动作系列列表
	 */
	@RequestMapping(value="/list",method={RequestMethod.GET,RequestMethod.POST})
	public JsonResult list(@RequestParam Map<String,Object> params, HttpServletRequest request) throws YJException {
    	 String code = CookieUtils.getCookieValue(request, "code", true),type="1";

		Object ownType = params.get("ownType");
    	 if(ownType != null){
			 type=String.valueOf(ownType);
		 }
    	 FrActionSeriesListDTO dto = new FrActionSeriesListDTO();
    	 
         EntityWrapper<FrActionSeries> wrapper = new EntityWrapper<>();
         wrapper.setSqlSelect(" * ").where("is_using = 1 and own_type={0}",type);
         List<FrActionSeries> seriesList = iFrActionSeriesService.selectList(wrapper);
         if(null != seriesList && !seriesList.isEmpty()){
        	 dto.setSeriesList(seriesList);
        	 FrActionSeries actionSeries = seriesList.get(0);
        	 
        	 EntityWrapper<FrAction> actionWrapper = new EntityWrapper<>();
        	 actionWrapper.setSqlSelect(" * ").where("is_using = 1 AND series_id = {0} and own_type={1}",actionSeries.getId(),type);
             List<FrAction> actionList = frActionMapper.selectList(actionWrapper);
             
             if(null != actionList && !actionList.isEmpty()){
            	 dto.setActionList(actionList);
             }
         }
         return JsonResult.success(dto);

    }
    
    
   /**
    * 功能：新增动作系列
    *	   	
    *  <p>
    *		创建日期：2018年11月15日上午12:13:41
    *  </p>
    * @param frActionSeries
    * @return
    */
    @RequestMapping(value="/addFrActionSeries",method={RequestMethod.GET,RequestMethod.POST})
    public JsonResult addFrActionSeries(@RequestBody FrActionSeries frActionSeries) {
        //组装数据
//    	frActionSeries.setId(UUID.randomUUID().toString().replace("-", ""));
//    	frActionSeries.setNote("note");
//    	frActionSeries.setName("huhu");
    	frActionSeries.setCustomerCode("1");
//    	frActionSeries.setCreateUser(1L);
    	frActionSeries.setIsUsing(1);
    	frActionSeries.setUpdateTime(new Date());
    	frActionSeries.setCreateTime(new Date());
    	iFrActionSeriesService.insert(frActionSeries);
        return JsonResult.success(frActionSeries);
    }
    
    
    /**
     * 功能：根据动作系列获取动作列表
     *	   	
     *  <p>
     *		创建日期：2018年11月20日下午11:56:44
     *  </p>
     * @param request
     * @param seriesId
     * @return
     * @throws YJException
     */
    @RequestMapping(value="/actionList",method={RequestMethod.GET,RequestMethod.POST})
	public JsonResult list(HttpServletRequest request,String seriesId) throws YJException {
         EntityWrapper<FrAction> wrapper = new EntityWrapper<>();
         wrapper.setSqlSelect("   name, diff, image, action_princeple AS actionPrinceple, create_time AS createTime, update_user AS updateUser, series_id AS seriesId, update_time AS updateTime, id, customer_code AS customerCode, is_using AS isUsing, create_user AS createUser ").where("is_using = 1 AND series_id = {0}",seriesId);
         List<FrAction> frActions = frActionMapper.selectList(wrapper);
         return JsonResult.success(frActions);

    }
}

