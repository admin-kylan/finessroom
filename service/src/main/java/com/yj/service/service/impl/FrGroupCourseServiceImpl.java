package com.yj.service.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.util.PageUtils;
import com.yj.common.util.Query;
import com.yj.dal.dao.FrGroupCourceRelationMapper;
import com.yj.dal.dao.FrTrainingSeriesMapper;
import com.yj.dal.dto.FrGroupCourseDTO;
import com.yj.dal.model.FrGroupCourceRelation;
import com.yj.dal.model.FrGroupCourse;
import com.yj.dal.dao.FrGroupCourseMapper;
import com.yj.dal.model.FrPrivatePackage;
import com.yj.dal.model.FrTrainingSeries;
import com.yj.service.service.IFrGroupCourseService;
import com.yj.service.base.BaseServiceImpl;
import com.yj.service.service.IFrTrainingSeriesService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-08
 */
@Service
public class FrGroupCourseServiceImpl extends BaseServiceImpl<FrGroupCourseMapper, FrGroupCourse> implements IFrGroupCourseService {

    @Autowired
    private FrGroupCourseMapper frGroupCourseMapper;
    @Autowired
    private FrGroupCourceRelationMapper frGroupCourceRelationMapper;
    @Autowired
    private FrTrainingSeriesMapper frTrainingSeriesMapper;

    @Override
    public Object queryPage(Map<String, Object> params) throws YJException {
        String code = String.valueOf(params.get("code")),
          seriesId = String.valueOf(params.get("seriesId"));
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        Page page = new Page();
        if (seriesId == "null") {
            page = this.selectPage(new Query<FrGroupCourse>(params).getPage(),
                    new EntityWrapper<FrGroupCourse>()
                            .where("is_using = 1 and customer_code={0} ", code)
                            .orderBy("create_time desc")
            );
        } else {
            page = this.selectPage(new Query<FrGroupCourse>(params).getPage(),
                    new EntityWrapper<FrGroupCourse>()
                            .where("is_using = 1 and customer_code={0} and series_id={1}", code, seriesId)
                            .orderBy("create_time desc")
            );
        }

        List<FrGroupCourseDTO> dtos = new ArrayList<>();
        List<FrGroupCourse> list = (List<FrGroupCourse>) page.getRecords();
        list.stream().forEach((course) -> {
            FrGroupCourseDTO dto = new FrGroupCourseDTO();
            BeanUtils.copyProperties(course, dto);
            FrGroupCourceRelation p = new FrGroupCourceRelation();
            p.setPrivateCourceId(course.getId());
            FrGroupCourceRelation relation = frGroupCourceRelationMapper.selectOne(p);
            if (relation != null) {
                FrTrainingSeries series = new FrTrainingSeries();
                if(relation.getTrainingSeriesId()!=null){
                    series.setId(relation.getTrainingSeriesId());
                    FrTrainingSeries s = frTrainingSeriesMapper.selectOne(series);
                    dto.setTrainSeriesIds(s.getId());
                    dto.setTrainSeriesName(s.getName());
                }
            }
            dtos.add(dto);
        });
        page.setRecords(dtos);

        return new PageUtils(page);
    }
}
