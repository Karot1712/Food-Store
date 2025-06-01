package com.karot.food.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private String description;
    private String imageUrl;
    private Integer price;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category",nullable = false)
    private Category category;
}
