package org.spring.bd.repositories.sql;

import org.spring.bd.entities.sql.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
