package com.example.ProductManagement.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private String category;
    private Integer stock;


    private Long vendorId; // 🔥 important
    @Lob
    private String image1; // Primary image
    @Lob
    private String image2;
    @Lob
    private String image3;
    @Lob
    private String image4;
}