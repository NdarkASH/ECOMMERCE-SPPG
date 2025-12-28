package com.darknash.ecommerce.controllers;

import com.darknash.ecommerce.dtos.request.ProductRequest;
import com.darknash.ecommerce.dtos.response.AppResponse;
import com.darknash.ecommerce.dtos.response.PageResponse;
import com.darknash.ecommerce.dtos.response.ProductResponse;
import com.darknash.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public AppResponse<ProductResponse> addProduct(@RequestBody ProductRequest request) {
        ProductResponse productResponse = productService.createProduct(request);

        return AppResponse.<ProductResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Product created successfully")
                .data(productResponse)
                .build();
    }

    @PutMapping("/{uuid}")
    public AppResponse<ProductResponse> updateProduct( @PathVariable UUID uuid, @RequestBody ProductRequest request) {
        ProductResponse productResponse = productService.updateProduct(uuid,request);

        return AppResponse.<ProductResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Product updated successfully")
                .data(productResponse)
                .build();

    }

    @GetMapping
    public AppResponse<PageResponse<ProductResponse>> getAllProduct (
            @RequestParam String categorySlug,
            Pageable pageable
    ) {
        Page<ProductResponse> productResponses = productService.getProductById(pageable, categorySlug);

        PageResponse<ProductResponse> pageResponse = PageResponse.<ProductResponse>builder()
                .content(productResponses.getContent())
                .number(productResponses.getNumber())
                .size(productResponses.getSize())
                .totalPages(productResponses.getTotalPages())
                .totalOfElements(productResponses.getTotalElements())
                .numberOfElements(productResponses.getNumberOfElements())
                .build();


        return AppResponse.<PageResponse<ProductResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Products found successfully")
                .data(pageResponse)
                .build();


    }

    @GetMapping("/uuid")
    public AppResponse<ProductResponse> getProductById(@PathVariable UUID uuid) {

        ProductResponse productResponse = productService.getProductById(uuid);

        return AppResponse.<ProductResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Product found successfully")
                .data(productResponse)
                .build();
    }

    @DeleteMapping("/uuid")
    public AppResponse<Void> deleteProductById(@PathVariable UUID uuid) {

        productService.deleteProduct(uuid);

        return AppResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Product deleted successfully")
                .data(null)
                .build();
    }





}
