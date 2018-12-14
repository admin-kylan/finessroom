package com.yj.dal.dao;

import com.yj.dal.model.FrFollowPic;
import com.yj.dal.model.FrLatenceFollowPic;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 潜在客户跟进图片表 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-10
 */
public interface FrLatenceFollowPicMapper extends BaseMapper<FrLatenceFollowPic> {
    List<FrLatenceFollowPic> queryPricList(FrLatenceFollowPic frFollowPic);
}
