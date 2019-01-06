package com.yj.web.controller;

import com.yj.common.result.JsonResult;
import com.yj.service.service.impl.FrEducationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * className FrEducationAppRest
 * by Kylan
 * 私教接口类
 * @author Kylan
 * @date 2019-01-05 11:22
 */
@RestController
@RequestMapping("/frEducationApp/private")
public class FrEducationPrivateAppRestController {

    private static final Integer PRIVATE = 1;

    @Autowired
    private FrEducationServiceImpl frEducationService;


    /**
     * 预约团体课程接口
     *
     * @param request
     * @return
     */
    @PostMapping("reserve")
    public JsonResult addReservePublic(@RequestBody Map<String, String> map, HttpServletRequest request) throws Exception {
        frEducationService.addReserveGroup(map, PRIVATE);
        return JsonResult.success(null);

    }

    /**
     * 取消预约
     *
     * @param request
     * @return
     */
    @PostMapping("cancel")
    public JsonResult cancelReserveGroup(@RequestBody Map<String, String> map, HttpServletRequest request){
        frEducationService.cancelReserveGroup(map);
        return JsonResult.success(null);
    }

    /**
     * 查询教练的的课程的接口,团教
     * @return
     */
    @GetMapping("coach/list")
    public JsonResult findEducationPublicListCoach(HttpServletRequest request){
        String shopId = request.getParameter("shopId");
        String CustomerCode = request.getParameter("CustomerCode");
        String sdaduimId = request.getParameter("sdaduimId");
        String executeDate = request.getParameter("executeDate");
        List<Map<String, Object>> list = frEducationService.findEducationPublicListCoach(shopId, executeDate, sdaduimId, CustomerCode,PRIVATE);
        return JsonResult.success(list);
    }

    /**
     * 查询教练的的课程的接口,团教
     * @return
     */
    @GetMapping("room/list")
    public JsonResult findEducationPublicListRoom(HttpServletRequest request){
        String shopId = request.getParameter("shopId");
        String CustomerCode = request.getParameter("CustomerCode");
        String sdaduimId = request.getParameter("sdaduimId");
        String executeDate = request.getParameter("executeDate");
        List<Map<String, Object>> list = frEducationService.findEducationPublicListRoom(shopId, sdaduimId, executeDate, CustomerCode,PRIVATE);
        return JsonResult.success(list);
    }

    /**
     * 查询教练的的课程的接口,团教
     * @return
     */
    @GetMapping("date/list")
    public JsonResult findEducationPublicListDate(HttpServletRequest request){
        String shopId = request.getParameter("shopId");
        String CustomerCode = request.getParameter("CustomerCode");
        String sdaduimId = request.getParameter("sdaduimId");
        String executeBeginDate = request.getParameter("executeBeginDate");
        String executeEndDate = request.getParameter("executeEndDate");
        List<Map<String, Object>> list = frEducationService.findEducationPublicListDate(shopId, sdaduimId, executeBeginDate, executeEndDate, CustomerCode,PRIVATE);
        return JsonResult.success(list);
    }
}
