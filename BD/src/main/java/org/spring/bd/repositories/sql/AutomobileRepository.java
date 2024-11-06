package org.spring.bd.repositories.sql;

import org.spring.bd.entities.sql.Automobile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutomobileRepository  extends JpaRepository<Automobile, Integer> {
}
