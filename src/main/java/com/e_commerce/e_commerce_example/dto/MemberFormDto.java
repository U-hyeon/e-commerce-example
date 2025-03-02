package com.e_commerce.e_commerce_example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

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
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    /**
     * 이메일
     */
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message="이메일 형식으로 입력해주세요.")
    private String email;

    /**
     * 비밀번호
     */
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min=8, max=16, message="비밀번호는 8~16자 이어야 합니다.")
    private String password;

    /**
     * 주소
     */
    @NotBlank(message = "주소는 필수 입력 값입니다.")
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
