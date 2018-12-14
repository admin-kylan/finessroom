package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.FrClientWorkTypeMapper;
import com.yj.dal.model.*;
import com.yj.dal.dao.FrClientWorkRelateMapper;
import com.yj.dal.model.FrClientWorkType;
import com.yj.service.service.IFrClientWorkRelateService;
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
 * 客户工作行业 内容保存 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-08
 */
@Service
public class FrClientWorkRelateServiceImpl extends BaseServiceImpl<FrClientWorkRelateMapper, FrClientWorkRelate> implements IFrClientWorkRelateService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FrClientWorkTypeMapper frClientWorkTypeMapper;

    @Autowired
    FrClientWorkRelateMapper frClientWorkRelateMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult saveWorks(String[] frClientWorkTypeIds, String cid, String workHistory, String workSituation) throws YJException {

        //校验参数
        if (frClientWorkTypeIds == null || cid == null || workHistory == null || workSituation == null) {
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        FrClientWorkRelate frClientWorkRelate = new FrClientWorkRelate();
        List<FrClientWorkType> frClientWorkTypes = new ArrayList<>();
        //当前客户id唯一,默认启用
        frClientWorkRelate.setClientId(cid);
        frClientWorkRelate.setUsing(true);
        //根据ID获取对应的类型
        for (int i = 0; i < frClientWorkTypeIds.length; i++) {
            FrClientWorkType frClientWorkType = frClientWorkTypeMapper.findById(frClientWorkTypeIds[i]);
            if (frClientWorkType != null) {
                frClientWorkTypes.add(frClientWorkType);
            }
        }
        if (frClientWorkTypes != null) {
            //添加前先删除
            Integer delete = frClientWorkRelateMapper.delete(
                    new EntityWrapper<FrClientWorkRelate>().where("client_id={0}", cid)
            );
            log.info("删除原有记录", SqlHelper.retBool(delete));
            //添加工作情况
            frClientWorkRelate.setId(UUIDUtils.generateGUID());
            frClientWorkRelate.setType(3);
            frClientWorkRelate.setWorkTypeContent(workSituation);
            Integer insert1 = frClientWorkRelateMapper.insert(frClientWorkRelate);
            log.info("添加工作情况", SqlHelper.retBool(insert1));
            //添加工作历史
            frClientWorkRelate.setId(UUIDUtils.generateGUID());
            frClientWorkRelate.setType(4);
            frClientWorkRelate.setWorkTypeContent(workHistory);
            Integer insert2 = frClientWorkRelateMapper.insert(frClientWorkRelate);
            log.info("添加工作历史", SqlHelper.retBool(insert2));
            //根据类型添加内容
            for (FrClientWorkType frClientWorkType : frClientWorkTypes) {
                frClientWorkRelate.setId(UUIDUtils.generateGUID());
                frClientWorkRelate.setWorkTypeContent(frClientWorkType.getWorkName());
                frClientWorkRelate.setType(frClientWorkType.getType());
                frClientWorkRelate.setWorkTypeId(frClientWorkType.getId());
                Integer insert3 = frClientWorkRelateMapper.insert(frClientWorkRelate);
                log.info("添加类型内容", SqlHelper.retBool(insert3));
            }
            return JsonResult.successMessage("保存成功");
        } else {
            return JsonResult.failMessage("保存失败");
        }

    }

    @Override
    public List<FrClientWorkRelate> getWorks() {
        List<FrClientWorkRelate> frClientWorkRelates = frClientWorkRelateMapper.selectList(
                new EntityWrapper<FrClientWorkRelate>().where("is_using={0}", 1));
        return frClientWorkRelates;
    }
}

