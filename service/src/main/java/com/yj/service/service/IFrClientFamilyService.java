package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrClientFamily;
import com.yj.service.base.BaseService;

import java.util.List;

/**
 * <p>
 * 客户家庭亲子关系 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-09
 */
public interface IFrClientFamilyService extends BaseService<FrClientFamily> {
    List<FrClientFamily> getFamily(String clientId) throws YJException;

    JsonResult updateFamily(FrClientFamily frClientFamily, String cid) throws YJException;

    JsonResult delFamily(String id);
}
