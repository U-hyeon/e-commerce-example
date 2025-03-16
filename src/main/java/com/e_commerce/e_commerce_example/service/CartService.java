package com.e_commerce.e_commerce_example.service;

import com.e_commerce.e_commerce_example.dto.CartItemDto;
import com.e_commerce.e_commerce_example.entity.Cart;
import com.e_commerce.e_commerce_example.entity.CartItem;
import com.e_commerce.e_commerce_example.entity.Item;
import com.e_commerce.e_commerce_example.entity.Member;
import com.e_commerce.e_commerce_example.repository.CartItemRepository;
import com.e_commerce.e_commerce_example.repository.CartRepository;
import com.e_commerce.e_commerce_example.repository.ItemRepository;
import com.e_commerce.e_commerce_example.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartService {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public CartService(ItemRepository itemRepository, MemberRepository memberRepository
            , CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.itemRepository = itemRepository;
        this.memberRepository = memberRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public Long addCart(CartItemDto cartItemDto, String email) {
        Item item = itemRepository.findById(cartItemDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByEmail(email);

        Cart cart = cartRepository.findByMemberId(member.getId());
        if(cart == null) {
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }

        CartItem savedCartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());
        if(savedCartItem != null) {
            savedCartItem.addAmount(cartItemDto.getAmount());
            return savedCartItem.getId();
        } else {
            CartItem cartItem = CartItem.createCartItem(cart, item, cartItemDto.getAmount());
            cartItemRepository.save(cartItem);
            return cartItem.getId();
        }
    }
}
