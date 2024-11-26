package com.example.seckill.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "t_goods")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String title;

    private String img;

    private String detail;

    private BigDecimal price;

    private Integer stock;

    // Getter and Setter methods
}
