package com.teckmils.warehousemanagementsystem.domain.category.service;

import com.teckmils.warehousemanagementsystem.domain.category.model.Category;
import com.teckmils.warehousemanagementsystem.domain.category.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return this.categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long Id) {
        return this.categoryRepository.findById(Id);
    }
}
