package org.jakarta.data.demonstrations.repository;

import org.common.entities.dbentities.Products;

import jakarta.data.repository.CrudRepository;

public interface ProductsRepository extends CrudRepository<Products, Long> {

}
