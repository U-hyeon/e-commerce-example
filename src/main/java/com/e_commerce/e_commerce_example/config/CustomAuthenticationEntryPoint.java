package com.e_commerce.e_commerce_example.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;

/**
 * ajax 요청에 대하여 인증진행
 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * 인증되지 않은 사용자 => 401 unauthorized error
     * else => redirect login page
     *
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException
    ) throws IOException, ServletException {
        if ("XMLHttpRequest".equals(request.getHeader("X-requested-with"))) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"); // 401 (unauthorized) error
        } else {
            response.sendRedirect("/members/login");
        }
    }
}
