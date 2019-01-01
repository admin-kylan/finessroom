package com.yj.dal.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.util.PageUtil;
import com.yj.dal.model.FrCard;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员卡表 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-04
 */
public interface FrCardMapper extends BaseMapper<FrCard> {

    Integer insertList(@Param("list") List list, @Param("param") FrCard param);

    Integer selectListExisted(@Param("list") List list);
    Integer selectListExisted2(@Param("list") List list);

    Map<String,Object> queryCardType(String cardId);

    List<FrCard> queryUserCardList(Page<FrCard> page,FrCard frCard);

    List<FrCard> queryUserCardList(FrCard frCard);

    List<FrCard> queryByFrCardList(FrCard frCard);

    Map<String,Object> queryCollectionOriginalSet(FrCard frCard);

    Map<String,Object> queryNumCard(Map<String,Object> map1);

    List<Map<String,Object>> queryClientCardList(Map<String,Object> map);

    List<Map<String,Object>> queryUserCardInfoList(Page<FrCard> page,FrCard frCard);

    List<Map<String,Object>> queryUserCardInfoList(FrCard frCard);

    Integer toUpdateStopTime(Map<String,Object> map);

    Integer toUpdateComplement(Map<String,Object> map);

    /**
     * 根据会员卡信息查询出是否有需卡记录及续卡设置
     * @param frCard
     * @return
     */
    Map<String,Object> queryAndCardSupply(FrCard frCard);
}
