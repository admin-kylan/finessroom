package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrClientMotionRelate;
import com.yj.service.base.BaseService;

import java.util.List;

/**
 * <p>
 * 客户运动档案  内容保存 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-02
 */
public interface IFrClientMotionRelateService extends BaseService<FrClientMotionRelate> {

    JsonResult saveSports(String[] frClientSportTypeIds, String cid, String sportType)throws YJException;

    List<FrClientMotionRelate> getSports(String clientId);
}
