package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.dal.model.FrCardOriginalSet;
import com.yj.dal.model.FrCardType;
import com.yj.service.base.BaseService;

import java.util.Map;

/**
 * <p>
 * 会员卡购买前，后台设置保存 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-10
 */
public interface IFrCardOriginalSetService extends BaseService<FrCardOriginalSet> {

    /**
     * 关联到会员卡获取单条数据(关联获取会员卡号)
     * @return
     */
    Map<String,Object> querySelectOneFrCard(FrCardOriginalSet frCardOriginalSet)throws YJException;

    /**
     * 初始化卡前设置信息
     * @param frCardOriginalSet
     * @param frCardType
     */
    public void initFrCardOrgiginalSet( FrCardOriginalSet frCardOriginalSet,FrCardType frCardType);

}
