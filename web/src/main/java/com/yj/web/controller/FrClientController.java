package com.yj.web.controller;


import com.alibaba.druid.sql.visitor.functions.If;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.dal.dao.FrClientPersonalMapper;
import com.yj.dal.dao.ShopMapper;
import com.yj.dal.model.FrClient;
import com.yj.dal.model.FrClientPersonal;
import com.yj.dal.model.FrClientPic;
import com.yj.dal.param.*;
import com.yj.service.service.IFrClientPersonalService;
import com.yj.service.service.IFrClientPicService;
import com.yj.service.service.IFrClientService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 客户表 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-12
 */
@RestController
@RequestMapping("/frClient")
public class FrClientController {

    @Autowired
    IFrClientService service;
    @Autowired
    ShopMapper shopMapper;
    @Autowired
    IFrClientPicService frClientPicService;

    /**
     * @Description: 根据筛选条件查询现有客户列表
     * @Author: 欧俊俊
     * @Date: 2018/9/29 12:01
     */
    @GetMapping("/getExistenceList")
    public JsonResult existenceList(@ModelAttribute ExistenceFilterParam params) throws YJException {
        return JsonResult.success(service.selectExistenceList(params));
    }

    /**
     * @Description: 根据筛选条件查询潜在客户列表
     * @Author: 欧俊俊
     * @Date: 2018/9/30 12:01
     */
    @GetMapping("/getPotentialList")
    public JsonResult potentialList(@ModelAttribute PotentialFilterParam params) throws YJException {
        return JsonResult.success(service.selectPotentialList(params));
    }


    /**
     * @Description: 现有客户统计(会员总数 / 近一周生日会员数 / 本周新增会员数)
     * @Author: 欧俊俊
     * @Date: 2018/9/30 12:01
     */
    @GetMapping("/getExistenceStat")
    public JsonResult existenceStat(HttpServletRequest request) throws YJException {
        //获取Cookie中的客户代码和门店id
        String code = CookieUtils.getCookieValue(request, "code", true);
        String shopId = CookieUtils.getCookieValue(request, "shopid", true);
        if (StringUtils.isEmpty(code)) {
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        return JsonResult.success(service.selectExistenceStat(code, shopId));
    }

    /**
     * @Description: 新增潜在客户
     * @Author: 欧俊俊
     * @Date: 2018-10-09 17:07
     */
    @PostMapping("/postAddPotentialCustomer")
    public JsonResult addPotentialCustomer(@RequestBody AddPotentialParam potentialParam) throws YJException {
        return service.addPotentialCustomer(potentialParam);
    }

    /**
     * @Description: 删除潜在客户(修改is_using为0)
     * @Author: 欧俊俊
     * @Date: 2018-10-12 9:39
     */
    @PostMapping("/postDelPotentialCustomer")
    public JsonResult delPotentialCustomer(@RequestParam String id) {
        return service.delPotentialCustomer(id);
    }

    /**
     * 获取客户信息
     *
     * @param id
     * @return
     * @throws YJException
     */
    @GetMapping("/getClient")
    public JsonResult getClient(@RequestParam String id) throws YJException {
        return service.getClient(id);
    }

    /**
     * 获取客户信息---根据手机号
     *
     * @param CustomerCode
     * @param mobile
     * @return
     * @throws YJException
     */
    @GetMapping("/getClientList")
    public JsonResult getClientList(@RequestParam String CustomerCode, @RequestParam String mobile) throws YJException {
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(CustomerCode)) {
            return JsonResult.failMessage("参数有误");
        }
        return service.getClientList(CustomerCode, mobile);
    }


    /**
     * 新建会员
     *
     * @param customerParam
     * @return
     * @throws YJException
     */
    @PostMapping(value = "/postAddCustomer")
    public JsonResult addCustomer(@RequestBody AddCustomerParam customerParam) throws YJException {
        return service.addCustomer(customerParam);
    }

    /**
     * 根据筛选条件查询w我的潜在客户列表
     */
    @GetMapping("/getMyPotentialList")
    public JsonResult myPotentialList(@ModelAttribute MyPotentialFilterParam params) throws YJException {
        return JsonResult.success(service.selectmyPotentialList(params));
    }

    /**
     * 查询潜在未认领客户
     *
     * @param params
     * @return
     * @throws YJException
     */
    @GetMapping("/getCollarList")
    public JsonResult CollarList(@ModelAttribute CollarClientParam params) throws YJException {
        return JsonResult.success(service.selectCollarList(params));
    }

    /**
     * 查询现有未认领客户
     *
     * @param params
     * @return
     * @throws YJException
     */
    @GetMapping("/getExistingList")
    public JsonResult ExistingList(@ModelAttribute CollarClientParam params) throws YJException {
        return JsonResult.success(service.selectExistingList(params));
    }

    /**
     * 查询我的现有客户
     */
    @GetMapping("/getMyExistenceList")
    public JsonResult MyExistenceList(@ModelAttribute MyExistenceFilterParam params) throws YJException {
        return JsonResult.success(service.selectMyExistenceList(params));
    }

    /**
     * 查询客户分配
     */
    @GetMapping("/getClientAllot")
    public JsonResult ClientAllot(@ModelAttribute CollarClientParam params) throws YJException {
        return JsonResult.success(service.selectClientAllot(params));
    }

    /**
     * 查询客户信息分析
     */
    @GetMapping("/getClientInformation")
    public JsonResult ClientInformation(@ModelAttribute ClientInformationParam params) throws YJException {
        return JsonResult.success(service.selectClientInformation(params));
    }

    //根据手机号码查询客户信息
    @GetMapping("/getByPhone")
    public JsonResult getByPhone(String phone) throws YJException {
        Map<String, Object> map = service.selectMap(
                new EntityWrapper<FrClient>().where("is_using=1 and mobile={0}", phone)
        );
        if (map == null || map.size() < 1) {
            return JsonResult.success(false);
        }

        String shopName = shopMapper.getShopName2(map.get("id").toString());
        FrClientPic frClientPic = frClientPicService.selectOne(
                new EntityWrapper<FrClientPic>().where("pic_type=1 and pic_state=1 and client_id={0}", map.get("id").toString())
        );
        if (shopName != null) {
            map.put("shopName", shopName);
        }
        if (frClientPic != null) {
            map.put("image", frClientPic.getPicLink());
        }
        return JsonResult.success(map);
    }


    /**
     * 根据条件查询所有客户
     */
    @GetMapping("getAllClient")
    public JsonResult getAllClient(){

        return null;
    }

}

