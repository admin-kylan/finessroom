package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.util.HttpServletUtils;
import com.yj.dal.dao.FrClientLifeTypeMapper;
import com.yj.dal.model.FrClientLifeType;
import com.yj.dal.model.FrClientLifeType;
import com.yj.dal.dao.FrClientLifeTypeMapper;
import com.yj.service.service.IFrClientLifeTypeService;
import com.yj.service.base.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 客户生活详情 类型 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-02
 */
@Service
public class FrClientLifeTypeServiceImpl extends BaseServiceImpl<FrClientLifeTypeMapper, FrClientLifeType> implements IFrClientLifeTypeService {
    @Autowired
    FrClientLifeTypeMapper frClientLifeTypeMapper;

    @Override
    public List<FrClientLifeType> getDetails() throws YJException {
        //获取客户代码
        String customerCode = HttpServletUtils.getCustmerCode();
        if (StringUtils.isEmpty(customerCode)) {
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        List<FrClientLifeType> frClientLifeTypes = frClientLifeTypeMapper.selectList(
                new EntityWrapper<FrClientLifeType>().where("is_using={0}", 1).where("CustomerCode={0}", customerCode));

        return frClientLifeTypes;
    }
}
