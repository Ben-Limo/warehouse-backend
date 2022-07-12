package com.teckmils.warehousemanagementsystem.data.part.repository;

import com.teckmils.warehousemanagementsystem.data.part.model.Part;
import com.teckmils.warehousemanagementsystem.data.part.model.PartStock;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PartStockRepository extends CrudRepository<PartStock, Long> {
    List<PartStock> findAll();
}
