package com.yj.web.controller;


import com.yj.common.result.JsonResult;
import com.yj.dal.dto.CategoryItemDTO;
import com.yj.service.service.IFrCategoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 场馆项目表 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-27
 */
@RestController
@RequestMapping("/frCategoryItem")
public class FrCategoryItemController {

    @Autowired
    private IFrCategoryItemService categoryItemService;//场馆项目

    /**
     * 搜索场馆关联的项目
     *
     * @param ssids 场馆
     * @return
     */
    @PostMapping("/getCategoryItem")
    public JsonResult getCategoryItemList(@RequestParam(value = "ssids[]") String[] ssids) {
        return categoryItemService.getCategoryItemList(ssids);
    }



    /**
     * 搜索场馆关联的项目
     *方法   还没写完	暂没有用
     * @param ssids 场馆
     * @return
     */
    @PostMapping("/CategoryItem")
    public JsonResult CategoryItemList(@RequestParam(value = "ssids[]") String[] ssids) {
        return categoryItemService.CategoryItemList(ssids);
    }

    /**
     * 查询项目的设置
     *
     * @param consumeid 项目id
     * @return
     */
    @PostMapping("/selectConsume")
    public JsonResult selectConsume(@RequestParam(value = "consumeid") String consumeid) {
        return categoryItemService.selectConsume(consumeid);
    }


    /**
     * 增加项目 设置
     *
     * @param
     * @return
     */
    @PostMapping("/setConsume")
    public JsonResult ctypeConsumePlset(@RequestBody CategoryItemDTO consume) {
        return categoryItemService.setConsumeDTO(consume);
    }


}

