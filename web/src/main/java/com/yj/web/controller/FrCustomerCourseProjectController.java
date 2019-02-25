package com.yj.web.controller;

import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.dal.model.*;
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
//        String id = CookieUtils.getCookieValue(request, "id", true);
//        String name = CookieUtils.getCookieValue(request, "name", true);
//        map.put("createUserId", id);
//        map.put("createUserName", name);
        //JsonResult jsonResult = null;
        frCustomerCourseProjectService.addSaveCustomer(map);
        return JsonResult.success(map);

    }

//    /**
//     * 用户id查询全部
//     * @param request
//     * @return
//     */
//    @GetMapping("fetchOrderListByUserId")
//    public JsonResult fetchOrderListByUserId( HttpServletRequest request){
//        String cid = request.getParameter("cid");
//        String code = request.getParameter("code");
//        String shopId = request.getParameter("shopId");
//
//        List list = frCustomerCourseProjectService.getOrderListByCid(shopId, code, cid);
//        return JsonResult.success(list);
//    }

    /**
     * 查询启用记录
     * @param request
     * @return
     */
    @GetMapping("findStartProjectRecord")
    public JsonResult findStartProjectRecord(HttpServletRequest request){

        String shopId = request.getParameter("shopId");
        String code = request.getParameter("code");
        String cid = request.getParameter("cid");
        List<Map<String, Object>> list = frCustomerCourseProjectService.findStartProjectRecord(shopId, cid, code);
        return JsonResult.success(list);
    }

    /**
     * 启用
     * @param request
     * @return
     */
    @PostMapping("customerStar")
    public JsonResult starCustomer(@RequestBody Map<String, String> map,HttpServletRequest request){

        try {
            frCustomerCourseProjectService.starCustomer(map);
        } catch (Exception e) {
            return JsonResult.failMessage(e.getMessage());
        }
        return JsonResult.success(null);
    }


    /**
     * 查询补余记录
     * @param request
     * @return
     */
    @GetMapping("findCustomerRemnantRecord")
    public JsonResult findCustomerRemnantRecord(HttpServletRequest request){

        String shopId = request.getParameter("shopId");
        String code = request.getParameter("code");
        String cid = request.getParameter("cid");
        List<Map<String, Object>> list = frCustomerCourseProjectService.findCustomerRemnantRecord(shopId, cid, code);
        return JsonResult.success(list);
    }

    /**
     * 补余
     * @param request
     * @return
     */
    @PostMapping("customerRemnant")
    public JsonResult customerRemnant(@RequestBody Map<String, String> map, HttpServletRequest request){

        try {
            frCustomerCourseProjectService.customerRemnant(map);
        } catch (Exception e) {
            return JsonResult.failMessage(e.getMessage());
        }

        return JsonResult.success(null);
    }

    /**
     * 查询补余记录
     * @param request
     * @return
     */
    @GetMapping("findCustomerExtensionRecord")
    public JsonResult findCustomerExtensionRecord(HttpServletRequest request){

        String shopId = request.getParameter("shopId");
        String code = request.getParameter("code");
        String cid = request.getParameter("cid");
        List<Map<String, Object>> list = frCustomerCourseProjectService.findCustomerExtensionRecord(shopId, cid, code);
        return JsonResult.success(list);
    }

    /**
     * 延期
     * @param request
     * @return
     */
    @PostMapping("customerExtension")
    public JsonResult customerExtension(@RequestBody Map<String, String> map, HttpServletRequest request){

        AddProject projectOrder = frCustomerCourseProjectService.customerExtension(map);
        return JsonResult.success(projectOrder);
    }

    /**
     * 转让表
     * @param request
     * @param map
     * @return
     */
    @PostMapping("customerTransfer")
    public JsonResult setReturnAddProject(@RequestBody Map<String, String> map, HttpServletRequest request){

        try {
            frCustomerCourseProjectService.setTurnProject(map);
        } catch (Exception e) {
            return JsonResult.failMessage(e.getMessage());
        }
        return JsonResult.success(map);
    }

    /**
     * 退费表
     * @param request
     * @param map
     * @return
     */
    @PostMapping("customerReturnMoney")
    public JsonResult customerReturnMoney(@RequestBody Map<String, String> map, HttpServletRequest request){
       // String cid = map.get("cid");
       // String name = CookieUtils.getCookieValue(request, "name", true);

        frCustomerCourseProjectService.setReturnAddProject(map);
        return JsonResult.success(map);
    }

    /**
     * 查询退费
     * @param request
     * @return
     */
    @GetMapping("returnAddProjects")
    public JsonResult getListReturnAddProject( HttpServletRequest request){
        String cid = request.getParameter("cid");
        String shopId = request.getParameter("shopId");
        String code = request.getParameter("code");
        List<ReturnAddProject> returnAddProjects = frCustomerCourseProjectService.getListReturnAddProject(shopId, cid, code);
        return JsonResult.success(returnAddProjects);
    }


    /**
     * 查询转让记录
     * @param request
     * @return
     */
    @GetMapping("turnProjects")
    public JsonResult getListTurnProject( HttpServletRequest request){
        String cid = request.getParameter("cid");
        String shopId = request.getParameter("shopId");
        String code = request.getParameter("code");
        List<TurnProject> turnProjects = frCustomerCourseProjectService.getListTurnProject(shopId, cid, code);
        return JsonResult.success(turnProjects);
    }

    /**
     * 冲销
     * @param request
     * @return
     */
    @PostMapping("turnProjectsDelete")
    public JsonResult turnProjectsDelete(@RequestBody Map<String, String> map, HttpServletRequest request){
        String id = map.get("id");
        frCustomerCourseProjectService.deleteTurnProject(id);
        return JsonResult.success(new HashMap<>());
    }


    /**
     * 查询数组
     * @param request
     * @return
     */
    @GetMapping("list")
    public JsonResult getProjectList(HttpServletRequest request){

        String cid = request.getParameter("cid");
        String type = request.getParameter("type");
        String status = request.getParameter("status");
        String shopId = request.getParameter("shopId");
        String timeType = request.getParameter("timeType");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String name = request.getParameter("name");
        String orderType = request.getParameter("orderType");
        String code = CookieUtils.getCookieValue(request, "code", true);
        List list = frCustomerCourseProjectService.getProjectList(type,status,shopId,timeType,startDate,endDate,name,code,orderType,cid);
        return JsonResult.success(list);
    }

    /**
     * 查询数组
     * @param request
     * @return
     */
    @GetMapping("getCourseList")
    public JsonResult getCourseList(HttpServletRequest request){

        String shopId = request.getParameter("shopId");
        String code = request.getParameter("code");
        String sdaduimId = request.getParameter("sdaduimId");
        String eduType = request.getParameter("eduType");
        List<Map<String, Object>> list = frCustomerCourseProjectService.getCourseList(shopId, sdaduimId, code, eduType);
        return JsonResult.success(list);
    }

    /**
     * 查询下拉框list
     * @param request
     * @return
     */
    @GetMapping("getProjectListSelect")
    public JsonResult getProjectListSelect(HttpServletRequest request){

        String shopId = request.getParameter("shopId");
        String code = request.getParameter("code");
        String cid = request.getParameter("cid");
        List<Map<String, Object>> list = frCustomerCourseProjectService.getProjectListSelect(shopId, cid, code);
        return JsonResult.success(list);
    }


  /**
     * 查询套餐
     * @param request
     * @return
     */
    @GetMapping("getCoursePackageCourseId")
    public JsonResult getCoursePackageCourseId(HttpServletRequest request){

        String shopId = request.getParameter("shopId");
        String code = request.getParameter("code");
        String sdaduimId = request.getParameter("sdaduimId");
        String courseId = request.getParameter("courseId");
        List<Map<String, Object>> list = frCustomerCourseProjectService.getCoursePackageCourseId(shopId, sdaduimId, code, courseId);
        return JsonResult.success(list);
    }






}