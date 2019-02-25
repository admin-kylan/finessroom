package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.*;
import com.yj.dal.model.FrLatenceFollow;
import com.yj.dal.dao.FrLatenceFollowMapper;
import com.yj.dal.model.FrLatenceFollowPic;
import com.yj.dal.model.PersonnelInfo;
import com.yj.service.service.IFrLatenceFollowPicService;
import com.yj.service.service.IFrLatenceFollowService;
import com.yj.service.base.BaseServiceImpl;
import com.yj.service.service.IPersonnelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    IPersonnelInfoService personnelInfoService;
    @Override
    public PageUtils queryLatenceClientList(PageUtil<FrLatenceFollow> pageUtil) throws YJException {
        Map map = new HashMap();
        map.put("limit", pageUtil.getRows());//每页多少条
        map.put("page", pageUtil.getPage());//当前页
        Page page = new Query<FrLatenceFollow>(map).getPage();
        FrLatenceFollow frLatenceFollow = pageUtil.getCondition();
        if (frLatenceFollow != null) {
            //查询该会员卡列表总数据
            List<Map<String, Object>> list = baseMapper.selectFrLatenceClienFollowList(page, pageUtil.getCondition());
            page.setRecords(list);
        }
        return new PageUtils(page);
    }

    /**
     * 更新或者插入数据
     *
     * @param frLatenceFollow
     * @return
     * @throws YJException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean toInsertOrUpdate(FrLatenceFollow frLatenceFollow) throws YJException {
        Integer successCount = 0;
        if (frLatenceFollow == null) {
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        boolean isFlage = true;
        if (StringUtils.isEmpty(frLatenceFollow.getId())) {
            //插入
            isFlage = false;
            successCount = this.toInterFollow(frLatenceFollow);
        }
        if (isFlage) {
            //更新
            successCount = this.toUpdateFollow(frLatenceFollow);
        }
        return SqlHelper.retBool(successCount);
    }

    /**
     * 添加跟进信息
     *
     * @param frLatenceFollow
     * @param imageList
     * @return
     * @throws YJException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean toInertAndUpdatImage(FrLatenceFollow frLatenceFollow, List<String> imageList, String imagePath) throws YJException {
        boolean isFlage = this.toInsertOrUpdate(frLatenceFollow);
        StringBuffer imageBuff = new StringBuffer(imagePath);
        if (isFlage) {
            FrLatenceFollowPic frLatenceFollowPic = new FrLatenceFollowPic();
            for (String imgUrl : imageList) {
                frLatenceFollowPic.setId(UUIDUtils.generateGUID());
                frLatenceFollowPic.setLatenceFollowId(frLatenceFollow.getId());
                frLatenceFollowPic.setFollowMarkPic(imageBuff.append(imgUrl).toString());
                boolean tointer = frLatenceFollowPicService.insert(frLatenceFollowPic);
                if (!tointer) {
                    throw new YJException(YJExceptionEnum.PARAM_ERROR);
                }
                frLatenceFollowPic = new FrLatenceFollowPic();
                imageBuff = new StringBuffer(imagePath);
            }
            return true;
        }
        return false;
    }

    @Override
    public JsonResult addFollow(List<String> imagesList, HttpServletRequest request, FrLatenceFollow frLatenceFollow) {
        if(frLatenceFollow!=null){
            boolean insert = insert(frLatenceFollow);
            if (insert) {
                //保存图片路径
                StringBuffer imagePath = new StringBuffer(CookieUtils.getCookieValue(request, "url", true));
                imagePath.append(CookieUtils.getCookieValue(request, "imgPath", true));
                String path = imagePath.toString();
                FrLatenceFollowPic frLatenceFollowPic = new FrLatenceFollowPic();
                for (String imgUrl : imagesList) {
                    frLatenceFollowPic.setId(UUIDUtils.generateGUID());
                    frLatenceFollowPic.setFollowMarkPic(path+ imgUrl);
                    frLatenceFollowPic.setLatenceFollowId(frLatenceFollow.getId());
                    frLatenceFollowPicService.insert(frLatenceFollowPic);
                }
            }
            return JsonResult.success();
        }
        return JsonResult.fail();
    }

    @Override
    public JsonResult getFollow(String id) {


        List<Map<String, Object>> maps = selectMaps(
                new EntityWrapper<FrLatenceFollow>()
                        .setSqlSelect("id,next_follow_time nextFollowTime,plan_visit_time planVisitTime,plan_purchase_time planPurchaseTime,follow_content followContent,follow_type followType,personal_id personalId")
                        .where("is_using=1 and client_id={0}", id)
        );
        for (Map<String, Object> map : maps) {

            List<FrLatenceFollowPic> followPics = frLatenceFollowPicService.selectList(
                    new EntityWrapper<FrLatenceFollowPic>().where("latence_follow_id ={0}", map.get("id"))
            );
            map.put("followPic", followPics);
            PersonnelInfo personnelInfo = personnelInfoService.selectOne(
                    new EntityWrapper<PersonnelInfo>().setSqlSelect("RelName").where("ID={0} and Status=0", map.get("personalId"))
            );
            if (personnelInfo != null) {
                map.put("followName", personnelInfo.getRelName());
            }

        }


        return JsonResult.success(maps);
    }

    /**
     * 更新单条数据
     *
     * @param frLatenceFollow 注意CustomerCode 必须要有值
     * @return
     * @throws YJException
     */
    public Integer toUpdateFollow(FrLatenceFollow frLatenceFollow) throws YJException {
        Integer successCount = 0;
        if (frLatenceFollow != null) {
            if (StringUtils.isEmpty(frLatenceFollow.getId())) {
                throw new YJException(YJExceptionEnum.PARAM_ERROR);
            }
            successCount = baseMapper.updateById(frLatenceFollow);
        }
        return successCount;
    }

    /**
     * 插入数据
     *
     * @param frLatenceFollow
     * @return
     * @throws YJException
     */
    public Integer toInterFollow(FrLatenceFollow frLatenceFollow) throws YJException {
        Integer successCount = 0;
        if (frLatenceFollow != null) {
            if (StringUtils.isEmpty(frLatenceFollow.getCustomerCode()) || StringUtils.isEmpty(frLatenceFollow.getClientId())
                    || StringUtils.isEmpty(frLatenceFollow.getPersonalId()) || StringUtils.isEmpty(frLatenceFollow.getShopId())) {
                throw new YJException(YJExceptionEnum.PARAM_ERROR);
            }
            frLatenceFollow.setId(UUIDUtils.generateGUID());
            frLatenceFollow.setType(0);
            frLatenceFollow.setUsing(true);
            if (frLatenceFollow.getCreateTime() == null) {
                frLatenceFollow.setCreateTime(new Date());
            }
            successCount = baseMapper.insert(frLatenceFollow);
        }
        return successCount;
    }
}
