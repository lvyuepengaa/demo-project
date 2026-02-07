package com.example.demo.controller;

import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private  OrderService orderService;

    // POST /orders：创建订单
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Map<String, Object> requestBody) {
        try {
            Long productId = Long.valueOf(requestBody.get("productId").toString());
            int quantity = Integer.parseInt(requestBody.get("quantity").toString());

            Order order = orderService.createOrder(productId, quantity);
            System.out.println(order);

            // 返回订单ID和总价
            return new ResponseEntity<>(Map.of(
                    "orderId", order.getId(),
                    "totalPrice", order.getTotalPrice()
            ), HttpStatus.CREATED);
        }  catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "订单创建失败"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}