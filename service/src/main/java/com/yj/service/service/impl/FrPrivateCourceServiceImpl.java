package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.util.PageUtils;
import com.yj.common.util.Query;
import com.yj.dal.dao.FrPrivateCourceMapper;
import com.yj.dal.dto.FrPrivateCourceDTO;
import com.yj.dal.model.*;
import com.yj.service.base.BaseServiceImpl;
import com.yj.service.service.IFrPrivateCourceRelationService;
import com.yj.service.service.IFrPrivateCourceService;
import com.yj.service.service.IFrPrivatePackageRelationService;
import com.yj.service.service.IFrTrainingSeriesService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-14
 */
@Service
public class FrPrivateCourceServiceImpl extends BaseServiceImpl<FrPrivateCourceMapper, FrPrivateCource> implements IFrPrivateCourceService {

    @Autowired
    private IFrPrivateCourceRelationService IFrPrivateCourceRelationService;
    @Autowired
    private IFrTrainingSeriesService iFrTrainingSeriesService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) throws YJException {
        String code = String.valueOf(params.get("code"));
        if (StringUtils.isEmpty(code)) {
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        Page page = new Page();
        if (params.get("shopId") != null) {
            Map<String, Object> map = new HashMap<>();
            if (params.get("curPage") == null) {
                map.put("page", "1");
            } else {
                map.put("page", params.get("curPage"));
            }
            if (params.get("limit") == null) {
                map.put("limit", "10");
            } else {
                map.put("limit", params.get("limit"));
            }


            page = new Query<FrPrivateCource>(map).getPage();
            List<FrPrivateCource> frPrivateCources = baseMapper.findCource(page, params.get("shopId").toString());
            page.setRecords(frPrivateCources);
        }else  if (params.get("sdaduimId") != null) {
            Map<String, Object> map = new HashMap<>();
            if (params.get("curPage") == null) {
                map.put("page", "1");
            } else {
                map.put("page", params.get("curPage"));
            }
            if (params.get("limit") == null) {
                map.put("limit", "10");
            } else {
                map.put("limit", params.get("limit"));
            }

            page = new Query<FrPrivateCource>(map).getPage();
            List<FrPrivateCource> frPrivateCources = baseMapper.findPrivateCource(page, params.get("sdaduimId").toString());
            page.setRecords(frPrivateCources);
        } else {
            String queryProperty = " private_image AS privateImage, class_scheduling AS classScheduling, customer_code AS customerCode, update_time AS updateTime, is_show_desk AS isShowDesk, time, create_time AS createTime, valid_time AS validTime, name, update_user AS updateUser, class_info AS classInfo, promotion_price AS promotionPrice, create_user AS createUser, assign_teacher AS assignTeacher, market_price AS marketPrice, class_scheduling_type AS classSchedulingType, valid_time_type AS validTimeType, id, is_account_spending AS isAccountSpending, remain_cource_num AS remainCourceNum, member_price AS memberPrice, is_using AS isUsing, assign_teacher_type AS assignTeacherType";
            page = this.selectPage(
                    new Query<FrPrivateCource>(params).getPage(),
                    new EntityWrapper<FrPrivateCource>()
                            .where("is_using = 1 ")
                            .orderBy("create_time desc")
                            .setSqlSelect(queryProperty)
            );
        }

        final List<String> courseList = new ArrayList<>();
        final List<FrPrivateCourceDTO> vo = new ArrayList<>();
        page.getRecords().stream().forEach((course) -> {
            courseList.add(((FrPrivateCource) course).getId());
            FrPrivateCourceDTO dto = new FrPrivateCourceDTO();
            BeanUtils.copyProperties(course, dto);
            vo.add(dto);
        });
        if (courseList.size() > 0) {
            final List<FrPrivateCourceRelation> pageRelaton = IFrPrivateCourceRelationService.selectList(new EntityWrapper<FrPrivateCourceRelation>()
                    .where("is_using = 1")
                    .in("private_cource_id", courseList.toArray())
                    .setSqlSelect("id,private_cource_id as privateCourceId,training_series_id as trainingSeriesId"));
            vo.stream().forEach((course) -> {
                FrTrainingSeries optional = null;
                for (int i = 0; i < pageRelaton.size(); i++) {
                    optional = iFrTrainingSeriesService.selectById(pageRelaton.get(i).getTrainingSeriesId());
                    if (optional != null && pageRelaton.get(i).getPrivateCourceId().equals(course.getId())) {
                        course.setTrainingSeriesIds(pageRelaton.get(i).getTrainingSeriesId());
                        course.setCourseName(optional.getName());
                        break;
                    }
                }
            });
        }
        page.setRecords(vo);

        return new PageUtils(page);
    }
}
