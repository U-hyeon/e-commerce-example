package com.e_commerce.e_commerce_example.repository;

import com.e_commerce.e_commerce_example.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 상품 이미지 정보 저장
 */
public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {
    List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);
}
