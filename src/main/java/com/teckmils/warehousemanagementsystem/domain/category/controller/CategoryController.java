package com.teckmils.warehousemanagementsystem.domain.category.controller;

import com.teckmils.warehousemanagementsystem.domain.category.dto.AddListOfCategories;
import com.teckmils.warehousemanagementsystem.domain.category.dto.CategoryItemDTO;
import com.teckmils.warehousemanagementsystem.domain.category.model.Category;
import com.teckmils.warehousemanagementsystem.domain.category.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(path = "/categories", method = RequestMethod.GET)
    public List<CategoryItemDTO> getCategories() {
        return this.categoryService.getCategories();
    }

    @GetMapping("/categories/{id}")
    public CategoryItemDTO getCategoryById(@PathVariable UUID id) {
        return this.categoryService.getCategoryById(id);
    }

    @PostMapping("/categories")
    public void addCategories(@RequestBody @Valid final AddListOfCategories request) {
        this.categoryService.addCategories(request.categories());
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable @NotNull final UUID id) {
        this.categoryService.deleteCategoryById(id);
    }
}
