package org.jakarta.data.demonstrations.repository;

import org.common.entities.dbentities.Payments;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface PaymentsRepository extends CrudRepository<Payments, Long> {

}
