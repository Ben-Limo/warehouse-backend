package com.teckmils.warehousemanagementsystem.domain.material.repository;

import com.teckmils.warehousemanagementsystem.domain.material.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MaterialRepository extends JpaRepository<Material, UUID> {

    Optional<Material> findById(UUID id);

    List<Material> findAll();

    void deleteById(@NonNull UUID id);
}
