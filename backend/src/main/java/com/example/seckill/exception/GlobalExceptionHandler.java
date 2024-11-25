package com.example.seckill.exception;

import lombok.Data;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Response handleException(Exception e) {
        return new Response(500, "服务器内部错误：" + e.getMessage(), null);
    }

    // 响应封装类
    @Data
    class Response {
        private int code;
        private String message;
        private Object data;

        public Response(int code, String message, Object data) {
            this.code = code;
            this.message = message;
            this.data = data;
        }

    }
}
