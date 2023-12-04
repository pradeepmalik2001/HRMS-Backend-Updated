package com.ahom.hrms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahom.hrms.entities.RaiseTicket;

public interface RaiseTicketRepository extends JpaRepository<RaiseTicket, Long>{
    
}
