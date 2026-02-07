package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.exception.InsufficientStockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderService {
    // 内存存储订单（线程安全的Map）
    private final Map<String, Order> orderMap = new ConcurrentHashMap<>();

    @Autowired
    private  ProductService productService;


    // 创建订单
    public Order createOrder(Long productId, int quantity) {
        // 1. 检查商品是否存在
        Product productOpt = productService.getProductById(productId);
        if (productOpt == null) {
            throw new IllegalArgumentException("商品不存在，ID: " + productId);
        }

        // 2. 检查库存
        if (!productService.checkStock(productId, quantity)) {
            throw new InsufficientStockException("库存不足，商品: " + productOpt.getName());
        }

        // 3. 扣减库存
        productService.reduceStock(productId, quantity);

        // 4. 生成订单ID（UUID）并计算总价
        String orderId = UUID.randomUUID().toString();
        double totalPrice = productOpt.getPrice() * quantity;

        // 5. 创建并保存订单
        Order order = new Order(orderId, productId, quantity, totalPrice);
        System.out.println(order);
        orderMap.put(orderId, order);

        return order;
    }
}