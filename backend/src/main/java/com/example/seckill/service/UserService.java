package com.example.seckill.service;

import com.example.seckill.entity.User;

public interface UserService {
    User register(User user);
    User login(String username, String password);

    // 其他方法
}
