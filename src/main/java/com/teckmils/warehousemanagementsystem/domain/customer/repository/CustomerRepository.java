package com.teckmils.warehousemanagementsystem.domain.customer.repository;

import com.teckmils.warehousemanagementsystem.domain.customer.model.Customer;
import com.teckmils.warehousemanagementsystem.domain.user.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, UUID> {
    @NonNull
    Optional<Customer> findById(@NonNull UUID uid);

    @NonNull
    List<Customer> findAll();

    void deleteById(@NonNull UUID uid);

    Optional<Customer> findByEmail(String email);

    Boolean existsByEmail(String email);
}
