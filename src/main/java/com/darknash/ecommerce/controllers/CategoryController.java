package com.darknash.ecommerce.controllers;

import com.darknash.ecommerce.dtos.request.CategoryRequest;
import com.darknash.ecommerce.dtos.response.AppResponse;
import com.darknash.ecommerce.dtos.response.CategoryResponse;
import com.darknash.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping
    public AppResponse<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = categoryService.createCategory(categoryRequest);

        return AppResponse.<CategoryResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Category created successfully")
                .data(categoryResponse)
                .build();
    }


    @PutMapping("/{uuid}")
    public AppResponse<CategoryResponse> updateCategory(@PathVariable UUID uuid, @RequestBody CategoryRequest categoryRequest) {

        CategoryResponse categoryResponse = categoryService.updateCategory(uuid, categoryRequest);

        return AppResponse.<CategoryResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Category updated successfully")
                .data(categoryResponse)
                .build();
    }

    @GetMapping("/{uuid}")
    public AppResponse<CategoryResponse> getCategory(@PathVariable UUID uuid) {
        CategoryResponse categoryResponse = categoryService.getCategoryById(uuid);

        return AppResponse.<CategoryResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Category get successfully")
                .data(categoryResponse)
                .build();
    }

    @GetMapping
    public AppResponse<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> categoryResponseList = categoryService.getAllCategories();

        return AppResponse.<List<CategoryResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Category get successfully")
                .data(categoryResponseList)
                .build();
    }

    @DeleteMapping("/{uuid}")
    public AppResponse<Void> deleteCategory(@PathVariable UUID uuid) {
        categoryService.deleteCategory(uuid);

        return AppResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Category deleted successfully")
                .build();
    }
}
