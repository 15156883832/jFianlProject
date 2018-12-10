package com.sf.jfinal.qs.core.tools;

import javax.servlet.http.HttpServletResponse;

public class Result<T> {
    private int code;
    private String desc;
    private T data;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static Result<Object> ok() {
        Result<Object> ret = new Result<>();
        ret.setCode(HttpServletResponse.SC_OK);
        return ret;
    }

    public static Result<Object> fail(int code) {
        Result<Object> ret = new Result<>();
        ret.setCode(code);
        return ret;
    }

    public static Result<Object> fail() {
        return new Result<>();
    }
}
