package com.darknash.ecommerce.service;

import com.darknash.ecommerce.dtos.request.ProductRequest;
import com.darknash.ecommerce.dtos.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);
    ProductResponse updateProduct(UUID uuid, ProductRequest request);
    Page<ProductResponse> getProductById(Pageable pageable, String categorySlug);
    ProductResponse getProductById(UUID uuid);
    void deleteProduct(UUID uuid);
}
