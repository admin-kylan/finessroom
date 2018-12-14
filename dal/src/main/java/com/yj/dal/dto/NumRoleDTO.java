package com.yj.dal.dto;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈协议号/会员卡号规则对象〉
 *
 * @author 欧俊俊
 * @create 2018/9/12
 */
public class NumRoleDTO {
    private String id;
    private String startNo;
    private String endNo;
    private Integer batchNum;
    private Integer limitNum;
    private Integer usedCount;
    private Integer invalidCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartNo() {
        return startNo;
    }

    public void setStartNo(String startNo) {
        this.startNo = startNo;
    }

    public String getEndNo() {
        return endNo;
    }

    public void setEndNo(String endNo) {
        this.endNo = endNo;
    }

    public Integer getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(Integer batchNum) {
        this.batchNum = batchNum;
    }

    public Integer getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }

    public Integer getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(Integer usedCount) {
        this.usedCount = usedCount;
    }

    public Integer getInvalidCount() {
        return invalidCount;
    }

    public void setInvalidCount(Integer invalidCount) {
        this.invalidCount = invalidCount;
    }
}
