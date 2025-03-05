package com.e_commerce.e_commerce_example.repository;

import com.e_commerce.e_commerce_example.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
