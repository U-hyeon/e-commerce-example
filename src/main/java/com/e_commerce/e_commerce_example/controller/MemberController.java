package com.e_commerce.e_commerce_example.controller;

import com.e_commerce.e_commerce_example.dto.MemberFormDto;
import com.e_commerce.e_commerce_example.entity.Member;
import com.e_commerce.e_commerce_example.service.MemberService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    public MemberController(MemberService memberService, PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        model.addAttribute("errorMessage","");
        return "member/memberForm";
    }

    @PostMapping
    public String memberForm(MemberFormDto memberFormDto) {
        // 회원가입 요청 정보로부터 비밀번호 암호화
        Member member = Member.createMember(memberFormDto, passwordEncoder);
        // 회원가입 진행
        memberService.saveMember(member);
        // 메인페이지로 이동
        return "redirect:/";
    }
}
