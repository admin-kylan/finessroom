package com.yj.service.service;

import com.yj.common.result.JsonResult;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.dto.ShopListDTO;
import com.yj.dal.model.Shop;
import com.yj.service.base.BaseService;

import java.util.List;

/**
 * <p>
 * 门店表 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-25
 */
public interface IShopService extends BaseService<Shop> {
	JsonResult getFrCardType(String code);

	JsonResult getStoreList(Integer type,String code);

	JsonResult<List<ShopListDTO>> selectShopList(String customerCode);

    Boolean updateWhereShop(List<ShopListDTO> list) throws YJException;

	JsonResult	getShopSdaduimList(String[] sids);

	JsonResult getIsUsingIsTrue(String code);

	JsonResult getShopList(String code);

	JsonResult getByShopIdList(String code,String shopId,Integer type);

}
