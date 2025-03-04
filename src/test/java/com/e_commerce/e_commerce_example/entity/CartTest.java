package com.e_commerce.e_commerce_example.entity;

import com.e_commerce.e_commerce_example.dto.MemberFormDto;
import com.e_commerce.e_commerce_example.repository.CartRepository;
import com.e_commerce.e_commerce_example.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class CartTest {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PersistenceContext
    EntityManager em;

    public Member createMember() {
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail("test1@email.com");
        memberFormDto.setName("test1");
        memberFormDto.setAddress("test address");
        memberFormDto.setPassword("1234");
        return Member.createMember(memberFormDto, passwordEncoder);
    }

    @Test
    @DisplayName("장바구니 회원 엔티티 매핑 조회 테스트")
    public void findCartAndMemberTest() {
        // 멤버생성 & 저장
        Member member = createMember();
        memberRepository.save(member);

        // 카트생성 & 저장
        Cart cart = new Cart();
        cart.setMember(member);
        cartRepository.save(cart);

        // initialize
        em.flush();
        em.clear();

        Cart savedCart = cartRepository.findById(cart.getId())
                                        .orElseThrow(EntityNotFoundException::new);

        // 카트에 연결된 멤버id, 멤버id 일치 확인
        assertEquals(savedCart.getMember().getId(), member.getId());
    }
}