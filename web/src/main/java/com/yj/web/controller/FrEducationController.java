package com.yj.web.controller;

import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.service.service.impl.FrEducationPublicServiceImpl;
import com.yj.service.service.impl.FrEducationServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * className FrEducationAppRest
 * by Kylan
 *
 * @author Kylan
 * @date 2019-01-05 11:22
 */
@RestController
@RequestMapping("/frEducation")
public class FrEducationController {


    /**
     * pc 端
     */
    private static final Integer PLATFORM_TYPE = 1;

    @Autowired
    private FrEducationPublicServiceImpl frEducationPublicService;

    @Autowired
    private FrEducationServiceImpl frEducationService;

    /**
     * 查询教练
     * @param request
     * @return
     */
    @GetMapping("findCoachList")
    public JsonResult findCoachList(HttpServletRequest request){
        String code = request.getParameter("code");
        String shopId = request.getParameter("shopId");
        return JsonResult.success(frEducationPublicService.findCoachList(code,shopId));
    }

    /**
     * 查询教练
     * @param request
     * @return
     */
    @GetMapping("findSdaduimList")
    public JsonResult findSdaduimList(HttpServletRequest request){
        String code = request.getParameter("code");
        String shopId = request.getParameter("shopId");
        return JsonResult.success(frEducationPublicService.findSdaduimList(code,shopId));
    }

    /**
     * 查询列表
     * @param request
     * @return
     */
    @GetMapping("findEducationList")
    public JsonResult findEducationList(HttpServletRequest request){
        String code = request.getParameter("code");
        String shopId = request.getParameter("shopId");
        String coachId = request.getParameter("coachId");
        String sdaduimId = request.getParameter("sdaduimId");
        String beginDate = request.getParameter("beginDate");
        String endDate = request.getParameter("endDate");
        String eduType = request.getParameter("eduType");
        String reserveType = request.getParameter("reserveType");
        return JsonResult.success(frEducationPublicService.findEducationList(code,shopId, coachId, sdaduimId, beginDate, endDate, eduType,reserveType));
    }


    /**
     * 查询指定教学中的待确认会员
     * @param request
     * @return
     */
    @GetMapping("findMemberReserveStatusList")
    public JsonResult findMemberReserveStatusList(HttpServletRequest request){
        String eduId = request.getParameter("eduId");
        String reserveStatus = request.getParameter("reserveStatus");
        String searchInput = request.getParameter("searchInput");
        return JsonResult.success(frEducationPublicService.findMemberReserveStatusList(eduId, reserveStatus,searchInput));
    }

    /**
     * 查询指定教学中的待确认会员
     * @param request
     * @return
     */
    @PostMapping("changeEduClientStatus")
    public JsonResult changeEduClientStatus(@RequestBody Map<String, String> map, HttpServletRequest request){
        try {
            frEducationPublicService.changeEduClientStatus(map);
        } catch (Exception e) {
            return JsonResult.failMessage(e.getMessage());
        }
        return JsonResult.success(null);

    }

    /**
     * 预约详情，根据Id 查询数据
     * @param request
     * @return
     */
    @GetMapping("findEducationById")
    public JsonResult findEducationById(HttpServletRequest request){
        String eduId = request.getParameter("eduId");
        return JsonResult.success(frEducationPublicService.findEducationById(eduId));
    }

    /**
     * 获取客户信息---根据手机号
     * @param CustomerCode
     * @param mobile
     * @return
     * @throws
     */
    @GetMapping("/getClientList")
    public JsonResult getClientList(@RequestParam String CustomerCode,@RequestParam String mobile) {
        return JsonResult.success(frEducationPublicService.getClientList(CustomerCode,mobile));
    }

    /**
     * 根据roomId 查询
     * @param roomId
     * @return
     * @throws
     */
    @GetMapping("/getGroupRoomSeatById")
    public JsonResult getGroupRoomSeatById(@RequestParam String roomId) {
        return JsonResult.success(frEducationPublicService.getGroupRoomSeatById(roomId));
    }

    /**
     * 新增预约会员信息
     * @param request
     * @return
     */
    @PostMapping("saveEducationItem")
    public JsonResult saveEducationItem(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        try {
            frEducationPublicService.saveEducationItem(map);
        } catch (Exception e) {
            return JsonResult.failMessage(e.getMessage());
        }
        return JsonResult.success(null);

    }

    /**
     * 查询已经付钱过的用户
     * @param eduId
     * @return
     * @throws
     */
    @GetMapping("/findMemberOrderListByEduId")
    public JsonResult findMemberOrderListByEduId(@RequestParam String eduId, @RequestParam String searchInput){
        return JsonResult.success(frEducationPublicService.findMemberOrderListByEduId(eduId, searchInput));
    }

    /**
     * 根据卡号，手机号，票券，团购号查询卡号
     * @param request
     * @return
     * @throws
     */
    @GetMapping("/findMemberCardByInput")
    public JsonResult findMemberCardByInput(HttpServletRequest request){
        String searchInput = request.getParameter("searchInput");
        String shopId = request.getParameter("shopId");
        String configStartClass = request.getParameter("configStartClass");
        String code = request.getParameter("code");
        return JsonResult.success(frEducationPublicService.findMemberCardByInput(searchInput, shopId, configStartClass, code));
    }

    /**
     * 查看训练计划
     * @param courseId
     * @return
     * @throws
     */
    @GetMapping("/findShowTrainingPlan")
    public JsonResult findShowTrainingPlan(@RequestParam String courseId){
        return JsonResult.success(frEducationPublicService.findShowTrainingPlan(courseId));
    }

    /**
     * 查看课程
     * @param courseId
     * @return
     * @throws
     */
    @GetMapping("/findCourseById")
    public JsonResult findCourseById(@RequestParam String courseId){
        return JsonResult.success(frEducationPublicService.findCourseById(courseId));
    }

     /**
     * 查看排课配置的课程
     * @param eduId
     * @return
     * @throws
     */
    @GetMapping("/findSettCourseEdu")
    public JsonResult findSettCourseEdu(@RequestParam String eduId){
        return JsonResult.success(frEducationPublicService.findSettCourseEdu(eduId));
    }

    /**
     * 查看该用户是否买了该课程
     * @param courseId
     * @return
     * @throws
     */
    @GetMapping("/findCourseByClientId")
    public JsonResult findCourseByClientId(@RequestParam String courseId, @RequestParam String clientId,
                                           @RequestParam String cardId, @RequestParam String code,
                                           @RequestParam String num, @RequestParam String shopId){
        return JsonResult.success(frEducationPublicService.findCourseByClientId(courseId, clientId, cardId, code, num,shopId));
    }

    /**
     * 保存修改对课程的修改
     * @param map
     * @return
     * @throws
     */
    @PostMapping("/updateProjectOrder")
    public JsonResult updateProjectOrder(@RequestBody Map<String, String> map){
        try {
            frEducationPublicService.updateProjectOrder(map);
        } catch (Exception e) {
            return JsonResult.failMessage(e.getMessage());
        }
        return JsonResult.success(null);
    }

    /**
     * 更新卡的权益
     * @param map
     * @return
     * @throws
     */
    @PostMapping("/updateCardDetail")
    public JsonResult updateCardDetail(@RequestBody Map<String, String> map){
        try {
            frEducationPublicService.updateCardDetail(map);
        } catch (Exception e) {
            return JsonResult.failMessage(e.getMessage());
        }
        return JsonResult.success(null);
    }


    /**
     * 保存预约
     * @param map
     * @return
     * @throws
     */
    @PostMapping("/saveEduMemberOrder")
    public JsonResult saveEduMemberOrder(@RequestBody Map<String, String> map){
        try {
            frEducationPublicService.saveEduMemberOrder(map);
        } catch (Exception e) {
            return JsonResult.failMessage(e.getMessage());
        }
        return JsonResult.success(null);
    }

    /**
     * 保存预约
     * @param map
     * @return
     * @throws
     */
    @PostMapping("/startEduClass")
    public JsonResult startEduClass(@RequestBody Map<String, String> map){
        try {
            frEducationPublicService.startEduClass(map);
        } catch (Exception e) {
            return JsonResult.failMessage(e.getMessage());
        }
        return JsonResult.success(null);
    }

    /**
     * 冲销
     * @param map
     * @return
     * @throws
     */
    @PostMapping("/cancelMemberOrder")
    public JsonResult cancelMemberOrder(@RequestBody Map<String, String> map){
        try {
            frEducationPublicService.cancelMemberOrder(map);
        } catch (Exception e) {
            return JsonResult.failMessage(e.getMessage());
        }
        return JsonResult.success(null);
    }




}
