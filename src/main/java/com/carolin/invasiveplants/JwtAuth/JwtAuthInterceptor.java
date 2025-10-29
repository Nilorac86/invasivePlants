package com.carolin.invasiveplants.JwtAuth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtAuthInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    public JwtAuthInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        String jwtCookie = null;
        if (request.getCookies() != null) {
            for ( Cookie cookie : request.getCookies()) {
                if ("jwt".equals(cookie.getName())) {
                    jwtCookie = cookie.getValue();
                    break;
                }
            }
        }
        if (jwtCookie == null || !jwtUtil.validateToken(jwtCookie)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        String email= jwtUtil.getUsernameFromToken(jwtCookie);
        request.setAttribute("email", email);
        return true;
    }

}
