package com.teckmils.warehousemanagementsystem.domain.customer.repository;

import com.teckmils.warehousemanagementsystem.domain.customer.model.CustomerType;
import com.teckmils.warehousemanagementsystem.domain.user.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerTypeRepository extends CrudRepository<CustomerType, UUID> {
    Optional<CustomerType> findByItemName(String itemName);
}
