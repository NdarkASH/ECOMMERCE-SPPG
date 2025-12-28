package com.darknash.ecommerce.service;

import com.darknash.ecommerce.dtos.request.CartRequest;
import com.darknash.ecommerce.dtos.response.CartResponse;

import java.util.List;
import java.util.UUID;

public interface CartService {
    CartResponse createCart(CartRequest cartRequest);
    CartResponse updateCart(CartRequest cartRequest);
    List<CartResponse> getCarts();
    CartResponse getCart(String username);
    void deleteCart(UUID uuid);
}
