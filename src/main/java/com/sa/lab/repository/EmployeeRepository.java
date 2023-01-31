package com.sa.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sa.lab.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
