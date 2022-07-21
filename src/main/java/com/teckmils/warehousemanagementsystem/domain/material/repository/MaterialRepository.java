package com.teckmils.warehousemanagementsystem.domain.material.repository;

import com.teckmils.warehousemanagementsystem.domain.material.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

    Optional<Material> findById(Long id);

    List<Material> findAll();
}
