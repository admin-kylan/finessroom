package com.yj.dal.dao;

import com.yj.dal.model.FrShopCtypeConsumePladdset;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-10
 */
public interface FrShopCtypeConsumePladdsetMapper extends BaseMapper<FrShopCtypeConsumePladdset> {

    List<FrShopCtypeConsumePladdset> selectCtypeConsumePladdset( String consumeId);



}
