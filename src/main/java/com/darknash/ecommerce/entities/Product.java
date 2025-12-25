package com.darknash.ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String sku;

    private String description;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "categori_id", nullable = false)
    private Category categories;

    @OneToMany(mappedBy = "product")
    private List<StockLog> stockLogList;

}
