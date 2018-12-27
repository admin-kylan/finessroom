package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrCardOrderAllotSet;
import com.yj.dal.model.FrCardOrderComplement;
import com.yj.dal.model.FrCardOrderPayMode;
import com.yj.service.base.BaseService;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;

/**
 * <p>
 * 会员卡补余订单 服务类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-06
 */
public interface IFrCardOrderComplementService extends BaseService<FrCardOrderComplement> {

    List<Map<String, Object>> getComplementList(FrCardOrderComplement frCardOrderComplement)throws YJException;

    JsonResult toAddComplemen(FrCardOrderComplement frCardOrderComplement, List<FrCardOrderPayMode>  frCardOrderPayModes, List<FrCardOrderAllotSet> frCardOrderAllotSetList, String orderSetIdList)throws YJException;

    boolean toUpdateOrderStart(FrCardOrderComplement frCardOrderComplement)throws YJException;

    /**
     * 获取补余的全部金额（已付款，审核通过，复核通过）
     * @param frCardOrderComplement
     * @return
     * @throws YJException
     */
    Double getComplementAllMoney (FrCardOrderComplement frCardOrderComplement)throws YJException;

}
