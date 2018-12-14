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

    Integer queryNumCard(Map<String,Object> map1);
}
