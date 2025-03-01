package com.e_commerce.e_commerce_example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;


@Configuration
public class SecurityConfig {
    @Bean
    protected SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/", "/login", "/**").permitAll()
                .requestMatchers("/**").hasRole("ADMIN")
                .requestMatchers("/api/v1/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
        );
        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(
                session -> session.sessionCreationPolicy
                        (SessionCreationPolicy.STATELESS));
        http.csrf(AbstractHttpConfigurer::disable);
//        http.headers().frameOptions().sameOrigin(); // Deprecated
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
