package com.yj.service.service.impl;

import com.yj.common.result.JsonResult;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.model.FrCardTypeSplitSet;
import com.yj.dal.dao.FrCardTypeSplitSetMapper;
import com.yj.service.service.IFrCardTypeSplitSetService;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员卡分期付款设置 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
@Service
public class FrCardTypeSplitSetServiceImpl extends BaseServiceImpl<FrCardTypeSplitSetMapper, FrCardTypeSplitSet> implements IFrCardTypeSplitSetService {

    @Autowired
    FrCardTypeSplitSetMapper frCardTypeSplitSetMapper;

    @Override
    public JsonResult insertCardTypeSplit(FrCardTypeSplitSet frCardTypeSplitSet){
        frCardTypeSplitSet.setId(UUIDUtils.generateGUID());
        frCardTypeSplitSetMapper.insert(frCardTypeSplitSet);
//        if (i == 0){
            return JsonResult.successMessage("添加成功");
//        }
//        return JsonResult.failMessage("添加失败");
    }
}
