package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.FrClientLifeTypeMapper;
import com.yj.dal.dao.FrClientMapper;
import com.yj.dal.model.FrClient;
import com.yj.dal.model.FrClientLifeRelate;
import com.yj.dal.dao.FrClientLifeRelateMapper;
import com.yj.dal.model.FrClientLifeType;
import com.yj.service.service.IFrClientLifeRelateService;
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
 * 客户生活详情 内容保存 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-02
 */
@Service
public class FrClientLifeRelateServiceImpl extends BaseServiceImpl<FrClientLifeRelateMapper, FrClientLifeRelate> implements IFrClientLifeRelateService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FrClientLifeTypeMapper frClientLifeTypeMapper;

    @Autowired
    FrClientLifeRelateMapper frClientLifeRelateMapper;

    @Autowired
    FrClientMapper frClientMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult saveDetails(String[] frClientLifeTypeIds, String cid, String source, String sid) throws YJException {
        //校验参数
        if (frClientLifeTypeIds == null || cid == null || source == null) {
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        FrClient frClient = frClientMapper.selectById(cid);
        if (frClient != null) {
            frClient.setCustomerSource(source);
            frClient.setSourceId(sid);
            Integer update = frClientMapper.updateById(frClient);
            log.info("删除原有记录", SqlHelper.retBool(update));
        }
        FrClientLifeRelate frClientLifeRelate = new FrClientLifeRelate();
        //当前客户id唯一,默认启用
        frClientLifeRelate.setClientId(cid);
        frClientLifeRelate.setUsing(true);
        List<FrClientLifeType> lifeTypes = new ArrayList<>();
        //根据ID获取对应的类型
        for (int i = 0; i < frClientLifeTypeIds.length; i++) {
            FrClientLifeType frClientLifeType = frClientLifeTypeMapper.findById(frClientLifeTypeIds[i]);
            if (frClientLifeType != null) {
                lifeTypes.add(frClientLifeType);
            }

        }
        if (lifeTypes != null) {
            //添加前先删除
            Integer delete = frClientLifeRelateMapper.delete(new EntityWrapper<FrClientLifeRelate>().where("client_id={0}", cid));
            log.info("添加类型内容[{}]", SqlHelper.retBool(delete));
            //根据类型添加内容
            for (FrClientLifeType frClientLifeType : lifeTypes) {
                frClientLifeRelate.setId(UUIDUtils.generateGUID());
                frClientLifeRelate.setLifeTypeId(frClientLifeType.getId());
                frClientLifeRelate.setType(frClientLifeType.getType());
                frClientLifeRelate.setLifeTypeContent(frClientLifeType.getLifeName());
                Integer insert = frClientLifeRelateMapper.insert(frClientLifeRelate);
                log.info("添加类型内容[{}]", SqlHelper.retBool(insert));
            }
            return JsonResult.successMessage("保存成功");
        } else {
            return JsonResult.failMessage("保存失败");
        }
    }

    @Override
    public List<FrClientLifeRelate> getDetails(String clientId) {
        List<FrClientLifeRelate> frClientLifeRelates = frClientLifeRelateMapper.selectList(
                new EntityWrapper<FrClientLifeRelate>().where("is_using=1 and client_id={0}", clientId));
        return frClientLifeRelates;
    }
}
