package org.spring.bd.repositories.sql;

import org.spring.bd.entities.sql.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer, Integer> {
}
