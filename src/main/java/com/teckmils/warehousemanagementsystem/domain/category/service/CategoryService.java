package com.teckmils.warehousemanagementsystem.domain.category.service;

import com.teckmils.warehousemanagementsystem.domain.category.dto.AddCategoryItem;
import com.teckmils.warehousemanagementsystem.domain.category.dto.CategoryItemDTO;
import com.teckmils.warehousemanagementsystem.domain.category.model.Category;
import com.teckmils.warehousemanagementsystem.domain.category.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryItemDTO> getCategories() {
        final List<Category> categories = this.categoryRepository.findAll();
        List<CategoryItemDTO> categoryItemDTOS = new ArrayList<>();
        categories.forEach(category -> categoryItemDTOS.add(new CategoryItemDTO(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getCreatedAt(),
                category.getUpdatedAt()
        )));
        return categoryItemDTOS;
    }

    public CategoryItemDTO getCategoryById(UUID id) {
        final Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new CategoryItemDTO(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getCreatedAt(),
                category.getUpdatedAt()
        );
    }

    public void addCategories(final Collection<AddCategoryItem> rawCategories) {
        rawCategories.forEach(rawCategory -> {
            final var category = new Category(rawCategory.name(), rawCategory.description());
            this.categoryRepository.save(category);
        });
    }

    public void deleteCategoryById(final UUID id) {
        this.categoryRepository.deleteById(id);
    }
}
