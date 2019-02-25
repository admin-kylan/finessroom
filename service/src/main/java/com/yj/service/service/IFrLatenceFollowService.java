package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.PageUtil;
import com.yj.common.util.PageUtils;
import com.yj.dal.model.FrLatenceFollow;
import com.yj.dal.model.FrLatenceFollow;
import com.yj.service.base.BaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 潜在会员跟进记录 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-20
 */
public interface IFrLatenceFollowService extends BaseService<FrLatenceFollow> {
    PageUtils queryLatenceClientList(PageUtil<FrLatenceFollow> pageUtil) throws YJException;

    boolean toInsertOrUpdate(FrLatenceFollow frLatenceFollow) throws YJException;

    boolean toInertAndUpdatImage(FrLatenceFollow frLatenceFollow, List<String> imageList, String imagePath) throws YJException;

    JsonResult addFollow(List<String> imagesList, HttpServletRequest request, FrLatenceFollow frLatenceFollow);

    JsonResult getFollow(String id);
}
