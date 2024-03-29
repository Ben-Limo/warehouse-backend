package com.teckmils.warehousemanagementsystem.domain.material.controller;

import com.teckmils.warehousemanagementsystem.domain.material.dto.AddListOfMaterials;
import com.teckmils.warehousemanagementsystem.domain.material.dto.MaterialStockDTO;
import com.teckmils.warehousemanagementsystem.domain.material.model.Material;
import com.teckmils.warehousemanagementsystem.domain.material.service.MaterialService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class MaterialController {
    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @RequestMapping(path = "/materials", method = RequestMethod.GET)
    public List<MaterialStockDTO> getMaterials() {
        return this.materialService.getMaterials();
    }

    @GetMapping("/materials/{id}")
    public MaterialStockDTO getMaterialById(@PathVariable UUID id) {
        return this.materialService.getMaterialById(id);
    }

    @PostMapping("/materials")
    public void addMaterials(@RequestBody @Valid final AddListOfMaterials materials) {
        this.materialService.addMaterials(materials.inventory());
    }

    @DeleteMapping("/materials/{id}")
    public void deleteMaterial(@PathVariable @NotNull final UUID id) {
        this.materialService.deleteMaterialById(id);
    }
}
