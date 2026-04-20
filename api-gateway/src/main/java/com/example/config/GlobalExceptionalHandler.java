package com.example.config;

import com.example.security.UnauthorizedException;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.server.*;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

public class GlobalExceptionalHandler implements ErrorWebExceptionHandler
{
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex)
    {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "Something went wrong";

        if (ex instanceof UnauthorizedException)
        {
            status = HttpStatus.UNAUTHORIZED;
            message = ex.getMessage();
        }

        String response = """
        {
          "status": "FAILED",
          "error": {
            "code": "%s",
            "message": "%s"
          }
        }
        """.formatted(status, message);

        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);

        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        return exchange.getResponse()
                .writeWith(Mono.just(exchange.getResponse()
                        .bufferFactory().wrap(bytes)));
    }
}
