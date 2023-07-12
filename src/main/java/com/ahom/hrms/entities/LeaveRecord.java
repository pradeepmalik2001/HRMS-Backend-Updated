package com.ahom.hrms.entities;

import com.ahom.hrms.constant.PrefixAndSequence;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRecord
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leaveId;
    private String employeeId;
    private String employeeName;
    private double totalLeave;
    private double leaveLeft;
    private double leaveTaken;
    private double lop;
    @DateTimeFormat(pattern = "MMMM yyyy")
    private String leaveRecordOfMonth;

}
