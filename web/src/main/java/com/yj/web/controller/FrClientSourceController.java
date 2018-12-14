package com.yj.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrClientSource;
import com.yj.service.service.IFrClientSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户来源渠道表 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-09
 */
@RestController
@RequestMapping("/frClientSource")
public class FrClientSourceController {

    @Autowired
    IFrClientSourceService service;

    /**
     * @Description: 获取来源渠道列表
     * @Author: 欧俊俊
     * @Date: 2018-10-09 16:03
     */
    @GetMapping("/getListByIsAuto")
    public JsonResult getList(@RequestParam(required = false) Integer isAuto) {
        EntityWrapper ew = new EntityWrapper<>();
        ew.setSqlSelect("id,source_name sourceName,prompt");
        ew.where("is_using = 1");
        if (isAuto != null) {
            ew.and("is_auto = 0", isAuto);
        }
        ew.orderBy("create_time desc");
        return JsonResult.success(service.selectList(ew));
    }

    @GetMapping("/getSource")
    public JsonResult getSource(String cid) throws YJException {
        Map<String, Object> map = service.getSource(cid);
        return JsonResult.success(map);
    }


}

