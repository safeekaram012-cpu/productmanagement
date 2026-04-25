package com.example.ProductManagement.Controller;

import com.example.ProductManagement.Entity.Orders;
import com.example.ProductManagement.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long productId,
            @RequestParam(required = false) Integer qty
    ) {
        try {
            // Validate required parameters
            if (userId == null) {
                return ResponseEntity.badRequest().body("User ID is required");
            }
            if (productId == null) {
                return ResponseEntity.badRequest().body("Product ID is required");
            }
            if (qty == null || qty <= 0) {
                return ResponseEntity.badRequest().body("Quantity must be a positive number");
            }
            
            Orders order = orderService.placeOrder(userId, productId, qty);
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Internal server error: " + e.getMessage());
        }
    }

    @GetMapping("/user/{id}")
    public List<Orders> getOrders(@PathVariable Long id) {
        return orderService.getUserOrders(id);
    }
}
