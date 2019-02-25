package com.yj.web.controller;


import com.alibaba.druid.sql.visitor.functions.If;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dao.FrClientPersonalMapper;
import com.yj.dal.dao.ShopMapper;
import com.yj.dal.model.FrClient;
import com.yj.dal.model.FrClientLatencePersonal;
import com.yj.dal.model.FrClientPersonal;
import com.yj.dal.model.FrClientPic;
import com.yj.dal.param.*;
import com.yj.service.service.IFrClientLatencePersonalService;
import com.yj.service.service.IFrClientPersonalService;
import com.yj.service.service.IFrClientPicService;
import com.yj.service.service.IFrClientService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    @Autowired
    IFrClientLatencePersonalService frClientLatencePersonalService;
    @Autowired
    IFrClientPersonalService frClientPersonalService;

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
     *  根据筛选条件查询现有客户列表 后台设置导出用
     * @param request
     * @param params
     * @return
     * @throws YJException
     */
    @GetMapping("/getExistenceListBG")
    public JsonResult existenceListBg(HttpServletRequest request,@ModelAttribute ExistenceFilterParam params) throws YJException {
        return JsonResult.success(service.existenceListBG(request,params));
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
     * 根据筛选条件查询潜在客户列表 后台设置导出用
     * @param request
     * @param params
     * @return
     * @throws YJException
     */
    @GetMapping("/getPotentialListBG")
    public JsonResult potentialListBG(HttpServletRequest request,@ModelAttribute PotentialFilterParam params) throws YJException {
        return JsonResult.success(service.potentialListBG(request,params));
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
     * 根据筛选条件查询我的潜在客户列表
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
     * 根据Id查询客户个人详情App
     */
    @GetMapping("getPersonalDetails")
    public JsonResult getPersonalDetails(String id) {

        return service.getPersonalDetails(id);
    }

    @Value("${fitness.uploadPath}")
    private String filePath;

    /**
     * 添加潜在客户app
     *
     * @param file
     * @param
     * @return
     * @throws YJException
     */
    @PostMapping("/addPotentialClient")
    public JsonResult addPotentialClient(@RequestParam("file") MultipartFile file, @RequestBody FrClient frClient, String shopId) throws YJException {
        if (frClient != null) {
            boolean insert = service.insert(frClient);
            if (insert) {
                FrClientLatencePersonal frClientLatencePersonal = new FrClientLatencePersonal();
                frClientLatencePersonal.setClientId(frClient.getId());
                frClientLatencePersonal.setId(UUIDUtils.generateGUID());
                frClientLatencePersonal.setShopId(shopId);
                frClientLatencePersonal.setPersonalId(frClient.getConsultantId());
                frClientLatencePersonalService.insert(frClientLatencePersonal);
                FrClientPic frClientPic = new FrClientPic();
                Map<String, String> map = new HashMap<>();
                String childPath = "avatar/";
                this.toUpdateLoad(file, childPath, map);
                frClientPic.setClientId(frClient.getId());
                frClientPic.setPicLink(map.get("imgUrl"));
                frClientPic.setPicType(1);
                frClientPic.setId(UUIDUtils.generateGUID());
                frClientPic.setCreateTime(new Date());
                boolean insert1 = frClientPicService.insert(frClientPic);
                if (insert1) {
                    return JsonResult.success();
                }

            }

        }

        return JsonResult.fail();
    }

    /**
     * //     * 查询现有客户列表App
     * //
     */
    @GetMapping("getEmployeeClientList")
    public JsonResult getEmployeeClientList() {

        return service.getEmployeeClientList();
    }

    /**
     * 查询潜在客户列表App
     */
    @GetMapping("getPotentialClientList")
    public JsonResult getPotentialClientList() {

        return service.getPotentialClientList();
    }

    @PostMapping("/updateEmployeeClient")
    public JsonResult updateEmployeeClient(@RequestParam(value = "file", required = false) MultipartFile file, @RequestBody FrClient frClient, String picId, String shopId) throws YJException {
        if (frClient != null) {
            boolean b = service.updateById(frClient);
            if (b) {
                FrClientPersonal frClientPersonal = frClientPersonalService.selectOne(
                        new EntityWrapper<FrClientPersonal>().where("client_id={0} and is_using=1", frClient.getId())
                );
                if (frClientPersonal != null) {
                    frClientPersonal.setShopId(shopId);
                    frClientPersonalService.updateById(frClientPersonal);
                }
                if (picId != null) {
                    savePic(file, frClient, picId);

                }
                return JsonResult.success();
            }
        }
        return JsonResult.fail();
    }
    @PostMapping("/updatePotentialClient")
    public JsonResult updatePotentialClient(@RequestParam(value = "file", required = false) MultipartFile file, @RequestBody FrClient frClient, String picId, String shopId) throws YJException {
        if (frClient != null) {
            boolean b = service.updateById(frClient);
            if (b) {
                FrClientLatencePersonal frClientLatencePersonal = frClientLatencePersonalService.selectOne(
                        new EntityWrapper<FrClientLatencePersonal>().where("client_id={0} and is_using=1", frClient.getId())
                );
                if (frClientLatencePersonal != null) {
                    frClientLatencePersonal.setShopId(shopId);
                    frClientLatencePersonalService.updateById(frClientLatencePersonal);
                }
                if (picId != null) {
                    savePic(file, frClient, picId);

                }
                return JsonResult.success();
            }
        }
        return JsonResult.fail();
    }
    private void savePic(@RequestParam(value = "file", required = false) MultipartFile file, @RequestBody FrClient frClient, String picId) throws YJException {
        FrClientPic FrClientPic = frClientPicService.selectById(picId);
        if (FrClientPic != null) {
            String path = FrClientPic.getPicLink();
            path = "/" + path.substring(path.indexOf("avatar"), path.length());
            File fileDel = new File(filePath + path);
            boolean del = frClientPicService.deleteById(FrClientPic);
            if (del) {
                fileDel.delete();
            }
        }
        FrClientPic frClientPic = new FrClientPic();
        Map<String, String> map = new HashMap<>();
        String childPath = "avatar/";
        this.toUpdateLoad(file, childPath, map);
        frClientPic.setClientId(frClient.getId());
        frClientPic.setPicLink(map.get("imgUrl"));
        frClientPic.setPicType(1);
        frClientPic.setId(UUIDUtils.generateGUID());
        frClientPic.setCreateTime(new Date());
        frClientPicService.insert(frClientPic);
    }


    public Map<String, String> toUpdateLoad(MultipartFile file, String childPath, Map<String, String> map) throws YJException {
        if (file.isEmpty()) {
            throw new YJException(YJExceptionEnum.FILE_NOT_FOUND);
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
//        log.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 解决中文问题，liunx下中文路径，图片显示问题
        fileName = UUID.randomUUID() + suffixName;
//        log.info("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        File dest = new File(filePath + childPath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            map.put("msg", "true");
            map.put("imgUrl", childPath.concat(fileName));
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}

