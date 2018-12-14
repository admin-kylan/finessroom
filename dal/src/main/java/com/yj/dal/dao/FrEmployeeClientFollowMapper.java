package com.yj.dal.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.util.PageUtil;
import com.yj.dal.model.FrEmployeeClientFollow;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 现有会员跟进记录 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-30
 */
public interface FrEmployeeClientFollowMapper extends BaseMapper<FrEmployeeClientFollow> {

    List<Map<String,Object>> selectFrEmployeeClienFollowList(Page<FrEmployeeClientFollow> page, FrEmployeeClientFollow frEmployeeClientFollow);

}
