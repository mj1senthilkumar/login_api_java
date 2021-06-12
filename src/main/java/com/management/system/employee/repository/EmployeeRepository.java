package com.management.system.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.system.employee.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {}
