package com.example.seckill.controller;

import lombok.Data;

import java.util.Map;

@Data
public class Response {
    private int code;
    private String message;
    private Map<String, Object> data;

    public Response(int code, String message, Map<String, Object> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
