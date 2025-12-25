package com.darknash.ecommerce.dtos.response;

import com.darknash.ecommerce.entities.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {

    private UUID id;

    private UserResponse user;

    private List<CartItem> cartItemIds;
}
