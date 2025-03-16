package com.e_commerce.e_commerce_example.entity;

import jakarta.persistence.*;
import lombok.ToString;

@Entity
@Table(name = "cart")
@ToString
public class Cart extends BaseEntity {
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동생성
    private Long id;

    @OneToOne(fetch = FetchType.LAZY) // default FetchType = EAGER
    @JoinColumn(name = "member_id")
    private Member member;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public static Cart createCart(Member member) {
        Cart cart = new Cart();
        cart.setMember(member);
        return cart;
    }
}
