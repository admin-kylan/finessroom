package com.yj.dal.param;

/**
 * 〈一句话功能简述〉<br>
 * 〈增加协议号〉
 *
 * @author 欧俊俊
 * @create 2018/9/10
 */
public class OperaterParam {
    private String operaterId;
    private String operaterName;
    private String customerCode;


    public String getOperaterId() {
        return operaterId;
    }

    public void setOperaterId(String operaterId) {
        this.operaterId = operaterId;
    }

    public String getOperaterName() {
        return operaterName;
    }

    public void setOperaterName(String operaterName) {
        this.operaterName = operaterName;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
}
