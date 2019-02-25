package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.PageUtil;
import com.yj.common.util.PageUtils;
import com.yj.dal.model.FrEmployeeClientFollow;
import com.yj.service.base.BaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 现有会员跟进记录 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-30
 */
public interface IFrEmployeeClientFollowService extends BaseService<FrEmployeeClientFollow> {

    PageUtils queryEmployeeClientList(PageUtil<FrEmployeeClientFollow> pageUtil) throws YJException;

    boolean toInsertOrUpdate(FrEmployeeClientFollow frEmployeeClientFollow) throws YJException;

    boolean toInertAndUpdatImage(FrEmployeeClientFollow frEmployeeClientFollow, List<String> imageList,String imagePath) throws YJException;

    JsonResult addFollow(List<String> imagesList, HttpServletRequest request, FrEmployeeClientFollow frEmployeeClientFollow);

    JsonResult getFollow(String id);
}
