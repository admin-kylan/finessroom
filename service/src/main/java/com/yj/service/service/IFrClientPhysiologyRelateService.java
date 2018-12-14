package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrClientPhysiologyRelate;
import com.yj.service.base.BaseService;

import java.util.List;

/**
 * <p>
 * 客户生理状况 内容保存 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-08
 */
public interface IFrClientPhysiologyRelateService extends BaseService<FrClientPhysiologyRelate> {
    JsonResult savePsychology(String[] frClientPsychologyTypeIds, String cid, String medicalHistory, String drug, String theHormone, String bloodPressure,String menstruation) throws YJException;

    List<FrClientPhysiologyRelate> getPsychology();
}
