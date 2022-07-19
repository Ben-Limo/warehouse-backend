package com.teckmils.warehousemanagementsystem.domain.part.service;

import com.teckmils.warehousemanagementsystem.domain.part.model.Part;
import com.teckmils.warehousemanagementsystem.domain.part.repository.PartRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<Part> getPartById(Long id) {
        return this.partRepository.findById(id);
    }
}
