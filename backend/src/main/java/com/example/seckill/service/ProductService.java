package com.example.seckill.service;

import com.example.seckill.entity.Product;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public interface ProductService {
    Page<Product> findProducts(int page, int size, String keyword, Long categoryId, BigDecimal priceMin, BigDecimal priceMax);
    // 其他方法
}
