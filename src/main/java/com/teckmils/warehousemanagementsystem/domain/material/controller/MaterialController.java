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
    public List<Material> getParts() {
        return this.materialService.getParts();
    }

    @GetMapping("/materials/{id}")
    public Optional<Material> getPart(@PathVariable Long id) {
        return this.materialService.getPartById(id);
    }
}
