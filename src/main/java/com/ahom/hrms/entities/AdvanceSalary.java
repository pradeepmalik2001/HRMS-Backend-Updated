package com.ahom.hrms.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
public class AdvanceSalary
{
    @Id
    private String id;
    private String employeeName;
    private LocalDate date;
    private double advance;
}
