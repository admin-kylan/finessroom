package com.yj.service.service.impl;

import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.util.PageUtils;
import com.yj.common.util.StringUtils;
import com.yj.dal.model.FrFollowPic;
import com.yj.dal.dao.FrFollowPicMapper;
import com.yj.service.service.IFrFollowPicService;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 跟进表的图片 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-01
 */
@Service
public class FrFollowPicServiceImpl extends BaseServiceImpl<FrFollowPicMapper, FrFollowPic> implements IFrFollowPicService {

    @Override
    public List<FrFollowPic> queryPricList(String followdId) throws YJException {
        if(StringUtils.isEmpty(followdId)){
            throw new YJException(YJExceptionEnum.VIPCARDNO_ISNOTBLANK);
        }
        FrFollowPic frFollowPic = new FrFollowPic();
        frFollowPic.setEmployeeClientFollowId(followdId);
        return  baseMapper.queryPricList(frFollowPic);
    }
}
