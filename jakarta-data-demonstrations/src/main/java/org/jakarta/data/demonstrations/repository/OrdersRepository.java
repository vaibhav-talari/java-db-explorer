package org.jakarta.data.demonstrations.repository;

import org.common.entities.dbentities.Orders;

import jakarta.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders, Long> {

}
