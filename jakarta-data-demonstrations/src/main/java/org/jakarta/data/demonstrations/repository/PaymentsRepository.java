package org.jakarta.data.demonstrations.repository;

import org.common.entities.dbentities.Payments;

import jakarta.data.repository.CrudRepository;

public interface PaymentsRepository extends CrudRepository<Payments, Long> {

}
