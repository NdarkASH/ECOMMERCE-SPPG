package com.darknash.ecommerce.controllers;

import com.darknash.ecommerce.dtos.request.LoginRequest;
import com.darknash.ecommerce.dtos.response.AppResponse;
import com.darknash.ecommerce.dtos.response.AuthResponse;
import com.darknash.ecommerce.securities.AuthenticatedUser;
import com.darknash.ecommerce.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final JwtService jwtService;

    @PostMapping(path = "/login")
    public AppResponse<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = jwtService.getAuthentication(loginRequest.getUsername(), loginRequest.getPassword());

        AuthenticatedUser authenticatedUser = (AuthenticatedUser) authentication.getPrincipal();

        AuthResponse token  = jwtService.generateToken(authenticatedUser);

        return AppResponse.<AuthResponse>builder()
                .code(HttpStatus.OK.value())
                .message(token.getToken())
                .data(token)
                .build();
    }


}
