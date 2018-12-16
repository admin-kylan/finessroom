package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.PersonnelInfo;
import com.yj.service.base.BaseService;

import java.util.List;

/**
 * <p>
 * 人员信息表 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-09
 */
public interface IPersonnelInfoService extends BaseService<PersonnelInfo> {

    JsonResult getMarketUserList(String shopId,String code);

    List<PersonnelInfo> getServicePersonnel(Integer userType,String customerCode)throws YJException;

    List<PersonnelInfo> getUnallocatedPersonnel(String rid)throws YJException;
}
