package com.sa.lab.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sa.lab.entities.Employee;
import com.sa.lab.entities.Tickets;
import com.sa.lab.exception.EmployeeNotExistException;
import com.sa.lab.exception.TicketAlreadClosedException;
import com.sa.lab.exception.TicketNotExistException;
import com.sa.lab.repository.EmployeeRepository;
import com.sa.lab.repository.TicketRepository;

@Service
public class TicketService {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public String logATicket(Tickets tickets) throws EmployeeNotExistException {
		
		String employeeId = tickets.getLoggedBy();
		
		if(!employeeRepository.existsById(employeeId)) {
			throw new EmployeeNotExistException("Employee Doesn't exist with this id");
		}
		Employee employee = employeeRepository.findById(employeeId).get();
		tickets.setStatus("OPEN");
		tickets.setResolvedBy(null);
		tickets.setResolvedDate(null);
		tickets.setResolution(null);
		tickets.setRaisedDate(LocalDate.now());
		employee.getTickets().add(tickets);
		ticketRepository.save(tickets);
		employeeRepository.save(employee);
		return "Ticket " + tickets.getTicketId() +" is submitted Successfully";
	}
	
	public String closeATicket(Tickets ticket) throws TicketNotExistException, TicketAlreadClosedException {
		if(!ticketRepository.existsById(ticket.getTicketId())) {
			throw new TicketNotExistException("There is no ticket with this id");
		}
		Tickets tickets = ticketRepository.findById(ticket.getTicketId()).get();
		if(tickets.getStatus().equalsIgnoreCase("CLOSED")) {
			throw new TicketAlreadClosedException("The Ticket already closed");
		}
		tickets.setResolution(ticket.getResolution());
		tickets.setResolvedBy(ticket.getResolvedBy());
		tickets.setStatus("CLOSED");
		tickets.setResolvedDate(LocalDate.now());
		ticketRepository.save(tickets);
		return "The Ticket"+ tickets.getTicketId() +"Successfully Closed";
	}
	
	public List<Tickets> getClosedTickets(){
		return ticketRepository.getClosedTickets();
	}
}
