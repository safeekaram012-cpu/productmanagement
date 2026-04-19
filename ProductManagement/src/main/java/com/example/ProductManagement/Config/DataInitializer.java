package com.example.ProductManagement.Config;

import com.example.ProductManagement.Entity.User;
import com.example.ProductManagement.Enum.Role;
import com.example.ProductManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if users already exist
        if (userRepository.count() == 0) {
            // Create test admin user
            User admin = new User();
            admin.setName("Admin User");
            admin.setEmail("admin@test.com");
            admin.setPassword("admin123");
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);

            // Create test vendor user
            User vendor = new User();
            vendor.setName("Vendor User");
            vendor.setEmail("vendor@test.com");
            vendor.setPassword("vendor123");
            vendor.setRole(Role.VENDOR);
            userRepository.save(vendor);

            // Create test customer user
            User customer = new User();
            customer.setName("Customer User");
            customer.setEmail("customer@test.com");
            customer.setPassword("customer123");
            customer.setRole(Role.CUSTOMER);
            userRepository.save(customer);

            System.out.println("Test users created:");
            System.out.println("Admin ID: 1, Email: admin@test.com");
            System.out.println("Vendor ID: 2, Email: vendor@test.com");
            System.out.println("Customer ID: 3, Email: customer@test.com");
        }
    }
}
