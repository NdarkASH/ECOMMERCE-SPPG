package com.darknash.ecommerce.dtos.request;

import com.darknash.ecommerce.entities.Product;
import com.darknash.ecommerce.entities.Type;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class StokLogRequest {

    private List<UUID> productIds;

    private Type type;

    private Integer quantity;

    private Integer stockBefore;

    private Integer stockAfter;
}
