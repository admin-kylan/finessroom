package com.yj.dal.dao;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrCardOrderTransfer;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员卡 转让订单 Mapper 接口
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-22
 */
public interface FrCardOrderTransferMapper extends BaseMapper<FrCardOrderTransfer> {

    List<Map<String,Object>> quereyTransferList(FrCardOrderTransfer frCardOrderTransfer);

}
