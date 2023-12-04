package com.ahom.hrms.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahom.hrms.Repository.RaiseTicketRepository;
import com.ahom.hrms.entities.RaiseTicket;


@Service
public class RaiseTicketService {
    @Autowired
    private RaiseTicketRepository ticketRepository;

    public RaiseTicket raiseTicket(RaiseTicket ticket) {
        return ticketRepository.save(ticket);
    }

    public RaiseTicket updateTicketStatus(Long ticketId, String status) {
        RaiseTicket ticket = ticketRepository.findById(ticketId)
            .orElseThrow(() -> new EntityNotFoundException("Ticket not found"));
        ticket.setStatus(status);
        return ticketRepository.save(ticket);
    }

    public List<RaiseTicket> getAllRaisedTickets() {
        return ticketRepository.findAll();
    }

    public Optional<RaiseTicket> getRaisedTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId);
    }
}
