package com.airtribe.newsAPI.configuration;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.airtribe.newsAPI.utils.JwtUtil;
import org.springframework.web.filter.OncePerRequestFilter;


public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authenticationHeader = request.getHeader("authorization");

        if (request.getRequestURI().contains("/api/register") ||
                request.getRequestURI().contains("/api/dashboard") ||
                request.getRequestURI().contains("/api/login") ||
                request.getRequestURI().contains("/h2-console")||
                request.getRequestURI().contains("/h2-console/**"))  {
            filterChain.doFilter(request, response);
            return;
        }

        if (authenticationHeader == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing Authorization header");
            return;
        }

        if(!JwtUtil.validateJwtToken(authenticationHeader)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid user token");
            return;
        }


        filterChain.doFilter(request, response);
    }
}