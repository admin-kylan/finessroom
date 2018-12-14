package com.yj.dal.base;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 欧俊俊
 * @create 2018-09-25
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseModel extends Model implements Serializable {
    /**
     * 操作人id
     */
    @TableField(exist = false)
    private String operaterId;
    /**
     * 操作人名称
     */
    @TableField(exist = false)
    private String operaterName;
    /**
     * 客户代码
     */
    @TableField(exist = false)
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

    protected abstract Serializable pkVal();
}
