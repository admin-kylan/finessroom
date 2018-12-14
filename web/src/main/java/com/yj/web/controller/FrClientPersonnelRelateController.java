package com.yj.web.controller;


import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.param.ClientDistributionParam;
import com.yj.service.service.IFrClientPersonnelRelateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 客户员工关系表 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-31
 */
@RestController
@RequestMapping("/frClientPersonnelRelate")
public class FrClientPersonnelRelateController {

    @Autowired
    IFrClientPersonnelRelateService service;

    /**
     * 添加认领关系
     * @param cids
     * @return
     * @throws YJException
     */
    @GetMapping("/claiming")
    public JsonResult claiming(String[] cids,String type)throws YJException {
        return service.claiming(cids,type);
    }
    /**
     * 根据客户id查询教练
     */
    @GetMapping("/getService")
    public  JsonResult getService(String cid,String type)throws YJException{
        return JsonResult.success(service.getService(cid ,type));
    }

    /**
     * 分配
     */
    @PostMapping("/clientDistribution")
    public  JsonResult clientDistribution(@RequestBody ClientDistributionParam params) throws YJException{
        return service.clientDistribution(params);
    }

}

