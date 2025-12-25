package com.darknash.ecommerce.repositories;

import com.darknash.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
