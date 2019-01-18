package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrCardTypeSplitSet;
import com.yj.service.base.BaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员卡分期付款设置 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
public interface IFrCardTypeSplitSetService extends BaseService<FrCardTypeSplitSet> {

    /**
     * 新增会员卡分期信息
     * @param map
     * @return
     * @throws YJException
     */
    JsonResult insert(Map<String, Object> map,FrCardTypeSplitSet frCardTypeSplitSet)throws YJException ;

    /**
     * 根据id更新修改会员卡分期信息
     * @param map
     * @return
     * @throws YJException
     */
    JsonResult update(HttpServletRequest request,Map<String, Object> map)throws YJException ;

    /**
     * 根据id删除数据
     * @param id
     * @return
     * @throws YJException
     */
    JsonResult delete(String id)throws YJException;

    /**
     * 会员卡分期详情
     * @param id
     * @return
     * @throws YJException
     */
    JsonResult get(String id)throws YJException;

    /**
     * 是否启用
     * @param frCardTypeSplitSet
     * @return
     * @throws YJException
     */
    JsonResult isUsing(FrCardTypeSplitSet frCardTypeSplitSet)throws YJException;
}
