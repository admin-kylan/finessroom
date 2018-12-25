package com.yj.web.controller;

import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈测试控制器〉
 *
 * @author 欧俊俊
 * @create 2018/8/31
 */
@RestController
public class TestController {
    /**
     * @Description:打招呼
     * @Author: 欧俊俊
     * @Date: 2018/8/31 11:57
     */
    @GetMapping("/")
    public JsonResult getMethod() {
        return JsonResult.success("你好世界");
    }
    /**
     * @Description: 获取json数据
     * @Author: 欧俊俊
     * @Date: 2018/8/31 13:18
     */
    @GetMapping("/json")
    public JsonResult getJson() {
        Map<String,Object> map = new HashMap<>();
        map.put("msg","你好世界");
        map.put("code",1000);
        map.put("success",true);
        return JsonResult.success(map);
    }

    /**
     * @Description: 抛出异常
     * @Author: 欧俊俊
     * @Date: 2018/8/31 15:02
     */
    @GetMapping("/ex")
    public void getEx() throws YJException {
        throw new YJException(YJExceptionEnum.SERVER_ERROR);

    }

    //@Autowired
    //private Sender sender;
    //
    //@GetMapping("/rabbitmq")
    //public JsonResult sendMsg() {
    //    sender.send();
    //    return JsonResult.successMessage("发送成功");
    //}

    /**
     * @Description: 获取Cookie
     * @Author: 欧俊俊
     * @Date: 2018/9/18 17:59
     */
    @GetMapping("/cookie/{cookieName}")
    public JsonResult getCookieName(@PathVariable("cookieName") String cookieName, HttpServletRequest request, HttpServletResponse response) {
        String cookieValue = CookieUtils.getCookieValue(request, cookieName,true);
        return JsonResult.success(cookieValue);
    }

    /**
     * @Description: 设置Cookie
     * @Author: 欧俊俊
     * @Date: 2018/9/18 17:59
     */
    @PostMapping("/setCookie")
    public JsonResult setCookie(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.setCookie(request, response,"userId","3260835b342a1c8b",36000,false);
        //CookieUtils.setCookie(request, response,"userName","13215026952",7,false);
        //CookieUtils.setCookie(request, response,"token","302D8A0B524A5C0FBC6C1B378C99A28518E9423F08EA3E2947688EDCD481B41892F871C07A97B1FAB1B81077FF695BBD171B876DA4B524E8F55F3E2339EE878843CE75930E966496",7,false);
        //CookieUtils.setCookie(request, response,"isTourist","true",7,false);
        //CookieUtils.setCookie(request, response,"imageName","",7,false);
        //CookieUtils.setCookie(request, response,"imagePath","",7,false);
        String cookieValue = CookieUtils.getCookieValue(request, "userId");
        System.out.println(cookieValue);
        return JsonResult.success();
    }
}
