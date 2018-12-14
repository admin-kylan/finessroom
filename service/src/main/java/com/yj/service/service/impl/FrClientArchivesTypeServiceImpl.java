package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.util.HttpServletUtils;
import com.yj.dal.model.FrClientArchivesType;
import com.yj.dal.dao.FrClientArchivesTypeMapper;
import com.yj.service.service.IFrClientArchivesTypeService;
import com.yj.service.base.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 客户档案（皮肤、头发、塑性抗衰 档案） 类型 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
@Service
public class FrClientArchivesTypeServiceImpl extends BaseServiceImpl<FrClientArchivesTypeMapper, FrClientArchivesType> implements IFrClientArchivesTypeService {

    @Override
    public List<FrClientArchivesType> getTypeAll(Integer type) throws YJException {
        //获取客户代码
        String customerCode = HttpServletUtils.getCustmerCode();
        if (StringUtils.isEmpty(customerCode)) {
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        List<FrClientArchivesType> frClientArchivesTypes = baseMapper.selectList(
                new EntityWrapper<FrClientArchivesType>().where("type={0}", type).where("CustomerCode={0}", customerCode));
        return frClientArchivesTypes;
    }
}
