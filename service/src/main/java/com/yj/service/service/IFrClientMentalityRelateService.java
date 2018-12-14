package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrClientMentalityRelate;
import com.yj.service.base.BaseService;

import java.util.List;

/**
 * <p>
 * 客户心理状况 内容保存 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-08
 */
public interface IFrClientMentalityRelateService extends BaseService<FrClientMentalityRelate> {

    JsonResult saveMentality(String[] frClientMentalityTypeIds, String cid, String familyLife, String emotionalState) throws YJException;

    List<FrClientMentalityRelate> getMentality();
}
