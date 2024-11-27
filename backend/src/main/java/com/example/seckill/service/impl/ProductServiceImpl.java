package com.example.seckill.service.impl;

import com.example.seckill.entity.Product;
import com.example.seckill.repository.ProductRepository;
import com.example.seckill.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> findProducts(int page, int size, String keyword, Long categoryId, BigDecimal priceMin, BigDecimal priceMax) {
        Pageable pageable = PageRequest.of(page - 1, size);

        Specification<Product> specification = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (keyword != null && !keyword.isEmpty()) {
                Predicate namePredicate = cb.like(root.get("name"), "%" + keyword + "%");
                Predicate descriptionPredicate = cb.like(root.get("description"), "%" + keyword + "%");
                predicates.add(cb.or(namePredicate, descriptionPredicate));
            }

            if (categoryId != null) {
                predicates.add(cb.equal(root.get("category"), categoryId));
            }

            if (priceMin != null) {
                predicates.add(cb.ge(root.get("price"), priceMin));
            }
            if (priceMax != null) {
                predicates.add(cb.le(root.get("price"), priceMax));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return productRepository.findAll(specification, pageable);
    }

    @Override
    public void addProduct() {
        Product product = new Product();
        product.setName("Premium Headphones");
        product.setPrice(BigDecimal.valueOf(299.99));
        product.setDescription("A Headphone.");
        product.setStock(100);
        product.setCategory("Electronics");
        product.setBrand("SoundMax");
        product.setSales(120);
        product.setImages(Arrays.asList("https://picsum.photos/id/1/800/600", "https://picsum.photos/id/2/800/600"));
        productRepository.save(product);

    }
}
