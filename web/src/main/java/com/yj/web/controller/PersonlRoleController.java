package com.yj.web.controller;


import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.PersonlRole;
import com.yj.dal.model.PersonnelInfo;
import com.yj.service.service.IPersonlRoleService;
import com.yj.service.service.impl.PersonlRoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 人员担任角色表 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-23
 */
@RestController
@RequestMapping("/personlRole")
public class PersonlRoleController {

    @Autowired
    IPersonlRoleService iPersonlRoleService;

    @GetMapping("/getPersonlByRole")
    public JsonResult getPersonlByRole(String rid)throws YJException {
       List<PersonnelInfo> personnelInfos=iPersonlRoleService.getPersonlByRole(rid);
       return JsonResult.success(personnelInfos);
    }


    @GetMapping("/getCoach")
    public JsonResult getCoach()throws YJException {
        List<PersonnelInfo> personnelInfos=iPersonlRoleService.getCoach();
        return JsonResult.success(personnelInfos);
    }
}

