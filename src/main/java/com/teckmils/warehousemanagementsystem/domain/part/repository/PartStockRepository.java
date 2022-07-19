package com.teckmils.warehousemanagementsystem.domain.part.repository;

import com.teckmils.warehousemanagementsystem.domain.part.model.PartStock;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PartStockRepository extends CrudRepository<PartStock, Long> {
    List<PartStock> findAll();
}
