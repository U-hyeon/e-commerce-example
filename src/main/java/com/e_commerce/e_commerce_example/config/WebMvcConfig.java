package com.e_commerce.e_commerce_example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${uploadPath}")
    String uploadPath;

    // "/images/**"로 시작하는 url 접근시, uploadPath 폴더를 기준으로 **파일을 찾도록 설정
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations(uploadPath);
    }
}
