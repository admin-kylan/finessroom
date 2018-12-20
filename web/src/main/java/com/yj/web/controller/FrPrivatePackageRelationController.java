package com.yj.web.controller;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.yj.dal.model.FrPrivatePackageRelation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.dal.dao.SdaduimMapper;
import com.yj.dal.model.Sdaduim;
import com.yj.dal.model.Shop;
import com.yj.service.service.IFrPrivatePackageRelationService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-28
 */
@RestController
@RequestMapping("/frPrivatePackageRelation")
public class FrPrivatePackageRelationController {
	
	@Resource
	IFrPrivatePackageRelationService iFrPrivatePackageRelationService;


	@RequestMapping("/deleteRelation")
	public JsonResult deleteRelation(String pakageId,String courceId){

		iFrPrivatePackageRelationService.delete(new EntityWrapper<FrPrivatePackageRelation>()
				.where("package_id={0}",pakageId).and("cource_id={0}",courceId));

		return JsonResult.successMessage("操作成功");
	}
	
	@RequestMapping(value="/shopList")
	 public JsonResult invalidList(@RequestParam Map<String, Object> params, HttpServletRequest request) throws YJException {
//	        String code = CookieUtils.getCookieValue(request, "code", true);
	        String code = "003";
	        params.put("code",code);
	        return JsonResult.success(iFrPrivatePackageRelationService.getShopList(params));
	 }

	@RequestMapping(value="/shopSdaduim")
	public JsonResult shopSdaduim(@RequestParam Map<String, Object> params, HttpServletRequest request) throws YJException {
//	        String code = CookieUtils.getCookieValue(request, "code", true);
		String code = "003";
		params.put("code",code);
		return JsonResult.success(iFrPrivatePackageRelationService.shopSdaduim(params));
	}
	
	@Resource
	SdaduimMapper sdaduimMapper;
	
	@RequestMapping(value="/sdaduimList")
	 public JsonResult sdaduimList(@RequestParam Map<String, Object> params, HttpServletRequest request) throws YJException {
	        String code = CookieUtils.getCookieValue(request, "code", true);
//	        String code = "003";
	        params.put("code",code);
	        EntityWrapper<Sdaduim> wrapper = new EntityWrapper<>();
			String property = "ID, CustomerCode, NumId, ShopId, Name, Status, IsHand, Num, IsPrivateEducation, IsBackstage, AutomaticEntry, Module, IsShow, IsConsume, AesEncrypt, checkFinger, UseType, StartTime, EndTime, BalanceNum, TotalNum, CreateId, CreateName, BusinessTypeId, ModelCode, CreateTime";
	        wrapper.setSqlSelect(property).where(" CustomerCode = {0}",code);
	        List<Sdaduim> sdaduimList = sdaduimMapper.selectList(wrapper);
	        return JsonResult.success(sdaduimList);
	 }
	/*
	 *  根据场馆ID获取私教列表
	 */
	@RequestMapping(value="/courceList")
	 public JsonResult courceList(@RequestParam Map<String, Object> params, HttpServletRequest request) throws YJException {
//	        String code = CookieUtils.getCookieValue(request, "code", true);
	        String code = "003";
	        params.put("code",code);
	        return JsonResult.success(iFrPrivatePackageRelationService.courceList(params));
	 }
	
	
	
	
}

