package com.ahom.hrms.entities;

import com.ahom.hrms.constant.PrefixAndSequence;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import org.hibernate.annotations.Parameter;


@Entity
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "branch_seq")
    @GenericGenerator(name = "branch_seq",
    strategy = "com.ahom.hrms.constant.PrefixAndSequence",
    parameters = {
            @Parameter(name = PrefixAndSequence.INCREMENT_PARAM,value = "1"),
            @Parameter(name = PrefixAndSequence.VALUE_PREFIX_PARAMETER, value = "Branch_"),
            @Parameter(name = PrefixAndSequence.NUMBER_FORMAT_PARAMETER,value = "%03d")
    })
    private String id;
    @NotBlank(message = "Branch Can`t be empty")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
