package com.yj.dal.dao;

import com.yj.dal.model.FrFollowPic;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 跟进表的图片 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-01
 */
public interface FrFollowPicMapper extends BaseMapper<FrFollowPic> {

    List<FrFollowPic> queryPricList(FrFollowPic frFollowPic);

}
