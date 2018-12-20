package com.yj.service.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.yj.dal.dao.SdaduimMapper;
import com.yj.dal.model.Sdaduim;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.dal.dao.FrPrivatePackageRelationMapper;
import com.yj.dal.dao.ShopMapper;
import com.yj.dal.model.FrPrivateCource;
import com.yj.dal.model.FrPrivatePackageRelation;
import com.yj.dal.model.Shop;
import com.yj.service.base.BaseServiceImpl;
import com.yj.service.service.IFrPrivatePackageRelationService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-28
 */
@Service
public class FrPrivatePackageRelationServiceImpl extends BaseServiceImpl<FrPrivatePackageRelationMapper, FrPrivatePackageRelation> implements IFrPrivatePackageRelationService {

	@Resource
	FrPrivatePackageRelationMapper frPrivatePackageRelationMapper;
	
	@Resource
	ShopMapper shopMapper;
	@Resource
	SdaduimMapper sdaduimMapper;

	@Override
	public Object shopSdaduim(Map<String, Object> params) {
		String code = String.valueOf(params.get("code"));
		if (StringUtils.isEmpty(code)) {
			return null;
		}

		EntityWrapper<Shop> wrapper = new EntityWrapper<>();
		wrapper.where(" CustomerCode = {0}",code);
		List<Shop> shopList = shopMapper.selectList(wrapper);

		Map<String,Map<String,Object>> data = new HashMap<>();
		shopList.stream().forEach((shop) -> {
			if(data.get(shop.getCityId()) == null){
				Map<String,Object> node = new HashMap<>();
				node.put("value",shop.getCityId());
				node.put("text",shop.getCityName());
				node.put("selected",false);
				node.put("children",new ArrayList<>());
				data.put(shop.getCityId(),node);
			}

			Map<String,Object> node = new HashMap<>();
			node.put("value",shop.getId());
			node.put("text",shop.getShopName());
			node.put("selected",false);
			((List)data.get(shop.getCityId()).get("children")).add(node);

		});
		final List<Map<String,Object>> result = new ArrayList<>();
		data.entrySet().stream().forEach((keySet) ->{
			result.add(keySet.getValue());
		});

		return result;
	}

	@Override
	public Object getShopList(Map<String, Object> params) throws YJException {
		String code = String.valueOf(params.get("code"));
		if (StringUtils.isEmpty(code)) {
			throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
		}
		
		EntityWrapper<Shop> wrapper = new EntityWrapper<>();
		String property = "ID AS id, CustomerCode, ShopName, CityName, ShopTel, ShopAddress, ProvinceId, ProvinceName, CityId, AreaId, AreaName, PlaceType, Status, ShopStatus, AreaCount, EmployCount, SdadiumCount, Money, MerchantId, ManageList, OtherProject, ExtendField1, CreateID, SimpleAddrs, CreateName, ExtendField2, ExtendField3, CreateTime";
        wrapper.setSqlSelect(property).where(" CustomerCode = {0}",code);
        List<Shop> shopList = shopMapper.selectList(wrapper);
//		List<Shop> shopList = frPrivatePackageRelationMapper.getShopList(code);
		final List<Map<String,Object>> tree = new ArrayList<>();

		shopList.stream().forEach((data) -> {
			Map<String,Object> node = new HashMap<>();
			Map<String,Object> state = new HashMap<>();
			state.put("expanded",false);
			node.put("text",data.getShopName());
			node.put("shopId",data.getId());
			node.put("state",state);
			EntityWrapper<Sdaduim> wrap = new EntityWrapper<>();
			String prop= "ID, CustomerCode, NumId, ShopId, Name, Status, IsHand, Num, IsPrivateEducation, IsBackstage, AutomaticEntry, Module, IsShow, IsConsume, AesEncrypt, checkFinger, UseType, StartTime, EndTime, BalanceNum, TotalNum, CreateId, CreateName, BusinessTypeId, ModelCode, CreateTime";
			wrap.setSqlSelect(prop).where(" ShopId = {0}",data.getId()).and("Status = {0}",0);
			List<Sdaduim> sdaduimList = sdaduimMapper.selectList(wrap);
			List<Map<String,Object>> nodes = new ArrayList<>();
			sdaduimList.stream().forEach((sd)->{
				Map<String,Object> sdNode = new HashMap<>();
				sdNode.put("text",sd.getName());
				sdNode.put("sdaduimId",sd.getId());
				nodes.add(sdNode);
			});
			node.put("nodes",nodes);
			tree.add(node);
		});

		return tree;
	}


	@Override
	public Object courceList(Map<String, Object> params) throws YJException {
		
		String code = String.valueOf(params.get("code"));
		
//		String code = params.get("code");
		
		if (StringUtils.isEmpty(code)) {
			throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
		}
		List<FrPrivateCource> courceList = frPrivatePackageRelationMapper.courceList(params);
		return courceList;
	}
}
