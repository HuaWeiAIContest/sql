package com.xxx.demo.Common;

public class Response {
    private int code;
    private String message;
    private Object data;

    public Response(){
    }

    public Response setCode(ResultCode resultCode)
    {
        this.code = resultCode.code;
        return this;
    }

    public Response(int code, String message, String data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getHr() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setHr(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
