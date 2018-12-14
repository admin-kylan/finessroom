package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.dal.model.FrFollowPic;
import com.yj.dal.model.FrLatenceFollowPic;
import com.yj.service.base.BaseService;

import java.util.List;

/**
 * <p>
 * 潜在客户跟进图片表 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-10
 */
public interface IFrLatenceFollowPicService extends BaseService<FrLatenceFollowPic> {
    List<FrLatenceFollowPic> queryPricList(String followdId) throws YJException;
}
