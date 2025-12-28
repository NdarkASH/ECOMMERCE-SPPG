package com.darknash.ecommerce.dtos.request;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    private String name;

    private String sku;

    private String description;

    private BigDecimal price;

    private UUID categoryId;
}
