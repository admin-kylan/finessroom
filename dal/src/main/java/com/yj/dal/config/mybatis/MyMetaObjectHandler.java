package com.yj.dal.config.mybatis;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.util.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpRequest;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 欧俊俊
 * @create 2018-09-25
 */
@Component
public class MyMetaObjectHandler extends MetaObjectHandler {
    //新增填充
    @Override
    public void insertFill(MetaObject metaObject) {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取当前登录用户
        String id = CookieUtils.getCookieValue(req, "id",true);
        String name = CookieUtils.getCookieValue(req, "name",true);
        String code = CookieUtils.getCookieValue(req, "code",true);
        Date now = new Date();

        Object createTime = getFieldValByName("createTime",metaObject);
        Object createUserId = getFieldValByName("createUserId",metaObject);
        Object createUserName = getFieldValByName("createUserName",metaObject);

        Object updateTime = getFieldValByName("updateTime",metaObject);
        Object updateUserId = getFieldValByName("updateUserId",metaObject);
        Object updateUserName = getFieldValByName("updateUserName",metaObject);
        Object customerCode = getFieldValByName("CustomerCode",metaObject);

        if (null == createTime) {
            setFieldValByName("createTime",now,metaObject);
        }
        if (null == createUserId) {
            setFieldValByName("createUserId",id,metaObject);
        }
        if (null == createUserName) {
            setFieldValByName("createUserName",name,metaObject);
        }
        if (null == updateTime) {
            setFieldValByName("updateTime",now,metaObject);
        }
        if (null == updateUserId) {
            setFieldValByName("updateUserId",id,metaObject);
        }
        if (null == updateUserName) {
            setFieldValByName("updateUserName",name,metaObject);
        }
        if (null == customerCode) {
            setFieldValByName("CustomerCode",code,metaObject);
        }

    }
    //更新填充
    @Override
    public void updateFill(MetaObject metaObject) {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取当前登录用户
        String id = CookieUtils.getCookieValue(req, "id",true);
        String name = CookieUtils.getCookieValue(req, "name",true);
        Date now = new Date();
        Object updateTime = getFieldValByName("updateTime",metaObject);
        Object updateUserId = getFieldValByName("updateUserId",metaObject);
        Object updateUserName = getFieldValByName("updateUserName",metaObject);

        if (updateTime == null) {
            setFieldValByName("updateTime",now,metaObject);
        }
        if (updateUserId == null) {
            setFieldValByName("updateUserId",id,metaObject);
        }
        if (updateUserName == null) {
            setFieldValByName("updateUserName",name,metaObject);
        }
    }
}
