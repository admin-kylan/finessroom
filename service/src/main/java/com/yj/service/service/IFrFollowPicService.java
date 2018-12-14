package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.util.PageUtils;
import com.yj.dal.model.FrFollowPic;
import com.yj.service.base.BaseService;

import java.util.List;

/**
 * <p>
 * 跟进表的图片 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-01
 */
public interface IFrFollowPicService extends BaseService<FrFollowPic> {

    List<FrFollowPic> queryPricList(String followdId) throws YJException;

}
