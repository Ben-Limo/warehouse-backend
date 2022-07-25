package com.teckmils.warehousemanagementsystem.domain.material.repository;

import com.teckmils.warehousemanagementsystem.domain.material.model.MaterialStock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public interface MaterialStockRepository extends CrudRepository<MaterialStock, UUID> {
    List<MaterialStock> findAll();

    void deleteByMaterialId(@NonNull UUID id);
}
