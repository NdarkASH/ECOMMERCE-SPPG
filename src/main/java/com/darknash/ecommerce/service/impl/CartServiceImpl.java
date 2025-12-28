package com.darknash.ecommerce.service.impl;

import com.darknash.ecommerce.dtos.request.CartRequest;
import com.darknash.ecommerce.dtos.response.CartResponse;
import com.darknash.ecommerce.dtos.response.UserResponse;
import com.darknash.ecommerce.entities.Cart;
import com.darknash.ecommerce.repositories.CartRepository;
import com.darknash.ecommerce.service.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;


    @Override
    public CartResponse createCart(CartRequest cartRequest) {
        Cart cart = new Cart();
        cart.setUser(cartRequest.getUser());
        cartRepository.save(cart);

        return toCartResponse(cart);
    }

    @Override
    public CartResponse updateCart(CartRequest cartRequest) {
        Cart cart = cartRepository.findCartByUser_UserName(cartRequest.getUser().getUserName());
        cart.setUser(cartRequest.getUser());
        cartRepository.save(cart);


        return toCartResponse(cart);
    }

    @Override
    public List<CartResponse> getCarts() {
        List<Cart> carts = cartRepository.findAll();

        return carts.stream()
                .map(this::toCartResponse)
                .toList();
    }

    @Override
    public CartResponse getCart(String username) {
        Cart cart = cartRepository.findCartByUser_UserName(username);

        return toCartResponse(cart);
    }

    @Override
    public void deleteCart(UUID uuid) {
        cartRepository.deleteById(uuid);
    }

    private CartResponse toCartResponse(Cart cart) {
        return CartResponse.builder()
                .id(cart.getId())
                .user(UserResponse.builder()
                        .id(cart.getUser().getId())
                        .username(cart.getUser().getUserName())
                        .build())
                .build();
    }
}
