package org.jakarta.data.demonstrations.repository;

import org.common.entities.dbentities.Customers;

import jakarta.data.repository.CrudRepository;

public interface OrderItemsRepository extends CrudRepository<Customers, Long> {

}
