package com.yj.dal.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.util.PageUtil;
import com.yj.dal.model.FrChildCard;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-19
 */
public interface FrChildCardMapper extends BaseMapper<FrChildCard> {

    List<Map<String,Object>> selectChildCardList(FrChildCard frChildCard);

    Double queryChildCardShareNum(FrChildCard frChildCard);

    /**
     * 根据父类卡号获取所有子卡的剩余储值金额
     * @param frChildCard
     * @return
     */
    List<Map<String,Object>> queryChildCardAmt(FrChildCard frChildCard);
}
