package com.example.ProductManagement.Controller;

import com.example.ProductManagement.Entity.Orders;
import com.example.ProductManagement.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public Orders placeOrder(
            @RequestParam Long userId,
            @RequestParam Long productId,
            @RequestParam Integer qty
    ) {
        return orderService.placeOrder(userId, productId, qty);
    }

    @GetMapping("/user/{id}")
    public List<Orders> getOrders(@PathVariable Long id) {
        return orderService.getUserOrders(id);
    }
}
