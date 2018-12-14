package com.yj.web.controller;


import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.DateUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.model.FrIndustry;
import com.yj.service.service.IFrIndustryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 行业表 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-04
 */
@RestController
@RequestMapping("/frIndustry")
public class FrIndustryController {

    @Autowired
    IFrIndustryService service;

    /**
     * @Description: 客户认领保护天数列表
     * @Author: 欧俊俊
     * @Date: 2018/9/13 11:43
     */
    @GetMapping("/getProtectDaysList")
    public JsonResult selectList(HttpServletRequest request) throws YJException {
        String customerCode = CookieUtils.getCookieValue(request,"code",true);
        if (StringUtils.isEmpty(customerCode)){
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        List<FrIndustry> frIndustries = service.selectProtectionDaysList(customerCode);
        return JsonResult.success(frIndustries);
    }

    /**
     * @Description: 客户认领设置列表
     * @Author: 欧俊俊
     * @Date: 2018/9/13 11:43
     */
    @GetMapping("/getClaimSetList")
    public JsonResult selectClaimSetList(HttpServletRequest request) throws YJException {
        String customerCode = CookieUtils.getCookieValue(request,"code",true);
        if (StringUtils.isEmpty(customerCode)){
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        List<FrIndustry> frIndustries = service.selectClaimSetList(customerCode);
        return JsonResult.success(frIndustries);
    }

    /**
     * @Description: 客户跟进列表
     * @Author: 欧俊俊
     * @Date: 2018/9/13 11:43
     */
    @GetMapping("/getFollowList")
    public JsonResult selectFollowList(@RequestParam Integer customerType,HttpServletRequest request) throws YJException {
        String customerCode = CookieUtils.getCookieValue(request,"code",true);
        if (StringUtils.isEmpty(customerCode)){
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        List<FrIndustry> frIndustries = service.selectFollowList(customerType,customerCode);
        return JsonResult.success(frIndustries);
    }


    /**
     * @Description: 修改行业设置
     * @Author: 欧俊俊
     * @Date: 2018/9/13 15:06
     */
    @PostMapping("/postUpdateSet")
    public JsonResult updateClaimSetList(@RequestBody List<FrIndustry> frIndustries) throws YJException {
        Boolean b = service.updateIndustrySetList(frIndustries);
        if(b){
            return JsonResult.successMessage("修改成功");
        }
        return JsonResult.failMessage("修改失败");
    }

    @GetMapping("/getClientCount")
    public  JsonResult getClientCount(String clientType)throws YJException{

        return JsonResult.success(service.getClientCount(clientType));
    }
}

