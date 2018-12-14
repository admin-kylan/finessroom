package com.yj.service.service.impl;

import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.util.StringUtils;
import com.yj.dal.model.FrFollowPic;
import com.yj.dal.model.FrLatenceFollowPic;
import com.yj.dal.dao.FrLatenceFollowPicMapper;
import com.yj.service.service.IFrLatenceFollowPicService;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 潜在客户跟进图片表 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-10
 */
@Service
public class FrLatenceFollowPicServiceImpl extends BaseServiceImpl<FrLatenceFollowPicMapper, FrLatenceFollowPic> implements IFrLatenceFollowPicService {
    @Override
    public List<FrLatenceFollowPic> queryPricList(String followdId) throws YJException {
        if(StringUtils.isEmpty(followdId)){
            throw new YJException(YJExceptionEnum.VIPCARDNO_ISNOTBLANK);
        }
        FrLatenceFollowPic frLatenceFollowPic = new FrLatenceFollowPic();
        frLatenceFollowPic.setLatenceFollowId(followdId);
        return  baseMapper.queryPricList(frLatenceFollowPic);
    }
}
