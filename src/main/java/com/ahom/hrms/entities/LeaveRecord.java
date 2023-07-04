package com.ahom.hrms.entities;

import com.ahom.hrms.constant.PrefixAndSequence;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Data
public class LeaveRecord
{
    @Id
    private String id;
    private String employeeName;
    private double totalLeave;
    private double leaveLeft;
    private double leaveTaken;
    private double lop;

}
