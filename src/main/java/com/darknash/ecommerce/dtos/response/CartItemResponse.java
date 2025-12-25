package com.darknash.ecommerce.dtos.response;

import com.darknash.ecommerce.entities.Cart;
import com.darknash.ecommerce.entities.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemResponse {

    private UUID id;

    private List<CartResponse> carts;

    private List<ProductResponse> products;

    private Integer quantity;

    private BigDecimal price;
}
