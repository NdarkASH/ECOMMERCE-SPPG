package com.darknash.ecommerce.service;

import com.darknash.ecommerce.dtos.request.CategoryRequest;
import com.darknash.ecommerce.dtos.response.CategoryResponse;
import com.darknash.ecommerce.entities.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest categoryRequest);
    CategoryResponse updateCategory(UUID uuid, CategoryRequest categoryRequest);
    void deleteCategory(UUID uuid);
    CategoryResponse getCategoryById(UUID uuid);
    List<CategoryResponse> getAllCategories();
    Category getCategoryEntity(UUID uuid);

}
