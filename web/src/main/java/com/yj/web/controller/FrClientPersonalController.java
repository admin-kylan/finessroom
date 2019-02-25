package com.yj.web.controller;


import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.dal.model.FrClientPersonal;
import com.yj.dal.model.FrEmployeeClientFollow;
import com.yj.dal.model.FrLatenceFollow;
import com.yj.service.service.IFrClientPersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 现有客户关系表 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-09-30
 */
@RestController
@RequestMapping("/frClientPersonal")
public class FrClientPersonalController {

}

