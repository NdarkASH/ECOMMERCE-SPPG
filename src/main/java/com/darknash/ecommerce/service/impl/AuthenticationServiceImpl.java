package com.darknash.ecommerce.service.impl;

import com.darknash.ecommerce.dtos.response.AuthResponse;
import com.darknash.ecommerce.entities.User;
import com.darknash.ecommerce.repositories.UserRepository;
import com.darknash.ecommerce.securities.AuthenticatedUser;
import com.darknash.ecommerce.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements JwtService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Override
    public Authentication getAuthentication(String email, String password) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
    }

    @Override
    public AuthResponse generateToken(AuthenticatedUser principal) {
        long expiresAt = System.currentTimeMillis() + jwtExpiration;

        String token = Jwts.builder()
                .subject(principal.getUsername())
                .claim("roles", principal.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList()
                ).expiration(new Date(System.currentTimeMillis() + expiresAt))
                .issuedAt(new Date())
                .signWith(getJwtSecret())
                .compact();

        return AuthResponse.builder()
                .token(token)
                .expiresIn(expiresAt)
                .build();
    }

    @Override
    public String extractUsers(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    @Override
    public boolean validateToken(String token) {
        return getClaimsFromToken(token).getExpiration().after(new Date());
    }

    @Override
    public List<String> extractRoles(String token) {
        Claims claims = getClaimsFromToken(token);

        Object roles = claims.get("roles");
        if (roles instanceof List<?> roleList) {
            return roleList.stream()
                    .map(Object::toString)
                    .toList();
        }
        return List.of();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(()-> new UsernameNotFoundException(username));

        return new AuthenticatedUser(user);
    }

    private JwtParser getJwtParser() {

        return Jwts.parser()
                .verifyWith(getJwtSecret())
                .build();
    }

    private SecretKey getJwtSecret() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    private Claims getClaimsFromToken(String token) {
        return getJwtParser()
                .parseSignedClaims(token)
                .getPayload();
    }


}
