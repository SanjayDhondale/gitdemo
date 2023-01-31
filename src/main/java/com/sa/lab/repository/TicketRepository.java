package com.sa.lab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sa.lab.entities.Tickets;

@Repository
public interface TicketRepository extends JpaRepository<Tickets, Long>{
	
	@Query(value = "select tk from Tickets tk where tk.status = 'CLOSED'")
	public List<Tickets> getClosedTickets();
}
