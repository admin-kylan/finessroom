package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.PageUtil;
import com.yj.common.util.PageUtils;
import com.yj.dal.model.FrChildCard;
import com.yj.service.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员卡表 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-04
 */
public interface IFrChildCardService extends BaseService<FrChildCard> {

    List<Map<String,Object>> selectChildCardList(FrChildCard frChildCard) throws YJException;

    FrChildCard selectChildCardOne(FrChildCard frChildCard);

    boolean saveChildCard(FrChildCard frChildCard) throws YJException;

    /**
     * 获取子卡下所有分享出去的权益
     * @param frChildCard
     * @return
     * @throws YJException
     */
    Double queryChildCardShareNum(FrChildCard frChildCard) throws YJException;

    /**
     * 获取所有子卡的剩余金额，剩余权益
     * @param frChildCard
     * @return
     * @throws YJException
     */
    Map<String,Double> queryChildCardAmt(FrChildCard frChildCard)throws YJException;

    /**
     * 获取所有子卡的剩余金额，剩余权益
     * @param frChildCard
     * @return
     * @throws YJException
     */
    List<Map<String,Object>>  queryChildCardAmtList(FrChildCard frChildCard)throws YJException;
}

