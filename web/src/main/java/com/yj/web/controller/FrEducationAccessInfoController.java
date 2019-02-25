package com.yj.web.controller;

import com.yj.common.result.JsonResult;
import com.yj.service.service.impl.FrEducationAccessInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * className FrEducationAccessInfoController
 * by Kylan
 *
 * @author Kylan
 * @date 2019-02-16 17:38
 */
@RestController
@RequestMapping("/accessInfo")
public class FrEducationAccessInfoController {


    @Autowired
    private FrEducationAccessInfoServiceImpl frEducationAccessInfoService;

    /**
     * 验证用户
     * @param map
     * @return
     */
    @PostMapping("versionClient")
    public JsonResult versionClient(@RequestBody Map<String, Object> map){
        String code = String.valueOf(map.get("code"));
        String shopId = String.valueOf(map.get("shopId"));
        String mobile = String.valueOf(map.get("mobile"));
        String password = String.valueOf(map.get("password"));
        try {
            frEducationAccessInfoService.versionClient(code,shopId, mobile, password);
        } catch (Exception e) {
            return JsonResult.failMessage(e.getMessage());
        }
        return JsonResult.success(null);
    }

    /**
     * 保存
     * @param map
     * @return
     */
    @PostMapping("saveAccessInfo")
    public JsonResult saveAccessInfo(@RequestBody Map<String, Object> map){

        try {
            frEducationAccessInfoService.saveAccessInfo(map);
        } catch (Exception e) {
            return JsonResult.failMessage(e.getMessage());
        }
        return JsonResult.success(null);
    }

    /**
     * 查询结果
     * @param request
     * @return
     */
    @GetMapping("loadAccessInfoList")
    public JsonResult loadAccessInfoList(HttpServletRequest request){
        String code = request.getParameter("code");
        String shopId = request.getParameter("shopId");
        String searchInput = request.getParameter("searchInput");
        return JsonResult.success(frEducationAccessInfoService.loadAccessInfoList(code,shopId, searchInput));
    }

    /**
     * 冲销
     * @param map
     * @return
     */
    @PostMapping("cancelAccessInfo")
    public JsonResult cancelAccessInfo(@RequestBody Map<String, Object> map){
        String id = String.valueOf(map.get("id"));
        try {
            frEducationAccessInfoService.cancelAccessInfo(id);
        } catch (Exception e) {
            return JsonResult.failMessage(e.getMessage());
        }
        return JsonResult.success(null);
    }

    /**
     * 出场验证
     * @param map
     * @return
     */
    @PostMapping("outputVerification")
    public JsonResult outputVerification(@RequestBody Map<String, Object> map){
        String code = String.valueOf(map.get("code"));
        String shopId = String.valueOf(map.get("shopId"));
        String outputSearch = String.valueOf(map.get("outputSearch"));
        String createId = String.valueOf(map.get("createId"));
        String createName = String.valueOf(map.get("createName"));
        try {
            frEducationAccessInfoService.outputSearch(code,shopId, outputSearch, createId, createName);
        } catch (Exception e) {
            return JsonResult.failMessage(e.getMessage());
        }
        return JsonResult.success(null);
    }

    /**
     * 出场查询
     * @param request
     * @return
     */
    @GetMapping("loadOutputInfoList")
    public JsonResult loadOutputInfoList(HttpServletRequest request){
        String code = request.getParameter("code");
        String shopId = request.getParameter("shopId");
        return JsonResult.success(frEducationAccessInfoService.loadOutputInfoList(code,shopId));
    }

    /**
     * 更换手牌
     * @param map
     * @return
     */
    @PostMapping("changeHandNum")
    public JsonResult changeHandNum(@RequestBody Map<String, Object> map){
        String id = String.valueOf(map.get("id"));
        String num = String.valueOf(map.get("num"));
        try {
            frEducationAccessInfoService.changeHandNum(id,num);
        } catch (Exception e) {
            return JsonResult.failMessage(e.getMessage());
        }
        return JsonResult.success(null);
    }

     /**
     * 计算数量
     * @param request
     * @return
     */
    @GetMapping("calcNum")
    public JsonResult calcNum(HttpServletRequest request){
        String code = request.getParameter("code");
        String shopId = request.getParameter("shopId");
        return JsonResult.success(frEducationAccessInfoService.calcNum(shopId, code));
    }


    /**
     * 根据手机号查询list
     * @param request
     * @return
     */
    @GetMapping("loadCardList")
    public JsonResult loadCardList(HttpServletRequest request){
        String code = request.getParameter("code");
        String shopId = request.getParameter("shopId");
        String mobile = request.getParameter("mobile");
        return JsonResult.success(frEducationAccessInfoService.loadCardList(shopId, code, mobile));
    }

    /**
     * 根据卡号查询当个
     * @param request
     * @return
     */
    @GetMapping("loadCardListOfCardNo")
    public JsonResult loadCardListOfCardNo(HttpServletRequest request){
        String code = request.getParameter("code");
        String shopId = request.getParameter("shopId");
        String cardNo = request.getParameter("cardNo");
        return JsonResult.success(frEducationAccessInfoService.loadCardListOfCardNo(shopId, code, cardNo));
    }

    /**
     * 根据卡号查询具体信息
     * @param request
     * @return
     */
    @GetMapping("loadInfo")
    public JsonResult loadInfo(HttpServletRequest request){
        String code = request.getParameter("code");
        String shopId = request.getParameter("shopId");
        String mobile = request.getParameter("mobile");
        String cardNo = request.getParameter("cardNo");
        return JsonResult.success(frEducationAccessInfoService.loadInfo(shopId, code, mobile, cardNo));
    }

}
