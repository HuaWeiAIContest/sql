package com.xxx.demo.Common;

public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static Response genSuccessResult() {
        Response response = new Response();
        response.setCode(ResultCode.SUCCESS);
        response.setMessage(DEFAULT_SUCCESS_MESSAGE);
        return response;
    }

    public static Response genSuccessResult(Object data) {
        Response response = new Response();
        response.setCode(ResultCode.SUCCESS);
        response.setMessage(DEFAULT_SUCCESS_MESSAGE);
        response.setData(data);
        return response;
    }

    public static Response genFailResult(String message) {
        Response response = new Response();
        response.setCode(ResultCode.FAIL);
        response.setMessage(message);
        return response;
    }
}