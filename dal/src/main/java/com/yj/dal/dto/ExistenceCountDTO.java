package com.yj.dal.dto;

import lombok.Data;

/**
 * 〈一句话功能简述〉<br>
 * 〈现有客户列表筛选参数〉
 *
 * @author 欧俊俊
 * @create 2018/9/15
 */
public class ExistenceCountDTO {
    private Integer count;
    private Integer birthdayCount;
    private Integer addCount;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getBirthdayCount() {
        return birthdayCount;
    }

    public void setBirthdayCount(Integer birthdayCount) {
        this.birthdayCount = birthdayCount;
    }

    public Integer getAddCount() {
        return addCount;
    }

    public void setAddCount(Integer addCount) {
        this.addCount = addCount;
    }
}
