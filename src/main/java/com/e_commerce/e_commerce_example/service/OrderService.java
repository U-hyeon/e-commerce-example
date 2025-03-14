package com.e_commerce.e_commerce_example.service;

import com.e_commerce.e_commerce_example.dto.OrderDto;
import com.e_commerce.e_commerce_example.dto.OrderHistoryDto;
import com.e_commerce.e_commerce_example.dto.OrderItemDto;
import com.e_commerce.e_commerce_example.entity.*;
import com.e_commerce.e_commerce_example.repository.ItemImgRepository;
import com.e_commerce.e_commerce_example.repository.ItemRepository;
import com.e_commerce.e_commerce_example.repository.MemberRepository;
import com.e_commerce.e_commerce_example.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderService {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemImgRepository itemImgRepository;

    public OrderService(ItemRepository itemRepository, MemberRepository memberRepository, OrderRepository orderRepository, ItemImgRepository itemImgRepository) {
        this.itemRepository = itemRepository;
        this.memberRepository = memberRepository;
        this.orderRepository = orderRepository;
        this.itemImgRepository = itemImgRepository;
    }

    public Long order(OrderDto orderDto, String email) {
        Item item = itemRepository.findById(orderDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByEmail(email);

        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getAmount());
        orderItemList.add(orderItem);

        Order order = Order.createOrder(member, orderItemList);
        orderRepository.save(order);

        return order.getId();
    }

    @Transactional(readOnly = true)
    public Page<OrderHistoryDto> getOrderList(String email, Pageable pageable) {
        List<Order> orders = orderRepository.findOrders(email, pageable);
        Long totalCount = orderRepository.countOrder(email);

        List<OrderHistoryDto> orderHistoryDtoList = new ArrayList<>();

        for (Order order : orders) {
            OrderHistoryDto orderHistoryDto = new OrderHistoryDto(order);
            List<OrderItem> orderItems = order.getOrderItems();
            for(OrderItem orderItem : orderItems) {
                ItemImg itemImg = itemImgRepository.findByItemIdAndRepresentImgFlag(orderItem.getItem().getId(), "Y");
                OrderItemDto orderItemDto = new OrderItemDto(orderItem, itemImg.getImgUrl());
                orderHistoryDto.addOrderItemDto(orderItemDto);
            }

            orderHistoryDtoList.add(orderHistoryDto);
        }

        return new PageImpl<OrderHistoryDto>(orderHistoryDtoList, pageable, totalCount);
    }
}
