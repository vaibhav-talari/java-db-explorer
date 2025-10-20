package org.jakarta.data.demonstrations.repository;

import org.common.entities.dbentities.Orders;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface OrdersRepository extends CrudRepository<Orders, Long> {

}
