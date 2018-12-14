package com.yj.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.dal.model.FrClientLifeType;
import com.yj.dal.model.FrClientWorkType;
import com.yj.service.service.IFrClientWorkTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 客户工作行业 类型 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-08
 */
@RestController
@RequestMapping("/frClientWorkType")
public class FrClientWorkTypeController {

    @Autowired
    IFrClientWorkTypeService iFrClientWorkTypeService;


    @RequestMapping("/getWorks")
    public JsonResult getWorks() throws YJException {
        List<FrClientWorkType> frClientWorkTypes = iFrClientWorkTypeService.getWorks();
        return JsonResult.success(frClientWorkTypes);
    }

    @GetMapping("/getIndustry")
    public JsonResult getIndustry(HttpServletRequest request) throws YJException {
        String customerCode = CookieUtils.getCookieValue(request, "code", true);
        List<FrClientWorkType> frClientWorkTypes = iFrClientWorkTypeService.selectList(
                new EntityWrapper<FrClientWorkType>().where("type={0}", 1).
                        where("CustomerCode={0}", customerCode).and("is_using=1")
        );
        return JsonResult.success(frClientWorkTypes);
    }
}

