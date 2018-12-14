package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.FrClientPhysiologyTypeMapper;
import com.yj.dal.model.FrClientPhysiologyRelate;
import com.yj.dal.dao.FrClientPhysiologyRelateMapper;
import com.yj.dal.model.FrClientPhysiologyType;
import com.yj.service.service.IFrClientPhysiologyRelateService;
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
 * 客户生理状况 内容保存 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-08
 */
@Service
public class FrClientPhysiologyRelateServiceImpl extends BaseServiceImpl<FrClientPhysiologyRelateMapper, FrClientPhysiologyRelate> implements IFrClientPhysiologyRelateService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FrClientPhysiologyTypeMapper frClientPhysiologyTypeMapper;

    @Autowired
    FrClientPhysiologyRelateMapper frClientPhysiologyRelateMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult savePsychology(String[] frClientPsychologyTypeIds, String cid, String medicalHistory, String drug, String theHormone, String bloodPressure,String menstruation) throws YJException {
        //校验参数
        if (frClientPsychologyTypeIds == null || cid == null || medicalHistory == null || drug == null || theHormone == null || bloodPressure == null|| menstruation == null) {
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
       FrClientPhysiologyRelate frClientPhysiologyRelate=new FrClientPhysiologyRelate();
        //当前客户id唯一,默认启用
        frClientPhysiologyRelate.setClientId(cid);
        frClientPhysiologyRelate.setUsing(true);

        List<FrClientPhysiologyType> frClientPhysiologyTypes=new ArrayList<>();
        //根据ID获取对应的类型
        for (int i = 0; i < frClientPsychologyTypeIds.length; i++) {
            FrClientPhysiologyType frClientPhysiologyType = frClientPhysiologyTypeMapper.findById(frClientPsychologyTypeIds[i]);
            if (frClientPhysiologyType != null) {
                frClientPhysiologyTypes.add(frClientPhysiologyType);
            }
        }
        if (frClientPhysiologyTypes != null) {
            //添加前先删除
            Integer delete = frClientPhysiologyRelateMapper.delete(
                    new EntityWrapper<FrClientPhysiologyRelate>().where("client_id={0}", cid)
            );
            log.info("删除原有记录[{}]", SqlHelper.retBool(delete));
            //添加以往病史
            frClientPhysiologyRelate.setId(UUIDUtils.generateGUID());
            frClientPhysiologyRelate.setType(5);
            frClientPhysiologyRelate.setPhysiologyTypeContent(medicalHistory);
            Integer insert1 = frClientPhysiologyRelateMapper.insert(frClientPhysiologyRelate);
            log.info("添加以往病史[{}]", SqlHelper.retBool(insert1));
            //添加曾服药物
            frClientPhysiologyRelate.setId(UUIDUtils.generateGUID());
            frClientPhysiologyRelate.setType(6);
            frClientPhysiologyRelate.setPhysiologyTypeContent(drug);
            Integer insert2 = frClientPhysiologyRelateMapper.insert(frClientPhysiologyRelate);
            log.info("曾服药物[{}]", SqlHelper.retBool(insert2));
            //添加荷尔蒙情况
            frClientPhysiologyRelate.setId(UUIDUtils.generateGUID());
            frClientPhysiologyRelate.setType(7);
            frClientPhysiologyRelate.setPhysiologyTypeContent(theHormone);
            Integer insert3 = frClientPhysiologyRelateMapper.insert(frClientPhysiologyRelate);
            log.info("荷尔蒙情况[{}]", SqlHelper.retBool(insert3));
            //添加血压状况
            frClientPhysiologyRelate.setId(UUIDUtils.generateGUID());
            frClientPhysiologyRelate.setType(8);
            frClientPhysiologyRelate.setPhysiologyTypeContent(bloodPressure);
            Integer insert4 = frClientPhysiologyRelateMapper.insert(frClientPhysiologyRelate);
            log.info("血压状况[{}]", SqlHelper.retBool(insert4));
            //添加月经状况
            frClientPhysiologyRelate.setId(UUIDUtils.generateGUID());
            frClientPhysiologyRelate.setType(9);
            frClientPhysiologyRelate.setPhysiologyTypeContent(menstruation);
            Integer insert5 = frClientPhysiologyRelateMapper.insert(frClientPhysiologyRelate);
            log.info("月经状况[{}]", SqlHelper.retBool(insert5));
            //根据类型添加内容
            for (FrClientPhysiologyType frClientPhysiologyType : frClientPhysiologyTypes) {
                frClientPhysiologyRelate.setId(UUIDUtils.generateGUID());
                frClientPhysiologyRelate.setPhysiologyTypeContent(frClientPhysiologyType.getPhysiologyName());
                frClientPhysiologyRelate.setType(frClientPhysiologyType.getType());
                frClientPhysiologyRelate.setPhysiologyTypeId(frClientPhysiologyType.getId());
                Integer insert6 = frClientPhysiologyRelateMapper.insert(frClientPhysiologyRelate);
                log.info("添加类型内容[{}]", SqlHelper.retBool(insert6));
            }
            return JsonResult.successMessage("保存成功");
        } else {
            return JsonResult.failMessage("保存失败");
        }
    }

    @Override
    public List<FrClientPhysiologyRelate> getPsychology() {
        List<FrClientPhysiologyRelate> frClientPhysiologyRelates = frClientPhysiologyRelateMapper.selectList(
                new EntityWrapper<FrClientPhysiologyRelate>().where("is_using={0}", 1));
        return frClientPhysiologyRelates;
    }
}
