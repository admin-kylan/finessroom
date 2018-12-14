package com.yj.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.StringUtils;
import com.yj.dal.model.RoleInfo;
import com.yj.service.service.IPersonlRoleService;
import com.yj.service.service.IRoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-10-23
 */
@RestController
@RequestMapping("/roleInfo")
public class RoleInfoController {

    @Autowired
    IRoleInfoService iRoleInfoService;

    @Autowired
    IPersonlRoleService iPersonlRoleService;

    /**
     * 查询全部角色
      * @return
     */
    @GetMapping("/findAll")
    public JsonResult findAll()throws YJException{
        List<RoleInfo> roleInfos = iRoleInfoService.findAll();
        return JsonResult.success(roleInfos);
    }

    /**
     * 根据角色查询角色名称
     * @param firstName
     * @return
     */
    @GetMapping("/getRoleName")
    public JsonResult getRoleName(String firstName)throws YJException {
       RoleInfo roleInfo= iRoleInfoService.getRoleByFirstName(firstName);
       return JsonResult.success(roleInfo);
    }

    /**
     * 获取对应员工的所有上级id，判断是否对方上级
     * @param roleInfoId 对方的角色Id
     * @param personalId 当前操作用户的用户ID
     * @return
     */
    @GetMapping("getParentIdList")
    public JsonResult getParentIdList(String roleInfoId,String personalId){
        boolean isFlage = false;
        String rId;
        String code;
        Map<String,String> cMap = new HashMap<>();
        if(!StringUtils.isEmpty(roleInfoId) && !StringUtils.isEmpty(personalId)){
            // 获取对方的所有上级领导
            List<Map<String,String>> list = iRoleInfoService.getUpGradeAll(roleInfoId);
            if(list != null && list.size()>0){
                cMap = list.get(0);
                if(cMap != null && cMap.get("CustomerCode")!=null ){
                    //根据对方的客户代码，查找当前操作客户的角色是否存在？
                    code = cMap.get("CustomerCode");
                    RoleInfo nowRol = iRoleInfoService.selectOne(
                            new EntityWrapper<RoleInfo>().where("ID={0}",roleInfoId).and("CustomerCode={0}",code));
                    if(nowRol == null){
                        return JsonResult.failMessage("抱歉权限不足");
                    }
                    //判断对方角色是否所属上级
                    for(Map<String,String> map:list){
                        Object num = map.get("Lev");
                        if(num != null && "0".equals(num.toString())){
                            cMap = map;
                        }
                        if(roleInfoId.equals(map.get("ParentId"))){
                            isFlage = true;
                            break;
                        }
                    }
                    if(!isFlage){
                        if(cMap != null && cMap.get("ParentId")!= null  && !"-1".equals(cMap.get("ParentId"))){
//                          根据被操作人的客户代码查找
                            if (StringUtils.isEmpty(nowRol.getParentId())){
                                if("-1".equals(nowRol.getParentId())){
                                    isFlage = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        if(isFlage){
            return JsonResult.successMessage("true");
        }
        return JsonResult.failMessage("抱歉权限不足");
    }

    @GetMapping("/getUnallocatedRole")
    public JsonResult getUnallocatedRole(HttpServletRequest req) {
        String customerCode = CookieUtils.getCookieValue(req, "code", true);
        List<RoleInfo> roleInfos = iRoleInfoService.selectList(
                new EntityWrapper<RoleInfo>().setSqlSelect("id", "FirstName").where("RoleType={0}", 1).where("CustomerCode={0}", customerCode)
        );
        return JsonResult.success(roleInfos);
    }
}

