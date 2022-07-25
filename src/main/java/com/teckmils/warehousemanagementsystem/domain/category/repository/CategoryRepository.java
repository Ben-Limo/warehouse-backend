package com.teckmils.warehousemanagementsystem.domain.category.repository;

import com.teckmils.warehousemanagementsystem.domain.category.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends CrudRepository<Category, UUID> {
    Optional<Category> findById(UUID Id);

    List<Category> findAll();

    void deleteById(@NonNull UUID id);
}
