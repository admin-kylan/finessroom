package com.yj.service.service;

import com.yj.common.result.JsonResult;
import com.yj.dal.dto.CategoryItemDTO;
import com.yj.dal.model.FrCategoryItem;
import com.yj.service.base.BaseService;

/**
 * <p>
 * 场馆项目表 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-27
 */
public interface IFrCategoryItemService extends BaseService<FrCategoryItem> {

    JsonResult getCategoryItemList(String[] ssids);//搜索场馆关联的项目

    JsonResult CategoryItemList(String[] ssids);//搜索场馆关联的项目

    JsonResult selectConsume( String consumeid);//查询项目的设置

    JsonResult setConsumeDTO( CategoryItemDTO consume); //增加项目 设置 ;






}
