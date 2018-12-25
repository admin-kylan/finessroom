package com.yj.dal.param;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

/**
 * 〈一句话功能简述〉<br>
 * 〈增加协议号〉
 *
 * @author 欧俊俊
 * @create 2018/9/10
 */
public class KeywordParam {
    private String id;
    private String keyword;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
