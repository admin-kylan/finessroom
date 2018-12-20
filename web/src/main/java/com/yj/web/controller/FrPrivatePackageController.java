package com.yj.web.controller;


import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.yj.common.util.CookieUtils;
import com.yj.dal.dto.FrPrivatePackageDTO2;
import com.yj.dal.model.FrPrivatePackageRelation;
import com.yj.service.service.IFrPrivateCourceRelationService;
import com.yj.service.service.IFrPrivatePackageRelationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.StringUtils;
import com.yj.dal.dto.FrPrivatePackageDTO;
import com.yj.dal.model.FrPrivatePackage;
import com.yj.service.service.IFrPrivatePackageService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-18
 */
@RestController
@RequestMapping("/frPrivatePackage")
public class FrPrivatePackageController {
	
	
	
	@Autowired
	IFrPrivatePackageService iFrPrivatePackageService;
	@Autowired
	IFrPrivatePackageRelationService iFrPrivatePackageRelationService;


	@RequestMapping("/delete")
    public 	JsonResult deletePackage(String id){

		FrPrivatePackage packge = new FrPrivatePackage();
		packge.setIsUsing(0);
		packge.setUpdateTime(new Date());

		iFrPrivatePackageService.update(packge,new EntityWrapper<FrPrivatePackage>().where("id={0}",id));
		return JsonResult.successMessage("操作成功");
	}
	/**
	 * 功能：训练计划和训练 系列  列表
	 *	   	
	 *  <p>
	 *		创建日期：2018年11月15日下午9:03:34
	 *  </p>
	 * @param request
	 * @return
	 * @throws YJException
	 */
	 @RequestMapping(value="/list",method={RequestMethod.GET,RequestMethod.POST})
	 public JsonResult list(@RequestParam Map<String, Object> params, HttpServletRequest request) throws YJException {
		 String code = CookieUtils.getCookieValue(request, "code", true);
		 params.put("code",code);

         return JsonResult.success(iFrPrivatePackageService.queryPage(params));
	 }
	 
	 @RequestMapping(value = "/getById")
	 public JsonResult getInfoById(String id){
		 Map<String, Object> params = new HashMap<>();
		 params.put("id",id);

		 return JsonResult.success(iFrPrivatePackageService.findProjectInfo(params));
	 }
	 
	 /**
	  * 功能： 更新或者新增
	  *	   	
	  *  <p>
	  *		创建日期：2018年11月15日下午9:10:29
	  *  </p>
	  * @return
	  * @throws YJException
	  */
	
	 @RequestMapping(value="/addOrUpdate",method={RequestMethod.GET,RequestMethod.POST})
	 public JsonResult addOrUpdate(@RequestBody FrPrivatePackageDTO2 dto,HttpServletRequest request) throws YJException {
		 String code = CookieUtils.getCookieValue(request, "code", true);
		 FrPrivatePackage packge = new FrPrivatePackage();

		 BeanUtils.copyProperties(dto,packge);

	 	if(StringUtils.isEmpty(dto.getId())){
			packge.setIsUsing(1);
			packge.setCreateTime(new Date());
			packge.setUpdateTime(new Date());
			packge.setCustomerCode(code);
			iFrPrivatePackageService.insert(packge);
			System.out.println(packge.getId());
			this.saveRelation(dto,packge.getId(),code);

			return JsonResult.successMessage("操作成功");
		}else {
			iFrPrivatePackageService.update(packge,new EntityWrapper<FrPrivatePackage>().where("id={0}",packge.getId()));
			//跟新关系
			iFrPrivatePackageRelationService.delete(new EntityWrapper<FrPrivatePackageRelation>().where("package_id={0}",packge.getId()));
			this.saveRelation(dto,packge.getId(),code);
			return JsonResult.successMessage("操作成功");
		}
	 }

	 private void saveRelation(FrPrivatePackageDTO2 dto,String packageId,String code){
		 if(dto.getCanUsingItem() != null && dto.getCanUsingItem().size() > 0){
			 dto.getCanUsingItem().stream().forEach((data) -> {
				 FrPrivatePackageRelation relation  = new FrPrivatePackageRelation();
				 relation.setSdaduimId(data.getSdaduimId());
				 relation.setCourceId(data.getCourseId());
				 relation.setPackageId(packageId);
				 relation.setCourceCount(data.getClassCount());
				 relation.setDayLimitCount(data.getLimitCount());
				 relation.setIsUsing(1);
				 relation.setCreateTime(new Date());
				 relation.setUpdateTime(new Date());
				 relation.setCustomerCode(code);
				 iFrPrivatePackageRelationService.insert(relation);
			 });
		 }
	 }
	 

}

