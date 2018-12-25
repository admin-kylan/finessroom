package com.yj.web.controller;

import com.alibaba.fastjson.JSON;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrClient;
import com.yj.service.service.IFrPersonalDetailsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/frPersonalDetails")
public class FrPersonalDetailsController {

    @Autowired
    IFrPersonalDetailsService iFrPersonalDetailsService;

    /**
     * 获取个人详情
     */
    @GetMapping("/getPersonalDetails")
    public JsonResult getPersonalDetails(@RequestParam String clientId) throws YJException {
        return iFrPersonalDetailsService.getPersonalDetails(clientId );

    }

    /**
     * 保存个人详情
     */
    @ResponseBody
    @PostMapping("/updatePersonalDetails")
    public JsonResult updatePersonalDetails(@RequestBody FrClient frClient, HttpServletRequest request) throws YJException {
        return  iFrPersonalDetailsService.updatePersonalDetails(frClient, request);
    }
}
