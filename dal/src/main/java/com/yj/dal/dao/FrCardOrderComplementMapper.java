package com.yj.dal.dao;

import com.yj.dal.model.FrCardOrderComplement;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员卡补余订单 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-06
 */
public interface FrCardOrderComplementMapper extends BaseMapper<FrCardOrderComplement> {

    List<Map<String, Object>> queryComplementList(FrCardOrderComplement frCardOrderComplement);

    Double getComplementAllMoney(FrCardOrderComplement frCardOrderComplement);

}
