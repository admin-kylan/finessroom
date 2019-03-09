package com.yj.web.controller;

import com.yj.common.result.JsonResult;
import com.yj.service.service.impl.FinancialServiceImpl;
import com.yj.service.service.impl.FrEducationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * className FinancialController
 * by Kylan
 * 财务报表controller
 * @author Kylan
 * @date 2019-03-03 12:07
 */
@RestController
@RequestMapping("/financial")
public class FinancialController {



    @Autowired
    private FinancialServiceImpl financialService;

    /**
     * 个人行为绩效
     * @param request
     * @return
     */
    @GetMapping("findEducationFinancialList")
    public JsonResult findEducationFinancialList(HttpServletRequest request){
        String code = request.getParameter("code");
        String shopId = request.getParameter("shopId");
        String eduType = request.getParameter("eduType");
        String beginDate = request.getParameter("beginDate");
        String endDate = request.getParameter("endDate");
        String name = request.getParameter("name");
        return JsonResult.success(financialService.findEducationFinancialList(shopId, eduType, beginDate, endDate, name, code));
    }

    /**
     * 课程内容分析
     * @param request
     * @return
     */
    @GetMapping("findCourseAnalysisList")
    public JsonResult findCourseAnalysisList(HttpServletRequest request){
        Map map = request.getParameterMap();
        return JsonResult.success(financialService.findCourseAnalysisList(map));
    }

}
