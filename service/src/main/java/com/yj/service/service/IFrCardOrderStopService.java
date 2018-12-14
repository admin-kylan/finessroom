package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrCardOrderPayMode;
import com.yj.dal.model.FrCardOrderStop;
import com.yj.service.base.BaseService;

import java.util.List;

/**
 * <p>
 * 会员卡 停止/冻结订单 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
public interface IFrCardOrderStopService extends BaseService<FrCardOrderStop> {

    JsonResult getStopCardListByType(FrCardOrderStop frCardOrderStop) throws YJException;

    JsonResult toInterCardOrderStop(FrCardOrderStop frCardOrderStop, List<FrCardOrderPayMode> list,String imagePath,List<String> imagesList,String shopId)throws YJException;

    JsonResult toAddStopType(FrCardOrderStop frCardOrderStop) throws YJException;

    JsonResult toUpdateStopType(FrCardOrderStop frCardOrderStop,String shopId)throws YJException;

    JsonResult toStopTerminationOK (FrCardOrderStop frCardOrderStop,String shopId)throws YJException;
}
