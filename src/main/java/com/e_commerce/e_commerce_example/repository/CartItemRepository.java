package com.e_commerce.e_commerce_example.repository;

import com.e_commerce.e_commerce_example.dto.CartDetailDto;
import com.e_commerce.e_commerce_example.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartIdAndItemId(Long cartId, Long itemId);

    @Query("select new com.e_commerce.e_commerce_example.dto.CartDetailDto" +
            "(cartItem.id, item.itemName, item.price, cartItem.amount, itemImg.imgUrl)" +
            "from CartItem cartItem, ItemImg itemImg " +
            "join cartItem.item item " +
            "where cartItem.cart.id = :cartId " +
            "and itemImg.item.id = cartItem.item.id " +
            "and itemImg.representImgFlag = 'Y' " +
            "order by cartItem.regTime desc"
    )
    List<CartDetailDto> findCartDetailDtoList(Long cartId);
}
