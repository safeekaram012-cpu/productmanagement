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

        User user = userRepository.findById(userId).orElseThrow();

        if (user.getRole() != Role.CUSTOMER) {
            throw new RuntimeException("Only CUSTOMER can order");
        }

        Product product = productRepository.findById(productId).orElseThrow();

        if (product.getStock() < qty) {
            throw new RuntimeException("Stock not enough");
        }

        double total = product.getPrice() * qty;

        product.setStock(product.getStock() - qty);
        productRepository.save(product);

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
