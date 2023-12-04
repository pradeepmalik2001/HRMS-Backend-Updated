package com.ahom.hrms.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ahom.hrms.entities.RaiseTicket;
import com.ahom.hrms.service.RaiseTicketService;

@RestController
@RequestMapping("/ticket")
public class RaiseTicketController {
    @Autowired
    private RaiseTicketService ticketService;

    @PostMapping("/raise")
    public ResponseEntity<RaiseTicket> raiseNewTicket(@RequestBody RaiseTicket ticket) {
        RaiseTicket createdTicket = ticketService.raiseTicket(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTicket);
    }

    @PutMapping("/updateticket/{ticketId}")
    public ResponseEntity<RaiseTicket> updateTicketStatus(
            @PathVariable Long ticketId,
            @RequestParam String status) {
        RaiseTicket updatedTicket = ticketService.updateTicketStatus(ticketId, status);
        if (updatedTicket != null) {
            return ResponseEntity.ok(updatedTicket);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<RaiseTicket>> getAllRaisedTickets() {
        List<RaiseTicket> raisedTickets = ticketService.getAllRaisedTickets();
        return ResponseEntity.ok(raisedTickets);
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<Optional<RaiseTicket>> getRaisedTicketById(@PathVariable Long ticketId) {
        Optional<RaiseTicket> ticket = ticketService.getRaisedTicketById(ticketId);
        if (ticket != null) {
            return ResponseEntity.ok(ticket);
        } else {
            return ResponseEntity.notFound().build();
      }
    }
}
