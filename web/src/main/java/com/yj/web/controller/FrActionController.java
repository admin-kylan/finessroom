package com.yj.web.controller;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.yj.common.util.UUIDUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.dao.FrActionMapper;
import com.yj.dal.model.FrAction;
import com.yj.service.service.IFrActionService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-14
 */
@RestController
@RequestMapping("/frAction")
public class FrActionController {

	
	@Resource
	IFrActionService iFrActionService;
	
	
	@Resource
	FrActionMapper frActionMapper;
	
	
	
	/**
	 * @TODO:  动作列表
	 * @author hujz
	 * @date 2018年11月15日 上午11:23:50 
	 * 
	 * @param request
	 * @return
	 * @throws YJException
	 */
    @RequestMapping(value="/list",method={RequestMethod.GET,RequestMethod.POST})
	public JsonResult list(@RequestParam Map<String,Object> params,HttpServletRequest request, String seriesId) throws YJException {
        String type="1";
		Object ownType = params.get("ownType");
		if(ownType != null){
			type=String.valueOf(ownType);
		}
    	EntityWrapper<FrAction> wrapper = new EntityWrapper<>();
         wrapper.setSqlSelect("   name, diff, image, action_princeple AS actionPrinceple, create_time AS createTime, update_user AS updateUser, series_id AS seriesId, update_time AS updateTime, id, customer_code AS customerCode, is_using AS isUsing, create_user AS createUser ").where(" is_using = 1 AND series_id = {0} and own_type={1}",seriesId,type);
         List<FrAction> frActions = iFrActionService.selectList(wrapper);
         return JsonResult.success(frActions);

    }
    
    /**
     * @TODO:  新增动作
     * @author hujz
     * @date 2018年11月15日 上午11:24:28 
     * 
     * @param frAction
     * @return
     * @throws YJException
     */
    @RequestMapping(value="/addOrUpdate",method={RequestMethod.GET,RequestMethod.POST})
   	public JsonResult addOrUpdateFrAction(@RequestBody FrAction frAction) throws YJException {
    		if(StringUtils.isNotBlank(frAction.getId())){
    			frActionMapper.updateById(frAction);
//    			iFrActionService.updateAllColumnById(frAction);
    		}else{
				frAction.setId(UUIDUtils.generateGUID());
    			frAction.setIsUsing(1);
    			iFrActionService.insert(frAction);
    		}
            return JsonResult.success(frAction);

    }
    
    /**
     * @TODO:  更新动作
     * @author hujz
     * @date 2018年11月15日 上午11:27:25 
     * 
     * @param frAction
     * @return
     * @throws YJException
     */
//    @RequestMapping(value="/updateFrAction",method={RequestMethod.GET,RequestMethod.POST})
//   	public JsonResult updateFrAction( FrAction frAction) throws YJException {
//    	JsonResult result = iFrActionService.updateFrAction(frAction);
//            return result;
//
//    }
    
    
    /**
     * @TODO:  批量更新动作列表  到动作系列
     * @author hujz
     * @date 2018年11月15日 上午11:25:08 
     * 
     * @param frAction
     * @return
     * @throws YJException
     */
    @RequestMapping(value="/batchFrAction",method={RequestMethod.GET,RequestMethod.POST})
   	public JsonResult batchFrAction(String actionIds,String seriesId) throws YJException {
    	JsonResult<?> result = iFrActionService.batchFrAction(actionIds,seriesId);
            return result;

    }
    
    
}

