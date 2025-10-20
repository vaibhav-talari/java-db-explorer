package org.jakarta.data.demonstrations.repository;

import org.common.entities.dbentities.Customers;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface CustomersRepository extends CrudRepository<Customers, Long> {

}
