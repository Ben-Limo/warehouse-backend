package com.teckmils.warehousemanagementsystem.domain.store.service;

import com.teckmils.warehousemanagementsystem.domain.category.dto.AddCategoryItem;
import com.teckmils.warehousemanagementsystem.domain.category.dto.CategoryItemDTO;
import com.teckmils.warehousemanagementsystem.domain.category.model.Category;
import com.teckmils.warehousemanagementsystem.domain.store.dto.AddStoreItem;
import com.teckmils.warehousemanagementsystem.domain.store.dto.StoreItemDTO;
import com.teckmils.warehousemanagementsystem.domain.store.model.Store;
import com.teckmils.warehousemanagementsystem.domain.store.repository.StoreRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class StoreService {
    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public List<StoreItemDTO> getStores() {
        final List<Store> stores = this.storeRepository.findAll();
        List<StoreItemDTO> StoreItemDTO = new ArrayList<>();
        stores.forEach(store -> StoreItemDTO.add(new StoreItemDTO(
                store.getId(),
                store.getStoreName(),
                store.getCity(),
                store.getCountry(),
                store.getCreatedAt(),
                store.getUpdatedAt()
        )));
        return StoreItemDTO;
    }

    public StoreItemDTO getStoreById(UUID id) {
        final Store store = this.storeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new StoreItemDTO(
                store.getId(),
                store.getStoreName(),
                store.getCity(),
                store.getCountry(),
                store.getCreatedAt(),
                store.getUpdatedAt()
        );
    }

    public void addStores(final Collection<AddStoreItem> storesReq) {
        storesReq.forEach(storeReq -> {
            final var store = new Store(storeReq.storeName(), storeReq.city(), storeReq.country());
            this.storeRepository.save(store);
        });
    }

    public void deleteStoreById(final UUID id) {
        this.storeRepository.deleteById(id);
    }
}
