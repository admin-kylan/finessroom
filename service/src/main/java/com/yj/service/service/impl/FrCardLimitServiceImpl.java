package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.StringUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.model.FrCardLimit;
import com.yj.dal.dao.FrCardLimitMapper;
import com.yj.service.service.IFrCardLimitService;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * <p>
 * 会员卡使用限定 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-31
 */
@Service
public class FrCardLimitServiceImpl extends BaseServiceImpl<FrCardLimitMapper, FrCardLimit> implements IFrCardLimitService {

    /**
     * 添加使用限定
     * @param frCardLimit
     * @param code
     * @return
     */
    @Override
    public JsonResult addCardLimit(FrCardLimit frCardLimit, String code)throws YJException {
        if(StringUtils.isEmpty(code)){
            throw  new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        frCardLimit.setCustomerCode(code);
        frCardLimit.setId(UUIDUtils.generateGUID());
        Integer b = baseMapper.insert(frCardLimit);
        if(b < 1){
            return JsonResult.failMessage("添加失败");
        }
        return JsonResult.success(SqlHelper.retBool(b));
    }

    /**
     * 获取指定会员卡使用限定列表（无分页）
     * @param code
     * @param cardId
     * @return
     */
    @Override
    public JsonResult getCardLimitList(String code, String cardId) {
       List<FrCardLimit> list =  baseMapper.selectList(
                new EntityWrapper<FrCardLimit>().where("CustomerCode = {0}",code)
                .and("card_id = {0}",cardId).and("is_using={0}",1)
        );
        return JsonResult.success(list);
    }

    /**
     * 删除指定使用限定
     * @param code
     * @param id
     * @return
     */
    @Override
    @Transactional
    public JsonResult deleteCardLimit(String code, String id) {
        FrCardLimit frCardLimit = new FrCardLimit();
        frCardLimit.setUsing(false);
        Integer b = baseMapper.update(frCardLimit,
                new EntityWrapper<FrCardLimit>().where("id = {0}",id)
                .and("CustomerCode = {0}",code).and("is_using={0}",1)
        );
        if(b < 1){
            return JsonResult.failMessage("删除失败");
        }
        return JsonResult.success();
    }
}
