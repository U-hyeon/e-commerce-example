package com.e_commerce.e_commerce_example.repository;

import com.e_commerce.e_commerce_example.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
