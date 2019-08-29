package com.angel.common.base.http;

import java.io.Serializable;

/**
 * @description:通用返回结果
 */
public class R implements Serializable {

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static R ok(){
        return new R();
    }

    public static R err(){
        return new R();
    }
}
