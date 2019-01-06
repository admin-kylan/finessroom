package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CommonUtils;
import com.yj.common.util.StringUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.model.FrCardLimit;
import com.yj.dal.dao.FrCardLimitMapper;
import com.yj.service.service.IFrCardLimitService;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
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
        if(CommonUtils.CARD_LIMIT_1 == frCardLimit.getType()){
            FrCardLimit frCardLimit1 = this.selectOne(new EntityWrapper<FrCardLimit>().where("is_using=1")
                    .and("CustomerCode={0}",code).and("client_id={0}",frCardLimit.getClientId())
                    .and("type={0}",CommonUtils.CARD_LIMIT_1));
            if(frCardLimit1 != null){
                frCardLimit.setId(frCardLimit1.getId());
            }
        }
        Integer b = null;
        if(!StringUtils.isEmpty(frCardLimit.getId())){
            b = baseMapper.update(frCardLimit,new EntityWrapper<FrCardLimit>().where("id={0}",frCardLimit.getId())
                    .and("is_using=1").and("CustomerCode={0}",code)
                    .and("client_id={0}",frCardLimit.getClientId())
                    .and("type={0}",CommonUtils.CARD_LIMIT_1));
        }
        if(StringUtils.isEmpty(frCardLimit.getId())){
            frCardLimit.setCustomerCode(code);
            frCardLimit.setId(UUIDUtils.generateGUID());
            b = baseMapper.insert(frCardLimit);
        }
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
    public JsonResult deleteCardLimit(String code, String id,String clientId) {
        FrCardLimit frCardLimit = new FrCardLimit();
        frCardLimit.setUsing(false);
        Integer b = baseMapper.update(frCardLimit,
                new EntityWrapper<FrCardLimit>().where("id = {0}",id)
                .and("CustomerCode = {0}",code).and("is_using={0}",1)
                        .and("client_id={0}",clientId)
        );
        if(b < 1){
            return JsonResult.failMessage("删除失败");
        }
        return JsonResult.success();
    }


    @Override
    public FrCardLimit getLimitInfoByClient(String code, String clientId) throws YJException {
        FrCardLimit frCardLimit = null;
        if(StringUtils.isEmpty(code)){
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        if(StringUtils.isEmpty(clientId)){
            throw new YJException(YJExceptionEnum.CLIENTID_NOT_FOUND);
        }
        FrCardLimit  frCardLimit1 = this.selectOne(
                new EntityWrapper<FrCardLimit>().where("is_using=1").and("CustomerCode={0}",code)
                        .and("client_id={0}",clientId).and("type={0}",CommonUtils.CARD_LIMIT_1));
        if(frCardLimit1 != null){
            frCardLimit = new FrCardLimit();
            frCardLimit.setId(UUIDUtils.generateGUID());
            frCardLimit.setType(CommonUtils.CARD_LIMIT_2);
            frCardLimit.setUsing(true);
            frCardLimit.setUseName(frCardLimit1.getUseName());
            frCardLimit.setUsePhone(frCardLimit1.getUsePhone());
            frCardLimit.setUsePasswd(frCardLimit1.getUsePasswd());
            frCardLimit.setUseLimit1(frCardLimit1.getUseLimit1());
            frCardLimit.setUseLimit2(frCardLimit1.getUseLimit2());
            frCardLimit.setUseLimit3(frCardLimit1.getUseLimit3());
            frCardLimit.setUseLimit4(frCardLimit1.getUseLimit4());
            frCardLimit.setUseLimit5(frCardLimit1.getUseLimit5());
            frCardLimit.setNote(frCardLimit1.getNote());
            frCardLimit.setFlag(frCardLimit1.getFlag());
            frCardLimit.setCustomerCode(frCardLimit1.getCustomerCode());
            frCardLimit.setClientId(frCardLimit1.getClientId());
        }
        return frCardLimit;
    }
}
