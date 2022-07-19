package com.teckmils.warehousemanagementsystem.domain.part.controller;

import com.teckmils.warehousemanagementsystem.domain.part.model.Part;
import com.teckmils.warehousemanagementsystem.domain.part.service.PartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PartController {
    private final PartService partService;

    public PartController(PartService partService) {
        this.partService = partService;
    }

    @RequestMapping(path = "/parts", method = RequestMethod.GET)
    public List<Part> getParts() {
        return this.partService.getParts();
    }

    @GetMapping("/parts/{id}")
    public Optional<Part> getPart(@PathVariable Long id) {
        return this.partService.getPartById(id);
    }
}
