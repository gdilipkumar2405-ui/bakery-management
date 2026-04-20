package com.example.controller;

import com.example.dto.AddToCartRequest;
import com.example.dto.CartResponse;
import com.example.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController
{
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public String addToCart(@RequestBody AddToCartRequest request)
    {
        cartService.addToCart(request);
        return "item added to cart.";
    }

    @GetMapping("/{userId}")
    public CartResponse getCart(@PathVariable Long userId)
    {
        return cartService.getCart(userId);
    }
}
