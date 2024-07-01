package ems.employeemanagementsystem.repository;

import ems.employeemanagementsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Employee entities.
 * Extends JpaRepository to provide CRUD operations and additional methods for Employee persistence.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
