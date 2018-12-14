package com.yj.dal.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.yj.dal.model.FrEmployeeClientFollow;
import com.yj.dal.model.FrLatenceFollow;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 潜在会员跟进记录 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-20
 */
public interface FrLatenceFollowMapper extends BaseMapper<FrLatenceFollow> {
    Date getFirstFollow(String customerCode);
        List<Map<String,Object>> selectFrLatenceClienFollowList(Page<FrLatenceFollow> page, FrLatenceFollow frLatenceFollow);
}
