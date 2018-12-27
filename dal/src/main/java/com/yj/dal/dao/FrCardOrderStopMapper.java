package com.yj.dal.dao;

import com.yj.dal.model.FrCardOrderStop;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yj.dal.model.FrCardOriginalSet;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员卡 停止/冻结订单 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
public interface FrCardOrderStopMapper extends BaseMapper<FrCardOrderStop> {

    List<Map<String,Object>> getStopCardListByType(FrCardOrderStop frCardOrderStop);


    List<FrCardOrderStop> queryStopTimeList(String nowTime);

    Integer toUpdateTimeCard(FrCardOrderStop frCardOrderStop);
}
