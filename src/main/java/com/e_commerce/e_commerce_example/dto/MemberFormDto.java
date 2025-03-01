package com.e_commerce.e_commerce_example.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * <sl>
 *     <li>회원명</li>
 *     <li>이메일</li>
 *     <li>비밀번호</li>
 *     <li>주소</li>
 * </sl>
 */
public class MemberFormDto {
    /**
     * 회원명
     */
    private String name;

    /**
     * 이메일
     */
    private String email;

    /**
     * 비밀번호
     */
    private String password;

    /**
     * 주소
     */
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
