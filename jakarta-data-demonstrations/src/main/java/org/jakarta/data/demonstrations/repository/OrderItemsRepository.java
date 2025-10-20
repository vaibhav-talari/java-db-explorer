package org.jakarta.data.demonstrations.repository;

import org.common.entities.dbentities.OrderItems;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface OrderItemsRepository extends CrudRepository<OrderItems, Long> {

}
