package com.e_commerce.e_commerce_example.repository;

import com.e_commerce.e_commerce_example.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByMemberId(Long memberId);
}
