package com.example.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.cloud.gateway.filter.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class JwtAuthenticationFilter implements GlobalFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();

        log.info("Incoming request: {}", path);

        // ✅ Allow public endpoints
        if (List.of("/auth", "/products", "/reviews")
                .stream()
                .anyMatch(path::startsWith)) {
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.error("Missing or invalid Authorization header");
            throw new UnauthorizedException("Missing Authorization Header");
        }

        String token = authHeader.substring(7);

        if (!jwtUtil.validateToken(token)) {
            log.error("Invalid JWT token");
            throw new UnauthorizedException("Invalid Token");
        }

        String email = jwtUtil.extractEmail(token);

        log.info("Authenticated user: {}", email);

        exchange = exchange.mutate()
                .request(r -> r.header("X-User-Email", email))
                .build();

        return chain.filter(exchange);
    }
}