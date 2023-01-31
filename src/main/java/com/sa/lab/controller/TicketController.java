package com.sa.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sa.lab.entities.Tickets;
import com.sa.lab.exception.EmployeeNotExistException;
import com.sa.lab.service.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController {

	@Autowired
	private TicketService service;

	@PostMapping("/ticket/log")
	public ResponseEntity<?> logATicket(@RequestBody Tickets tickets) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(service.logATicket(tickets), HttpStatus.OK);
		} catch (EmployeeNotExistException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return response;
	}

	@PutMapping("/ticket/close")
	public ResponseEntity<?> closeATicket(@RequestBody Tickets tickets) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(service.closeATicket(tickets), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return response;
	}

	@GetMapping("/ticket/view/closed")
	public ResponseEntity<?> getClosedTickets() {
		ResponseEntity<?> response = new ResponseEntity<>(service.getClosedTickets(), HttpStatus.OK);
		/*
		 * // System.out.println(response.getBody().equals(null));
		 * 
		 * if (response.getBody().equals(null)) { return new
		 * ResponseEntity<>("There is no closed Ticket", HttpStatus.OK); } else { return
		 * response; }
		 */

		 return response;
	}
}
