package com.teckmils.warehousemanagementsystem.data.product.repository;

import com.teckmils.warehousemanagementsystem.data.product.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
