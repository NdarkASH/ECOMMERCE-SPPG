package com.darknash.ecommerce.repositories;

import com.darknash.ecommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
