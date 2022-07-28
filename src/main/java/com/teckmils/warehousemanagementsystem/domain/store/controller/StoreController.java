package com.teckmils.warehousemanagementsystem.domain.store.controller;

import com.teckmils.warehousemanagementsystem.domain.category.dto.AddListOfCategories;
import com.teckmils.warehousemanagementsystem.domain.category.dto.CategoryItemDTO;
import com.teckmils.warehousemanagementsystem.domain.store.dto.AddListOfStores;
import com.teckmils.warehousemanagementsystem.domain.store.dto.StoreItemDTO;
import com.teckmils.warehousemanagementsystem.domain.store.service.StoreService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @RequestMapping(path = "/stores", method = RequestMethod.GET)
    public List<StoreItemDTO> getStores() {
        return this.storeService.getStores();
    }

    @GetMapping("/stores/{id}")
    public StoreItemDTO getStoreById(@PathVariable UUID id) {
        return this.storeService.getStoreById(id);
    }

    @PostMapping("/stores")
    public void addStores(@RequestBody @Valid final AddListOfStores request) {
        this.storeService.addStores(request.stores());
    }

    @DeleteMapping("/stores/{id}")
    public void deleteStore(@PathVariable @NotNull final UUID id) {
        this.storeService.deleteStoreById(id);
    }

}
