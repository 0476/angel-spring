package com.angel.common.base.http;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;

/**
 * 功能描述: layui表格公共结果接收类
 * @ClassName LayTableData
 * @Description
 * @Author ailikes
 * @Date 2019-05-23 18:15
 * @Version 1.0.0
 */
public class PageData<T> extends Page<T> implements IPage<T>,Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private int code = 0;
    /**
     * 消息
     */
    private String msg = "";

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
