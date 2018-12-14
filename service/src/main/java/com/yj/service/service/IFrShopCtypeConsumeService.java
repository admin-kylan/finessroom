package com.yj.service.service;

import com.yj.dal.model.FrShopCtypeConsume;
import com.yj.service.base.BaseService;
import org.springframework.boot.configurationprocessor.json.JSONException;

import java.util.Map;

/**
 * <p>
 * 会员卡类型-门店-场馆-项目关系表（消费门店会员卡权益设置） 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-29
 */
public interface IFrShopCtypeConsumeService extends BaseService<FrShopCtypeConsume> {

     //提交   会员卡类型-门店-场馆-项目关系表
     Boolean addShopCtypeConsume(Map<String, Object> map, String cardTypeId) throws JSONException;

}


