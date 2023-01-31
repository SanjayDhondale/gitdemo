package com.sa.lab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sa.lab.entities.Employee;
import com.sa.lab.exception.EmployeeAlreadExistException;
import com.sa.lab.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee addEmployee(Employee employee) throws EmployeeAlreadExistException {
		Employee employeeToSave = null;
		if(employeeRepository.existsById(employee.getEmployeeId())) {
			throw new EmployeeAlreadExistException("An employee exist with this id");
		}
		employeeToSave = employeeRepository.save(employee);
		return employeeToSave;
	}
}
