package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrClient;
import com.yj.service.base.BaseService;

import javax.servlet.http.HttpServletRequest;

public interface IFrPersonalDetailsService extends BaseService<FrClient> {
    JsonResult getPersonalDetails(String id) throws YJException;

    JsonResult updatePersonalDetails(FrClient frClient,HttpServletRequest request)throws YJException;
}
