package org.jakarta.data.demonstrations.repository;

import org.common.entities.dbentities.Products;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface ProductsRepository extends CrudRepository<Products, Long> {

}
