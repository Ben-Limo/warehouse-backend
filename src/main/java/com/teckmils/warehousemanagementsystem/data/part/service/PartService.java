package com.teckmils.warehousemanagementsystem.data.part.service;

import com.teckmils.warehousemanagementsystem.data.part.model.Part;
import com.teckmils.warehousemanagementsystem.data.part.repository.PartRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartService {
    private final PartRepository partRepository;

    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    public List<Part> getParts() {
        Iterable<Part> parts = this.partRepository.findAll();
        List<Part> partList = new ArrayList<>();
        parts.forEach(part -> {partList.add(part);});
        return  partList;
    }
}
