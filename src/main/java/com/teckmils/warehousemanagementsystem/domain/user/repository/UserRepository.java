package com.teckmils.warehousemanagementsystem.domain.user.repository;

import com.teckmils.warehousemanagementsystem.domain.user.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    @NonNull
    Optional<User> findById(@NonNull UUID uid);

    @NonNull
    List<User> findAll();

    void deleteById(@NonNull UUID uid);

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    Optional<User> findByToken(String token);
}
