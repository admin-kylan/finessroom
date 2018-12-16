package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.PersonnelInfoMapper;
import com.yj.dal.dto.PhysicalDateDTO;
import com.yj.dal.model.FrClientPhysicalTest;
import com.yj.dal.dao.FrClientPhysicalTestMapper;
import com.yj.dal.model.PersonnelInfo;
import com.yj.service.service.IFrClientPhysicalTestService;
import com.yj.service.base.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 体能测试 内容保存 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-16
 */
@Service
public class FrClientPhysicalTestServiceImpl extends BaseServiceImpl<FrClientPhysicalTestMapper, FrClientPhysicalTest> implements IFrClientPhysicalTestService {

    @Autowired
    PersonnelInfoMapper personnelInfoMapper;

    @Override
    public List<FrClientPhysicalTest> getPhysical(String cid, Date date) throws YJException {
        //校验参数
        if (date == null || cid == null) {
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        List<FrClientPhysicalTest> frClientPhysicalTests = new ArrayList<>();
        FrClientPhysicalTest frClientPhysicalTestOne = selectOne(
                new EntityWrapper<FrClientPhysicalTest>().where("client_id={0}", cid).and("build_date={0}", date).and("result={0}", true));
        FrClientPhysicalTest frClientPhysicalTestTwo = selectOne(
                new EntityWrapper<FrClientPhysicalTest>().where("client_id={0}", cid).and("build_date={0}", date).and("result={0}", false));
        if (frClientPhysicalTestOne != null) {
            frClientPhysicalTests.add(frClientPhysicalTestOne);
        }
        if (frClientPhysicalTestTwo != null) {
            frClientPhysicalTests.add(frClientPhysicalTestTwo);
        }
        return frClientPhysicalTests;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult savePhysical(List<FrClientPhysicalTest> frClientPhysicalTests) throws YJException {
        //校验参数
        if (frClientPhysicalTests.size() < 0) {
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(sdf.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String id=null;
        for (FrClientPhysicalTest frClientPhysicalTest : frClientPhysicalTests) {

            if (StringUtils.isNotEmpty(frClientPhysicalTest.getId())) {
                baseMapper.updateById(frClientPhysicalTest);
            } else {
                frClientPhysicalTest.setId(UUIDUtils.generateGUID());
                frClientPhysicalTest.setBuildDate(date);
                frClientPhysicalTest.setEvaluationDate(date);
                baseMapper.insert(frClientPhysicalTest);
            }
            if (frClientPhysicalTest.getResult()==false){
                id=frClientPhysicalTest.getId();
            }
        }

        return JsonResult.success(id);
    }

    @Override
    public List<PhysicalDateDTO> getSaveDate(String cid) throws YJException {
        List<FrClientPhysicalTest> frClientPhysicalTests = baseMapper.selectList(
                new EntityWrapper<FrClientPhysicalTest>().where("client_id={0}", cid).where("result={0}", 0));
        List<PhysicalDateDTO> physicalDates = new ArrayList<>();
        if (frClientPhysicalTests.size() > 0) {
            PhysicalDateDTO physicalDate = null;
            for (int i = 0; i < frClientPhysicalTests.size(); i++) {
                physicalDate = new PhysicalDateDTO();
                physicalDate.setSaveDate(frClientPhysicalTests.get(i).getBuildDate());
                PersonnelInfo personnelInfo = personnelInfoMapper.selectById(frClientPhysicalTests.get(i).getPersonalId());
                physicalDate.setPersonalName(personnelInfo.getRelName());
                physicalDates.add(physicalDate);
            }
        }
        return physicalDates;
    }
}
