package com.example.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cart-service", url = "http://localhost:8083")
public interface CartClient
{
    @GetMapping("/cart/{userId}")
    Object getCart(@PathVariable Long userId);
}
