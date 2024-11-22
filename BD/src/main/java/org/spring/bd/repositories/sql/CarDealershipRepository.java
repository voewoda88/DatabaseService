package org.spring.bd.repositories.sql;

import org.spring.bd.entities.sql.CarDealership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDealershipRepository extends JpaRepository<CarDealership, Integer> {
}
