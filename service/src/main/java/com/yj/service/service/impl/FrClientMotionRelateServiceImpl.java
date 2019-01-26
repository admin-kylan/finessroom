package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.FrClientMotionTypeMapper;
import com.yj.dal.model.*;
import com.yj.dal.dao.FrClientMotionRelateMapper;
import com.yj.service.service.IFrClientMotionRelateService;
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
 * 客户运动档案  内容保存 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-02
 */
@Service
public class FrClientMotionRelateServiceImpl extends BaseServiceImpl<FrClientMotionRelateMapper, FrClientMotionRelate> implements IFrClientMotionRelateService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FrClientMotionTypeMapper frClientMotionTypeMapper;

    @Autowired
    FrClientMotionRelateMapper frClientMotionRelateMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult saveSports(String[] frClientSportTypeIds, String cid, String sportType) throws YJException {

        //校验参数
        if (frClientSportTypeIds == null || cid == null || sportType == null) {
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        FrClientMotionRelate frClientMotionRelate = new FrClientMotionRelate();
        //当前客户id唯一,默认启用
        frClientMotionRelate.setClientId(cid);
        frClientMotionRelate.setUsing(true);
        List<FrClientMotionType> frClientMotionTypes = new ArrayList<>();
        //根据ID获取对应的类型
        for (int i = 0; i < frClientSportTypeIds.length; i++) {
            FrClientMotionType frClientMotionType = frClientMotionTypeMapper.findById(frClientSportTypeIds[i]);
            if (frClientMotionType != null) {
                frClientMotionTypes.add(frClientMotionType);
            }
        }

        if (frClientMotionTypes != null) {
            //添加前先删除
            Integer delete = frClientMotionRelateMapper.delete(
                    new EntityWrapper<FrClientMotionRelate>().where("client_id={0}", cid)
            );
            log.info("删除原有记录[{}]", SqlHelper.retBool(delete));
            //先添加运动类型
            frClientMotionRelate.setId(UUIDUtils.generateGUID());
            frClientMotionRelate.setType(9);
            frClientMotionRelate.setMotionTypeContent(sportType);
            Integer insert1 = frClientMotionRelateMapper.insert(frClientMotionRelate);
            log.info("添加运动类型[{}]", SqlHelper.retBool(insert1));
            //根据类型添加内容
            for (FrClientMotionType frClientMotionType : frClientMotionTypes) {
                frClientMotionRelate.setId(UUIDUtils.generateGUID());
                frClientMotionRelate.setMotionTypeContent(frClientMotionType.getMotionName());
                frClientMotionRelate.setType(frClientMotionType.getType());
                frClientMotionRelate.setMotionTypeId(frClientMotionType.getId());
                Integer insert2 = frClientMotionRelateMapper.insert(frClientMotionRelate);
                log.info("添加类型内容[{}]", SqlHelper.retBool(insert2));
            }
            return JsonResult.successMessage("保存成功");
        } else {
            return JsonResult.failMessage("保存失败");
        }

    }

    @Override
    public List<FrClientMotionRelate> getSports(String clientId) {

        List<FrClientMotionRelate> frClientMotionRelates = frClientMotionRelateMapper.selectList(
                new EntityWrapper<FrClientMotionRelate>().where("is_using=1 and client_id={0}", clientId));
        return frClientMotionRelates;
    }
}
