package com.ahom.hrms.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RaiseTicket{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String selectEmployee;
    private String ticket;
    private String status;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSelectEmployee() {
        return selectEmployee;
    }
    public void setSelectEmployee(String selectEmployee) {
        this.selectEmployee = selectEmployee;
    }
    public String getTicket() {
        return ticket;
    }
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public RaiseTicket(Long id, String selectEmployee, String ticket, String status) {
        this.id = id;
        this.selectEmployee = selectEmployee;
        this.ticket = ticket;
        this.status = status;
    }
    public RaiseTicket() {
    }
  
      
}
