package com.teckmils.warehousemanagementsystem.domain.material.service;

import com.teckmils.warehousemanagementsystem.domain.material.dto.MaterialStockDTO;
import com.teckmils.warehousemanagementsystem.domain.material.model.Material;
import com.teckmils.warehousemanagementsystem.domain.material.model.MaterialStock;
import com.teckmils.warehousemanagementsystem.domain.material.repository.MaterialRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MaterialService {
    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public List<MaterialStockDTO> getMaterials() {
        return ((List<MaterialStockDTO>) materialRepository
                .findAll()
                .stream().
                map(this::convertDataIntoDTO).
                collect(Collectors.toList()));
    }



    public MaterialStockDTO getMaterialById(Long id) {
        final Material material = this.materialRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return convertDataIntoDTO(material);
    }



    private MaterialStockDTO convertDataIntoDTO (Material materialData) {
        MaterialStockDTO materialStockDTO = new MaterialStockDTO();

        materialStockDTO.setId(materialData.getId());
        materialStockDTO.setName(materialData.getName());
        materialStockDTO.setCreatedAt(materialData.getCreatedAt());
        materialStockDTO.setUpdatedAt(materialData.getUpdatedAt());

        //create instance of the MaterialStock class
        MaterialStock materialStock = materialData.getStock();

        // set the stock value
        materialStockDTO.setStock(materialStock.getStock());

        return materialStockDTO;
    }
}
