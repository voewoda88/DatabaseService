package org.spring.bd.repositories.sql;

import org.spring.bd.entities.sql.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
