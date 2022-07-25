package com.teckmils.warehousemanagementsystem.domain.product.repository;

import com.teckmils.warehousemanagementsystem.domain.product.model.ProductMaterial;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.UUID;

public interface ProductMaterialRepository extends CrudRepository<ProductMaterial, UUID> {
    List<ProductMaterial> findAll();

    void deleteByMaterialId(@NonNull UUID id);
}
