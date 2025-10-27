package com.carolin.invasiveplants.JwtAuth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final TokenBlacklistService tokenBlacklistService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, TokenBlacklistService tokenBlacklistService) {
        this.jwtUtil = jwtUtil;
        this.tokenBlacklistService = tokenBlacklistService;

    }

    /** Filters every request once.
    - Extracts JWT token from HttpOnly cookie
     -validates token and ensures it's not blacklisted.
     - If valid, sets authentication in the security context
     */

     @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterchain)
        throws ServletException, IOException {

        //Get JWT from HttpOnly cookie
        String jwt = getJwtFromCookie(request);

        // if a jwt exists, continue with validation
        if(jwt != null) {
            //check if token is blacklisted (user logged out)
            if(tokenBlacklistService.isBlacklisted(jwt)) {
                //skip authentication if token is invalidated
                filterchain.doFilter(request, response);
                return;
            }

            //Validate token with jwt signature and expiration
            if (jwtUtil.validateToken(jwt)) {
                String email = jwtUtil.getUsernameFromToken(jwt);
                String role = jwtUtil.getRoleFromToken(jwt);

                // Convert role string into Spring Security authority object
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                List<GrantedAuthority> authorities = Collections.singletonList(authority);

                // Create an authentication token for the current user
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(email, null, authorities);

                // Set details and store in security context
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        // Continue filter chain
        filterchain.doFilter(request, response);
    }

    //Method to get jwt from cookies
    private String getJwtFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for(Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
