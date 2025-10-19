package org.jakarta.data.demonstrations.repository;

import org.common.entities.dbentities.OrderItems;

import jakarta.data.repository.CrudRepository;

public interface CustomersRepository extends CrudRepository<OrderItems, Long> {

}
