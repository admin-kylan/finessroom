package com.yj.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.StringUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dto.FrGroupCourseDTO;
import com.yj.dal.model.FrGroupCourceRelation;
import com.yj.dal.model.FrGroupCourse;
import com.yj.service.service.IFrGroupCourceRelationService;
import com.yj.service.service.IFrGroupCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-08
 */
@RestController
@RequestMapping("/frGroupCourse")
public class FrGroupCourseController {
    @Autowired
    private IFrGroupCourseService iFrGroupCourseService;
    @Autowired
    private IFrGroupCourceRelationService iFrGroupCourceRelationService;
    @GetMapping("/change/staus")
    public JsonResult changeStaus(String id,String flag){
        FrGroupCourse group = new FrGroupCourse();

        if("1".equals(flag)){//删除
            group.setIsUsing(0);
        }else if("2".equals(flag)){//停课
            group.setClassStatus(0);
        }else{
            group.setClassStatus(1);
        }

        group.setUpdateTime(new Date());
        iFrGroupCourseService.update(group,new EntityWrapper<FrGroupCourse>().where("id={0}",id));

        return JsonResult.successMessage("操作成功");
    }

    @PostMapping("/saveOrUpdate")
    public JsonResult saveOrUpdate(@RequestBody FrGroupCourseDTO course, HttpServletRequest request){
        String code = CookieUtils.getCookieValue(request, "code", true);
        FrGroupCourse  group = new FrGroupCourse();
        BeanUtils.copyProperties(course,group);

        if(StringUtils.isNotEmpty(group.getId())){
            iFrGroupCourceRelationService.delete(new EntityWrapper<FrGroupCourceRelation>().where("private_cource_id={0}",group.getId()));
            iFrGroupCourseService.update(group,new EntityWrapper<FrGroupCourse>().where("id={0}",course.getId()));

        }else{
            group.setId(UUIDUtils.generateGUID());
            group.setCustomerCode(code);
            group.setIsUsing(1);
            group.setCreateTime(new Date());
            group.setClassStatus(1);
            iFrGroupCourseService.insert(group);
        }
        FrGroupCourceRelation relation = new FrGroupCourceRelation();
        relation.setId(UUIDUtils.generateGUID());
        relation.setCreateTime(new Date());
        relation.setCustomerCode(code);
        relation.setPrivateCourceId(group.getId());
        relation.setTrainingSeriesId(course.getTrainSeriesIds());
        iFrGroupCourceRelationService.insert(relation);

        return JsonResult.successMessage("操作成功");
    }

    @RequestMapping(value="/list",method={RequestMethod.GET,RequestMethod.POST})
    public JsonResult list(@RequestParam Map<String, Object> params, HttpServletRequest request) throws YJException {
        String code = CookieUtils.getCookieValue(request, "code", true);
        params.put("code",code);

        return JsonResult.success(iFrGroupCourseService.queryPage(params));
    }

}

