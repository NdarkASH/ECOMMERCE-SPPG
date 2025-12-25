package com.darknash.ecommerce.dtos.request;

import com.darknash.ecommerce.entities.Cart;
import com.darknash.ecommerce.entities.Product;
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
public class CartItemRequest {


    private List<UUID> cartIds;

    private List<UUID> productIds;

    private Integer quantity;

    private BigDecimal price;
}
