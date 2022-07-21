package com.teckmils.warehousemanagementsystem.domain.category.service;

import com.teckmils.warehousemanagementsystem.domain.category.repository.CategoryRepository;

public class Service {
    private final CategoryRepository categoryRepository;

    public Service(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
