package com.yj.common.exception;

/**
 * 〈一句话功能简述〉<br>
 * 〈抽象接口〉
 *
 * @author 欧俊俊
 * @create 2018/8/31
 */
public interface ServiceExceptionEnum {
    /**
     * 获取异常编码
     */
    Integer getCode();

    /**
     * 获取异常信息
     */
    String getMessage();
}
