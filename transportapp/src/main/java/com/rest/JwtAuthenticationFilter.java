package com.rest;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger logger = Logger.getLogger(JwtAuthenticationFilter.class.getName());
    private final String secretKey = "thisIsASecureKeyThatIsAtLeast64BytesLongAndRandom123456789012345678901234567890";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            logger.info("No Bearer token found in the request");
            chain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);
        logger.info("Processing token: " + token);
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            logger.info("Token validated. Username: " + username);
            List<?> roles = claims.get("role", List.class);
            logger.info("Roles extracted: " + roles);
            List<SimpleGrantedAuthority> authorities = roles.stream()
                    .map(role -> {
                        if (role instanceof Map) {
                            String authority = (String) ((Map<?, ?>) role).get("authority");
                            logger.info("Processing role: " + authority);
                            return new SimpleGrantedAuthority(authority);
                        }
                        return null;
                    })
                    .filter(authority -> authority != null)
                    .collect(Collectors.toList());
            logger.info("Authorities set: " + authorities);

            if (username != null) {
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(auth);
                logger.info("Authentication set for user: " + username);
            } else {
                logger.warning("Username is null in token claims");
            }
        } catch (Exception e) {
            logger.severe("Failed to validate JWT token: " + e.getMessage());
            SecurityContextHolder.clearContext();
        }

        chain.doFilter(request, response);
    }
}