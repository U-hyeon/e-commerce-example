package com.e_commerce.e_commerce_example.repository;

import com.e_commerce.e_commerce_example.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
}
