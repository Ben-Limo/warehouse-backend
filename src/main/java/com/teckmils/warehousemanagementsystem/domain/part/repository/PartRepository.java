package com.teckmils.warehousemanagementsystem.domain.part.repository;

import com.teckmils.warehousemanagementsystem.domain.part.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {

    Optional<Part> findById(Long id);

    List<Part> findAll();
}
