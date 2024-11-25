package com.example.seckill.controller;

import com.example.seckill.entity.User;
import com.example.seckill.service.UserService;
import com.example.seckill.service.impl.UserServiceImpl;
import com.example.seckill.util.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Response register(@RequestBody User user) {
        try {
            // 解密密码
            logger.debug("user.getPassword() = " + user.getPassword());
            String decryptedPassword = RSAUtil.decrypt(user.getPassword());
            user.setPassword(decryptedPassword);
            User registeredUser = userService.register(user);
            logger.info("User registered: " + registeredUser + " successfully");
            return new Response(200, "注册成功", registeredUser);
        } catch (Exception e) {
            return new Response(400, e.getMessage(), null);
        }
    }

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
