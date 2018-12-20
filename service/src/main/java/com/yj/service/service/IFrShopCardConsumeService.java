package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.dal.model.FrShopCardConsume;
import com.yj.service.base.BaseService;
import org.springframework.boot.bind.YamlConfigurationFactory;

/**
 * <p>
 * 会员卡类型-门店-场馆-项目关系表（消费门店会员卡权益设置） 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-19
 */
public interface IFrShopCardConsumeService extends BaseService<FrShopCardConsume> {

    /**
     * 根据卡种初始化会员卡的使用范围及悬疑
     * @param cardTypeId
     * @param cardId
     * @return
     */
      boolean queryByCardTypeId(String cardTypeId,String cardId,String code)throws YJException;
}
