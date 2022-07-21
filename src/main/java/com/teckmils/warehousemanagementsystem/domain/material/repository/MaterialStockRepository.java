package com.teckmils.warehousemanagementsystem.domain.material.repository;

import com.teckmils.warehousemanagementsystem.domain.material.model.MaterialStock;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MaterialStockRepository extends CrudRepository<MaterialStock, Long> {
    List<MaterialStock> findAll();
}
