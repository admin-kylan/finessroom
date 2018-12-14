package com.yj.web.controller;


import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dto.ShopListDTO;
import com.yj.dal.model.FrStore;
import com.yj.service.service.IFrStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 门店表 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-04
 */
@RestController
@RequestMapping("/frStore")
public class FrStoreController {

    @Autowired
    IFrStoreService service;

    /**
     * @Description: 增加门店
     * @Author: 欧俊俊
     * @Date: 2018/9/12 17:37
     */
    @PostMapping("/add")
    public JsonResult add(@RequestBody FrStore store) throws YJException {
        if(store==null){
            throw new YJException(YJExceptionEnum.PARAM_ERROR);
        }
        //store.setId(UUIDUtils.generateGUID());
        store.setUpdateTime(new Date());
        boolean insert = service.insert(store);
        if(insert){
            return JsonResult.successMessage("增加成功");
        }
        return JsonResult.failMessage("增加失败");
    }
    

    /**
     * @Description: 修改所在门店的关联设置
     * @Author: 欧俊俊
     * @Date: 2018/9/12 18:04
     */
    @PostMapping("/updateList")
    public JsonResult updateList(@RequestBody List<ShopListDTO> dtos) throws YJException {
        Boolean b = service.updateList(dtos);
        if (b) {
            return JsonResult.successMessage("修改成功");
        }
        return JsonResult.failMessage("修改失败");
    }



    /*
         * @Author Sinyu
         * @Description 根据门店搜索可以使用 的场馆
          * @param sid  门店id
         * @return
         **/
    @GetMapping("/getStoreCategoryList")
    public JsonResult getStoreCategoryList(Integer[] sid)  {





        return JsonResult.success(service.getStoreCategoryList( sid));
     //   return null;
    }





}

