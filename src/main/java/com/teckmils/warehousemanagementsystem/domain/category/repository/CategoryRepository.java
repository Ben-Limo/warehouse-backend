package com.teckmils.warehousemanagementsystem.domain.category.repository;

import com.teckmils.warehousemanagementsystem.domain.category.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findById(Long Id);

    List<Category> findAll();
}
