package com.darknash.ecommerce.controllers;

import com.darknash.ecommerce.dtos.request.CartRequest;
import com.darknash.ecommerce.dtos.response.AppResponse;
import com.darknash.ecommerce.dtos.response.CartResponse;
import com.darknash.ecommerce.entities.User;
import com.darknash.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping
    public AppResponse<CartResponse> addCart(@RequestBody CartRequest cartRequest) {
        CartResponse cartResponse = cartService.createCart(cartRequest);

        return AppResponse.<CartResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Cart created successfully")
                .data(cartResponse)
                .build();
    }

    @GetMapping
    public AppResponse<CartResponse> getCart(@AuthenticationPrincipal User user ) {
        CartResponse cartResponse = cartService.getCart(user.getUserName());

        return AppResponse.<CartResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Cart successfully retrieved")
                .data(cartResponse)
                .build();
    }

}
