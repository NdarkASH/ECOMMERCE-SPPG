package com.darknash.ecommerce.dtos.response;

import com.darknash.ecommerce.entities.Product;
import com.darknash.ecommerce.entities.Type;
import jakarta.persistence.*;
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
public class StockLogResponse {

    private UUID id;

    private List<ProductResponse> products;

    private Type type;

    private Integer quantity;

    private Integer stockBefore;

    private Integer stockAfter;

}
