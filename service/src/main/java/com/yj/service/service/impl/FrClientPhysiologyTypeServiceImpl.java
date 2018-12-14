package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.util.HttpServletUtils;
import com.yj.dal.model.FrClientPhysiologyType;
import com.yj.dal.dao.FrClientPhysiologyTypeMapper;
import com.yj.service.service.IFrClientPhysiologyTypeService;
import com.yj.service.base.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 客户生理状况 类型 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-08
 */
@Service
public class FrClientPhysiologyTypeServiceImpl extends BaseServiceImpl<FrClientPhysiologyTypeMapper, FrClientPhysiologyType> implements IFrClientPhysiologyTypeService {

    @Autowired
    FrClientPhysiologyTypeMapper frClientPhysiologyTypeMapper;

    @Override
    public List<FrClientPhysiologyType> getPsychology() throws YJException {
        //获取客户代码
        String customerCode = HttpServletUtils.getCustmerCode();
        if (StringUtils.isEmpty(customerCode)) {
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        List<FrClientPhysiologyType> frClientPhysiologyTypes = frClientPhysiologyTypeMapper.selectList(
                new EntityWrapper<FrClientPhysiologyType>().where("is_using={0}", 1).where("CustomerCode={0}", customerCode));
        return frClientPhysiologyTypes;
    }
}
