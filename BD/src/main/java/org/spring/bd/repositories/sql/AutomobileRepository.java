package org.spring.bd.repositories.sql;

import org.spring.bd.entities.sql.Automobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomobileRepository  extends JpaRepository<Automobile, Integer> {
}
