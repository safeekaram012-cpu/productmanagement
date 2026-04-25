package com.example.ProductManagement.Service;

import com.example.ProductManagement.Entity.Orders;
import com.example.ProductManagement.Entity.Product;
import com.example.ProductManagement.Entity.User;
import com.example.ProductManagement.Enum.Role;
import com.example.ProductManagement.Repository.OrderRepository;
import com.example.ProductManagement.Repository.ProductRepository;
import com.example.ProductManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public Orders placeOrder(Long userId, Long productId, Integer qty) {

        // Check if user exists
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new RuntimeException("User not found with ID: " + userId);
        }

        // Role restriction removed - any user can now place orders

        // Check if product exists
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            throw new RuntimeException("Product not found with ID: " + productId);
        }

        // Check if stock is sufficient
        if (product.getStock() < qty) {
            throw new RuntimeException("Stock not enough. Available: " + product.getStock() + ", Requested: " + qty);
        }

        // Calculate total price
        double total = product.getPrice() * qty;

        // Update product stock
        product.setStock(product.getStock() - qty);
        productRepository.save(product);

        // Create and save order
        Orders order = new Orders();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setQuantity(qty);
        order.setTotalPrice(total);

        return orderRepository.save(order);
    }

    public List<Orders> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
