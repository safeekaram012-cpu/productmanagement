package com.example.ProductManagement.Service;

import com.example.ProductManagement.Entity.Product;
import com.example.ProductManagement.Entity.User;
import com.example.ProductManagement.Enum.Role;
import com.example.ProductManagement.Repository.ProductRepository;
import com.example.ProductManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public Product addByAdmin(Product product, Long adminId) {
        User user = userRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + adminId + ". Please create an admin user first."));

        if (user.getRole() != Role.ADMIN) {
            throw new RuntimeException("Only ADMIN allowed");
        }

        return productRepository.save(product);
    }

    public Product addByVendor(Product product, Long vendorId) {
        User user = userRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found with id: " + vendorId + ". Please create a vendor user first."));

        if (user.getRole() != Role.VENDOR) {
            throw new RuntimeException("Only VENDOR allowed");
        }

        product.setVendorId(vendorId);
        return productRepository.save(product);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public List<Product> getVendorProducts(Long vendorId) {
        return productRepository.findByVendorId(vendorId);
    }
}