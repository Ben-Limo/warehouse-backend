package com.teckmils.warehousemanagementsystem.domain.store.repository;

import com.teckmils.warehousemanagementsystem.domain.category.model.Category;
import com.teckmils.warehousemanagementsystem.domain.store.model.Store;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StoreRepository extends CrudRepository<Store, UUID> {
    Optional<Store> findById(UUID id);

    List<Store> findAll();

    void deleteById(@NonNull UUID id);
}
