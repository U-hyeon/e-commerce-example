package com.e_commerce.e_commerce_example.service;

import com.e_commerce.e_commerce_example.constant.ItemSellStatus;
import com.e_commerce.e_commerce_example.dto.CartItemDto;
import com.e_commerce.e_commerce_example.entity.CartItem;
import com.e_commerce.e_commerce_example.entity.Item;
import com.e_commerce.e_commerce_example.entity.Member;
import com.e_commerce.e_commerce_example.repository.CartItemRepository;
import com.e_commerce.e_commerce_example.repository.ItemRepository;
import com.e_commerce.e_commerce_example.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class CartServiceTest {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CartService cartService;

    @Autowired
    CartItemRepository cartItemRepository;

    public Item saveItem() {
        Item item = new Item();
        item.setItemName("Test");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        return itemRepository.save(item);
    }

    public Member saveMember() {
        Member member = new Member();
        member.setEmail("test@test.com");
        return memberRepository.save(member);
    }

    @Test
    @DisplayName("장바구니 담기 테스트")
    public void addCart() {
        Item item = saveItem();
        Member member = saveMember();

        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setAmount(5);
        cartItemDto.setItemId(item.getId());

        Long cartItemId = cartService.addCart(cartItemDto, member.getEmail());

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);

        assertEquals(item.getId(), cartItem.getItem().getId());
        assertEquals(cartItemDto.getAmount(), cartItem.getAmount());
    }
}