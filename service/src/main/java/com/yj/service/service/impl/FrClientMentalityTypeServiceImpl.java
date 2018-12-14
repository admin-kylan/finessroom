package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.util.HttpServletUtils;
import com.yj.dal.model.FrClientMentalityType;
import com.yj.dal.dao.FrClientMentalityTypeMapper;
import com.yj.dal.model.FrClientMotionType;
import com.yj.service.service.IFrClientMentalityTypeService;
import com.yj.service.base.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 客户心理情况 类型 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-08
 */
@Service
public class FrClientMentalityTypeServiceImpl extends BaseServiceImpl<FrClientMentalityTypeMapper, FrClientMentalityType> implements IFrClientMentalityTypeService {

    @Autowired
    FrClientMentalityTypeMapper frClientMentalityTypeMapper;

    @Override
    public List<FrClientMentalityType> getMentality() throws YJException {

        //获取客户代码
        String customerCode = HttpServletUtils.getCustmerCode();
        if (StringUtils.isEmpty(customerCode)) {
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        List<FrClientMentalityType> frClientMentalityTypes = frClientMentalityTypeMapper.selectList(
                new EntityWrapper<FrClientMentalityType>().where("is_using={0}", 1).where("CustomerCode={0}", customerCode));

        return frClientMentalityTypes;
    }
}
