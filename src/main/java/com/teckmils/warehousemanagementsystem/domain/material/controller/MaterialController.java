package com.teckmils.warehousemanagementsystem.domain.material.controller;

import com.teckmils.warehousemanagementsystem.domain.material.model.Material;
import com.teckmils.warehousemanagementsystem.domain.material.service.MaterialService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MaterialController {
    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @RequestMapping(path = "/materials", method = RequestMethod.GET)
    public List<Material> getMaterials() {
        return this.materialService.getMaterials();
    }

    @GetMapping("/materials/{id}")
    public Optional<Material> getMaterialById(@PathVariable Long id) {
        return this.materialService.getMaterialById(id);
    }
}
