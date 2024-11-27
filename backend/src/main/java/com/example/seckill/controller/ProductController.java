package com.example.seckill.controller;

import com.example.seckill.entity.Product;
import com.example.seckill.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public Response getProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) BigDecimal priceMin,
            @RequestParam(required = false) BigDecimal priceMax
    ) {

        Page<Product> productPage = productService.findProducts(page, size, keyword, categoryId, priceMin, priceMax);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("content", productPage.getContent());
        responseMap.put("totalPages", productPage.getTotalPages());
        responseMap.put("totalElements", productPage.getTotalElements());
        responseMap.put("size", productPage.getSize());
        responseMap.put("number", productPage.getNumber());

        return new Response(200, "获取商品列表成功", responseMap);
    }

    @PostMapping("/addProducts")
    public Response addProduct() {
        productService.addProduct();
        return new Response(200, "添加商品成功", null);
    }


}
