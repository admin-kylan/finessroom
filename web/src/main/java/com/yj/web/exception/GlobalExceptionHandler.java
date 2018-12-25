package com.yj.web.exception;

import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * 〈一句话功能简述〉<br>
 * 〈全局统一异常〉
 *
 * @author 欧俊俊
 * @create 2018/8/31
 * @RestControllerAdvice = @ControllerAdvice + @ResponseBody
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * @Description: 浏览器页面请求异常
     * @Author: 欧俊俊
     * @Date: 2018/9/14 9:42
     */
    @ExceptionHandler(value = Exception.class)
    public Object defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        log.error("服务器异常:URL = " + req.getRequestURL());
        log.error("服务器异常:Message = " + e.getMessage());
        log.error("服务器异常:StackTrace = "+ e.getStackTrace());
        //使用HttpServletRequest中的header检测请求是否为ajax, 如果是ajax则返回json, 如果为非ajax则返回view(即ModelAndView)
        String contentTypeHeader = req.getHeader("Content-Type");
        String acceptHeader = req.getHeader("Accept");
        String xRequestedWith = req.getHeader("X-Requested-With");
        if ((contentTypeHeader != null && contentTypeHeader.contains("application/json"))
                || (acceptHeader != null && acceptHeader.contains("application/json"))
                || "XMLHttpRequest".equalsIgnoreCase(xRequestedWith)) {
            return JsonResult.fail(YJExceptionEnum.SERVER_ERROR);
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("msg", e.getMessage());
            modelAndView.addObject("url", req.getRequestURL());
            modelAndView.addObject("stackTrace", e.getStackTrace());
            modelAndView.setViewName("error");
            return modelAndView;
        }
    }

    /**
     * @Description: 主动抛出异常
     * @Author: 欧俊俊
     * @Date: 2018/9/14 9:42
     */
    @ExceptionHandler(value = YJException.class)
    public JsonResult<String> jsonErrorHandler(HttpServletRequest req, YJException e) throws Exception {
        log.error("主动抛出异常:URL = " + req.getRequestURL());
        log.error("主动抛出异常:Message = " + e.getMessage());
        log.error("主动抛出异常:Message = " + e.getStackTrace());
        return JsonResult.failMessage(e.getMessage());
    }

    /**
     * @Description: SpringMvc 校验参数异常
     * @Author: 欧俊俊
     * @Date: 2018/9/14 9:42
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResult<Object> handleValidationException(ConstraintViolationException e) throws YJException {
        for(ConstraintViolation<?> s:e.getConstraintViolations()){
            return JsonResult.failMessage(s.getMessage());
        }
        return JsonResult.failMessage(e.getMessage());
    }

}
