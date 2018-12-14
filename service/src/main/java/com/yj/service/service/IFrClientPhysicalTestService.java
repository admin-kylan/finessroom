package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.dto.PhysicalDateDTO;
import com.yj.dal.model.FrClientPhysicalTest;
import com.yj.service.base.BaseService;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 体能测试 内容保存 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-16
 */
public interface IFrClientPhysicalTestService extends BaseService<FrClientPhysicalTest> {

    List<FrClientPhysicalTest> getPhysical(String cid, Date date) throws YJException;

    JsonResult savePhysical(List<FrClientPhysicalTest> frClientPhysicalTests)throws YJException;

    List<PhysicalDateDTO> getSaveDate(String cid) throws YJException;
}
