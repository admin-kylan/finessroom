package com.yj.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.DateUtils;
import com.yj.dal.model.FrStoreSingle;
import com.yj.dal.param.OperaterParam;
import com.yj.service.service.IFrStoreSingleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 门店单个设置表 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-04
 */
@RestController
@RequestMapping("/frStoreSingle")
public class FrStoreSingleController {


    @Autowired
    IFrStoreSingleService service;

    /**
     * @Description: 读取单个门店设置
     * @Author: 欧俊俊
     * @Date: 2018/9/5 14:09
     */
    @GetMapping("/getSingleShopSet")
    public JsonResult getOne(HttpServletRequest request) throws YJException {
        String customerCode = CookieUtils.getCookieValue(request,"code",true);
        if (StringUtils.isEmpty(customerCode)){
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        EntityWrapper<FrStoreSingle> entityWrapper = new EntityWrapper<>();
        entityWrapper.where("CustomerCode = {0}",customerCode);
        Page page = service.selectPage(new Page<>(1, 1),entityWrapper);
        List records = page.getRecords();
        if(records.size()>0){
            FrStoreSingle frStoreSingle = (FrStoreSingle) records.get(0);
            return JsonResult.success(frStoreSingle);
        }else {
            return JsonResult.failMessage("读取失败");
        }
    }

    /**
     * @Description: 修改单个门店设置
     * @Author: 欧俊俊
     * @Date: 2018/9/5 14:09
     */
    @PostMapping("/postUpdateSet")
    @Transactional
    public JsonResult update(@RequestBody FrStoreSingle model) {
        boolean b = service.updateById(model);
        if(b){
            return JsonResult.successMessage("修改成功");
        }
        return JsonResult.failMessage("修改失败");
    }
}

