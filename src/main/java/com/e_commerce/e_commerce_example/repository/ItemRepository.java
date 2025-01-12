package com.e_commerce.e_commerce_example.repository;

import com.e_commerce.e_commerce_example.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
