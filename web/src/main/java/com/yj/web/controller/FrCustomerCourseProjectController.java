package com.yj.web.controller;

import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.dal.model.PersonnelInfo;
import com.yj.service.service.impl.FrCustomerCourseProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * by Kylan
 * 课程项目/产品 类
 * @author YongL
 * @date 2018/12/17
 */
@RestController
@RequestMapping("/frCustomerCourse")
public class FrCustomerCourseProjectController {


    /**
     * 课程项目/产品实现类
     */
    @Autowired
    private FrCustomerCourseProjectServiceImpl frCustomerCourseProjectService;


    /**
     * 根据businessTypeId 查询技师
     * @param request
     * @param businessTypeId
     * @return
     */
    @GetMapping("/getMarketUserList")
    public JsonResult getTechnicianBySdaduimId(HttpServletRequest request, String businessTypeId, String userType){
        String code = CookieUtils.getCookieValue(request, "code", true);
        List<PersonnelInfo> personnelInfos =  frCustomerCourseProjectService.getTechnicianBySdaduimId(userType, businessTypeId, code);
        return JsonResult.success(personnelInfos);

    }
}
