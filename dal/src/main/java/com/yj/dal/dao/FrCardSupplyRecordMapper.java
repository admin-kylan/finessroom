package com.yj.dal.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.yj.dal.model.FrCardSupplyRecord;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员卡补卡、转卡、卡升、续卡记录 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-06
 */
public interface FrCardSupplyRecordMapper extends BaseMapper<FrCardSupplyRecord> {

    List<Map<String, Object>> queryCarDsupplyRecordList(FrCardSupplyRecord frCardSupplyRecord);

    /**
     * 根据会员ID统计续卡金额
     * @param clientId
     * @return
     */
    Double queryCountAll(String clientId);

    List<Map<String,Object>> queryContinueCardLis(Page page, Map<String,Object> map);

    /**
     * 根据指定的会员卡id获取该会员卡的所有续卡记录
     * @param frCardSupplyRecord
     * @return
     */
    List<FrCardSupplyRecord> quereySupplyList(FrCardSupplyRecord frCardSupplyRecord);


    List<FrCardSupplyRecord> updateSupplyRecordTime( Map<String,Object> map);

}
