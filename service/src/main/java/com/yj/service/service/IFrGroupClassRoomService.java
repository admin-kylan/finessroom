package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.dal.model.FrGroupClassRoom;
import com.yj.service.base.BaseService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-08
 */
public interface IFrGroupClassRoomService extends BaseService<FrGroupClassRoom> {

    Object queryPage(Map<String, Object> params)throws YJException;
}
