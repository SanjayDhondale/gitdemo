package com.sa.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sa.lab.entities.Employee;
import com.sa.lab.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@PostMapping("/employee/add")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee){
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(service.addEmployee(employee), HttpStatus.CREATED);
		} catch (Exception e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return response;
	}
}
