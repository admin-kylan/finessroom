package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.util.*;
import com.yj.dal.model.FrLatenceFollow;
import com.yj.dal.dao.FrLatenceFollowMapper;
import com.yj.dal.model.FrLatenceFollowPic;
import com.yj.service.service.IFrLatenceFollowPicService;
import com.yj.service.service.IFrLatenceFollowService;
import com.yj.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 潜在会员跟进记录 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-20
 */
@Service
public class FrLatenceFollowServiceImpl extends BaseServiceImpl<FrLatenceFollowMapper, FrLatenceFollow> implements IFrLatenceFollowService {
    @Autowired
    IFrLatenceFollowPicService frLatenceFollowPicService;


    @Override
    public PageUtils queryLatenceClientList(PageUtil<FrLatenceFollow> pageUtil) throws YJException {
        Map map = new HashMap();
        map.put("limit",pageUtil.getRows());//每页多少条
        map.put("page",pageUtil.getPage());//当前页
        Page page = new Query<FrLatenceFollow>(map).getPage();
        FrLatenceFollow frLatenceFollow = pageUtil.getCondition();
        if(frLatenceFollow != null){
            //查询该会员卡列表总数据
            List<Map<String,Object>> list = baseMapper.selectFrLatenceClienFollowList(page, pageUtil.getCondition());
            page.setRecords(list);
        }
        return new PageUtils(page);
    }

    /**
     * 更新或者插入数据
     * @param frLatenceFollow
     * @return
     * @throws YJException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean toInsertOrUpdate(FrLatenceFollow frLatenceFollow) throws YJException {
        Integer successCount = 0;
        if(frLatenceFollow == null){
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        boolean isFlage = true;
        if(StringUtils.isEmpty(frLatenceFollow.getId())){
            //插入
            isFlage = false;
            successCount = this.toInterFollow(frLatenceFollow);
        }
        if(isFlage){
            //更新
            successCount = this.toUpdateFollow(frLatenceFollow);
        }
        return SqlHelper.retBool(successCount);
    }

    /**
     * 添加跟进信息
     * @param frLatenceFollow
     * @param imageList
     * @return
     * @throws YJException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean toInertAndUpdatImage(FrLatenceFollow frLatenceFollow, List<String> imageList,String imagePath) throws YJException {
        boolean isFlage = this.toInsertOrUpdate(frLatenceFollow);
        StringBuffer imageBuff = new StringBuffer(imagePath);
        if(isFlage){
            FrLatenceFollowPic frLatenceFollowPic = new FrLatenceFollowPic();
            for(String imgUrl:imageList){
                frLatenceFollowPic.setId(UUIDUtils.generateGUID());
                frLatenceFollowPic.setLatenceFollowId(frLatenceFollow.getId());
                frLatenceFollowPic.setFollowMarkPic(imageBuff.append(imgUrl).toString());
                boolean tointer = frLatenceFollowPicService.insert(frLatenceFollowPic);
                if(!tointer){
                    throw new YJException(YJExceptionEnum.PARAM_ERROR);
                }
                frLatenceFollowPic = new FrLatenceFollowPic();
                imageBuff = new StringBuffer(imagePath);
            }
            return true;
        }
        return false;
    }

    /**
     * 更新单条数据
     * @param frLatenceFollow 注意CustomerCode 必须要有值
     * @return
     * @throws YJException
     */
    public Integer toUpdateFollow(FrLatenceFollow frLatenceFollow) throws YJException{
        Integer successCount = 0;
        if(frLatenceFollow != null){
            if(StringUtils.isEmpty(frLatenceFollow.getId())){
                throw new YJException(YJExceptionEnum.PARAM_ERROR);
            }
            successCount = baseMapper.updateById(frLatenceFollow);
        }
        return successCount;
    }

    /**
     * 插入数据
     * @param frLatenceFollow
     * @return
     * @throws YJException
     */
    public Integer toInterFollow(FrLatenceFollow frLatenceFollow)throws YJException {
        Integer successCount = 0;
        if(frLatenceFollow != null){
            if(StringUtils.isEmpty(frLatenceFollow.getCustomerCode()) || StringUtils.isEmpty(frLatenceFollow.getClientId())
                    || StringUtils.isEmpty(frLatenceFollow.getPersonalId()) || StringUtils.isEmpty(frLatenceFollow.getShopId())){
                throw new YJException(YJExceptionEnum.PARAM_ERROR);
            }
            frLatenceFollow.setId(UUIDUtils.generateGUID());
            frLatenceFollow.setType(0);
            frLatenceFollow.setUsing(true);
            if(frLatenceFollow.getCreateTime() == null){
                frLatenceFollow.setCreateTime(new Date());
            }
            successCount = baseMapper.insert(frLatenceFollow);
        }
        return successCount;
    }
}
