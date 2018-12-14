package com.yj.dal.dao;

import com.yj.dal.model.FrClientPic;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 客户图片表 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-21
 */
public interface FrClientPicMapper extends BaseMapper<FrClientPic> {
    FrClientPic getClientImg(String id);

}
