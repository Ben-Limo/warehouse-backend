package com.teckmils.warehousemanagementsystem.data.part.controller;

import com.teckmils.warehousemanagementsystem.data.part.model.Part;
import com.teckmils.warehousemanagementsystem.data.part.service.PartService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
