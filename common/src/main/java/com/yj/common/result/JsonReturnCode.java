package com.yj.common.result;

/**
 * 〈一句话功能简述〉<br>
 * 〈json格式数据返回码〉
 *
 * @author 欧俊俊
 * @create 2018/8/31
 */
public enum JsonReturnCode {

    NOT_LOGIN("401","未登录"),
    SUCCESS ("200","成功"),
    FAIL ("500","接口错误"),
    ACCESS_ERROR ("403","禁止访问"),
    NOT_FOUND ("404","页面未发现"),
    PARAMETER_ERROR ("501","参数错误"),
    APP_SUCCESS ("1","成功"),
    APP_FAIL ("0","失败"),
    APP_FAIL_VERIFICATION ("0","失败");
    private String code;
    private String desc;

    JsonReturnCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
