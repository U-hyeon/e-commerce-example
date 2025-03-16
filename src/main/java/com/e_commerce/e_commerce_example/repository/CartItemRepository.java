package com.e_commerce.e_commerce_example.repository;

import com.e_commerce.e_commerce_example.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartIdAndItemId(Long cartId, Long itemId);
}
