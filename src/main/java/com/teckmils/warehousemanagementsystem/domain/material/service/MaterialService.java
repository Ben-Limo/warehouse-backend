package com.teckmils.warehousemanagementsystem.domain.material.service;

import com.teckmils.warehousemanagementsystem.domain.material.dto.AddMaterialStockItem;
import com.teckmils.warehousemanagementsystem.domain.material.dto.MaterialStockDTO;
import com.teckmils.warehousemanagementsystem.domain.material.model.Material;
import com.teckmils.warehousemanagementsystem.domain.material.model.MaterialStock;
import com.teckmils.warehousemanagementsystem.domain.material.repository.MaterialRepository;
import com.teckmils.warehousemanagementsystem.domain.material.repository.MaterialStockRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MaterialService {
    private final MaterialRepository materialRepository;
    private final MaterialStockRepository materialStockRepository;
    public MaterialService(MaterialRepository materialRepository, MaterialStockRepository materialStockRepository) {
        this.materialRepository = materialRepository;
        this.materialStockRepository = materialStockRepository;
    }

    public List<MaterialStockDTO> getMaterials() {
        final List<Material> materials = this.materialRepository.findAll();
        List<MaterialStockDTO> materialStockDTOS = new ArrayList<>();
        materials.forEach(material -> materialStockDTOS.add(new MaterialStockDTO(
                        material.getId(),
                        material.getName(),
                        material.getStock(),
                        material.getCreatedAt(),
                        material.getUpdatedAt()
        )));

        return materialStockDTOS;
    }



    public MaterialStockDTO getMaterialById(Long id) {
        final Material material = this.materialRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new MaterialStockDTO(
                material.getId(),
                material.getName(),
                material.getStock(),
                material.getCreatedAt(),
                material.getUpdatedAt()
        );
    }

    private void addSingleMaterial(final AddMaterialStockItem rawMaterial) {
        var material = new Material(rawMaterial.name());
        var materialStock = new MaterialStock(material, rawMaterial.stock());

        System.out.println(material.toString());
        this.materialRepository.save(material);
        System.out.println(materialStock.toString());
        this.materialStockRepository.save(materialStock);
    }

    public void addMaterials(final Collection<AddMaterialStockItem> rawMaterials) {
        rawMaterials.forEach(this::addSingleMaterial);
    }


}
