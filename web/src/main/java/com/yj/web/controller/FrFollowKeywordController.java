package com.yj.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.HttpServletUtils;
import com.yj.dal.model.FrFollowKeyword;
import com.yj.dal.param.KeywordParam;
import com.yj.service.service.IFrFollowKeywordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 跟进记录关键字表 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-04
 */
@RestController
@RequestMapping("/frFollowKeyword")
@Validated
public class FrFollowKeywordController {

    @Autowired
    IFrFollowKeywordService service;

    /**
     * @Description: 增加关键字
     * @Author: 欧俊俊
     * @Date: 2018/9/13 16:01
     */
    @PostMapping("/postAddKeyWord")
    public JsonResult addFollowKeyword(@RequestBody KeywordParam param) throws YJException {
        Boolean b = service.addFollowKeyword(param);
        if(b){
            return JsonResult.successMessage("新增成功");
        }
        return JsonResult.failMessage("新增失败");
    }

    /**
     * @Description: 查询关键字列表
     * @Author: 欧俊俊
     * @Date: 2018/9/13 16:01
     */
    @GetMapping("/getKeywordList")
    public JsonResult selectList() throws YJException {
        //获取客户代码
        String custmerCode = HttpServletUtils.getCustmerCode();
        if(StringUtils.isEmpty(custmerCode)){
            throw new YJException(YJExceptionEnum.CUSTOMERCODE_NOT_FOUND);
        }
        EntityWrapper<FrFollowKeyword> ew = new EntityWrapper<>();
        ew.setSqlSelect("id,content");
        ew.where("is_using = 1").and("CustomerCode = {0}",custmerCode).orderBy("create_time desc");
        List<FrFollowKeyword> list = service.selectList(ew);
        return JsonResult.success(list);
    }

    /**
     * @Description: 删除关键字
     * @Author: 欧俊俊
     * @Date: 2018/9/13 16:01
     */
    @PostMapping("/postDelKeyWord")
    @Transactional
    public JsonResult updateKeyword(@RequestBody FrFollowKeyword keyword) throws YJException {
        EntityWrapper<FrFollowKeyword> ew = new EntityWrapper<>();
        ew.where("is_using = 1").and("id = {0}",keyword.getId());
        FrFollowKeyword frFollowKeyword = service.selectOne(ew);
        if(frFollowKeyword==null){
            throw new YJException(YJExceptionEnum.SERVER_ERROR);
        }
        frFollowKeyword.setUsing(false);
        boolean b = service.updateById(frFollowKeyword);
        if(b){
            return JsonResult.successMessage("删除成功");
        }
        return JsonResult.failMessage("删除失败");
    }

}

