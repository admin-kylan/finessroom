package com.yj.web.controller;


import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrSetGym;
import com.yj.service.service.IFrSetGymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 健身馆设置保存 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-28
 */
@RestController
@RequestMapping("/frSetGym")
public class FrSetGymController {

    @Autowired
    IFrSetGymService service;

    /**
     * 获得上次操作人与时间
     * @return
     * @throws YJException
     */
    @GetMapping("/getTime")
    public JsonResult getTime(String type) throws YJException{
        return JsonResult.success(service.getTime(type));
    }

    /**
     * 获取门店
     * @param code code不传默认从cookie获取
     * @return
     * @throws YJException
     */
    @GetMapping("/getShop")
    public JsonResult getShop(String code,String type) throws YJException{
        return JsonResult.success(service.getShop(code,type));
    }

    /**
     * 获取连锁店
     * @return
     * @throws YJException
     */
    @GetMapping("/getChainStore")
    public JsonResult getChainStore(String type) throws YJException{
        return JsonResult.success(service.getChainStore(type));
    }

    /**
     * 获取城市及门店
     * @param code code不传默认从cookie获取
     * @return
     * @throws YJException
     */
    @GetMapping("/getCityShop")
    public JsonResult getCityShop(String code) throws YJException{
        return JsonResult.success(service.getCityShop(code));
    }

    /**
     * 保存项目
     * @param map
     * @return
     * @throws YJException
     */
    @PostMapping("/saveProject")
    public JsonResult saveProject(@RequestBody Map<String, Object> map) throws YJException{
        return service.saveProject(map);
    }

    /**
     * 修改项目
     * @param frSetGym
     * @return
     * @throws YJException
     */
    @PostMapping("/updateProject")
    public JsonResult updateProject(@RequestBody FrSetGym frSetGym) throws YJException{
        return service.updateProject(frSetGym);
    }

    /**
     * 删除项目
     * @param id
     * @return
     * @throws YJException
     */
    @GetMapping("/delProject")
    public JsonResult delProject(String id) throws YJException{
        return service.delProject(id);
    }
}

