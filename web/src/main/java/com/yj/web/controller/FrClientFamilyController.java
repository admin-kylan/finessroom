package com.yj.web.controller;


import com.alibaba.fastjson.JSON;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.FrClientFamily;
import com.yj.service.service.IFrClientFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 客户家庭亲子关系 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-10
 */
@RestController
@RequestMapping("/frClientFamily")
public class FrClientFamilyController {

    @Autowired
    IFrClientFamilyService iFrClientFamilyService;

    @GetMapping("/getFamily")
    public JsonResult getFamily() throws YJException {
        List<FrClientFamily> frClientFamilies = iFrClientFamilyService.getFamily();
        return JsonResult.success(frClientFamilies);
    }

    @GetMapping("/updateFamily")
    public JsonResult updateFamily(String frClientFamilystr,String cid) throws YJException {
        FrClientFamily frClientFamily=new FrClientFamily();
        frClientFamily= JSON.parseObject(frClientFamilystr, frClientFamily.getClass());
        return iFrClientFamilyService.updateFamily(frClientFamily,cid);
    }

    @GetMapping("/delFamily")
    public JsonResult delFamily(@RequestParam String id) {
        return iFrClientFamilyService.delFamily(id);
    }

}

