package com.yj.service.service.impl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.PageUtils;
import com.yj.common.util.Query;
import com.yj.dal.dao.FrActionMapper;
import com.yj.dal.model.FrAction;
import com.yj.dal.model.FrCard;
import com.yj.service.base.BaseServiceImpl;
import com.yj.service.service.IFrActionService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-14
 */
@Service
public class FrActionServiceImpl extends BaseServiceImpl<FrActionMapper, FrAction> implements IFrActionService {
	
	
	
	@Override
	public PageUtils queryPage(Map<String, Object> params) throws YJException {
//		 if (StringUtils.isEmpty(code)) {
//	            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
//	        }
		
		String seriesId = (String) params.get("seriesId");
	        Page page = this.selectPage(
	                new Query<FrAction>(params).getPage(),
	                new EntityWrapper<FrAction>()
	                        .where("is_using = 1 AND series_id = {0}",seriesId)
	                        .orderBy("create_time desc")
	                        .setSqlSelect(" * ")
	        );
	        return new PageUtils(page);
	}

	@Override
	public JsonResult updateFrAction(FrAction frAction) {
		
		Wrapper<FrAction> entityWrapper = new EntityWrapper<>();
		entityWrapper.where("id = {0}",frAction.getId());
		boolean update = this.update(frAction, entityWrapper);
		if(update){
			return JsonResult.successMessage("更新成功");
		}else{
			return JsonResult.failMessage("更新失败");
		}
	}

	@Override
	public JsonResult batchFrAction(String actionIds, String seriesId) {
		
		 EntityWrapper entityWrapper = new EntityWrapper<>();
		 entityWrapper.setSqlSelect(" * ").where("actionIds = {0} ",actionIds);
		 String[] arrIds = actionIds.split(",");
		 Collection<? extends Serializable> ids = Arrays.asList(arrIds);
		List<FrAction> list = this.selectBatchIds(ids);
		boolean updateAllColumnById = false;
		for (FrAction frAction : list) {
			frAction.setSeriesId(seriesId);
			frAction.setUpdateTime(new Date());
			updateAllColumnById = this.updateAllColumnById(frAction);
		}
		if(updateAllColumnById){
			return JsonResult.successMessage("批量更新动作成功");
		}else{
			return JsonResult.failMessage("批量更新动作失败");
		}
	}

}
