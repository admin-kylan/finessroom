package com.yj.dal.param;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

/**
 * 〈一句话功能简述〉<br>
 * 〈增加协议号〉
 *
 * @author 欧俊俊
 * @create 2018/9/10
 */
public class NumParam {
    @Max(value = 99999)
    private Integer batchNum;
    @Size(min = 9,max = 12)
    private String  startNo;
    @Size(min = 9,max = 12)
    private String endNo;
    private String[] position;
    private Integer notInNum;

    public Integer getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(Integer batchNum) {
        this.batchNum = batchNum;
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

    public String[] getPosition() {
        return position;
    }

    public void setPosition(String[] position) {
        this.position = position;
    }

    public Integer getNotInNum() {
        return notInNum;
    }

    public void setNotInNum(Integer notInNum) {
        this.notInNum = notInNum;
    }
}
