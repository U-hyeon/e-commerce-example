package com.e_commerce.e_commerce_example.entity;

import com.e_commerce.e_commerce_example.constant.ItemSellStatus;
import com.e_commerce.e_commerce_example.repository.ItemRepository;
import com.e_commerce.e_commerce_example.repository.MemberRepository;
import com.e_commerce.e_commerce_example.repository.OrderItemRepository;
import com.e_commerce.e_commerce_example.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class OrderTest {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @PersistenceContext
    EntityManager em;

    public Item createItem() {
        Item item = new Item();
        item.setItemName("test item");
        item.setPrice(10000);
        item.setItemDetail("test item detail");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        return item;
    }

    @Test
    @DisplayName("영속성 전이 테스트")
    public void cascadeTest(){
        Order order = new Order();

        for(int i=0; i<3; i++) {
            Item item = this.createItem();
            itemRepository.save(item);
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setAmount(10);
            orderItem.setOrderPrice(1000);
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        }

        orderRepository.saveAndFlush(order);
        em.clear();

        Order savedOrder = orderRepository.findById(order.getId())
                .orElseThrow(EntityNotFoundException::new);
        assertEquals(3, savedOrder.getOrderItems().size());
    }

    public Order createOrder() {
        Order order = new Order();

        for (int i=0; i<3; i++) {
            Item item = createItem();
            itemRepository.save(item);
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setAmount(10);
            orderItem.setOrderPrice(1000);
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        }

        Member member = new Member();
        memberRepository.save(member);

        order.setMember(member);
        orderRepository.save(order);
        return order;
    }

    @Test
    @DisplayName("고아객체 제거 테스트")
    public void orphanRemovalTest() {
        Order order = this.createOrder();
        order.getOrderItems().remove(0);
        em.flush();
    }

    /**
     * 지연로딩 : 즉시로딩 수행시 매핑된 엔티티를 모두 조인해서 가지고 오기 때문에, 쿼리 예측이 어렵고 성능저하우려.
     */
    @Test
    @DisplayName("지연 로딩 테스트")
    public void lazyLoadingTest() {
        Order order = this.createOrder();
        Long orderItemId = order.getOrderItems().get(0).getId();
        em.flush();
        em.clear();

        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(EntityNotFoundException::new);
        System.out.println("Order class : " + orderItem.getOrder().getClass());
        // 지연로딩시 실제 엔티티 대신 프록시객체를 넣어둔다.
        // 프록시 객체는 실제 사용되기 전까지 데이터 로딩 X
        System.out.println("=====================");
        orderItem.getOrder().getOrderDate();
        // 실제 사용시점에 조회 쿼리문 실행
        System.out.println("=====================");

    }
}