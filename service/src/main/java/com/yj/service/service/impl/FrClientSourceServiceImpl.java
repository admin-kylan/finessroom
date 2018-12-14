package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.dal.dao.FrClientMapper;
import com.yj.dal.model.FrClient;
import com.yj.dal.model.FrClientSource;
import com.yj.dal.dao.FrClientSourceMapper;
import com.yj.service.service.IFrClientSourceService;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户来源渠道表 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-09
 */
@Service
public class FrClientSourceServiceImpl extends BaseServiceImpl<FrClientSourceMapper, FrClientSource> implements IFrClientSourceService {

    @Autowired
    FrClientSourceMapper frClientSourceMapper;

    @Autowired
    FrClientMapper frClientMapper;

    @Override
    public Map<String, Object> getSource(String cid) throws YJException {

        //校验参数
        if (cid == null) {
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        List<FrClientSource> frClientSources = frClientSourceMapper.selectList(
                new EntityWrapper<FrClientSource>().where("is_using={0}", 1));
        FrClient frClient = frClientMapper.selectById(cid);
        Map<String, Object> map = new HashMap();
        map.put("frClientSources", frClientSources);
        map.put("customerSource", frClient.getCustomerSource());
        map.put("sourceId", frClient.getSourceId());
        return map;
    }
}
