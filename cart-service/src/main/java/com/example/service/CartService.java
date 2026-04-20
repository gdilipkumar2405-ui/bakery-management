package com.example.service;

import com.example.config.ProductClient;
import com.example.dto.AddToCartRequest;
import com.example.dto.CartItemDto;
import com.example.dto.CartResponse;
import com.example.model.CartEntity;
import com.example.model.CartItemEntity;
import com.example.repository.CartItemRepository;
import com.example.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class CartService
{
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductClient productClient;

    ///add to cart
    public void addToCart(AddToCartRequest request)
    {
        /// validate product via product service
        productClient.getProduct(request.getProductId());

        CartEntity entity = cartRepository.findByUserId(request.getUserId())
                .orElseGet(()->{
                    CartEntity newCart = new CartEntity();
                    newCart.setUserId(request.getUserId());
                    return cartRepository.save(newCart);
                });

        CartItemEntity item = new CartItemEntity();
        item.setCartId(entity.getId());
        item.setProductId(request.getProductId());
        item.setQuantity(request.getQuantity());

        cartItemRepository.save(item);
    }
    // ✅ Get Cart
    public CartResponse getCart(Long userId)
    {
        CartEntity cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        List<CartItemDto> items =
                cartItemRepository.findByCartId(cart.getId())
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        CartResponse response = new CartResponse();
        response.setUserId(userId);
        response.setItems(items);

        return response;
    }
    private CartItemDto mapToDTO(CartItemEntity entity)
    {
        CartItemDto dto = new CartItemDto();
        dto.setProductId(entity.getProductId());
        dto.setQuantity(entity.getQuantity());
        return dto;
    }
}
