package com.example.ProductManagement.Repository;

import com.example.ProductManagement.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByVendorId(Long vendorId);
}
