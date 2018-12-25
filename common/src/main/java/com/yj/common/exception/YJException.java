package com.yj.common.exception;

/**
 * 〈一句话功能简述〉<br>
 * 〈自定义异常〉
 *
 * @author 欧俊俊
 * @create 2018/8/31
 */
public class YJException extends Exception {
    private Integer code;

    private String message;

    public YJException(YJExceptionEnum serviceExceptionEnum) {
        this.code = serviceExceptionEnum.getCode();
        this.message = serviceExceptionEnum.getMessage();
    }

    //public YJException(Integer code,String message) {
    //    this.code = code;
    //    this.message = message;
    //} 

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
