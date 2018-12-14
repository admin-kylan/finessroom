package com.yj.service.service;

import com.yj.common.result.JsonResult;
import com.yj.common.exception.YJException;
import com.yj.dal.dto.ShopListDTO;
import com.yj.dal.model.FrStore;
import com.yj.service.base.BaseService;

import java.util.List;

/**
 * <p>
 * 门店表 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-04
 */
public interface IFrStoreService extends BaseService<FrStore> {

    List<ShopListDTO> selectStoreList();

    Boolean updateList(List<ShopListDTO> dtos) throws YJException;


  List getStoreCategoryList(Integer[] sid);//根据门店搜索可以使用 的场馆

}
