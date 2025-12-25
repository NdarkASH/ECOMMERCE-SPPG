package com.darknash.ecommerce.service;

import com.darknash.ecommerce.dtos.response.AuthResponse;
import com.darknash.ecommerce.securities.AuthenticatedUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface JwtService {
    Authentication getAuthentication(String email, String password);
    AuthResponse generateToken(AuthenticatedUser principal);
    String extractUsers(String token);
    boolean validateToken(String token);
    List<String> extractRoles(String token);
    UserDetails loadUserByUsername(String username);
}
