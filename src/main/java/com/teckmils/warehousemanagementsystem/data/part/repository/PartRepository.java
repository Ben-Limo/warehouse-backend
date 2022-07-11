package com.teckmils.warehousemanagementsystem.data.part.repository;

import com.teckmils.warehousemanagementsystem.data.part.model.Part;
import com.teckmils.warehousemanagementsystem.data.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {

    Optional<Part> findById(Long id);

    List<Part> findAll();
}
