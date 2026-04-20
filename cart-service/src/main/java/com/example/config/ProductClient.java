package com.example.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "http://localhost:8082")
public interface ProductClient
{
    @GetMapping("/products/{id}")
    Object getProduct(@PathVariable Long id);
}
