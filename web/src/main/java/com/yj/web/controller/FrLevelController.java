package com.yj.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.DateUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.model.FrLevel;
import com.yj.service.service.IFrLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 会员等级表 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-12
 */
@RestController
@RequestMapping("/frLevel")
public class FrLevelController {

    @Autowired
    IFrLevelService service;
    
    /**
     * @Description: 获取等级列表(用于下拉选择)
     * @Author: 欧俊俊
     * @Date: 2018/9/15 9:47
     */
    @GetMapping("/getListForSelect")
    public JsonResult listForSelect(HttpServletRequest request) {
        String code = CookieUtils.getCookieValue(request, "code", true);
        EntityWrapper<FrLevel> wrapper = new EntityWrapper<>();
        wrapper.setSqlSelect("id,level_name levelName").where("is_using = 1 and CustomerCode = {0}",code).orderBy("low_money asc");
        List<FrLevel> frLevels = service.selectList(wrapper);
        return JsonResult.success(frLevels);
    }
}

