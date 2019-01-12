package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrCardTypeSplitSet;
import com.yj.dal.model.FrCardTypeSplitSetDd;
import com.yj.dal.dao.FrCardTypeSplitSetDdMapper;
import com.yj.service.service.IFrCardTypeSplitSetDdService;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员卡分期付款设置 详细 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
@Service
public class FrCardTypeSplitSetDdServiceImpl extends BaseServiceImpl<FrCardTypeSplitSetDdMapper, FrCardTypeSplitSetDd> implements IFrCardTypeSplitSetDdService {


    @Override
    public FrCardTypeSplitSetDd get(String id) throws YJException {
        FrCardTypeSplitSetDd frCardTypeSplitSetDd = selectOne(new EntityWrapper<FrCardTypeSplitSetDd>().where("id={0}",id));
        return frCardTypeSplitSetDd;
    }

    @Override
    public JsonResult update(FrCardTypeSplitSetDd frCardTypeSplitSetDd) throws YJException {
        boolean flag = updateById(frCardTypeSplitSetDd);
        if(flag){
            return  JsonResult.successMessage("修改成功");
        }
        return JsonResult.fail();
    }
}
