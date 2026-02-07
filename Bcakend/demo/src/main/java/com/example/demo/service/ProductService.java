package com.example.demo.service;

import com.example.demo.entity.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProductService {
    // 内存存储商品（线程安全的Map）
    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();

    // 初始化示例商品数据
    public ProductService() {
        productMap.put(1L, new Product(1L, "iPhone 15", 5999.00, 100));
        productMap.put(2L, new Product(2L, "MacBook Pro", 12999.00, 300));
        productMap.put(3L, new Product(3L, "AirPods Pro", 1899.00, 150));
        productMap.put(4L, new Product(4L, "Apple Watch", 2899.00, 150));
        productMap.put(5L, new Product(5L, "MacBook Air", 7899, 400));
        productMap.put(6L, new Product(6L, "iPhone 15 Pro", 6899.00, 600));
        productMap.put(7L, new Product(7L, "iPhone 15 ProMax", 9899.00, 700));
    }
    public List<Product> getProductList() {
        return new ArrayList<>(productMap.values());
    }

    // 根据ID获取商品
    public Product  getProductById(Long id) {
        return productMap.get(id);
    }

    // 检查库存是否足够
    public boolean checkStock(Long productId, int quantity) {
        Product product = productMap.get(productId);
        return product != null && product.getStock() >= quantity;
    }

    // 扣减库存
    public void reduceStock(Long productId, int quantity) {
        Product product = productMap.get(productId);
        product.setStock(product.getStock() - quantity);
    }
}
