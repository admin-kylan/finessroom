package com.yj.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.dal.model.FrClientLifeRelate;
import com.yj.dal.model.FrClientLifeType;
import com.yj.service.service.IFrClientLifeRelateService;
import com.yj.service.service.IFrClientLifeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 客户生活详情 类型 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-02
 */
@RestController
@RequestMapping("/frClientLifeType")
public class FrClientLifeTypeController {
    @Autowired
    IFrClientLifeTypeService iFrClientLifeTypeService;

    @GetMapping("/getDetails")
    public JsonResult getDetails() throws YJException {

        List<FrClientLifeType> frClientLifeTypes = iFrClientLifeTypeService.getDetails();

        return JsonResult.success(frClientLifeTypes);
    }

    @GetMapping("/getVehicle")
    public JsonResult getVehicle(HttpServletRequest request) throws YJException {
        String customerCode = CookieUtils.getCookieValue(request, "code", true);
        List<FrClientLifeType> frClientLifeTypes = iFrClientLifeTypeService.selectList(
                new EntityWrapper<FrClientLifeType>().where("type={0}", 4).
                        where("CustomerCode={0}", customerCode).and("is_using=1")
        );
        return JsonResult.success(frClientLifeTypes);
    }

}

