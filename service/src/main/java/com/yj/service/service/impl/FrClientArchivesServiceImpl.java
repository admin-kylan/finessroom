package com.yj.service.service.impl;

import com.yj.common.exception.YJException;
import com.yj.dal.model.FrClientArchives;
import com.yj.dal.dao.FrClientArchivesMapper;
import com.yj.service.service.IFrClientArchivesService;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 客户档案（皮肤、头发、塑性抗衰 档案） 区分 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
@Service
public class FrClientArchivesServiceImpl extends BaseServiceImpl<FrClientArchivesMapper, FrClientArchives> implements IFrClientArchivesService {

    @Override
    public List<FrClientArchives> getArchives( String cid, Integer type) throws YJException {
        List<FrClientArchives> frClientArchives = baseMapper.findByClient(cid, type);
        return frClientArchives;
    }
}
