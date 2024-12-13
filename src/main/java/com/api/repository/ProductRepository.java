package com.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
