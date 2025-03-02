package com.e_commerce.e_commerce_example.config;

import com.e_commerce.e_commerce_example.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class SecurityConfig {
    @Autowired
    MemberService memberService;

    @Bean
    protected SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/", "/members/login","/members/login/error", "/members/new").permitAll()
                .requestMatchers("/**").hasRole("ADMIN")
                .requestMatchers("/api/v1/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
        ).formLogin((formLogin) -> formLogin
                .loginPage("/members/login")
                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .failureUrl("/members/login/error")
        ).logout((logout) -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                .logoutSuccessUrl("/")
        ).userDetailsService(memberService);

//        http.httpBasic(Customizer.withDefaults());
//        http.sessionManagement(
//                session -> session.sessionCreationPolicy
//                        (SessionCreationPolicy.STATELESS));
//        http.csrf(AbstractHttpConfigurer::disable);
//        http.headers().frameOptions().sameOrigin(); // Deprecated

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
