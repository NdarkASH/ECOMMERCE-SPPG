package com.darknash.ecommerce.service;

import com.darknash.ecommerce.dtos.request.CartItemRequest;
import com.darknash.ecommerce.dtos.response.CartItemResponse;

import java.util.UUID;

public interface CartItemService {
    CartItemResponse createCartItem(CartItemRequest cartItemRequest);
    CartItemResponse updateCartItem(CartItemRequest cartItemRequest);
    CartItemResponse deleteCartItem(CartItemRequest cartItemRequest);
    CartItemResponse getCartItem(CartItemRequest cartItemRequest);
    CartItemResponse getCartItemById(UUID id);
    CartItemResponse getCartItemBySlug(String slug);

}
