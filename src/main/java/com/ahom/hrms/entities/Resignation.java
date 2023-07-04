package com.ahom.hrms.entities;

import com.ahom.hrms.constant.PrefixAndSequence;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class Resignation
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "branch_seq")
    @GenericGenerator(name = "branch_seq",
            strategy = "com.ahom.hrms.constant.PrefixAndSequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = PrefixAndSequence.INCREMENT_PARAM,value = "1"),
                    @org.hibernate.annotations.Parameter(name = PrefixAndSequence.VALUE_PREFIX_PARAMETER, value = "RESG_"),
                    @org.hibernate.annotations.Parameter(name = PrefixAndSequence.NUMBER_FORMAT_PARAMETER,value = "%03d")
            })
    @NotEmpty(message = "Id can`t be null")
    private String id;

    @NotEmpty(message = "Employee Name can`t be null")
    private String employeeName;

    @NotEmpty(message = "Please write something here")
    private String description;
}
