package com.yj.web.controller;

import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.dal.model.PersonnelInfo;
import com.yj.service.service.impl.FrCustomerCourseProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     *
     * @param request
     * @param businessTypeId
     * @return
     */
    @GetMapping("getMarketUserList")
    public JsonResult getTechnicianBySdaduimId(HttpServletRequest request, String businessTypeId, String userType) {
        String code = CookieUtils.getCookieValue(request, "code", true);
        List<PersonnelInfo> personnelInfos = frCustomerCourseProjectService.getTechnicianBySdaduimId(userType, businessTypeId, code);
        return JsonResult.success(personnelInfos);

    }

    /**
     * 新建项目
     *
     * @param map
     * @param request
     * @return
     * @throws YJException
     */
    @PostMapping("addSaveCustomer")
    public JsonResult addSaveCustomer(@RequestBody Map<String, String> map, HttpServletRequest request) throws YJException {
        if (map == null) {
            throw new YJException(YJExceptionEnum.OBJECT_NOT_FOUND);
        }

        JsonResult jsonResult = null;
        frCustomerCourseProjectService.addSaveCustomer(map);
        return JsonResult.success(map);

    }
}