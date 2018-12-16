package com.yj.dal.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.util.PageUtil;
import com.yj.dal.model.FrCard;
import com.yj.dal.model.FrCardOrderInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员卡订单记录 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-26
 */
public interface FrCardOrderInfoMapper extends BaseMapper<FrCardOrderInfo> {

    List<FrCardOrderInfo> getCardOrederList(Page<FrCard> page, PageUtil<FrCard> pageUtil);

    Map<String, Object> queryOrderInfoPrice(FrCardOrderInfo frCardOrderInfo);

    Map<String, Object> getBlackCardData(FrCardOrderInfo frCardOrderInfo);

    Double queryOrderInfoAllPrice(FrCardOrderInfo frCardOrderInfo);

    Map<String, Object> queryCardAndType(FrCardOrderInfo frCardOrderInfo);

    /**
     * 获取此会员卡的所有订单记录，新购，续卡
     * @param map
     * @return
     */
    List<FrCardOrderInfo> queryBrackInfoList(Map<String,Object> map);
}
