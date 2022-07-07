package com.teckmils.warehousemanagementsystem.data.part.repository;

import com.teckmils.warehousemanagementsystem.data.part.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PartRepository extends JpaRepository<Part, UUID> {
}
