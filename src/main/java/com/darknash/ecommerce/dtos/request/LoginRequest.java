package com.darknash.ecommerce.dtos.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginRequest {

    private String username;

    private String password;
}
