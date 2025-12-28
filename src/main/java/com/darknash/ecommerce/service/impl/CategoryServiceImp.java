package com.darknash.ecommerce.service.impl;

import com.darknash.ecommerce.dtos.request.CategoryRequest;
import com.darknash.ecommerce.dtos.response.CategoryResponse;
import com.darknash.ecommerce.entities.Category;
import com.darknash.ecommerce.repositories.CategoryRepository;
import com.darknash.ecommerce.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {

        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setSlug(categoryRequest.getSlug());
        categoryRepository.save(category);

        return toCategoryResponse(category);
    }

    @Override
    public CategoryResponse updateCategory(UUID uuid, CategoryRequest categoryRequest) {

        Category category = getCategoryEntity(uuid);

        category.setName(categoryRequest.getName());
        category.setSlug(categoryRequest.getSlug());
        categoryRepository.save(category);

        return toCategoryResponse(category);
    }

    @Override
    public void deleteCategory(UUID uuid) {
        categoryRepository.deleteById(uuid);
    }

    @Override
    public CategoryResponse getCategoryById(UUID uuid) {
        Category category = getCategoryEntity(uuid);

        return toCategoryResponse(category);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(this::toCategoryResponse)
                .toList();
    }

    @Override
    public Category getCategoryEntity(UUID uuid) {
        return categoryRepository.findById(uuid)
                .orElseThrow(()-> new RuntimeException("Category not found!"));
    }

    private CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .slug(category.getSlug())
                .name(category.getName())
                .build();
    }

}
