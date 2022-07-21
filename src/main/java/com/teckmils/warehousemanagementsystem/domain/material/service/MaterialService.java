package com.teckmils.warehousemanagementsystem.domain.material.service;

import com.teckmils.warehousemanagementsystem.domain.material.model.Material;
import com.teckmils.warehousemanagementsystem.domain.material.repository.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {
    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public List<Material> getMaterials() {
        Iterable<Material> materials = this.materialRepository.findAll();
        List<Material> materialList = new ArrayList<>();
        materials.forEach(material -> {
            materialList.add(material);});
        return materialList;
    }

    public Optional<Material> getMaterialById(Long id) {
        return this.materialRepository.findById(id);
    }
}
