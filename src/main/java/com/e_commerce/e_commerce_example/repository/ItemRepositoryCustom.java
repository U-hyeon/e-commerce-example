package com.e_commerce.e_commerce_example.repository;

import com.e_commerce.e_commerce_example.dto.ItemSearchDto;
import com.e_commerce.e_commerce_example.dto.MainItemDto;
import com.e_commerce.e_commerce_example.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {
    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}
