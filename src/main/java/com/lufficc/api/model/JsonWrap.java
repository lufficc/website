package com.lufficc.api.model;

/**
 * Created by lcc_luffy on 2016/8/8.
 */
public class JsonWrap<T> {
    private int code;
    private String msg;
    private T content;

    public JsonWrap(int code, String msg, T content) {
        this.code = code;
        this.msg = msg;
        this.content = content;
    }

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

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
