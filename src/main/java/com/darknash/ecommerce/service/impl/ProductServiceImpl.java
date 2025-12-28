package com.darknash.ecommerce.service.impl;

import com.darknash.ecommerce.dtos.request.ProductRequest;
import com.darknash.ecommerce.dtos.response.CategoryResponse;
import com.darknash.ecommerce.dtos.response.ProductResponse;
import com.darknash.ecommerce.entities.Product;
import com.darknash.ecommerce.repositories.ProductRepository;
import com.darknash.ecommerce.service.CategoryService;
import com.darknash.ecommerce.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService  categoryService;


    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(categoryService.getCategoryEntity(request.getCategoryId()));
        productRepository.save(product);

        return toProductResponse(product);
    }

    @Override
    public ProductResponse updateProduct(UUID uuid, ProductRequest request) {

        Product product = productRepository.findById(uuid)
                .orElseThrow(()-> new RuntimeException("product not found"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setSku(request.getSku());
        product.setCategory(categoryService.getCategoryEntity(request.getCategoryId()));
        productRepository.save(product);
        return toProductResponse(product);
    }

    @Override
    public Page<ProductResponse> getProductById(Pageable pageable, String categorySlug) {

        Page<Product> products = productRepository.findByCategory_Slug(categorySlug, pageable);


        return products.map(this::toProductResponse);
    }


    @Override
    public ProductResponse getProductById(UUID uuid) {

       Product product = productRepository.findById(uuid)
               .orElseThrow(()-> new RuntimeException("product not found"));

        return toProductResponse(product);
    }

    @Override
    public void deleteProduct(UUID uuid) {
        productRepository.deleteById(uuid);

    }


    private ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .sku(product.getSku())
                .categoryResponse(
                        CategoryResponse.builder()
                                .id(product.getCategory().getId())
                                .name(product.getCategory().getName())
                                .slug(product.getCategory().getSlug())
                                .build())
                .build();
    }


}
