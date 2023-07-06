package com.ahom.hrms.entities;

import com.ahom.hrms.constant.PrefixAndSequence;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
public class AdvanceSalary
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "branch_seq1")
    @GenericGenerator(name = "branch_seq1",
            strategy = "com.ahom.hrms.constant.PrefixAndSequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = PrefixAndSequence.INCREMENT_PARAM,value = "1"),
                    @org.hibernate.annotations.Parameter(name = PrefixAndSequence.VALUE_PREFIX_PARAMETER, value = "ADV_"),
                    @org.hibernate.annotations.Parameter(name = PrefixAndSequence.NUMBER_FORMAT_PARAMETER,value = "%03d")
            })
    private String id;
    private String employeeId;
    private String employeeName;
    private LocalDate date;
    private double advance;
    private double remainingAdvance;
    private double amountToPayWithinMonth;
    private double amountToPayPerMonth;
    private boolean status;
}
