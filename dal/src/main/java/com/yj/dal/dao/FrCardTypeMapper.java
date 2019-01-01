package com.yj.dal.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yj.dal.model.FrCardType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 卡类型表 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-12
 */
public interface FrCardTypeMapper extends BaseMapper<FrCardType> {

    List<FrCardType> selectCardTypetByIdAndName(@Param("shopId") String shopId, @Param("cardTypeId") String cardTypeId , @Param("cardTypeName")String cardTypeName, @Param("pId")String pId, @Param("typeSetState")Integer typeSetState);

    Integer selectCardTypetCount(@Param("shopId") String shopId, @Param("cardTypeId") String cardTypeId , @Param("cardTypeName")String cardTypeName, @Param("pId")String pId, @Param("typeSetState")Integer typeSetState);

    List<FrCardType> queryByShopIdList(Map<String,Object> map);

    /**
     * 根据会员卡ID 获取卡类型
     * @param cardId
     * @param CustomerCode
     * @return
     */
    FrCardType queryByCardId(@Param("cardId") String cardId , @Param("CustomerCode")String CustomerCode);

    List<FrCardType> getAllShopIdList(Map<String,Object> map);

    List<Map<String,Object>> getCardTypeByShopIdList(Map<String,Object> map);
}
