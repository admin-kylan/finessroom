package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.FrClientMentalityTypeMapper;
import com.yj.dal.model.*;
import com.yj.dal.dao.FrClientMentalityRelateMapper;
import com.yj.dal.model.FrClientMentalityType;
import com.yj.service.service.IFrClientMentalityRelateService;
import com.yj.service.base.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 客户心理状况 内容保存 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-08
 */
@Service
public class FrClientMentalityRelateServiceImpl extends BaseServiceImpl<FrClientMentalityRelateMapper, FrClientMentalityRelate> implements IFrClientMentalityRelateService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FrClientMentalityTypeMapper frClientMentalityTypeMapper;

    @Autowired
    FrClientMentalityRelateMapper frClientMentalityRelateMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult saveMentality(String[] frClientMentalityTypeIds, String cid, String familyLife, String emotionalState) throws YJException {

        //校验参数
        if (frClientMentalityTypeIds == null || cid == null || familyLife == null || emotionalState == null) {
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }

        FrClientMentalityRelate frClientMentalityRelate = new FrClientMentalityRelate();
        //当前客户id唯一,默认启用
        frClientMentalityRelate.setClientId(cid);
        frClientMentalityRelate.setUsing(true);
        List<FrClientMentalityType> frClientMentalityTypes = new ArrayList<>();
        //根据ID获取对应的类型
        for (int i = 0; i < frClientMentalityTypeIds.length; i++) {
            FrClientMentalityType frClientMentalityType = frClientMentalityTypeMapper.findById(frClientMentalityTypeIds[i]);
            if (frClientMentalityType != null) {
                frClientMentalityTypes.add(frClientMentalityType);
            }
        }
        if (frClientMentalityTypeIds != null) {
            //添加前先删除
            Integer delete = frClientMentalityRelateMapper.delete(
                    new EntityWrapper<FrClientMentalityRelate>().where("client_id={0}", cid)
            );
            log.info("删除原有记录", SqlHelper.retBool(delete));
            //添加家庭生活情况
            frClientMentalityRelate.setId(UUIDUtils.generateGUID());
            frClientMentalityRelate.setType(1);
            frClientMentalityRelate.setMentalityTypeContent(familyLife);
            Integer insert1 = frClientMentalityRelateMapper.insert(frClientMentalityRelate);
            log.info("添加家庭生活情况", SqlHelper.retBool(insert1));
            //添加情绪状况
            frClientMentalityRelate.setId(UUIDUtils.generateGUID());
            frClientMentalityRelate.setType(4);
            frClientMentalityRelate.setMentalityTypeContent(emotionalState);
            Integer insert2 = frClientMentalityRelateMapper.insert(frClientMentalityRelate);
            log.info("添加情绪状况", SqlHelper.retBool(insert2));
            //根据类型添加内容
            for (FrClientMentalityType frClientMentalityType : frClientMentalityTypes) {
                frClientMentalityRelate.setId(UUIDUtils.generateGUID());
                frClientMentalityRelate.setMentalityTypeContent(frClientMentalityType.getMentalityName());
                frClientMentalityRelate.setType(frClientMentalityType.getType());
                frClientMentalityRelate.setMentalityTypeId(frClientMentalityType.getId());
                Integer insert3 = frClientMentalityRelateMapper.insert(frClientMentalityRelate);
                log.info("添加类型内容", SqlHelper.retBool(insert3));
            }
            return JsonResult.successMessage("保存成功");
        } else {
            return JsonResult.failMessage("保存失败");
        }
    }

    @Override
    public List<FrClientMentalityRelate> getMentality() {
        List<FrClientMentalityRelate> frClientMentalityRelates = frClientMentalityRelateMapper.selectList(
                new EntityWrapper<FrClientMentalityRelate>().where("is_using={0}", 1));
        return frClientMentalityRelates;
    }
}


