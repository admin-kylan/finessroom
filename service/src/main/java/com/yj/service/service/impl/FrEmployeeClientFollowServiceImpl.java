package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.util.*;
import com.yj.dal.model.FrEmployeeClientFollow;
import com.yj.dal.dao.FrEmployeeClientFollowMapper;
import com.yj.dal.model.FrFollowPic;
import com.yj.service.service.IFrEmployeeClientFollowService;
import com.yj.service.base.BaseServiceImpl;
import com.yj.service.service.IFrFollowPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.management.BufferPoolMXBean;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 现有会员跟进记录 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-30
 */
@Service
public class FrEmployeeClientFollowServiceImpl extends BaseServiceImpl<FrEmployeeClientFollowMapper, FrEmployeeClientFollow> implements IFrEmployeeClientFollowService {

    @Autowired
    IFrFollowPicService iFrFollowPicService;

    @Override
    public PageUtils queryEmployeeClientList(PageUtil<FrEmployeeClientFollow> pageUtil) throws YJException {
        Map map = new HashMap();
        map.put("limit",pageUtil.getRows());//每页多少条
        map.put("page",pageUtil.getPage());//当前页
        Page page = new Query<FrEmployeeClientFollow>(map).getPage();
        FrEmployeeClientFollow frEmployeeClientFollow = pageUtil.getCondition();
        if(frEmployeeClientFollow != null){
            //查询该会员卡列表总数据
            List<Map<String,Object>> list = baseMapper.selectFrEmployeeClienFollowList(page, pageUtil.getCondition());
            page.setRecords(list);
        }
        return new PageUtils(page);
    }

    /**
     * 更新或者插入数据
     * @param frEmployeeClientFollow
     * @return
     * @throws YJException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean toInsertOrUpdate(FrEmployeeClientFollow frEmployeeClientFollow) throws YJException {
        Integer successCount = 0;
        if(frEmployeeClientFollow == null){
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        boolean isFlage = true;
        if(StringUtils.isEmpty(frEmployeeClientFollow.getId())){
            //插入
            isFlage = false;
            successCount = this.toInterFollow(frEmployeeClientFollow);
        }
        if(isFlage){
            //更新
           successCount = this.toUpdateFollow(frEmployeeClientFollow);
        }
        return SqlHelper.retBool(successCount);
    }

    /**
     * 添加跟进信息
     * @param frEmployeeClientFollow
     * @param imageList
     * @return
     * @throws YJException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean toInertAndUpdatImage(FrEmployeeClientFollow frEmployeeClientFollow, List<String> imageList,String imagePath) throws YJException {
        boolean isFlage = this.toInsertOrUpdate(frEmployeeClientFollow);
        StringBuffer imageBuff = new StringBuffer(imagePath);
        if(isFlage){
            FrFollowPic frFollowPic = new FrFollowPic();
            for(String imgUrl:imageList){
                frFollowPic.setId(UUIDUtils.generateGUID());
                frFollowPic.setEmployeeClientFollowId(frEmployeeClientFollow.getId());
                frFollowPic.setFollowMarkPic(imageBuff.append(imgUrl).toString());
                boolean tointer = iFrFollowPicService.insert(frFollowPic);
                if(!tointer){
                    throw new YJException(YJExceptionEnum.PARAM_ERROR);
                }
                frFollowPic = new FrFollowPic();
                imageBuff = new StringBuffer(imagePath);
            }
            return true;
        }
        return false;
    }

    /**
     * 更新单条数据
     * @param frEmployeeClientFollow 注意CustomerCode 必须要有值
     * @return
     * @throws YJException
     */
    public Integer toUpdateFollow(FrEmployeeClientFollow frEmployeeClientFollow) throws YJException{
        Integer successCount = 0;
        if(frEmployeeClientFollow != null){
            if(StringUtils.isEmpty(frEmployeeClientFollow.getId())){
                throw new YJException(YJExceptionEnum.PARAM_ERROR);
            }
            successCount = baseMapper.updateById(frEmployeeClientFollow);
        }
        return successCount;
    }

    /**
     * 插入数据
     * @param frEmployeeClientFollow
     * @return
     * @throws YJException
     */
    public Integer toInterFollow(FrEmployeeClientFollow frEmployeeClientFollow)throws YJException{
        Integer successCount = 0;
        if(frEmployeeClientFollow != null){
            if(StringUtils.isEmpty(frEmployeeClientFollow.getCustomerCode()) || StringUtils.isEmpty(frEmployeeClientFollow.getClientId())
                    || StringUtils.isEmpty(frEmployeeClientFollow.getPersonalId()) || StringUtils.isEmpty(frEmployeeClientFollow.getShopId())){
                throw new YJException(YJExceptionEnum.PARAM_ERROR);
            }
            frEmployeeClientFollow.setId(UUIDUtils.generateGUID());
            frEmployeeClientFollow.setType(0);
            frEmployeeClientFollow.setUsing(true);
            if(frEmployeeClientFollow.getCreateTime() == null){
                frEmployeeClientFollow.setCreateTime(new Date());
            }
            successCount = baseMapper.insert(frEmployeeClientFollow);
        }
        return successCount;
    }

}
